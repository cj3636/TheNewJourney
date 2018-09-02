package com.thenewjourney.blocks.apiary;

import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.util.Arrays;

public class ApiaryTileEntity extends TileEntity implements IInventory, ITickable {
    public static ApiaryTileEntity instance = new ApiaryTileEntity();
    private int invSize = 2;
    private int stackLimit = 64;
    private int beeTime;
    private int totalBeeTime = 100000;
    private ItemStack[] itemStacks = new ItemStack[invSize];

    public double getBeeTime() {
        double fraction = (double) beeTime / (double) totalBeeTime;
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
        if (itemStackInSlot == null) return null;

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
        itemStacks[index] = stack;
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

        return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET, pos.getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        FurnaceRecipes recipe = FurnaceRecipes.instance();
        ItemStack result = recipe.getSmeltingResult(stack);
        return result != null;
    }

    public static boolean isItemValidInput(ItemStack stack) {
        FurnaceRecipes recipe = FurnaceRecipes.instance();
        ItemStack result = recipe.getSmeltingResult(stack);
        return result != null;
    }

    private static final byte BeeTime_id = 0;
    private static final byte SlotCount_id = 1;
    private static final byte FieldCount = 2;

    @Override
    public int getField(int id) {
        if (id == BeeTime_id) {
            return beeTime;
        } else if (id == SlotCount_id) {
            return invSize;
        } else {
            System.err.println("Invalid field ID in TileInventoryApiary.setField:" + id);
        }
        return 0;
    }

    @Override
    public void setField(int id, int value) {
        if (id == BeeTime_id) {
            beeTime = value;
        } else if (id == SlotCount_id) {

        } else {
            System.err.println("Invalid field ID in TileInventoryApiary.setField:" + id);
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
        return "Apiary";
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
        if (canGrow() && getStackInSlot(0) != null && getStackInSlot(0).getItem().equals(ModItems.Bee)) {
            addBeeTime();
            if (beeTime >= totalBeeTime) {
                createJelly();
                beeTime = 0;
            }
        }
        if (beeTime < 0) beeTime = 0;
    }

    private void addBeeTime() {
        int timeToAdd = 0;
        BlockPos[] posList = {
                pos.north(),
                pos.south(),
                pos.east(),
                pos.west(),
                pos.north().east(),
                pos.north().west(),
                pos.south().east(),
                pos.south().west()
        };
        for (BlockPos posAt : posList) {
            Block stateIn = world.getBlockState(posAt).getBlock();
            if (stateIn.equals(Blocks.RED_FLOWER) ||
                    stateIn.equals(Blocks.YELLOW_FLOWER) ||
                    stateIn.equals(Blocks.SAPLING)) {
                timeToAdd++;
            }
        }
        beeTime += timeToAdd;
        markDirty();
        timeToAdd = 0;
    }

    private boolean canGrow() {
        BlockPos[] posList = {
                pos.north(),
                pos.south(),
                pos.east(),
                pos.west(),
                pos.north().east(),
                pos.north().west(),
                pos.south().east(),
                pos.south().west()
        };
        for (BlockPos posAt : posList) {
            Block stateIn = world.getBlockState(posAt).getBlock();
            if (stateIn.equals(Blocks.RED_FLOWER) ||
                    stateIn.equals(Blocks.YELLOW_FLOWER) ||
                    stateIn.equals(Blocks.SAPLING)) {
                return true;
            }
        }
        return false;
    }

    private void createJelly() {
        double rand = Math.random();
        if (rand < 0.3D && !world.getBlockState(pos.up()).getBlock().equals(ModBlocks.Crystal)) {
            itemStacks[0] = null;
        }
        if (itemStacks[1] != null) {
            itemStacks[1].setCount(itemStacks[1].getCount() + 1);
        } else {
            itemStacks[1] = new ItemStack(ModItems.RoyalJelly);
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
        compound.setInteger("BeeTime", beeTime);
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
        beeTime = nbtTagCompound.getInteger("BeeTime");
        setField(BeeTime_id, nbtTagCompound.getInteger("BeeTime"));
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);
        final int METADATA = 0;
        return new SPacketUpdateTileEntity(this.pos, METADATA, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

}