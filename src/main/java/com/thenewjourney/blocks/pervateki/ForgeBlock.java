package com.thenewjourney.blocks.pervateki;

import com.cj3636.lib.LocUtil;
import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.blocks.furnace.tileentity.AndesiteFurnaceTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class ForgeBlock extends Block implements ITileEntityProvider {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	private final boolean isActive;

	public ForgeBlock(boolean isActive, String unlocalizedName, Material material, float hardness, float resistance) {
		super(material);
		this.isActive = isActive;
		this.setCreativeTab(Ref.CTAB);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
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
				runGoblet(worldIn, refpos, facing);
			}

		}
		return true;
	}

	private void runGoblet(World worldIn, BlockPos pos, EnumFacing side) {
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
				worldIn.setBlockState(pos.offset(side), ModBlocks.ForgeControllerOn.getDefaultState());
		}
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

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof AndesiteFurnaceTileEntity) {
                ((AndesiteFurnaceTileEntity) tileentity).setCustomInventoryName(stack.getDisplayName());
            }
        }
    }
    
    public static void setState(boolean active, World worldIn, BlockPos pos) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (active) {
            worldIn.setBlockState(pos, ModBlocks.ForgeControllerOn.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, ModBlocks.ForgeControllerOn.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
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
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
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