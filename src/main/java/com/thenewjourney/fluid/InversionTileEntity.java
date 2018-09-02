package com.thenewjourney.fluid;

import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.entity.lightning.ColoredLightningEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

/**
 * Created by cj3636 on 1/19/2017.
 */
public class InversionTileEntity extends TileEntity implements ITickable {
    private static int lifeValue;

    public InversionTileEntity() {
    }

    public InversionTileEntity(int lifeValue) {
        InversionTileEntity.lifeValue = lifeValue;
    }

    public static int getLifeValue() {
        return lifeValue;
    }

    public static void setLifeValue(int lifeValue) {
        InversionTileEntity.lifeValue -= lifeValue;
    }

    @Override
    public void update() {
        if (lifeValue < 1) {
            if (!world.isRemote) {
                world.destroyBlock(pos, false);
                world.setBlockState(pos, ModBlocks.Distributor.getDefaultState(), 3);
            }
            world.spawnEntity(new ColoredLightningEntity(world, pos.getX(), pos.getY(), pos.getZ(), true));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("LifeValue", lifeValue);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        lifeValue = nbtTagCompound.getInteger("LifeValue");
    }
}
