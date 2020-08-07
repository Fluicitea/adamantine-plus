package shadowvappy.adamantineplus;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import shadowvappy.adamantineplus.data.APModReference;
import shadowvappy.adamantineplus.tinkers.TConstructHandler;
import shadowvappy.adamantineplus.util.ModChecker;
import shadowvappy.adamantineplus.util.handlers.GuiHandler;
import shadowvappy.adamantineplus.util.handlers.MineLevelHandler;
import shadowvappy.adamantineplus.util.handlers.OreDictHandler;
import shadowvappy.adamantineplus.util.handlers.PlayerDeathHandler;
import shadowvappy.adamantineplus.util.registry.APModClientRegistry;
import shadowvappy.adamantineplus.util.registry.APModRecipeRegistry;
import shadowvappy.adamantineplus.world.generators.ModGenCustomOres;
import shadowvappy.adamantineplus.world.generators.ModGenCustomStructures;

@Mod(modid = APModReference.MODID, name = APModReference.NAME, version = APModReference.VERSION,
        acceptedMinecraftVersions = APModReference.ACCEPTED_VERSIONS, dependencies = APModReference.DEPENDENCIES)
public class Main
{
    @Mod.Instance
    public static Main instance;

    //@SidedProxy(clientSide = APModReference.CLIENT_PROXY_CLASS, serverSide = APModReference.SERVER_PROXY_CLASS)
    //public static IProxy proxy;

    static {
        FluidRegistry.enableUniversalBucket();
        MineLevelHandler.preLoad();
    }

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        ModChecker.printSuccessMessage();
        MinecraftForge.EVENT_BUS.register(APModClientRegistry.class);
        MinecraftForge.EVENT_BUS.register(PlayerDeathHandler.class);
        TConstructHandler.instance.preInit();
        GameRegistry.registerWorldGenerator(new ModGenCustomOres(), 0);
        GameRegistry.registerWorldGenerator(new ModGenCustomStructures(), 0);
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        TConstructHandler.instance.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
        OreDictHandler.registerOres();
        APModRecipeRegistry.init();
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        TConstructHandler.instance.postInit();
    }
}