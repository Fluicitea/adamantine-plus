package shadowvappy.adamantineplus.blocks;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import shadowvappy.adamantineplus.data.APModItems;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class AdamantineOre extends OreBase {
    public AdamantineOre(String name, Material material, SoundType sound, int harvestLevel) {
        super(name, material, sound, harvestLevel);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return APModItems.adamantineRaw;
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return 1;
    }
}
