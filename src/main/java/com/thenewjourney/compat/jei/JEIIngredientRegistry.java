package com.thenewjourney.compat.jei;

import mezz.jei.api.recipe.IIngredientType;
import net.minecraft.item.ItemStack;

public class JEIIngredientRegistry implements IIngredientType {
    @Override
    public Class getIngredientClass() {
        return ItemStack.class;
    }
}
