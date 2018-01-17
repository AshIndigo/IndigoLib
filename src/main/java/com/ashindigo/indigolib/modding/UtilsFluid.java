package com.ashindigo.indigolib.modding;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

/**
 * This is a class used for making a fluid.
 * @author Ash Indigo
 * 
 */
// XXX This code probably doesnt even work anymore, delete until it needs to remade?
// XXX Or possibly add a method to make fluids and let the constructors remain separate
public class UtilsFluid extends Fluid {

	public UtilsFluid(String fluidName, ResourceLocation still, ResourceLocation flowing, String fluidBlockName, Material material, String modid) {
		super(fluidName, still, flowing);
		FluidRegistry.registerFluid(this);
		FluidRegistry.addBucketForFluid(this);
		setUnlocalizedName(fluidName);
		new UtilsFluidBlock(this, material, fluidBlockName, modid);
	}
	public static class UtilsFluidBlock extends BlockFluidClassic {

		public UtilsFluidBlock(Fluid fluid, Material material, String name, String modid) {
			super(fluid, material);
			setUnlocalizedName(name);
			ForgeRegistries.BLOCKS.register(this);
			ForgeRegistries.ITEMS.register(new ItemBlock(this));
		}

	}
}
