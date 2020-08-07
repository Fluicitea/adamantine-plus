package shadowvappy.adamantineplus.blocks.machines.slots;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import shadowvappy.adamantineplus.data.APModItems;

import javax.annotation.Nonnull;

public class SlotForgeInput extends SlotItemHandler {

    public SlotForgeInput(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        return stack.getItem().equals(APModItems.adamantineWafer);
    }

    @Override
    public int getItemStackLimit(@Nonnull ItemStack stack) {
        return super.getItemStackLimit(stack);
    }
}
