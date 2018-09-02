package com.thenewjourney.blocks.distortedbricks;

import com.thenewjourney.blocks.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityDistortedBricks extends TileEntity implements ITickable {
    @Override
    public void update() {
    	if (this.world.getBlockState(this.getPos().down()).getBlock().equals(Blocks.FIRE)) {
    		if (this.world.getTotalWorldTime() % 1000L == 0L) {
            	this.world.setBlockState(this.getPos(), ModBlocks.ClearGlass.getDefaultState());
            }
		}
    }
}
