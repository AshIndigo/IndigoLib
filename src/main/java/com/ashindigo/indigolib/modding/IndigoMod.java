package com.ashindigo.indigolib.modding;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public abstract class IndigoMod {
	
	protected Logger logger;
	
	protected IndigoMod() {
		if (!getModID().equals("indigoutils")) {
			UtilsMod.modidList.add(getModID());
		}
		logger = LogManager.getLogger(getModID());	
	}
	
	/**
	* The preinit event for Forge
	*/
	@EventHandler
	public abstract void preinit(FMLPreInitializationEvent event);
	
	/**
	* The init event for Forge
	*/
	@EventHandler
	public abstract void init(FMLInitializationEvent event);
	
	/**
	* The postinit event for Forge
	*/
	@EventHandler
	public abstract void postinit(FMLPostInitializationEvent event);
	
	public abstract String getModID();

}
