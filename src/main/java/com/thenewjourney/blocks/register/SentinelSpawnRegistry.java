package com.thenewjourney.blocks.register;

import com.cj3636.lib.Ref;
import com.thenewjourney.entity.lightning.ColoredLightningEntity;
import com.thenewjourney.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SentinelSpawnRegistry extends Block {

	public SentinelSpawnRegistry(String unlocalizedName, Material material, float hardness, float resistance) {
		super(material);

		this.setCreativeTab(Ref.CTAB);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);
		this.setHardness(hardness);
		this.setResistance(resistance);
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
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack heldItem = playerIn.getHeldItemMainhand();
		if (heldItem != null && heldItem.getItem().equals(ModItems.SteelIngot)) {
			worldIn.setRainStrength(4.0F);
			worldIn.spawnEntity(new ColoredLightningEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), true));
		}
		return false;
	}

}