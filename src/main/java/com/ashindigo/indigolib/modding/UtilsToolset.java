package com.ashindigo.indigolib.modding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.text.WordUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
/**
 * A class dedicated to making tool sets.
 * Will automatically registers recipes for each tool
 * @author Ash Indigo
 * XXX Fix tool recipes
 */ 
public class UtilsToolset {
	
	public static Map<Item, String> toollistsname = new HashMap<Item, String>();
	public static ListMultimap<String, Item> toollists = ArrayListMultimap.create();
	public static ListMultimap<Item, Item> craftingmap = ArrayListMultimap.create();
	public static ArrayList<Item> matlist = new ArrayList<Item>();
	
	/**
	 * Adds a toolset
	 * @param material The Tool material
	 * @param modid The Mod's modid
	 * @param toolmat Item used in creation of the tool
	 * @param name The name of the tool
	 */
	public static void toolsetGen(ToolMaterial material, String modid, Item toolmat, String name, CreativeTabs tab) {
		new UtilsPickaxe(material, name + "pickaxe", modid, toolmat, WordUtils.capitalize(name) + " Pickaxe", tab);
		new UtilsAxe(material, name + "axe", modid, toolmat, WordUtils.capitalize(name) + " Axe", tab);
		new UtilsSword(material, name + "sword", modid, toolmat, WordUtils.capitalize(name) + " Sword", tab);
		new UtilsHoe(material, name + "hoe", modid, toolmat, WordUtils.capitalize(name) + " Hoe", tab);
		new UtilsShovel(material, name + "shovel", modid, toolmat, WordUtils.capitalize(name) + " Shovel", tab);
	}
	
	/**
	 * Sets up a toolset based on {@link ToolEnum} provided
	 * @param material The tools {@link ToolMaterial} material
	 * @param name Unlocalized name of the item
	 * @param modid Modid of the mod creating it
	 * @param toolmat The tools crafting material
	 * @param translatedName The items ingame name
	 * @param type The tool type {@link ToolEnum}
	 */
	public UtilsToolset(ToolMaterial material, String name, String modid, Item toolmat, String translatedName, ToolEnum type, CreativeTabs tab) {
		switch (type) {
		case PICKAXE: new UtilsPickaxe(material, name, modid, toolmat, translatedName, tab); break;
		case AXE: new UtilsAxe(material, name, modid, toolmat, translatedName, tab); break;
		case HOE: new UtilsHoe(material, name, modid, toolmat, translatedName, tab); break;
		case SHOVEL: new UtilsShovel(material, name, modid, toolmat, translatedName, tab); break;
		case SWORD: new UtilsSword(material, name, modid, toolmat, translatedName, tab); break;
		default: break;
		}
	}
	
	public static class UtilsPickaxe extends ItemPickaxe {

		public UtilsPickaxe(ToolMaterial material, String name, String modid, Item toolmat, String translatedName, CreativeTabs tab) {
			super(material);			
			setCreativeTab(tab);
			matlist.add(toolmat);
			craftingmap.put(toolmat, this);
			setRegistryName(modid, name);
			setUnlocalizedName(this.getRegistryName().toString());
			if (this != null) {
				toollists.put(modid, this);
				toollistsname.put(this, name);
				UtilsItem.modItems.put(modid, this);
				UtilsItem.itemNameList.put(this, name);
				UtilsItem.translatedNameList.put(this, translatedName);
			}
		}
	}
	
	public static class UtilsTool extends ItemTool {

		public UtilsTool(ToolMaterial material, String name, String modid, String translatedName, Set<Block> effectiveBlocks, float attackSpeed, float attackDamage, CreativeTabs tab) {
			super(attackDamage, attackSpeed, material, effectiveBlocks);
			setCreativeTab(tab);
			setRegistryName(modid, name);
			setUnlocalizedName(this.getRegistryName().toString());
			if (this != null) {
				toollists.put(modid, this);
				toollistsname.put(this, name);
				UtilsItem.modItems.put(modid, this);
				UtilsItem.itemNameList.put(this, name);
				UtilsItem.translatedNameList.put(this, translatedName);
			}
		}
	}

	public static class UtilsAxe extends ItemAxe {

		public UtilsAxe(ToolMaterial material, String name, String modid, Item toolmat, String translatedName, CreativeTabs tab) {
			super(material, material.getAttackDamage(), material.getEfficiency());
			setCreativeTab(tab);
			matlist.add(toolmat);
			setRegistryName(modid, name);
			setUnlocalizedName(this.getRegistryName().toString());
			craftingmap.put(toolmat, this);
			if (this != null) {
				toollists.put(modid, this);
				toollistsname.put(this, name);
				UtilsItem.modItems.put(modid, this);
				UtilsItem.itemNameList.put(this, name);
				UtilsItem.translatedNameList.put(this, translatedName);
			}
		}
	}

	public static class UtilsSword extends ItemSword {

		public UtilsSword(ToolMaterial material, String name, String modid, Item toolmat, String translatedName, CreativeTabs tab) {
			super(material);
			setCreativeTab(tab);
			matlist.add(toolmat);
			setRegistryName(modid, name);
			setUnlocalizedName(this.getRegistryName().toString());
			craftingmap.put(toolmat, this);
			if (this != null) {
				toollists.put(modid, this);
				toollistsname.put(this, name);
				UtilsItem.modItems.put(modid, this);
				UtilsItem.itemNameList.put(this, name);
				UtilsItem.translatedNameList.put(this, translatedName);
			}
		}
	}

	public static class UtilsHoe extends ItemHoe {

		public UtilsHoe(ToolMaterial material, String name, String modid, Item toolmat, String translatedName, CreativeTabs tab) {
			super(material);
			this.setUnlocalizedName(modid + "_" + name);
			setCreativeTab(tab);
			matlist.add(toolmat);
			craftingmap.put(toolmat, this);
			setRegistryName(modid, name);
			if (this != null) {
				toollists.put(modid, this);
				toollistsname.put(this, name);
				UtilsItem.modItems.put(modid, this);
				UtilsItem.itemNameList.put(this, name);
				UtilsItem.translatedNameList.put(this, translatedName);
			}
		}
	}

	public static class UtilsShovel extends ItemSpade {

		public UtilsShovel(ToolMaterial material, String name, String modid, Item toolmat, String translatedName, CreativeTabs tab) {
			super(material);
			setCreativeTab(tab);
			matlist.add(toolmat);
			craftingmap.put(toolmat, this);
			setRegistryName(modid, name);
			setUnlocalizedName(this.getRegistryName().toString());
			if (this != null) {
				toollists.put(modid, this);
				toollistsname.put(this, name);
				UtilsItem.modItems.put(modid, this);
				UtilsItem.itemNameList.put(this, name);
				UtilsItem.translatedNameList.put(this, translatedName);
			}
		}
	}
	
	public enum ToolEnum {
		PICKAXE,
		AXE,
		SWORD,
		HOE,
		SHOVEL;
	}
	
	/**
	 * Method that automatically add recipes for tools
	 */
	// TODO Make type safe...
	public static void registerRecipes() {
		try {
			/*
			int runtime2 = 0;
		// Matlist contains the materials used for crafting tools
			for (int m = 0; matlist.size() > m; m++) {
			// crafting map is a multimap that has the key of the material to get to get a list of tools
				for (int c = 0; craftingmap.get(matlist.get(m)).size() > c; c++) {
					// XXX Fix later
					GameRegistry.addShapedRecipe(new ItemStack(craftingmap.get((Item) matlist.get(m)).get(runtime2), 1), new Object[]{
							"AAA",
							" B ",
							" B ",
							'A', matlist.get(m), 'B', Items.STICK
					});
					runtime2++;
					GameRegistry.addShapedRecipe(new ItemStack(craftingmap.get((Item) matlist.get(m)).get(runtime2), 1), new Object[]{
							"AA ",
							"AB ",
							" B ",
							'A', matlist.get(m), 'B', Items.STICK
					});
					GameRegistry.addShapedRecipe(new ItemStack(craftingmap.get((Item) matlist.get(m)).get(runtime2), 1), new Object[]{
						" AA",
						" BA",
						" B ",
						'A', matlist.get(m), 'B', Items.STICK
					});
					runtime2++;
					GameRegistry.addShapedRecipe(new ItemStack(craftingmap.get((Item) matlist.get(m)).get(runtime2), 1), new Object[]{
						" A ",
						" A ",
						" B ",
						'A', matlist.get(m), 'B', Items.STICK
					});
					runtime2++;
					GameRegistry.addShapedRecipe(new ItemStack(craftingmap.get((Item) matlist.get(m)).get(runtime2), 1), new Object[]{
						"AA ",
						" B ",
						" B ",
						'A', matlist.get(m), 'B', Items.STICK
					});
					GameRegistry.addShapedRecipe(new ItemStack(craftingmap.get((Item) matlist.get(m)).get(runtime2), 1), new Object[]{
						" AA",
						" B ",
						" B ",
						'A', matlist.get(m), 'B', Items.STICK
					});
					runtime2++;
					GameRegistry.addShapedRecipe(new ItemStack(craftingmap.get((Item) matlist.get(m)).get(runtime2), 1), new Object[]{
						" A ",
						" B ",
						" B ",
						'A', matlist.get(m), 'B', Items.STICK
					});
					
					UtilsHelper.addShapedRecipe(modid, name, output, params);
					runtime2 = 0;
				} 
			} */
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
}