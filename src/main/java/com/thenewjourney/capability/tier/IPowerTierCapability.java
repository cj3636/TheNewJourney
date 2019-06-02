package com.thenewjourney.capability.tier;

public interface IPowerTierCapability {

    void addTier(int tier);

    void setTier(int tier);

    void removeTier(int tier);

    int getTier();
}
