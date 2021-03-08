package com.thenewjourney.compat.jei.infuser;

import com.thenewjourney.blocks.infuser.InfuserRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;

public class InfuserWrapper extends BlankRecipeWrapper implements IShapedCraftingRecipeWrapper {

    private final InfuserRecipe recipe;

    public InfuserWrapper(InfuserRecipe recipe) {
        this.recipe = recipe;

    }

    @Override
    public void getIngredients(IIngredients ingredients) {

    }

    @Override
    public int getWidth() {
        return 3;
    }

    @Override
    public int getHeight() {
        return 3;
    }
}
