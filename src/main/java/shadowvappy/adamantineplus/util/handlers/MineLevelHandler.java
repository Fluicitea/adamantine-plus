package shadowvappy.adamantineplus.util.handlers;

import net.minecraft.util.text.TextFormatting;
import shadowvappy.adamantineplus.data.APModReference;
import shadowvappy.adamantineplus.util.ModChecker;
import shadowvappy.harvestcore.common.harvestlevels.Levels;
import slimeknights.tconstruct.tools.TinkerMaterials;

public class MineLevelHandler {
    public static void preLoad() {
        Levels.addLevel("Copper", APModReference.MODID, 1, ModChecker.isTinkersConstructLoaded ? TinkerMaterials.copper.getTextColor() : null);
        Levels.addLevel("Lead", APModReference.MODID, 1, ModChecker.isTinkersConstructLoaded ? TinkerMaterials.lead.getTextColor() : null);
        Levels.addLevel("Adamantine", APModReference.MODID, 5, TextFormatting.AQUA.toString());
    }
}