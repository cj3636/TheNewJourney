package com.thenewjourney.compat.jei.infuser;


import com.thenewjourney.blocks.infuser.InfuserRecipe;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class InfuserHandler implements IRecipeHandler<InfuserRecipe> {

    @Override
    public Class<InfuserRecipe> getRecipeClass() {
        return InfuserRecipe.class;
    }

    @Override
    public String getRecipeCategoryUid(InfuserRecipe recipe) {
        return InfuserCategory.INFUSION;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(InfuserRecipe recipe) {
        return new InfuserWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(InfuserRecipe recipe) {
        return true;
    }
}
