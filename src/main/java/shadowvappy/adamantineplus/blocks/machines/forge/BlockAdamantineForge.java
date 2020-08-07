package shadowvappy.adamantineplus.blocks.machines.forge;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import shadowvappy.adamantineplus.Main;
import shadowvappy.adamantineplus.data.APModCreativeTabs;
import shadowvappy.adamantineplus.data.APModReference;
import shadowvappy.harvestcore.config.HarvestCoreConfig;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class BlockAdamantineForge extends Block {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool BURNING = PropertyBool.create("burning");
    private static final int HARVEST_LEVEL = HarvestCoreConfig.addMineLevels ? 4 : 2;

    public BlockAdamantineForge(String name) {
        super(Material.IRON);
        setRegistryName(name);
        setUnlocalizedName(Objects.requireNonNull(getRegistryName()).toString());
        setCreativeTab(APModCreativeTabs.TAB_GENERAL);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(BURNING, false));

        setSoundType(SoundType.METAL);
        setHardness(6.0F);
        setResistance(6.0F);
        setHarvestLevel("pickaxe", HARVEST_LEVEL);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if(!worldIn.isRemote) {
            IBlockState north = worldIn.getBlockState(pos.north());
            IBlockState south = worldIn.getBlockState(pos.south());
            IBlockState east = worldIn.getBlockState(pos.east());
            IBlockState west = worldIn.getBlockState(pos.west());
            EnumFacing face = state.getValue(FACING);

            if(face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock())
                face = EnumFacing.SOUTH;
            else if(face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock())
                face = EnumFacing.NORTH;
            else if(face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock())
                face = EnumFacing.WEST;
            else if(face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock())
                face = EnumFacing.EAST;
            worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        IItemHandler capability = Objects.requireNonNull(worldIn.getTileEntity(pos)).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        for(int i = 0; i< Objects.requireNonNull(capability).getSlots(); i++) {
            ItemStack stack = capability.getStackInSlot(i);
            if(!stack.isEmpty())
                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
        }
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote)
            playerIn.openGui(Main.instance, APModReference.GUI_ADAMANTINE_FORGE, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, BURNING, FACING);
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getFront(meta);
        if(facing.getAxis() == EnumFacing.Axis.Y)
            facing = EnumFacing.NORTH;
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(FACING)).getIndex();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) { return new TileEntityAdamantineForge(); }
}