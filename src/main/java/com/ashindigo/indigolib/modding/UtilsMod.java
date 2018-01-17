package com.ashindigo.indigolib.modding;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import com.ashindigo.indigolib.modding.automation.UtilsItemBlockLoader;
import com.ashindigo.indigolib.modding.automation.UtilsJsonCreator;
import com.ashindigo.indigolib.modding.automation.UtilsLanguageCreator;
import com.ashindigo.indigolib.modding.world.UtilsWorldgen;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Small Forge mod to run required methods.
 */

// TODO Fix registrations or add quick ItemBlock support
@Mod(modid = "indigoutils", version = "1.0", name = "IndigoUtils")
public class UtilsMod extends IndigoMod {
	
	public static Configuration config;
	//public static Logger logger;
	public static ArrayList<String> modidList = new ArrayList<String>();
	public static final boolean debug = Launch.blackboard.get("fml.deobfuscatedEnvironment") != null;

	@EventHandler
	public void constructing(FMLConstructionEvent event) {
		FluidRegistry.enableUniversalBucket();
	}
	
	@SidedProxy(clientSide = "com.ashindigo.indigolib.modding.ClientProxy", serverSide = "com.ashindigo.indigolib.modding.ServerProxy")
	public static ServerProxy proxy;
	
	@Override
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		logger.log(Level.INFO, "IndigoLib API Pre-Init");
		config = new Configuration(event.getSuggestedConfigurationFile());
		UtilsItemBlockLoader.preInitItems();
		UtilsItemBlockLoader.preInitBlocks();
	}

	@Override
	@EventHandler
	public void init(FMLInitializationEvent event) {
		// Checks to see if debug mode is enabled and loads items/blocks
		if (debug == true) {
			debugMode();
		}
		UtilsItemBlockLoader.initItems();
		UtilsItemBlockLoader.initBlocks();
		GameRegistry.registerWorldGenerator(new UtilsWorldgen(), 1);
	}
	
	@Override
	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		UtilsItemBlockLoader.postInitItems();
		UtilsItemBlockLoader.postInitBlocks();
	}
	
	/**
	 * Debug mode for when IndigoUtils is in a development environment.
	 */
	public void debugMode() {
		logger.log(Level.INFO, "Debug Mode is enabled.");
		for (int i = 0; UtilsMod.modidList.size() > i; i++) {
			for (int i1 = 0; UtilsItem.modItems.get(UtilsMod.modidList.get(i).toString()).size() > i1; i1++) {
				UtilsLanguageCreator.init(UtilsItem.modItems.get((String) UtilsMod.modidList.get(i)).get(i1).getUnlocalizedName(), UtilsItem.translatedNameList.get(UtilsItem.modItems.get((String) UtilsMod.modidList.get(i)).get(i1)), UtilsMod.modidList.get(i).toString());
			}
		}
		for (int i = 0; UtilsMod.modidList.size() > i; i++) {
			for (int a = 0; UtilsCreativeTab.modTab.get(UtilsMod.modidList.get(i)).size() > a; a++) {
				UtilsLanguageCreator.tabInit(UtilsCreativeTab.tabNameList.get(UtilsCreativeTab.modTab.get(UtilsMod.modidList.get(i)).get(a)), UtilsCreativeTab.transTabList.get(UtilsCreativeTab.modTab.get(UtilsMod.modidList.get(i)).get(a)), UtilsMod.modidList.get(i));
			}
		}
		for (int i = 0; modidList.size() > i; i++) {
			UtilsJsonCreator.init((String) modidList.get(i));
		}
	}
	
	@Override
	public String getModID() {
		return "indigoutils";
	}
}
