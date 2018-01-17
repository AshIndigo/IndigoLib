package com.ashindigo.indigolib.modding.automation;

import com.ashindigo.indigolib.modding.UtilsArmor;
import com.ashindigo.indigolib.modding.UtilsBlock;
import com.ashindigo.indigolib.modding.UtilsBlockOre;
import com.ashindigo.indigolib.modding.UtilsItem;
import com.ashindigo.indigolib.modding.UtilsMod;
import com.ashindigo.indigolib.modding.UtilsToolset;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Manages the item and block apis for IndigoUtils
 * @author Ash Indigo
 */
public class UtilsItemBlockLoader {
	
	public static void preInitItems() {
		
	}
	
	/**
	* Start the auto json register
	* @author Ash Indigo
	*/
	public static void initItems() {
		try {
			for (int i = 0; UtilsMod.modidList.size() > i; i++) {
				if (UtilsItem.modItems.size() != 0 && UtilsItem.modItems.keySet().toArray().length > i) {
					for (int i1 = 0; UtilsItem.modItems.get((String) UtilsItem.modItems.keySet().toArray()[i]).size() > i1; i1++) {
						if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {
							Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(UtilsItem.modItems.get((String) UtilsItem.modItems.keySet().toArray()[i]).get(i1), 0, new ModelResourceLocation(UtilsItem.modItems.keySet().toArray()[i] + ":" + UtilsItem.itemNameList.get(UtilsItem.modItems.get((String) UtilsItem.modItems.keySet().toArray()[i]).get(i1)), "inventory"));
						}
					}
				}
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads recipes
	 */
	public static void postInitItems() {
		
		UtilsToolset.registerRecipes();
		UtilsArmor.registerRecipes();
		
	}
	
	public static void preInitBlocks() {
		
	}
	
	/**
	 * Initiates the auto model loader
	 * @author Ash Indigo
	 */
	public static void initBlocks() {
		for (int m = 0; UtilsMod.modidList.size() > m; m++) {
			for (int r = 0; UtilsBlock.modBlocks.get((String) UtilsMod.modidList.get(m)).size() > r; r++) {
				UtilsLanguageCreator.init(UtilsBlock.modBlocks.get((String) UtilsMod.modidList.get(m)).get(r).getUnlocalizedName(), UtilsBlock.translatedNameList.get(UtilsBlock.modBlocks.get((String) UtilsMod.modidList.get(m)).get(r)), (String) UtilsMod.modidList.get(m));
			}
		}
		
		for (int m = 0; UtilsMod.modidList.size() > m; m++) {
			for (int r = 0; UtilsBlock.modBlocks.get((String) UtilsMod.modidList.get(m)).size() > r; r++) {
				if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {
					Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(UtilsBlock.modBlocks.get((String) UtilsMod.modidList.get(m)).get(r)), 0, new ModelResourceLocation(UtilsBlock.modBlocks.get(UtilsMod.modidList.get(m)).get(r).getRegistryName(), "inventory"));
				}
			}
		}
	}
	
	/**
	 * Load the recipe handlers for ores
	 */
	public static void postInitBlocks() {
		UtilsBlockOre.registerRecipes();
	}

}
