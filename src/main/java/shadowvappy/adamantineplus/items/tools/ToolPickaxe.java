package shadowvappy.adamantineplus.items.tools;

import net.minecraft.item.ItemPickaxe;
import shadowvappy.adamantineplus.data.APModCreativeTabs;

import java.util.Objects;

public class ToolPickaxe extends ItemPickaxe {
    public ToolPickaxe(String name, ToolMaterial material) {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(Objects.requireNonNull(getRegistryName()).toString());
        setCreativeTab(APModCreativeTabs.TAB_TOOLS);
    }
}