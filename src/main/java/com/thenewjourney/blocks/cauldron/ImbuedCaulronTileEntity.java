package com.thenewjourney.blocks.cauldron;

import com.thenewjourney.items.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by cj3636 on 1/19/2017.
 */
public class ImbuedCaulronTileEntity extends TileEntity implements ITickable {
    private ItemStack[] itemStacks = new ItemStack[9];
    private ArrayList<ItemStack> itemStackArray = new ArrayList<ItemStack>(9);
    

	public ImbuedCaulronTileEntity() {
	}

	public ItemStack[] getItemStacks() {
		return itemStacks;
	}

	public void addItemStack(ItemStack itemStack) {
		this.itemStackArray.add(itemStack);
	}

	@Override
	public void update() {
		if (!itemStackArray.isEmpty()) {
			itemStacks = itemStackArray.toArray(itemStacks);
			runRecpie();
		}
	}

    private void runRecpie() {
    	if (itemStacks[4] == null) {
    		return;
    	}
    	Item item1 = itemStacks[0].getItem();
    	Item item2 = itemStacks[1].getItem();
    	Item item3 = itemStacks[2].getItem();
    	Item item4 = itemStacks[3].getItem();
    	Item item5 = itemStacks[4].getItem();
    	if (item1.equals(Items.BLAZE_ROD) &&
    			item2.equals(Items.BLAZE_ROD) &&
    			item3.equals(Items.BLAZE_ROD) &&
    			item4.equals(Items.BLAZE_ROD) &&
    			item5.equals(ModItems.FireCrystal)) {
    		itemStacks = null;
    		IBlockState state = world.getBlockState(pos);
			world.setBlockState(pos, state.withProperty(ImbuedCauldron.LEVEL, 1), 2);
    		
    	}
    	if (item1.equals(Item.getItemFromBlock(Blocks.ICE)) &&
    			item2.equals(Item.getItemFromBlock(Blocks.PACKED_ICE)) &&
    			item3.equals(Item.getItemFromBlock(Blocks.ICE)) &&
    			item4.equals(Item.getItemFromBlock(Blocks.PACKED_ICE)) &&
    			item5.equals(ModItems.AquisCrystal)) {
    		itemStacks = null;
    		IBlockState state = world.getBlockState(pos);
			world.setBlockState(pos, state.withProperty(ImbuedCauldron.LEVEL, 2), 2);
    	}
    	if (item1.equals(Items.EMERALD) &&
    			item2.equals(Items.EMERALD) &&
    			item3.equals(ModItems.EmeraldCrystal) &&
    			item4.equals(Items.EMERALD) &&
    			item5.equals(Items.EMERALD)) {
    		itemStacks = null;
    		IBlockState state = world.getBlockState(pos);
			world.setBlockState(pos, state.withProperty(ImbuedCauldron.LEVEL, 3), 2);
    	}
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
        return compound;
    }

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
    }
}
