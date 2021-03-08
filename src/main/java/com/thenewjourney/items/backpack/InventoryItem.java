package com.thenewjourney.items.backpack;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextComponentString;

import java.util.Arrays;

public class InventoryItem implements IInventory {
    /**
     * Defining your inventory size this way is handy
     */
    public static final int INV_SIZE = 81;
    /**
     * Provides NBT Tag Compound to reference
     */
    private final ItemStack invItem;
    /*
    If you want to be able to place your inventory-holding Item within another
    instance of itself, you'll need to have a way to distinguish between each
    instance of the item so you can check to make sure you're not placing the item
    within itself. What you'll need to do is assign a UUID to a String variable
    within your Inventory class for every ItemStack that holds an instance of your
    Item, like so:
     */
// declaration of variable:
    protected String uniqueID;
    private String name = "Inventory Item";
    /**
     * Inventory's size must be same as number of slots you add to the Container class
     */
    private ItemStack[] inventory;

    /**
     * @param stack - the ItemStack to which this inventory belongs
     */
    public InventoryItem(ItemStack stack) {
        invItem = stack;
        inventory = new ItemStack[INV_SIZE];
        // Create a new NBT Tag Compound if one doesn't already exist, or you will crash
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        // note that it's okay to use stack instead of invItem right there
        // both reference the same memory location, so whatever you change using
        // either reference will change in the other

        // Read the inventory contents from NBT
        readFromNBT(stack.getTagCompound());
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void openInventory(EntityPlayer player) {
        if (player.world.isRemote) {
            player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F, 1.3f);
        }
    }

    @Override
    public void closeInventory(EntityPlayer player) {
        if (player.world.isRemote) {
            player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0F, 1.3f);
        }
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public boolean isEmpty() {
        return !(inventory.length > 0);
    }

    @Override
    public void clear() {
        Arrays.fill(inventory, ItemStack.EMPTY);
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.getCount() > amount) {
                stack = stack.splitStack(amount);
                // Don't forget this line or your inventory will not be saved!
                markDirty();
            } else {
                // this method also calls onInventoryChanged, so we don't need to call it again
                setInventorySlotContents(slot, ItemStack.EMPTY);
            }
        }
        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int slotIndex) {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null) setInventorySlotContents(slotIndex, ItemStack.EMPTY);
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventory[slot] = stack;

        if (stack != ItemStack.EMPTY && stack.getCount() > getInventoryStackLimit()) {
            stack.setCount(getInventoryStackLimit());
        }

        // Don't forget this line or your inventory will not be saved!
        markDirty();
    }

    // 1.7.2+ renamed to getInventoryName
    @Override
    public String getName() {
        return "container.backpack.name";
    }

    @Override
    public TextComponentString getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentString(this.getName());
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    /**
     * This is the method that will handle saving the inventory contents, as it is called (or should be called!)
     * anytime the inventory changes. Perfect. Much better than using onUpdate in an Item, as this will also
     * let you change things in your inventory without ever opening a Gui, if you want.
     */
    // 1.7.2+ renamed to markDirty
    @Override
    public void markDirty() {
        for (int i = 0; i < getSizeInventory(); ++i) {
            if (getStackInSlot(i) != null && getStackInSlot(i).getCount() == 0) {
                inventory[i] = null;
            }
        }

        // This line here does the work:
        writeToNBT(invItem.getTagCompound());
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityplayer) {
        return true;
    }

    /**
     * This method doesn't seem to do what it claims to do, as
     * items can still be left-clicked and placed in the inventory
     * even when this returns false
     */
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
        // Don't want to be able to store the inventory item within itself
        // Bad things will happen, like losing your inventory
        // Actually, this needs a custom Slot to work
        return !(itemstack.getItem() instanceof ItemStore);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound parentNBTTagCompound) {

        NBTTagList dataForAllSlots = new NBTTagList();
        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] != null) {
                NBTTagCompound dataForThisSlot = new NBTTagCompound();
                dataForThisSlot.setByte("Slot", (byte) i);
                this.inventory[i].writeToNBT(dataForThisSlot);
                dataForAllSlots.appendTag(dataForThisSlot);
            }
        }
        parentNBTTagCompound.setTag("Items", dataForAllSlots);
        return parentNBTTagCompound;
    }

    public void readFromNBT(NBTTagCompound parentNBTTagCompound) {
        final byte NBT_TYPE_COMPOUND = 10;
        NBTTagList dataForAllSlots = parentNBTTagCompound.getTagList("Items", NBT_TYPE_COMPOUND);

        Arrays.fill(inventory, ItemStack.EMPTY);
        for (int i = 0; i < dataForAllSlots.tagCount(); ++i) {
            NBTTagCompound dataForOneSlot = dataForAllSlots.getCompoundTagAt(i);
            int slotIndex = dataForOneSlot.getByte("Slot") & 255;

            if (slotIndex >= 0 && slotIndex < this.inventory.length) {
                this.inventory[slotIndex] = new ItemStack(dataForOneSlot);
            }
        }
    }
}