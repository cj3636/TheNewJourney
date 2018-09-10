package com.thenewjourney.world;

import com.cj3636.lib.Ref;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class ModWorldSaveData extends WorldSavedData {
    private static int powerNum;
    private static final String key_powerNum = "powerNum";
    private static int powerTier;
    private static final String key_powerTier = "powerTier";
    public static final String key_providerOn = "providerOn";
    public static boolean providerOn = false;

    public ModWorldSaveData(String s) {
        super(s);
    }

    public static ModWorldSaveData forWorld(World world) {
        MapStorage storage = world.getMapStorage();
        ModWorldSaveData instance = (ModWorldSaveData) storage.getOrLoadData(ModWorldSaveData.class, Ref.MODID);
        if (instance == null) {
            instance = new ModWorldSaveData(Ref.MODID);
            storage.setData(Ref.MODID, instance);
        }
        return instance;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        powerNum = nbt.getInteger(key_powerNum);
        powerTier = nbt.getInteger(key_powerTier);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger(key_powerNum, powerNum);
        nbt.setInteger(key_powerTier, powerTier);
        return nbt;
    }

    public int getPowerNum() {
        return powerNum;
    }

    public void setPowerNum(int toSet) {
        powerNum = toSet;
        this.markDirty();
    }

    public int getPowerTier() {
        return powerTier;
    }

    public void setPowerTier(int toSet) {
        if (toSet < 9 && toSet > -1) {
            powerTier = toSet;
        }
        this.markDirty();
    }
}
