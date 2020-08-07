package shadowvappy.adamantineplus.data;

import com.google.common.collect.Maps;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class APModReference
{
    //Mod Information
    public static final String MODID                = "adamantineplus";
    public static final String NAME                 = "Adamantine+";
    public static final String VERSION              = "1.12.2-1.0.0";
    public static final String ACCEPTED_VERSIONS    = "[1.12.2, 1.13)";
    public static final String DEPENDENCIES         = "required-after:forge@[14.23.1.2577,);" +
            "required-after:harvestcore@[1.12.2-1.3.6,);" +
            "after:mantle@[1.12-1.3.3.55,);" +
            "after:tconstruct@[1.12.2-2.13.0.183,);" +
            "after:conarm@[1.2.5.9,)";
    //Proxy Locations
    public static final String CLIENT_PROXY_CLASS = "shadowvappy.adamantineplus.proxy.ClientProxy";
    public static final String TINKER_CLIENT_PROXY_CLASS = "shadowvappy.adamantineplus.tinkers.proxy.TinkerClientProxy";
    public static final String SERVER_PROXY_CLASS = "shadowvappy.adamantineplus.proxy.ServerProxy";
    //Gui Ids
    public static final int GUI_STRAND_EXTRACTOR = 0;
    public static final int GUI_WAFER_WEAVER = 1;
    public static final int GUI_ADAMANTINE_FORGE = 2;


    private static final Map<String, ResourceLocation> cache = Maps.newHashMap();

    public static ResourceLocation asLocation(String name) {
        return asLocation(name, false);
    }

    public static ResourceLocation asLocation(String name, boolean doCache) {
        if(doCache) {
            if(!cache.containsKey(name))
                cache.put(name, new ResourceLocation(MODID, name));
            return cache.get(name);
        }else
            return new ResourceLocation(MODID, name);
    }
}