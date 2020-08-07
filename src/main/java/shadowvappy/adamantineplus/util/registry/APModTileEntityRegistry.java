package shadowvappy.adamantineplus.util.registry;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import shadowvappy.adamantineplus.blocks.machines.extractor.TileEntityStrandExtractor;
import shadowvappy.adamantineplus.blocks.machines.forge.TileEntityAdamantineForge;
import shadowvappy.adamantineplus.blocks.machines.weaver.TileEntityWaferWeaver;
import shadowvappy.adamantineplus.data.APModReference;

@Mod.EventBusSubscriber
public class APModTileEntityRegistry {
    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent<Block> event) {
        GameRegistry.registerTileEntity(TileEntityStrandExtractor.class, APModReference.asLocation("strand_extractor"));
        GameRegistry.registerTileEntity(TileEntityWaferWeaver.class, APModReference.asLocation("wafer_weaver"));
        GameRegistry.registerTileEntity(TileEntityAdamantineForge.class, APModReference.asLocation("adamantine_forge"));
    }
}