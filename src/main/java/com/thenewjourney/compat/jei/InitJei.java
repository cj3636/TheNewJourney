package com.thenewjourney.compat.jei;

import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.blocks.arcane.ArcaneFurnaceGuiInventory;
import com.thenewjourney.blocks.blastfurnace.BlastFurnaceGuiInventory;
import com.thenewjourney.blocks.cauldron.ImbuedCraftingManager;
import com.thenewjourney.blocks.infuser.InfuserContainer;
import com.thenewjourney.blocks.infuser.InfuserCraftingManager;
import com.thenewjourney.blocks.infuser.InfuserGuiInventory;
import com.thenewjourney.blocks.purifier.PurifierGuiInventory;
import com.thenewjourney.compat.baubles.InitItems;
import com.thenewjourney.compat.jei.arcane.ArcaneCategory;
import com.thenewjourney.compat.jei.arcane.ArcaneCraftingManager;
import com.thenewjourney.compat.jei.blastfurnace.BlastCategory;
import com.thenewjourney.compat.jei.blastfurnace.BlastCraftingManager;
import com.thenewjourney.compat.jei.imbued.ImbuedCategory;
import com.thenewjourney.compat.jei.infuser.InfuserCategory;
import com.thenewjourney.compat.jei.purifier.PurifierCategory;
import com.thenewjourney.items.ModItems;
import com.thenewjourney.items.register.ItemRegistry;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Loader;

import javax.annotation.Nonnull;

@JEIPlugin
public class InitJei implements IModPlugin {
    @Override
    public void register(@Nonnull IModRegistry registry) {
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();
        IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
        //Register
        //Infuser

/*        registry.addRecipeHandlers(new InfuserHandler());
        //Blast

        registry.addRecipeHandlers(new BlastHandler());
        //Arcane

        registry.addRecipeHandlers(new ArcaneHandler());
        //Purifier

        registry.addRecipeHandlers(new PurifierHandler());
        //Imbued

        registry.addRecipeHandlers(new ImbuedHandler());*/
        //Crafting Recipes
        //Infuser
        registry.addRecipes(InfuserCraftingManager.getInstance().getRecipeList(), InfuserCategory.INFUSION);
        registry.addRecipeClickArea(InfuserGuiInventory.class, 1, 1, 64, 8, InfuserCategory.INFUSION);
        //Blast
        registry.addRecipes(BlastCraftingManager.getInstance().getRecipeList(), BlastCategory.BLASTFURNACE);
        registry.addRecipeClickArea(BlastFurnaceGuiInventory.class, 1, 1, 64, 8, BlastCategory.BLASTFURNACE);
        //Arcane
        registry.addRecipes(ArcaneCraftingManager.getInstance().getRecipeList(), ArcaneCategory.ArcaneFURNACE);
        registry.addRecipeClickArea(ArcaneFurnaceGuiInventory.class, 1, 1, 64, 8, ArcaneCategory.ArcaneFURNACE);
        //Purifier
        registry.addRecipeClickArea(PurifierGuiInventory.class, 1, 1, 64, 8, PurifierCategory.PurifierFURNACE);
        //Imbued
        registry.addRecipes(ImbuedCraftingManager.getInstance().getRecipeList(), ImbuedCategory.IMBUED);
        //Register stuff associated
        registry.addRecipeCatalyst(new ItemStack(ModItems.ArchaicInfuserI), InfuserCategory.INFUSION);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.BlastFurnace), BlastCategory.BLASTFURNACE);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.ArcaneFurnace), ArcaneCategory.ArcaneFURNACE);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.Purifier), PurifierCategory.PurifierFURNACE);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.ImbuedCauldron), ImbuedCategory.IMBUED);
        //WIP
        recipeTransferRegistry.addRecipeTransferHandler(InfuserContainer.class, InfuserCategory.INFUSION, 1, 9, 10, 36);
        //Descriptions
        registry.addIngredientInfo(new ItemStack(ModItems.CobaltIngot), ItemRegistry.class, TextFormatting.BLUE + "The new rock of a new journey.");
        registry.addIngredientInfo(new ItemStack(ModItems.RoyalJelly), ItemRegistry.class, TextFormatting.LIGHT_PURPLE + "Produced from a bee placed in an Apiary");
        registry.addIngredientInfo(new ItemStack(ModItems.AquisIngot), ItemRegistry.class, TextFormatting.AQUA + "I didn't think they could get any shinier!");
        registry.addIngredientInfo(new ItemStack(ModItems.BloodGem), ItemRegistry.class, TextFormatting.DARK_RED + "Deliver the finishing blow to a Zombie with a Diamond.");
        registry.addIngredientInfo(new ItemStack(ModItems.DistortionGem), ItemRegistry.class, "It doesn't shimmer...or\n" + TextFormatting.OBFUSCATED + "  ABCDEFGHIJ\n" + TextFormatting.OBFUSCATED + "   ABCDEFGHIJ/n" + TextFormatting.OBFUSCATED + " ABCDEFGHIJ\n" + TextFormatting.RESET + "What??");
        if (Loader.isModLoaded("Baubles")) {
            registry.addIngredientInfo(new ItemStack(InitItems.AquisBelt), ItemRegistry.class, TextFormatting.AQUA + "+ Water Breathing Lv. 4\n" + "- Weakness Lv. 2");
            registry.addIngredientInfo(new ItemStack(InitItems.DistortionBelt), ItemRegistry.class, TextFormatting.STRIKETHROUGH + "+ Regeneration Lv. 2\n" + "- Blindness 2s\n" + "* Addictive");
            registry.addIngredientInfo(new ItemStack(InitItems.EmeraldBelt), ItemRegistry.class, TextFormatting.GREEN + "+ Luck Lv. 1024\n" + "- Hunger Lv. 2");
            registry.addIngredientInfo(new ItemStack(InitItems.FireBelt), ItemRegistry.class, TextFormatting.DARK_RED + "+ Fire Resistance\n" + "* Hydrophobic");
            registry.addIngredientInfo(new ItemStack(InitItems.ShadowBelt), ItemRegistry.class, TextFormatting.ITALIC + "+ Invisibility\n" + "- Mining Fatigue Lv. 1");

            registry.addIngredientInfo(new ItemStack(InitItems.GoldRing), ItemRegistry.class, TextFormatting.GOLD + "+ Hyper Speed!\n" + "- Lifetime 200s");
            registry.addIngredientInfo(new ItemStack(InitItems.FlyRing), ItemRegistry.class, TextFormatting.BOLD + "+ Flying!\n" + "- Lifetime 1000s");
        }
        registry.addIngredientInfo(new ItemStack(ModBlocks.Purifier), ItemRegistry.class, "Duplicates all smelted items. Requires Crystal Power Tier x and speed is amplified by Crystal Power Number.");
        registry.addIngredientInfo(new ItemStack(ModItems.FireLight), ItemRegistry.class, "Brew a Fire Ingot onto a Splash Water Bottle.");
        registry.addIngredientInfo(new ItemStack(ModItems.AtriumLight), ItemRegistry.class, "Brew an Aquis Ingot onto a Splash Water Bottle.");
        registry.addIngredientInfo(new ItemStack(ModItems.ArcaneArtifact), ItemRegistry.class, "Can sometimes be found in Fire Ruin chests. Can be crafted by right-clicking a Bookshelf with a Distortion Wand.");
        registry.addIngredientInfo(new ItemStack(ModItems.UpgradeEight), ItemRegistry.class, "Arcane Pillar Infusion:" + "\n" + "Boreal Lamp + Archium Ingot + Vitaem Ingot + Florium Ingot + Crystal Binder");
        registry.addIngredientInfo(new ItemStack(ModItems.UpgradeEight), ItemRegistry.class, "Arcane Pillar Infusion:" + "\n" + "Boreal Lamp + Archium Ingot + Vitaem Ingot + Florium Ingot + Crystal Binder");

        registry.addIngredientInfo(new ItemStack(ModBlocks.VisceonCore), ItemRegistry.class, "Right click a face to extrude. Shift + Right Click with an empty hand to remove.");
        registry.addIngredientInfo(new ItemStack(ModBlocks.Substrate), ItemRegistry.class, "An orientable base used to create Visceon Portals and Visceon Magic Networks.");
        registry.addIngredientInfo(new ItemStack(ModBlocks.Substrate), ItemRegistry.class, TextFormatting.DARK_PURPLE + "Arcane Pillar Infusion:" + "\n" + "Warpstone + Warpstone + Warpstone + Warpstone + Aquis Ingot");
        registry.addIngredientInfo(new ItemStack(ModItems.DiviningRod), ItemRegistry.class, TextFormatting.DARK_GREEN + "Arcane Pillar Infusion:" + "\n" + "Ruby + Ruby + Carbon + Steel Rod + Spatium Powder");
        registry.addIngredientInfo(new ItemStack(ModItems.DiviningRod), ItemRegistry.class, TextFormatting.DARK_GREEN + "This rod will search the surrounding area for a block in the Player's Off-Hand. It then spits out the coordinates.");

        registry.addIngredientInfo(new ItemStack(ModItems.FireDust), ItemRegistry.class, TextFormatting.RED + "Dropped from a Fire Fern grown from Fire Seeds.");
        registry.addIngredientInfo(new ItemStack(ModItems.FireSeed), ItemRegistry.class, TextFormatting.RED + "Kill the King to obtain (Summoned by activating the King Block with an Aquis Ingot. Must be grown on arcane soil.");
        registry.addIngredientInfo(new ItemStack(ModBlocks.ArcaneSoil), ItemRegistry.class, TextFormatting.RED + "Use Spatium Powder on farmland to imbue it with magic.");
        registry.addIngredientInfo(new ItemStack(ModItems.SpatiumPowder), ItemRegistry.class, TextFormatting.RED + "Used in many recipes, turns farmland into Arcane Soil for growing Arcane Crops.");

        registry.addIngredientInfo(new ItemStack(ModBlocks.Quarry), ItemRegistry.class, TextFormatting.ITALIC + "Crystal Catalysm: 4 Frames and a Visceon Core.");

        registry.addIngredientInfo(new ItemStack(ModBlocks.StoneMan), ItemRegistry.class, TextFormatting.ITALIC + "Repels all hostile mobs in a 32 (ish) block radius");
        registry.addIngredientInfo(new ItemStack(ModBlocks.ImbuedCauldron), ItemRegistry.class, TextFormatting.ITALIC + "Activate a cauldron with Distortion Wand");

        registry.addIngredientInfo(new ItemStack(ModBlocks.Frame), ItemRegistry.class, "Right click with additional frames to make a tower. Shift + RClick to remove a tower. Also acts as a ladder. RClick while holding a stack of Florus to build the Compilable tower.");

        registry.addIngredientInfo(new ItemStack(ModBlocks.KingBlock), ItemRegistry.class, TextFormatting.GOLD + "These can be crafted at a hefty price or rarely found below Fire Ruins in the Fire Dimension. Necessary to summon the King. Activate with an Aquis Ingot. Be warned! The King is a worthy opponent.");

        registry.addIngredientInfo(new ItemStack(ModBlocks.CompressedFlorusBlock), ItemRegistry.class, TextFormatting.AQUA + "Activate a compiler that is placed on top of a tower of 8 frames, each frame surrounded by a ring of 8 florus.");
        registry.addIngredientInfo(new ItemStack(ModItems.FloricOrb), ItemRegistry.class, TextFormatting.AQUA + "Dropped from mining a Compressed Florus block.");

        registry.addIngredientInfo(new ItemStack(ModItems.InversionSerumBottle), ItemRegistry.class, TextFormatting.DARK_PURPLE + "In this highly volatile form, Inversion Serum can replace any block in the world. Be wary, this form of the substance is very unstable. You may experience item loss, item duplication, spontaneous damage, and more.\n" + TextFormatting.RESET
                + "Inversion recipes:\n"
                + TextFormatting.RED + "Fire Ingot" + TextFormatting.RESET + " <-> " + TextFormatting.AQUA + "Aquis Ingot" + TextFormatting.RESET + "\n"
                + TextFormatting.BLACK + "Burnt Stone" + TextFormatting.RESET + " -> " + TextFormatting.LIGHT_PURPLE + "Warp Stone" + TextFormatting.RESET + "\n"
                + TextFormatting.GRAY + "Chicken (Mob)" + TextFormatting.RESET + " -> " + TextFormatting.OBFUSCATED + "abcdefgh" + TextFormatting.RESET + "\n"
                + TextFormatting.RED + "Ruby" + TextFormatting.RESET + " -> " + TextFormatting.GREEN + TextFormatting.OBFUSCATED + "abcdefgh" + TextFormatting.RESET + "\n");

        registry.addIngredientInfo(new ItemStack(ModBlocks.ClearGlass), ItemRegistry.class, TextFormatting.WHITE + "Place Distorted Bricks above a fire and after a little while they will become Clear Glass.");
        registry.addIngredientInfo(new ItemStack(ModBlocks.WarpStone), ItemRegistry.class, TextFormatting.DARK_PURPLE + "Inversion Serum:" + "\n" + "You can Invert Stone into Warpstone.");
        registry.addIngredientInfo(new ItemStack(ModItems.ArchaicOrb), ItemRegistry.class, TextFormatting.DARK_PURPLE + "Arcane Pillar Infusion:" + "\n" + "Ancient Ingot + Binding Powder + Binding Powder + Fire Gem");
        registry.addIngredientInfo(new ItemStack(ModBlocks.VisceonCore), ItemRegistry.class, TextFormatting.DARK_PURPLE + "Arcane Pillar Infusion:" + "\n" + "Warpstone + Warpstone + Warpstone + Warpstone + Shadow Crystal");

        registry.addIngredientInfo(new ItemStack(ModItems.DistortionWand), ItemRegistry.class, TextFormatting.DARK_GRAY
                + "The primary magic device of The New Journey" + "\n"
                + "Right clicking/activating a block will summon an Irredescent Lightning Strike." + "\n"
                + "Activating a Cauldron will make it Imbued" + "\n"
                + "Activating a Jack'o'Lantern will summon a stone man if setup correctly (Burnt Stone Body and Nether Fence arms)" + "\n"
                + "RClick on a bookshelf to craft an Arcane Artifact" + "\n"
                + "Activating the Distributor block in an Inverion Cauldron setup will generate the Serum" + "\n");

        registry.addIngredientInfo(new ItemStack(ModBlocks.ArcanePillar), ItemRegistry.class, TextFormatting.DARK_GRAY
                + "Primarily used for Arcane Pillar Infusion." + "\n"
                + "Can be used to display items as decoration" + "\n"
                + "WARNING: Any item dropped near this block will be deleted! [WIP]" + "\n"
                + "Right Click with an item in hand to place it in the inventory" + "\n"
                + "Right Clicke with an empty hand to remove it from the inventory" + "\n");

        registry.addIngredientInfo(new ItemStack(ModItems.AquisStaff), ItemRegistry.class, TextFormatting.BLUE
                + "Actions:" + "\n"
                + "Shift: Place water up to 75 blocks away" + "\n"
                + "Ctrl: Launch the player" + "\n"
                + "BR: Mine a 3x3 area" + "\n"
                + "BAlt: 150 Ticks of Invincibility" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.ShadowStaff), ItemRegistry.class, TextFormatting.BLACK
                + "Actions:" + "\n"
                + "R: Boost the player to highest block above them" + "\n"
                + "Shift: Strike Down A powerful beam of Irredescent Lightning" + "\n"
                + "Ctrl: Speed + Invisibility" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.BabylonWand), ItemRegistry.class, TextFormatting.DARK_PURPLE
                + "Actions:" + "\n"
                + "R: Shoot a fireball" + "\n"
                + "BShift: Set a block on fire" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.ZirconiumStaff), ItemRegistry.class, TextFormatting.DARK_PURPLE
                + "Actions:" + "\n"
                + "R: Shoot a Floruc Fireball (creates an area of freezing)" + "\n"
                + "Shift: Shoot an explosive wither skull" + "\n"
                + "Alt: Place a glass block under the player" + "\n"
                + "BAlt: 225 Ticks of Invincibility" + "\n"
                + "+: Allows the player to fly when held)" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.SwordOfTheAlti), ItemRegistry.class, TextFormatting.DARK_PURPLE
                + "Actions:" + "\n"
                + "R: Currently does nothing" + "\n"
                + "Shift: Launches the player backwards" + "\n"
                + "Ctrl: Return to the player's bed" + "\n"
                + "BR: Elevates the player and block up 1" + "\n"
                + "BAlt: Activates an extra 4 hearts of health" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.FlorucBattleAxe), ItemRegistry.class, TextFormatting.AQUA
                + "Actions:" + "\n"
                + "BR: Smash the ground with the hammer, throwing all entities away and boosting the players health. The player cannot move for a moment after use." + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.FlorucSword), ItemRegistry.class, TextFormatting.AQUA
                + "Actions:" + "\n"
                + "Shift: Cures all status effects" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.FloriumAxe), ItemRegistry.class, TextFormatting.AQUA
                + "Actions:" + "\n"
                + "R: Throws a Splash Potion of Slowness and Weakness and gives the player 2.5 hearts of absorption" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.SwordOfFlorus), ItemRegistry.class, TextFormatting.AQUA
                + "Actions:" + "\n"
                + "Shift: Summons a floric spirit (at the cost of a crystal binder)" + "\n"
                + "Ctrl: Gives the player a speed boost for 30 seconds" + "\n"
                + "BAlt: Heals the player 1 heart)" + "\n"
                + "BR: Elevates the player and block up 1" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.GreenBlade), ItemRegistry.class, TextFormatting.GREEN
                + "Actions:" + "\n"
                + "+: Gives the player strength and resistance when held" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.VitraecBow), ItemRegistry.class, TextFormatting.GREEN
                + "Actions:" + "\n"
                + "Shift: Shoots a powerful arrow (9 damage)" + "\n"
                + "Ctrl: Shoots punch arrows (5 damage)" + "\n"
                + "Alt: Shoots flame arrows (7 damage)" + "\n"
                + "+: Deals double the damage of a normal bow" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.VitraemSaber), ItemRegistry.class, TextFormatting.GREEN
                + "Actions:" + "\n"
                + "Shift: Throws a splash potion of Poison II" + "\n"
                + "Ctrl: Gives the player a speed boost for 30 seconds" + "\n"
                + "BR: Elevates the player and block up 1" + "\n"
                + "BAlt: Heals the player 1 heart)" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.Goblet), ItemRegistry.class, TextFormatting.GOLD + "Right click on a correctly brewed Imbued Cauldron to collect the essence.");
        registry.addIngredientInfo(new ItemStack(ModItems.Claw), ItemRegistry.class, TextFormatting.YELLOW + "Used to collect Bee's from their hive in order to produce Royal Jelly in an Apiary.");
        registry.addIngredientInfo(new ItemStack(ModItems.NarcoBerry), ItemRegistry.class, TextFormatting.DARK_BLUE
                + "Will severely poison the player if eaten." + "\n"
                + "Hold this in the offhand with a Pestle in your mainhand. Activate a Mortar to grind the berries into Narcotics. 16 Berries is 1 Narcotic." + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.Narcotic), ItemRegistry.class, TextFormatting.DARK_GREEN
                + "Made by grinding Narco Berries at a Mortar with a Pestle" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.Pestal), ItemRegistry.class, TextFormatting.GRAY
                + "Can grind Narco Berries into Narcotics at a Mortar" + "\n");
        registry.addIngredientInfo(new ItemStack(ModBlocks.Mortar), ItemRegistry.class, TextFormatting.DARK_GRAY
                + "Use a Pestle (Mainhand) and Narco berries (Offhand) at a Mortar to make Narcotics" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.NarcoBerrySeed), ItemRegistry.class, TextFormatting.DARK_PURPLE
                + "Place on Grass to grow a Narco Berry Bush" + "\n");
        registry.addIngredientInfo(new ItemStack(ModBlocks.Idol), ItemRegistry.class, TextFormatting.LIGHT_PURPLE
                + "Found in abandoned ships above Fire Ruins in the Florus Dimension" + "\n"
                + "Used to produce Quazan Scales. See Guide: Quazan Scale for more information" + "\n");
        registry.addIngredientInfo(new ItemStack(ModBlocks.CrystalProvider), ItemRegistry.class, TextFormatting.LIGHT_PURPLE
                + "Used to provide power to an Idol in order to produce Quazan Scales. See Guide: Quazan Scale for more information" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.DimensionChanger), ItemRegistry.class, TextFormatting.BOLD
                + "Actions: (On A Block)" + "\n"
                + "Right: Teleport to Fire Dimension" + "\n"
                + "Shift: Teleport to Florus Dimension" + "\n"
                + "Alt: Teleport to Nether" + "\n"
                + "Ctrl: Teleport to End" + "\n"
                + TextFormatting.RED + "NOT SAFE FOR USE IN SURVIVAL");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new PurifierCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new ImbuedCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new ArcaneCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new BlastCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new InfuserCategory(registry.getJeiHelpers().getGuiHelper()));
    }


}