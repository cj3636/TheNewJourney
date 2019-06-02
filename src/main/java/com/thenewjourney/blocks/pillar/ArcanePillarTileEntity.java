package com.thenewjourney.blocks.pillar;

import com.thenewjourney.blocks.apiary.ApiaryTileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.util.Arrays;
import java.util.List;

public class ArcanePillarTileEntity extends TickableInventory implements ITickable {
    public static ApiaryTileEntity instance = new ApiaryTileEntity();
    private int invSize = 1;
    private ItemStack[] itemStacks;
    private EntityItem displayEntity;

    public ArcanePillarTileEntity() {
        itemStacks = new ItemStack[invSize];
        clear();
    }

    static boolean isItemValidInput(ItemStack stack) {
        return true;
    }

    @Override
    public int getSizeInventory() {
        return invSize;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index == 0) {
            return itemStacks[index];
        }
        markDirty();
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int count) {
        ItemStack itemStackInSlot = getStackInSlot(slotIndex);
        if (itemStackInSlot.isEmpty()) return ItemStack.EMPTY;  // isEmpt();   EMPTY_ITEM

        ItemStack itemStackRemoved;
        if (itemStackInSlot.getCount() <= count) {  // getStackSize()
            itemStackRemoved = itemStackInSlot;
            setInventorySlotContents(slotIndex, ItemStack.EMPTY);   // EMPTY_ITEM
        } else {
            itemStackRemoved = itemStackInSlot.splitStack(count);
            if (itemStackInSlot.getCount() == 0) { // getStackSize
                setInventorySlotContents(slotIndex, ItemStack.EMPTY);   // EMPTY_ITEM
            }
        }
        markDirty();
        return itemStackRemoved;
    }

    // overwrites the stack in the given slotIndex with the given stack
    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemstack) {
        itemStacks[slotIndex] = itemstack;
        if (itemstack.isEmpty() && itemstack.getCount() > getInventoryStackLimit()) { //  isEmpty(); getStackSize()
            itemstack.setCount(getInventoryStackLimit());  //setStackSize
        }
        markDirty();
    }

    @Override
    public ItemStack removeStackFromSlot(int slotIndex) {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (!itemStack.isEmpty()) setInventorySlotContents(slotIndex, ItemStack.EMPTY);  //isEmpty(), EMPTY_ITEM
        return itemStack;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
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
    public void clear() {
        Arrays.fill(itemStacks, ItemStack.EMPTY);
    }

    @Override
    public String getName() {
        return "Arcane Pillar";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString(this.getName());
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        NBTTagList dataForAllSlots = new NBTTagList();
        for (int i = 0; i < this.itemStacks.length; ++i) {
            if (this.itemStacks[i] != ItemStack.EMPTY) {
                NBTTagCompound dataForThisSlot = new NBTTagCompound();
                dataForThisSlot.setByte("Slot", (byte) i);
                this.itemStacks[i].writeToNBT(dataForThisSlot);
                dataForAllSlots.appendTag(dataForThisSlot);
            }
        }
        compound.setTag("Items", dataForAllSlots);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        final byte NBT_TYPE_COMPOUND = 10;
        NBTTagList dataForAllSlots = nbtTagCompound.getTagList("Items", NBT_TYPE_COMPOUND);
        Arrays.fill(itemStacks, ItemStack.EMPTY);
        for (int i = 0; i < dataForAllSlots.tagCount(); ++i) {
            NBTTagCompound dataForOneSlot = dataForAllSlots.getCompoundTagAt(i);
            byte slotNumber = dataForOneSlot.getByte("Slot");
            if (slotNumber >= 0 && slotNumber < this.itemStacks.length) {
                this.itemStacks[slotNumber] = new ItemStack(dataForOneSlot);
            }
        }
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);
        final int METADATA = 0;
        return new SPacketUpdateTileEntity(this.pos, METADATA, nbtTagCompound);
    }

    @Override
    public void markDirty() {
        if (displayEntity != null) {
            world.removeEntity(displayEntity);
            displayEntity = null;
        }
    }

    @Override
    public void update() {
        if (displayEntity != null) return;
        else if (itemStacks[0] != ItemStack.EMPTY) {
            displayEntity = new EntityItem(world, pos.getX() + .5, pos.getY() + 1.5, pos.getZ() + .5, itemStacks[0].copy());
            displayEntity.setInfinitePickupDelay();
            displayEntity.setNoDespawn();
            displayEntity.motionX = 0;
            displayEntity.motionY = 0;
            displayEntity.motionZ = 0;
            if (!world.isRemote) {
                world.spawnEntity(displayEntity);
            }
        } else {
            if (!world.isRemote) {
                List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(null,
                        new AxisAlignedBB(pos.getX() - 1.0D, pos.getY() - 1.0D, pos.getZ() - 1.0D, pos.getX() + 1.0D, pos.getY() + 2, pos.getZ() + 1.0D));
                for (int i = 0; i < list.size(); ++i) {
                    Entity entity = list.get(i);
                    if (entity instanceof EntityItem) {
                        EntityItem entityItem = (EntityItem) entity;
                        entityItem.setDead();
                    }
                }
            }
        }
    }
}
