package com.ashindigo.indigolib.modding;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ClientProxy extends ServerProxy {

	public double getReachDistance(EntityPlayer e) {
		return Minecraft.getMinecraft().playerController.getBlockReachDistance();
	}
}
