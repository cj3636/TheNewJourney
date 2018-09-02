package com.thenewjourney.blocks.duplicator;

import com.thenewjourney.items.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DuplicatorContainer extends Container {

    public final int INPUT_SLOTS_COUNT = 3;
    public final int OUTPUT_SLOTS_COUNT = 1;
    public final int FURNACE_SLOTS_COUNT = INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT;
    private final int HOTBAR_SLOT_COUNT = 9;
    private final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    // slot index is the unique index for all slots in this container
    private final int VANILLA_FIRST_SLOT_INDEX = 0;
    private final int FIRST_INPUT_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private final int FIRST_OUTPUT_SLOT_INDEX = FIRST_INPUT_SLOT_INDEX + 1;
    // slot number is the slot number within each component
    private final int FIRST_INPUT_SLOT_NUMBER = 0;
    private final int FIRST_OUTPUT_SLOT_NUMBER = 4;
    private DuplicatorTileEntity tileInventoryPurifier;
    // These store cache values, used by the server to only update the client side tile entity when values have changed
    private int[] cachedFields;
    private int grindTime;

    public DuplicatorContainer(InventoryPlayer invPlayer, DuplicatorTileEntity tileInventoryPurifier) {
        this.tileInventoryPurifier = tileInventoryPurifier;

        final int SLOT_X_SPACING = 18;
        final int SLOT_Y_SPACING = 18;
        final int HOTBAR_XPOS = 8;
        final int HOTBAR_YPOS = 183;
        // Add the players hotbar to the gui - the [xpos, ypos] location of each item
        for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
            int slotNumber = x;
            addSlotToContainer(new Slot(invPlayer, slotNumber, HOTBAR_XPOS + SLOT_X_SPACING * x, HOTBAR_YPOS));
        }

        final int PLAYER_INVENTORY_XPOS = 8;
        final int PLAYER_INVENTORY_YPOS = 125;
        // Add the rest of the players inventory to the gui
        for (int y = 0; y < PLAYER_INVENTORY_ROW_COUNT; y++) {
            for (int x = 0; x < PLAYER_INVENTORY_COLUMN_COUNT; x++) {
                int slotNumber = HOTBAR_SLOT_COUNT + y * PLAYER_INVENTORY_COLUMN_COUNT + x;
                int xpos = PLAYER_INVENTORY_XPOS + x * SLOT_X_SPACING;
                int ypos = PLAYER_INVENTORY_YPOS + y * SLOT_Y_SPACING;
                addSlotToContainer(new Slot(invPlayer, slotNumber, xpos, ypos));
            }
        }

        final int FUEL_SLOTS_XPOS = 62;
        final int FUEL_SLOTS_YPOS = 96;
        addSlotToContainer(new SlotInput(tileInventoryPurifier, 0, 27, 60));
        addSlotToContainer(new SlotInput3(tileInventoryPurifier, 1, 80, 78));
        addSlotToContainer(new SlotInput2(tileInventoryPurifier, 2, 80, 95));
        addSlotToContainer(new SlotOutput(tileInventoryPurifier, 3, 133, 61));
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int sourceSlotIndex) {
        return null;
    }

    // Checks each tick to make sure the player is still able to access the inventory and if not closes the gui
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileInventoryPurifier.isUsableByPlayer(player);
    }
    /* Client Synchronization */

    // This is where you check if any values have changed and if so send an update to any clients accessing this container
    // The container itemstacks are tested in Container.detectAndSendChanges, so we don't need to do that
    // We iterate through all of the TileEntity Fields to find any which have changed, and send them.
    // You don't have to use fields if you don't wish to; just manually match the ID in sendProgressBarUpdate with the value in
    //   updateProgressBar()
    // The progress bar values are restricted to shorts.  If you have a larger value (eg int), it's not a good idea to try and split it
    //   up into two shorts because the progress bar values are sent independently, and unless you add synchronisation logic at the
    //   receiving side, your int value will be wrong until the second short arrives.  Use a custom packet instead.
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.listeners.size(); ++i) {
            IContainerListener icontainerlistener = this.listeners.get(i);
            if (this.grindTime != this.tileInventoryPurifier.getField(0)) {
                icontainerlistener.sendWindowProperty(this, 0, this.tileInventoryPurifier.getField(0));
                icontainerlistener.sendSlotContents(this, 36, null);
                icontainerlistener.sendSlotContents(this, 37, null);
                icontainerlistener.sendSlotContents(this, 38, null);
                icontainerlistener.sendSlotContents(this, 39, null);
                icontainerlistener.sendSlotContents(this, 36, tileInventoryPurifier.getStackInSlot(0));
                icontainerlistener.sendSlotContents(this, 37, tileInventoryPurifier.getStackInSlot(1));
                icontainerlistener.sendSlotContents(this, 38, tileInventoryPurifier.getStackInSlot(2));
                icontainerlistener.sendSlotContents(this, 39, tileInventoryPurifier.getStackInSlot(3));
            }
        }
        this.grindTime = this.tileInventoryPurifier.getField(0);
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, tileInventoryPurifier);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        this.tileInventoryPurifier.setField(id, data);
    }

    public class SlotInput extends Slot {
        public SlotInput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }
    }

    public class SlotInput2 extends Slot {
        public SlotInput2(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack) {
            if (stack.getItem().equals(Item.getItemFromBlock(Blocks.DIAMOND_BLOCK))) {
                return true;
            }
            return false;
        }
    }

    public class SlotInput3 extends Slot {
        public SlotInput3(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack) {
            if (stack.getItem().equals(ModItems.CrystalBinder)) {
                return true;
            }
            return false;
        }
    }

    public class SlotOutput extends Slot {
        public SlotOutput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack) {
            return false;
        }
    }
}
