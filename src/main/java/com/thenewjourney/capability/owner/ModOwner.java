package com.thenewjourney.capability.owner;

public class ModOwner implements IOwnerCapability {
    private String blockOwner;

    @Override
    public void setOwner(String owner) {
        blockOwner = owner;
    }

    @Override
    public void removeOwner() {
        blockOwner = null;
    }

    @Override
    public String getOwner() {
        return blockOwner;
    }
}
