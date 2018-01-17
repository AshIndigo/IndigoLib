package com.ashindigo.indigolib.modding;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.GameData;

/**
 * Random helpful methods
 * @author Ash
 */
public class UtilsHelper {
	/**
	 * Adds a shaped oredict recipe to the workbench
	 * @param modid The mods modid
	 * @param name Name of the recipe
	 * @param output What the recipe outputs
	 * @param params The recipe layout and keys (Ore dict'd)
	 */
	// Thanks modmuss50!
	public static void addShapedOreRecipe(String modid, String name, ItemStack output, Object... params) {
		ResourceLocation location = new ResourceLocation(modid,name);
		ShapedOreRecipe recipe = new ShapedOreRecipe(location, output, params);
		recipe.setRegistryName(location);
		GameData.register_impl(recipe);
	}
	
	/**
	 * Adds a shaped recipe to the workbench
	 * @param modid The mods modid
	 * @param name Name of the recipe
	 * @param output What the recipe outputs
	 * @param params The recipe layout and keys (Non oredict'd)
	 */
	public static void addShapedRecipe(String modid, String name, ItemStack output, Object... params) {
		CraftingHelper.ShapedPrimer primer = CraftingHelper.parseShaped(params);
		ShapedRecipes recipe = new ShapedRecipes(output.getItem().getRegistryName().toString(), primer.width, primer.height, primer.input, output);
		recipe.setRegistryName(new ResourceLocation(modid, name));
		GameData.register_impl(recipe);
	}
}
