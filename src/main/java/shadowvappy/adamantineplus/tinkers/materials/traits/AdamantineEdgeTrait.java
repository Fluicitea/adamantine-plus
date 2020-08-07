package shadowvappy.adamantineplus.tinkers.materials.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import slimeknights.tconstruct.library.potion.TinkerPotion;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.tools.traits.TraitSharp;

public class AdamantineEdgeTrait extends AbstractTrait {
    private static final TinkerPotion DOT = new TraitSharp.DoT();
    private static float piercedDamage;

    public AdamantineEdgeTrait() {
        super("adamantine_edge", 0x00ffff);
    }

    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        boolean hasArmor = target.getTotalArmorValue() > 0;
        if(hasArmor) {
            int armor = target.getTotalArmorValue();
            float armorNotPierced = 0.0f;
            float armorPierced = 1.0f;
            for(int i=0; i<armor; i+=4) {
                if(i>=4) {
                    armorNotPierced += 0.1f;
                    armorPierced -= 0.1f;
                }
            }
            newDamage = damage*armorNotPierced;
            piercedDamage = damage*armorPierced;
            return super.damage(tool, player, target, damage, newDamage, isCritical);
        }else {
            return super.damage(tool, player, target, damage, damage, isCritical);
        }
    }

    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
        boolean hasArmor = target.getTotalArmorValue() > 0;
        if(hasArmor) {
            if(player instanceof EntityPlayer) {
                dealPierceDamage(DamageSource.causePlayerDamage((EntityPlayer)player), target, piercedDamage);
            }else {
                dealPierceDamage(DamageSource.causeMobDamage(player), target, piercedDamage);
            }
        }
    }
    private static void dealPierceDamage(DamageSource source, EntityLivingBase target, float damage) {
        target.hurtResistantTime = 0;
        target.attackEntityFrom(source.setDamageBypassesArmor(), damage);
    }

    @Override
    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
        if(wasHit && target.isEntityAlive()) {
            target.setLastAttackedEntity(player);
            int armor = target.getTotalArmorValue();
            float bleedToApply = 1.0f;

            for(int i=0; i<armor; i+=4) {
                if(i>=4)
                    bleedToApply -= 0.1f;
            }
            DOT.apply(target, Math.round(333*bleedToApply));
        }
    }
}