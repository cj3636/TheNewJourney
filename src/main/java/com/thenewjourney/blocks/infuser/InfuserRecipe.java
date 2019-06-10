package com.thenewjourney.blocks.infuser;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;

import java.util.List;

public class InfuserRecipe extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

    /**
     * Is the ItemStack that you get when craft the recipe.
     */
    private final ItemStack recipeOutput;
    /**
     * Is a List of ItemStack that composes the recipe.
     */
    public final NonNullList<Ingredient> recipeItems;
    private final String group;
    private final boolean isSimple;

    public InfuserRecipe(String group, ItemStack output, NonNullList<Ingredient> ingredients) {
        this.group = group;
        this.recipeOutput = output;
        this.recipeItems = ingredients;
        boolean simple = true;
        for (Ingredient i : ingredients)
            simple &= i.isSimple();
        this.isSimple = simple;
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        if (!inv.getName().equals("container.infuser")) {
            return false;
        }
        int ingredientCount = 0;
        net.minecraft.client.util.RecipeItemHelper recipeItemHelper = new net.minecraft.client.util.RecipeItemHelper();
        List<ItemStack> inputs = Lists.newArrayList();
        for (int i = 0; i < inv.getHeight(); ++i) {
            for (int j = 0; j < inv.getWidth(); ++j) {
                ItemStack itemstack = inv.getStackInRowAndColumn(j, i);

                if (!itemstack.isEmpty()) {
                    ++ingredientCount;
                    if (this.isSimple)
                        recipeItemHelper.accountStack(itemstack, 1);
                    else
                        inputs.add(itemstack);
                }
            }
        }

        if (ingredientCount != this.recipeItems.size())
            return false;

        if (this.isSimple)
            return recipeItemHelper.canCraft(this, null);

        return net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs, this.recipeItems) != null;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return this.recipeOutput.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return width * height >= this.recipeItems.size();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        NonNullList<ItemStack> nonnulllist = NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);
        if (!inv.getName().equals("container.infuser")) {
            return nonnulllist;
        }
        for (int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);

            nonnulllist.set(i, net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack));
        }

        return nonnulllist;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.recipeItems;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    public static class Factory implements IRecipeFactory {
        @Override
        public IRecipe parse(JsonContext context, JsonObject json) {
            return InfuserRecipes.deserialize(json);
        }
    }

}