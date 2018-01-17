package com.ashindigo.indigolib.modding;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
* An Block class which automatically registers the block that is being added.
* @author Ash Indigo
*/
public class UtilsBlock extends Block {
	
	public static Map<Block, String> blockNameList = new HashMap<Block, String>();
	public static ListMultimap<String, Block> modBlocks = ArrayListMultimap.create();
	public static Map<Block, String> translatedNameList = new HashMap<Block, String>();
	
	/**
	 * Constructor used for declaring blocks
	 * @param mat The material for the block
	 * @param modid The mod's Mod ID
	 * @param name The name of the block
	 * @param translatedName The In-Game name of the block
	 */
	public UtilsBlock(Material mat, String modid, String name, String translatedName, CreativeTabs tab) {
			super(mat);
			setRegistryName(modid, name);
			setUnlocalizedName(this.getRegistryName().toString());
			setCreativeTab(tab);
			blockNameList.put(this, name);
			modBlocks.put(modid, this);
			translatedNameList.put(this, translatedName);
		}
}