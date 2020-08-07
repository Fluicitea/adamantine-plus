package shadowvappy.adamantineplus.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadowvappy.adamantineplus.data.APModReference;

@Config(modid = APModReference.MODID)
@Config.LangKey("adamantineplus.config.title")
public class AdamantinePlusConfig {
    public static final WorldGen WORLD_GEN = new WorldGen();
    public static final TinkerIntegration TINKER_INTEGRATION = new TinkerIntegration();

    /** Adds silver to the game. */
    @Config.Comment("Adds silver to the game." + "\n"
            + "Boolean[Accepted_Values={true, false}, Default=true]")
    @Config.RequiresMcRestart
    public static boolean addSilver = true;

    /** World Generation Configuration */
    public static class WorldGen {
        /** Sets the maximum spawn height for copper ore. */
        @Config.Comment("Max spawn height of copper ore in worldgen." + "\n"
                + "Integer[Range={0, 2147483647}, Default=64]")
        public int copperSpawnHeight = 64;
        /** Sets the number of spawn chances for copper ore. */
        @Config.Comment("Number of spawn chances of copper ore in worldgen." + "\n"
                + "Integer[Range={0, 2147483647}, Default=16, 0=disabled]")
        public int copperSpawnChances = 16;
        /** Sets the maximum spawn height for lead ore. */
        @Config.Comment("Max spawn height of lead ore in worldgen." + "\n"
                + "Integer[Range={0, 2147483647}, Default=64]")
        public int leadSpawnHeight = 64;
        /** Sets the number of spawn chances for lead ore. */
        @Config.Comment("Number of spawn chances of lead ore in worldgen." + "\n"
                + "Integer[Range={0, 2147483647}, Default=16, 0=disabled]")
        public int leadSpawnChances = 16;
        /** Sets the maximum spawn height for silver ore. */
        @Config.Comment("Max spawn height of silver ore in worldgen." + "\n"
                + "Integer[Range={0, 2147483647}, Default=32]")
        public int silverSpawnHeight = 32;
        /** Sets the number of spawn chances for silver ore. */
        @Config.Comment("Number of spawn chances of silver ore in worldgen." + "\n"
                + "Integer[Range={0, 2147483647}, Default=6, 0=disabled]")
        public int silverSpawnChances = 6;
        /** Sets the rarity (one in this value) of a spiral spawning. */
        @Config.Comment("Rarity of spiral structure spawning underground." + "\n"
                + "One in [Spiral Rarity] chance to spawn, higher value is lower chance." + "\n"
                + "Integer[Range={0, 2147483647}, Default=20]")
        public int spiralRarity = 20;
    }


    /** Tinkers Integrations Configuration */
    public static class TinkerIntegration {
        /*/** If true, replace aluminum brass alloy recipe with more accurate one. */
		/*@Config.Comment("Replaces the default aluminum brass alloy recipe with a new, more accurate one." + "\n"
				+ "Old recipe: 4 alubrass = 3 aluminum + 1 copper. New recipe: 10 alubrass = 9 brass + 1 aluminum." + "\n"
				+ "Boolean[Accepted_Values={true, false}, Default=false]")
		@Config.RequiresMcRestart
		public boolean replaceAlubrassRecipe = false;*/
        /** If true, add electrum to the game. */
        @Config.Comment("Adds electrum to be used in Tinkers' Construct." + "\n"
                + "Boolean[Accepted_Values={true, false}, Default=true]")
        @Config.RequiresMcRestart
        public boolean addElectrum = true;
        /** If true, add obsidian items to the game. */
        @Config.Comment("Adds gem (ingot) and chip (nugget) items and glass (block) blocks for obsidian." + "\n"
                + "Boolean[Accepted_Values={true, false}, Default=true]")
        @Config.RequiresMcRestart
        public boolean addObsidianItems = true;
    }

    @Mod.EventBusSubscriber(modid = APModReference.MODID)
    private static class EventHandler {
        /**
         * Inject the new values and save to the config when the config is changed through the GUI.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if(event.getModID().equals(APModReference.MODID)) {
                ConfigManager.sync(APModReference.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
