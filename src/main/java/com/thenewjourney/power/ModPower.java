package com.thenewjourney.power;

import com.thenewjourney.world.ModWorldSaveData;
import net.minecraft.world.World;

import java.awt.*;
//Moving over to player capabilities system
@Deprecated
public class ModPower {

	// Returns power tier
	public static int getPowerTier(World worldIn) {
		ModWorldSaveData data = ModWorldSaveData.forWorld(worldIn);
		return data.getPowerTier();
	}

	// Return boolean if the world is at the specified tier
	public static boolean isAtTier(World worldIn, int tier) {
		return (tier <= getPowerTier(worldIn));
	}

	// returns if the world is powered at all yet
	public static boolean isPowered(World worldIn) {
		ModWorldSaveData data = ModWorldSaveData.forWorld(worldIn);
		return (data.getPowerNum() > 0) && (data.getPowerTier() > 0);
	}

	// return if the world has reached a specified power tier and is currently
	// powered
	public static boolean isPoweredAtTier(World worldIn, int tier) {
		return (isAtTier(worldIn, tier) && isPowered(worldIn));
	}

	// returns if the world has 8+ power sources
	public static boolean isFullPowered(World worldIn) {
		ModWorldSaveData data = ModWorldSaveData.forWorld(worldIn);
		return (data.getPowerNum() > 7) && (data.getPowerTier() > 0);
	}

	// returns if the world is at the specified tier AND has 8+ power
	public static boolean isFullPoweredAtTier(World worldIn, int tier) {
		return (isAtTier(worldIn, tier) && isFullPowered(worldIn));
	}

	public static int getPowerNum(World worldIn) {
		ModWorldSaveData data = ModWorldSaveData.forWorld(worldIn);
		return data.getPowerNum();
	}

	public static boolean isNumPoweredAtTier(World worldIn, int tier, int powerNum) {
		return (isAtTier(worldIn, tier) && (getPowerNum(worldIn) >= powerNum));
	}

	public static Color getPowerColor(World worldIn) {
		ModWorldSaveData data = ModWorldSaveData.forWorld(worldIn);
		Color finalColor = Color.BLACK;

		switch (data.getPowerTier()) {
		case 1:
			finalColor = EnumPowerColor.getTierColor(EnumPowerColor.TIERONE);
			break;
		case 2:
			finalColor = EnumPowerColor.getTierColor(EnumPowerColor.TIERTWO);
			break;
		case 3:
			finalColor = EnumPowerColor.getTierColor(EnumPowerColor.TIERTHREE);
			break;
		case 4:
			finalColor = EnumPowerColor.getTierColor(EnumPowerColor.TIERFOUR);
			break;
		case 5:
			finalColor = EnumPowerColor.getTierColor(EnumPowerColor.TIERFIVE);
			break;
		case 6:
			finalColor = EnumPowerColor.getTierColor(EnumPowerColor.TIERSIX);
			break;
		case 7:
			finalColor = EnumPowerColor.getTierColor(EnumPowerColor.TIERSEVEN);
			break;
		case 8:
			finalColor = EnumPowerColor.getTierColor(EnumPowerColor.TIEREIGHT);
			break;
		}
		return finalColor;
	}
}
