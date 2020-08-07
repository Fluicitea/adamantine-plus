package shadowvappy.adamantineplus.util.registry;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadowvappy.adamantineplus.blocks.AdamantineOre;
import shadowvappy.adamantineplus.blocks.AirBase;
import shadowvappy.adamantineplus.blocks.MetalBase;
import shadowvappy.adamantineplus.blocks.OreBase;
import shadowvappy.adamantineplus.blocks.machines.extractor.BlockStrandExtractor;
import shadowvappy.adamantineplus.blocks.machines.forge.BlockAdamantineForge;
import shadowvappy.adamantineplus.blocks.machines.weaver.BlockWaferWeaver;
import shadowvappy.adamantineplus.config.AdamantinePlusConfig;
import shadowvappy.adamantineplus.data.APModBlocks;
import shadowvappy.adamantineplus.tinkers.blocks.ObsidianGlass;
import shadowvappy.adamantineplus.util.HarvestLevels;
import shadowvappy.adamantineplus.util.ModChecker;

@Mod.EventBusSubscriber
@SuppressWarnings("ConstantConditions")
public class APModBlockRegistry {
    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                /* Register Copper Blocks */
                new OreBase("copper_ore", Material.ROCK, SoundType.STONE, 1),
                new MetalBase("copper_block", Material.IRON, SoundType.METAL, 1),
                /* Register Lead Blocks */
                new OreBase("lead_ore", Material.ROCK, SoundType.STONE, HarvestLevels.LEAD_HARVEST_LEVEL),
                new MetalBase("lead_block", Material.IRON, SoundType.METAL, HarvestLevels.LEAD_HARVEST_LEVEL),
                /* Register Adamantine Blocks */
                new AdamantineOre("adamantine_ore", Material.ROCK, SoundType.STONE, HarvestLevels.ADAMANTINE_HARVEST_LEVEL),
                new MetalBase("adamantine_block", Material.IRON, SoundType.METAL, HarvestLevels.ADAMANTINE_HARVEST_LEVEL),
                /* Register Machine Blocks */
                new BlockStrandExtractor("strand_extractor"),
                new BlockWaferWeaver("wafer_weaver"),
                new BlockAdamantineForge("adamantine_forge"),
                /* Register Miscellaneous Blocks */
                new AirBase("spiral_core", Material.AIR)
        );
        if(AdamantinePlusConfig.addSilver) {
            event.getRegistry().registerAll(
                    /* Register Silver Blocks */
                    new OreBase("silver_ore", Material.ROCK, SoundType.STONE, HarvestLevels.SILVER_HARVEST_LEVEL),
                    new MetalBase("silver_block", Material.IRON, SoundType.METAL, HarvestLevels.SILVER_HARVEST_LEVEL)
            );
        }
        /* Register blocks for Tinker's alloys */
        if(ModChecker.isTinkersConstructLoaded) {
            if(AdamantinePlusConfig.TINKER_INTEGRATION.addElectrum)
                event.getRegistry().register(new MetalBase("electrum_block", Material.IRON, SoundType.METAL, HarvestLevels.ELECTRUM_HARVEST_LEVEL));
            if(AdamantinePlusConfig.TINKER_INTEGRATION.addObsidianItems)
                event.getRegistry().register(new ObsidianGlass("obsidian_glass", Material.IRON, SoundType.GLASS, HarvestLevels.OBSIDIAN_HARVEST_LEVEL));
        }
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                /* Register Copper ItemBlocks */
                new ItemBlock(APModBlocks.copperOre).setRegistryName(APModBlocks.copperOre.getRegistryName()),
                new ItemBlock(APModBlocks.copperBlock).setRegistryName(APModBlocks.copperBlock.getRegistryName()),
                /* Register Lead ItemBlocks */
                new ItemBlock(APModBlocks.leadOre).setRegistryName(APModBlocks.leadOre.getRegistryName()),
                new ItemBlock(APModBlocks.leadBlock).setRegistryName(APModBlocks.leadBlock.getRegistryName()),
                /* Register Adamantine ItemBlocks */
                new ItemBlock(APModBlocks.adamantineOre).setRegistryName(APModBlocks.adamantineOre.getRegistryName()),
                new ItemBlock(APModBlocks.adamantineBlock).setRegistryName(APModBlocks.adamantineBlock.getRegistryName()),
                /* Register Machine ItemBlocks */
                new ItemBlock(APModBlocks.strandExtractor).setRegistryName(APModBlocks.strandExtractor.getRegistryName()),
                new ItemBlock(APModBlocks.waferWeaver).setRegistryName(APModBlocks.waferWeaver.getRegistryName()),
                new ItemBlock(APModBlocks.adamantineForge).setRegistryName(APModBlocks.adamantineForge.getRegistryName())
        );
        if(AdamantinePlusConfig.addSilver) {
            event.getRegistry().registerAll(
                    new ItemBlock(APModBlocks.silverOre).setRegistryName(APModBlocks.silverOre.getRegistryName()),
                    new ItemBlock(APModBlocks.silverBlock).setRegistryName(APModBlocks.silverBlock.getRegistryName())
            );
        }
        if(ModChecker.isTinkersConstructLoaded) {
            if(AdamantinePlusConfig.TINKER_INTEGRATION.addElectrum)
                event.getRegistry().register(new ItemBlock(APModBlocks.electrumBlock).setRegistryName(APModBlocks.electrumBlock.getRegistryName()));
            if(AdamantinePlusConfig.TINKER_INTEGRATION.addObsidianItems)
                event.getRegistry().register(new ItemBlock(APModBlocks.obsidianGlass).setRegistryName(APModBlocks.obsidianGlass.getRegistryName()));
        }
    }
}
