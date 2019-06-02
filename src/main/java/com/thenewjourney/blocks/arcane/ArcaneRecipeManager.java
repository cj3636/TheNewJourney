package com.thenewjourney.blocks.arcane;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thenewjourney.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ArcaneRecipeManager implements IRecipe {
    private static final ArcaneRecipeManager SMELTING_BASE = new ArcaneRecipeManager();
    private final Map<ItemStack, ItemStack> smeltingList = Maps.newHashMap();
    public static ArrayList<ItemStack> refiningList = new ArrayList<>();
    private final Map<ItemStack, Float> experienceList = Maps.newHashMap();
    private final List<IRecipe> recipes = Lists.newArrayList();

    /**
     * Returns an instance of FurnaceRecipes.
     */
    public static ArcaneRecipeManager getInstance() {
        return SMELTING_BASE;
    }

    private ArcaneRecipeManager() {

        this.addSmelting(ModItems.ArchaicOrb, new ItemStack(ModItems.ArchiumIngot), 1.0F);
        this.addSmelting(ModItems.FloricOrb, new ItemStack(ModItems.FloriumIngot), 1.0F);
        this.addSmelting(ModItems.QuazanScale, new ItemStack(ModItems.VitaemIngot), 1.0F);
    }

    /**
     * Adds a smelting recipe, where the input item is an instance of Block.
     */
    public void addSmeltingRecipeForBlock(Block input, ItemStack stack, float experience) {
        this.addSmelting(Item.getItemFromBlock(input), stack, experience);
    }

    /**
     * Adds a smelting recipe using an Item as the input item.
     */
    public void addSmelting(Item input, ItemStack stack, float experience) {
        this.addSmeltingRecipe(new ItemStack(input, 1, 32767), stack, experience);
    }

    /**
     * Adds a smelting recipe using an ItemStack as the input for the recipe.
     */
    public void addSmeltingRecipe(ItemStack input, ItemStack stack, float experience) {
        if (getSmeltingResult(input) == null) {
            net.minecraftforge.fml.common.FMLLog.info("Ignored smelting recipe with conflicting input: " + input + " = " + stack);
            return;
        }
        this.smeltingList.put(input, stack);
        this.experienceList.put(stack, Float.valueOf(experience));
    }

    /**
     * Returns the smelting result of an item.
     */
    @Nullable
    public ItemStack getSmeltingResult(ItemStack stack) {
        for (Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet()) {
            if (this.compareItemStacks(stack, entry.getKey())) {
                return entry.getValue();
            }
        }
        return ItemStack.EMPTY;
    }

    @Nullable
    public ItemStack getBlastSmeltingResult(ItemStack stack1, ItemStack stack2) {
        if (stack1 != ItemStack.EMPTY && stack2 != ItemStack.EMPTY) {
            if (stack1.getItem() == ModItems.LowGradeSteelIngot && stack2.getItem() == ModItems.Carbon) {
                return new ItemStack(ModItems.SteelIngot);
            }
            if (stack1.getItem() == ModItems.Carbon && stack2.getItem() == ModItems.LowGradeSteelIngot) {
                int length = stack1.toString().length();
                return new ItemStack(ModItems.SteelIngot);
            }
            if (stack1.getItem() == ModItems.SteelIngot && stack2.getItem() == ModItems.SteelIngot) {
                int length = stack1.toString().length();
                return new ItemStack(ModItems.SteelRod);
            }
        }
        if (stack2 != ItemStack.EMPTY) {
            return ItemStack.EMPTY;
        }
        if (stack1 != ItemStack.EMPTY) {
            getSmeltingResult(stack1);
        }
        return ItemStack.EMPTY;
    }

    /**
     * Compares two itemstacks to ensure that they are the same. This checks both the item and the metadata of the item.
     */
    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Map<ItemStack, ItemStack> getSmeltingList() {
        return this.smeltingList;
    }

    public ArrayList<ItemStack> getRefiningList() {
        return refiningList;
    }

    public float getSmeltingExperience(ItemStack stack) {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;

        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
            if (this.compareItemStacks(stack, entry.getKey())) {
                return entry.getValue().floatValue();
            }
        }

        return 0.0F;
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        return false;
    }

    @Nullable
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Nullable
    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    @Override
    public IRecipe setRegistryName(ResourceLocation name) {
        return null;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return null;
    }

    @Override
    public Class<IRecipe> getRegistryType() {
        return null;
    }
}