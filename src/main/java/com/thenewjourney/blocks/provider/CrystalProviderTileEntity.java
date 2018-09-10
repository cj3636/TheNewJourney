package com.thenewjourney.blocks.provider;

import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.blocks.idol.IdolTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class CrystalProviderTileEntity extends TileEntity implements ITickable {
    private static boolean isActive;

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
        markDirty();
    }

    @Override
    public void update() {
        EnumFacing facing = world.getBlockState(pos).getValue(CrystalProvider.FACING);
        if (world.getBlockState(pos.offset(facing, 2)).equals(ModBlocks.Idol.getDefaultState())) {
            if (world.getTileEntity(pos.offset(facing, 2)) instanceof IdolTileEntity) {
                IdolTileEntity idolTile = (IdolTileEntity) world.getTileEntity(pos.offset(facing, 2));
                if (idolTile.getFullActive()) {
                    this.setActive(false);
                }
                if (idolTile.getActive()) {
                    this.setActive(true);
                    return;
                } else {
                    this.setActive(false);
                    return;
                }
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setBoolean("powered", this.getActive());
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.setActive(compound.getBoolean("powered"));
    }
}