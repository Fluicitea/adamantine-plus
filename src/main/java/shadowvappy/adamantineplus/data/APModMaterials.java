package shadowvappy.adamantineplus.data;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import shadowvappy.adamantineplus.util.HarvestLevels;

public class APModMaterials {
    /* Tool Materials: (name, harvest level, durability, efficiency, damage, enchantability) */
    public static final Item.ToolMaterial materialAdamantine =
            EnumHelper.addToolMaterial("material_adamantine", HarvestLevels.ADAMANTINE_MINE_LEVEL, 2632, 12.0F, 7.0F, 12);
    public static final Item.ToolMaterial materialCopper =
            EnumHelper.addToolMaterial("material_copper", HarvestLevels.COPPER_MINE_LEVEL, 200, 5.0F, 1.0F, 8);
    public static final Item.ToolMaterial materialLead =
            EnumHelper.addToolMaterial("material_lead", HarvestLevels.LEAD_MINE_LEVEL, 400, 5.0F, 1.5F, 15);
    public static final Item.ToolMaterial materialSilver =
            EnumHelper.addToolMaterial("material_silver", HarvestLevels.SILVER_MINE_LEVEL, 320, 5.0F, 2.5F, 20);

    /* Armor Materials: (name, texture name, durability, reduction amounts {boots, leggings, chest, helmet}, enchantability, equip sound, toughness */
    public static final ItemArmor.ArmorMaterial armorMaterialAdamantine =
            EnumHelper.addArmorMaterial("armor_material_adamantine", APModReference.MODID + ":adamantine", 37, new int[]{4, 8, 9, 4}, 20,
                    SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 4.0F);
    public static final ItemArmor.ArmorMaterial armorMaterialCopper =
            EnumHelper.addArmorMaterial("armor_material_copper", APModReference.MODID + ":copper", 13, new int[]{1, 3, 4, 1}, 8,
                    SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
    public static final ItemArmor.ArmorMaterial armorMaterialLead =
            EnumHelper.addArmorMaterial("armor_material_lead", APModReference.MODID + ":lead", 25, new int[]{2, 4, 5, 2}, 15,
                    SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
    public static final ItemArmor.ArmorMaterial armorMaterialSilver =
            EnumHelper.addArmorMaterial("armor_material_silver", APModReference.MODID + ":silver", 18, new int[]{2, 5, 6, 2}, 20,
                    SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);
}
