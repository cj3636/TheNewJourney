package com.thenewjourney.power;

import com.thenewjourney.world.ModWorldSaveData;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.awt.*;

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

	public static boolean getRecipeCompletion(World worldIn, BlockPos pos, IBlockState blockState, int powerTier) {
		if (ModPower.getPowerTier(worldIn) < powerTier) {
			return false;
		}
		if (!ModPower.isFullPowered(worldIn)) {
			return false;
		}
		BlockPos pos_n = pos.north().up();
		BlockPos pos_n2 = pos.north(2).up();
		BlockPos pos_s = pos.south().up();
		BlockPos pos_s2 = pos.south(2).up();
		BlockPos pos_e = pos.east().up();
		BlockPos pos_e2 = pos.east(2).up();
		BlockPos pos_w = pos.west().up();
		BlockPos pos_w2 = pos.west(2).up();

		IBlockState block_n = worldIn.getBlockState(pos_n);
		IBlockState block_n2 = worldIn.getBlockState(pos_n2);
		IBlockState block_s = worldIn.getBlockState(pos_s);
		IBlockState block_s2 = worldIn.getBlockState(pos_s2);
		IBlockState block_e = worldIn.getBlockState(pos_e);
		IBlockState block_e2 = worldIn.getBlockState(pos_e2);
		IBlockState block_w = worldIn.getBlockState(pos_w);
		IBlockState block_w2 = worldIn.getBlockState(pos_w2);

		if (blockState.equals(block_n) && blockState.equals(block_s) && blockState.equals(block_e)
				&& blockState.equals(block_w)) {
			return true;
		}
		return blockState.equals(block_n2) && blockState.equals(block_s2) && blockState.equals(block_e2)
				&& blockState.equals(block_w2);
	}

	public static boolean getRecipeCompletionAndDestroy(World worldIn, BlockPos pos, IBlockState blockState,
			int powerTier) {
		if (ModPower.getPowerTier(worldIn) < powerTier) {
			return false;
		}
		if (!ModPower.isFullPowered(worldIn)) {
			return false;
		}
		BlockPos pos_n = pos.north().up();
		BlockPos pos_n2 = pos.north(2).up();
		BlockPos pos_s = pos.south().up();
		BlockPos pos_s2 = pos.south(2).up();
		BlockPos pos_e = pos.east().up();
		BlockPos pos_e2 = pos.east(2).up();
		BlockPos pos_w = pos.west().up();
		BlockPos pos_w2 = pos.west(2).up();

		IBlockState block_n = worldIn.getBlockState(pos_n);
		IBlockState block_n2 = worldIn.getBlockState(pos_n2);
		IBlockState block_s = worldIn.getBlockState(pos_s);
		IBlockState block_s2 = worldIn.getBlockState(pos_s2);
		IBlockState block_e = worldIn.getBlockState(pos_e);
		IBlockState block_e2 = worldIn.getBlockState(pos_e2);
		IBlockState block_w = worldIn.getBlockState(pos_w);
		IBlockState block_w2 = worldIn.getBlockState(pos_w2);

		if (blockState.equals(block_n) && blockState.equals(block_s) && blockState.equals(block_e)
				&& blockState.equals(block_w)) {
			worldIn.destroyBlock(pos_n, false);
			worldIn.destroyBlock(pos_s, false);
			worldIn.destroyBlock(pos_e, false);
			worldIn.destroyBlock(pos_w, false);
			return true;
		}
		if (blockState.equals(block_n2) && blockState.equals(block_s2) && blockState.equals(block_e2)
				&& blockState.equals(block_w2)) {
			worldIn.destroyBlock(pos_n2, false);
			worldIn.destroyBlock(pos_s2, false);
			worldIn.destroyBlock(pos_e2, false);
			worldIn.destroyBlock(pos_w2, false);
			return true;
		}
		return false;
	}
}
