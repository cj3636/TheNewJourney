package com.thenewjourney.compat.jei.blastfurnace;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class BlastCraftingManager {
    /**
     * The static instance of this class
     */
    private static final BlastCraftingManager INSTANCE = new BlastCraftingManager();
    private final List<IRecipe> recipes = Lists.newArrayList();

    /**
     * Returns the static instance of this class
     */
    public static BlastCraftingManager getInstance() {
        /** The static instance of this class */
        return INSTANCE;
    }

    private BlastCraftingManager() {
        //Steel
        this.addRecipe(new ItemStack(ModItems.SteelIngot), "X  ", "Y   ", "   ", 'X', ModItems.Carbon, 'Y', ModItems.LowGradeSteelIngot);
        this.addRecipe(new ItemStack(ModItems.BronzeIngot), "X  ", "Y   ", "   ", 'X', ModItems.CopperIngot, 'Y', ModItems.TinIngot);
        this.addRecipe(new ItemStack(ModItems.SteelRod), "X  ", "X   ", "   ", 'X', ModItems.SteelIngot);
        this.addRecipe(new ItemStack(ModBlocks.ObsidianGlass, 2), "X  ", "Y   ", "   ", 'X', Blocks.OBSIDIAN, 'Y', Blocks.SAND);
    }

    /**
     * Adds a shaped recipe to the games recipe list.
     */
    public BlastRecipe addRecipe(ItemStack stack, Object... recipeComponents) {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (recipeComponents[i] instanceof String[]) {
            String[] astring = (String[]) recipeComponents[i++];

            for (String s2 : astring) {
                ++k;
                j = s2.length();
                s = s + s2;
            }
        } else {
            while (recipeComponents[i] instanceof String) {
                String s1 = (String) recipeComponents[i++];
                ++k;
                j = s1.length();
                s = s + s1;
            }
        }

        Map<Character, ItemStack> map;

        for (map = Maps.newHashMap(); i < recipeComponents.length; i += 2) {
            Character character = (Character) recipeComponents[i];
            ItemStack itemstack = null;

            if (recipeComponents[i + 1] instanceof Item) {
                itemstack = new ItemStack((Item) recipeComponents[i + 1]);
            } else if (recipeComponents[i + 1] instanceof Block) {
                itemstack = new ItemStack((Block) recipeComponents[i + 1], 1, 32767);
            } else if (recipeComponents[i + 1] instanceof ItemStack) {
                itemstack = (ItemStack) recipeComponents[i + 1];
            }

            map.put(character, itemstack);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        for (int l = 0; l < j * k; ++l) {
            char c0 = s.charAt(l);

            if (map.containsKey(Character.valueOf(c0))) {
                aitemstack[l] = map.get(Character.valueOf(c0)).copy();
            } else {
                aitemstack[l] = null;
            }
        }

        BlastRecipe InfuserRecipe = new BlastRecipe(j, k, aitemstack, stack);
        this.recipes.add(InfuserRecipe);
        return InfuserRecipe;
    }

    public void addRecipe(IRecipe recipe) {
        this.recipes.add(recipe);
    }

    /**
     * Retrieves an ItemStack that has multiple recipes for it.
     */
    @Nullable
    public ItemStack findMatchingRecipe(InventoryCrafting craftMatrix, World worldIn) {
        for (IRecipe irecipe : this.recipes) {
            if (irecipe.matches(craftMatrix, worldIn)) {
                return irecipe.getCraftingResult(craftMatrix);
            }
        }

        return null;
    }


    public List<IRecipe> getRecipeList() {
        return this.recipes;
    }
}