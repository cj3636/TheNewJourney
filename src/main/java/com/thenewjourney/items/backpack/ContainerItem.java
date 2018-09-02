package com.thenewjourney.items.backpack;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerItem extends Container {
    /**
     * Using these will make transferStackInSlot easier to understand and implement
     * INV_START is the index of the first slot in the Player's Inventory, so our
     * InventoryItem's number of slots (e.g. 5 slots is array indices 0-4, so start at 5)
     * Notice how we don't have to remember how many slots we made? We can just use
     * InventoryItem.INV_SIZE and if we ever change it, the Container updates automatically.
     */
    /**
     * The Item Inventory for this Container, only needed if you want to reference isUseableByPlayer
     */
    public final InventoryItem inventory;
    private final int HOTBAR_SLOT_COUNT = 9;
    private final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private final int VANILLA_FIRST_SLOT_INDEX = 0;
    private final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private final int TE_INVENTORY_SLOT_COUNT = 81;

    // If you're planning to add armor slots, put those first like this:
    // ARMOR_START = InventoryItem.INV_SIZE, ARMOR_END = ARMOR_START+3,
    // INV_START = ARMOR_END+1, and then carry on like above.

    public ContainerItem(EntityPlayer par1Player, InventoryPlayer inventoryPlayer, InventoryItem inventoryItem) {
        this.inventory = inventoryItem;

        // ITEM INVENTORY - you'll need to adjust the slot locations to match your texture file
        // I have them set vertically in columns of 4 to the right of the player model
        final int HOTBAR_SLOT_COUNT = 9;
        final int PLAYER_INVENTORY_ROW_COUNT = 3;
        final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
        final int SLOT_X_SPACING = 18;
        final int SLOT_Y_SPACING = 18;
        final int TILE_INVENTORY_XPOS = 12;
        int TILE_INVENTORY_YPOS = 8;
        // If you want, you can add ARMOR SLOTS here as well, but you need to
        // make a public version of SlotArmor. I won't be doing that in this tutorial.
		/*
		for (i = 0; i < 4; ++i)
		{
			// These are the standard positions for survival inventory layout
			this.addSlotToContainer(new SlotArmor(this.player, inventoryPlayer, inventoryPlayer.getSizeInventory() - 1 - i, 8, 8 + i * 18, i));
		}
		*/

        // PLAYER INVENTORY - uses default locations for standard inventory texture file
        final int HOTBAR_XPOS = 12;
        final int HOTBAR_YPOS = 232;

        for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
            int slotNumber = x;
            addSlotToContainer(new Slot(inventoryPlayer, slotNumber, HOTBAR_XPOS + SLOT_X_SPACING * x, HOTBAR_YPOS));
        }

        final int PLAYER_INVENTORY_XPOS = 12;
        final int PLAYER_INVENTORY_YPOS = 174;

        for (int y = 0; y < PLAYER_INVENTORY_ROW_COUNT; y++) {
            for (int x = 0; x < PLAYER_INVENTORY_COLUMN_COUNT; x++) {
                int slotNumber = HOTBAR_SLOT_COUNT + y * PLAYER_INVENTORY_COLUMN_COUNT + x;
                int xpos = PLAYER_INVENTORY_XPOS + x * SLOT_X_SPACING;
                int ypos = PLAYER_INVENTORY_YPOS + y * SLOT_Y_SPACING;
                addSlotToContainer(new Slot(inventoryPlayer, slotNumber, xpos, ypos));
            }
        }
        for (int slotNumber = 0; slotNumber < 81; slotNumber++) {
            addSlotToContainer(new Slot(this.inventory, slotNumber, TILE_INVENTORY_XPOS + SLOT_X_SPACING * (slotNumber % 9), TILE_INVENTORY_YPOS + SLOT_Y_SPACING * (slotNumber / 9)));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        // be sure to return the inventory's isUseableByPlayer method
        // if you defined special behavior there:
        return inventory.isUsableByPlayer(entityplayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int sourceSlotIndex) {
        Slot sourceSlot = inventorySlots.get(sourceSlotIndex);
        if (sourceSlot == null || !sourceSlot.getHasStack()) return null;
        ItemStack sourceStack = sourceSlot.getStack();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (sourceSlotIndex >= VANILLA_FIRST_SLOT_INDEX && sourceSlotIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (!mergeItemStack(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT, false)) {
                return null;
            }
        } else if (sourceSlotIndex >= TE_INVENTORY_FIRST_SLOT_INDEX && sourceSlotIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            if (!mergeItemStack(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return null;
            }
        } else {
            System.err.print("Invalid slotIndex:" + sourceSlotIndex);
            return null;
        }
        if (sourceStack.getCount() == 0) {
            sourceSlot.putStack(null);
        } else {
            sourceSlot.onSlotChanged();
        }

        sourceSlot.onTake(player, sourceStack);
        return copyOfSourceStack;
    }

    /**
     * You should override this method to prevent the player from moving the stack that
     * opened the inventory, otherwise if the player moves it, the inventory will not
     * be able to save properly
     */
    @Override
    public ItemStack slotClick(int slot, int dragType, ClickType clickTypeIn, EntityPlayer player) {
        // this will prevent the player from interacting with the item that opened the inventory:
        if (slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItemMainhand()) {
            return null;
        }
        return super.slotClick(slot, dragType, clickTypeIn, player);
    }
}
