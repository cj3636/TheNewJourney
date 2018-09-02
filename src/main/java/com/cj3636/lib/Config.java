package com.cj3636.lib;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Config {
	private static final String LANG_PREFIX = Ref.MODID + ".config.";

	private static Configuration config;
	
	public static int fireDimId;
	public static int florusDimId;
	public static boolean opTools;
	public static boolean enableDuplicatorRecipe;
	public static boolean enableStory;
	public static boolean enableBronzeAge;
	public static boolean enableCoords;
	public static boolean enableBooks;
	public static int cobaltSpawn;
	public static int shadowSpawn;
	public static int distortionSpawn;
	public static int rubySpawn;
	public static int copperSpawn;
	public static int tinSpawn;
	public static int graphiteSpawn;
	public static int marbleSpawn;
	public static int fireSpawn;

	public static void load(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());
		reloadConfig();

		MinecraftForge.EVENT_BUS.register(Config.class);
	}

	private static void reloadConfig() {
		//Basics
		fireDimId = config.getInt("fireDimId", Configuration.CATEGORY_GENERAL, -7, -100, 100, "The id for the Fire Dimension. Use for compatibility purposes.", LANG_PREFIX + "fireDimId");
		florusDimId = config.getInt("florusDimId", Configuration.CATEGORY_GENERAL, -8, -100, 100, "The id for the Fire Dimension. Use for compatibility purposes.", LANG_PREFIX + "florusDimId");
		opTools = config.getBoolean("opTools", Configuration.CATEGORY_GENERAL, true, "Whether or not some of the O.P. tools in the mod are available. For Example: Bedrock Sword, Bedrock Hammer, etc.", LANG_PREFIX + "opTools");
		enableStory = config.getBoolean("enableStory", Configuration.CATEGORY_GENERAL, true, "Whether or not access to any of the storyline dimensions and items are available. Turn off for performance/compatibility purposes.", LANG_PREFIX + "enableStory");
		enableCoords = config.getBoolean("enableCoords", Configuration.CATEGORY_GENERAL, false, "Whether or not the Overworld generated fire ruins display their x, z location in chat. Enable for easily discoverable Fire Ruins.", LANG_PREFIX + "enableCoords");
		enableBooks = config.getBoolean("enableBooks", Configuration.CATEGORY_GENERAL, true, "Whether the first-spawn tutorial book and jorunal are given.", LANG_PREFIX + "enableBooks");
		enableBronzeAge = config.getBoolean("enableBronzeAge", Configuration.CATEGORY_GENERAL, true, "Whether or not to use the altered progression system that requires the player to mine copper -> Tin = Bronze -> Iron", LANG_PREFIX + "enableBronzeAge");
		enableDuplicatorRecipe = config.getBoolean("enableDuplicatorRecipe", Configuration.CATEGORY_GENERAL, true, "Enables the crafting recipe for the Duplicator. This item is balanced in vanilla, but can become very OP when used with other mods. EMC Implementation may be used to balance dupe costs.", LANG_PREFIX + "enableDuplicatorRecipe");
		//Ore
		cobaltSpawn = config.getInt("cobaltSpawn", Configuration.CATEGORY_GENERAL, 4, 0, 100, "How often per chunk Minecraft attempts to generate this ore. 100 is rediculous, 0 to disable the ore", LANG_PREFIX + "cobaltSpawn");
		copperSpawn = config.getInt("copperSpawn", Configuration.CATEGORY_GENERAL, 6, 0, 100, "How often per chunk Minecraft attempts to generate this ore. 100 is rediculous, 0 to disable the ore", LANG_PREFIX + "copperSpawn");
		tinSpawn = config.getInt("tinSpawn", Configuration.CATEGORY_GENERAL, 6, 0, 100, "How often per chunk Minecraft attempts to generate this ore. 100 is rediculous, 0 to disable the ore", LANG_PREFIX + "tinSpawn");
		shadowSpawn = config.getInt("shadowSpawn", Configuration.CATEGORY_GENERAL, 3, 0, 100, "How often per chunk Minecraft attempts to generate this ore. 100 is rediculous, 0 to disable the ore", LANG_PREFIX + "shadowSpawn");
		distortionSpawn = config.getInt("distortionSpawn", Configuration.CATEGORY_GENERAL, 8, 0, 100, "How often per chunk Minecraft attempts to generate this ore. 100 is rediculous, 0 to disable the ore", LANG_PREFIX + "distortionSpawn");
		fireSpawn = config.getInt("fireSpawn", Configuration.CATEGORY_GENERAL, 5, 0, 100, "How often per chunk Minecraft attempts to generate this ore. 100 is rediculous, 0 to disable the ore", LANG_PREFIX + "fireSpawn");
		rubySpawn = config.getInt("rubySpawn", Configuration.CATEGORY_GENERAL, 8, 0, 100, "How often per chunk Minecraft attempts to generate this ore. 100 is rediculous, 0 to disable the ore", LANG_PREFIX + "rubySpawn");rubySpawn = config.getInt("rubySpawn", Configuration.CATEGORY_GENERAL, 8, 0, 100, "How often per chunk Minecraft attempts to generate this ore. 100 is rediculous, 0 to disable the ore", LANG_PREFIX + "rubySpawn");
		graphiteSpawn = config.getInt("graphiteSpawn", Configuration.CATEGORY_GENERAL, 4, 0, 100, "How often per chunk Minecraft attempts to generate this ore. 100 is rediculous, 0 to disable the ore", LANG_PREFIX + "graphiteSpawn");
		marbleSpawn = config.getInt("marbleSpawn", Configuration.CATEGORY_GENERAL, 4, 0, 100, "How often per chunk Minecraft attempts to generate this 'ore'. 100 is rediculous, 0 to disable the ore", LANG_PREFIX + "marbleSpawn");
		if (config.hasChanged()) {
			config.save();
		}
	}

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(Ref.MODID)) {
			reloadConfig();
		}
	}
}