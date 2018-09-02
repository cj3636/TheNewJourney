package com.thenewjourney.misc;

import com.thenewjourney.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TheNewJourneyWeaponTab extends CreativeTabs {

    public TheNewJourneyWeaponTab(String label) {
        super("TheNewJourneyWeaponTab");
        this.setBackgroundImageName("gui.png");
    }

    @Override
    public ItemStack getTabIconItem() {
        ItemStack icon = new ItemStack(ModItems.AncientIngot);
        return icon;
    }
}
