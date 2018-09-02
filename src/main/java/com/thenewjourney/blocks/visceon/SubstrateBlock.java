package com.thenewjourney.blocks.visceon;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.blocks.portal.VisceonFirePortal;
import com.thenewjourney.blocks.portal.VisceonFlorusPortal;
import com.thenewjourney.items.ModItems;
import com.thenewjourney.power.ModPower;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SubstrateBlock extends Block {

    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    public SubstrateBlock(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setCreativeTab(Ref.CTAB);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }

    public static EnumFacing getFacing(int meta) {
        int i = meta & 7;
        return i > 5 ? null : EnumFacing.getFront(i);
    }

    public static EnumFacing getFacingFromEntity(BlockPos pos, EntityLivingBase p_185647_1_) {
        if (MathHelper.abs((float) p_185647_1_.posX - (float) pos.getX()) < 2.0F && MathHelper.abs((float) p_185647_1_.posZ - (float) pos.getZ()) < 2.0F) {
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

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = playerIn.getHeldItemMainhand();
        if (heldItem != null && heldItem.getItem().equals(ModItems.FireLight) && ModPower.isAtTier(worldIn, 4)) {
            BlockPos up = pos.up();
            BlockPos down = pos.down();
            BlockPos left = pos.east();
            BlockPos lefta = pos.north();
            BlockPos right = pos.west();
            BlockPos righta = pos.south();
            if (worldIn.getBlockState(up).getBlock().equals(ModBlocks.VisceonCore) && worldIn.getBlockState(down).getBlock().equals(ModBlocks.VisceonCore)) {
                if (worldIn.getBlockState(left).getBlock().equals(ModBlocks.VisceonCore) && worldIn.getBlockState(right).getBlock().equals(ModBlocks.VisceonCore)) {
                    worldIn.setBlockState(pos, ModBlocks.Sphere.getDefaultState().withProperty(VisceonFirePortal.AXIS, EnumFacing.Axis.Z));
                    if (!playerIn.capabilities.isCreativeMode) {
                        heldItem.setCount(heldItem.getCount() - 1);
                    }
                } else if (worldIn.getBlockState(lefta).getBlock().equals(ModBlocks.VisceonCore) && worldIn.getBlockState(righta).getBlock().equals(ModBlocks.VisceonCore)) {
                    worldIn.setBlockState(pos, ModBlocks.Sphere.getDefaultState().withProperty(VisceonFirePortal.AXIS, EnumFacing.Axis.X));
                    if (!playerIn.capabilities.isCreativeMode) {
                        heldItem.setCount(heldItem.getCount() - 1);
                    }
                } else {
                    if (!worldIn.isRemote) {
                        playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Invalid Configuration!"));
                    }
                }
            } else {
                if (!worldIn.isRemote) {
                    playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Invalid Configuration!"));
                }
            }
        } else if (heldItem != null && heldItem.getItem().equals(ModItems.FireLight)) {
            if (!worldIn.isRemote) {
                playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "World power must be at tier 4 or higher."));
            }
            if (heldItem != null && heldItem.getItem().equals(ModItems.AtriumLight) && ModPower.isAtTier(worldIn, 4)) {
                BlockPos up = pos.up();
                BlockPos down = pos.down();
                BlockPos left = pos.east();
                BlockPos lefta = pos.north();
                BlockPos right = pos.west();
                BlockPos righta = pos.south();
                if (worldIn.getBlockState(up).getBlock().equals(ModBlocks.VisceonCore) && worldIn.getBlockState(down).getBlock().equals(ModBlocks.VisceonCore)) {
                    if (worldIn.getBlockState(left).getBlock().equals(ModBlocks.VisceonCore) && worldIn.getBlockState(right).getBlock().equals(ModBlocks.VisceonCore)) {
                        worldIn.setBlockState(pos, ModBlocks.Spheref.getDefaultState().withProperty(VisceonFlorusPortal.AXIS, EnumFacing.Axis.Z));
                        if (!playerIn.capabilities.isCreativeMode) {
                            heldItem.setCount(heldItem.getCount() - 1);
                        }
                    } else if (worldIn.getBlockState(lefta).getBlock().equals(ModBlocks.VisceonCore) && worldIn.getBlockState(righta).getBlock().equals(ModBlocks.VisceonCore)) {
                        worldIn.setBlockState(pos, ModBlocks.Spheref.getDefaultState().withProperty(VisceonFlorusPortal.AXIS, EnumFacing.Axis.X));
                        if (!playerIn.capabilities.isCreativeMode) {
                            heldItem.setCount(heldItem.getCount() - 1);
                        }
                    } else {
                        if (!worldIn.isRemote) {
                            playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Invalid Configuration!"));
                        }
                    }
                } else {
                    if (!worldIn.isRemote) {
                        playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Invalid Configuration."));
                    }
                }
            } else if (heldItem != null && heldItem.getItem().equals(ModItems.AtriumLight)) {
                if (!worldIn.isRemote) {
                    playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "World power must be at tier 4 or higher."));
                }
            }
        }
        return true;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.375D, 0.375D, 0.375D, 0.625D, 0.625D, 0.625D);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, getFacing(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | state.getValue(FACING).getIndex();
        return i;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        this.setDefaultFacing(worldIn, pos, state);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack
            stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, getFacingFromEntity(pos, placer)), 2);
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
