package com.thenewjourney.compat.jei.purifier;


import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.Arrays;
import java.util.List;

public class PurifierWrapper extends BlankRecipeWrapper implements IShapedCraftingRecipeWrapper {

    private final ShapedOreRecipe recipe;
    private final int width;
    private final int height;

    public PurifierWrapper(ShapedOreRecipe recipe) {
        this.recipe = recipe;
        for (Object input : this.recipe.getIngredients()) {
            if (input instanceof ItemStack) {
                ItemStack itemStack = (ItemStack) input;
                if (itemStack.getCount() != 1) {
                    itemStack.setCount(1);
                }
            }
        }
        this.width = ObfuscationReflectionHelper.getPrivateValue(ShapedOreRecipe.class, this.recipe, "width");
        this.height = ObfuscationReflectionHelper.getPrivateValue(ShapedOreRecipe.class, this.recipe, "height");
    }

    public static IJeiHelpers jeiHelpers;

    @Override
    public void getIngredients(IIngredients ingredients) {
        IStackHelper stackHelper = jeiHelpers.getStackHelper();

        List<List<ItemStack>> inputs = stackHelper.expandRecipeItemStackInputs(Arrays.asList(recipe.getIngredients()));
        ingredients.setInputLists(ItemStack.class, inputs);

        ItemStack recipeOutput = recipe.getRecipeOutput();
        if (recipeOutput != null) {
            ingredients.setOutput(ItemStack.class, recipeOutput);
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

}
