package com.thenewjourney.misc;

import com.thenewjourney.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TheNewJourneyTab extends CreativeTabs {
	
	public TheNewJourneyTab(String label) {
		super("TheNewJourneyTab");
		this.setBackgroundImageName("gui.png");
	}	
	@Override
	public ItemStack getTabIconItem() {
		ItemStack icon = new ItemStack(ModItems.DistortionGem);
	    return icon;
	}
}
