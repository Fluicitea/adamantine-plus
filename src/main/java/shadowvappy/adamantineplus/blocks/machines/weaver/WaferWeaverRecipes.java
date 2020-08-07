package shadowvappy.adamantineplus.blocks.machines.weaver;

import com.google.common.collect.Maps;
import net.minecraft.item.ItemStack;
import shadowvappy.adamantineplus.data.APModItems;

import java.util.Map;

@SuppressWarnings("ConstantConditions")
public class WaferWeaverRecipes {
    private static final WaferWeaverRecipes INSTANCE = new WaferWeaverRecipes();
    private final Map<ItemStack, ItemStack> smeltingList = Maps.newHashMap();
    private final Map<ItemStack, Integer> smeltingTime = Maps.newHashMap();
    private final Map<ItemStack, Float> experienceList = Maps.newHashMap();

    public static WaferWeaverRecipes getInstance() {
        return INSTANCE;
    }

    private WaferWeaverRecipes() {
        addWeavingRecipe(new ItemStack(APModItems.adamantineStrand, 3), new ItemStack(APModItems.adamantineWafer), 0.8F, 1200);
    }

    /**
     * Adds an weaving recipe if the recipe does not already exist
     * @param input The input of the recipe.
     * @param result The result of the recipe.
     * @param experience The experience given upon recipe completion.
     */
    public void addWeavingRecipe(ItemStack input, ItemStack result, float experience, int time) {
        if(getWeavingResult(input) != ItemStack.EMPTY) return;
        this.smeltingList.put(input, result);
        this.smeltingTime.put(input, time);
        this.experienceList.put(result, experience);
    }

    /**
     * Gets the input count of the extracting recipe with the given input.
     * @param input The input of the recipe.
     * @return The number of items the recipe requires.
     */
    public int getInputCount(ItemStack input) {
        for(Map.Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet()) {
            if(this.compareItemStacks(input, entry.getKey())) {
                return entry.getKey().getCount();
            }
        }
        return 0;
    }

    /**
     * Gets the result of the weaving recipe with the given input.
     * @param input The input of the recipe.
     * @return The result of the recipe, if it exists.
     */
    public ItemStack getWeavingResult(ItemStack input) {
        for(Map.Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet()) {
            if(this.compareItemStacks(input, entry.getKey())) {
                return entry.getValue();
            }
        }
        return ItemStack.EMPTY;
    }

    /**
     * Gets the time taken for the weaving recipe with the given input to complete.
     * @param input The input of the recipe.
     * @return The time taken for the recipe to complete, if it exists.
     */
    public int getWeavingTime(ItemStack input) {
        for(Map.Entry<ItemStack, Integer> entry : this.smeltingTime.entrySet()) {
            if(this.compareItemStacks(input, entry.getKey())) {
                return entry.getValue();
            }
        }
        return 0;
    }

    /**
     * Gets the experience given by the recipe with the given result.
     * @param result The result of the recipe.
     * @return The experience given by the recipe, if it exists.
     */
    public float getWeavingExperience(ItemStack result) {
        for(Map.Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
            if(this.compareItemStacks(result, entry.getKey()))
                return entry.getValue();
        }
        return 0.0F;
    }

    /**
     * Compares two item stacks to check if they are the same.
     * @param stack1 The item stack to compare.
     * @param stack2 The item stack to compare to.
     * @return True if the item stacks are identical, else false.
     */
    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
        return stack2.getItem() == stack1.getItem() && stack2.getMetadata() == stack1.getMetadata();
    }
}
