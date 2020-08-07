package shadowvappy.adamantineplus.items;

import net.minecraft.item.Item;
import shadowvappy.adamantineplus.data.APModCreativeTabs;

import java.util.Objects;

public class APModItem extends Item {
    public APModItem(String name) {
        setRegistryName(name);
        setUnlocalizedName(Objects.requireNonNull(getRegistryName()).toString());
        setCreativeTab(APModCreativeTabs.TAB_GENERAL);
    }
}