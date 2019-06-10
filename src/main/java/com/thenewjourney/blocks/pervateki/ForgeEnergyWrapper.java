package com.thenewjourney.blocks.pervateki;

import net.minecraftforge.energy.IEnergyStorage;

class ForgeEnergyWrapper implements IEnergyStorage {

    private ModEnergyStorage storage;

    public ForgeEnergyWrapper(ModEnergyStorage storage) {
        this.storage = storage;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return (int) this.storage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return (int) this.storage.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        return (int) this.storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return (int) this.storage.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return this.storage.canExtract();
    }

    @Override
    public boolean canReceive() {
        return this.storage.canReceive();
    }

    public int getMaxTransfer() {
        return (int) this.storage.getMaxTransfer();
    }

}