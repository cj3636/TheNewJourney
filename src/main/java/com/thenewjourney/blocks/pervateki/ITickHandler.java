package com.thenewjourney.blocks.pervateki;

import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.EnumSet;

public interface ITickHandler {

    void tick(TickEvent.Type type, Object... context);

    EnumSet<TickEvent.Type> getHandledTypes();

    boolean canFire(TickEvent.Phase phase);

    String getName();

}