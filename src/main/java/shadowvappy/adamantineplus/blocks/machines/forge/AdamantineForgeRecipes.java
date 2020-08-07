package shadowvappy.adamantineplus.blocks.machines.forge;

import com.google.common.collect.Maps;
import net.minecraft.item.ItemStack;
import shadowvappy.adamantineplus.data.APModItems;

import java.util.Map;

@SuppressWarnings("ConstantConditions")
public class AdamantineForgeRecipes {
    private static final AdamantineForgeRecipes INSTANCE = new AdamantineForgeRecipes();
    private final Map<ItemStack, ItemStack> smeltingList = Maps.newHashMap();
    private final Map<ItemStack, Integer> smeltingTime = Maps.newHashMap();
    private final Map<ItemStack, Float> experienceList = Maps.newHashMap();

    public static AdamantineForgeRecipes getInstance() {
        return INSTANCE;
    }

    private AdamantineForgeRecipes() {
        addForgingRecipe(new ItemStack(APModItems.adamantineSword), new ItemStack(APModItems.adamantineWafer, 7), 4.0F, 1500);
        addForgingRecipe(new ItemStack(APModItems.adamantinePickaxe), new ItemStack(APModItems.adamantineWafer, 11), 4.0F, 1800);
        addForgingRecipe(new ItemStack(APModItems.adamantineAxe), new ItemStack(APModItems.adamantineWafer, 11), 4.0F, 1800);
        addForgingRecipe(new ItemStack(APModItems.adamantineShovel), new ItemStack(APModItems.adamantineWafer, 5), 4.0F, 1200);
        addForgingRecipe(new ItemStack(APModItems.adamantineHoe), new ItemStack(APModItems.adamantineWafer, 8), 4.0F, 1400);
        addForgingRecipe(new ItemStack(APModItems.adamantineHelmet), new ItemStack(APModItems.adamantineWafer, 15), 4.0F, 2400);
        addForgingRecipe(new ItemStack(APModItems.adamantineChestplate), new ItemStack(APModItems.adamantineWafer, 24), 4.0F, 3000);
        addForgingRecipe(new ItemStack(APModItems.adamantineLeggings), new ItemStack(APModItems.adamantineWafer, 21), 4.0F, 2600);
        addForgingRecipe(new ItemStack(APModItems.adamantineBoots), new ItemStack(APModItems.adamantineWafer, 12), 4.0F, 2800);
    }

    /**
     * Adds an extracting recipe if the recipe does not already exist
     * @param result The result of the recipe.
     * @param input The input of the recipe.
     * @param experience The chance for the recipe to drop an experience.
     */
    public void addForgingRecipe(ItemStack result, ItemStack input, float experience, int time) {
        if(getForgingInput(result) != ItemStack.EMPTY) return;
        this.smeltingList.put(result, input);
        this.smeltingTime.put(result, time);
        this.experienceList.put(result, experience);
    }

    /**
     * Gets the input count of the extracting recipe with the given result.
     * @param result The input of the recipe.
     * @return The number of items the recipe requires.
     */
    public int getInputCount(ItemStack result) {
        for(Map.Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet()) {
            if(this.compareItemStacks(result, entry.getKey())) {
                return entry.getValue().getCount();
            }
        }
        return 0;
    }

    /**
     * Gets the result of the extracting recipe with the given input.
     * @param result The input of the recipe.
     * @return The result of the recipe, if it exists.
     */
    public ItemStack getForgingInput(ItemStack result) {
        for(Map.Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet()) {
            if(this.compareItemStacks(result, entry.getKey())) {
                return entry.getValue();
            }
        }
        return ItemStack.EMPTY;
    }

    /**
     * Gets the time taken for the extracting recipe with the given input to complete.
     * @param result The input of the recipe.
     * @return The time taken for the recipe to complete, if it exists.
     */
    public int getForgingTime(ItemStack result) {
        for(Map.Entry<ItemStack, Integer> entry : this.smeltingTime.entrySet()) {
            if(this.compareItemStacks(result, entry.getKey())) {
                return entry.getValue();
            }
        }
        return 0;
    }

    /**
     * Gets the experience given by the recipe with the given result.
     *
     * @param result The result of the recipe.
     * @return The experience given by the recipe, if it exists.
     */
    public float getForgingExperience(ItemStack result) {
        for (Map.Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
            if (this.compareItemStacks(result, entry.getKey()))
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
