package shadowvappy.adamantineplus.blocks.machines.weaver;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.state.IBlockState;
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

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TileEntityWaferWeaver extends TileEntity implements ITickable {
    private final ItemStackHandler handler = new ItemStackHandler(3);

    public ItemStack result = ItemStack.EMPTY;
    public int burnTime, currentBurnTime, cookTime, totalCookTime = 200;

    private final String TAG_BURN_TIME = "BurnTime", TAG_CURRENT_BURN_TIME = "CurrentBurnTime", TAG_COOK_TIME = "CookTime", TAG_TOTAL_COOK_TIME = "TotalCookTime";
    private final String INVENTORY_KEY = "Inventory";
    private final String TAG_RESULT = "CurrentResult";

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
        return new TextComponentTranslation("container.wafer_weaver");
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

        if(active != state.getValue(BlockWaferWeaver.BURNING)) {
            if(active)
                world.setBlockState(pos, state.withProperty(BlockWaferWeaver.BURNING, true));
            else
                world.setBlockState(pos, state.withProperty(BlockWaferWeaver.BURNING, false));
        }

        if(tileEntity != null)
            tileEntity.validate();
    }

    public void update() {
        ItemStack input = this.handler.getStackInSlot(0); //Gets an item stack containing the stack in the input slot
        ItemStack fuel = this.handler.getStackInSlot(1); //Gets an item stack containing the stack in the fuel slot

        if(!this.isBurning())
            setState(false, this.getWorld(), this.getPos()); //Sets the state of the strand extractor to not burning when it isn't burning
        if(this.isBurning() || !fuel.isEmpty()) {
            this.burnTime--; //Reduces the burn time by one if the extractor is burning (reduces the time left fueled by one tick)
            if(!this.handler.getStackInSlot(0).isEmpty() || this.getCookTime() > 0) { //If input slot is not empty or is extracting
                if(!this.isBurning() && (this.canSmelt() || this.getCookTime() > 0)) { //If the strand extractor isn't active and can smelt or is smelting
                    this.setBurnTime(TileEntityFurnace.getItemBurnTime(fuel)); //Set the burn time to the burn time of the fuel
                    this.setCurrentBurnTime(this.getBurnTime()); //Set the current burn time to the burn time
                    setState(true, this.getWorld(), this.getPos()); //Sets the state of the strand extractor to burning when it first starts burning

                    if(this.isBurning() && !fuel.isEmpty()) { //If the strand extractor is active and fuel isn't empty
                        if(fuel.getItem() != Items.LAVA_BUCKET) //If fuel isn't a lava bucket
                            fuel.shrink(1); //Reduce the fuel count by one
                        else
                            this.handler.setStackInSlot(1, new ItemStack(Items.BUCKET)); //Sets the stack in the fuel slot to an empty bucket

                        if(fuel.isEmpty()) //If the fuel stack is empty
                            this.handler.setStackInSlot(1, ItemStack.EMPTY); //Sets the stack in the fuel slot to the empty item stack
                    }
                }
            }
            if(this.getResult() != ItemStack.EMPTY && this.getCookTime() > 0) {
                this.cookTime++; //Increment cook time
                if(this.getCookTime() == this.getTotalCookTime()) { //If cook time is total cook time
                    if(this.handler.getStackInSlot(2).getCount() > 0 && //If is one or more items in the output slot...
                            this.getResult().getItem() == this.handler.getStackInSlot(2).getItem()) //...and the result is the same as the item in the output
                        this.handler.getStackInSlot(2).grow(result.getCount()); //Increase the stack in the output by the count of the result
                    else this.handler.insertItem(2, result, false); //Else insert the recipe result into the output slot
                    this.setResult(ItemStack.EMPTY); //Set global result stack to be empty
                    this.setTotalCookTime(0); //Set the total cook time to 0
                    this.setCookTime(0); //Set the cook time to 0
                }
            }else if(this.canSmelt()) {
                int inputCount = WaferWeaverRecipes.getInstance().getInputCount(input); //Get the input stack's item count
                ItemStack result = WaferWeaverRecipes.getInstance().getWeavingResult(input).copy(); //Get the result of the recipe
                if(!result.isEmpty() && input.getCount() >= inputCount) { //If result is not empty and input slot has more items than the input count
                    this.setResult(result); //Set result stack to local result stack
                    this.cookTime++; //Increment cook time
                    this.setTotalCookTime(this.getCookTime(input)); //Sets the total cook time to the cook time of the recipe
                    this.handler.getStackInSlot(0).shrink(inputCount); //Decrease the stack in the input slot by the input count
                }
            }
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
    public static boolean isBurning(TileEntityWaferWeaver te) {
        return te.burnTime > 0;
    }

    /**
     * Gets the cook time for the recipe with the given input
     * @param input The input of the recipe.
     * @return The cook time for the recipe.
     */
    public int getCookTime(ItemStack input) {
        return WaferWeaverRecipes.getInstance().getWeavingTime(input);
    }

    /**
     * Checks whether the recipe is valid and the output slot can fit the result.
     * @return True if the recipe is valid and the output slot is not full, else false.
     */
    private boolean canSmelt() {
        if(this.handler.getStackInSlot(0).isEmpty())
            return false;
        else {
            ItemStack result = WaferWeaverRecipes.getInstance().getWeavingResult(this.handler.getStackInSlot(0)).copy();
            if(result.isEmpty())
                return false;
            else {
                ItemStack output = this.handler.getStackInSlot(2);
                if(output.isEmpty())
                    return true;
                if(!output.isItemEqual(result))
                    return false;
                int res = output.getCount() + result.getCount();
                return res <= 64 && res <= output.getMaxStackSize();
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

    public ItemStack getResult() {
        return this.result;
    }
    public void setResult(ItemStack result) {
        this.result = result;
    }
}