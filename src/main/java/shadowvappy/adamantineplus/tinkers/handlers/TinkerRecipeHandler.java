package shadowvappy.adamantineplus.tinkers.handlers;

import net.minecraft.item.ItemStack;
import shadowvappy.adamantineplus.data.APModBlocks;
import shadowvappy.adamantineplus.data.APModItems;
import shadowvappy.adamantineplus.items.APModItem;
import shadowvappy.adamantineplus.tinkers.materials.Fluids;
import shadowvappy.adamantineplus.tinkers.materials.Materials;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

public class TinkerRecipeHandler {
    public static void registerCastRecipes() {
        /* Register adamantine wafer casting */
        TinkerRegistry.registerTableCasting(new ItemStack(APModItems.adamantineWafer), TinkerSmeltery.castIngot, Fluids.fluidMoltenAdamantine,
                Material.VALUE_Ingot/3);
        /* Register adamantine block casting */
        TinkerRegistry.registerBasinCasting(new ItemStack(APModBlocks.adamantineBlock), ItemStack.EMPTY, Fluids.fluidMoltenAdamantine,
                (Material.VALUE_Ingot/3)*9);
        /* Register obsidian gem casting */
        TinkerRegistry.registerTableCasting(new ItemStack(APModItems.obsidianGem), TinkerSmeltery.castGem, TinkerFluids.obsidian,
                Material.VALUE_Ingot);
        /* Register obsidian chip casting */
        TinkerRegistry.registerTableCasting(new ItemStack(APModItems.obsidianChip), TinkerSmeltery.castNugget, TinkerFluids.obsidian,
                Material.VALUE_Nugget);
    }

    public static void registerMeltingRecipes() {
        TinkerSmeltery.registerToolpartMeltingCasting(Materials.adamantine);
        TinkerRegistry.registerMelting(APModItems.adamantineWafer, Fluids.fluidMoltenAdamantine, Material.VALUE_Ingot/3);
        TinkerRegistry.registerMelting(APModBlocks.adamantineBlock, Fluids.fluidMoltenAdamantine, (Material.VALUE_Ingot/3)*9);
        TinkerRegistry.registerMelting(APModItems.obsidianGem, TinkerFluids.obsidian, Material.VALUE_Ingot);
        TinkerRegistry.registerMelting(APModItems.obsidianChip, TinkerFluids.obsidian, Material.VALUE_Nugget);
    }
	/*private static void registerMeltingRecipes(Material mat) {
		if(mat != null) {
			TinkerSmeltery.registerToolpartMeltingCasting(mat);
			if(mat.getFluid() != null) {
				Fluid fluid = mat.getFluid();
				TinkerSmeltery.registerOredictMeltingCasting(fluid, StringHelper.capitalize(mat.getIdentifier()));
			}
		}
	}*/

	/*@SubscribeEvent
	public static void onAlloyRegister(AlloyRegisterEvent event) {
		AlloyRecipe recipe = event.getRecipe();

		if(recipe.getResult().getFluid() == TinkerFluids.alubrass) {
			if(TinkerIntegration.isIntegrated(TinkerFluids.brass) &&
			  TinkerIntegration.isIntegrated(TinkerFluids.zinc) &&
			  ModConfig.TINKER_INTEGRATION.replaceAlubrassRecipe) {
				TinkerRegistry.registerAlloy(new FluidStack(TinkerFluids.alubrass, 10),
											 new FluidStack(TinkerFluids.brass, 9),
											 new FluidStack(TinkerFluids.aluminum, 1));
				event.setCanceled(true);
			}
		}
	}*/
}