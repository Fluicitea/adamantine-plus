package shadowvappy.adamantineplus.items.tools;

import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import shadowvappy.adamantineplus.data.APModCreativeTabs;

import java.util.Objects;

public class ToolHoe extends ItemHoe {
    public ToolHoe(String name, Item.ToolMaterial material) {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(Objects.requireNonNull(getRegistryName()).toString());
        setCreativeTab(APModCreativeTabs.TAB_TOOLS);
    }
}