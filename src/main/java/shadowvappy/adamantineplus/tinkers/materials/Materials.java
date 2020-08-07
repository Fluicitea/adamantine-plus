package shadowvappy.adamantineplus.tinkers.materials;

import c4.conarm.lib.materials.ArmorMaterialType;
import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import net.minecraft.util.text.TextFormatting;
import shadowvappy.adamantineplus.tinkers.materials.traits.Traits;
import shadowvappy.adamantineplus.util.ModChecker;
import shadowvappy.harvestcore.config.HarvestCoreConfig;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;

public class Materials {
    public static final int ADAMANTINE = HarvestCoreConfig.TINKER_INTEGRATION.tinkerMineLevels ? 7 : 5;

    public static Material adamantine;

    public static void registerMaterials() {
        adamantine = new Material("adamantine", TextFormatting.AQUA);
        /* Stats: Durability, Speed, Attack, Handle, Extra, Harvest Level */
        TinkerRegistry.addMaterialStats(adamantine,
                    new HeadMaterialStats(1200, 8.47f, 9.00f, ADAMANTINE),
                    new HandleMaterialStats(1.30f, 300),
                    new ExtraMaterialStats(500),
                    new BowMaterialStats(0.8f, 1.1f, 5.0f),
                    new BowStringMaterialStats(1.0f));
        if(ModChecker.isConstructsArmoryLoaded) {
            TinkerRegistry.addMaterialStats(adamantine,
                    new CoreMaterialStats(12.0f, 20),
                    new PlatesMaterialStats(1.0f, 12.0f, 6),
                    new TrimMaterialStats(12.0f));
        }
        TinkerRegistry.integrate(adamantine, Fluids.fluidMoltenAdamantine, "Adamantine").toolforge().preInit();
    }

    public static void addToMaterials() {
        adamantine.addTrait(Traits.durable);
        adamantine.addTrait(Traits.durable, MaterialTypes.HEAD);
        adamantine.addTrait(Traits.adamantineEdge, MaterialTypes.HEAD);
        if(ModChecker.isConstructsArmoryLoaded) {
            adamantine.addTrait(Traits.durableArmor, ArmorMaterialType.PLATES);
            adamantine.addTrait(Traits.durableArmor, ArmorMaterialType.TRIM);
            adamantine.addTrait(Traits.durableArmor, ArmorMaterialType.CORE);
            adamantine.addTrait(Traits.adamantineResistance, ArmorMaterialType.CORE);
        }
    }
}