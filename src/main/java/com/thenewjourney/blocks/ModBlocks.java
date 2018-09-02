package com.thenewjourney.blocks;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.apiary.ApiaryBlock;
import com.thenewjourney.blocks.arcane.ArcaneFurnaceBlock;
import com.thenewjourney.blocks.arcane.IngotCastBlock;
import com.thenewjourney.blocks.blastfurnace.BlastFurnaceBlock;
import com.thenewjourney.blocks.bush.BerryBush;
import com.thenewjourney.blocks.cauldron.ImbuedCauldron;
import com.thenewjourney.blocks.crystal.CrystalBlock;
import com.thenewjourney.blocks.distortedbricks.ModBlockDistortedBricks;
import com.thenewjourney.blocks.drawer.DrawerBlock;
import com.thenewjourney.blocks.duplicator.DuplicatorBlock;
import com.thenewjourney.blocks.florus.CompilerBlock;
import com.thenewjourney.blocks.florus.CompressedFlorusBlock;
import com.thenewjourney.blocks.furnace.block.*;
import com.thenewjourney.blocks.idol.IdolBlock;
import com.thenewjourney.blocks.infuser.InfuserBlock;
import com.thenewjourney.blocks.pervateki.ForgeBlock;
import com.thenewjourney.blocks.pillar.ArcanePillarBlock;
import com.thenewjourney.blocks.portal.VisceonFirePortal;
import com.thenewjourney.blocks.portal.VisceonFlorusPortal;
import com.thenewjourney.blocks.provider.CrystalProvider;
import com.thenewjourney.blocks.provider.Provider;
import com.thenewjourney.blocks.purifier.PurifierBlock;
import com.thenewjourney.blocks.quarry.QuarryBlock;
import com.thenewjourney.blocks.register.*;
import com.thenewjourney.blocks.special.ArcaneSoilRegistry;
import com.thenewjourney.blocks.special.FireFernRegistry;
import com.thenewjourney.blocks.special.FrameBlock;
import com.thenewjourney.blocks.special.Weed;
import com.thenewjourney.blocks.stair.StairBlock;
import com.thenewjourney.blocks.stoneman.StoneManBlock;
import com.thenewjourney.blocks.visceon.SubstrateBlock;
import com.thenewjourney.blocks.visceon.VisceonCoreBlock;
import com.thenewjourney.blocks.wood.LeavesBlock;
import com.thenewjourney.blocks.wood.LogBlock;
import com.thenewjourney.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class ModBlocks {
    public static final List<Block> Block_List = new ArrayList<Block>();
    public static final List<Item> Block_Item_List = new ArrayList<Item>();
    //Ore
    public static final Block AquisOre;
    public static final Block CobaltOre;
    public static final Block DistortedOre;
    public static final Block FireOre;
    public static final Block RubyOre;
    public static final Block ShadowOre;
    public static final Block Graphite;
    public static final Block CopperOre;
    public static final Block TinOre;
    //Ore Blocks
    public static final Block CobaltBlock;
    public static final Block RubyBlock;
    public static final Block CopperBlock;
    public static final Block TinBlock;
    public static final Block BronzeBlock;
    public static final Block DistortedBlock;
    public static final Block ShadowBlock;
    public static final Block FireBlock;
    public static final Block AquisBlock;
    //Building
    public static final Block FlorusBlock;
    public static final Block CompressedFlorusBlock;
    public static final Block ClearGlass;
    public static final Block BurntBricks;
    public static final Block BurntBrickStairs;
    public static final Block WarpStone;
    public static final Block WarpStoneStairs;
    public static final Block CrypticBlock;
    public static final Block BurntStone;
    public static final Block BurntStoneStairs;
    public static final Block Carpet;
    public static final Block Carpet2;
    public static final Block Carpet3;
    public static final Block CarpetG;
    public static final Block CarpetG2;
    public static final Block DistortedStone;
    public static final Block DistortedBricks;
    public static final Block Marble;
    public static final Block MarbleStairs;
    public static final Block ArmoredStone;
    public static final Block ArmoredGlass;
    public static final Block RefractoryBrick;
    //Magic
    public static final Block ArcaneFurnace;
    public static final Block Distributor;
    public static final Block Compiler;
    public static final Block KingBlock;
    public static final Block ObsidianGlass;
    public static final Block SentinelSpawn;
    public static final Block StoneMan;
    public static final Block Purifier;
    public static final Block Duplicator;
    public static final Block Quarry;
    public static final Block ArchaicInfuser;
    public static final Block ArcanePillar;
    public static final Block VisceonCore;
    public static final Block Substrate;
    public static final Block Crystal;
    public static final Block CrystalProvider;
    public static final Block Provider;
    public static final Block PervatekiForge;
    public static final Block ForgeController;
    public static final Block ForgeControllerOn;
    //Furnaces
    public static final Block GraniteFurnaceIdle;
    public static final Block GraniteFurnaceActive;
    public static final Block AndesiteFurnaceIdle;
    public static final Block AndesiteFurnaceActive;
    public static final Block DioriteFurnaceIdle;
    public static final Block DioriteFurnaceActive;
    public static final Block NetherrackFurnaceIdle;
    public static final Block NetherrackFurnaceActive;
    public static final Block NetherBrickFurnaceIdle;
    public static final Block NetherBrickFurnaceActive;
    public static final Block BrickFurnaceIdle;
    public static final Block BrickFurnaceActive;
    //Machine
    public static final Block BlastFurnace;
    public static final Block BlastFurnaceActive;
    public static final Block IngotCast;
    public static final Block ImbuedCauldron;
    //Plant
    public static final Block ArcaneSoil;
    public static final Block Apiary;
    public static final Block FireFern;
    public static final Block NarcoBush;
    public static final Block NarcoBerryBush;
    public static final Block Mortar;
    public static final Block Weed;
    public static final Block BeeHive;
    //Misc
    public static final Block Frame;
    public static final Block Sphere;
    public static final Block Spheref;
    public static final Block Drawer;
    public static final Block Lamp;
    public static final Block Chandelier;
    public static final Block Idol;
    public static final Block GobletBlock;
    //Wood
    public static final Block PurviaLignumLog;
    public static final Block PurviaLignumPlanks;
    public static final Block PurviaLeaves;
    public static final Block PurviaGrass;

    static {
        //Ores
        AquisOre = registerBlock(new BlockRegistry("aquisore", Material.ROCK, 150F, 2000F).setLightLevel(0.7F));
        AquisBlock = registerBlock(new BlockRegistry("aquisblock", Material.IRON, 10F, 2000F).setLightLevel(0.7F));
        CobaltBlock = registerBlock(new BlockRegistry("cobaltblock", Material.IRON, 10F, 120F));
        CobaltOre = registerBlock(new BlockRegistry("cobaltore", Material.ROCK, 15F, 120F));
        DistortedBlock = registerBlock(new BlockRegistry("distortionblock", Material.IRON, 11F, 580F));
        FireBlock = registerBlock(new BlockRegistry("fireblock", Material.IRON, 14F, 2000F));
        FireOre = registerBlock(new BlockRegistry("fireore", Material.ROCK, 19F, 2000F));
        RubyBlock = registerBlock(new BlockRegistry("rubyblock", Material.IRON, 4F, 12F));
        CopperBlock = registerBlock(new BlockRegistry("copperblock", Material.IRON, 3F, 12F));
        TinBlock = registerBlock(new BlockRegistry("tinblock", Material.IRON, 4F, 12F));
        BronzeBlock = registerBlock(new BlockRegistry("bronzeblock", Material.IRON, 5F, 12F));
        ShadowBlock = registerBlock(new BlockRegistry("shadowblock", Material.IRON, 10F, 1220F));
        ShadowOre = registerBlock(new BlockRegistry("shadowore", Material.ROCK, 170F, 1220F));
        Graphite = registerBlock(new BlockRegistry("graphite", Material.ROCK, 8F, 15F));
        CopperOre = registerBlock(new BlockRegistry("copperore", Material.ROCK, 5F, 20F));
        TinOre = registerBlock(new BlockRegistry("tinore", Material.ROCK, 7F, 20F));
        //Misc Blocks
        ArcaneFurnace = registerBlock(new ArcaneFurnaceBlock("arcanefurnace").setCreativeTab(Ref.CTAB));
        ArmoredStone = registerBlock(new BlockRegistry("armoredstone", Material.IRON, 10F, 1200F));
        BurntBricks = registerBlock(new BlockRegistry("burntbricks", Material.ROCK, 7F, 1200F));
        BurntBrickStairs = registerBlock(new StairBlock(BurntBricks.getDefaultState(), "burntbrickstairs", 7F, 2000F));
        BurntStone = registerBlock(new BlockRegistry("burntstone", Material.ROCK, 15F, 2000F));
        BurntStoneStairs = registerBlock(new StairBlock(BurntStone.getDefaultState(), "burntstonestairs", 15F, 2000F));
        Carpet = registerBlock(new BlockRegistry("carpet", Material.ROCK, 1F, 12F));
        Carpet2 = registerBlock(new BlockRegistry("carpet2", Material.ROCK, 1F, 12F));
        Carpet3 = registerBlock(new BlockRegistry("carpet3", Material.ROCK, 1F, 12F));
        CarpetG = registerBlock(new TransparentRegistry("carpetg", Material.GLASS, 1F, 2F, false).setLightOpacity(0));
        CarpetG2 = registerBlock(new TransparentRegistry("carpetg2", Material.GLASS, 1F, 2F, false).setLightOpacity(0));
        NarcoBush = registerBlock(new BerryBush("narcobush", Material.VINE, 1F, 2F, false).setLightOpacity(0));
        NarcoBerryBush = registerBlock(new BerryBush("narcoberrybush", Material.VINE, 1F, 2F, false).setLightOpacity(0));
        Mortar = registerBlock(new Mortar("mortar", Material.ROCK, 2F, 12F).setLightOpacity(0));
        Lamp = registerBlock(new BlockRegistry("lamp", Material.ROCK, 15F, 20F).setLightLevel(1F).setLightOpacity(0));
        Chandelier = registerBlock(new TransparentRegistry("chandelier", Material.ROCK, 5F, 200F, true).setLightLevel(1F).setLightOpacity(0));
        Idol = registerBlock(new IdolBlock("idol", Material.IRON, 25F, 2000F).setLightOpacity(0));
        DistortedStone = registerBlock(new BlockRegistry("distortedstone", Material.ROCK, 40F, 2000F));
        DistortedBricks = registerBlock(new ModBlockDistortedBricks("distortedbricks", Material.ROCK, 11F, 2000F));
        Marble = registerBlock(new BlockRegistry("marble", Material.ROCK, 7F, 120F));
        MarbleStairs = registerBlock(new StairBlock(Marble.getDefaultState(), "marblestairs", 7F, 120F));
        WarpStone = registerBlock(new BlockRegistry("warpstone", Material.ROCK, 7F, 1200F));
        WarpStoneStairs = registerBlock(new StairBlock(WarpStone.getDefaultState(), "warpstonestairs", 7F, 120F));
        ImbuedCauldron = registerBlock(new ImbuedCauldron("imbuedcauldron", Material.IRON, 8F, 12F));
        //Gem Ores
        RubyOre = registerBlock(new GemOreRegistry("rubyore", Material.ROCK, 9F, 12F, ModItems.RubyGem));
        DistortedOre = registerBlock(new GemOreRegistry("distortionore", Material.ROCK, 16F, 580F, ModItems.DistortionGem));
        //Glass-Like Blocks
        ClearGlass = registerBlock(new TransparentRegistry("clearglass", Material.GLASS, 1F, 1F, false).setLightOpacity(0));
        ArmoredGlass = registerBlock(new TransparentRegistry("armoredglass", Material.GLASS, 1F, 1200F, false).setLightOpacity(0));
        FlorusBlock = registerBlock(new TransparentRegistry("florusblock", Material.GLASS, 8F, 11F, false).setLightOpacity(0));
        GobletBlock = registerBlock(new GobletRegistry("gobletblock", Material.CLAY, 1F, 110F, true).setLightOpacity(1));
        Compiler = registerBlock(new CompilerBlock("compiler", Material.IRON, 8F, 11F));
        CompressedFlorusBlock = registerBlock(new CompressedFlorusBlock("compressedflorusblock", Material.GLASS, 8F, 11F).setLightOpacity(0));
        //Plant Related Blocks
        BeeHive = registerBlock(new HiveRegistry("beehive", Material.GLASS, 2F, 1F));
        FireFern = registerBlock(new FireFernRegistry("firefern"));
        Weed = registerBlock(new Weed());
        ArcaneSoil = registerBlock(new ArcaneSoilRegistry("arcanesoil"));
        //Furnace
        AndesiteFurnaceIdle = registerBlock(new AndesiteFurnaceBlock(false, "andesitefurnaceidle").setCreativeTab(Ref.CTAB));
        AndesiteFurnaceActive = registerBlock(new AndesiteFurnaceBlock(true, "andesitefurnaceactive").setLightLevel(1.0F));
        BrickFurnaceIdle = registerBlock(new BrickFurnaceBlock(false, "brickfurnaceidle").setCreativeTab(Ref.CTAB));
        BrickFurnaceActive = registerBlock(new BrickFurnaceBlock(true, "brickfurnaceactive").setLightLevel(1.0F));
        DioriteFurnaceIdle = registerBlock(new DioriteFurnaceBlock(false, "dioritefurnaceidle").setCreativeTab(Ref.CTAB));
        DioriteFurnaceActive = registerBlock(new DioriteFurnaceBlock(true, "dioritefurnaceactive").setLightLevel(1.0F));
        GraniteFurnaceIdle = registerBlock(new GraniteFurnaceBlock(false, "granitefurnaceidle").setCreativeTab(Ref.CTAB));
        GraniteFurnaceActive = registerBlock(new GraniteFurnaceBlock(false, "granitefurnaceactive").setLightLevel(1.0F));
        NetherBrickFurnaceIdle = registerBlock(new NetherBrickFurnaceBlock(false, "netherbrickfurnaceidle").setCreativeTab(Ref.CTAB));
        NetherBrickFurnaceActive = registerBlock(new NetherBrickFurnaceBlock(true, "netherbrickfurnaceactive").setLightLevel(1.0F));
        NetherrackFurnaceIdle = registerBlock(new NetherrackFurnaceBlock(false, "netherrackfurnaceidle").setCreativeTab(Ref.CTAB));
        NetherrackFurnaceActive = registerBlock(new NetherrackFurnaceBlock(true, "netherrackfurnaceactive").setLightLevel(1.0F));
        //Special Blocks/OBJ's
        ArchaicInfuser = registerBlock(new InfuserBlock("archaicinfuser", Material.IRON));
        ArcanePillar = registerBlock(new ArcanePillarBlock("arcanepillar", Material.IRON, 5F, 20F));
        KingBlock = registerBlock(new KingBlockRegistry("kingblock", Material.IRON).setLightOpacity(0));
        ObsidianGlass = registerBlock(new TransparentRegistry("obsidianglass", Material.IRON, 48F, 10000F, false).setLightOpacity(0));
        SentinelSpawn = registerBlock(new SentinelSpawnRegistry("sentinelspawn", Material.GLASS, 8, 25).setLightOpacity(0).setLightLevel(1F));
        StoneMan = registerBlock(new StoneManBlock("stoneman", Material.ROCK, 10F, 10F));
        CrypticBlock = registerBlock(new GrinderRegistry("crypticblock", Material.ROCK, 30F, 2000F));
        Sphere = registerBlock(new VisceonFirePortal("sphere"));
        Spheref = registerBlock(new VisceonFlorusPortal("spheref"));
        Drawer = registerBlock(new DrawerBlock("drawer"));
        Apiary = registerBlock(new ApiaryBlock("apiary"));
        BlastFurnace = registerBlock(new BlastFurnaceBlock("blastfurnace", false).setRegistryName("blastfurnace").setCreativeTab(Ref.CTAB));
        Purifier = registerBlock(new PurifierBlock("purifier").setRegistryName("purifier").setCreativeTab(Ref.CTAB));
        Duplicator = registerBlock(new DuplicatorBlock("duplicator").setRegistryName("duplicator").setCreativeTab(Ref.CTAB));
        IngotCast = registerBlock(new IngotCastBlock("ingotcast", Material.IRON, 7F, 200F));
        BlastFurnaceActive = registerBlock(new BlastFurnaceBlock("blastfurnaceactive", true).setRegistryName("blastfurnaceactive"));
        RefractoryBrick = registerBlock(new BlockRegistry("refractorybrick", Material.IRON, 8F, 120F));
        //Power
        Crystal = registerBlock(new CrystalBlock("crystal"));
        CrystalProvider = registerBlock(new CrystalProvider("crystalprovider"));
        Provider = registerBlock(new Provider("provider"));
        Distributor = registerBlock(new CoagulativeBlockRegistry("distributor", Material.IRON, 8F, 120F));
        Frame = registerBlock(new FrameBlock("frame", Material.IRON, 5F, 20F));

        VisceonCore = registerBlock(new VisceonCoreBlock("visceoncore", Material.IRON, 13F, 2000F));
        Substrate = registerBlock(new SubstrateBlock("substrate", Material.IRON, 13F, 2000F));

        Quarry = registerBlock(new QuarryBlock("quarry", Material.IRON, 10F, 1200F));

        PervatekiForge = registerBlock(new TransparentRegistry("pervatekiforge", Material.IRON, 14F, 1200F, true));
        ForgeController = registerBlock(new ForgeBlock(false, "forgecontroller", Material.IRON, 14F, 1200F));
        ForgeControllerOn = registerBlock(new ForgeBlock(true, "forgecontrolleron", Material.IRON, 14F, 1200F));

        PurviaLignumLog = registerBlock(new LogBlock("purvialignumlog", 4F, 14F));
        PurviaLignumPlanks = registerBlock(new BlockRegistry("purvialignumplanks", Material.WOOD, 3F, 7F));
        PurviaLeaves = registerBlock(new LeavesBlock("purvialeaves", 4F, 14F));
        PurviaGrass = registerBlock(new GrassRegistry("purviagrass", 4F, 14F));
    }

    public static void mainRegistry() {
    }

    public static <BLOCK extends Block> BLOCK registerBlock(BLOCK block) {
        return registerBlock(block, ItemBlock::new);
    }

    protected static <BLOCK extends Block> BLOCK registerBlock(BLOCK block, @Nullable Function<BLOCK, ItemBlock> itemFactory) {
        Block_List.add(block);
        Block_Item_List.add(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        return block;
    }
}
