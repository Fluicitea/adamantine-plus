package shadowvappy.adamantineplus.util;

import net.minecraftforge.fml.common.Loader;
import org.apache.logging.log4j.Logger;
import shadowvappy.adamantineplus.data.APModReference;
import shadowvappy.harvestcore.util.LogHelper;

public class ModChecker {
    private static final Logger LOG = LogHelper.getLogger(APModReference.MODID, "ModChecker");
    public static boolean isTinkersConstructLoaded = Loader.isModLoaded("tconstruct");
    public static boolean isConstructsArmoryLoaded = Loader.isModLoaded("conarm");

    public static void printSuccessMessage() {
        LOG.info("Tinkers Construct is Loaded: " + isTinkersConstructLoaded);
        LOG.info("Constructs Armory is Loaded: " + isConstructsArmoryLoaded);
    }
}
