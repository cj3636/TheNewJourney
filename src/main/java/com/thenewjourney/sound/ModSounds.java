package com.thenewjourney.sound;

import com.cj3636.lib.Ref;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModSounds {
    public static SoundEvent HoverExplosion;
    public static SoundEvent ExplosionShift;
    public static SoundEvent PageForward;
    public static SoundEvent PageBack;
    public static SoundEvent BookOpen;
    public static SoundEvent BookClose;
    public static SoundEvent Quazar;
    public static SoundEvent Saronade;
    public static SoundEvent Time;

    public static void mainRegistry() {
        HoverExplosion = registerSound("HoverExplosion");
        ExplosionShift = registerSound("ExplosionShift");
        PageForward = registerSound("PageForward");
        PageBack = registerSound("PageBack");
        BookOpen = registerSound("BookOpen");
        BookClose = registerSound("BookClose");
        Quazar = registerSound("Quazar");
        Saronade = registerSound("Saronade");
        Time = registerSound("Time");
    }

    public static SoundEvent registerSound(String name) {
        ResourceLocation location = new ResourceLocation(Ref.MODID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}
