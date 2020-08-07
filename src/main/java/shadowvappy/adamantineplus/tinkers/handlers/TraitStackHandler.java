package shadowvappy.adamantineplus.tinkers.handlers;

import c4.conarm.lib.events.ArmoryEvent;
import com.google.common.collect.ImmutableList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadowvappy.adamantineplus.tinkers.materials.traits.Traits;
import slimeknights.tconstruct.library.events.TinkerEvent;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class TraitStackHandler {
    @SubscribeEvent
    public static void onItemBuilding(TinkerEvent.OnItemBuilding event) {
        NBTTagCompound tag = event.tag; //Get the tag with the data of the item
        ImmutableList<Material> matList = event.materials; //Get a list of the materials the item is made of
        NBTTagList traitList = TagUtil.getModifiersTagList(tag); //Get a list of traits the item has
        int levelDur = 0; //Create an int for the level of durable, to increase if adamantine material found

        ModifierNBT data = new ModifierNBT(TinkerUtil.getModifierTag(tag, Traits.durable.getModifierIdentifier())); //Get the data of the durable trait, if it exists
        int index = TinkerUtil.getIndexInCompoundList(traitList, Traits.durable.getModifierIdentifier()); //Get the index of the durable trait, if it exists
        //Checks through all materials the item is made of, and increments levelDur up to a maximum of 3 for each instance of adamantine
        for (Material mat : matList) {
            if (mat.getLocalizedName().equalsIgnoreCase("adamantine")) {
                levelDur += levelDur < 3 ? 1 : 0;
            }
        }
        NBTTagCompound traitTag; //Creates a new NBTTagCompound
        if (index > -1) {
            traitTag = traitList.getCompoundTagAt(index); //Sets the traitTag to the compound tag of durable trait

            data.level = levelDur; //Sets the level of the durable trait in data to levelDur
            data.write(traitTag); //Writes the data to the traitTag
            traitTag.setBoolean(Traits.durable.identifier, true); //Sets the traitTag to have the durable trait identifier
            traitList.set(index, traitTag); //Sets the traitTag to the index of durable trait in the trait list
            TagUtil.setModifiersTagList(tag, traitList); //Sets the tag list of the item to the trait list
        }
    }

    @SubscribeEvent
    public static void onArmorBuilding(ArmoryEvent.OnItemBuilding event) {
        NBTTagCompound tag = event.tag; //Get the tag with the data of the item
        ImmutableList<Material> matList = event.materials; //Get a list of the materials the item is made of
        NBTTagList traitList = TagUtil.getModifiersTagList(tag); //Get a list of traits the item has
        int levelDur = 0; //Create an int for the level of durable, to increase if adamantine material found

        ModifierNBT data = new ModifierNBT(TinkerUtil.getModifierTag(tag, Traits.durableArmor.getModifierIdentifier())); //Get the data of the durable trait, if it exists
        int index = TinkerUtil.getIndexInCompoundList(traitList, Traits.durableArmor.getModifierIdentifier()); //Get the index of the durable trait, if it exists
        //Checks through all materials the item is made of, and increments levelDur up to a maximum of 3 for each instance of adamantine
        for (Material mat : matList) {
            if (mat.getLocalizedName().equalsIgnoreCase("adamantine")) {
                levelDur += levelDur < 3 ? 1 : 0;
            }
        }
        NBTTagCompound traitTag; //Creates a new NBTTagCompound
        if (index > -1) {
            traitTag = traitList.getCompoundTagAt(index); //Sets the traitTag to the compound tag of durable trait

            data.level = levelDur; //Sets the level of the durable trait in data to levelDur
            data.write(traitTag); //Writes the data to the traitTag
            traitTag.setBoolean(Traits.durableArmor.identifier, true); //Sets the traitTag to have the durable trait identifier
            traitList.set(index, traitTag); //Sets the traitTag to the index of durable trait in the trait list
            TagUtil.setModifiersTagList(tag, traitList); //Sets the tag list of the item to the trait list
        }
    }
}