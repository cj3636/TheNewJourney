package com.thenewjourney.blocks.arcane;

import com.thenewjourney.world.ModWorldSaveData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.util.Arrays;

public class ArcaneFurnaceTileEntity extends TileEntity implements IInventory, ITickable {
    private static final byte SmeltTime_id = 0;
    private static final byte SlotCount_id = 1;
    private static final byte FieldCount = 2;
    private int invSize = 2;
    private int stackLimit = 64;
    private int smeltTime;
    private int totalSmeltTime = 3000;
    private int lavaTime;
    private ItemStack[] itemStacks = new ItemStack[invSize];

    public static boolean isItemValidInput(ItemStack stack) {
        FurnaceRecipes recipe = FurnaceRecipes.instance();
        ItemStack result = recipe.getSmeltingResult(stack);
        if (result != null) {
            return true;
        }
        ArcaneRecipeManager recipe2 = ArcaneRecipeManager.getInstance();
        ItemStack result2 = recipe2.getSmeltingResult(stack);
        return result2 != null;
    }

    public double getSmeltTime() {
        ModWorldSaveData worldData = ModWorldSaveData.forWorld(world);
        double fraction = (double) (smeltTime * worldData.getPowerNum()) / (double) (totalSmeltTime);
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
        if (index == 1) {
            return false;
        }
        FurnaceRecipes recipe = FurnaceRecipes.instance();
        ItemStack result = recipe.getSmeltingResult(stack);
        if (result != null) {
            return true;
        }
        ArcaneRecipeManager recipe2 = ArcaneRecipeManager.getInstance();
        ItemStack result2 = recipe2.getSmeltingResult(stack);
        return result2 != null;
    }

    @Override
    public int getField(int id) {
        if (id == SmeltTime_id) {
            return smeltTime;
        } else if (id == SlotCount_id) {
            return invSize;
        } else {
            System.err.println("Invalid field ID in TileInventoryArcaneFurnace.setField:" + id);
        }
        return 0;
    }

    @Override
    public void setField(int id, int value) {
        if (id == SmeltTime_id) {
            smeltTime = value;
        } else if (id == SlotCount_id) {

        } else {
            System.err.println("Invalid field ID in TileInventoryArcaneFurnace.setField:" + id);
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
        return "Arcane Furnace";
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
        if (canSmelt()) {
            smeltTime += 1;
            if (getSmeltTime() == 1) {
                doSmelt();
                smeltTime = 0;
            }
        }
        if (lavaTime >= 16) {
            EnumFacing facing = world.getBlockState(pos).getValue(ArcaneFurnaceBlock.FACING);
            world.setBlockState(pos.offset(facing.getOpposite()), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(pos.offset(facing.getOpposite()).up(), Blocks.AIR.getDefaultState());
            world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 10.0F, 1.0F, false);
            lavaTime = 0;
        }
        if (smeltTime < 0) smeltTime = 0;
    }

    private boolean canSmelt() {
        EnumFacing facing = world.getBlockState(pos).getValue(ArcaneFurnaceBlock.FACING);
        return world.getBlockState(pos.offset(facing.getOpposite())).getBlock().equals(Blocks.LAVA) && itemStacks[0] != null;
    }

    private void doSmelt() {
        ArcaneRecipeManager recipe = ArcaneRecipeManager.getInstance();

        ItemStack slot0 = itemStacks[0];
        ItemStack slot1 = itemStacks[1];

        ItemStack result = recipe.getSmeltingResult(slot0);

        if (slot1 != null && slot1.getCount() >= 64) {
            itemStacks[1].setCount(64);
            return;
        }
        if (result != null) {
            if (slot1 == null) {
                itemStacks[1] = result.copy();
                itemStacks[0].setCount(itemStacks[0].getCount() - 1);
                lavaTime++;
                if (itemStacks[0].getCount() <= 0) {
                    itemStacks[0] = null;
                }
                EnumFacing facing = world.getBlockState(pos).getValue(ArcaneFurnaceBlock.FACING);
                world.setBlockState(pos.offset(facing.getOpposite()), Blocks.AIR.getDefaultState());
                world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 3.0F, (float) Math.random(), false);
            } else if (slot1.getItem() == result.getItem()) {
                itemStacks[1].setCount(itemStacks[1].getCount() + 1);
                itemStacks[0].setCount(itemStacks[0].getCount() - 1);
                lavaTime++;
                if (itemStacks[0].getCount() <= 0) {
                    itemStacks[0] = null;
                }
                EnumFacing facing = world.getBlockState(pos).getValue(ArcaneFurnaceBlock.FACING);
                world.setBlockState(pos.offset(facing.getOpposite()), Blocks.AIR.getDefaultState());
                world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 3.0F, (float) Math.random(), false);
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
        compound.setInteger("SmeltTime", smeltTime);
        compound.setInteger("LavaTime", lavaTime);
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
        smeltTime = nbtTagCompound.getInteger("SmeltTime");
        lavaTime = nbtTagCompound.getInteger("LavaTime");
        setField(SmeltTime_id, nbtTagCompound.getInteger("SmeltTime"));
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
