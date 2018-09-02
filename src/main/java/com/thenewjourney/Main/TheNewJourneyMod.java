package com.thenewjourney.Main;

import com.cj3636.lib.Config;
import com.cj3636.lib.Ref;
import com.thenewjourney.misc.ModCommandPower;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Ref.MODID, name = Ref.NAME, version = Ref.VERSION)
public class TheNewJourneyMod {
	
	@SidedProxy(clientSide = Ref.CLIENTSIDE, serverSide = Ref.SERVERSIDE)

	private static CommonProxy proxy;
	public static final String MODID = "thenewjourney";
	public static final String VERSION = "0.1";
	
	@Instance
	public static TheNewJourneyMod modInstance;
    @Mod.Instance(TheNewJourneyMod.MODID)
    public static TheNewJourneyMod instance;
    
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		String s = "\u001B[35m" + "[The New Journey] Initializing Mod." + "\u001B[0m";
		System.out.println(s);
		Config.load(e);
		proxy.preInit(e);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
		String s = "\u001B[35m" + "[The New Journey] Successfully Enabled Mod." + "\u001B[0m";
		System.out.println(s);
	}
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new ModCommandPower());
	}
}
