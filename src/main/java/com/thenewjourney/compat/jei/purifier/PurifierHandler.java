package com.thenewjourney.compat.jei.purifier;


import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.List;

public class PurifierHandler implements IRecipeHandler<ShapedOreRecipe> {

    @Override
    public Class<ShapedOreRecipe> getRecipeClass() {
        return ShapedOreRecipe.class;
    }

    @Override
    public String getRecipeCategoryUid(ShapedOreRecipe recipe) {
        return PurifierCategory.PurifierFURNACE;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(ShapedOreRecipe recipe) {
        return new PurifierWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(ShapedOreRecipe recipe) {
        if (recipe.getRecipeOutput() == null) {
            return false;
        }
        int inputCount = 0;
        for (Object input : recipe.getIngredients()) {
            if (input instanceof List) {
                if (((List) input).isEmpty()) {
                    // missing items for an oreDict name. This is normal behavior, but the recipe is invalid.
                    return false;
                }
            }
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