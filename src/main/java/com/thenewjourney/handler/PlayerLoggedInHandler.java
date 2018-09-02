package com.thenewjourney.handler;


import com.cj3636.lib.Config;
import com.cj3636.lib.Ref;
import com.thenewjourney.items.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class PlayerLoggedInHandler {
	
	@SubscribeEvent
	public void onPlayerStart(PlayerEvent.PlayerLoggedInEvent event) {
		if (Config.enableBooks) {
			NBTTagCompound nbt = event.player.getEntityData();
			boolean loggedInBefore = nbt.getBoolean(Ref.MODID + "loggedInBefore");
		
			if (!loggedInBefore) {
				event.player.inventory.addItemStackToInventory(new ItemStack(Items.WRITABLE_BOOK));
				event.player.inventory.addItemStackToInventory(new ItemStack(ModItems.Guide));
				nbt.setBoolean(Ref.MODID + "loggedInBefore", true);
			}
		}
	}
	@SubscribeEvent
	public void onPlayerJoin(PlayerEvent.PlayerChangedDimensionEvent event) {
		//FOr fire dim interence
		
	}
}