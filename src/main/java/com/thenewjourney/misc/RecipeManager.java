package com.thenewjourney.misc;

import com.cj3636.lib.RecipeGen;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.blocks.cauldron.ImbuedCraftingManager;
import com.thenewjourney.compat.baubles.InitItems;
import com.thenewjourney.items.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeManager {
    public static void mainRegistry() {
        addOreDictionary();
        //addCraftingRecipe();
        addSmeltingRecipe();
        addBrewingRecipe();
        //addDictionaryRecipes();
        //addInfuserRecipes();
        //addBaubleRecipes();
    }

    private static void addCraftingRecipe() {
        // ItemStacks
        ItemStack PurpleDyeStack = new ItemStack(Items.DYE, 1, 5);
        ItemStack BrownDyeStack = new ItemStack(Items.DYE, 1, 3);
        ItemStack CyanDyeStack = new ItemStack(Items.DYE, 1, 6);
        ItemStack CactusDyeStack = new ItemStack(Items.DYE, 1, 2);
        ItemStack PinkDyeStack = new ItemStack(Items.DYE, 1, 9);
        ItemStack BoneMealStack = new ItemStack(Items.DYE, 1, 15);

        ItemStack AndesiteStack = new ItemStack(Blocks.STONE, 1, 5);
        ItemStack DioriteStack = new ItemStack(Blocks.STONE, 1, 3);
        ItemStack GraniteStack = new ItemStack(Blocks.STONE, 1, 1);
        ItemStack NetherrackStack = new ItemStack(Blocks.NETHERRACK, 1);
        ItemStack NetherBrickStack = new ItemStack(Blocks.NETHER_BRICK, 1);
        ItemStack BrickStack = new ItemStack(Blocks.BRICK_BLOCK, 1);
        // Shapeless
        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.ClayMixture, 1), Blocks.GRAVEL, Blocks.SAND,
                Items.CLAY_BALL, ModItems.BurntRedstone);
        // Blocks
        RecipeGen.addShapelessRecipe(new ItemStack(ModBlocks.CobaltBlock), ModItems.CobaltIngot,
                ModItems.CobaltIngot, ModItems.CobaltIngot, ModItems.CobaltIngot, ModItems.CobaltIngot,
                ModItems.CobaltIngot, ModItems.CobaltIngot, ModItems.CobaltIngot, ModItems.CobaltIngot);
        RecipeGen.addShapelessRecipe(new ItemStack(ModBlocks.DistortedBlock), ModItems.DistortionGem,
                ModItems.DistortionGem, ModItems.DistortionGem, ModItems.DistortionGem, ModItems.DistortionGem,
                ModItems.DistortionGem, ModItems.DistortionGem, ModItems.DistortionGem, ModItems.DistortionGem);
        RecipeGen.addShapelessRecipe(new ItemStack(ModBlocks.RubyBlock), ModItems.RubyGem, ModItems.RubyGem,
                ModItems.RubyGem, ModItems.RubyGem, ModItems.RubyGem, ModItems.RubyGem, ModItems.RubyGem,
                ModItems.RubyGem, ModItems.RubyGem);
        RecipeGen.addShapelessRecipe(new ItemStack(ModBlocks.CopperBlock), ModItems.CopperIngot, ModItems.CopperIngot,
                ModItems.CopperIngot, ModItems.CopperIngot, ModItems.CopperIngot, ModItems.CopperIngot, ModItems.CopperIngot,
                ModItems.CopperIngot, ModItems.CopperIngot);
        RecipeGen.addShapelessRecipe(new ItemStack(ModBlocks.TinBlock), ModItems.TinIngot, ModItems.TinIngot,
                ModItems.TinIngot, ModItems.TinIngot, ModItems.TinIngot, ModItems.TinIngot, ModItems.TinIngot,
                ModItems.TinIngot, ModItems.TinIngot);
        RecipeGen.addShapelessRecipe(new ItemStack(ModBlocks.BronzeBlock), ModItems.BronzeIngot, ModItems.BronzeIngot,
                ModItems.BronzeIngot, ModItems.BronzeIngot, ModItems.BronzeIngot, ModItems.BronzeIngot, ModItems.BronzeIngot,
                ModItems.BronzeIngot, ModItems.BronzeIngot);
        RecipeGen.addShapelessRecipe(new ItemStack(ModBlocks.ShadowBlock), ModItems.ShadowIngot,
                ModItems.ShadowIngot, ModItems.ShadowIngot, ModItems.ShadowIngot, ModItems.ShadowIngot,
                ModItems.ShadowIngot, ModItems.ShadowIngot, ModItems.ShadowIngot, ModItems.ShadowIngot);
        RecipeGen.addShapelessRecipe(new ItemStack(ModBlocks.FireBlock), ModItems.FireIngot, ModItems.FireIngot,
                ModItems.FireIngot, ModItems.FireIngot, ModItems.FireIngot, ModItems.FireIngot, ModItems.FireIngot,
                ModItems.FireIngot, ModItems.FireIngot);
        RecipeGen.addShapelessRecipe(new ItemStack(ModBlocks.AquisBlock), ModItems.AquisIngot, ModItems.AquisIngot,
                ModItems.AquisIngot, ModItems.AquisIngot, ModItems.AquisIngot, ModItems.AquisIngot, ModItems.AquisIngot,
                ModItems.AquisIngot, ModItems.AquisIngot);
        //Reverse Blocks to Ingots
        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.CobaltIngot, 9), ModBlocks.CobaltBlock);
        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.RubyGem, 9), ModBlocks.RubyBlock);
        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.CopperIngot, 9), ModBlocks.CopperBlock);
        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.TinIngot, 9), ModBlocks.TinBlock);
        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.BronzeIngot, 9), ModBlocks.BronzeBlock);
        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.DistortionGem, 9), ModBlocks.DistortedBlock);
        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.ShadowIngot, 9), ModBlocks.ShadowBlock);
        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.FireIngot, 9), ModBlocks.FireBlock);
        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.AquisIngot, 9), ModBlocks.AquisBlock);

        //Misc BLocks
        RecipeGen.addRecipe(new ItemStack(ModBlocks.DistortedBricks, 4), "## ", "## ", "   ", '#',
                ModBlocks.DistortedStone);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.Lamp, 4), "BFB", "CLC", "BFB", 'B', ModBlocks.BurntStone, 'F',
                ModItems.FireGem, 'C', ModItems.CobaltIngot, 'L', Blocks.REDSTONE_LAMP);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.Chandelier, 1), " B ", "BBB", "FLF", 'B', ModBlocks.BurntStone, 'F',
                ModBlocks.FlorusBlock, 'L', ModBlocks.Lamp);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.PervatekiForge), "SBS", "DTD", "SBS", 'S',
                ModItems.SpatiumPowder, 'B', ModBlocks.BurntStone, 'D', ModBlocks.DistortedBricks, 'T', ModBlocks.Substrate);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.ArcaneFurnace), "WIW", "FPE", "WWW", 'W',
                ModBlocks.WarpStone, 'P', ModBlocks.PervatekiForge, 'I', ModItems.GobletOfIce, 'F', ModItems.GobletOfFire, 'E', ModItems.GobletOfEmerald);
        RecipeGen.addShapelessRecipe(new ItemStack(ModBlocks.ArmoredStone), ModBlocks.BurntStone, Blocks.IRON_BARS);
        RecipeGen.addShapelessRecipe(new ItemStack(ModBlocks.ArmoredGlass), Blocks.GLASS, Blocks.IRON_BARS);
        RecipeGen.addShapelessRecipe(new ItemStack(ModBlocks.ArmoredGlass), ModBlocks.ClearGlass, Blocks.IRON_BARS);
        //Stairs
        RecipeGen.addRecipe(new ItemStack(ModBlocks.WarpStoneStairs, 4), "B  ", "BB ", "BBB", 'B',
                ModBlocks.WarpStone);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.BurntBrickStairs, 4), "B  ", "BB ", "BBB", 'B',
                ModBlocks.BurntBricks);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.BurntStoneStairs, 4), "B  ", "BB ", "BBB", 'B',
                ModBlocks.BurntStone);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.MarbleStairs, 4), "B  ", "BB ", "BBB", 'B',
                ModBlocks.Marble);
        // Blast Furnace
        RecipeGen.addShapelessRecipe(new ItemStack(ModBlocks.RefractoryBrick), ModItems.FireBrick,
                ModItems.FireBrick, ModItems.FireBrick, ModItems.FireBrick);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.BlastFurnace), "#*#", "*F*", "#*#", '#',
                ModItems.LowGradeSteelIngot, '*', ModBlocks.RefractoryBrick, 'F', Blocks.FURNACE);
        RecipeGen.addRecipe(new ItemStack(ModItems.Claw), "# #", " # ", " I ", '#', ModItems.SteelIngot, 'I',
                Items.STICK);
        // Repair
        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.AquisStaff),
                new ItemStack(ModItems.AquisStaff, 1, OreDictionary.WILDCARD_VALUE), ModItems.AquisIngot,
                ModItems.AquisIngot);
        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.ShadowStaff),
                new ItemStack(ModItems.ShadowStaff, 1, OreDictionary.WILDCARD_VALUE), ModItems.ShadowIngot,
                ModItems.ShadowIngot);
        // Shaped
        RecipeGen.addRecipe(new ItemStack(ModItems.FireGem), "###", "#X#", "###", '#', ModItems.FireDust, 'X',
                Items.FLINT);
        // Pickaxe
        RecipeGen.addRecipe(new ItemStack(ModItems.CobaltPickaxe), "###", " I ", " I ", '#', ModItems.CobaltIngot,
                'I', Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.DistortionPickaxe), "###", " I ", " I ", '#',
                ModItems.DistortionGem, 'I', Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.FirePickaxe), "###", " I ", " I ", '#', ModItems.FireIngot, 'I',
                Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.ShadowPickaxe), "###", " I ", " I ", '#', ModItems.ShadowIngot,
                'I', ModItems.SteelRod);
        RecipeGen.addRecipe(new ItemStack(ModItems.AquisPickaxe), "###", " I ", " I ", '#', ModItems.AquisIngot, 'I',
                ModItems.CobaltRod);
        // Axe
        RecipeGen.addRecipe(new ItemStack(ModItems.CobaltAxe), "## ", "#I ", " I ", '#', ModItems.CobaltIngot, 'I',
                Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.DistortionAxe), "## ", "#I ", " I ", '#', ModItems.DistortionGem,
                'I', Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.FireAxe), "## ", "#I ", " I ", '#', ModItems.FireIngot, 'I',
                Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.ShadowAxe), "## ", "#I ", " I ", '#', ModItems.ShadowIngot, 'I',
                ModItems.SteelRod);
        RecipeGen.addRecipe(new ItemStack(ModItems.AquisAxe), "## ", "#I ", " I ", '#', ModItems.AquisIngot, 'I',
                ModItems.CobaltRod);
        // Shovel
        RecipeGen.addRecipe(new ItemStack(ModItems.CobaltShovel), " # ", " I ", " I ", '#', ModItems.CobaltIngot,
                'I', Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.DistortionShovel), " # ", " I ", " I ", '#',
                ModItems.DistortionGem, 'I', Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.FireShovel), " # ", " I ", " I ", '#', ModItems.FireIngot, 'I',
                Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.ShadowShovel), " # ", " I ", " I ", '#', ModItems.ShadowIngot,
                'I', ModItems.SteelRod);
        RecipeGen.addRecipe(new ItemStack(ModItems.AquisShovel), " # ", " I ", " I ", '#', ModItems.AquisIngot, 'I',
                ModItems.CobaltRod);
        // Hoe
        RecipeGen.addRecipe(new ItemStack(ModItems.CobaltHoe), "## ", " I ", " I ", '#', ModItems.CobaltIngot, 'I',
                Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.DistortionHoe), "## ", " I ", " I ", '#', ModItems.DistortionGem,
                'I', Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.FireHoe), "## ", " I ", " I ", '#', ModItems.FireIngot, 'I',
                Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.ShadowHoe), "## ", " I ", " I ", '#', ModItems.ShadowIngot, 'I',
                ModItems.SteelRod);
        RecipeGen.addRecipe(new ItemStack(ModItems.AquisHoe), "## ", " I ", " I ", '#', ModItems.AquisIngot, 'I',
                ModItems.CobaltRod);
        // Sword
        RecipeGen.addRecipe(new ItemStack(ModItems.CobaltSword), " # ", " # ", " I ", '#', ModItems.CobaltIngot, 'I',
                Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.DistortionSword), " # ", " # ", " I ", '#',
                ModItems.DistortionGem, 'I', Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.FireSword), " # ", " # ", " I ", '#', ModItems.FireIngot, 'I',
                Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.ShadowSword), " # ", " # ", " I ", '#', ModItems.ShadowIngot, 'I',
                ModItems.SteelRod);
        RecipeGen.addRecipe(new ItemStack(ModItems.AquisSword), " # ", " # ", " I ", '#', ModItems.AquisIngot, 'I',
                ModItems.CobaltRod);
        // Armor
        // Cobalt
        RecipeGen.addRecipe(new ItemStack(ModItems.CobaltHelmet), "###", "# #", "   ", '#', ModItems.CobaltIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.CobaltChestplate), "# #", "###", "###", '#',
                ModItems.CobaltIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.CobaltLeggings), "###", "# #", "# #", '#', ModItems.CobaltIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.CobaltBoots), "   ", "# #", "# #", '#', ModItems.CobaltIngot);
        // Distortion
        RecipeGen.addRecipe(new ItemStack(ModItems.DistortionHelmet), "###", "# #", "   ", '#',
                ModItems.DistortionGem);
        RecipeGen.addRecipe(new ItemStack(ModItems.DistortionChestplate), "# #", "###", "###", '#',
                ModItems.DistortionGem);
        RecipeGen.addRecipe(new ItemStack(ModItems.DistortionLeggings), "###", "# #", "# #", '#',
                ModItems.DistortionGem);
        RecipeGen.addRecipe(new ItemStack(ModItems.DistortionBoots), "   ", "# #", "# #", '#',
                ModItems.DistortionGem);
        // Fire
        RecipeGen.addRecipe(new ItemStack(ModItems.FireHelmet), "###", "# #", "   ", '#', ModItems.FireIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.FireChestplate), "# #", "###", "###", '#', ModItems.FireIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.FireLeggings), "###", "# #", "# #", '#', ModItems.FireIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.FireBoots), "   ", "# #", "# #", '#', ModItems.FireIngot);
        // Aquis
        RecipeGen.addRecipe(new ItemStack(ModItems.AquisHelmet), "###", "# #", "   ", '#', ModItems.AquisIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.AquisChestplate), "# #", "###", "###", '#', ModItems.AquisIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.AquisLeggings), "###", "# #", "# #", '#', ModItems.AquisIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.AquisBoots), "   ", "# #", "# #", '#', ModItems.AquisIngot);
        // Ruby
        RecipeGen.addRecipe(new ItemStack(ModItems.RubyHelmet), "###", "# #", "   ", '#', ModItems.RubyGem);
        RecipeGen.addRecipe(new ItemStack(ModItems.RubyChestplate), "# #", "###", "###", '#', ModItems.RubyGem);
        RecipeGen.addRecipe(new ItemStack(ModItems.RubyLeggings), "###", "# #", "# #", '#', ModItems.RubyGem);
        RecipeGen.addRecipe(new ItemStack(ModItems.RubyBoots), "   ", "# #", "# #", '#', ModItems.RubyGem);
        // Copper
        RecipeGen.addRecipe(new ItemStack(ModItems.CopperHelmet), "###", "# #", "   ", '#', ModItems.CopperIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.CopperChestplate), "# #", "###", "###", '#', ModItems.CopperIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.CopperLeggings), "###", "# #", "# #", '#', ModItems.CopperIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.CopperBoots), "   ", "# #", "# #", '#', ModItems.CopperIngot);
        // Bronze
        RecipeGen.addRecipe(new ItemStack(ModItems.BronzeHelmet), "###", "# #", "   ", '#', ModItems.BronzeIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.BronzeChestplate), "# #", "###", "###", '#', ModItems.BronzeIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.BronzeLeggings), "###", "# #", "# #", '#', ModItems.BronzeIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.BronzeBoots), "   ", "# #", "# #", '#', ModItems.BronzeIngot);
        // Shadow
        RecipeGen.addRecipe(new ItemStack(ModItems.ShadowHelmet), "###", "# #", "   ", '#', ModItems.ShadowIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.ShadowChestplate), "# #", "###", "###", '#',
                ModItems.ShadowIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.ShadowLeggings), "###", "# #", "# #", '#', ModItems.ShadowIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.ShadowBoots), "   ", "# #", "# #", '#', ModItems.ShadowIngot);
        // Furnaces
        RecipeGen.addRecipe(new ItemStack(ModBlocks.AndesiteFurnaceIdle), "###", "# #", "###", '#', AndesiteStack);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.GraniteFurnaceIdle), "###", "# #", "###", '#', GraniteStack);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.DioriteFurnaceIdle), "###", "# #", "###", '#', DioriteStack);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.NetherrackFurnaceIdle), "###", "# #", "###", '#',
                NetherrackStack);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.NetherBrickFurnaceIdle), "###", "# #", "###", '#',
                NetherBrickStack);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.BrickFurnaceIdle), "###", "# #", "###", '#', BrickStack);
        // Magic Stuff
        RecipeGen.addRecipe(new ItemStack(ModItems.CobaltRod), "  #", " # ", "#  ", '#', ModItems.CobaltIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.OrbMesh), "FEF", "EAE", "FEF", 'A', ModItems.ArchiumIngot, 'F',
                ModItems.FireDust, 'E', ModItems.EmeraldShard);
        // Normal Orbs
        RecipeGen.addRecipe(new ItemStack(ModItems.AquisOrb), " # ", "#*#", " F ", '#', ModItems.AquisIngot, '*',
                ModItems.OrbMesh, 'F', ModItems.FireDust);
        RecipeGen.addRecipe(new ItemStack(ModItems.ShadowOrb), " # ", "#*#", " F ", '#', ModItems.ShadowIngot, '*',
                ModItems.OrbMesh, 'F', ModItems.FireDust);
        // Wand Orbs
        RecipeGen.addRecipe(new ItemStack(ModItems.DistortionOrb), " # ", "#*#", " F ", '#', ModItems.DistortionGem,
                '*', Items.ENDER_EYE, 'F', ModItems.FireDust);
        // Staves
        RecipeGen.addRecipe(new ItemStack(ModItems.ShadowStaff), " # ", " * ", " * ", '#', ModItems.ShadowOrb, '*',
                Items.BLAZE_ROD);
        // Wands
        RecipeGen.addRecipe(new ItemStack(ModItems.DistortionWand), "  #", " * ", "D  ", '#', ModItems.DistortionOrb,
                '*', Items.BLAZE_ROD, 'D', ModItems.DistortionGem);
        // Special Tools
        RecipeGen.addRecipe(new ItemStack(ModItems.Hammer), " S ", "#S#", "###", '#', ModItems.TinIngot, 'S',
                Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.LowGradeSteelRod), "  #", " # ", "#  ", '#',
                ModItems.LowGradeSteelIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.KingsHammer), "###", "*X*", " X ", '#', ModItems.SteelIngot, 'X',
                ModItems.SteelRod, '*', ModItems.FireIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.Icepick), "###", " X ", " X ", '#', ModItems.SteelIngot, 'X',
                ModItems.SteelRod);
        RecipeGen.addRecipe(new ItemStack(ModItems.IronOrePick), "CSC", "TPT", " I ", 'C', ModItems.CopperIngot, 'S',
                Items.STRING, 'P', Items.STONE_PICKAXE, 'T', ModItems.TinIngot, 'I', Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.BedrockHammer), "AFA", "SRS", " R ", 'S', ModItems.SteelIngot,
                'R', ModItems.SteelRod, 'F', ModItems.FireDust, 'A', ModItems.AquisIngot);
        // Baubles
        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.AncientIngot), ModItems.CrypticChunk,
                ModItems.CrypticChunk, ModItems.CrypticChunk, ModItems.CrypticChunk);
        RecipeGen.addRecipe(new ItemStack(ModItems.CrystalBinder), "###", "#*#", "###", '#',
                new ItemStack(ModBlocks.ClearGlass), '*', ModItems.AncientIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.BindingPowder, 4), "PRP", "RBR", "PRP", 'R',
                ModItems.BurntRedstone, 'B', ModItems.BloodGem, 'P', ModItems.SpatiumPowder);
        // BoundCrystals
        RecipeGen.addRecipe(new ItemStack(ModItems.AquisBoundCrystal), "B#B", "#*#", "B#B", '#', ModItems.AquisIngot,
                '*', ModItems.CrystalBinder, 'B', ModItems.BindingPowder);
        RecipeGen.addRecipe(new ItemStack(ModItems.ShadowBoundCrystal), "B#B", "#*#", "B#B", '#',
                ModItems.ShadowIngot, '*', ModItems.CrystalBinder, 'B', ModItems.BindingPowder);
        RecipeGen.addRecipe(new ItemStack(ModItems.EmeraldBoundCrystal), "B#B", "#*#", "B#B", '#', Items.EMERALD,
                '*', ModItems.CrystalBinder, 'B', ModItems.BindingPowder);
        RecipeGen.addRecipe(new ItemStack(ModItems.DistortionBoundCrystal), "B#B", "#*#", "B#B", '#',
                ModItems.DistortionGem, '*', ModItems.CrystalBinder, 'B', ModItems.BindingPowder);
        RecipeGen.addRecipe(new ItemStack(ModItems.FireBoundCrystal), "B#B", "#*#", "B#B", '#', ModItems.FireIngot,
                '*', ModItems.CrystalBinder, 'B', ModItems.BindingPowder);
        // Misc
        RecipeGen.addRecipe(new ItemStack(ModItems.Backpack), "LWL", "LCL", "SSS", 'L',
                ModItems.LeatherStrip, 'W', Blocks.WOOL, 'C', ModBlocks.Drawer, 'S', Items.STRING);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.Carpet, 8), "SWS", "WDW", "SWS", 'W', Blocks.WOOL, 'S', ModBlocks.Marble, 'D', CyanDyeStack);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.Carpet2, 8), "SWS", "WDW", "SWS", 'W', Blocks.WOOL, 'S', ModBlocks.Marble, 'D', BrownDyeStack);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.Carpet3, 8), "SWS", "WDW", "SWS", 'W', Blocks.WOOL, 'S', ModBlocks.Marble, 'D', CactusDyeStack);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.CarpetG, 8), "SWS", "WDW", "SWS", 'W', Blocks.GLASS, 'S', ModBlocks.Marble, 'D', CactusDyeStack);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.CarpetG2, 8), "SWS", "WDW", "SWS", 'W', Blocks.GLASS, 'S', ModBlocks.Marble, 'D', PinkDyeStack);
        RecipeGen.addRecipe(new ItemStack(ModItems.SpatiumPowder, 4), "PRP", "RBR", "PRP", 'R',
                ModItems.BurntRedstone, 'B', BoneMealStack, 'P', PurpleDyeStack);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.KingBlock), "CFC", "FAF", "CFC", 'C', ModItems.CobaltIngot, 'F', ModBlocks.FireBlock, 'A', ModItems.AquisIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.BedrockSword), " B ", " B ", " S ", 'B', Blocks.BEDROCK, 'S',
                Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.EtherealClock), "SRS", "RCR", "SRS", 'S', ModItems.SpatiumPowder,
                'C', Items.CLOCK, 'R', ModItems.RubyGem);

        RecipeGen.addRecipe(new ItemStack(ModBlocks.Apiary), "WGW", "WDW", "RRR", 'W', Blocks.PLANKS, 'G',
                ModItems.RubyGem, 'D', ModBlocks.Drawer, 'R', ModBlocks.WarpStone);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.Purifier), "RWR", "RFR", "WBW", 'W', ModBlocks.WarpStone, 'F',
                ModItems.FireCrystal, 'B', ModBlocks.FireBlock, 'R', ModItems.RubyGem);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.Duplicator), "FAF", "BDB", "LLL", 'F', ModBlocks.FlorusBlock, 'A',
                ModBlocks.AquisBlock, 'B', ModBlocks.Carpet, 'D', Blocks.DIAMOND_BLOCK, 'L', ModItems.FloriumIngot);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.Compiler), "DWD", "DPD", "WPW", 'W', ModBlocks.WarpStone, 'D',
                ModItems.DistortionGem, 'P', Blocks.PISTON);
        // Artifacts
        RecipeGen.addRecipe(new ItemStack(ModItems.ArchaicInfuserI), "#A#", "DFD", "FFF", '#', Blocks.PLANKS, 'D',
                ModBlocks.DistortedStone, 'F', ModBlocks.BurntStone, 'A', ModItems.ArcaneArtifact);
        // Crystal Power
        RecipeGen.addRecipe(new ItemStack(ModBlocks.Crystal), "GFG", "FSF", "GFG", 'F', ModItems.FireIngot, 'G',
                Items.GOLD_INGOT, 'S', ModItems.ShadowCrystal);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.CrystalProvider, 4), "RPR", "ACA", "ARA", 'R', ModItems.RubyGem, 'P',
                ModItems.PheonixDust, 'A', ModItems.AncientIngot, 'C', ModBlocks.Crystal);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.Frame, 4), "GAG", "A A", "GAG", 'A', Items.IRON_INGOT, 'G',
                ModItems.SteelRod);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.ForgeController), "RRR", "RFR", "RRR", 'R',
                ModBlocks.RefractoryBrick, 'F', Blocks.FURNACE);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.ArcanePillar), "FCF", "F F", "FRF", 'F', ModBlocks.BurntStone,
                'C', Blocks.CRAFTING_TABLE, 'R', ModItems.RubyGem);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.StoneMan, 4), " S ", "SAS", " S ", 'S', ModBlocks.BurntStone,
                'A', ModItems.ArchaicOrb);
        RecipeGen.addRecipe(new ItemStack(ModItems.TranquilizerArrow, 4), " S ", "SAS", " S ", 'S', Items.ARROW,
                'A', ModItems.Narcotic);
        RecipeGen.addRecipe(new ItemStack(ModItems.Goblet), "GCG", "GBG", " G ", 'G', Items.GOLD_INGOT, 'C',
                ModBlocks.ClearGlass, 'B', Items.GOLDEN_APPLE);
        // Upgrade Ions
        RecipeGen.addRecipe(new ItemStack(ModItems.UpgradeTwo), "OGO", "BEB", "ORO", 'O', Blocks.OBSIDIAN, 'G',
                ModItems.BloodGem, 'B', ModItems.BindingPowder, 'E', Blocks.EMERALD_BLOCK, 'R', ModItems.RubyGem);
        RecipeGen.addRecipe(new ItemStack(ModItems.UpgradeThree), "OGO", "BEB", "ORO", 'O', Blocks.OBSIDIAN, 'G',
                ModItems.BloodGem, 'B', ModItems.BindingPowder, 'E', ModBlocks.BeeHive, 'R', ModItems.CobaltIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.UpgradeFour), "OGO", "BEB", "ORO", 'O', Blocks.OBSIDIAN, 'G',
                ModItems.BloodGem, 'B', ModItems.BindingPowder, 'E', ModBlocks.FlorusBlock, 'R', ModItems.ShadowIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.UpgradeFive), "OGO", "BEB", "ORO", 'O', Blocks.OBSIDIAN, 'G',
                ModBlocks.AquisOre, 'B', ModItems.SteelRod, 'E', ModBlocks.CobaltBlock, 'R', ModItems.AncientIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.UpgradeSix), "OGO", "BEB", "ORO", 'O', Blocks.OBSIDIAN, 'G',
                ModBlocks.StoneMan, 'B', ModItems.ArchiumIngot, 'E', ModItems.EtherealClock, 'R',
                ModItems.InversionSerumBottle);
        RecipeGen.addRecipe(new ItemStack(ModItems.UpgradeSeven), "BIB", "FJE", "ORO", 'O', Blocks.OBSIDIAN, 'B',
                ModItems.BindingPowder, 'J', ModItems.RoyalJelly, 'R', ModItems.RubyGem, 'I', ModItems.GobletOfIce,
                'F', ModItems.GobletOfFire, 'E', ModItems.GobletOfEmerald);
        // Weaponry
        RecipeGen.addRecipe(new ItemStack(ModItems.AquisStaff), " # ", " * ", " * ", '#', ModItems.AquisOrb, '*',
                Items.BLAZE_ROD);
        RecipeGen.addRecipe(new ItemStack(ModItems.BabylonWand), "  #", " * ", "D  ", '#', ModItems.FireCrystal, '*',
                Items.BLAZE_ROD, 'D', ModItems.DistortionGem);
        RecipeGen.addRecipe(new ItemStack(ModItems.ZirconiumStaff), "ANA", "ABA", " B ", 'A', ModItems.ArchiumIngot,
                'B', Items.BLAZE_ROD, 'N', Items.NETHER_STAR);
        RecipeGen.addRecipe(new ItemStack(ModItems.SwordOfTheAlti), " A ", " A ", " S ", 'A', ModItems.ArchiumIngot,
                'S', ModItems.SteelRod);

        RecipeGen.addRecipe(new ItemStack(ModItems.FlorucBattleAxe), "F F", " S ", " S ", 'F', ModItems.FloriumIngot,
                'S', ModItems.SteelRod);
        RecipeGen.addRecipe(new ItemStack(ModItems.FlorucSword), " F ", " F ", " S ", 'F', ModItems.FloriumIngot,
                'S', Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.FloriumAxe), "F  ", "FS ", " S ", 'F', ModItems.FloriumIngot, 'S',
                Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.SwordOfFlorus), " F ", " F ", " S ", 'F', ModItems.FloriumIngot,
                'S', ModItems.SteelRod);

        RecipeGen.addRecipe(new ItemStack(ModItems.GreenBlade), " V ", " V ", " S ", 'V', ModItems.QuazanScale, 'S',
                Items.STICK);
        RecipeGen.addRecipe(new ItemStack(ModItems.VitriusStar), " V ", "VRV", " V ", 'R', ModItems.RubyGem, 'V',
                ModItems.VitaemIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.VitraecBow), "SV ", "S V", "SV ", 'S', Items.STRING, 'V',
                ModItems.VitaemIngot);
        RecipeGen.addRecipe(new ItemStack(ModItems.VitraemSaber), " V ", " V ", " S ", 'V', ModItems.VitaemIngot,
                'S', ModItems.SteelRod);

        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.PheonixDust), ModItems.FireDust, ModItems.BloodGem, ModItems.FireGem, ModItems.SpatiumPowder, Items.GUNPOWDER, Items.BLAZE_POWDER, ModItems.BindingPowder);
        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.NarcoBerrySeed), ModItems.NarcoBerry);
        RecipeGen.addRecipe(new ItemStack(ModBlocks.Mortar), "S S", "S S", " S ", 'S', Blocks.STONE);
        RecipeGen.addRecipe(new ItemStack(ModItems.Pestal), " S ", " S ", " B ", 'S', ModItems.SteelRod, 'B', ModBlocks.BurntStone);

        //Wood
        RecipeGen.addShapelessRecipe(new ItemStack(ModBlocks.PurviaLignumPlanks, 4), ModBlocks.PurviaLignumLog);
    }

    private static void addBrewingRecipe() {
        BrewingRecipeRegistry.addRecipe(new ItemStack(Items.SPLASH_POTION), new ItemStack(ModItems.FireIngot),
                new ItemStack(ModItems.FireLight));
        BrewingRecipeRegistry.addRecipe(new ItemStack(Items.SPLASH_POTION), new ItemStack(ModItems.AquisIngot),
                new ItemStack(ModItems.AtriumLight));
    }

    private static void addSmeltingRecipe() {
        GameRegistry.addSmelting(ModBlocks.AquisOre, new ItemStack(ModItems.AquisIngot), 0.7f);
        GameRegistry.addSmelting(ModBlocks.DistortedOre, new ItemStack(ModItems.DistortionGem), 1.7f);
        GameRegistry.addSmelting(ModBlocks.CobaltOre, new ItemStack(ModItems.CobaltIngot), 0.7f);
        GameRegistry.addSmelting(ModBlocks.FireOre, new ItemStack(ModItems.FireIngot), 2.7f);
        GameRegistry.addSmelting(ModBlocks.ShadowOre, new ItemStack(ModItems.ShadowIngot), 3.7f);
        GameRegistry.addSmelting(Items.REDSTONE, new ItemStack(ModItems.BurntRedstone), 0.35f);
        GameRegistry.addSmelting(Blocks.STONE, new ItemStack(ModBlocks.BurntStone), 0.35f);
        GameRegistry.addSmelting(Blocks.BRICK_BLOCK, new ItemStack(ModBlocks.BurntBricks), 0.35f);
        GameRegistry.addSmelting(ModItems.IronDisc, new ItemStack(ModItems.LowGradeSteelIngot), 0.7f);
        GameRegistry.addSmelting(ModItems.ClayMixture, new ItemStack(ModItems.FireBrick), 0.7f);
        GameRegistry.addSmelting(ModBlocks.CrypticBlock, new ItemStack(ModItems.CrypticChunk), 2.7f);
        GameRegistry.addSmelting(ModBlocks.CopperOre, new ItemStack(ModItems.CopperIngot), 0.7f);
        GameRegistry.addSmelting(ModBlocks.TinOre, new ItemStack(ModItems.TinIngot), 0.7f);
    }

    private static void addOreDictionary() {
        OreDictionary.registerOre("rodSteel", ModItems.SteelRod);
        OreDictionary.registerOre("gemRuby", ModItems.RubyGem);
        OreDictionary.registerOre("ingotSteel", ModItems.SteelIngot);
        OreDictionary.registerOre("ingotCobalt", ModItems.CobaltIngot);
        OreDictionary.registerOre("stoneMarble", ModBlocks.Marble);
        OreDictionary.registerOre("blockMarble", ModBlocks.Marble);
        OreDictionary.registerOre("craftingToolForgeHammer", ModItems.Hammer);
        OreDictionary.registerOre("leather", ModItems.LeatherStrip);
        OreDictionary.registerOre("ingotBrickSeared", ModItems.FireBrick);
        OreDictionary.registerOre("BlockSeared", ModBlocks.RefractoryBrick);
        OreDictionary.registerOre("ingotCopper", ModItems.CopperIngot);
        OreDictionary.registerOre("ingotTin", ModItems.TinIngot);
        OreDictionary.registerOre("ingotBronze", ModItems.BronzeIngot);
        OreDictionary.registerOre("blockCopper", ModBlocks.CopperBlock);
        OreDictionary.registerOre("blockTin", ModBlocks.TinBlock);
        OreDictionary.registerOre("blockBronze", ModBlocks.BronzeBlock);
        OreDictionary.registerOre("blockGlassHardened", ModBlocks.ObsidianGlass);
        OreDictionary.registerOre("blockGlass", ModBlocks.ClearGlass);
        OreDictionary.registerOre("logWood", ModBlocks.PurviaLignumLog);
        OreDictionary.registerOre("plankWood", ModBlocks.PurviaLignumPlanks);
        OreDictionary.registerOre("treeLeaves", ModBlocks.PurviaLeaves);
    }

    private static void addDictionaryRecipes() {
        for (ItemStack s : OreDictionary.getOres("ingotSteel")) {
            RecipeGen.addRecipe(new ItemStack(ModItems.KingsHammer), "###", "*X*", " X ", '#', s, 'X',
                    ModItems.SteelRod, '*', ModItems.FireIngot);
            RecipeGen.addRecipe(new ItemStack(ModItems.Icepick), "###", " X ", " X ", '#', s, 'X',
                    ModItems.SteelRod);
            RecipeGen.addRecipe(new ItemStack(ModItems.BedrockHammer), "AFA", "SRS", " R ", 'S', s, 'R',
                    ModItems.SteelRod, 'F', ModItems.FireDust, 'A', ModItems.AquisIngot);
        }
        for (ItemStack s : OreDictionary.getOres("craftingToolForgeHammer")) {
            RecipeGen.addShapelessRecipe(new ItemStack(ModItems.IronDisc), Items.IRON_INGOT, new ItemStack(s.getItem(), 1, OreDictionary.WILDCARD_VALUE));
            RecipeGen.addShapelessRecipe(new ItemStack(ModItems.LeatherStrip, 2), Items.LEATHER, new ItemStack(s.getItem(), 1, OreDictionary.WILDCARD_VALUE));
            RecipeGen.addShapelessRecipe(new ItemStack(ModItems.EmeraldShard, 2), Items.EMERALD, new ItemStack(s.getItem(), 1, OreDictionary.WILDCARD_VALUE));
            RecipeGen.addShapelessRecipe(new ItemStack(ModItems.Guide), Items.BOOK, new ItemStack(s.getItem(), 1, OreDictionary.WILDCARD_VALUE));
        }
        for (ItemStack s : OreDictionary.getOres("logWood")) {
            RecipeGen.addRecipe(new ItemStack(ModBlocks.Drawer), "WCW", "R R", "WCW", 'W', s, 'C', Blocks.CHEST, 'R',
                    ModItems.RubyGem);
        }
        for (ItemStack s : OreDictionary.getOres("ingotCopper")) {
            RecipeGen.addRecipe(new ItemStack(ModItems.CopperPickaxe), "sss", " I ", " I ", 'I', Items.STICK, 's', s);
            RecipeGen.addRecipe(new ItemStack(ModItems.CopperAxe), "ss ", "sI ", " I ", 'I', Items.STICK, 's', s);
            RecipeGen.addRecipe(new ItemStack(ModItems.CopperShovel), " s ", " I ", " I ", 'I', Items.STICK, 's', s);
            RecipeGen.addRecipe(new ItemStack(ModItems.CopperHoe), "ss ", " I ", " I ", 'I', Items.STICK, 's', s);
            RecipeGen.addRecipe(new ItemStack(ModItems.CopperSword), " s ", " s ", " I ", 'I', Items.STICK, 's', s);
        }
        for (ItemStack s : OreDictionary.getOres("ingotBronze")) {
            RecipeGen.addRecipe(new ItemStack(ModItems.BronzePickaxe), "sss", " I ", " I ", 'I', Items.STICK, 's', s);
            RecipeGen.addRecipe(new ItemStack(ModItems.BronzeAxe), "ss ", "sI ", " I ", 'I', Items.STICK, 's', s);
            RecipeGen.addRecipe(new ItemStack(ModItems.BronzeShovel), " s ", " I ", " I ", 'I', Items.STICK, 's', s);
            RecipeGen.addRecipe(new ItemStack(ModItems.BronzeHoe), "ss ", " I ", " I ", 'I', Items.STICK, 's', s);
            RecipeGen.addRecipe(new ItemStack(ModItems.BronzeSword), " s ", " s ", " I ", 'I', Items.STICK, 's', s);
        }
    }

    private static void addInfuserRecipes() {
        // Crystals
//        InfuserCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.AquisCrystal), "X X", " # ", "X X", '#',
//                ModItems.AquisBoundCrystal, 'X', ModItems.RubyGem);
//        InfuserCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.FireCrystal), "X X", " # ", "X X", '#',
//                ModItems.FireBoundCrystal, 'X', ModItems.RubyGem);
//        InfuserCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.ShadowCrystal), "X X", " # ", "X X", '#',
//                ModItems.ShadowBoundCrystal, 'X', ModItems.RubyGem);
//        InfuserCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.DistortionCrystal), "X X", " # ", "X X",
//                '#', ModItems.DistortionBoundCrystal, 'X', ModItems.RubyGem);
//        InfuserCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.EmeraldCrystal), "X X", " # ", "X X", '#',
//                ModItems.EmeraldBoundCrystal, 'X', ModItems.RubyGem);
//        // Pellucidus
//        InfuserCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.SoldersPellucidus), "RER", "ASF", "RDR",
//                'R', ModItems.RubyGem, 'E', ModItems.EmeraldCrystal, 'F', ModItems.FireCrystal, 'A',
//                ModItems.AquisCrystal, 'S', ModItems.ShadowCrystal, 'D', ModItems.DistortionCrystal);
        // Imbued
        ImbuedCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.GobletOfFire), "BB ", "BB ", "F  ", 'B',
                Items.BLAZE_ROD, 'F', ModItems.FireCrystal);
        ImbuedCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.GobletOfIce), "IP ", "IP ", "A  ", 'I',
                Blocks.ICE, 'P', Blocks.PACKED_ICE, 'A', ModItems.AquisCrystal);
        ImbuedCraftingManager.getInstance().addRecipe(new ItemStack(ModItems.GobletOfEmerald), "EE ", "C  ", "EE ", 'E',
                Items.EMERALD, 'C', ModItems.EmeraldCrystal);
    }

    @Optional.Method(modid = "baubles")
    private static void addBaubleRecipes() {
        RecipeGen.addRecipe(new ItemStack(InitItems.GoldRing), "BLS", "G G", "SLB", 'B', ModItems.BronzeIngot, 'L', ModItems.LeatherStrip, 'G', Items.GOLD_INGOT, 'S', ModItems.SteelIngot);
        RecipeGen.addRecipe(new ItemStack(InitItems.AquisBelt), " C ", "L L", " L ", 'C', ModItems.AquisCrystal, 'L', ModItems.LeatherStrip);
        RecipeGen.addRecipe(new ItemStack(InitItems.ShadowBelt), " C ", "L L", " L ", 'C', ModItems.ShadowCrystal, 'L', ModItems.LeatherStrip);
        RecipeGen.addRecipe(new ItemStack(InitItems.DistortionBelt), " C ", "L L", " L ", 'C', ModItems.DistortionCrystal, 'L', ModItems.LeatherStrip);
        RecipeGen.addRecipe(new ItemStack(InitItems.EmeraldBelt), " C ", "L L", " L ", 'C', ModItems.EmeraldCrystal, 'L', ModItems.LeatherStrip);
        RecipeGen.addRecipe(new ItemStack(InitItems.FireBelt), " C ", "L L", " L ", 'C', ModItems.FireCrystal, 'L', ModItems.LeatherStrip);
        RecipeGen.addRecipe(new ItemStack(InitItems.BeltOfTranscendence), " C ", "L L", " L ", 'C', ModItems.SoldersPellucidus, 'L', ModItems.LeatherStrip);
        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.AquisCrystal), InitItems.AquisBelt);
        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.ShadowCrystal), InitItems.ShadowBelt);
        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.DistortionCrystal), InitItems.DistortionBelt);
        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.EmeraldCrystal), InitItems.EmeraldBelt);
        RecipeGen.addShapelessRecipe(new ItemStack(ModItems.FireCrystal), InitItems.FireBelt);
        //Ring
        RecipeGen.addRecipe(new ItemStack(InitItems.RingOfFlight), "RVR", "FGA", "RSR", 'R', ModItems.RubyGem, 'V', ModItems.VitaemIngot, 'F', ModItems.FloriumIngot, 'G', InitItems.FlyRing, 'A', ModItems.ArchiumIngot, 'S', ModItems.ShadowIngot);
    }
}
