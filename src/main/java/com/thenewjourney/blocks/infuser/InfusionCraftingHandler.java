package com.thenewjourney.blocks.infuser;

import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.compat.baubles.InitItems;
import com.thenewjourney.items.ModItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
@Deprecated
public class InfusionCraftingHandler {
	
	//This class is BS and needs to be replaced with a proper method in InfuserContainer
	@SubscribeEvent
	public void onInfusion(ItemCraftedEvent event) {
		Item item = event.crafting.getItem();
		if (item == ModItems.AquisCrystal ||
			item == ModItems.ShadowCrystal || 
			item == ModItems.FireCrystal || 
			item == ModItems.EmeraldCrystal || 
			item == ModItems.DistortionCrystal ||
			item == Item.getItemFromBlock(ModBlocks.VisceonCore) ||
			item == ModItems.SoldersPellucidus ||
			item == InitItems.RingOfFlight) {
			event.craftMatrix.clear();
		}
	}
}
