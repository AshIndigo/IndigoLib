package com.ashindigo.indigolib.modding;

import java.util.ArrayList;

import com.ashindigo.indigolib.modding.world.UtilsWorldgen;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
/**
 * Sub-Class dedicated to adding ores with automatic smelting and compression recipes added.
 * @author Ash Indigo
 */
public class UtilsBlockOre {

	public static ArrayList<Block> orelist = new ArrayList<Block>();
	public static ArrayList<Item> ingotlist = new ArrayList<Item>();
	private static ArrayList<Block> compressedlist = new ArrayList<Block>();

	/**
	 * Constructor for setting up the ore
	 * @param ingot The resulting item from the ore (Item)
	 * @param ore The ore block that will be smelted (Block)
	 * @param dim The dimension number 0: Overworld 1: Nether 2: End
	 * @param compressedblock The compressed version of the ingots
	 */
	public UtilsBlockOre(Item ingot, Block ore, int dim, Block compressedblock) {
		ingotlist.add(ingot);
		orelist.add(ore);
		compressedlist.add(compressedblock);
		switch (dim) {
        case 0: UtilsWorldgen.overworldList.add(ore); break;
        case -1: UtilsWorldgen.netherList.add(ore); break;
        case 1: UtilsWorldgen.endList.add(ore); break;
	}
    }
	/**
	 * Method to add smelting recipes to ores and compression reipes
	 */
	// XXX Hack fix
	public static void registerRecipes(){
		for (int i = 0; orelist.size() > i; i++) {
			GameRegistry.addSmelting((Block) orelist.get(i), new ItemStack ((Item) ingotlist.get(i), 1), 5);
			UtilsHelper.addShapedRecipe("indigoapi", compressedlist.get(i).getUnlocalizedName(), new ItemStack((Block) compressedlist.get(i)), new Object[]{ "AAA", "AAA", "AAA", 'A', ingotlist.get(i)});
			
			//GameRegistry.addShapelessRecipe(new ItemStack((Item) ingotlist.get(i), 9), new Object[]{ new ItemStack((Block) compressedlist.get(i), 1) });
		}
	}
}