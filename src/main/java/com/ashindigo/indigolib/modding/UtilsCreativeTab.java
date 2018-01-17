package com.ashindigo.indigolib.modding;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * Easy way for making a creative tab
 * @author Ash Indigo
 *
 */
public class UtilsCreativeTab extends CreativeTabs {
	
	public static ListMultimap<String, CreativeTabs> modTab = ArrayListMultimap.create();
	public static Map<CreativeTabs, String> tabNameList = new HashMap<CreativeTabs, String>();
	public static Map<CreativeTabs, String> transTabList = new HashMap<CreativeTabs, String>();
	ItemStack icon2;

	/**
	 * Default constructor
	 * @param label The name of the group
	 * @param icon The icon of the tab
	 */
	public UtilsCreativeTab(String unlocalizedName, ItemStack icon, String translatedName, String modid) {
		super(unlocalizedName);
		modTab.put(modid, this);
		tabNameList.put(this, unlocalizedName);
		transTabList.put(this, translatedName);
		icon2 = icon;
	}

	@Override
	public ItemStack getTabIconItem() {
		return icon2;
	}

}
