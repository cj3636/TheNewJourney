package com.thenewjourney.blocks.bush;

import com.thenewjourney.blocks.ModBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

import java.util.Random;

public class BerryBushTileEntity extends TileEntity implements ITickable {
    Random random = new Random();
    @Override
    public void update() {
        if (world.getBlockState(pos).equals(ModBlocks.NarcoBerryBush.getDefaultState())) {
            return;
        }
        if (random.nextInt(3000) == 13) {
            world.setBlockState(pos, ModBlocks.NarcoBerryBush.getDefaultState());
        }
    }
}
