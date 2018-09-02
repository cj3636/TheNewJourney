package com.thenewjourney.blocks.arcane;

import com.cj3636.lib.LocUtil;
import com.thenewjourney.Main.TheNewJourneyMod;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.power.ModPower;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by cj3636 on 1/27/2017.
 */
public class ArcaneFurnaceBlock extends BlockContainer {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private static boolean keepInventory;

    public ArcaneFurnaceBlock(String unlocalizedName) {
        super(Material.IRON);
        this.setRegistryName(unlocalizedName);
        this.setUnlocalizedName(unlocalizedName);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setHardness(12F);
        this.setResistance(120F);
    }

    public static void setState(boolean active, World worldIn, BlockPos pos) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        keepInventory = true;
        if (active) {
            worldIn.setBlockState(pos, ModBlocks.BlastFurnaceActive.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, ModBlocks.BlastFurnaceActive.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        } else {
            worldIn.setBlockState(pos, ModBlocks.BlastFurnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, ModBlocks.BlastFurnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }
        keepInventory = false;
        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Nullable
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new ArcaneFurnaceTileEntity();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) return true;
        if (ModPower.getPowerTier(worldIn) < 2) {
        	playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Too low of power tier! At least 2 is required."));
        	return true;
        }
        if (LocUtil.checkVertical(pos, worldIn, ModBlocks.WarpStone.getDefaultState(), side) &&
                LocUtil.checkVerticalNoTop(pos.offset(side.getOpposite()), worldIn, ModBlocks.WarpStone.getDefaultState(), side) &&
                LocUtil.checkVerticalAndCenter(pos.offset(side.getOpposite(), 2), worldIn, ModBlocks.WarpStone.getDefaultState(), side)) {
            playerIn.openGui(TheNewJourneyMod.instance, ArcaneFurnaceGUIRegistry.getGuiID(), worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        } else {
            if (!worldIn.isRemote) {
                playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Invalid setup! Furnace is Facing: " + side));
                playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Invalid setup! Furnace is Facing: " + LocUtil.checkVertical(pos, worldIn, ModBlocks.WarpStone.getDefaultState(), side)));
                playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Invalid setup! Furnace is Facing: " + LocUtil.checkVerticalNoTop(pos.offset(side.getOpposite()), worldIn, ModBlocks.WarpStone.getDefaultState(), side)));
                playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Invalid setup! Furnace is Facing: " + LocUtil.checkVerticalAndCenter(pos.offset(side.getOpposite()).offset(side.getOpposite()), worldIn, ModBlocks.WarpStone.getDefaultState(), side)));
                return true;
            }
        }
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (!keepInventory) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof IInventory) {
                InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileEntity);
            }
            super.breakBlock(worldIn, pos, state);
        }
    }

    @SideOnly(Side.CLIENT)
    public IBlockState getStateForEntityRender(IBlockState state) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            Block block = worldIn.getBlockState(pos.north()).getBlock();
            Block block1 = worldIn.getBlockState(pos.south()).getBlock();
            Block block2 = worldIn.getBlockState(pos.west()).getBlock();
            Block block3 = worldIn.getBlockState(pos.east()).getBlock();
            EnumFacing enumfacing = state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && block.isFullBlock(state) && !block1.isFullBlock(state)) {
                enumfacing = EnumFacing.SOUTH;
            } else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock(state) && !block.isFullBlock(state)) {
                enumfacing = EnumFacing.NORTH;
            } else if (enumfacing == EnumFacing.WEST && block2.isFullBlock(state) && !block3.isFullBlock(state)) {
                enumfacing = EnumFacing.EAST;
            } else if (enumfacing == EnumFacing.EAST && block3.isFullBlock(state) && !block2.isFullBlock(state)) {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("incomplete-switch")
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
