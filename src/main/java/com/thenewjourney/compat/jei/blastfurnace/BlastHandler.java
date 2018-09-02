package com.thenewjourney.compat.jei.blastfurnace;


import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class BlastHandler implements IRecipeHandler<BlastRecipe> {

    @Override
    public Class<BlastRecipe> getRecipeClass() {
        return BlastRecipe.class;
    }

    @Override
    public String getRecipeCategoryUid(BlastRecipe recipe) {
        return BlastCategory.BLASTFURNACE;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(BlastRecipe recipe) {
        return new BlastWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(BlastRecipe recipe) {
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
