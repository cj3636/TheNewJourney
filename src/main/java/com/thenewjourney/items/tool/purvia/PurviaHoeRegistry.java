package com.thenewjourney.items.tool.purvia;

import com.cj3636.lib.Ref;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class PurviaHoeRegistry extends ItemHoe {

    public PurviaHoeRegistry(String unlocalizedName, ToolMaterial material) {
        super(material);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        final IBlockState blockAt = worldIn.getBlockState(pos);
        for (ItemStack s : OreDictionary.getOres("treeLeaves")) {
            if (worldIn.getBlockState(pos).getBlock().equals(Block.getBlockFromItem(s.getItem()))) {
                mineLeaf(worldIn, pos, playerIn, blockAt);
            }
        }
        for (ItemStack s : OreDictionary.getOres("cropWheat")) {
            if (worldIn.getBlockState(pos).getBlock().equals(Block.getBlockFromItem(s.getItem()))) {
                mineLeaf(worldIn, pos, playerIn, blockAt);
            }
        }
        for (ItemStack s : OreDictionary.getOres("cropCarrot")) {
            if (worldIn.getBlockState(pos).getBlock().equals(Block.getBlockFromItem(s.getItem()))) {
                mineLeaf(worldIn, pos, playerIn, blockAt);
            }
        }
        for (ItemStack s : OreDictionary.getOres("cropPotato")) {
            if (worldIn.getBlockState(pos).getBlock().equals(Block.getBlockFromItem(s.getItem()))) {
                mineLeaf(worldIn, pos, playerIn, blockAt);
            }
        }
        if (worldIn.getBlockState(pos).getBlock().equals(Blocks.GRASS)) {
            tillGrass(worldIn, pos, playerIn, blockAt);
        }
        return EnumActionResult.PASS;
    }

    private void tillGrass(World worldIn, BlockPos pos, EntityPlayer playerIn, IBlockState blockAt) {
        BlockPos start = pos.add(-4, 0, -4);
        for (int i = 0; i < 64; i++) {
            tillGrassSurrounding(worldIn, blockAt, playerIn, new BlockPos(start.getX() + (i % 8), start.getY(), start.getZ() + (i / 8)));
        }
    }

    private void tillGrassSurrounding(World worldIn, IBlockState blockAt, EntityPlayer playerIn, BlockPos blockPos) {
        IBlockState blockCheck = worldIn.getBlockState(blockPos);
        if (blockAt.equals(blockCheck)) {
            worldIn.setBlockState(blockPos, Blocks.FARMLAND.getDefaultState());
        }
    }

    private void mineLeaf(World worldIn, BlockPos pos, EntityPlayer playerIn, IBlockState blockAt) {
        BlockPos start = pos.add(-4, -1, -4);
        for (int i = 0; i < 64; i++) {
            mineLeafColumn(worldIn, blockAt, playerIn, new BlockPos(start.getX() + (i % 8), start.getY(), start.getZ() + (i / 8)));
        }
    }

    private void mineLeafColumn(World worldIn, IBlockState blockAt, EntityPlayer playerIn, BlockPos blockPos) {
        for (int i = 0; i < 64; i++) {
            BlockPos posAt = blockPos.up(i);
            IBlockState blockCheck = worldIn.getBlockState(posAt);
            if (blockAt.equals(blockCheck)) {
                worldIn.setBlockState(posAt, Blocks.AIR.getDefaultState(), 3);
                if (!worldIn.isRemote) {
                    EntityItem itemDrop = new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(blockAt.getBlock()));
                    worldIn.spawnEntity(itemDrop);
                }
            }
        }
    }
}
