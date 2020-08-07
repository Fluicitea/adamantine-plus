package shadowvappy.adamantineplus.data;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

@SuppressWarnings("ConstantConditions")
public class APModCreativeTabs {
    public static final CreativeTabs TAB_GENERAL = new CreativeTabs(APModReference.MODID + ".general") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(APModBlocks.copperOre);
        }
    };
    public static final CreativeTabs TAB_TOOLS = new CreativeTabs(APModReference.MODID + ".tools") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(APModItems.copperPickaxe);
        }
    };
    public static final CreativeTabs TAB_COMBAT = new CreativeTabs(APModReference.MODID + ".combat") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(APModItems.copperSword);
        }
    };
}
