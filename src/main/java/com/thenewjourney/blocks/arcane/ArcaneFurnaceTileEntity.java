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
    private int totalSmeltTime = 5000;
    private int lavaTime;
    private ItemStack[] itemStacks;


    public ArcaneFurnaceTileEntity() {
        itemStacks = new ItemStack[invSize];
        clear();
    }

    public static boolean isItemValidInput(ItemStack stack) {
        FurnaceRecipes recipe = FurnaceRecipes.instance();
        ItemStack result = recipe.getSmeltingResult(stack);
        if (result != ItemStack.EMPTY) {
            return true;
        }
        ArcaneRecipeManager recipe2 = ArcaneRecipeManager.getInstance();
        ItemStack result2 = recipe2.getSmeltingResult(stack);
        return result2 != ItemStack.EMPTY;
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
        if (itemStackInSlot == ItemStack.EMPTY) return ItemStack.EMPTY;

        ItemStack itemStackRemoved;
        if (itemStackInSlot.getCount() <= count) {
            itemStackRemoved = itemStackInSlot;
            setInventorySlotContents(index, ItemStack.EMPTY);
        } else {
            itemStackRemoved = itemStackInSlot.splitStack(count);
            if (itemStackInSlot.getCount() == 0) {
                setInventorySlotContents(index, ItemStack.EMPTY);
            }
        }
        markDirty();
        return itemStackRemoved;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack itemStack = getStackInSlot(index);
        if (itemStack != ItemStack.EMPTY) {
            setInventorySlotContents(index, ItemStack.EMPTY);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        itemStacks[index] = stack;
        if (stack != ItemStack.EMPTY && stack.getCount() > getInventoryStackLimit()) {
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
        if (result != ItemStack.EMPTY) {
            return true;
        }
        ArcaneRecipeManager recipe2 = ArcaneRecipeManager.getInstance();
        ItemStack result2 = recipe2.getSmeltingResult(stack);
        return result2 != ItemStack.EMPTY;
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
        Arrays.fill(itemStacks, ItemStack.EMPTY);
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
        ItemStack input = itemStacks[0];
        if (input.isEmpty()) {
            return false;
        }
        EnumFacing facing = world.getBlockState(pos).getValue(ArcaneFurnaceBlock.FACING);
        return world.getBlockState(pos.offset(facing.getOpposite())).getBlock().equals(Blocks.LAVA) && itemStacks[0] != ItemStack.EMPTY;
    }

    private void doSmelt() {
        ArcaneRecipeManager recipe = ArcaneRecipeManager.getInstance();
        ItemStack input = itemStacks[0];
        ItemStack output = itemStacks[1];
        ItemStack result = recipe.getSmeltingResult(input);
        if (result != ItemStack.EMPTY) {
            if (output.getCount() == 64) {
                markDirty();
                return;
            }
            if (output == ItemStack.EMPTY) {
                input.shrink(1);
                itemStacks[1] = result.copy();
                lavaTime += 1;
                EnumFacing facing = world.getBlockState(pos).getValue(ArcaneFurnaceBlock.FACING);
                world.setBlockState(pos.offset(facing.getOpposite()), Blocks.AIR.getDefaultState());
                world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 3.0F, (float) Math.random(), false);
            } else if (result.getItem().equals(output.getItem())) {
                input.shrink(1);
                itemStacks[1].grow(1);
                lavaTime += 1;
                EnumFacing facing = world.getBlockState(pos).getValue(ArcaneFurnaceBlock.FACING);
                world.setBlockState(pos.offset(facing.getOpposite()), Blocks.AIR.getDefaultState());
                world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 3.0F, (float) Math.random(), false);
            } else {
                markDirty();
                return;
            }
        }
        markDirty();
    }

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

        Arrays.fill(itemStacks, ItemStack.EMPTY);
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
