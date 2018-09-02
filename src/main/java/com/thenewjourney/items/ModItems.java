package com.thenewjourney.items;

import com.cj3636.lib.Config;
import com.cj3636.lib.Ref;
import com.thenewjourney.armor.AquisArmor;
import com.thenewjourney.armor.ArmorRegistry;
import com.thenewjourney.armor.FireArmor;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.blocks.infuser.InfuserItem;
import com.thenewjourney.guide.GuideItem;
import com.thenewjourney.items.backpack.ItemStore;
import com.thenewjourney.items.food.JellyItem;
import com.thenewjourney.items.food.NarcoBerry;
import com.thenewjourney.items.food.NarcoBerrySeed;
import com.thenewjourney.items.register.*;
import com.thenewjourney.items.staff.ShadowStaff;
import com.thenewjourney.items.tool.*;
import com.thenewjourney.items.tool.purvia.*;
import com.thenewjourney.items.tranquilizer.ItemTranquilizerArrow;
import com.thenewjourney.items.wand.DistortionWand;
import com.thenewjourney.items.weapon.*;
import com.thenewjourney.sound.ModSounds;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public final class ModItems extends Item {
    public static final List<Item> Item_List = new ArrayList<Item>();

    public static Item Guide;
    //Tools: harvest, durability, speed, damage, enchant
    public static ToolMaterial Copper = EnumHelper.addToolMaterial("copper", 2, 150, 6.0F, 3F, 3);
    public static ToolMaterial Bronze = EnumHelper.addToolMaterial("bronze", 3, 200, 7.0F, 3F, 3);
    public static ToolMaterial Ruby = EnumHelper.addToolMaterial("ruby", 4, 300, 10.0F, 3F, 10);
    public static ToolMaterial Cobalt = EnumHelper.addToolMaterial("cobalt", 5, 2100, 18.0F, 4.5F, 27);
    public static ToolMaterial Distortion = EnumHelper.addToolMaterial("distortion", 6, 2000, 17.0F, 6F, 20);
    public static ToolMaterial Fire = EnumHelper.addToolMaterial("fire", 7, 3200, 24.0F, 7F, 30);
    public static ToolMaterial Shadow = EnumHelper.addToolMaterial("shadow", 8, 3800, 27.0F, 8F, 50);
    public static ToolMaterial Aquis = EnumHelper.addToolMaterial("aquis", 9, 5000, 36.0F, 10F, 100);
    public static ToolMaterial Purvia = EnumHelper.addToolMaterial("purvia", 10, -1, 45.0F, 12F, 100);

    public static ToolMaterial Archium = EnumHelper.addToolMaterial("archium", 9, -1, 72, 1, 72);
    public static ToolMaterial Florium = EnumHelper.addToolMaterial("florium", 9, -1, 72, 1, 72);
    public static ToolMaterial Vitaem = EnumHelper.addToolMaterial("vitaem", 9, -1, 72, 1, 72);

    public static ToolMaterial LowGradeSteel = EnumHelper.addToolMaterial("lowgradesteel", 2, 3200, 10.0F, 5F, 18);
    public static ToolMaterial Bedrock = EnumHelper.addToolMaterial("bedrock", 0, 100, 0F, 0F, 0);
    public static ToolMaterial GlassTool = EnumHelper.addToolMaterial("glassbreak", 1, 1000, 50.0F, 6F, 17);
    public static ToolMaterial ClawTool = EnumHelper.addToolMaterial("claw", 0, 64, 50.0F, 0F, 0);

    public static ArmorMaterial CopperArmor = EnumHelper.addArmorMaterial("copper", Ref.MODID + ":copper", 18, new int[]{2, 3, 3, 1}, 3, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F);
    public static ArmorMaterial BronzeArmor = EnumHelper.addArmorMaterial("bronze", Ref.MODID + ":bronze", 22, new int[]{3, 4, 4, 2}, 3, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F);
    public static ArmorMaterial RubyArmor = EnumHelper.addArmorMaterial("ruby", Ref.MODID + ":ruby", 27, new int[]{6, 6, 6, 5}, 30, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F);
    public static ArmorMaterial CobaltArmor = EnumHelper.addArmorMaterial("cobalt", Ref.MODID + ":cobalt", 44, new int[]{5, 5, 5, 5}, 27, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.1F);
    public static ArmorMaterial DistortionArmor = EnumHelper.addArmorMaterial("distortion", Ref.MODID + ":distortion", 49, new int[]{5, 7, 7, 3}, 12, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F);
    public static ArmorMaterial FireArmor = EnumHelper.addArmorMaterial("fire", Ref.MODID + ":fire", 50, new int[]{7, 7, 7, 6}, 30, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2.0F);
    public static ArmorMaterial ShadowArmor = EnumHelper.addArmorMaterial("shadow", Ref.MODID + ":shadow", 54, new int[]{7, 7, 7, 7}, 30, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2.0F);
    public static ArmorMaterial AquisArmor = EnumHelper.addArmorMaterial("aquis", Ref.MODID + ":aquis", 60, new int[]{7, 8, 8, 7}, 32, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4.0F);
    //Ruby
    public static Item RubyGem;

    public static Item RubyPickaxe;
    public static Item RubyAxe;
    public static Item RubyShovel;
    public static Item RubyHoe;
    public static Item RubySword;

    public static Item RubyHelmet;
    public static Item RubyChestplate;
    public static Item RubyLeggings;
    public static Item RubyBoots;
    //Bronze
    //Bronze
    public static Item CopperIngot;
    public static Item TinIngot;
    public static Item BronzeIngot;

    public static Item CopperPickaxe;
    public static Item CopperAxe;
    public static Item CopperShovel;
    public static Item CopperHoe;
    public static Item CopperSword;

    public static Item BronzePickaxe;
    public static Item BronzeAxe;
    public static Item BronzeShovel;
    public static Item BronzeHoe;
    public static Item BronzeSword;

    public static Item CopperHelmet;
    public static Item CopperChestplate;
    public static Item CopperLeggings;
    public static Item CopperBoots;

    public static Item BronzeHelmet;
    public static Item BronzeChestplate;
    public static Item BronzeLeggings;
    public static Item BronzeBoots;
    //Cobalt
    public static Item CobaltIngot;
    public static Item DimensionChanger;

    public static Item CobaltHelmet;
    public static Item CobaltChestplate;
    public static Item CobaltLeggings;
    public static Item CobaltBoots;

    public static Item CobaltPickaxe;
    public static Item CobaltAxe;
    public static Item CobaltShovel;
    public static Item CobaltHoe;
    public static Item CobaltSword;
    //Distorted

    public static Item DistortionHelmet;
    public static Item DistortionChestplate;
    public static Item DistortionLeggings;
    public static Item DistortionBoots;
    public static Item DistortionGem;

    public static Item DistortionPickaxe;
    public static Item DistortionAxe;
    public static Item DistortionShovel;
    public static Item DistortionHoe;
    public static Item DistortionSword;
    //Fire
    public static Item FireIngot;

    public static Item FireHelmet;
    public static Item FireChestplate;
    public static Item FireLeggings;
    public static Item FireBoots;

    public static Item FirePickaxe;
    public static Item FireAxe;
    public static Item FireShovel;
    public static Item FireHoe;
    public static Item FireSword;
    //Shadow
    public static Item ShadowIngot;
    public static Item ShadowEssence;

    public static Item ShadowPickaxe;
    public static Item ShadowAxe;
    public static Item ShadowShovel;
    public static Item ShadowHoe;
    public static Item ShadowSword;

    public static Item ShadowHelmet;
    public static Item ShadowChestplate;
    public static Item ShadowLeggings;
    public static Item ShadowBoots;

    public static Item ShadowHiltParts;
    public static Item ShadowHilt;

    //Aquis
    public static Item AquisIngot;

    public static Item AquisHelmet;
    public static Item AquisChestplate;
    public static Item AquisLeggings;
    public static Item AquisBoots;

    public static Item AquisPickaxe;
    public static Item AquisAxe;
    public static Item AquisShovel;
    public static Item AquisHoe;
    public static Item AquisSword;
    //Ancient
    public static Item CrypticChunk;
    public static Item AncientIngot;
    //Trio
    public static Item ArchaicOrb;
    public static Item ArchiumIngot;
    public static Item AquisStaff;
    public static Item BabylonWand;
    public static Item ZirconiumStaff;
    public static Item SwordOfTheAlti;

    public static Item FloricOrb;
    public static Item FloriumIngot;
    public static Item FlorucBattleAxe;
    public static Item FlorucSword;
    public static Item FloriumAxe;
    public static Item SwordOfFlorus;

    public static Item QuazanScale;
    public static Item VitaemIngot;
    public static Item GreenBlade;
    public static Item VitriusStar;
    public static Item VitraecBow;
    public static Item VitraemSaber;

    //Misc
    public static Item PheonixDust;
    public static Item TranquilizerArrow;

    public static Item FireDust;
    public static Item FireGem;
    public static Item BurntRedstone;
    public static Item ClayMixture;
    public static Item FireLight;
    public static Item AtriumLight;
    public static Item BloodGem;
    public static Item InversionSerumBottle;
    //Plant
    public static Item FireSeed;
    public static Item SpatiumPowder;
    //Machined Items
    public static Item IronDisc;
    public static Item LowGradeSteelIngot;
    public static Item FireBrick;
    public static Item Carbon;
    public static Item SteelIngot;
    public static Item SteelRod;
    public static Item LowGradeSteelRod;


    public static Item KingsHammer;
    public static Item BedrockHammer;
    public static Item BedrockSword;
    public static Item Hammer;

    public static Item Icepick;
    public static Item IronOrePick;

    public static Item Claw;
    //Bee
    public static Item Bee;
    public static Item RoyalJelly;
    public static Item NarcoBerry;
    public static Item NarcoBerrySeed;
    public static Item Narcotic;
    //Staves
    public static Item CobaltRod;
    public static Item AquisOrb;
    public static Item ShadowOrb;
    public static Item DistortionOrb;
    public static Item EmeraldShard;
    public static Item OrbMesh;
    public static Item ShadowStaff;
    public static Item EtherealClock;
    //Wands
    public static Item DistortionWand;
    //Baubles
    public static Item CrystalBinder;
    public static Item ShadowBoundCrystal;
    public static Item AquisBoundCrystal;
    public static Item FireBoundCrystal;
    public static Item DistortionBoundCrystal;
    public static Item EmeraldBoundCrystal;
    public static Item SoldersPellucidus;

    public static Item ShadowCrystal;
    public static Item AquisCrystal;
    public static Item FireCrystal;
    public static Item DistortionCrystal;
    public static Item EmeraldCrystal;

    public static Item LeatherStrip;

    public static Item BindingPowder;

    public static Item ArchaicInfuserI;
    //Artifacts
    public static Item ArcaneArtifact;

    public static Item UpgradeTwo;
    public static Item UpgradeThree;
    public static Item UpgradeFour;
    public static Item UpgradeFive;
    public static Item UpgradeSix;
    public static Item UpgradeSeven;
    public static Item UpgradeEight;

    public static Item Goblet;
    public static Item GobletOfFire;
    public static Item GobletOfIce;
    public static Item GobletOfEmerald;

    public static Item Quazar;
    public static Item Saronade;
    public static Item Time;
    public static Item Backpack;
    public static Item DiviningRod;
    public static Item Pestal;

    public static Item PurviaStick;
    public static Item PurviaPickaxe;
    public static Item PurviaShovel;
    public static Item PurviaAxe;
    public static Item PurviaHoe;
    public static Item PurviaSword;

    static {
        Guide = registerItem(new GuideItem("guide"));
        AquisIngot = registerItem(new ItemRegistry("aquisingot"));
        CopperIngot = registerItem(new ItemRegistry("copperingot"));
        TinIngot = registerItem(new ItemRegistry("tiningot"));
        BronzeIngot = registerItem(new ItemRegistry("bronzeingot"));
        ArchiumIngot = registerItem(new ItemRegistry("archiumingot"));
        FloriumIngot = registerItem(new ItemRegistry("floriumingot"));
        VitaemIngot = registerItem(new ItemRegistry("vitaemingot"));
        CobaltIngot = registerItem(new ItemRegistry("cobaltingot"));
        DimensionChanger = registerItem(new DimensionChangerRegistry("dimensionchanger", Cobalt, -1));
        DistortionGem = registerItem(new ItemRegistry("distortiongem"));
        FireIngot = registerItem(new ItemRegistry("fireingot"));
        RubyGem = registerItem(new ItemRegistry("ruby"));
        ShadowIngot = registerItem(new ItemRegistry("shadowingot"));
        CrypticChunk = registerItem(new ItemRegistry("crypticchunk"));
        AncientIngot = registerItem(new GrenadeRegistry("ancientingot"));
        ShadowEssence = registerItem(new GrenadeRegistry("shadowessence"));

        FireDust = registerItem(new FireStarterRegistry("firedust"));
        Pestal = registerItem(new Pestal("pestal"));
        PheonixDust = registerItem(new FireStarterRegistry("pheonixdust"));
        TranquilizerArrow = registerItem(new ItemTranquilizerArrow("tranquilizerarrow"));
        FireLight = registerItem(new FireStarterRegistry("firelight"));
        AtriumLight = registerItem(new FireStarterRegistry("atriumlight"));
        FireGem = registerItem(new ItemRegistry("firegem"));
        BloodGem = registerItem(new ItemRegistry("bloodgem"));
        FloricOrb = registerItem(new ItemRegistry("floricorb"));
        ArchaicOrb = registerItem(new ItemRegistry("archaicorb"));
        QuazanScale = registerItem(new ItemRegistry("quazanscale"));
        BurntRedstone = registerItem(new ItemRegistry("burntredstone"));
        ClayMixture = registerItem(new ItemRegistry("claymixture"));

        FireSeed = registerItem(new FireSeedsRegistry(ModBlocks.FireFern, Blocks.FARMLAND, "fireseed"));
        SpatiumPowder = registerItem(new FertilizerRegistry("spatiumpowder"));
        InversionSerumBottle = registerItem(new BottleRegistry("inversionserumbottle"));

        LowGradeSteelIngot = registerItem(new ItemRegistry("lowgradesteelingot"));
        SteelIngot = registerItem(new ItemRegistry("steelingot"));
        FireBrick = registerItem(new ItemRegistry("firebrick"));
        Carbon = registerItem(new ItemRegistry("carbon"));
        SteelRod = registerItem(new ItemRegistry("steelrod"));
        IronDisc = registerItem(new ItemRegistry("irondisc"));
        LowGradeSteelRod = registerItem(new ItemRegistry("lowgradesteelrod"));
        Claw = registerItem(new ClawRegistry("claw", ClawTool, 64));

        CobaltRod = registerItem(new ItemRegistry("cobaltrod"));
        AquisOrb = registerItem(new ItemRegistry("aquisorb"));
        ShadowOrb = registerItem(new ItemRegistry("shadoworb"));
        ShadowHiltParts = registerItem(new ItemRegistry("shadowhiltparts"));
        ShadowHilt = registerItem(new ItemRegistry("shadowhilt"));
        DistortionOrb = registerItem(new ItemRegistry("distortionorb"));
        EmeraldShard = registerItem(new ItemRegistry("emeraldshard"));
        OrbMesh = registerItem(new ItemRegistry("orbmesh"));
        ShadowStaff = registerItem(new ShadowStaff("shadowstaff", Shadow, 2400));
        DistortionWand = registerItem(new DistortionWand("distortionwand", Distortion, 41));

        AquisStaff = registerItem(new AquisStaff("aquisstaff", Archium, 7, 1F, 5400));
        BabylonWand = registerItem(new BabylonWand("babylonwand", Archium, 7, 1F, 5400));
        ZirconiumStaff = registerItem(new ZirconiumStaff("zirconiumstaff", Archium, 7, 1F, 5400));
        SwordOfTheAlti = registerItem(new SwordOfTheAlti("swordofthealti", Archium, 23, 3F, -1));

        FlorucBattleAxe = registerItem(new FlorucBattleAxe("florucbattleaxe", Florium, 11F, 1.5F, 3600));
        FlorucSword = registerItem(new FlorucSword("florucsword", Florium, 31, 0.25F, 3600));
        FloriumAxe = registerItem(new FloriumAxe("floriumaxe", Florium, 23, 0.35F, 3600));
        SwordOfFlorus = registerItem(new SwordOfFlorus("swordofflorus", Florium, 29, 3F, -1));

        GreenBlade = registerItem(new GreenBlade("greenblade", Vitaem, 24, 3.5F, 3600));
        VitriusStar = registerItem(new VitriusStar("vitriusstar", Vitaem, 4, 1F, 400));
        VitraecBow = registerItem(new VitraecBow("vitraecbow", Vitaem, 23, 1F, 3600));
        VitraemSaber = registerItem(new VitraemSaber("vitraemsaber", Vitaem, 49, 5F, -1));

        Bee = registerItem(new ItemRegistry("bee")).setMaxStackSize(1);
        RoyalJelly = registerItem(new JellyItem("royaljelly", 20, 20, true, 10));
        NarcoBerry = registerItem(new NarcoBerry("narcoberry", -5, 5, false));
        NarcoBerrySeed = registerItem(new NarcoBerrySeed("narcoberryseed"));
        Narcotic = registerItem(new ItemRegistry("narcotic")).setMaxStackSize(1);

        CrystalBinder = registerItem(new ItemRegistry("crystalbinder"));
        BindingPowder = registerItem(new ItemRegistry("bindingpowder"));

        ShadowBoundCrystal = registerItem(new ItemRegistry("shadowboundcrystal"));
        AquisBoundCrystal = registerItem(new ItemRegistry("aquisboundcrystal"));
        DistortionBoundCrystal = registerItem(new ItemRegistry("distortionboundcrystal"));
        EmeraldBoundCrystal = registerItem(new ItemRegistry("emeraldboundcrystal"));
        FireBoundCrystal = registerItem(new ItemRegistry("fireboundcrystal"));
        SoldersPellucidus = registerItem(new ItemRegistry("solderspellucidus"));

        ShadowCrystal = registerItem(new ItemRegistry("shadowcrystal"));
        AquisCrystal = registerItem(new ItemRegistry("aquiscrystal"));
        DistortionCrystal = registerItem(new ItemRegistry("distortioncrystal"));
        EmeraldCrystal = registerItem(new ItemRegistry("emeraldcrystal"));
        FireCrystal = registerItem(new ItemRegistry("firecrystal"));

        UpgradeTwo = registerItem(new ItemRegistry("upgradetwo"));
        UpgradeThree = registerItem(new ItemRegistry("upgradethree"));
        UpgradeFour = registerItem(new ItemRegistry("upgradefour"));
        UpgradeFive = registerItem(new ItemRegistry("upgradefive"));
        UpgradeSix = registerItem(new ItemRegistry("upgradesix"));
        UpgradeSeven = registerItem(new ItemRegistry("upgradeseven"));
        UpgradeEight = registerItem(new ItemRegistry("upgradeeight"));

        Goblet = registerItem(new GobletRegistry("goblet"));
        GobletOfFire = registerItem(new GobletRegistry("gobletoffire"));
        GobletOfIce = registerItem(new GobletRegistry("gobletofice"));
        GobletOfEmerald = registerItem(new GobletRegistry("gobletofemerald"));

        LeatherStrip = registerItem(new ItemRegistry("leatherstrip"));

        EtherealClock = registerItem(new EtherealClockRegistry("etherealclock", 20));

        AquisPickaxe = registerItem(new ModPickaxeRegistry("aquispickaxe", Aquis));
        AquisAxe = registerItem(new ModAxeRegistry("aquisaxe", Aquis, 19F, -1F));
        AquisShovel = registerItem(new ModShovelRegistry("aquisshovel", Aquis));
        AquisHoe = registerItem(new ModHoeRegistry("aquishoe", Aquis));
        AquisSword = registerItem(new ModSwordRegistry("aquissword", Aquis, 16F, -0.5F));

        CobaltPickaxe = registerItem(new ModPickaxeRegistry("cobaltpickaxe", Cobalt));
        CobaltAxe = registerItem(new ModAxeRegistry("cobaltaxe", Cobalt, 8.5F, -3.0F));
        CobaltShovel = registerItem(new ModShovelRegistry("cobaltshovel", Cobalt));
        CobaltHoe = registerItem(new ModHoeRegistry("cobalthoe", Cobalt));
        CobaltSword = registerItem(new ModSwordRegistry("cobaltsword", Cobalt, 7F, -2.4F));

        DistortionPickaxe = registerItem(new ModPickaxeRegistry("distortionpickaxe", Distortion));
        DistortionAxe = registerItem(new ModAxeRegistry("distortionaxe", Distortion, 10.5F, -3.0F));
        DistortionShovel = registerItem(new ModShovelRegistry("distortionshovel", Distortion));
        DistortionHoe = registerItem(new ModHoeRegistry("distortionhoe", Distortion));
        DistortionSword = registerItem(new ModSwordRegistry("distortionsword", Distortion, 8.5F, -2.4F));

        FirePickaxe = registerItem(new ModPickaxeRegistry("firepickaxe", Fire));
        FireAxe = registerItem(new ModAxeRegistry("fireaxe", Fire, 16F, -3.0F));
        FireShovel = registerItem(new ModShovelRegistry("fireshovel", Fire));
        FireHoe = registerItem(new ModHoeRegistry("firehoe", Fire));
        FireSword = registerItem(new ModSwordRegistry("firesword", Fire, 13F, -2.4F));

        RubyPickaxe = registerItem(new ModPickaxeRegistry("rubypickaxe", Ruby));
        RubyAxe = registerItem(new ModAxeRegistry("rubyaxe", Ruby, 6.5F, -3.0F));
        RubyShovel = registerItem(new ModShovelRegistry("rubyshovel", Ruby));
        RubyHoe = registerItem(new ModHoeRegistry("rubyhoe", Ruby));
        RubySword = registerItem(new ModSwordRegistry("rubysword", Ruby, 5.5F, -2.4F));

        CopperPickaxe = registerItem(new ModPickaxeRegistry("copperpickaxe", Copper));
        CopperAxe = registerItem(new ModAxeRegistry("copperaxe", Copper, 3.5F, -3.0F));
        CopperShovel = registerItem(new ModShovelRegistry("coppershovel", Copper));
        CopperHoe = registerItem(new ModHoeRegistry("copperhoe", Copper));
        CopperSword = registerItem(new ModSwordRegistry("coppersword", Copper, 3F, -2.4F));

        BronzePickaxe = registerItem(new ModPickaxeRegistry("bronzepickaxe", Bronze));
        BronzeAxe = registerItem(new ModAxeRegistry("bronzeaxe", Bronze, 12.5F, -1.0F));
        BronzeShovel = registerItem(new ModShovelRegistry("bronzeshovel", Bronze));
        BronzeHoe = registerItem(new ModHoeRegistry("bronzehoe", Bronze));
        BronzeSword = registerItem(new ModSwordRegistry("bronzesword", Bronze, 5F, -0.5F));

        ShadowPickaxe = registerItem(new ModPickaxeRegistry("shadowpickaxe", Shadow));
        ShadowAxe = registerItem(new ModAxeRegistry("shadowaxe", Shadow, 17.5F, 0.01F));
        ShadowShovel = registerItem(new ModShovelRegistry("shadowshovel", Shadow));
        ShadowHoe = registerItem(new ModHoeRegistry("shadowhoe", Shadow));
        ShadowSword = registerItem(new ModSwordRegistry("shadowsword", Shadow, 15.5F, -0.001F));

        PurviaStick = registerItem(new ItemRegistry("purviastick"));

        PurviaPickaxe = registerItem(new PurviaPickaxeRegistry("purviapickaxe", Purvia));
        PurviaAxe = registerItem(new PurviaAxeRegistry("purviaaxe", Purvia, 23F, -1F));
        PurviaShovel = registerItem(new PurviaShovelRegistry("purviashovel", Purvia));
        PurviaHoe = registerItem(new PurviaHoeRegistry("purviahoe", Purvia));
        PurviaSword = registerItem(new PurviaSwordRegistry("purviasword", Purvia, 21F, -0.5F));

        Hammer = registerItem(new DurableToolRegistry("hammer", 64));
        Icepick = registerItem(new GlassBreakerRegistry("icepick", GlassTool, 1000));
        IronOrePick = registerItem(new IronOrePickaxe("ironorepick", Bronze, 3));

        RubyHelmet = registerItem(new ArmorRegistry("rubyhelmet", RubyArmor, 1, EntityEquipmentSlot.HEAD));
        RubyChestplate = registerItem(new ArmorRegistry("rubychestplate", RubyArmor, 1, EntityEquipmentSlot.CHEST));
        RubyLeggings = registerItem(new ArmorRegistry("rubyleggings", RubyArmor, 2, EntityEquipmentSlot.LEGS));
        RubyBoots = registerItem(new ArmorRegistry("rubyboots", RubyArmor, 1, EntityEquipmentSlot.FEET));

        CopperHelmet = registerItem(new ArmorRegistry("copperhelmet", CopperArmor, 1, EntityEquipmentSlot.HEAD));
        CopperChestplate = registerItem(new ArmorRegistry("copperchestplate", CopperArmor, 1, EntityEquipmentSlot.CHEST));
        CopperLeggings = registerItem(new ArmorRegistry("copperleggings", CopperArmor, 2, EntityEquipmentSlot.LEGS));
        CopperBoots = registerItem(new ArmorRegistry("copperboots", CopperArmor, 1, EntityEquipmentSlot.FEET));

        BronzeHelmet = registerItem(new ArmorRegistry("bronzehelmet", BronzeArmor, 1, EntityEquipmentSlot.HEAD));
        BronzeChestplate = registerItem(new ArmorRegistry("bronzechestplate", BronzeArmor, 1, EntityEquipmentSlot.CHEST));
        BronzeLeggings = registerItem(new ArmorRegistry("bronzeleggings", BronzeArmor, 2, EntityEquipmentSlot.LEGS));
        BronzeBoots = registerItem(new ArmorRegistry("bronzeboots", BronzeArmor, 1, EntityEquipmentSlot.FEET));

        CobaltHelmet = registerItem(new ArmorRegistry("cobalthelmet", CobaltArmor, 1, EntityEquipmentSlot.HEAD));
        CobaltChestplate = registerItem(new ArmorRegistry("cobaltchestplate", CobaltArmor, 1, EntityEquipmentSlot.CHEST));
        CobaltLeggings = registerItem(new ArmorRegistry("cobaltleggings", CobaltArmor, 2, EntityEquipmentSlot.LEGS));
        CobaltBoots = registerItem(new ArmorRegistry("cobaltboots", CobaltArmor, 1, EntityEquipmentSlot.FEET));

        FireHelmet = registerItem(new FireArmor("firehelmet", FireArmor, 1, EntityEquipmentSlot.HEAD));
        FireChestplate = registerItem(new FireArmor("firechestplate", FireArmor, 1, EntityEquipmentSlot.CHEST));
        FireLeggings = registerItem(new FireArmor("fireleggings", FireArmor, 2, EntityEquipmentSlot.LEGS));
        FireBoots = registerItem(new FireArmor("fireboots", FireArmor, 1, EntityEquipmentSlot.FEET));

        ShadowHelmet = registerItem(new ArmorRegistry("shadowhelmet", ShadowArmor, 1, EntityEquipmentSlot.HEAD));
        ShadowChestplate = registerItem(new ArmorRegistry("shadowchestplate", ShadowArmor, 1, EntityEquipmentSlot.CHEST));
        ShadowLeggings = registerItem(new ArmorRegistry("shadowleggings", ShadowArmor, 2, EntityEquipmentSlot.LEGS));
        ShadowBoots = registerItem(new ArmorRegistry("shadowboots", ShadowArmor, 1, EntityEquipmentSlot.FEET));

        DistortionHelmet = registerItem(new ArmorRegistry("distortionhelmet", DistortionArmor, 1, EntityEquipmentSlot.HEAD));
        DistortionChestplate = registerItem(new ArmorRegistry("distortionchestplate", DistortionArmor, 1, EntityEquipmentSlot.CHEST));
        DistortionLeggings = registerItem(new ArmorRegistry("distortionleggings", DistortionArmor, 2, EntityEquipmentSlot.LEGS));
        DistortionBoots = registerItem(new ArmorRegistry("distortionboots", DistortionArmor, 1, EntityEquipmentSlot.FEET));

        AquisHelmet = registerItem(new AquisArmor("aquishelmet", AquisArmor, 1, EntityEquipmentSlot.HEAD));
        AquisChestplate = registerItem(new AquisArmor("aquischestplate", AquisArmor, 1, EntityEquipmentSlot.CHEST));
        AquisLeggings = registerItem(new AquisArmor("aquisleggings", AquisArmor, 2, EntityEquipmentSlot.LEGS));
        AquisBoots = registerItem(new AquisArmor("aquisboots", AquisArmor, 1, EntityEquipmentSlot.FEET));

        ArchaicInfuserI = registerItem(new InfuserItem("archaicinfuseri"));

        ArcaneArtifact = registerItem(new ItemRegistry("arcaneartifact"));

        Quazar = registerItem(new RecordRegistry("quazar", ModSounds.Quazar));
        Saronade = registerItem(new RecordRegistry("saronade", ModSounds.Saronade));
        Time = registerItem(new RecordRegistry("time", ModSounds.Time));
        Backpack = registerItem(new ItemStore("backpack"));
        DiviningRod = registerItem(new ItemDiviningRod("diviningrod", ToolMaterial.WOOD, 16));


        if (Config.opTools == true) {
            KingsHammer = registerItem(new MultiToolRegistry("kingshammer", LowGradeSteel, 9F, -2.5F));
            BedrockHammer = registerItem(new BedrockToolRegistry("bedrockhammer", 100, Bedrock));
            BedrockSword = registerItem(new BedrockSwordRegistry("bedrocksword"));
        }

    }

    public static void mainRegistry() {
    }

    private static <T extends Item> T registerItem(T item) {
        Item_List.add(item);
        return item;
    }
}