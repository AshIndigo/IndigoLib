package com.ashindigo.indigolib.modding;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;

public class UtilsPlayerHelper {

	public static Biome getPlayerBiome(EntityPlayer ep) {
		return ep.world.getChunkFromChunkCoords(ep.chunkCoordX, ep.chunkCoordZ).getBiome(new BlockPos(ep), ep.world.getBiomeProvider());
	}
	
	public static BlockPos getBlockPosFromRayTrace(EntityPlayer ep) {
		return rayTrace(ep).getBlockPos();
	}
	
	public static RayTraceResult rayTrace(EntityPlayer ep) {
		double reachDist = UtilsMod.proxy.getReachDistance(ep);
		Vec3d vec3d = ep.getPositionEyes(1.0F);
		Vec3d vec3d1 = ep.getLook(1.0F);
		Vec3d vec3d2 = vec3d.addVector(vec3d1.x * reachDist, vec3d1.y * reachDist, vec3d1.z * reachDist);
		return ep.world.rayTraceBlocks(vec3d, vec3d2, false, false, true);
	}
}
