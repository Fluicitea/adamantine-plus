package shadowvappy.adamantineplus.explosion;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class SpiralExplosion extends EntityLiving {
    public SpiralExplosion(World worldIn) {
        super(worldIn);
    }

    public float getExplosionResistance(Explosion explosionIn, World worldIn, BlockPos pos, IBlockState blockStateIn) {
        return 0.5F;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        DamageSource source1 = new DamageSourceSpiral();
        return super.attackEntityFrom(source1, amount);
    }
}