package com.thenewjourney.blocks.reactor;

import com.thenewjourney.blocks.register.FaceRotatableBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class InjectorBeam extends FaceRotatableBlock {

    public InjectorBeam(String unlocalizedName) {
        super(unlocalizedName, Material.GLASS, 5f, 1200f);
        this.setLightLevel(.4F);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        if (state.getValue(FACING).equals(EnumFacing.EAST) || state.getValue(FACING).equals(EnumFacing.WEST)) {
            return new AxisAlignedBB(0D, 0.375D, 0.375D, 1D, 0.625D, 0.625D);
        }
        if (state.getValue(FACING).equals(EnumFacing.NORTH) || state.getValue(FACING).equals(EnumFacing.SOUTH)) {
            return new AxisAlignedBB(0.375D, 0.375D, 0D, 0.625D, 0.625D, 1D);
        }
        return new AxisAlignedBB(0, 0, 0, 1, 1, 1);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}