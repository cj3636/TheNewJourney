package com.thenewjourney.capability.block;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class BlockStorage implements Capability.IStorage<IPowerBlockCapability> {

    @Override
    public NBTBase writeNBT(Capability<IPowerBlockCapability> capability, IPowerBlockCapability instance, EnumFacing side) {
        return new NBTTagInt(instance.getBlock());
    }

    @Override
    public void readNBT(Capability<IPowerBlockCapability> capability, IPowerBlockCapability instance, EnumFacing side, NBTBase nbt) {
        instance.setBlock(((NBTPrimitive) nbt).getInt());
    }
}
