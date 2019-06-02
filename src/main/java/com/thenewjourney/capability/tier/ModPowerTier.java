package com.thenewjourney.capability.tier;

public class ModPowerTier implements IPowerTierCapability {
    private int tierNum;

    @Override
    public void addTier(int tier) {
        tierNum += tier;
    }

    @Override
    public void setTier(int tier) {
        tierNum = tier;
        if (tierNum < 0) {
            tierNum = 0;
        }
    }

    @Override
    public void removeTier(int tier) {
        tierNum -= tier;
        if (tierNum < 0) {
            tierNum = 0;
        }
    }

    @Override
    public int getTier() {
        return tierNum;
    }
}
