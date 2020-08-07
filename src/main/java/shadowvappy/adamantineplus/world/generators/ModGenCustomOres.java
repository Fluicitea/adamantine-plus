package shadowvappy.adamantineplus.world.generators;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import shadowvappy.adamantineplus.config.AdamantinePlusConfig;
import shadowvappy.adamantineplus.data.APModBlocks;

import java.util.Random;

@SuppressWarnings("ConstantConditions")
public class ModGenCustomOres implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if(world.provider.getDimension() == 0) {
            generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }
    }

    private void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        generateOre(APModBlocks.copperOre.getDefaultState(), world, random, chunkX*16, chunkZ*16, 8, AdamantinePlusConfig.WORLD_GEN.copperSpawnHeight, random.nextInt(8)+4, AdamantinePlusConfig.WORLD_GEN.copperSpawnChances);
        generateOre(APModBlocks.leadOre.getDefaultState(), world, random, chunkX*16, chunkZ*16, 8, AdamantinePlusConfig.WORLD_GEN.leadSpawnHeight, random.nextInt(8)+4, AdamantinePlusConfig.WORLD_GEN.leadSpawnChances);
        if(AdamantinePlusConfig.addSilver)
            generateOre(APModBlocks.silverOre.getDefaultState(), world, random, chunkX*16, chunkZ*16, 8, AdamantinePlusConfig.WORLD_GEN.silverSpawnHeight, random.nextInt(6)+3, AdamantinePlusConfig.WORLD_GEN.silverSpawnChances);
    }

    private void generateOre(IBlockState ore, World world, Random random, int chunkX, int chunkZ, int minY, int maxY, int size, int chances) {
        int deltaY = maxY - minY;

        if(deltaY >= 0) {
            for(int i = 0; i<chances; i++) {
                BlockPos pos = new BlockPos(chunkX + random.nextInt(16), minY + random.nextInt(deltaY), chunkZ + random.nextInt(16));

                WorldGenMinable generator = new WorldGenMinable(ore, size);
                generator.generate(world, random, pos);
            }
        }
    }
}
