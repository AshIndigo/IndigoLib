package com.ashindigo.indigolib.modding;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * NBT Helper class with constants for magic numbers
 * @author Ash Indigo
 *
 */
public class UtilsNBTHelper {
	// getTagList types
	// Will be filled in as I go along
	public static final int STRING = 8;
	
	public static final byte BTRUE = 1;
	public static final byte BFALSE = 0;
	
	
	public static NBTTagCompound getPlayerPersistedTag(EntityPlayer ep) {
		return ep.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
	}
}
