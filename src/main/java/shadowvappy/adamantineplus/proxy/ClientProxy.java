package shadowvappy.adamantineplus.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ClientProxy implements IProxy
{
    private final Minecraft MINECRAFT = Minecraft.getMinecraft();

    @Override
    public void preInit() {}

    @Override
    public void init() {}

    @Override
    public void postInit() {}

    @Override
    public EntityPlayer getClientPlayer() {
        return MINECRAFT.player;
    }

    @Override
    public World getClientWorld() {
        return MINECRAFT.world;
    }

    @Override
    public void registerFluidModel(Block fluidBlock, String name) {}
}