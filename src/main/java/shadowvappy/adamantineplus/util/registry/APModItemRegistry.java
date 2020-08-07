package shadowvappy.adamantineplus.util.registry;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadowvappy.adamantineplus.config.AdamantinePlusConfig;
import shadowvappy.adamantineplus.data.APModMaterials;
import shadowvappy.adamantineplus.items.APModItem;
import shadowvappy.adamantineplus.items.armor.ArmorBase;
import shadowvappy.adamantineplus.items.tools.*;
import shadowvappy.adamantineplus.util.ModChecker;

@Mod.EventBusSubscriber
public class APModItemRegistry {
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                /* Register Copper Items */
                new APModItem("copper_nugget"),
                new APModItem("copper_ingot"),
                new ToolSword("copper_sword", APModMaterials.materialCopper),
                new ToolPickaxe("copper_pickaxe", APModMaterials.materialCopper),
                new ToolAxe("copper_axe", APModMaterials.materialCopper, 8.0F, -3.2F),
                new ToolSpade("copper_shovel", APModMaterials.materialCopper),
                new ToolHoe("copper_hoe", APModMaterials.materialCopper),
                new ArmorBase("copper_helmet", APModMaterials.armorMaterialCopper, 1, EntityEquipmentSlot.HEAD),
                new ArmorBase("copper_chestplate", APModMaterials.armorMaterialCopper, 1, EntityEquipmentSlot.CHEST),
                new ArmorBase("copper_leggings", APModMaterials.armorMaterialCopper, 2, EntityEquipmentSlot.LEGS),
                new ArmorBase("copper_boots", APModMaterials.armorMaterialCopper, 1, EntityEquipmentSlot.FEET),
                /* Register Lead Items */
                new APModItem("lead_nugget"),
                new APModItem("lead_ingot"),
                new ToolSword("lead_sword", APModMaterials.materialLead),
                new ToolPickaxe("lead_pickaxe", APModMaterials.materialLead),
                new ToolAxe("lead_axe", APModMaterials.materialLead, 8.0F, -3.5F),
                new ToolSpade("lead_shovel", APModMaterials.materialLead),
                new ToolHoe("lead_hoe", APModMaterials.materialLead),
                new ArmorBase("lead_helmet", APModMaterials.armorMaterialLead, 1, EntityEquipmentSlot.HEAD),
                new ArmorBase("lead_chestplate", APModMaterials.armorMaterialLead, 1, EntityEquipmentSlot.CHEST),
                new ArmorBase("lead_leggings", APModMaterials.armorMaterialLead, 2, EntityEquipmentSlot.LEGS),
                new ArmorBase("lead_boots", APModMaterials.armorMaterialLead, 1, EntityEquipmentSlot.FEET),
                /* Register Adamantine Items */
                new APModItem("raw_adamantine"),
                new APModItem("adamantine_strand"),
                new APModItem("adamantine_wafer"),
                new ToolSword("adamantine_sword", APModMaterials.materialAdamantine),
                new ToolPickaxe("adamantine_pickaxe", APModMaterials.materialAdamantine),
                new ToolAxe("adamantine_axe", APModMaterials.materialAdamantine, 13.0F, -2.8F),
                new ToolSpade("adamantine_shovel", APModMaterials.materialAdamantine),
                new ToolHoe("adamantine_hoe", APModMaterials.materialAdamantine),
                new ArmorBase("adamantine_helmet", APModMaterials.armorMaterialAdamantine, 1, EntityEquipmentSlot.HEAD),
                new ArmorBase("adamantine_chestplate", APModMaterials.armorMaterialAdamantine, 1, EntityEquipmentSlot.CHEST),
                new ArmorBase("adamantine_leggings", APModMaterials.armorMaterialAdamantine, 2, EntityEquipmentSlot.LEGS),
                new ArmorBase("adamantine_boots", APModMaterials.armorMaterialAdamantine, 1, EntityEquipmentSlot.FEET),
                /* Register Machine Items */
                new APModItem("iron_rod"),
                new APModItem("extractor_arm"),
                new APModItem("weaver_arm"),
                /* Register Miscellaneous Items */
                new APModItem("dwarven_scroll")
        );
        if(AdamantinePlusConfig.addSilver) {
            event.getRegistry().registerAll(
                    /* Register Silver Items */
                    new APModItem("silver_nugget"),
                    new APModItem("silver_ingot"),
                    new ToolSword("silver_sword", APModMaterials.materialSilver),
                    new ToolPickaxe("silver_pickaxe", APModMaterials.materialSilver),
                    new ToolAxe("silver_axe", APModMaterials.materialSilver, 8.0F, -3.0F),
                    new ToolSpade("silver_shovel", APModMaterials.materialSilver),
                    new ToolHoe("silver_hoe", APModMaterials.materialSilver),
                    new ArmorBase("silver_helmet", APModMaterials.armorMaterialSilver, 1, EntityEquipmentSlot.HEAD),
                    new ArmorBase("silver_chestplate", APModMaterials.armorMaterialSilver, 1, EntityEquipmentSlot.CHEST),
                    new ArmorBase("silver_leggings", APModMaterials.armorMaterialSilver, 2, EntityEquipmentSlot.LEGS),
                    new ArmorBase("silver_boots", APModMaterials.armorMaterialSilver, 1, EntityEquipmentSlot.FEET)
            );
        }
        /* Register items for Tinkers' */
        if(ModChecker.isTinkersConstructLoaded) {
            if(AdamantinePlusConfig.TINKER_INTEGRATION.addElectrum) {
                event.getRegistry().registerAll(
                        new APModItem("electrum_nugget"),
                        new APModItem("electrum_ingot")
                );
            }
            if(AdamantinePlusConfig.TINKER_INTEGRATION.addObsidianItems) {
                event.getRegistry().registerAll(
                        new APModItem("obsidian_chip"),
                        new APModItem("obsidian_gem")
                );
            }
        }
    }
}
