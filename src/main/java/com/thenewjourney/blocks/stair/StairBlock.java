package com.thenewjourney.blocks.stair;

import com.cj3636.lib.Ref;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public class StairBlock extends BlockStairs {

    public StairBlock(IBlockState state, String unlocalizedName, float hardness, float resistance) {
        super(state);
        this.setCreativeTab(Ref.CTAB);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.useNeighborBrightness = true;
    }
}
