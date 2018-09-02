package com.thenewjourney.blocks.register;

import com.cj3636.lib.Ref;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class GrassRegistry extends Block {
    public static final PropertyBool SNOWY = PropertyBool.create("snowy");

    public GrassRegistry(String unlocalizedName, float hardness, float resistance) {
        super(Material.GRASS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(SNOWY, Boolean.valueOf(false)));
        this.setTickRandomly(true);
        this.setCreativeTab(Ref.CTAB);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setTickRandomly(true);
    }

    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Blocks.DIRT.getItemDropped(Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT), rand, fortune);
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
        Block blockN = worldIn.getBlockState(pos.north()).getBlock();
        Block blockS = worldIn.getBlockState(pos.south()).getBlock();
        Block blockE = worldIn.getBlockState(pos.east()).getBlock();
        Block blockW = worldIn.getBlockState(pos.west()).getBlock();
        Block blockNu = worldIn.getBlockState(pos.north().up()).getBlock();
        Block blockSu = worldIn.getBlockState(pos.south().up()).getBlock();
        Block blockEu = worldIn.getBlockState(pos.east().up()).getBlock();
        Block blockWu = worldIn.getBlockState(pos.west().up()).getBlock();
        if (blockN.equals(Blocks.GRASS)) {
            worldIn.setBlockState(pos.north(), this.getDefaultState());
        }
        if (blockS.equals(Blocks.GRASS)) {
            worldIn.setBlockState(pos.south(), this.getDefaultState());
        }
        if (blockE.equals(Blocks.GRASS)) {
            worldIn.setBlockState(pos.east(), this.getDefaultState());
        }
        if (blockW.equals(Blocks.GRASS)) {
            worldIn.setBlockState(pos.west(), this.getDefaultState());
        }
        if (blockNu.equals(Blocks.GRASS)) {
            worldIn.setBlockState(pos.north().up(), this.getDefaultState());
        }
        if (blockSu.equals(Blocks.GRASS)) {
            worldIn.setBlockState(pos.south().up(), this.getDefaultState());
        }
        if (blockEu.equals(Blocks.GRASS)) {
            worldIn.setBlockState(pos.east().up(), this.getDefaultState());
        }
        if (blockWu.equals(Blocks.GRASS)) {
            worldIn.setBlockState(pos.west().up(), this.getDefaultState());
        }
    }

    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        Block block = worldIn.getBlockState(pos.up()).getBlock();
        return state.withProperty(SNOWY, Boolean.valueOf(block == Blocks.SNOW || block == Blocks.SNOW_LAYER));
    }

    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{SNOWY});
    }
}