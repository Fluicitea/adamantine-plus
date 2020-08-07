package shadowvappy.adamantineplus.tinkers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadowvappy.adamantineplus.data.APModReference;
import shadowvappy.adamantineplus.proxy.IProxy;
import shadowvappy.adamantineplus.tinkers.handlers.TinkerRecipeHandler;
import shadowvappy.adamantineplus.tinkers.handlers.TraitStackHandler;
import shadowvappy.adamantineplus.tinkers.materials.Fluids;
import shadowvappy.adamantineplus.tinkers.materials.Materials;
import shadowvappy.adamantineplus.util.ModChecker;

public class TConstructHandler {
    public static TConstructHandler instance = new TConstructHandler();
    @SidedProxy(clientSide = APModReference.TINKER_CLIENT_PROXY_CLASS, serverSide = APModReference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    public void preInit() {
        if(ModChecker.isTinkersConstructLoaded) {
            MinecraftForge.EVENT_BUS.register(this);
            MinecraftForge.EVENT_BUS.register(TinkerRecipeHandler.class);
            MinecraftForge.EVENT_BUS.register(TraitStackHandler.class);
            Fluids.registerFluids();
            Materials.registerMaterials();
        }
    }

    public void init() {
        if(ModChecker.isTinkersConstructLoaded) {
            Materials.addToMaterials();
        }
    }

    public void postInit() {
        if(ModChecker.isTinkersConstructLoaded) {
            TinkerRecipeHandler.registerCastRecipes();
            TinkerRecipeHandler.registerMeltingRecipes();
        }
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        if(ModChecker.isTinkersConstructLoaded) {
            event.getRegistry().register(Fluids.moltenAdamantine.setRegistryName(new ResourceLocation(APModReference.MODID, "moltenadamantine")));
        }
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        if(ModChecker.isTinkersConstructLoaded) {
            event.getRegistry().register(new ItemBlock(Fluids.moltenAdamantine).setRegistryName(new ResourceLocation(APModReference.MODID, "moltenadamantine")));
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        if(ModChecker.isTinkersConstructLoaded) {
            proxy.registerFluidModel(Fluids.moltenAdamantine, "adamantine");
        }
    }
}