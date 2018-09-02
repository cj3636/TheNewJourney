package com.thenewjourney.blocks.special;

import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.items.ModItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class FireFernRegistry extends BlockCrops {
	
	public FireFernRegistry(String unlocalizedName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);
		this.setDefaultState(this.withAge(0));
	}
	@Override
	public Item getSeed() {
		return ModItems.FireSeed;
	}
	@Override
	public Item getCrop() {
		return	ModItems.FireDust;
	}
	@Override
    protected boolean canSustainBush(IBlockState state) {
        return state.getBlock() == ModBlocks.ArcaneSoil;
    }
	@Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 0;
    }
	@Override
    public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return false;
    }
	@Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 0;
    }
}
