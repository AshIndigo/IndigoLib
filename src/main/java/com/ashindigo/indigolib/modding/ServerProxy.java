package com.ashindigo.indigolib.modding;

import net.minecraft.entity.player.EntityPlayer;

public class ServerProxy {

	public double getReachDistance(EntityPlayer e) {
		return e.getEntityAttribute(EntityPlayer.REACH_DISTANCE).getAttributeValue();
	}
}
