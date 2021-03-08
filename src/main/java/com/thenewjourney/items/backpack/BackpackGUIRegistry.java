package com.thenewjourney.items.backpack;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class BackpackGUIRegistry implements IGuiHandler {
    private static final int GUI_ID = 38;

    public static int getGuiID() {
        return GUI_ID;
    }
    @Override
    public Object getServerGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z) {
        // Hooray, no 'magic' numbers - we know exactly which Gui this refers to
        if (guiId == getGuiID()) {
            // Use the player's held item to create the inventory
            return new ContainerItem(player, player.inventory, new InventoryItem(player.getHeldItemMainhand()));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z) {
        if (guiId == getGuiID()) {
            // We have to cast the new container as our custom class
            // and pass in currently held item for the inventory
            return new GuiItemInventory(player, player.inventory, new InventoryItem(player.getHeldItemMainhand()));
        }
        return null;
    }
}