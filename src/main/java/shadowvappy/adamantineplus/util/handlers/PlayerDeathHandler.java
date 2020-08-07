package shadowvappy.adamantineplus.util.handlers;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class PlayerDeathHandler {
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        NBTTagCompound tagOld = event.getOriginal().getEntityData();
        NBTTagCompound tag = event.getEntityPlayer().getEntityData();

        if(event.isWasDeath()) {
            if(!tagOld.hasKey("sinned")) {
                tag.setBoolean("sinned", false);
            }else {
                tag.setBoolean("sinned", tagOld.getBoolean("sinned"));
            }
        }
    }
}