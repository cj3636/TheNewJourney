package com.thenewjourney.compat.jei.arcane;

import com.cj3636.lib.Ref;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class ArcaneCategory extends BlankRecipeCategory<ArcaneWrapper> {

    private static final int craftOutputSlot = 0;
    private static final int craftInputSlot1 = 1;
    public static final String ArcaneFURNACE = "gui.thenewjourney.category.Arcanefurnace";
    private final IDrawable background;
    private final String localizedName;
    private final ICraftingGridHelper craftingGridHelper;

    public ArcaneCategory(IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(Ref.MODID, "textures/gui/arcanefurnacejei.png");
        background = guiHelper.createDrawable(location, 0, 0, 176, 166, 0, 0, 0, 0);
        localizedName = I18n.format("gui.thenewjourney.category.Arcanefurnace");
        craftingGridHelper = guiHelper.createCraftingGridHelper(craftInputSlot1, craftOutputSlot);
    }

    @Override
    public String getUid() {
        return ArcaneFURNACE;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public String getModName() {
        return "thenewjourney";
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, ArcaneWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(craftOutputSlot, false, 133, 60);

        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                int index = craftInputSlot1 + x + (y * 3);
                guiItemStacks.init(index, true, 27 + x * 18, 60 + y * 18);
            }
        }

        List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);
        List<List<ItemStack>> outputs = ingredients.getOutputs(ItemStack.class);

        if (recipeWrapper instanceof ArcaneWrapper) {
            ArcaneWrapper wrapper = recipeWrapper;
            craftingGridHelper.setInputStacks(guiItemStacks, inputs, wrapper.getWidth(), wrapper.getHeight());
            craftingGridHelper.setOutput(guiItemStacks, outputs.get(0));
        } else {
            craftingGridHelper.setInputStacks(guiItemStacks, inputs);
            craftingGridHelper.setOutput(guiItemStacks, outputs.get(0));
        }
    }
}
