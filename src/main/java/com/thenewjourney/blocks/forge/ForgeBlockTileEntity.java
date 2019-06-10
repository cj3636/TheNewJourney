package com.thenewjourney.blocks.forge;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class ForgeBlockTileEntity extends TileEntity {
    private boolean isActive;

    public ForgeBlockTileEntity() {
    }

    public ForgeBlockTileEntity(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setBoolean("isActive", this.isActive);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        this.isActive = nbtTagCompound.getBoolean("isActive");
    }

    public boolean isActive() {
        markDirty();
        return this.isActive;
    }

    public void setActive(boolean isActive) {

        this.isActive = isActive;
        markDirty();
    }
}
