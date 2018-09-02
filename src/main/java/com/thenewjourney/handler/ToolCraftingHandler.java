package com.thenewjourney.handler;

import com.thenewjourney.items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class ToolCraftingHandler {

    @SubscribeEvent
    public void onCrafting(ItemCraftedEvent event) {

        for (int i = 0; i < event.craftMatrix.getSizeInventory(); i++) {
            if (event.craftMatrix.getStackInSlot(i) != null) {

                //Iron Hammer
                ItemStack item0 = event.craftMatrix.getStackInSlot(i);

                if (item0 != null && item0.getItem() == ModItems.Hammer) {
                    ItemStack k = new ItemStack(ModItems.Hammer, 2, (item0.getItemDamage() + 1));

                    if (k.getItemDamage() >= k.getMaxDamage()) {
                        k.setCount(k.getCount() - 1);
                    }
                    event.craftMatrix.setInventorySlotContents(i, k);
                }
            }
        }
    }
}
