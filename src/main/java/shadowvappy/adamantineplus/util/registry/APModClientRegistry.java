package shadowvappy.adamantineplus.util.registry;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import shadowvappy.adamantineplus.config.AdamantinePlusConfig;
import shadowvappy.adamantineplus.data.APModBlocks;
import shadowvappy.adamantineplus.data.APModItems;
import shadowvappy.adamantineplus.data.APModReference;
import shadowvappy.adamantineplus.util.ModChecker;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = APModReference.MODID, value = {Side.CLIENT})
@SuppressWarnings("ConstantConditions")
public class APModClientRegistry {
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        /* Register Block Models for Copper */
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(APModBlocks.copperOre), 0,
                new ModelResourceLocation(Objects.requireNonNull(APModBlocks.copperOre.getRegistryName()), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(APModBlocks.copperBlock), 0,
                new ModelResourceLocation(Objects.requireNonNull(APModBlocks.copperBlock.getRegistryName()), "inventory"));
        /* Register Item Models for Copper */
        registerStaticModel(APModItems.copperIngot, new ModelResourceLocation(APModItems.copperIngot.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.copperNugget, new ModelResourceLocation(APModItems.copperNugget.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.copperSword, new ModelResourceLocation(APModItems.copperSword.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.copperPickaxe, new ModelResourceLocation(APModItems.copperPickaxe.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.copperAxe, new ModelResourceLocation(APModItems.copperAxe.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.copperShovel, new ModelResourceLocation(APModItems.copperShovel.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.copperHoe, new ModelResourceLocation(APModItems.copperHoe.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.copperHelmet, new ModelResourceLocation(APModItems.copperHelmet.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.copperChestplate, new ModelResourceLocation(APModItems.copperChestplate.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.copperLeggings, new ModelResourceLocation(APModItems.copperLeggings.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.copperBoots, new ModelResourceLocation(APModItems.copperBoots.getRegistryName(), "inventory"));
        /* Register Block Models for Lead */
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(APModBlocks.leadOre), 0,
                new ModelResourceLocation(Objects.requireNonNull(APModBlocks.leadOre.getRegistryName()), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(APModBlocks.leadBlock), 0,
                new ModelResourceLocation(Objects.requireNonNull(APModBlocks.leadBlock.getRegistryName()), "inventory"));
        /* Register Item Models for Lead */
        registerStaticModel(APModItems.leadIngot, new ModelResourceLocation(APModItems.leadIngot.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.leadNugget, new ModelResourceLocation(APModItems.leadNugget.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.leadSword, new ModelResourceLocation(APModItems.leadSword.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.leadPickaxe, new ModelResourceLocation(APModItems.leadPickaxe.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.leadAxe, new ModelResourceLocation(APModItems.leadAxe.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.leadShovel, new ModelResourceLocation(APModItems.leadShovel.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.leadHoe, new ModelResourceLocation(APModItems.leadHoe.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.leadHelmet, new ModelResourceLocation(APModItems.leadHelmet.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.leadChestplate, new ModelResourceLocation(APModItems.leadChestplate.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.leadLeggings, new ModelResourceLocation(APModItems.leadLeggings.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.leadBoots, new ModelResourceLocation(APModItems.leadBoots.getRegistryName(), "inventory"));
        /* Register Block Models for Adamantine */
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(APModBlocks.adamantineOre), 0,
                new ModelResourceLocation(Objects.requireNonNull(APModBlocks.adamantineOre.getRegistryName()), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(APModBlocks.adamantineBlock), 0,
                new ModelResourceLocation(Objects.requireNonNull(APModBlocks.adamantineBlock.getRegistryName()), "inventory"));
        /* Register Item Models for Adamantine */
        registerStaticModel(APModItems.adamantineRaw, new ModelResourceLocation(APModItems.adamantineRaw.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.adamantineStrand, new ModelResourceLocation(APModItems.adamantineStrand.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.adamantineWafer, new ModelResourceLocation(APModItems.adamantineWafer.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.adamantineSword, new ModelResourceLocation(APModItems.adamantineSword.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.adamantinePickaxe, new ModelResourceLocation(APModItems.adamantinePickaxe.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.adamantineAxe, new ModelResourceLocation(APModItems.adamantineAxe.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.adamantineShovel, new ModelResourceLocation(APModItems.adamantineShovel.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.adamantineHoe, new ModelResourceLocation(APModItems.adamantineHoe.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.adamantineHelmet, new ModelResourceLocation(APModItems.adamantineHelmet.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.adamantineChestplate, new ModelResourceLocation(APModItems.adamantineChestplate.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.adamantineLeggings, new ModelResourceLocation(APModItems.adamantineLeggings.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.adamantineBoots, new ModelResourceLocation(APModItems.adamantineBoots.getRegistryName(), "inventory"));
        /* Register Block Models for Machines */
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(APModBlocks.strandExtractor), 0,
                new ModelResourceLocation(Objects.requireNonNull(APModBlocks.strandExtractor.getRegistryName()), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(APModBlocks.waferWeaver), 0,
                new ModelResourceLocation(Objects.requireNonNull(APModBlocks.waferWeaver.getRegistryName()), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(APModBlocks.adamantineForge), 0,
                new ModelResourceLocation(Objects.requireNonNull(APModBlocks.adamantineForge.getRegistryName()), "inventory"));
        /* Register Item Models for Machines */
        registerStaticModel(APModItems.ironRod, new ModelResourceLocation(APModItems.ironRod.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.extractorArm, new ModelResourceLocation(APModItems.extractorArm.getRegistryName(), "inventory"));
        registerStaticModel(APModItems.weaverArm, new ModelResourceLocation(APModItems.weaverArm.getRegistryName(), "inventory"));
        /* Register Item Models for Miscellaneous */
        registerStaticModel(APModItems.dwarfScroll, new ModelResourceLocation(APModItems.dwarfScroll.getRegistryName(), "inventory"));
        if(AdamantinePlusConfig.addSilver) {
            /* Register Block Models for Silver */
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(APModBlocks.silverOre), 0,
                    new ModelResourceLocation(Objects.requireNonNull(APModBlocks.silverOre.getRegistryName()), "inventory"));
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(APModBlocks.silverBlock), 0,
                    new ModelResourceLocation(Objects.requireNonNull(APModBlocks.silverBlock.getRegistryName()), "inventory"));
            /* Register Item Models for Silver */
            registerStaticModel(APModItems.silverIngot, new ModelResourceLocation(APModItems.silverIngot.getRegistryName(), "inventory"));
            registerStaticModel(APModItems.silverNugget, new ModelResourceLocation(APModItems.silverNugget.getRegistryName(), "inventory"));
            registerStaticModel(APModItems.silverSword, new ModelResourceLocation(APModItems.silverSword.getRegistryName(), "inventory"));
            registerStaticModel(APModItems.silverPickaxe, new ModelResourceLocation(APModItems.silverPickaxe.getRegistryName(), "inventory"));
            registerStaticModel(APModItems.silverAxe, new ModelResourceLocation(APModItems.silverAxe.getRegistryName(), "inventory"));
            registerStaticModel(APModItems.silverShovel, new ModelResourceLocation(APModItems.silverShovel.getRegistryName(), "inventory"));
            registerStaticModel(APModItems.silverHoe, new ModelResourceLocation(APModItems.silverHoe.getRegistryName(), "inventory"));
            registerStaticModel(APModItems.silverHelmet, new ModelResourceLocation(APModItems.silverHelmet.getRegistryName(), "inventory"));
            registerStaticModel(APModItems.silverChestplate, new ModelResourceLocation(APModItems.silverChestplate.getRegistryName(), "inventory"));
            registerStaticModel(APModItems.silverLeggings, new ModelResourceLocation(APModItems.silverLeggings.getRegistryName(), "inventory"));
            registerStaticModel(APModItems.silverBoots, new ModelResourceLocation(APModItems.silverBoots.getRegistryName(), "inventory"));
        }
        /* Register Models for Tinkers' Construct */
        if(ModChecker.isTinkersConstructLoaded) {
            if(AdamantinePlusConfig.TINKER_INTEGRATION.addElectrum) {
                /* Register Block Model for Electrum */
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(APModBlocks.electrumBlock), 0,
                        new ModelResourceLocation(Objects.requireNonNull(APModBlocks.electrumBlock.getRegistryName()), "inventory"));
                /* Register Item Models for Electrum */
                registerStaticModel(APModItems.electrumIngot, new ModelResourceLocation(APModItems.electrumIngot.getRegistryName(), "inventory"));
                registerStaticModel(APModItems.electrumNugget, new ModelResourceLocation(APModItems.electrumNugget.getRegistryName(), "inventory"));
            }
            if(AdamantinePlusConfig.TINKER_INTEGRATION.addObsidianItems) {
                /* Register Block Model for Obsidian */
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(APModBlocks.obsidianGlass), 0,
                        new ModelResourceLocation(Objects.requireNonNull(APModBlocks.obsidianGlass.getRegistryName()), "inventory"));
                /* Register Item Models for Obsidian */
                registerStaticModel(APModItems.obsidianGem, new ModelResourceLocation(APModItems.obsidianGem.getRegistryName(), "inventory"));
                registerStaticModel(APModItems.electrumNugget, new ModelResourceLocation(APModItems.electrumNugget.getRegistryName(), "inventory"));
            }
        }
    }

    public static void registerStaticModel(Item item, ModelResourceLocation staticLocation) {
        ModelLoader.setCustomMeshDefinition(item, i -> staticLocation);
        ModelBakery.registerItemVariants(item, staticLocation);
    }
}