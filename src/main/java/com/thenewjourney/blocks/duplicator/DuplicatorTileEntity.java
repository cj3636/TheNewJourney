package com.thenewjourney.blocks.duplicator;

import com.thenewjourney.items.ModItems;
import com.thenewjourney.power.ModPower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.util.Arrays;

public class DuplicatorTileEntity extends TileEntity implements ISidedInventory, ITickable {
    private static final byte GrindTime_id = 0;
    private static final byte FieldCount = 1;
    private static final int[] slotsTop = new int[]{0};
    private static final int[] slotsBottom = new int[]{3};
    public static DuplicatorTileEntity instance = new DuplicatorTileEntity();
    private int invSize = 4;
    private int stackLimit = 1;
    private int grindTime;
    private int totalGrindTime = 3200;
    private ItemStack[] itemStacks = new ItemStack[invSize];

    public double getGrindTime() {
        double fraction = (double) grindTime / (double) totalGrindTime;
        return MathHelper.clamp(fraction, 0.0, 1.0);
    }

    @Override
    public int getSizeInventory() {
        return invSize;
    }

    @Override
    public boolean isEmpty() {
        return !(itemStacks.length > 0);
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return itemStacks[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack itemStackInSlot = getStackInSlot(index);
        if (itemStackInSlot == null)
            return null;

        ItemStack itemStackRemoved;
        if (itemStackInSlot.getCount() <= count) {
            itemStackRemoved = itemStackInSlot;
            setInventorySlotContents(index, null);
        } else {
            itemStackRemoved = itemStackInSlot.splitStack(count);
            if (itemStackInSlot.getCount() == 0) {
                setInventorySlotContents(index, null);
            }
        }
        markDirty();
        return itemStackRemoved;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack itemStack = getStackInSlot(index);
        if (itemStack != null) {
            setInventorySlotContents(index, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (stack == null) {
            itemStacks[index] = stack;
        }
        if (itemStacks[index] != null) {
            if (itemStacks[index].getItem().equals(stack.getItem())) {
                itemStacks[index].setCount(itemStacks[index].getCount() + 1);
            }
        } else {
            itemStacks[index] = stack;
        }
        if (stack != null && stack.getCount() > getInventoryStackLimit()) {
            stack.setCount(getInventoryStackLimit());
        }
        markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return stackLimit;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        if (this.world.getTileEntity(this.pos) != this) {
            return false;
        }

        final double X_CENTRE_OFFSET = 0.5;
        final double Y_CENTRE_OFFSET = 0.5;
        final double Z_CENTRE_OFFSET = 0.5;
        final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;

        return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET,
                pos.getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        if (id == GrindTime_id) {
            return grindTime;
        } else {
            System.err.println("Invalid field ID in TileInventoryDuplicator.setField:" + id);
        }
        return 0;
    }

    @Override
    public void setField(int id, int value) {
        if (id == GrindTime_id) {
            grindTime = value;
        } else {
            System.err.println("Invalid field ID in TileInventoryDuplicator.setField:" + id);
        }
    }

    @Override
    public int getFieldCount() {
        return FieldCount;
    }

    @Override
    public void clear() {
        Arrays.fill(itemStacks, null);
    }

    @Override
    public String getName() {
        return "Visceon Duplicator";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString(this.getName());
    }

    @Override
    public void update() {
        if (canGrind()) {
            addGrindTime();
            if (grindTime >= totalGrindTime) {
                doGrind();
                grindTime = 0;
            }
        } else {
            grindTime = 0;
        }
        if (grindTime < 0)
            grindTime = 0;
    }

    private void addGrindTime() {
        int timeToAdd = ModPower.getPowerNum(world);
        grindTime += timeToAdd;
        markDirty();
        timeToAdd = 0;
    }

    public boolean canGrind() {
        if (itemStacks[0] == null || itemStacks[1] == null || itemStacks[2] == null) {
            return false;
        }
        return ModPower.isFullPoweredAtTier(world, 8);

    }

    private void doGrind() {
        itemStacks[3] = itemStacks[0].copy();
        itemStacks[1] = null;
        itemStacks[2] = null;
        markDirty();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        NBTTagList dataForAllSlots = new NBTTagList();
        for (int i = 0; i < this.itemStacks.length; ++i) {
            if (this.itemStacks[i] != null) {
                NBTTagCompound dataForThisSlot = new NBTTagCompound();
                dataForThisSlot.setByte("Slot", (byte) i);
                this.itemStacks[i].writeToNBT(dataForThisSlot);
                dataForAllSlots.appendTag(dataForThisSlot);
            }
        }
        compound.setTag("Items", dataForAllSlots);
        compound.setLong("GrindTime", grindTime);
        return compound;
    }

    // This is where you load the data that you saved in writeToNBT
    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        final byte NBT_TYPE_COMPOUND = 10;
        NBTTagList dataForAllSlots = nbtTagCompound.getTagList("Items", NBT_TYPE_COMPOUND);

        Arrays.fill(itemStacks, null);
        for (int i = 0; i < dataForAllSlots.tagCount(); ++i) {
            NBTTagCompound dataForOneSlot = dataForAllSlots.getCompoundTagAt(i);
            byte slotNumber = dataForOneSlot.getByte("Slot");
            if (slotNumber >= 0 && slotNumber < this.itemStacks.length) {
                this.itemStacks[slotNumber] = new ItemStack(dataForOneSlot);
            }
        }
        grindTime = nbtTagCompound.getShort("GrindTime");
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        if (side.equals(EnumFacing.UP)) {
            return slotsTop;
        } else if (side.equals(EnumFacing.DOWN)) {
            return slotsBottom;
        } else {
            return new int[]{1, 2};
        }
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        if (index == 0 || direction == EnumFacing.UP) return true;
        if (index == 1) {
            return itemStackIn.getItem().equals(ModItems.CrystalBinder);
        }
        if (index == 2) {
            return itemStackIn.getItem().equals(Item.getItemFromBlock(Blocks.DIAMOND_BLOCK));
        }
        return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        if (index == 3 || direction == EnumFacing.DOWN) return true;
        return false;
    }
}
