package com.thenewjourney.compat.jei.imbued;

import com.thenewjourney.blocks.cauldron.ImbuedRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ImbuedWrapper extends BlankRecipeWrapper implements IShapedCraftingRecipeWrapper {

    private final ImbuedRecipe recipe;

    public ImbuedWrapper(ImbuedRecipe recipe) {
        this.recipe = recipe;
        for (ItemStack itemStack : this.recipe.recipeItems) {
            if (itemStack != null && itemStack.getCount() != 1) {
                itemStack.setCount(1);
            }
        }
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        List<ItemStack> recipeItems = Arrays.asList(recipe.recipeItems);
        ingredients.setInputs(ItemStack.class, recipeItems);

        ItemStack recipeOutput = recipe.getRecipeOutput();
        if (recipeOutput != null) {
            ingredients.setOutput(ItemStack.class, recipeOutput);
        }
    }

    @Override
    public int getWidth() {
        return recipe.recipeWidth;
    }

    @Override
    public int getHeight() {
        return recipe.recipeHeight;
    }
}
