package com.thenewjourney.blocks.special;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FrameBlock extends Block {

    public FrameBlock(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);
        this.setCreativeTab(Ref.CTAB);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.375D, 0.000D, 0.375D, 0.625D, 1.000D, 0.625D);
    }

    @Override
    public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) {
        return true;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = playerIn.getHeldItemMainhand();
        if (GuiScreen.isShiftKeyDown()) {
            worldIn.destroyBlock(pos, true);
            for (int i = 1; i < 255; i++) {
                if (worldIn.getBlockState(pos.up(i)).getBlock().equals(ModBlocks.Frame)) {
                    worldIn.destroyBlock(pos.up(i), true);
                } else {
                    return false;
                }
            }
            return false;
        }
        if (heldItem == null) {
            return true;
        }
        if (heldItem.getCount() < 8 && !playerIn.capabilities.isCreativeMode) {
            return true;
        }
        if (heldItem != null && heldItem.getItem().equals(Item.getItemFromBlock(ModBlocks.Frame))) {
            for (int i = 1; i < 8; i++) {
                if (worldIn.getBlockState(pos.up(i)).getBlock().equals(Blocks.AIR)) {
                    worldIn.setBlockState(pos.up(i), ModBlocks.Frame.getDefaultState());
                    if (!playerIn.capabilities.isCreativeMode) {
                        heldItem.setCount(heldItem.getCount() - 1);
                        if (heldItem.getCount() < 1) {
                            heldItem = null;
                        }
                    }
                } else {
                    return true;
                }
            }
            return true;
        }
        if (heldItem.getCount() != 64 && !playerIn.capabilities.isCreativeMode) {
            return true;
        }
        if (heldItem != null && heldItem.getItem().equals(Item.getItemFromBlock(ModBlocks.FlorusBlock))) {
            for (int i = 0; i < 8; i++) {
                if (worldIn.getBlockState(pos.up(i)).getBlock().equals(ModBlocks.Frame)) {
                    placeFlorus(worldIn, pos.up(i), heldItem, playerIn);
                }
            }
        }
        return true;

    }

    private void placeFlorus(World worldIn, BlockPos pos, ItemStack heldItem, EntityPlayer playerIn) {
        BlockPos[] posList = {pos.north(), pos.south(), pos.east(), pos.west(), pos.north().east(), pos.north().west(),
                pos.south().east(), pos.south().west()};

        for (BlockPos posAt : posList) {
            if (worldIn.getBlockState(posAt).getBlock().equals(Blocks.AIR)) {
                worldIn.setBlockState(posAt, ModBlocks.FlorusBlock.getDefaultState());
                if (!playerIn.capabilities.isCreativeMode) {
                    heldItem.setCount(heldItem.getCount() - 1);
                    if (heldItem.getCount() < 1) {
                        heldItem = null;
                    }
                }
            }
        }
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
}
