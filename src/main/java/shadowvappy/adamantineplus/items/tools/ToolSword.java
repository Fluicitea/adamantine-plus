package shadowvappy.adamantineplus.items.tools;

import net.minecraft.item.ItemSword;
import shadowvappy.adamantineplus.data.APModCreativeTabs;

import java.util.Objects;

public class ToolSword extends ItemSword {
    public ToolSword(String name, ToolMaterial material) {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(Objects.requireNonNull(getRegistryName()).toString());
        setCreativeTab(APModCreativeTabs.TAB_COMBAT);
    }
}