package shadowvappy.adamantineplus.util.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import shadowvappy.adamantineplus.blocks.machines.extractor.ContainerStrandExtractor;
import shadowvappy.adamantineplus.blocks.machines.extractor.GuiStrandExtractor;
import shadowvappy.adamantineplus.blocks.machines.extractor.TileEntityStrandExtractor;
import shadowvappy.adamantineplus.blocks.machines.forge.ContainerAdamantineForge;
import shadowvappy.adamantineplus.blocks.machines.forge.GuiAdamantineForge;
import shadowvappy.adamantineplus.blocks.machines.forge.TileEntityAdamantineForge;
import shadowvappy.adamantineplus.blocks.machines.weaver.ContainerWaferWeaver;
import shadowvappy.adamantineplus.blocks.machines.weaver.GuiWaferWeaver;
import shadowvappy.adamantineplus.blocks.machines.weaver.TileEntityWaferWeaver;
import shadowvappy.adamantineplus.data.APModReference;

import java.util.Objects;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == APModReference.GUI_STRAND_EXTRACTOR)
            return new ContainerStrandExtractor(player.inventory, (TileEntityStrandExtractor)Objects.requireNonNull(world.getTileEntity(new BlockPos(x, y, z))));
        if(ID == APModReference.GUI_WAFER_WEAVER)
            return new ContainerWaferWeaver(player.inventory, (TileEntityWaferWeaver)Objects.requireNonNull(world.getTileEntity(new BlockPos(x, y, z))));
        if(ID == APModReference.GUI_ADAMANTINE_FORGE)
            return new ContainerAdamantineForge(player.inventory, (TileEntityAdamantineForge)Objects.requireNonNull(world.getTileEntity(new BlockPos(x, y, z))));
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == APModReference.GUI_STRAND_EXTRACTOR)
            return new GuiStrandExtractor(player.inventory, (TileEntityStrandExtractor)Objects.requireNonNull(world.getTileEntity(new BlockPos(x, y, z))));
        if(ID == APModReference.GUI_WAFER_WEAVER)
            return new GuiWaferWeaver(player.inventory, (TileEntityWaferWeaver)Objects.requireNonNull(world.getTileEntity(new BlockPos(x, y, z))));
        if(ID == APModReference.GUI_ADAMANTINE_FORGE)
            return new GuiAdamantineForge(player.inventory, (TileEntityAdamantineForge)Objects.requireNonNull(world.getTileEntity(new BlockPos(x, y, z))));
        return null;
    }
}