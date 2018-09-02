package com.thenewjourney.blocks.register;

import com.thenewjourney.items.ModItems;
import com.thenewjourney.particle.BubblingParticle;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GobletRegistry extends Block {

	protected static final AxisAlignedBB BUSH_AABB = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D,
			0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);
	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	public GobletRegistry(String unlocalizedName, Material material, float hardness, float resistance,
			boolean ignoreSimilarity) {
		super(material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);
		this.setHardness(hardness);
		this.setResistance(resistance);
	}
	@SuppressWarnings("incomplete-switch")
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		EnumFacing enumfacing = state.getValue(FACING);
		switch (enumfacing) {
		case WEST:
			return ModItems.GobletOfFire;
		case EAST:
			return ModItems.GobletOfIce;
		case SOUTH:
			return ModItems.GobletOfEmerald;
		case NORTH:
			break;
		}
        return Item.getItemFromBlock(this);
    }

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, getFacing(meta));
	}

	public static EnumFacing getFacing(int meta) {
		int i = meta & 7;
		return i > 5 ? null : EnumFacing.getFront(i);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BUSH_AABB;
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		EnumFacing enumfacing = state.getValue(FACING);
		double dx = (double) pos.getX() + .5D;
		double dy = (double) pos.getY() + .5D;
		double dz = (double) pos.getZ() + .5D;
		double speedMult = 32D;
		float[] red = {1, 0, 0};
		float[] green = {0, 1, 0};
		float[] blue = {0, 0, 1};
		switch (enumfacing) {
		case WEST:
			BubblingParticle fireEffect = new BubblingParticle(worldIn, dx, dy, dz,
					-rand.nextDouble() / speedMult, rand.nextDouble() / speedMult, -rand.nextDouble() / speedMult, red);
			Minecraft.getMinecraft().effectRenderer.addEffect(fireEffect);
			BubblingParticle fireEffect2 = new BubblingParticle(worldIn, dx, dy, dz,
					rand.nextDouble() / speedMult, rand.nextDouble() / speedMult, rand.nextDouble() / speedMult, red);
			Minecraft.getMinecraft().effectRenderer.addEffect(fireEffect2);
			break;
		case EAST:
			BubblingParticle iceEffect = new BubblingParticle(worldIn, dx, dy, dz,
					-rand.nextDouble() / speedMult, rand.nextDouble() / speedMult, -rand.nextDouble() / speedMult, blue);
			Minecraft.getMinecraft().effectRenderer.addEffect(iceEffect);
			BubblingParticle iceEffect2 = new BubblingParticle(worldIn, dx, dy, dz,
					rand.nextDouble() / speedMult, rand.nextDouble() / speedMult, rand.nextDouble() / speedMult, blue);
			Minecraft.getMinecraft().effectRenderer.addEffect(iceEffect2);
			break;
		case SOUTH:
			BubblingParticle emeraldEffect = new BubblingParticle(worldIn, dx, dy, dz,
					-rand.nextDouble() / speedMult, rand.nextDouble() / speedMult, -rand.nextDouble() / speedMult, green);
			Minecraft.getMinecraft().effectRenderer.addEffect(emeraldEffect);
			BubblingParticle emeraldEffect2 = new BubblingParticle(worldIn, dx, dy, dz,
					rand.nextDouble() / speedMult, rand.nextDouble() / speedMult, rand.nextDouble() / speedMult, green);
			Minecraft.getMinecraft().effectRenderer.addEffect(emeraldEffect2);
			break;
		case NORTH:
			break;
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
