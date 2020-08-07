package shadowvappy.adamantineplus.util.handlers;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadowvappy.adamantineplus.data.APModBlocks;
import shadowvappy.adamantineplus.explosion.DamageSourceSpiral;
import shadowvappy.adamantineplus.explosion.SpiralExplosion;

@Mod.EventBusSubscriber
@SuppressWarnings("ConstantConditions")
public class ExplosionHandler {
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.HarvestDropsEvent event) {
        BlockPos pos = event.getPos();
        World world = event.getWorld();

        for(EnumFacing direction : EnumFacing.VALUES) {
            BlockPos newPos = pos.offset(direction);
            IBlockState state = world.getBlockState(newPos);
            Block block = state.getBlock();

            if(block == APModBlocks.fakeAir) {
                world.setBlockState(newPos, Blocks.AIR.getDefaultState());
                world.newExplosion(new SpiralExplosion(world), newPos.getX(), newPos.getY(), newPos.getZ(), 6.0F, false, true);
            }
        }
    }

    @SubscribeEvent
    public static void onExplosionEvent(ExplosionEvent.Detonate event) {
        World world = event.getWorld();
        if(event.getExplosion().getExplosivePlacedBy() instanceof SpiralExplosion) {
            for(Entity entity : event.getAffectedEntities()) {
                entity.attackEntityFrom(new DamageSourceSpiral(), 20.0F);
            }
        }

        for(BlockPos pos : event.getExplosion().getAffectedBlockPositions()) {
            for(EnumFacing direction : EnumFacing.VALUES) {
                BlockPos newPos = pos.offset(direction);
                IBlockState state = world.getBlockState(newPos);
                Block block = state.getBlock();

                if(block == APModBlocks.fakeAir) {
                    world.setBlockState(newPos, Blocks.AIR.getDefaultState());
                    world.newExplosion(new SpiralExplosion(world), newPos.getX(), newPos.getY(), newPos.getZ(), 6.0F, false, true);
                }
            }
        }
    }
}