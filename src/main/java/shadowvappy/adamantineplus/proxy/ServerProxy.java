package shadowvappy.adamantineplus.proxy;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ServerProxy implements IProxy {
    @Override
    public void preInit() {}

    @Override
    public void init() {}

    @Override
    public void postInit() {}

    @Override
    public EntityPlayer getClientPlayer() {
        return null;
    }

    @Override
    public World getClientWorld() {
        return null;
    }

    @Override
    public void registerFluidModel(Block fluidBlock, String name) {}
}