package com.thenewjourney.capability.tier;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class TierPowerProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IPowerTierCapability.class)
    public static final Capability<IPowerTierCapability> TIER_CAP = null;

    private IPowerTierCapability instance = TIER_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == TIER_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == TIER_CAP ? TIER_CAP.<T>cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return TIER_CAP.getStorage().writeNBT(TIER_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        TIER_CAP.getStorage().readNBT(TIER_CAP, this.instance, null, nbt);
    }
}
