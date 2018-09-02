package com.thenewjourney.blocks.visceon;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.blocks.portal.VisceonFirePortal;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VisceonCoreBlock extends Block {

	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final PropertyBool EXTENDED = PropertyBool.create("extended");

	public VisceonCoreBlock(String unlocalizedName, Material material, float hardness, float resistance) {
		super(material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH)
				.withProperty(EXTENDED, Boolean.valueOf(false)));
		this.setCreativeTab(Ref.CTAB);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);
		this.setHardness(hardness);
		this.setResistance(resistance);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		super.breakBlock(worldIn, pos, state);
		EnumFacing enumFacing = state.getValue(FACING);
		if (worldIn.getBlockState(pos.offset(enumFacing)).getBlock().equals(ModBlocks.Sphere)
				|| worldIn.getBlockState(pos.offset(enumFacing)).getBlock().equals(ModBlocks.Spheref)) {
			EnumFacing.Axis axisIn = worldIn.getBlockState(pos.offset(enumFacing)).getValue(VisceonFirePortal.AXIS);
			switch (axisIn) {
			case X:
				worldIn.setBlockState(pos.offset(enumFacing),
						ModBlocks.Substrate.getDefaultState().withProperty(FACING, EnumFacing.EAST));
				break;
			case Y:
				worldIn.setBlockState(pos.offset(enumFacing),
						ModBlocks.Substrate.getDefaultState().withProperty(FACING, EnumFacing.DOWN));
				break;
			case Z:
				worldIn.setBlockState(pos.offset(enumFacing),
						ModBlocks.Substrate.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
				break;
			}
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (GuiScreen.isShiftKeyDown()) {
			worldIn.setBlockState(pos, state.withProperty(EXTENDED, Boolean.valueOf(false)));
		} else {
			worldIn.setBlockState(pos, state.withProperty(FACING, facing).withProperty(EXTENDED, true));
		}
		return true;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(EXTENDED,
				Boolean.valueOf(meta > 0));
	}

	public static EnumFacing getFacing(int meta) {
		int i = meta & 7;
		return i > 5 ? null : EnumFacing.getFront(i);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | state.getValue(FACING).getIndex();

		if (state.getValue(EXTENDED).booleanValue()) {
			i |= 8;
		}

		return i;
	}

	/**
	 * Returns the blockstate with the given rotation from the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 */
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 */
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING, EXTENDED);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.375D, 0.375D, 0.375D, 0.625D, 0.625D, 0.625D);
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
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, getFacingFromEntity(pos, placer)), 2);
	}

	public static EnumFacing getFacingFromEntity(BlockPos pos, EntityLivingBase p_185647_1_) {
		if (MathHelper.abs((float) p_185647_1_.posX - (float) pos.getX()) < 2.0F
				&& MathHelper.abs((float) p_185647_1_.posZ - (float) pos.getZ()) < 2.0F) {
			double d0 = p_185647_1_.posY + (double) p_185647_1_.getEyeHeight();

			if (d0 - (double) pos.getY() > 2.0D) {
				return EnumFacing.UP;
			}

			if ((double) pos.getY() - d0 > 0.0D) {
				return EnumFacing.DOWN;
			}
		}

		return p_185647_1_.getHorizontalFacing().getOpposite();
	}
}
