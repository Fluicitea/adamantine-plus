package shadowvappy.adamantineplus.tinkers.materials.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.traits.AbstractTraitLeveled;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class DurableTrait extends AbstractTraitLeveled {
    public static final int MAX_LEVELS = 3;

    public DurableTrait(int levels) {
        super("durable", 0x00FFFF, MAX_LEVELS, levels);
    }

    @Override
    public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
        super.applyEffect(rootCompound, modifierTag);
        ToolNBT data = TagUtil.getToolStats(rootCompound);
        ToolNBT base = TagUtil.getOriginalToolStats(rootCompound);
        data.durability += base.durability*bonusModifier(modifierTag);
        TagUtil.setToolTag(rootCompound, data.get());
    }

    @Override
    public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
        ModifierNBT data = new ModifierNBT(TinkerUtil.getModifierTag(tool, name));
        if(data.level >= 3)
            return 0;
        return damage;
    }

    private float bonusModifier(NBTTagCompound modifierTag) {
        ModifierNBT data = ModifierNBT.readTag(modifierTag);
        if(data.level == 1) {
            return 0.5f;
        }else if(data.level > 1) {
            return 1.5f;
        }
        return 0.0f;
    }

    @Override
    public String getTooltip(NBTTagCompound modifierTag, boolean detailed) {
        ModifierNBT data = ModifierNBT.readTag(modifierTag);
        if(data.level >= 3) {
            return "Unbreakable";
        }
        return super.getTooltip(modifierTag, detailed);
    }
}