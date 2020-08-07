package shadowvappy.adamantineplus.items.tools;

import net.minecraft.item.ItemAxe;
import shadowvappy.adamantineplus.data.APModCreativeTabs;

import java.util.Objects;

public class ToolAxe extends ItemAxe {
    public ToolAxe(String name, ToolMaterial material, Float damage, Float aSpeed) {
        super(material, damage, aSpeed);
        setRegistryName(name);
        setUnlocalizedName(Objects.requireNonNull(getRegistryName()).toString());
        setCreativeTab(APModCreativeTabs.TAB_TOOLS);
    }
}