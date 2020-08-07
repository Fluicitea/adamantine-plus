package shadowvappy.adamantineplus.world.generators;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import shadowvappy.adamantineplus.config.AdamantinePlusConfig;
import shadowvappy.adamantineplus.data.APModLootTables;
import shadowvappy.adamantineplus.world.structures.ForgeStructure;
import shadowvappy.adamantineplus.world.structures.SpiralStructure;
import shadowvappy.harvestcore.common.structures.Structure;
import shadowvappy.harvestcore.common.structures.StructureBase;

import java.util.Random;

public class ModGenCustomStructures implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.getDimension())
        {
            case 0:
                generateSpiralStructure(world, random, chunkX, chunkZ, AdamantinePlusConfig.WORLD_GEN.spiralRarity);
                generateForgeStructure(world, random, chunkX, chunkZ);
                break;
            default:
                break;
        }
    }

    private void generateSpiralStructure(World world, Random random, int chunkX, int chunkZ, int chance) {
        int x = (chunkX*16) + random.nextInt(15);
        int z = (chunkZ*16) + random.nextInt(15);
        int y = 0;
        int rotations = random.nextInt(4);
        BlockPos pos = new BlockPos(x, y, z);

        StructureBase generator = new StructureBase();
        Structure structure = new SpiralStructure("adamantine_spiral");
        generator.setStructure(structure);
        generator.rotateStructureFacing(rotations);

        if(world.getWorldType() != WorldType.FLAT) {
            if(random.nextInt(chance) == 0) {
                generator.generate(world, random, pos);
                structure.resetStructure();
            }
        }
    }
    private void generateForgeStructure(World world, Random random, int chunkX, int chunkZ) {
        int x = (chunkX*16) + random.nextInt(15);
        int z = (chunkZ*16) + random.nextInt(15);
        int y = calculateGenerationHeight(world, random, x, z);
        int rotations = random.nextInt(4);
        BlockPos pos = new BlockPos(x, y, z);

        StructureBase generator = new StructureBase();
        Structure structure = new ForgeStructure("dwarf_forge");
        generator.setStructure(structure);
        generator.rotateStructureFacing(rotations);

        if(world.getWorldType() != WorldType.FLAT) {
            if(random.nextInt(50) == 0) {
                generator.generate(world, random, pos);
                for(int[][][][] blocks : structure.blockArrayList()) {
                    int centerX = blocks[0].length / 2, centerZ = blocks[0][0].length / 2;
                    for(int y1=0; y1<blocks.length; y1++) {
                        for(int x1=0; x1<blocks[y1].length; x1++) {
                            for(int z1=0; z1<blocks[y1][x1].length; z1++) {
                                if(blocks[y1][x1][z1][0] == Block.getIdFromBlock(Blocks.CHEST)) {
                                    BlockPos chestPos;
                                    switch(rotations) {
                                        case 1:
                                            chestPos = new BlockPos(x-(z1-centerZ), y+y1, z+x1-centerX);
                                            break;
                                        case 2:
                                            chestPos = new BlockPos(x-(x1-centerX), y+y1, z-(z1-centerZ));
                                            break;
                                        case 3:
                                            chestPos = new BlockPos(x+z1-centerZ, y+y1, z-(x1-centerX));
                                            break;
                                        default:
                                            chestPos = new BlockPos(x+x1-centerX, y+y1, z+z1-centerZ);
                                    }
                                    IBlockState blockChest = world.getBlockState(chestPos);
                                    switch(rotations) {
                                        case 1:
                                            world.setBlockState(chestPos, blockChest.withRotation(Rotation.CLOCKWISE_180));
                                            break;
                                        case 2:
                                            world.setBlockState(chestPos, blockChest.withRotation(Rotation.COUNTERCLOCKWISE_90));
                                            break;
                                        case 3:
                                            world.setBlockState(chestPos, blockChest.withRotation(Rotation.NONE));
                                            break;
                                        default:
                                            world.setBlockState(chestPos, blockChest.withRotation(Rotation.CLOCKWISE_90));
                                    }
                                    TileEntityChest entityChest = (TileEntityChest)world.getTileEntity(chestPos);
                                    if(entityChest != null)
                                        entityChest.setLootTable(APModLootTables.forgeChest, random.nextLong());
                                }
                            }
                        }
                    }
                }
                structure.resetStructure();
                structure = new SpiralStructure("adamantine_spiral");
                generator.setStructure(structure);
                x += (random.nextInt(8)-4);
                z += (random.nextInt(8)-4);
                y = 0;
                pos = new BlockPos(x, y, z);
                if(random.nextInt(10)<9){
                    generator.generate(world, random, pos);
                }
            }
        }
    }

    private static int calculateGenerationHeight(World world, Random random, int x, int z) {
        int y = 32;
        boolean foundGround = false;

        while(!foundGround && y-- >= 0) {
            Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            foundGround = block == Blocks.STONE;
        }
        if(foundGround) {
            return y - random.nextInt(16);
        }else {
            return -1;
        }
    }
}