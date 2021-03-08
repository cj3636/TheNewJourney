package com.thenewjourney.blocks.pervateki;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;

public class PowerAdapterTileEntity extends TileEntity implements ICapabilityProvider {

    ModEnergyStorage energyStorage;
    private ForgeEnergyWrapper forgeWrapper;

    public PowerAdapterTileEntity() {
        this(1000000000, 1000000, 1000000);

    }

    PowerAdapterTileEntity(int capacity, int maxReceive, int maxExtract) {
        this.energyStorage = new ModEnergyStorage(capacity, maxReceive, maxExtract, 1000);
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        energyStorage.writeToNBT(nbt);
        return super.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        energyStorage.readFromNBT(nbt);
        super.readFromNBT(nbt);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY)
            return true;
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            if (this.forgeWrapper == null)
                this.forgeWrapper = new ForgeEnergyWrapper(this.energyStorage);
            return (T) this.forgeWrapper;
        }
        return super.getCapability(capability, facing);
    }
}
