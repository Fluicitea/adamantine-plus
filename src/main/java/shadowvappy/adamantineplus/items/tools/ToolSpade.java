package shadowvappy.adamantineplus.items.tools;

import net.minecraft.item.ItemSpade;
import shadowvappy.adamantineplus.data.APModCreativeTabs;

import java.util.Objects;

public class ToolSpade extends ItemSpade {
    public ToolSpade(String name, ToolMaterial material) {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(Objects.requireNonNull(getRegistryName()).toString());
        setCreativeTab(APModCreativeTabs.TAB_TOOLS);
    }
}