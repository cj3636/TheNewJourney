package com.thenewjourney.Main;

import com.cj3636.lib.Config;
import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.blocks.apiary.ApiaryGUIRegistry;
import com.thenewjourney.blocks.apiary.ApiaryTileEntity;
import com.thenewjourney.blocks.arcane.ArcaneFurnaceGUIRegistry;
import com.thenewjourney.blocks.arcane.ArcaneFurnaceTileEntity;
import com.thenewjourney.blocks.blastfurnace.BlastFurnaceGUIRegistry;
import com.thenewjourney.blocks.blastfurnace.BlastFurnaceTileEntity;
import com.thenewjourney.blocks.bush.BerryBushTileEntity;
import com.thenewjourney.blocks.cauldron.ImbuedCaulronTileEntity;
import com.thenewjourney.blocks.crystal.CrystalTileEntity;
import com.thenewjourney.blocks.distortedbricks.TileEntityDistortedBricks;
import com.thenewjourney.blocks.drawer.DrawerGUIRegistry;
import com.thenewjourney.blocks.drawer.DrawerTileEntity;
import com.thenewjourney.blocks.duplicator.DuplicatorGUIRegistry;
import com.thenewjourney.blocks.duplicator.DuplicatorTileEntity;
import com.thenewjourney.blocks.furnace.tileentity.*;
import com.thenewjourney.blocks.idol.IdolTileEntity;
import com.thenewjourney.blocks.infuser.InfuserGUIRegistry;
import com.thenewjourney.blocks.infuser.InfuserTileEntity;
import com.thenewjourney.blocks.infuser.InfusionCraftingHandler;
import com.thenewjourney.blocks.pervateki.ForgeBlockTileEntity;
import com.thenewjourney.blocks.pillar.ArcanePillarGUIRegistry;
import com.thenewjourney.blocks.pillar.ArcanePillarTileEntity;
import com.thenewjourney.blocks.provider.CrystalProviderTileEntity;
import com.thenewjourney.blocks.purifier.PurifierGUIRegistry;
import com.thenewjourney.blocks.purifier.PurifierTileEntity;
import com.thenewjourney.blocks.stoneman.StoneManTileEntity;
import com.thenewjourney.compat.baubles.InitItems;
import com.thenewjourney.dimension.ModDimension;
import com.thenewjourney.entity.bluefireball.FlorucFireballEntity;
import com.thenewjourney.entity.effectcloud.EffectCloudFlorusEntity;
import com.thenewjourney.entity.effectcloud.EffectCloudVitaemEntity;
import com.thenewjourney.entity.grenade.GrenadeEntity;
import com.thenewjourney.entity.king.KingEntity;
import com.thenewjourney.entity.rift.RiftEntity;
import com.thenewjourney.entity.vitraecstar.VitraecStarEntity;
import com.thenewjourney.fluid.InversionTileEntity;
import com.thenewjourney.guide.GuideGUIRegistry;
import com.thenewjourney.handler.*;
import com.thenewjourney.items.ModItems;
import com.thenewjourney.items.backpack.BackpackGUIRegistry;
import com.thenewjourney.items.tranquilizer.EntityTranquilizerArrow;
import com.thenewjourney.misc.RecipeManager;
import com.thenewjourney.misc.TheNewJourneyTab;
import com.thenewjourney.misc.TheNewJourneyWeaponTab;
import com.thenewjourney.sound.ModSounds;
import com.thenewjourney.world.OreGenerator;
import com.thenewjourney.world.SingleOreGenerator;
import com.thenewjourney.world.StructureGenerator;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {
    public static CreativeTabs TheNewJourneyTab = new TheNewJourneyTab("customtab");
    public static CreativeTabs TheNewJourneyWeaponTab = new TheNewJourneyWeaponTab("customwtab");

    public void preInit(FMLPreInitializationEvent e) {
        //Main Stuff
        ModSounds.mainRegistry();
        ModItems.mainRegistry();
        ModBlocks.mainRegistry();
        //Entities
        registerEntity(KingEntity.class, "King", 1, 50, 10, 1);
        registerEntityNoEgg(GrenadeEntity.class, "Grenade", 2, 50);
        registerEntityNoEgg(RiftEntity.class, "Rift", 3, 50);
        registerEntityNoEgg(EntityTranquilizerArrow.class, "TranquilizerArrow", 4, 50);
        registerEntityNoEgg(FlorucFireballEntity.class, "FlorucFireball", 5, 50);
        registerEntityNoEgg(VitraecStarEntity.class, "VitraecStar", 6, 50);
        registerEntityNoEgg(EffectCloudFlorusEntity.class, "EffectCloudEntity", 7, 50);
        registerEntityNoEgg(EffectCloudVitaemEntity.class, "EffectCloudVitaemEntity", 8, 50);
        //EntityRegistry.addSpawn(KingEntity.class, Config.kingSpawn, 1, 2, EnumCreatureType.AMBIENT, new Biome[] { Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS});
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModBlocks.Block_Item_List.toArray(new Item[0]));
        event.getRegistry().registerAll(ModItems.Item_List.toArray(new Item[0]));
        //Compat
        if (Loader.isModLoaded("baubles")) {
            InitItems.mainRegistry();
            event.getRegistry().registerAll(InitItems.Bauble_Item_List.toArray(new Item[0]));
            String s = "\u001B[36m" + "[The New Journey] Baubles Compat Loaded!" + "\u001B[0m";
            System.out.println(s);
        } else {
            String s = "\u001B[31m" + "[The New Journey] No Baubles found!" + "\u001B[0m";
            System.out.println(s);
        }
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(ModBlocks.Block_List.toArray(new Block[0]));
    }

    private static void registerEntity(Class<? extends Entity> entity, String name, int id, int range, int color1, int color2) {
        EntityRegistry.registerModEntity(new ResourceLocation(Ref.MODID + ":" + name), entity, name, id, TheNewJourneyMod.instance, range, 1, true, color1, color2);
    }

    private static void registerEntityNoEgg(Class<? extends Entity> entity, String name, int id, int range) {
        EntityRegistry.registerModEntity(new ResourceLocation(Ref.MODID + ":" + name), entity, name, id, TheNewJourneyMod.instance, range, 1, true);
    }

    public void init(FMLInitializationEvent e) {
        //Achievements
        //ModAchievements.mainRegistry();
        //Event Handlers
        MinecraftForge.EVENT_BUS.register(new ToolCraftingHandler());
        MinecraftForge.EVENT_BUS.register(new GlassBreakerHandler());
        MinecraftForge.EVENT_BUS.register(new EntityAttackHandler());
        MinecraftForge.EVENT_BUS.register(new InfusionCraftingHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerLoggedInHandler());
        MinecraftForge.EVENT_BUS.register(new LootHandler());
        //Recipes
        RecipeManager.mainRegistry();
        //TileEntities
        GameRegistry.registerTileEntity(GraniteFurnaceTileEntity.class, new ResourceLocation(Ref.MODID, "GraniteFurnaceTileEntity"));
        GameRegistry.registerTileEntity(AndesiteFurnaceTileEntity.class, new ResourceLocation(Ref.MODID, "AndesiteFurnaceTileEntity"));
        GameRegistry.registerTileEntity(DioriteFurnaceTileEntity.class, new ResourceLocation(Ref.MODID, "DioriteFurnaceTileEntity"));
        GameRegistry.registerTileEntity(NetherrackFurnaceTileEntity.class, new ResourceLocation(Ref.MODID, "NetherrackFurnaceTileEntity"));
        GameRegistry.registerTileEntity(NetherBrickFurnaceTileEntity.class, new ResourceLocation(Ref.MODID, "NetherBrickFurnaceTileEntity"));
        GameRegistry.registerTileEntity(BrickFurnaceTileEntity.class, new ResourceLocation(Ref.MODID, "BrickFurnaceTileEntity"));
        GameRegistry.registerTileEntity(CrystalTileEntity.class, new ResourceLocation(Ref.MODID, "CrystalTileEntity"));
        GameRegistry.registerTileEntity(DrawerTileEntity.class, new ResourceLocation(Ref.MODID, "DrawerTileEntity"));
        GameRegistry.registerTileEntity(BlastFurnaceTileEntity.class, new ResourceLocation(Ref.MODID, "BlastFurnaceTileEntity"));
        GameRegistry.registerTileEntity(InfuserTileEntity.class, new ResourceLocation(Ref.MODID, "InfuserTileEntity"));
        GameRegistry.registerTileEntity(TileEntityDistortedBricks.class, new ResourceLocation(Ref.MODID, "TileEntityDistortedBricks"));
        GameRegistry.registerTileEntity(ApiaryTileEntity.class, new ResourceLocation(Ref.MODID, "ApiaryTileEntity"));
        GameRegistry.registerTileEntity(InversionTileEntity.class, new ResourceLocation(Ref.MODID, "InversionTileEntity"));
        GameRegistry.registerTileEntity(ImbuedCaulronTileEntity.class, new ResourceLocation(Ref.MODID, "ImbuedCaulronTileEntity"));
        GameRegistry.registerTileEntity(PurifierTileEntity.class, new ResourceLocation(Ref.MODID, "PurifierTileEntity"));
        GameRegistry.registerTileEntity(ArcaneFurnaceTileEntity.class, new ResourceLocation(Ref.MODID, "ArcaneFurnaceTileEntity"));
        GameRegistry.registerTileEntity(ArcanePillarTileEntity.class, new ResourceLocation(Ref.MODID, "ArcanePillarTileEntity"));
        GameRegistry.registerTileEntity(StoneManTileEntity.class, new ResourceLocation(Ref.MODID, "StoneManTileEntity"));
        GameRegistry.registerTileEntity(ForgeBlockTileEntity.class, new ResourceLocation(Ref.MODID, "ForgeBlockTileEntity"));
        GameRegistry.registerTileEntity(IdolTileEntity.class, new ResourceLocation(Ref.MODID, "IdolTileEntity"));
        GameRegistry.registerTileEntity(CrystalProviderTileEntity.class, new ResourceLocation(Ref.MODID, "CrystalProviderTileEntity"));
        GameRegistry.registerTileEntity(DuplicatorTileEntity.class, new ResourceLocation(Ref.MODID, "DuplicatorTileEntity"));
        GameRegistry.registerTileEntity(BerryBushTileEntity.class, new ResourceLocation(Ref.MODID, "BerryBushTileEntity"));
        //Gui Stuff (Handler and then each Gui)
        NetworkRegistry.INSTANCE.registerGuiHandler(TheNewJourneyMod.instance, GuiHandlerRegistry.getInstance());
        GuiHandlerRegistry.getInstance().registerGuiHandler(new DrawerGUIRegistry(), DrawerGUIRegistry.getGuiID());
        GuiHandlerRegistry.getInstance().registerGuiHandler(new BlastFurnaceGUIRegistry(), BlastFurnaceGUIRegistry.getGuiID());
        GuiHandlerRegistry.getInstance().registerGuiHandler(new InfuserGUIRegistry(), InfuserGUIRegistry.getGuiID());
        GuiHandlerRegistry.getInstance().registerGuiHandler(new GuideGUIRegistry(), GuideGUIRegistry.getGuiID());
        GuiHandlerRegistry.getInstance().registerGuiHandler(new ApiaryGUIRegistry(), ApiaryGUIRegistry.getGuiID());
        GuiHandlerRegistry.getInstance().registerGuiHandler(new PurifierGUIRegistry(), PurifierGUIRegistry.getGuiID());
        GuiHandlerRegistry.getInstance().registerGuiHandler(new ArcaneFurnaceGUIRegistry(), ArcaneFurnaceGUIRegistry.getGuiID());
        GuiHandlerRegistry.getInstance().registerGuiHandler(new ArcanePillarGUIRegistry(), ArcanePillarGUIRegistry.getGuiID());
        GuiHandlerRegistry.getInstance().registerGuiHandler(new BackpackGUIRegistry(), BackpackGUIRegistry.getGuiID());
        GuiHandlerRegistry.getInstance().registerGuiHandler(new DuplicatorGUIRegistry(), DuplicatorGUIRegistry.getGuiID());
        //Dimensions and Biomes
        ModDimension.mainRegistry();
    }

    public void postInit(FMLPostInitializationEvent e) {
        //World Gen
        GameRegistry.registerWorldGenerator(new OreGenerator(), 0);
        GameRegistry.registerWorldGenerator(new StructureGenerator(), 0);
        GameRegistry.registerWorldGenerator(new SingleOreGenerator(), 0);
        //Compat JEI
        if (Loader.isModLoaded("jei")) {
            String s = "\u001B[36m" + "[The New Journey] JEI Compat Loaded!" + "\u001B[0m";
            System.out.println(s);
        } else {
            String s = "\u001B[31m" + "[The New Journey] No JEI found!" + "\u001B[0m";
            System.out.println(s);
        }
        //Init Block Harvest Level
        //Bronze
        //Vanilla Resets
        /**
         0 Wood
         1 Stone
         2 Copper
         3 Bronze
         4 Iron/Ruby
         5 Diamond/Cobalt
         6 Distortion
         7 Fire
         8 Shadow
         9 Aquis
         */
        ModBlocks.CopperOre.setHarvestLevel("pickaxe", 1);
        ModBlocks.TinOre.setHarvestLevel("pickaxe", 2);
        ModBlocks.CopperBlock.setHarvestLevel("pickaxe", 2);
        ModBlocks.BronzeBlock.setHarvestLevel("pickaxe", 3);

        ModBlocks.RubyOre.setHarvestLevel("pickaxe", 3);
        ModBlocks.CobaltOre.setHarvestLevel("pickaxe", 4);
        ModBlocks.DistortedOre.setHarvestLevel("pickaxe", 5);
        ModBlocks.FireOre.setHarvestLevel("pickaxe", 6);
        ModBlocks.ShadowOre.setHarvestLevel("pickaxe", 7);
        ModBlocks.AquisOre.setHarvestLevel("pickaxe", 8);
        //Ruby
        ModBlocks.RubyBlock.setHarvestLevel("pickaxe", 3);
        //Cobalt
        ModBlocks.CobaltBlock.setHarvestLevel("pickaxe", 4);
        //Distortion
        ModBlocks.DistortedBlock.setHarvestLevel("pickaxe", 5);
        //Fire
        ModBlocks.FireBlock.setHarvestLevel("pickaxe", 6);
        //Shadow
        ModBlocks.ShadowBlock.setHarvestLevel("pickaxe", 7);
        //Misc
        ModBlocks.Quarry.setHarvestLevel("pickaxe", 5);
        //TODO change to event handler hook: PlayerEvent.HarvestCheck
        if (Config.enableBronzeAge) {
            Blocks.COAL_ORE.setHarvestLevel("pickaxe", 1);
            Blocks.IRON_ORE.setHarvestLevel("pickaxe", 3);
        }
    }
}    