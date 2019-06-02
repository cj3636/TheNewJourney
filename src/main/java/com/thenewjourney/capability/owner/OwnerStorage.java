package com.thenewjourney.capability.owner;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class OwnerStorage implements Capability.IStorage<IOwnerCapability> {

    @Override
    public NBTBase writeNBT(Capability<IOwnerCapability> capability, IOwnerCapability instance, EnumFacing side) {
        return new NBTTagString(instance.getOwner());
    }

    @Override
    public void readNBT(Capability<IOwnerCapability> capability, IOwnerCapability instance, EnumFacing side, NBTBase nbt) {
        instance.setOwner(((NBTTagString) nbt).getString());
    }
}