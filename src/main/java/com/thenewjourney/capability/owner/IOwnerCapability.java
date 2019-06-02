package com.thenewjourney.capability.owner;

public interface IOwnerCapability {

    void setOwner(String owner);

    void removeOwner();

    String getOwner();
}