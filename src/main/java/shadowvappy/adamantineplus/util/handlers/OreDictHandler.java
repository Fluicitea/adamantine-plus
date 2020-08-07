package shadowvappy.adamantineplus.util.handlers;

import net.minecraftforge.oredict.OreDictionary;
import shadowvappy.adamantineplus.config.AdamantinePlusConfig;
import shadowvappy.adamantineplus.data.APModItems;

public class OreDictHandler {
    public static void registerOres() {
        OreDictionary.registerOre("ingotCopper", APModItems.copperIngot);
        OreDictionary.registerOre("nuggetCopper", APModItems.copperNugget);
        OreDictionary.registerOre("ingotLead", APModItems.leadIngot);
        OreDictionary.registerOre("nuggetLead", APModItems.leadNugget);
        if(AdamantinePlusConfig.addSilver) {
            OreDictionary.registerOre("ingotSilver", APModItems.silverIngot);
            OreDictionary.registerOre("nuggetSilver", APModItems.silverNugget);
        }
    }
}