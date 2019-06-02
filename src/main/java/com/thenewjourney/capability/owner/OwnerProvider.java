package com.thenewjourney.capability.owner;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class OwnerProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IOwnerCapability.class)
    public static final Capability<IOwnerCapability> OWNER_CAP = null;

    private IOwnerCapability instance = OWNER_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == OWNER_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == OWNER_CAP ? OWNER_CAP.<T>cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return OWNER_CAP.getStorage().writeNBT(OWNER_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        OWNER_CAP.getStorage().readNBT(OWNER_CAP, this.instance, null, nbt);
    }
}
