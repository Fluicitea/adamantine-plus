package shadowvappy.adamantineplus.blocks.machines.weaver;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import shadowvappy.adamantineplus.blocks.machines.slots.SlotFuel;
import shadowvappy.adamantineplus.blocks.machines.slots.SlotWeaverOutput;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ContainerWaferWeaver extends Container {
    private final TileEntityWaferWeaver tileEntity;
    private int cookTime, totalCookTime, burnTime, currentBurnTime;

    /**
     * Creates the slots in the container.
     * @param player The player to create player inventory slots.
     * @param tileEntity The tile entity to create item handler for tile entity slots.
     */
    public ContainerWaferWeaver(InventoryPlayer player, TileEntityWaferWeaver tileEntity) {
        this.tileEntity = tileEntity;
        IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        this.addSlotToContainer(new SlotItemHandler(handler, 0, 53, 17));
        this.addSlotToContainer(new SlotFuel(handler, 1, 80, 39));
        this.addSlotToContainer(new SlotWeaverOutput(player.player, handler, 2, 113, 18));

        for(int y=0; y<3; y++) {
            for(int x=0; x<9; x++) {
                this.addSlotToContainer(new Slot(player, x + y*9 + 9, 8 + x*18, 84 + y*18));
            }
        }
        for(int x=0; x<9; x++) {
            this.addSlotToContainer(new Slot(player, x, 8 + x*18, 142));
        }
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for(IContainerListener iContainerListener : this.listeners) {
            if(this.cookTime != this.tileEntity.getCookTime())
                iContainerListener.sendWindowProperty(this, 2, this.tileEntity.getCookTime());
            if(this.burnTime != this.tileEntity.getBurnTime())
                iContainerListener.sendWindowProperty(this, 0, this.tileEntity.getBurnTime());
            if(this.currentBurnTime != this.tileEntity.getCurrentBurnTime())
                iContainerListener.sendWindowProperty(this, 1, this.tileEntity.getCurrentBurnTime());
            if(this.totalCookTime != this.tileEntity.getTotalCookTime())
                iContainerListener.sendWindowProperty(this, 3, this.tileEntity.getTotalCookTime());
        }

        this.cookTime = this.tileEntity.getCookTime();
        this.burnTime = this.tileEntity.getBurnTime();
        this.currentBurnTime = this.tileEntity.getCurrentBurnTime();
        this.totalCookTime = this.tileEntity.getTotalCookTime();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        switch(id) {
            case 0:
                this.tileEntity.setBurnTime(data);
                break;
            case 1:
                this.tileEntity.setCurrentBurnTime(data);
                break;
            case 2:
                this.tileEntity.setCookTime(data);
                break;
            case 3:
                this.tileEntity.setTotalCookTime(data);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tileEntity.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if(slot != null && slot.getHasStack()) {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if(index == 2) {
                if(!this.mergeItemStack(stack1, 4, 39, true)) return ItemStack.EMPTY;
                slot.onSlotChange(stack1, stack);
            }else if(index != 1 && index != 0) {
                if(!WaferWeaverRecipes.getInstance().getWeavingResult(stack1).isEmpty()) {
                    if(!this.mergeItemStack(stack1, 0, 1, false))
                        return ItemStack.EMPTY;
                }
                if(TileEntityFurnace.isItemFuel(stack1)) {
                    if(!this.mergeItemStack(stack1, 1, 2, false))
                        return ItemStack.EMPTY;
                }else if(index >= 4 && index <31) {
                    if(!this.mergeItemStack(stack1, 31, 39, false))
                        return ItemStack.EMPTY;
                }else if(index >= 31 && index < 39 && !this.mergeItemStack(stack1, 4, 31, false)) {
                    return ItemStack.EMPTY;
                }
            }else if(!this.mergeItemStack(stack1, 4, 39, false))
                return ItemStack.EMPTY;
            if(stack1.isEmpty())
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();
            if(stack1.getCount() == stack.getCount())
                return ItemStack.EMPTY;
            slot.onTake(playerIn, stack1);
        }
        return stack;
    }
}
