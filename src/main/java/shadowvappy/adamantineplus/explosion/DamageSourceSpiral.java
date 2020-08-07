package shadowvappy.adamantineplus.explosion;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class DamageSourceSpiral extends DamageSource {
    String prevMessage = "";
    int calls = 0;

    public DamageSourceSpiral() {
        super("spiral_explosion");
        setDamageBypassesArmor();
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase target) {
        Random rand = new Random();
        String string = "death.attack." + this.damageType + "." + rand.nextInt(4);
        String s = prevMessage;
        NBTTagCompound tag = target.getEntityData();
        ++calls;

        if(calls%2 == 1) {
            if(!tag.getBoolean("sinned")) {
                tag.setBoolean("sinned", true);
                s = string;
            }else {
                if(rand.nextBoolean()) {
                    if(rand.nextInt(20) == 1)
                        s = "death.attack." + this.damageType + ".stop";
                    else
                        s = string + ".again";
                }else
                    s = string;
            }
            if(!s.equals("death.attack." + this.damageType + ".stop"))
                prevMessage = s;
        }

        return new TextComponentTranslation(s, target.getDisplayName());
    }
}