package com.thenewjourney.compat.baubles;

import com.cj3636.lib.Config;
import com.thenewjourney.items.bauble.belt.*;
import com.thenewjourney.items.bauble.ring.FlyRing;
import com.thenewjourney.items.bauble.ring.GoldRing;
import com.thenewjourney.items.bauble.ring.RingOfFlight;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Optional;

import java.util.ArrayList;
import java.util.List;

public class InitItems {
    public static final List<Item> Bauble_Item_List = new ArrayList<Item>();

    public static Item AquisBelt;
    public static Item FireBelt;
    public static Item DistortionBelt;
    public static Item EmeraldBelt;
    public static Item ShadowBelt;
    public static Item GoldRing;
    public static Item FlyRing;
    public static Item RingOfFlight;
    public static Item BeltOfTranscendence;

    public static void mainRegistry() {
        register();
    }

    @Optional.Method(modid = "baubles")
    private static void register() {
        AquisBelt = registerItem(new AquisBelt("aquisbelt"));
        DistortionBelt = registerItem(new DistortionBelt("distortionbelt"));
        EmeraldBelt = registerItem(new EmeraldBelt("emeraldbelt"));
        FireBelt = registerItem(new FireBelt("firebelt"));
        ShadowBelt = registerItem(new ShadowBelt("shadowbelt"));
        BeltOfTranscendence = registerItem(new BeltOfTranscendence("beltoftranscendence"));
        GoldRing = registerItem(new GoldRing("goldring", 200));
        if (Config.opTools == true) {
            FlyRing = registerItem(new FlyRing("flyring", 1000));
            RingOfFlight = registerItem(new RingOfFlight("ringofflight", -1));
        }
    }

    private static <T extends Item> T registerItem(T item) {
        Bauble_Item_List.add(item);
        return item;
    }
}