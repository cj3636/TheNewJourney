package com.thenewjourney.items.tool.purvia;

import com.cj3636.lib.Ref;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PurviaPickaxeRegistry extends ItemPickaxe {

	public PurviaPickaxeRegistry(String unlocalizedName, ToolMaterial material) {
	    super(material);
	    this.setUnlocalizedName(unlocalizedName);
	    this.setRegistryName(unlocalizedName);
	    this.setCreativeTab(Ref.CTAB);
	}
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		final IBlockState blockAt = worldIn.getBlockState(pos);
		if (worldIn.getBlockState(pos).getBlock().isToolEffective("pickaxe", blockAt)) {
			doShovel(worldIn, pos, playerIn, blockAt);
		}
		return EnumActionResult.PASS;
	}

	private void doShovel(World worldIn, BlockPos pos, EntityPlayer playerIn, IBlockState blockAt) {
		BlockPos start = pos.add(-3, 0, -3);
		for (int i = 0; i < 49; i++) {
			shovelSurrounding(worldIn, blockAt, playerIn, new BlockPos(start.getX() + (i % 7), start.getY(), start.getZ() + (i / 7)));
		}
	}

	private void shovelSurrounding(World worldIn, IBlockState blockAt, EntityPlayer playerIn, BlockPos blockPos) {
		IBlockState blockCheck = worldIn.getBlockState(blockPos);
		if (blockAt.equals(blockCheck)) {
			worldIn.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 3);
			if (!worldIn.isRemote) {
				EntityItem itemDrop = new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(blockAt.getBlock()));
				worldIn.spawnEntity(itemDrop);
			}
		}
	}
}
