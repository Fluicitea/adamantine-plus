package shadowvappy.adamantineplus.world.structures;

import net.minecraft.block.Block;
import shadowvappy.adamantineplus.data.APModBlocks;
import shadowvappy.harvestcore.common.structures.Structure;
import shadowvappy.harvestcore.common.structures.StructureBase;

import java.util.Random;

@SuppressWarnings("ConstantConditions")
public class SpiralStructure extends Structure {
    public SpiralStructure(String name) {
        super(name);
        this.addBlockArray(spiralStructureArray());
    }

    private static int[][][][] spiralStructureArray() {
        int[][][][] blocks; //Creates an empty array of blocks for the structure
        Random rand = new Random(); //Creates a new Random
        int height = rand.nextInt(4) + 5; //Sets the max height of the spiral randomly between 5 and 8
        float maxWidth = height%2==0 ? height+1 : height; //Sets the max width of the spiral based on height
        int centerArray = (int)Math.ceil(maxWidth/2); //Gets the center of the spiral
        blocks = new int[height][(int)maxWidth][(int)maxWidth][1]; //Sets the maximum size of the blocks array

        for(int y=0; y<blocks.length; y++) { //Loops through all y values of the spiral
            for(int x=0; x<blocks[y].length; x++) { //Loops through all x values of the current y value
                for(int z=0; z<blocks[y][x].length; z++) { //Loops through all z values of the current x value and y value
                    if(y==0) {
                        //Sets only the center block of the array to the ore if it is the base of the spiral
                        if(x==centerArray && z==centerArray) {
                            blocks[y][x][z][0] = Block.getIdFromBlock(APModBlocks.adamantineOre);
                        }else {
                            blocks[y][x][z][0] = StructureBase.SET_NO_BLOCK;
                        }
                    }else if(y==1) {
                        if(x==centerArray && z==centerArray) {
                            //Sets the center block of the array to spiral core if it is the block above the base of the spiral
                            blocks[y][x][z][0] = Block.getIdFromBlock(APModBlocks.fakeAir);
                        }else if(((x==centerArray+1 || x==centerArray-1) && z==centerArray) ||
                                ((z==centerArray+1 || z==centerArray-1) && x==centerArray)){
                            //Sets the blocks around the spiral core to ore
                            blocks[y][x][z][0] = Block.getIdFromBlock(APModBlocks.adamantineOre);
                        }else {
                            blocks[y][x][z][0] = StructureBase.SET_NO_BLOCK;
                        }
                    }else {
                        if(y<4) {
                            //If it is y values 2 or 3 of the spiral, set the block above spiral core to also be spiral core
                            if(blocks[y-1][x][z][0] == Block.getIdFromBlock(APModBlocks.fakeAir)) {
                                blocks[y][x][z][0] = Block.getIdFromBlock(APModBlocks.fakeAir);
                            }
                        }else if(y<blocks.length-1) {
                            //If it is any y value except the top, randomly set the block above spiral core to spiral core or ore
                            if(blocks[y-1][x][z][0] == Block.getIdFromBlock(APModBlocks.fakeAir)) {
                                if(rand.nextBoolean()) {
                                    blocks[y][x][z][0] = Block.getIdFromBlock(APModBlocks.fakeAir);
                                }else {
                                    blocks[y][x][z][0] = Block.getIdFromBlock(APModBlocks.adamantineOre);
                                }
                            }
                        }else {
                            //If it is the top of the spiral, set the block above spiral core to ore
                            if(blocks[y-1][x][z][0] == Block.getIdFromBlock(APModBlocks.fakeAir)) {
                                blocks[y][x][z][0] = Block.getIdFromBlock(APModBlocks.adamantineOre);
                            }
                        }
                        if(blocks[y][x][z][0] == Block.getIdFromBlock(APModBlocks.fakeAir)) {
                            //Set spiral core to spiral core
                            blocks[y][x][z][0] = Block.getIdFromBlock(APModBlocks.fakeAir);
                        }else if(blocks[y][x][z][0] == Block.getIdFromBlock(APModBlocks.adamantineOre)) {
                            //Set ore to ore
                            blocks[y][x][z][0] = Block.getIdFromBlock(APModBlocks.adamantineOre);
                        }else if(blocks[y-1][x][z][0] == Block.getIdFromBlock(APModBlocks.adamantineOre)) {
                            //Set blocks above ore to ore
                            blocks[y][x][z][0] = Block.getIdFromBlock(APModBlocks.adamantineOre);
                        }else if((x<maxWidth-1 && blocks[y-1][x+1][z][0] == Block.getIdFromBlock(APModBlocks.adamantineOre)) ||
                                (x>0 && blocks[y-1][x-1][z][0] == Block.getIdFromBlock(APModBlocks.adamantineOre)) ||
                                (z<maxWidth-1 && blocks[y-1][x][z+1][0] == Block.getIdFromBlock(APModBlocks.adamantineOre)) ||
                                (z>0 && blocks[y-1][x][z-1][0] == Block.getIdFromBlock(APModBlocks.adamantineOre))) {
                            //If it is a valid place for the block (within the core maximum size) and is next to a block that will be ore
                            //Randomly change the block to ore or nothing
                            if(rand.nextInt(6) == 1) {
                                blocks[y][x][z][0] = Block.getIdFromBlock(APModBlocks.adamantineOre);
                            }else {
                                blocks[y][x][z][0] = StructureBase.SET_NO_BLOCK;
                            }
                        }else {
                            //Set the block to be nothing
                            blocks[y][x][z][0] = StructureBase.SET_NO_BLOCK;
                        }
                    }
                }
            }
        }
        return blocks;
    }
}