package com.thenewjourney.blocks.special;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class Weed extends BlockBush implements IGrowable, net.minecraftforge.common.IShearable {
    public static final PropertyEnum<net.minecraft.block.BlockDoublePlant.EnumBlockHalf> HALF = PropertyEnum.<net.minecraft.block.BlockDoublePlant.EnumBlockHalf>create("half", net.minecraft.block.BlockDoublePlant.EnumBlockHalf.class);
    public static final PropertyEnum<EnumFacing> FACING = BlockHorizontal.FACING;

    public Weed() {
        super(Material.VINE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(HALF, net.minecraft.block.BlockDoublePlant.EnumBlockHalf.LOWER).withProperty(FACING, EnumFacing.NORTH));
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.setUnlocalizedName("weed");
        this.setRegistryName("weed");
    }
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(FACING).getHorizontalIndex();
    }
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return FULL_BLOCK_AABB;
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.up());
    }


    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (!this.canBlockStay(worldIn, pos, state)) {
            boolean flag = state.getValue(HALF) == net.minecraft.block.BlockDoublePlant.EnumBlockHalf.UPPER;
            BlockPos blockpos = flag ? pos : pos.up();
            BlockPos blockpos1 = flag ? pos.down() : pos;
            Block block = (Block) (flag ? this : worldIn.getBlockState(blockpos).getBlock());
            Block block1 = (Block) (flag ? worldIn.getBlockState(blockpos1).getBlock() : this);

            if (!flag) this.dropBlockAsItem(worldIn, pos, state, 0); //Forge move above the setting to air.

            if (block == this) {
                worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
            }

            if (block1 == this) {
                worldIn.setBlockState(blockpos1, Blocks.AIR.getDefaultState(), 3);
            }
        }
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        if (state.getBlock() != this)
            return super.canBlockStay(worldIn, pos, state); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        if (state.getValue(HALF) == net.minecraft.block.BlockDoublePlant.EnumBlockHalf.UPPER) {
            return worldIn.getBlockState(pos.down()).getBlock() == this;
        } else {
            IBlockState iblockstate = worldIn.getBlockState(pos.up());
            return iblockstate.getBlock() == this && super.canBlockStay(worldIn, pos, iblockstate);
        }
    }


    public void placeAt(World worldIn, BlockPos lowerPos, net.minecraft.block.BlockDoublePlant.EnumPlantType variant, int flags) {
        worldIn.setBlockState(lowerPos, this.getDefaultState().withProperty(HALF, net.minecraft.block.BlockDoublePlant.EnumBlockHalf.LOWER), flags);
        worldIn.setBlockState(lowerPos.up(), this.getDefaultState().withProperty(HALF, net.minecraft.block.BlockDoublePlant.EnumBlockHalf.UPPER), flags);
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(HALF, net.minecraft.block.BlockDoublePlant.EnumBlockHalf.UPPER), 2);
    }

    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack) {
        {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }

    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (state.getValue(HALF) == net.minecraft.block.BlockDoublePlant.EnumBlockHalf.UPPER) {
            if (worldIn.getBlockState(pos.down()).getBlock() == this) {

            }
        } else if (worldIn.getBlockState(pos.up()).getBlock() == this) {
            worldIn.setBlockState(pos.up(), Blocks.AIR.getDefaultState(), 2);
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }


    /**
     * Whether this IGrowable can grow
     */
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        spawnAsEntity(worldIn, pos, new ItemStack(this, 1));
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        return (meta & 8) > 0 ? this.getDefaultState().withProperty(HALF, net.minecraft.block.BlockDoublePlant.EnumBlockHalf.UPPER) : this.getDefaultState().withProperty(HALF, net.minecraft.block.BlockDoublePlant.EnumBlockHalf.LOWER);
    }


    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{HALF, FACING});
    }

    /**
     * Get the OffsetType for this Block. Determines if the model is rendered slightly offset.
     */
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType() {
        return Block.EnumOffsetType.XZ;
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return null;
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        //Forge: Break both parts on the client to prevent the top part flickering as default type for a few frames.
        if (state.getBlock() == this && state.getValue(HALF) == net.minecraft.block.BlockDoublePlant.EnumBlockHalf.LOWER && world.getBlockState(pos.up()).getBlock() == this)
            world.setBlockToAir(pos.up());
        return world.setBlockToAir(pos);
    }

    public static enum EnumBlockHalf implements IStringSerializable {
        UPPER,
        LOWER;

        public String toString() {
            return this.getName();
        }

        public String getName() {
            return this == UPPER ? "upper" : "lower";
        }
    }
}