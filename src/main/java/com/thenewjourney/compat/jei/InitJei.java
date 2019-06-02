package com.thenewjourney.compat.jei;

import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.blocks.arcane.ArcaneFurnaceGuiInventory;
import com.thenewjourney.blocks.blastfurnace.BlastFurnaceGuiInventory;
import com.thenewjourney.blocks.cauldron.ImbuedCraftingManager;
import com.thenewjourney.blocks.infuser.InfuserContainer;
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
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.VanillaTypes;
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

/*      registry.addRecipeHandlers(new InfuserHandler());
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
        //registry.addRecipes(InfuserCraftingManager, InfuserCategory.INFUSION);
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
        registry.addIngredientInfo(new ItemStack(ModItems.CobaltIngot), VanillaTypes.ITEM, TextFormatting.BLUE + "The new rock of a new journey.");
        registry.addIngredientInfo(new ItemStack(ModItems.RoyalJelly), VanillaTypes.ITEM, TextFormatting.LIGHT_PURPLE + "Produced from a bee placed in an Apiary");
        registry.addIngredientInfo(new ItemStack(ModItems.AquisIngot), VanillaTypes.ITEM, TextFormatting.AQUA + "I didn't think they could get any shinier!");
        registry.addIngredientInfo(new ItemStack(ModItems.BloodGem), VanillaTypes.ITEM, TextFormatting.DARK_RED + "Deliver the finishing blow to a Zombie with a Diamond in your hand.");
        registry.addIngredientInfo(new ItemStack(ModItems.DistortionGem), VanillaTypes.ITEM, "It doesn't shimmer...or\n" + TextFormatting.OBFUSCATED + "  ABCDEFGHIJ\n" + TextFormatting.OBFUSCATED + "   ABCDEFGHIJ\n" + TextFormatting.OBFUSCATED + " ABCDEFGHIJ\n" + TextFormatting.RESET + "What??");
        if (Loader.isModLoaded("Baubles")) {
            registry.addIngredientInfo(new ItemStack(InitItems.AquisBelt), VanillaTypes.ITEM, TextFormatting.AQUA + "+ Water Breathing Lv. 4\n" + "- Weakness Lv. 2");
            registry.addIngredientInfo(new ItemStack(InitItems.DistortionBelt), VanillaTypes.ITEM, TextFormatting.STRIKETHROUGH + "+ Regeneration Lv. 2\n" + "- Blindness 2s\n" + "* Addictive");
            registry.addIngredientInfo(new ItemStack(InitItems.EmeraldBelt), VanillaTypes.ITEM, TextFormatting.GREEN + "+ Luck Lv. 1024\n" + "- Hunger Lv. 2");
            registry.addIngredientInfo(new ItemStack(InitItems.FireBelt), VanillaTypes.ITEM, TextFormatting.DARK_RED + "+ Fire Resistance\n" + "* Hydrophobic");
            registry.addIngredientInfo(new ItemStack(InitItems.ShadowBelt), VanillaTypes.ITEM, TextFormatting.ITALIC + "+ Invisibility\n" + "- Mining Fatigue Lv. 1");

            registry.addIngredientInfo(new ItemStack(InitItems.GoldRing), VanillaTypes.ITEM, TextFormatting.GOLD + "+ Hyper Speed!\n" + "- Lifetime 200s");
            registry.addIngredientInfo(new ItemStack(InitItems.FlyRing), VanillaTypes.ITEM, TextFormatting.BOLD + "+ Flying!\n" + "- Lifetime 1000s");
        }
        registry.addIngredientInfo(new ItemStack(ModBlocks.Purifier), VanillaTypes.ITEM, "Duplicates all smelted items. Requires Crystal Power Tier 5 and speed is amplified by Crystal Power Number.");
        registry.addIngredientInfo(new ItemStack(ModItems.FireLight), VanillaTypes.ITEM, "Brew a Fire Ingot onto any Splash Potion.");
        registry.addIngredientInfo(new ItemStack(ModItems.AtriumLight), VanillaTypes.ITEM, "Brew an Aquis Ingot onto any Splash Potion.");
        registry.addIngredientInfo(new ItemStack(ModItems.ArcaneArtifact), VanillaTypes.ITEM, "Can sometimes be found in Fire Ruin chests. Can be crafted by right-clicking a Bookshelf with a Distortion Wand.");
        registry.addIngredientInfo(new ItemStack(ModItems.UpgradeEight), VanillaTypes.ITEM, "Arcane Pillar Infusion:" + "\n" + "Boreal Lamp + Archium Ingot + Vitaem Ingot + Florium Ingot + Crystal Binder");
        registry.addIngredientInfo(new ItemStack(ModItems.UpgradeEight), VanillaTypes.ITEM, "Arcane Pillar Infusion:" + "\n" + "Boreal Lamp + Archium Ingot + Vitaem Ingot + Florium Ingot + Crystal Binder");

        registry.addIngredientInfo(new ItemStack(ModBlocks.VisceonCore), VanillaTypes.ITEM, "Right click a face to extrude. Shift + Right Click with an empty hand to remove.");
        registry.addIngredientInfo(new ItemStack(ModBlocks.Substrate), VanillaTypes.ITEM, "An orientable base used to create Visceon Portals and Visceon Magic Networks.");
        registry.addIngredientInfo(new ItemStack(ModBlocks.Substrate), VanillaTypes.ITEM, TextFormatting.DARK_PURPLE + "Arcane Pillar Infusion:" + "\n" + "Warpstone + Warpstone + Warpstone + Warpstone + Aquis Ingot");
        registry.addIngredientInfo(new ItemStack(ModItems.DiviningRod), VanillaTypes.ITEM, TextFormatting.DARK_GREEN + "Arcane Pillar Infusion:" + "\n" + "Ruby + Ruby + Carbon + Steel Rod + Spatium Powder");
        registry.addIngredientInfo(new ItemStack(ModItems.DiviningRod), VanillaTypes.ITEM, TextFormatting.DARK_GREEN + "This rod will search the surrounding area for a block in the Player's Off-Hand. It then spits out the coordinates.");

        registry.addIngredientInfo(new ItemStack(ModItems.FireDust), VanillaTypes.ITEM, TextFormatting.RED + "Dropped from a Fire Fern grown from Fire Seeds.");
        registry.addIngredientInfo(new ItemStack(ModItems.FireSeed), VanillaTypes.ITEM, TextFormatting.RED + "Kill the King to obtain (Summoned by activating the King Block with an Aquis Ingot. Must be grown on arcane soil.");
        registry.addIngredientInfo(new ItemStack(ModBlocks.ArcaneSoil), VanillaTypes.ITEM, TextFormatting.RED + "Use Spatium Powder on farmland to imbue it with magic.");
        registry.addIngredientInfo(new ItemStack(ModItems.SpatiumPowder), VanillaTypes.ITEM, TextFormatting.RED + "Used in many recipes, turns farmland into Arcane Soil for growing Arcane Crops.");

        registry.addIngredientInfo(new ItemStack(ModBlocks.Quarry), VanillaTypes.ITEM, TextFormatting.ITALIC + "Arcane Pillar Infusion:" + "\n" + "4 Frames and a Visceon Core" + "\n" + "This block will mine for all ores (and more). It searches the surrounding 16 blocks down to bedrock. Requires tier 5 power and needs a Crystal Binder to fuel.");

        registry.addIngredientInfo(new ItemStack(ModBlocks.StoneMan), VanillaTypes.ITEM, TextFormatting.ITALIC + "Repels all hostile mobs in a 32 (ish) block radius");
        registry.addIngredientInfo(new ItemStack(ModBlocks.ImbuedCauldron), VanillaTypes.ITEM, TextFormatting.ITALIC + "Activate a cauldron with Distortion Wand");

        registry.addIngredientInfo(new ItemStack(ModBlocks.Frame), VanillaTypes.ITEM, "Right click with additional frames to make a tower. Shift + RClick to remove a tower. Also acts as a ladder. RClick while holding a stack of Florus to build the Compilable tower.");

        registry.addIngredientInfo(new ItemStack(ModBlocks.KingBlock), VanillaTypes.ITEM, TextFormatting.GOLD + "These can be crafted at a hefty price or rarely found below Fire Ruins in the Fire Dimension. Necessary to summon the King. Activate with an Aquis Ingot. Be warned! The King is a worthy opponent.");

        registry.addIngredientInfo(new ItemStack(ModBlocks.CompressedFlorusBlock), VanillaTypes.ITEM, TextFormatting.AQUA + "Activate a compiler that is placed on top of a tower of 8 frames, each frame surrounded by a ring of 8 florus.");
        registry.addIngredientInfo(new ItemStack(ModItems.FloricOrb), VanillaTypes.ITEM, TextFormatting.AQUA + "Dropped from mining a Compressed Florus block.");

        registry.addIngredientInfo(new ItemStack(ModItems.InversionSerumBottle), VanillaTypes.ITEM, TextFormatting.DARK_PURPLE + "In this highly volatile form, Inversion Serum can replace any block in the world. Be wary, this form of the substance is very unstable. You may experience item loss, item duplication, spontaneous damage, and more.\n" + TextFormatting.RESET
                + "Inversion recipes:\n"
                + TextFormatting.RED + "Fire Ingot" + TextFormatting.RESET + " <-> " + TextFormatting.AQUA + "Aquis Ingot" + TextFormatting.RESET + "\n"
                + TextFormatting.BLACK + "Burnt Stone" + TextFormatting.RESET + " -> " + TextFormatting.LIGHT_PURPLE + "Warp Stone" + TextFormatting.RESET + "\n"
                + TextFormatting.GRAY + "Chicken (Mob)" + TextFormatting.RESET + " -> " + TextFormatting.OBFUSCATED + "abcdefgh" + TextFormatting.RESET + "\n"
                + TextFormatting.RED + "Ruby" + TextFormatting.RESET + " -> " + TextFormatting.GREEN + TextFormatting.OBFUSCATED + "abcdefgh" + TextFormatting.RESET + "\n");

        registry.addIngredientInfo(new ItemStack(ModBlocks.ClearGlass), VanillaTypes.ITEM, TextFormatting.WHITE + "Place Distorted Bricks above a fire and after a little while they will become Clear Glass.");
        registry.addIngredientInfo(new ItemStack(ModBlocks.WarpStone), VanillaTypes.ITEM, TextFormatting.DARK_PURPLE + "Inversion Serum:" + "\n" + "You can Invert Stone into Warpstone.");
        registry.addIngredientInfo(new ItemStack(ModItems.ArchaicOrb), VanillaTypes.ITEM, TextFormatting.DARK_PURPLE + "Arcane Pillar Infusion:" + "\n" + "Ancient Ingot + Binding Powder + Binding Powder + Fire Gem");
        registry.addIngredientInfo(new ItemStack(ModBlocks.VisceonCore), VanillaTypes.ITEM, TextFormatting.DARK_PURPLE + "Arcane Pillar Infusion:" + "\n" + "Warpstone + Warpstone + Warpstone + Warpstone + Shadow Crystal");

        registry.addIngredientInfo(new ItemStack(ModItems.DistortionWand), VanillaTypes.ITEM, TextFormatting.DARK_GRAY
                + "The primary magic device of The New Journey" + "\n"
                + "Right clicking/activating a block will summon an Irredescent Lightning Strike." + "\n"
                + "Activating a Cauldron will make it Imbued" + "\n"
                + "Activating a Jack'o'Lantern will summon a stone man if setup correctly (Burnt Stone Body and Nether Fence arms)" + "\n"
                + "RClick on a bookshelf to craft an Arcane Artifact" + "\n"
                + "Activating the Distributor block in an Inverion Cauldron setup will generate the Serum" + "\n");

        registry.addIngredientInfo(new ItemStack(ModBlocks.ArcanePillar), VanillaTypes.ITEM, TextFormatting.DARK_GRAY
                + "Primarily used for Arcane Pillar Infusion." + "\n"
                + "Can be used to display items as decoration" + "\n"
                + "WARNING: Any item dropped near this block will be deleted! [WIP]" + "\n"
                + "Right Click with an item in hand to place it in the inventory" + "\n"
                + "Right Clicke with an empty hand to remove it from the inventory" + "\n");

        registry.addIngredientInfo(new ItemStack(ModItems.AquisStaff), VanillaTypes.ITEM, TextFormatting.BLUE
                + "Actions:" + "\n"
                + "Shift: Place water up to 75 blocks away" + "\n"
                + "Ctrl: Launch the player" + "\n"
                + "BR: Mine a 3x3 area" + "\n"
                + "BAlt: 150 Ticks of Invincibility" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.ShadowStaff), VanillaTypes.ITEM, TextFormatting.BLACK
                + "Actions:" + "\n"
                + "R: Boost the player to highest block above them" + "\n"
                + "Shift: Strike Down A powerful beam of Irredescent Lightning" + "\n"
                + "Ctrl: Speed + Invisibility" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.BabylonWand), VanillaTypes.ITEM, TextFormatting.DARK_PURPLE
                + "Actions:" + "\n"
                + "R: Shoot a fireball" + "\n"
                + "BShift: Set a block on fire" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.ZirconiumStaff), VanillaTypes.ITEM, TextFormatting.DARK_PURPLE
                + "Actions:" + "\n"
                + "R: Shoot a Floruc Fireball (creates an area of freezing)" + "\n"
                + "Shift: Shoot an explosive wither skull" + "\n"
                + "Alt: Place a glass block under the player" + "\n"
                + "BAlt: 225 Ticks of Invincibility" + "\n"
                + "+: Allows the player to fly when held)" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.SwordOfTheAlti), VanillaTypes.ITEM, TextFormatting.DARK_PURPLE
                + "Actions:" + "\n"
                + "R: Currently does nothing" + "\n"
                + "Shift: Launches the player backwards" + "\n"
                + "Ctrl: Return to the player's bed" + "\n"
                + "BR: Elevates the player and block up 1" + "\n"
                + "BAlt: Activates an extra 4 hearts of health" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.FlorucBattleAxe), VanillaTypes.ITEM, TextFormatting.AQUA
                + "Actions:" + "\n"
                + "BR: Smash the ground with the hammer, throwing all entities away and boosting the players health. The player cannot move for a moment after use." + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.FlorucSword), VanillaTypes.ITEM, TextFormatting.AQUA
                + "Actions:" + "\n"
                + "Shift: Cures all status effects" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.FloriumAxe), VanillaTypes.ITEM, TextFormatting.AQUA
                + "Actions:" + "\n"
                + "R: Throws a Splash Potion of Slowness and Weakness and gives the player 2.5 hearts of absorption" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.SwordOfFlorus), VanillaTypes.ITEM, TextFormatting.AQUA
                + "Actions:" + "\n"
                + "Shift: Summons a floric spirit (at the cost of a crystal binder)" + "\n"
                + "Ctrl: Gives the player a speed boost for 30 seconds" + "\n"
                + "BAlt: Heals the player 1 heart)" + "\n"
                + "BR: Elevates the player and block up 1" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.GreenBlade), VanillaTypes.ITEM, TextFormatting.GREEN
                + "Actions:" + "\n"
                + "+: Gives the player strength and resistance when held" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.VitraecBow), VanillaTypes.ITEM, TextFormatting.GREEN
                + "Actions:" + "\n"
                + "Shift: Shoots a powerful arrow (9 damage)" + "\n"
                + "Ctrl: Shoots punch arrows (5 damage)" + "\n"
                + "Alt: Shoots flame arrows (7 damage)" + "\n"
                + "+: Deals double the damage of a normal bow" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.VitraemSaber), VanillaTypes.ITEM, TextFormatting.GREEN
                + "Actions:" + "\n"
                + "Shift: Throws a splash potion of Poison II" + "\n"
                + "Ctrl: Gives the player a speed boost for 30 seconds" + "\n"
                + "BR: Elevates the player and block up 1" + "\n"
                + "BAlt: Heals the player 1 heart)" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.Goblet), VanillaTypes.ITEM, TextFormatting.GOLD + "Right click on a correctly brewed Imbued Cauldron to collect the essence.");
        registry.addIngredientInfo(new ItemStack(ModItems.Claw), VanillaTypes.ITEM, TextFormatting.YELLOW + "Used to collect Bee's from their hive in order to produce Royal Jelly in an Apiary.");
        registry.addIngredientInfo(new ItemStack(ModItems.Claw), VanillaTypes.ITEM, TextFormatting.YELLOW + "Used to collect Bee's from their hive in order to produce Royal Jelly in an Apiary.");
        registry.addIngredientInfo(new ItemStack(ModItems.NarcoBerry), VanillaTypes.ITEM, TextFormatting.DARK_BLUE
                + "Will severely poison the player if eaten." + "\n"
                + "Hold this in the Offhand with a Pestle in your Mainhand. Activate a Mortar to grind the berries into Narcotics. 16 Berries is 1 Narcotic." + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.Narcotic), VanillaTypes.ITEM, TextFormatting.DARK_GREEN
                + "Made by grinding Narco Berries at a Mortar with a Pestle" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.Pestle), VanillaTypes.ITEM, TextFormatting.GRAY
                + "Use a Pestle (Mainhand) and Narco berries (Offhand) at a Mortar to make Narcotics" + "\n");
        registry.addIngredientInfo(new ItemStack(ModBlocks.Mortar), VanillaTypes.ITEM, TextFormatting.DARK_GRAY
                + "Use a Pestle (Mainhand) and Narco berries (Offhand) at a Mortar to make Narcotics" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.NarcoBerrySeed), VanillaTypes.ITEM, TextFormatting.DARK_PURPLE
                + "Place on Grass to grow a Narco Berry Bush" + "\n");
        registry.addIngredientInfo(new ItemStack(ModBlocks.Idol), VanillaTypes.ITEM, TextFormatting.LIGHT_PURPLE
                + "Found in abandoned ships above Fire Ruins in the Florus Dimension." + "\n"
                + "Used to produce Quazan Scales." + "\n" +
                "See Guide: 'Constructs' for more information" + "\n");
        registry.addIngredientInfo(new ItemStack(ModBlocks.CrystalProvider), VanillaTypes.ITEM, TextFormatting.LIGHT_PURPLE
                + "Used to provide power to an Idol in order to produce Quazan Scales. See Guide: Quazan Scale for more information" + "\n");
        registry.addIngredientInfo(new ItemStack(ModItems.DimensionChanger), VanillaTypes.ITEM, TextFormatting.BOLD
                + "Actions: (On A Block)" + "\n"
                + "Right: Teleport to Fire Dimension" + "\n"
                + "Shift: Teleport to Florus Dimension" + "\n"
                + "Alt: Teleport to Nether" + "\n"
                + "Ctrl: Teleport to End" + "\n"
                + TextFormatting.RED + "NOT SAFE FOR USE IN SURVIVAL");
        registry.addIngredientInfo(new ItemStack(ModBlocks.Crystal), VanillaTypes.ITEM, TextFormatting.BOLD
                + "The center of Visceon magic," + "\n"
                + "Each of these blocks provide power to your world." + "\n"
                + "Clicking the block with an empty hand displays the current 'World Power' (Number of these blocks you have placed), as well as the 'Power Tier'." + "\n"
                + "By using 'Upgrade Ions' you can upgrade your power tier." + "\n"
                + "Each magic machine in TNJ requires different amounts of Crystals to function and a certain power tier to unlock" + "\n"
                + "Most machines speed can be increased by increasing the number of Crystals placed in the world." + "\n"
                + TextFormatting.RED + "The Visceon Gods of magic hold any magic user in high regard. By no means can you share your magical abilities with another player. You may however, give them the tools to do it themselves." + "\n"
                + TextFormatting.RED + "It should be noted that a Crystal can ONLY be placed into the world or removed from the world by a player. Any other methods WILL crash the game. I have done my best to prevent this, but best of luck!");
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