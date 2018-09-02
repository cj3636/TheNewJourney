package com.thenewjourney.items;

import com.cj3636.lib.Config;
import com.cj3636.lib.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ItemRenderer {

    public static void RegisterItemRenderer() {
        reg(ModItems.Guide);

        reg(ModItems.ArchiumIngot);
        reg(ModItems.ArchaicOrb);
        reg(ModItems.FloricOrb);
        reg(ModItems.FloriumIngot);
        reg(ModItems.QuazanScale);
        reg(ModItems.VitaemIngot);

        reg(ModItems.AquisIngot);
        reg(ModItems.AquisPickaxe);
        reg(ModItems.AquisAxe);
        reg(ModItems.AquisShovel);
        reg(ModItems.AquisHoe);
        reg(ModItems.AquisSword);
        reg(ModItems.AquisHelmet);
        reg(ModItems.AquisChestplate);
        reg(ModItems.AquisLeggings);
        reg(ModItems.AquisBoots);

        reg(ModItems.CobaltIngot);
        reg(ModItems.DimensionChanger);
        reg(ModItems.CobaltPickaxe);
        reg(ModItems.CobaltAxe);
        reg(ModItems.CobaltShovel);
        reg(ModItems.CobaltHoe);
        reg(ModItems.CobaltSword);
        reg(ModItems.CobaltHelmet);
        reg(ModItems.CobaltChestplate);
        reg(ModItems.CobaltLeggings);
        reg(ModItems.CobaltBoots);

        reg(ModItems.DistortionGem);
        reg(ModItems.DistortionPickaxe);
        reg(ModItems.DistortionAxe);
        reg(ModItems.DistortionShovel);
        reg(ModItems.DistortionHoe);
        reg(ModItems.DistortionSword);
        reg(ModItems.DistortionHelmet);
        reg(ModItems.DistortionChestplate);
        reg(ModItems.DistortionLeggings);
        reg(ModItems.DistortionBoots);

        reg(ModItems.FireIngot);
        reg(ModItems.FirePickaxe);
        reg(ModItems.FireAxe);
        reg(ModItems.FireShovel);
        reg(ModItems.FireHoe);
        reg(ModItems.FireSword);
        reg(ModItems.FireHelmet);
        reg(ModItems.FireChestplate);
        reg(ModItems.FireLeggings);
        reg(ModItems.FireBoots);

        reg(ModItems.RubyGem);
        reg(ModItems.RubyPickaxe);
        reg(ModItems.RubyAxe);
        reg(ModItems.RubyShovel);
        reg(ModItems.RubyHoe);
        reg(ModItems.RubySword);
        reg(ModItems.RubyHelmet);
        reg(ModItems.RubyChestplate);
        reg(ModItems.RubyLeggings);
        reg(ModItems.RubyBoots);

        reg(ModItems.CopperIngot);
        reg(ModItems.TinIngot);
        reg(ModItems.BronzeIngot);
        reg(ModItems.CopperIngot);
        reg(ModItems.CopperPickaxe);
        reg(ModItems.CopperAxe);
        reg(ModItems.CopperShovel);
        reg(ModItems.CopperHoe);
        reg(ModItems.CopperSword);
        reg(ModItems.BronzeIngot);
        reg(ModItems.BronzePickaxe);
        reg(ModItems.BronzeAxe);
        reg(ModItems.BronzeShovel);
        reg(ModItems.BronzeHoe);
        reg(ModItems.BronzeSword);
        reg(ModItems.CopperHelmet);
        reg(ModItems.CopperChestplate);
        reg(ModItems.CopperLeggings);
        reg(ModItems.CopperBoots);
        reg(ModItems.BronzeHelmet);
        reg(ModItems.BronzeChestplate);
        reg(ModItems.BronzeLeggings);
        reg(ModItems.BronzeBoots);

        reg(ModItems.ShadowIngot);
        reg(ModItems.ShadowEssence);
        reg(ModItems.ShadowPickaxe);
        reg(ModItems.ShadowAxe);
        reg(ModItems.ShadowShovel);
        reg(ModItems.ShadowHoe);
        reg(ModItems.ShadowSword);
        reg(ModItems.ShadowHelmet);
        reg(ModItems.ShadowChestplate);
        reg(ModItems.ShadowLeggings);
        reg(ModItems.ShadowBoots);

        reg(ModItems.PurviaStick);
        reg(ModItems.PurviaPickaxe);
        reg(ModItems.PurviaAxe);
        reg(ModItems.PurviaShovel);
        reg(ModItems.PurviaHoe);
        reg(ModItems.PurviaSword);

        reg(ModItems.CrypticChunk);
        reg(ModItems.AncientIngot);

        reg(ModItems.FireDust);
        reg(ModItems.PheonixDust);
        reg(ModItems.Pestal);
        reg(ModItems.TranquilizerArrow);
        reg(ModItems.FireLight);
        reg(ModItems.AtriumLight);
        reg(ModItems.FireGem);
        reg(ModItems.BurntRedstone);
        reg(ModItems.ClayMixture);
        reg(ModItems.FireSeed);
        reg(ModItems.SpatiumPowder);
        reg(ModItems.InversionSerumBottle);
        reg(ModItems.BloodGem);

        reg(ModItems.Hammer);
        reg(ModItems.Icepick);
        reg(ModItems.IronOrePick);
        reg(ModItems.IronDisc);
        reg(ModItems.LowGradeSteelRod);
        reg(ModItems.SteelRod);
        reg(ModItems.LowGradeSteelIngot);
        reg(ModItems.Carbon);
        reg(ModItems.FireBrick);
        reg(ModItems.SteelIngot);
        reg(ModItems.Claw);

        reg(ModItems.CobaltRod);
        reg(ModItems.AquisOrb);
        reg(ModItems.ShadowOrb);
        reg(ModItems.DistortionOrb);
        reg(ModItems.EmeraldShard);
        reg(ModItems.OrbMesh);
        reg(ModItems.ShadowStaff);
        reg(ModItems.EtherealClock);

        reg(ModItems.AquisStaff);
        reg(ModItems.BabylonWand);
        reg(ModItems.ZirconiumStaff);
        reg(ModItems.SwordOfTheAlti);

        reg(ModItems.FlorucBattleAxe);
        reg(ModItems.FlorucSword);
        reg(ModItems.FloriumAxe);
        reg(ModItems.SwordOfFlorus);

        reg(ModItems.GreenBlade);
        reg(ModItems.VitriusStar);
        reg(ModItems.VitraecBow);
        reg(ModItems.VitraemSaber);

        reg(ModItems.DistortionWand);

        reg(ModItems.Bee);
        reg(ModItems.RoyalJelly);

        reg(ModItems.NarcoBerry);
        reg(ModItems.NarcoBerrySeed);
        reg(ModItems.Narcotic);

        reg(ModItems.CrystalBinder);
        reg(ModItems.BindingPowder);
        reg(ModItems.ShadowHilt);
        reg(ModItems.ShadowHiltParts);

        reg(ModItems.ShadowBoundCrystal);
        reg(ModItems.AquisBoundCrystal);
        reg(ModItems.FireBoundCrystal);
        reg(ModItems.DistortionBoundCrystal);
        reg(ModItems.EmeraldBoundCrystal);
        reg(ModItems.SoldersPellucidus);

        reg(ModItems.ShadowCrystal);
        reg(ModItems.AquisCrystal);
        reg(ModItems.FireCrystal);
        reg(ModItems.DistortionCrystal);
        reg(ModItems.EmeraldCrystal);

        reg(ModItems.UpgradeTwo);
        reg(ModItems.UpgradeThree);
        reg(ModItems.UpgradeFour);
        reg(ModItems.UpgradeFive);
        reg(ModItems.UpgradeSix);
        reg(ModItems.UpgradeSeven);
        reg(ModItems.UpgradeEight);

        reg(ModItems.Goblet);
        reg(ModItems.GobletOfFire);
        reg(ModItems.GobletOfIce);
        reg(ModItems.GobletOfEmerald);

        reg(ModItems.LeatherStrip);

        reg(ModItems.ArchaicInfuserI);

        reg(ModItems.ArcaneArtifact);

        reg(ModItems.Quazar);
        reg(ModItems.Saronade);
        reg(ModItems.Time);

        reg(ModItems.Backpack);


        if (Config.opTools == true) {
            reg(ModItems.KingsHammer);
            reg(ModItems.BedrockHammer);
            reg(ModItems.BedrockSword);
            reg(ModItems.DiviningRod);
        }
    }

    public static void reg(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .register(item, 0, new ModelResourceLocation(Ref.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}

