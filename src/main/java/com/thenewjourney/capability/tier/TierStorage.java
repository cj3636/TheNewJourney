package com.thenewjourney.capability.tier;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class TierStorage implements Capability.IStorage<IPowerTierCapability> {

    @Override
    public NBTBase writeNBT(Capability<IPowerTierCapability> capability, IPowerTierCapability instance, EnumFacing side) {
        return new NBTTagInt(instance.getTier());
    }

    @Override
    public void readNBT(Capability<IPowerTierCapability> capability, IPowerTierCapability instance, EnumFacing side, NBTBase nbt) {
        instance.setTier(((NBTPrimitive) nbt).getInt());
    }
}
