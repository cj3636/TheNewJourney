package com.thenewjourney.compat.jei.arcane;


import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class ArcaneHandler implements IRecipeHandler<ArcaneRecipe> {

    @Override
    public Class<ArcaneRecipe> getRecipeClass() {
        return ArcaneRecipe.class;
    }

    @Override
    public String getRecipeCategoryUid(ArcaneRecipe recipe) {
        return ArcaneCategory.ArcaneFURNACE;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(ArcaneRecipe recipe) {
        return new ArcaneWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(ArcaneRecipe recipe) {
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
