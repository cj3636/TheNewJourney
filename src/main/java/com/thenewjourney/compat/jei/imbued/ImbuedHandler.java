package com.thenewjourney.compat.jei.imbued;


import com.thenewjourney.blocks.cauldron.ImbuedRecipe;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class ImbuedHandler implements IRecipeHandler<ImbuedRecipe> {

    @Override
    public Class<ImbuedRecipe> getRecipeClass() {
        return ImbuedRecipe.class;
    }

    @Override
    public String getRecipeCategoryUid(ImbuedRecipe recipe) {
        return ImbuedCategory.IMBUED;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(ImbuedRecipe recipe) {
        return new ImbuedWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(ImbuedRecipe recipe) {
        if (recipe.getRecipeOutput() == null) {
            return false;
        }
        int inputCount = 0;
        for (ItemStack input : recipe.recipeItems) {
            if (input != null) {
                inputCount++;
            }
        }
        if (inputCount > 9) {
            return false;
        }
        return inputCount != 0;
    }
}
