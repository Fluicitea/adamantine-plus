package shadowvappy.adamantineplus.blocks.machines.forge;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.lwjgl.input.Mouse;
import shadowvappy.adamantineplus.data.APModItems;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TileEntityAdamantineForge extends TileEntity implements ITickable {
    private final ItemStackHandler handler = new ItemStackHandler(3);
    private boolean mouseDown = false;
    public static int mouseX, mouseY;

    public ItemStack result = new ItemStack(APModItems.adamantineSword);
    public int burnTime, currentBurnTime, cookTime, totalCookTime = 200;
    public boolean isCurrentlyActive = false;

    private final String TAG_BURN_TIME = "BurnTime", TAG_CURRENT_BURN_TIME = "CurrentBurnTime", TAG_COOK_TIME = "CookTime", TAG_TOTAL_COOK_TIME = "TotalCookTime";
    private final String INVENTORY_KEY = "Inventory";
    private final String TAG_RESULT = "CurrentResult";
    private final String TAG_ACTIVE = "IsCurrentlyActive";

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return true;
        return super.hasCapability(capability, facing);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return (T)this.handler;
        return super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.handler.deserializeNBT(compound.getCompoundTag(INVENTORY_KEY));
        this.burnTime = compound.getInteger(TAG_BURN_TIME);
        this.cookTime = compound.getInteger(TAG_COOK_TIME);
        this.totalCookTime = compound.getInteger(TAG_TOTAL_COOK_TIME);
        this.currentBurnTime = compound.getInteger(TAG_CURRENT_BURN_TIME);
        this.isCurrentlyActive = compound.getBoolean(TAG_ACTIVE);
        NBTTagCompound compoundStack = compound.getCompoundTag(TAG_RESULT);
        this.result = new ItemStack(compoundStack);
    }
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger(TAG_BURN_TIME, this.burnTime);
        compound.setInteger(TAG_COOK_TIME, this.cookTime);
        compound.setInteger(TAG_TOTAL_COOK_TIME, this.totalCookTime);
        compound.setInteger(TAG_CURRENT_BURN_TIME, this.currentBurnTime);
        compound.setBoolean(TAG_ACTIVE, this.isCurrentlyActive);
        compound.setTag(INVENTORY_KEY, this.handler.serializeNBT());
        NBTTagCompound compoundStack = this.result.writeToNBT(new NBTTagCompound());
        compound.setTag(TAG_RESULT, compoundStack);

        return compound;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentTranslation("container.adamantine_forge");
    }

    /**
     * Checks if the player can use the tile entity
     * @param player The player to check.
     * @return True if the player is within range to use the tile entity, else false.
     */
    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public void setState(boolean active, World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        TileEntity tileEntity = world.getTileEntity(pos);

        if(active != state.getValue(BlockAdamantineForge.BURNING)) {
            if(active)
                world.setBlockState(pos, state.withProperty(BlockAdamantineForge.BURNING, true));
            else
                world.setBlockState(pos, state.withProperty(BlockAdamantineForge.BURNING, false));
        }

        if(tileEntity != null)
            tileEntity.validate();
    }

    public void update() {
        ItemStack fuel = this.handler.getStackInSlot(1); //Gets an item stack containing the stack in the fuel slot#

        if(this.result == ItemStack.EMPTY)
            this.setResult(new ItemStack(APModItems.adamantineSword));
        if(!this.isBurning())
            setState(false, this.getWorld(), this.getPos()); //Sets the state of the forge to not burning when it isn't burning
        if(this.isBurning() || !fuel.isEmpty()) {
            if(this.isCurrentlyActive && !(getCookTime() > 0)) {
                int inputCount = AdamantineForgeRecipes.getInstance().getInputCount(this.result); //Gets the input count of the recipe
                this.cookTime++; //Increment cook time
                this.setTotalCookTime(this.getCookTime(this.result)); //Sets the total cook time to the cook time of the recipe
                this.handler.getStackInSlot(0).shrink(inputCount); //Decrease the stack in the input slot by the input count
            }
            if(this.getCookTime() > 0) {
                this.burnTime--; //Reduces the burn time by one if the forge is burning (reduces the time left fueled by one tick)
                this.cookTime++; //Increment cook time
                if(!this.isBurning()) { //If the forge isn't burning and can smelt or is smelting
                    this.setBurnTime(TileEntityFurnace.getItemBurnTime(fuel)); //Set the burn time to the burn time of the fuel
                    this.setCurrentBurnTime(this.getBurnTime()); //Set the current burn time to the burn time
                    setState(true, this.getWorld(), this.getPos()); //Sets the state of the forge to burning when it first starts burning

                    if(this.isBurning() && !fuel.isEmpty()) { //If the forge is active and fuel isn't empty
                        if(fuel.getItem() == Items.LAVA_BUCKET) //If fuel is a lava bucket
                            this.handler.setStackInSlot(1, new ItemStack(Items.BUCKET)); //Sets the stack in the fuel slot to an empty bucket
                    }
                }
                if(this.getResult() != ItemStack.EMPTY) {
                    if(this.getCookTime() == this.getTotalCookTime()) { //If cook time is total cook time
                        if(!(this.handler.getStackInSlot(2).getCount() > 0)) //If the output slot is empty
                            this.handler.insertItem(2, this.result, false); //Insert the recipe result into the output slot
                        this.setTotalCookTime(0); //Set the total cook time to 0
                        this.setCookTime(0); //Set the cook time to 0
                        this.setCurrentlyActive(false); //Sets inactive
                    }
                }
            }
        }
        this.onClick();
    }

    private void onClick() {
        if(Mouse.isButtonDown(0) && !mouseDown) {
            mouseDown = true;
            if(Minecraft.getMinecraft().currentScreen instanceof GuiAdamantineForge) {
                GuiAdamantineForge gui = (GuiAdamantineForge)Minecraft.getMinecraft().currentScreen;

                if(mouseX > gui.getGuiLeft()+62 && mouseX < gui.getGuiLeft()+85) {
                    if(mouseY > gui.getGuiTop()+50 && mouseY < gui.getGuiTop()+61) {
                        if(!this.isCurrentlyActive && this.canSmelt())
                            this.setCurrentlyActive(true);
                    }
                }else if(mouseX > gui.getGuiLeft()+94 && mouseX < gui.getGuiLeft()+130) {
                    if(mouseY > gui.getGuiTop()+10 && mouseY < gui.getGuiTop()+22) {
                        if(!this.isCurrentlyActive)
                            this.setResult(new ItemStack(APModItems.adamantineSword));
                    }else if(mouseY > gui.getGuiTop()+23 && mouseY < gui.getGuiTop()+35) {
                        if(!this.isCurrentlyActive)
                            this.setResult(new ItemStack(APModItems.adamantinePickaxe));
                    }else if(mouseY > gui.getGuiTop()+36 && mouseY < gui.getGuiTop()+48) {
                        if(!this.isCurrentlyActive)
                            this.setResult(new ItemStack(APModItems.adamantineAxe));
                    }else if(mouseY > gui.getGuiTop()+49 && mouseY < gui.getGuiTop()+61) {
                        if(!this.isCurrentlyActive)
                            this.setResult(new ItemStack(APModItems.adamantineShovel));
                    }else if(mouseY > gui.getGuiTop()+62 && mouseY < gui.getGuiTop()+74) {
                        if(!this.isCurrentlyActive)
                            this.setResult(new ItemStack(APModItems.adamantineHoe));
                    }
                }else if(mouseX > gui.getGuiLeft()+132 && mouseX < gui.getGuiLeft()+168) {
                    if(mouseY > gui.getGuiTop()+17 && mouseY < gui.getGuiTop()+29) {
                        if(!this.isCurrentlyActive)
                            this.setResult(new ItemStack(APModItems.adamantineHelmet));
                    }else if(mouseY > gui.getGuiTop()+30 && mouseY < gui.getGuiTop()+42) {
                        if(!this.isCurrentlyActive)
                            this.setResult(new ItemStack(APModItems.adamantineChestplate));
                    }else if(mouseY > gui.getGuiTop()+43 && mouseY < gui.getGuiTop()+55) {
                        if(!this.isCurrentlyActive)
                            this.setResult(new ItemStack(APModItems.adamantineLeggings));
                    }else if(mouseY > gui.getGuiTop()+56 && mouseY < gui.getGuiTop()+68) {
                        if(!this.isCurrentlyActive)
                            this.setResult(new ItemStack(APModItems.adamantineBoots));
                    }
                }
            }
        }
        if(!Mouse.isButtonDown(0) && mouseDown) {
            mouseDown = false;
        }
    }

    /**
     * Checks whether the tile entity is active.
     * @return True if the burn time is greater than 0, else false.
     */
    public boolean isBurning() {
        return this.burnTime > 0;
    }

    /**
     * Checks whether the tile entity is active.
     * @param te The tile entity.
     * @return True if the burnTime field of the tile entity is greater than 0, else false.
     */
    @SideOnly(Side.CLIENT)
    public static boolean isBurning(TileEntityAdamantineForge te) {
        return te.burnTime > 0;
    }

    /**
     * Gets the cook time for the recipe with the given input
     * @param result The result of the recipe.
     * @return The cook time for the recipe.
     */
    public int getCookTime(ItemStack result) {
        return AdamantineForgeRecipes.getInstance().getForgingTime(result);
    }

    /**
     * Checks whether the recipe is valid and the output slot can fit the result.
     * @return True if the recipe is valid and the output slot is not full, else false.
     */
    public boolean canSmelt() {
        if(this.handler.getStackInSlot(0).isEmpty())
            return false;
        else {
            if(this.result.isEmpty())
                return false;
            else {
                ItemStack input = this.handler.getStackInSlot(0);
                ItemStack output = this.handler.getStackInSlot(2);
                ItemStack recipeInput = AdamantineForgeRecipes.getInstance().getForgingInput(this.result);
                int inputCount = AdamantineForgeRecipes.getInstance().getInputCount(this.result);

                if(input.getItem() == recipeInput.getItem() && input.getCount() >= inputCount)
                    return output.isEmpty();
                else
                    return false;
            }
        }
    }

    public int getBurnTime() {
        return this.burnTime;
    }
    public void setBurnTime(int burnTime) {
        this.burnTime = burnTime;
    }
    public int getCurrentBurnTime() {
        return this.currentBurnTime;
    }
    public void setCurrentBurnTime(int currentBurnTime) {
        this.currentBurnTime = currentBurnTime;
    }

    public int getCookTime() {
        return this.cookTime;
    }
    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }
    public int getTotalCookTime() {
        return this.totalCookTime;
    }
    public void setTotalCookTime(int totalCookTime) {
        this.totalCookTime = totalCookTime;
    }

    public boolean isCurrentlyActive() {
        return this.isCurrentlyActive;
    }
    public void setCurrentlyActive(boolean isCurrentlyActive) {
        this.isCurrentlyActive = isCurrentlyActive;
    }

    public ItemStack getResult() {
        return this.result;
    }
    public void setResult(ItemStack result) {
        this.result = result;
    }
}