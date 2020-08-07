package shadowvappy.adamantineplus.util;

import shadowvappy.harvestcore.config.HarvestCoreConfig;

public class HarvestLevels {
    /* Main Mod Harvest Levels */
    /** Harvest level for lead blocks (used as block harvest levels). */
    public static final int LEAD_HARVEST_LEVEL = HarvestCoreConfig.addMineLevels ? 2 : 1;
    /** Harvest level for silver blocks (used as block harvest levels). */
    public static final int SILVER_HARVEST_LEVEL = HarvestCoreConfig.addMineLevels ? 4 : 2;
    /** Harvest level for adamantine blocks (used as block harvest levels). */
    public static final int ADAMANTINE_HARVEST_LEVEL = HarvestCoreConfig.addMineLevels ? 5 : 3;

    /** Harvest level of copper tools (used as material harvest levels). */
    public static final int COPPER_MINE_LEVEL = HarvestCoreConfig.addMineLevels ? 2 : 1;
    /** Harvest level of lead tools (used as material harvest levels). */
    public static final int LEAD_MINE_LEVEL = HarvestCoreConfig.addMineLevels ? 3 : 1;
    /** Harvest level of silver tools (used as material harvest levels). */
    public static final int SILVER_MINE_LEVEL = HarvestCoreConfig.addMineLevels ? 4 : 2;
    /** Harvest level for adamantine blocks (used as block harvest levels). */
    public static final int ADAMANTINE_MINE_LEVEL = HarvestCoreConfig.addMineLevels ? 7 : 5;

    /* Tinkers' Harvest Levels */
    /** Harvest level for electrum blocks (used as block harvest levels). */
    public static final int ELECTRUM_HARVEST_LEVEL = HarvestCoreConfig.addMineLevels ? 4 : 2;
    /** Harvest level for obsidian blocks (used as block harvest levels). */
    public static final int OBSIDIAN_HARVEST_LEVEL = HarvestCoreConfig.addMineLevels ? 5 : 3;
}
