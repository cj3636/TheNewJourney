package com.thenewjourney.blocks.purifier;

import com.thenewjourney.power.ModPower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.util.Arrays;

public class PurifierTileEntity extends TileEntity implements ISidedInventory, ITickable {
    public static PurifierTileEntity instance = new PurifierTileEntity();
    private int invSize = 3;
    private int stackLimit = 64;
    private int grindTime;
    private int totalGrindTime = 320;
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

    public static boolean isItemValidInput(ItemStack stack) {
        return true;
    }

    private static final byte GrindTime_id = 0;
    private static final byte FieldCount = 1;

    @Override
    public int getField(int id) {
        if (id == GrindTime_id) {
            return grindTime;
        } else {
            System.err.println("Invalid field ID in TileInventoryPurifier.setField:" + id);
        }
        return 0;
    }

    @Override
    public void setField(int id, int value) {
        if (id == GrindTime_id) {
            grindTime = value;
        } else {
            System.err.println("Invalid field ID in TileInventoryPurifier.setField:" + id);
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
        return "Visceon Purifier";
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
        if (itemStacks[0] == null) {
            return false;
        } else if (FurnaceRecipes.instance().getSmeltingResult(itemStacks[0]) == null) {
            return false;
        }
        if (itemStacks[1] != null && itemStacks[2] != null) {
            if (itemStacks[1].getCount() >= 64 || itemStacks[2].getCount() >= 64) {
                return false;
            }
        }
        return ModPower.isPoweredAtTier(world, 2);

    }

    private void doGrind() {
        FurnaceRecipes recipe = FurnaceRecipes.instance();

        ItemStack slot0 = itemStacks[0];
        ItemStack slot1 = itemStacks[1];
        ItemStack slot2 = itemStacks[2];

        ItemStack result = recipe.getSmeltingResult(slot0);

        if (slot1 != null && slot1.getCount() >= 64) {
            itemStacks[1].setCount(64);
            return;
        }
        if (slot2 != null && slot2.getCount() >= 64) {
            itemStacks[2].setCount(64);
            return;
        }
        if (result != null) {
            if (itemStacks[1] == null && itemStacks[2] == null) {
                itemStacks[1] = result.copy();
                itemStacks[2] = result.copy();
                itemStacks[0].setCount(itemStacks[0].getCount() - 1);
                if (itemStacks[0].getCount() <= 0) {
                    itemStacks[0] = null;
                }
            } else if (itemStacks[1] == null || itemStacks[2] == null) {
                return;
            } else if (slot1.getItem() == result.getItem() && slot2.getItem() == result.getItem()) {
                itemStacks[1].setCount(itemStacks[1].getCount() + 1);
                itemStacks[2].setCount(itemStacks[2].getCount() + 1);
                itemStacks[0].setCount(itemStacks[0].getCount() - 1);
                if (itemStacks[0].getCount() <= 0) {
                    itemStacks[0] = null;
                }
            }
        }
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

    private static final int[] slotsTop = new int[]{0};
    private static final int[] slotsBottom = new int[]{1, 2};
    private static final int[] slotsSides = new int[]{1, 2};

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return true;
    }
}
