package shadowvappy.adamantineplus.world.structures;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import shadowvappy.harvestcore.common.structures.Structure;
import shadowvappy.harvestcore.common.structures.StructureBase;

import java.util.Random;

public class ForgeStructure extends Structure {
    public ForgeStructure(String name) {
        super(name);
        this.addBlockArray(forgeStructureArray());
    }

    private static int[][][][] forgeStructureArray() {
        int[][][][] blocks = new int[6][10][9][2];
        Random rand = new Random();
        int variants = 2;
        int wallBlock = StructureBase.SET_NO_BLOCK;
        if(rand.nextInt(variants) == 1) {
            wallBlock = Block.getIdFromBlock(Blocks.STONEBRICK);
        }

        for(int y=0; y<blocks.length; y++) {
            for(int x=0; x<blocks[y].length; x++) {
                for(int z=0; z<blocks[y][x].length; z++) {
                    if(((y==0 && ((x==7 && (z==1 || z==2 || z==3)) || (x==8 && (z==3 ||z==4))))||
                            (x==8 && (z==2 || (y==1 && z==1))) ||
                            (x==9 && ((z==3 || z==4) || (y==1 && z==2))))) {
                        blocks[y][x][z][0] = Block.getIdFromBlock(Blocks.COAL_ORE);
                    }else if(((y==0) && (x==1 && z==3)) ||
                            (y==1 && (x==0 && (z==3 || z==4)))) {
                        blocks[y][x][z][0] = Block.getIdFromBlock(Blocks.IRON_ORE);
                    }else if(y==0) {
                        if(x==3 || x==4 || x==5 || x==6) {
                            if(z==1 || z==2 || z==3)
                                blocks[y][x][z][0] = Block.getIdFromBlock(Blocks.STONEBRICK);
                            else
                                blocks[y][x][z][0] = Block.getIdFromBlock(Blocks.STONE);
                        }else
                            blocks[y][x][z][0] = Block.getIdFromBlock(Blocks.STONE);
                    }else {
                        if(x==0 || x==9) {
                            blocks[y][x][z][0] = StructureBase.SET_NO_BLOCK;
                        }else if(x==1 || x==8) {
                            if((y<3) && (z==3 || z==4 || z==5))
                                blocks[y][x][z][0] = Block.getIdFromBlock(Blocks.AIR);
                            else
                                blocks[y][x][z][0] = StructureBase.SET_NO_BLOCK;
                        }else if(x==2 || x==7) {
                            if(((y<3) && (z==2 || z==6)) || ((y<4) && (z>2 && z<6 || z==8)))
                                blocks[y][x][z][0] = Block.getIdFromBlock(Blocks.AIR);
                            else if(x==7 && (y==1 && z==1))
                                blocks[y][x][z][0] = Block.getIdFromBlock(Blocks.COAL_ORE);
                            else
                                blocks[y][x][z][0] = StructureBase.SET_NO_BLOCK;
                        }else {
                            if((y==1 || y==2) && (z==1 || ((x==3 || x==6) && (z==2 || z==3))))
                                blocks[y][x][z][0] = Block.getIdFromBlock(Blocks.STONEBRICK);
                            else if(y==1 && (x==3 && z==4)) {
                                blocks[y][x][z][0] = Block.getIdFromBlock(Blocks.ANVIL);
                                blocks[y][x][z][1] = rand.nextInt(4);
                            }else if(y==1 && (x==6 && z==4)) {
                                blocks[y][x][z][0] = Block.getIdFromBlock(Blocks.CHEST);
                                blocks[y][x][z][1] = EnumFacing.SOUTH.getIndex();
                            }else if(y==1 && (x==4 || x==5)) {
                                if(z==0)
                                    blocks[y][x][z][0] = StructureBase.SET_NO_BLOCK;
                                else if(z==2)
                                    blocks[y][x][z][0] = Block.getIdFromBlock(Blocks.LAVA);
                                else if(z==3)
                                    blocks[y][x][z][0] = Block.getIdFromBlock(Blocks.STONEBRICK);
                            }else if((y<4 && z>1) || (y<5 && (z>2 && z<6)))
                                blocks[y][x][z][0] = Block.getIdFromBlock(Blocks.AIR);
                            else
                                blocks[y][x][z][0] = StructureBase.SET_NO_BLOCK;
                        }
                    }
                }
            }
        }
        swapAdjacentToAir(blocks, wallBlock);
        if(rand.nextInt(4) > 0) {
            for(int y=0; y<blocks.length; y++) {
                for(int x=0; x<blocks[y].length; x++) {
                    for(int z=0; z<blocks[y][x].length; z++) {
                        if(blocks[y][x][z][0] == Block.getIdFromBlock(Blocks.COAL_ORE))
                            blocks[y][x][z][0] = StructureBase.SET_NO_BLOCK;
                    }
                }
            }
        }
        if(rand.nextInt(8) > 0) {
            for(int y=0; y<blocks.length; y++) {
                for(int x=0; x<blocks[y].length; x++) {
                    for(int z=0; z<blocks[y][x].length; z++) {
                        if(blocks[y][x][z][0] == Block.getIdFromBlock(Blocks.IRON_ORE))
                            blocks[y][x][z][0] = StructureBase.SET_NO_BLOCK;
                    }
                }
            }
        }
        if(rand.nextInt(20) == 0) {
            for(int y=0; y<blocks.length; y++) {
                for(int x=0; x<blocks[y].length; x++) {
                    for(int z=0; z<blocks[y][x].length; z++) {
                        if(blocks[y][x][z][0] == Block.getIdFromBlock(Blocks.AIR))
                            blocks[y][x][z][0] = Block.getIdFromBlock(Blocks.WATER);
                    }
                }
            }
        }

        return blocks;
    }

    private static void swapAdjacentToAir(int[][][][] blocks, int wallBlock) {
        for(int y=0; y<blocks.length; y++) {
            for(int x=0; x<blocks[y].length; x++) {
                for(int z=0; z<blocks[y][x].length; z++) {
                    if(blocks[y][x][z][0] == Block.getIdFromBlock(Blocks.AIR)) {
                        if(wallBlock != StructureBase.SET_NO_BLOCK) {
                            if(x+1 < blocks[y].length) {
                                if(blocks[y][x+1][z][0] != Block.getIdFromBlock(Blocks.AIR) &&
                                        blocks[y][x+1][z][0] != Block.getIdFromBlock(Blocks.ANVIL) &&
                                        blocks[y][x+1][z][0] != Block.getIdFromBlock(Blocks.CHEST) &&
                                        blocks[y][x+1][z][0] != Block.getIdFromBlock(Blocks.STONEBRICK))
                                    blocks[y][x+1][z][0] = wallBlock;
                            }
                            if(x-1 >= 0) {
                                if(blocks[y][x-1][z][0] != Block.getIdFromBlock(Blocks.AIR) &&
                                        blocks[y][x-1][z][0] != Block.getIdFromBlock(Blocks.ANVIL) &&
                                        blocks[y][x-1][z][0] != Block.getIdFromBlock(Blocks.CHEST) &&
                                        blocks[y][x-1][z][0] != Block.getIdFromBlock(Blocks.STONEBRICK))
                                    blocks[y][x-1][z][0] = wallBlock;
                            }
                            if(z+1 < blocks[y][x].length) {
                                if(blocks[y][x][z+1][0] != Block.getIdFromBlock(Blocks.AIR) &&
                                        blocks[y][x][z+1][0] != Block.getIdFromBlock(Blocks.ANVIL) &&
                                        blocks[y][x][z+1][0] != Block.getIdFromBlock(Blocks.CHEST) &&
                                        blocks[y][x][z+1][0] != Block.getIdFromBlock(Blocks.STONEBRICK))
                                    blocks[y][x][z+1][0] = wallBlock;
                            }
                            if(z-1 >= 0) {
                                if(blocks[y][x][z-1][0] != Block.getIdFromBlock(Blocks.AIR) &&
                                        blocks[y][x][z-1][0] != Block.getIdFromBlock(Blocks.ANVIL) &&
                                        blocks[y][x][z-1][0] != Block.getIdFromBlock(Blocks.CHEST) &&
                                        blocks[y][x][z-1][0] != Block.getIdFromBlock(Blocks.STONEBRICK))
                                    blocks[y][x][z-1][0] = wallBlock;
                            }
                        }
                    }
                }
            }
        }
    }
}
