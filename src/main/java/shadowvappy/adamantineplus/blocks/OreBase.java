package shadowvappy.adamantineplus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import shadowvappy.adamantineplus.data.APModCreativeTabs;

import java.util.Objects;

public class OreBase extends Block {
    public OreBase(String name, Material material, SoundType sound, int harvestLevel) {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(Objects.requireNonNull(getRegistryName()).toString());
        setCreativeTab(APModCreativeTabs.TAB_GENERAL);

        setSoundType(sound);
        setHardness(3.0F);
        setResistance(3.0F);
        setHarvestLevel("pickaxe", harvestLevel);
    }
}