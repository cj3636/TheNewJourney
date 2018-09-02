package com.thenewjourney.compat.jei.blastfurnace;

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

public class BlastCategory extends BlankRecipeCategory<BlastWrapper> {

	private static final int craftOutputSlot = 0;
	private static final int craftInputSlot1 = 1;
	public static final String BLASTFURNACE = "gui.thenewjourney.category.blastfurnace";
	private final IDrawable background;
	private final String localizedName;
	private final ICraftingGridHelper craftingGridHelper;
	public BlastCategory(IGuiHelper guiHelper) {
		ResourceLocation location = new ResourceLocation(Ref.MODID, "textures/gui/blastfurnaceJei.png");
		background = guiHelper.createDrawable(location, 0, 0, 176, 166, 0, 0, 0, 0);
		localizedName = I18n.format("gui.thenewjourney.category.blastfurnace");
		craftingGridHelper = guiHelper.createCraftingGridHelper(craftInputSlot1, craftOutputSlot);
	}
	@Override
	public String getUid() {
		return BLASTFURNACE;
	}
	@Override
	public String getTitle() {
		return localizedName;
	}

	@Override
	public String getModName() {
		return Ref.MODID;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, BlastWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(craftOutputSlot, false, 133, 41);

		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 3; ++x) {
				int index = craftInputSlot1 + x + (y * 3);
				guiItemStacks.init(index, true, 25 + x * 18, 31 + y * 18);
			}
		}

		List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);
		List<List<ItemStack>> outputs = ingredients.getOutputs(ItemStack.class);

		if (recipeWrapper instanceof BlastWrapper) {
			BlastWrapper wrapper = recipeWrapper;
			craftingGridHelper.setInputStacks(guiItemStacks, inputs, wrapper.getWidth(), wrapper.getHeight());
			craftingGridHelper.setOutput(guiItemStacks, outputs.get(0));
		} else {
			craftingGridHelper.setInputStacks(guiItemStacks, inputs);
			craftingGridHelper.setOutput(guiItemStacks, outputs.get(0));
		}
	}
}
