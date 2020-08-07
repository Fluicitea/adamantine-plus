package shadowvappy.adamantineplus.util.registry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import shadowvappy.adamantineplus.config.AdamantinePlusConfig;
import shadowvappy.adamantineplus.data.APModBlocks;
import shadowvappy.adamantineplus.data.APModItems;

@SuppressWarnings("ConstantConditions")
public class APModRecipeRegistry {
    public static void init() {
        //Ore Smelting
        GameRegistry.addSmelting(APModBlocks.copperOre, new ItemStack(APModItems.copperIngot), 0.3F);
        GameRegistry.addSmelting(APModBlocks.leadOre, new ItemStack(APModItems.leadIngot), 0.5F);
        if(AdamantinePlusConfig.addSilver)
            GameRegistry.addSmelting(APModBlocks.silverOre, new ItemStack(APModItems.silverIngot), 0.6F);
    }
}