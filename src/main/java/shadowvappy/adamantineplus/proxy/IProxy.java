package shadowvappy.adamantineplus.proxy;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public interface IProxy
{
    void preInit();

    void init();

    void postInit();

    /**
     * Get the client player
     *
     * @return The client player
     */
    @Nullable
    EntityPlayer getClientPlayer();

    /**
     * Get the client {@link World}
     *
     * @return The client world
     */
    @Nullable
    World getClientWorld();

    void registerFluidModel(Block fluidBlock, String name);
}