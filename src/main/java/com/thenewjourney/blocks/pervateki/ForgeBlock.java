package com.thenewjourney.blocks.pervateki;

import com.cj3636.lib.LocUtil;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.blocks.register.FaceRotatableBlockContainer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class ForgeBlock extends FaceRotatableBlockContainer {

    private final boolean isActive;

    public ForgeBlock(String unlocalizedName, Material material, float hardness, float resistance, boolean isActive) {
        super(unlocalizedName, material, hardness, resistance);
        this.isActive = isActive;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        BlockPos refpos = new BlockPos(pos.offset(facing.getOpposite()));
        BlockPos pos1 = new BlockPos(refpos.north().east());
        BlockPos pos2 = new BlockPos(refpos.north().west());
        BlockPos pos3 = new BlockPos(refpos.south().east());
        BlockPos pos4 = new BlockPos(refpos.south().west());
        Block block1 = worldIn.getBlockState(pos1).getBlock();
        Block block2 = worldIn.getBlockState(pos2).getBlock();
        Block block3 = worldIn.getBlockState(pos3).getBlock();
        Block block4 = worldIn.getBlockState(pos4).getBlock();
        Block block5 = worldIn.getBlockState(refpos).getBlock();

        if (LocUtil.checkFlatAndCenter(pos.down().offset(facing.getOpposite()), worldIn,
                ModBlocks.RefractoryBrick.getDefaultState())
                && LocUtil.checkFlatAndCenter(pos.offset(facing.getOpposite()).up(), worldIn,
                ModBlocks.RefractoryBrick.getDefaultState())) {
            if (block1.equals(ModBlocks.RefractoryBrick) && block2.equals(ModBlocks.RefractoryBrick)
                    && block3.equals(ModBlocks.RefractoryBrick) && block4.equals(ModBlocks.RefractoryBrick)
                    && block5.equals(ModBlocks.PervatekiForge)) {
                checkGoblet(worldIn, refpos, facing);
            }

        }
        return true;
    }

    private void checkGoblet(World worldIn, BlockPos pos, EnumFacing side) {
        IBlockState block1 = worldIn.getBlockState(pos.offset(side.rotateYCCW()));
        IBlockState block2 = worldIn.getBlockState(pos.offset(side.rotateYCCW().rotateYCCW()));
        IBlockState block3 = worldIn.getBlockState(pos.offset(side.rotateYCCW().rotateYCCW().rotateYCCW()));
        ArrayList<IBlockState> blockList = new ArrayList<IBlockState>();
        blockList.add(block1);
        blockList.add(block2);
        blockList.add(block3);
        ArrayList<IBlockState> blockCheck = new ArrayList<IBlockState>();
        blockCheck.add(ModBlocks.GobletBlock.getDefaultState()
                .withProperty(com.thenewjourney.blocks.register.GobletRegistry.FACING, EnumFacing.WEST));
        blockCheck.add(ModBlocks.GobletBlock.getDefaultState()
                .withProperty(com.thenewjourney.blocks.register.GobletRegistry.FACING, EnumFacing.EAST));
        blockCheck.add(ModBlocks.GobletBlock.getDefaultState()
                .withProperty(com.thenewjourney.blocks.register.GobletRegistry.FACING, EnumFacing.SOUTH));

        if (blockList.containsAll(blockCheck)) {
            worldIn.setBlockState(pos.offset(side), ModBlocks.ForgeControllerActive.getDefaultState());
        }
    }

    public static void setState(boolean active, World worldIn, BlockPos pos) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (active) {
            worldIn.setBlockState(pos, ModBlocks.ForgeControllerActive.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, ModBlocks.ForgeControllerActive.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        } else {
            worldIn.setBlockState(pos, ModBlocks.ForgeController.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, ModBlocks.ForgeController.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }

        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new ForgeBlockTileEntity(isActive);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}