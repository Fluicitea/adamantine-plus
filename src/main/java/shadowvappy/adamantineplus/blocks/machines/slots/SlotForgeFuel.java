package shadowvappy.adamantineplus.blocks.machines.slots;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class SlotForgeFuel extends SlotItemHandler {
    public SlotForgeFuel(IItemHandler itemHandler, int index, int x, int y) {
        super(itemHandler, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem().equals(Items.LAVA_BUCKET);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return super.getItemStackLimit(stack);
    }
}
