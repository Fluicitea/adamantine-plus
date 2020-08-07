package shadowvappy.adamantineplus.tinkers.materials.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class AdamantineResistanceTrait extends AbstractArmorTrait {
    public AdamantineResistanceTrait() {
        super("adamantine_resistance", 0x00ffff);
    }

    @Override
    public float onHurt(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingHurtEvent evt) {
        if(source.getTrueSource() != null && !source.isExplosion() && !source.isMagicDamage()) {
            newDamage *= damage<6 ? (0.1*damage)-0.05 : 1-(2.75/damage);
        }

        return super.onHurt(armor, player, source, damage, newDamage, evt);
    }
}