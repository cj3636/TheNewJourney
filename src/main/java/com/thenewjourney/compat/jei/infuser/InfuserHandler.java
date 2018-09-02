package com.thenewjourney.compat.jei.infuser;


import com.thenewjourney.blocks.infuser.InfuserRecipe;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

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
