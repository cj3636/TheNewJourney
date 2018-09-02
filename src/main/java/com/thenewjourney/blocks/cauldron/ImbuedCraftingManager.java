package com.thenewjourney.blocks.cauldron;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ImbuedCraftingManager {
    /**
     * The static instance of this class
     */
    private static final ImbuedCraftingManager INSTANCE = new ImbuedCraftingManager();
    private final List<IRecipe> recipes = Lists.newArrayList();

    /**
     * Returns the static instance of this class
     */
    public static ImbuedCraftingManager getInstance() {
        /** The static instance of this class */
        return INSTANCE;
    }
    private ImbuedCraftingManager() {


        /*Collections.sort(this.recipes, new Comparator<IRecipe>() {
            public int compare(IRecipe p_compare_1_, IRecipe p_compare_2_) {
                return p_compare_1_ instanceof ImbuedRecipe && p_compare_2_ instanceof ImbuedRecipe ? 1 : (p_compare_2_ instanceof ImbuedRecipe && p_compare_1_ instanceof ImbuedRecipe ? -1 : (p_compare_2_. < p_compare_1_.getRecipeSize() ? -1 : (p_compare_2_.getRecipeSize() > p_compare_1_.getRecipeSize() ? 1 : 0)));
            }
        });*/
    }

    /**
     * Adds a shaped recipe to the games recipe list.
     */
    public ImbuedRecipe addRecipe(ItemStack stack, Object... recipeComponents) {
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

        ImbuedRecipe InfuserRecipe = new ImbuedRecipe(j, k, aitemstack, stack);
        this.recipes.add(InfuserRecipe);
        return InfuserRecipe;
    }

    public List<IRecipe> getRecipeList() {
        return this.recipes;
    }
}