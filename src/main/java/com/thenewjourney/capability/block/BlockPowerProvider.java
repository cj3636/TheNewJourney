package com.thenewjourney.capability.block;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class BlockPowerProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IPowerBlockCapability.class)
    public static final Capability<IPowerBlockCapability> BLOCK_CAP = null;

    private IPowerBlockCapability instance = BLOCK_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == BLOCK_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == BLOCK_CAP ? BLOCK_CAP.<T>cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return BLOCK_CAP.getStorage().writeNBT(BLOCK_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        BLOCK_CAP.getStorage().readNBT(BLOCK_CAP, this.instance, null, nbt);
    }
}
