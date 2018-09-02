package com.thenewjourney.compat.baubles;

import com.cj3636.lib.Config;
import com.thenewjourney.blocks.infuser.InfuserCraftingManager;
import com.thenewjourney.items.ModItems;
import com.thenewjourney.items.bauble.belt.*;
import com.thenewjourney.items.bauble.ring.FlyRing;
import com.thenewjourney.items.bauble.ring.GoldRing;
import com.thenewjourney.items.bauble.ring.RingOfFlight;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
        reg1();
        reg3();
    }

    @Optional.Method(modid = "baubles")
    private static void reg1() {
        AquisBelt = registerItem(new AquisBelt("AquisBelt"));
        DistortionBelt = registerItem(new DistortionBelt("DistortionBelt"));
        EmeraldBelt = registerItem(new EmeraldBelt("EmeraldBelt"));
        FireBelt = registerItem(new FireBelt("FireBelt"));
        ShadowBelt = registerItem(new ShadowBelt("ShadowBelt"));
        BeltOfTranscendence = registerItem(new BeltOfTranscendence("BeltOfTranscendence"));
        GoldRing = registerItem(new GoldRing("GoldRing", 200));
        if (Config.opTools == true) {
            FlyRing = registerItem(new FlyRing("FlyRing", 1000));
            RingOfFlight = registerItem(new RingOfFlight("RingOfFlight", -1));
        }
    }

    private static <T extends Item> T registerItem(T item) {
        Bauble_Item_List.add(item);
        return item;
    }

    @Optional.Method(modid = "baubles")
    public static void reg3() {
/*        GameRegistry.addRecipe(new ItemStack(InitItems.GoldRing), "BLS", "G G", "SLB", 'B', ModItems.BronzeIngot, 'L', ModItems.LeatherStrip, 'G', Items.GOLD_INGOT, 'S', ModItems.SteelIngot);
        GameRegistry.addRecipe(new ItemStack(InitItems.AquisBelt), " C ", "L L", " L ", 'C', ModItems.AquisCrystal, 'L', ModItems.LeatherStrip);
        GameRegistry.addRecipe(new ItemStack(InitItems.ShadowBelt), " C ", "L L", " L ", 'C', ModItems.ShadowCrystal, 'L', ModItems.LeatherStrip);
        GameRegistry.addRecipe(new ItemStack(InitItems.DistortionBelt), " C ", "L L", " L ", 'C', ModItems.DistortionCrystal, 'L', ModItems.LeatherStrip);
        GameRegistry.addRecipe(new ItemStack(InitItems.EmeraldBelt), " C ", "L L", " L ", 'C', ModItems.EmeraldCrystal, 'L', ModItems.LeatherStrip);
        GameRegistry.addRecipe(new ItemStack(InitItems.FireBelt), " C ", "L L", " L ", 'C', ModItems.FireCrystal, 'L', ModItems.LeatherStrip);
        GameRegistry.addRecipe(new ItemStack(InitItems.BeltOfTranscendence), " C ", "L L", " L ", 'C', ModItems.SoldersPellucidus, 'L', ModItems.LeatherStrip);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.AquisCrystal), InitItems.AquisBelt);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ShadowCrystal), InitItems.ShadowBelt);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.DistortionCrystal), InitItems.DistortionBelt);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.EmeraldCrystal), InitItems.EmeraldBelt);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.FireCrystal), InitItems.FireBelt);*/
        //Ring
        InfuserCraftingManager.getInstance().addRecipe(new ItemStack(InitItems.RingOfFlight), "RVR", "FGA", "RSR", 'R', ModItems.RubyGem, 'V', ModItems.VitaemIngot, 'F', ModItems.FloriumIngot, 'G', InitItems.FlyRing, 'A', ModItems.ArchiumIngot, 'S', ModItems.ShadowIngot);
    }
}