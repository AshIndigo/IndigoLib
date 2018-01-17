package com.ashindigo.indigolib.modding.world;

import java.util.ArrayList;
import java.util.Random;

import com.ashindigo.indigolib.modding.UtilsBlockOre;

import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

/**
 * Manages worldgen from {@link UtilsBlockOre}
 * @author Ash Indigo
 */
// TODO Add more customization
public class UtilsWorldgen implements IWorldGenerator {

    	public static ArrayList<Block> overworldList = new ArrayList<Block>();
    	public static ArrayList<Block> netherList = new ArrayList<Block>();
    	public static ArrayList<Block> endList = new ArrayList<Block>();

	@Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case 0: oregenOverworld(random, chunkX * 16, chunkZ * 16, world); break;
            case 1: oregenEnd(random, chunkX * 16, chunkZ * 16, world); break;
            case -1: oregenNether(random, chunkX * 16, chunkZ * 16, world); break;
        }
    }
	
	public static void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int minVeinSize, int maxVeinSize, int chancesToSpawn, int minY, int maxY, Block blockgen) {
		WorldGenMinable minable = new WorldGenMinable(block.getDefaultState(), (minVeinSize + random.nextInt(maxVeinSize - minVeinSize)), BlockMatcher.forBlock(blockgen));
		for (int i = 0; i < chancesToSpawn; i++) {
			int posX = blockXPos + random.nextInt(16);
			int posY = minY + random.nextInt(maxY - minY);
			int posZ = blockZPos + random.nextInt(16);
			minable.generate(world, random, new BlockPos(posX, posY, posZ));
		}
	}

	/**
	 * Overwold ore gen method
	 */
	private static void oregenOverworld(Random random, int x, int z, World world) {
		for (int i = 0; overworldList.size() > i; i++) {
			addOreSpawn(overworldList.get(i), world, random, x, z, 10, 15, 8, 0, 128, Blocks.STONE);
		}
	}

	/**
	 * Nether ore gen method
	 */
	private static void oregenNether(Random random, int x, int z, World world) {
		for (int i = 0; netherList.size() > i; i++) {
			addOreSpawn(netherList.get(i), world, random, x, z, 10, 15, 8, 0, 128, Blocks.NETHERRACK);
		}
	}

	/**
	 * End ore gen method
	 */
	private static void oregenEnd(Random random, int x, int z, World world) {
		for (int i = 0; endList.size() > i; i++) {
			addOreSpawn(endList.get(i), world, random, x, z, 10, 15, 8, 0, 128, Blocks.END_STONE);
		}
	}
}