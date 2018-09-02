package com.thenewjourney.blocks.duplicator;

import com.thenewjourney.Main.TheNewJourneyMod;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.particle.SmokingFireParticle;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class DuplicatorBlock extends BlockContainer {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private static boolean keepInventory;

    public DuplicatorBlock(String unlocalizedName) {
        super(Material.IRON);
        this.setUnlocalizedName(unlocalizedName);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setHardness(12F);
        this.setResistance(120F);
    }

    public static void setState(boolean active, World worldIn, BlockPos pos) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        keepInventory = true;
        worldIn.setBlockState(pos, ModBlocks.Purifier.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        keepInventory = false;
        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new DuplicatorTileEntity();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) return true;
        playerIn.openGui(TheNewJourneyMod.instance, DuplicatorGUIRegistry.getGuiID(), worldIn, pos.getX(), pos.getY(), pos.getZ());
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
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof DuplicatorTileEntity) {
            DuplicatorTileEntity DuplicatorTileEntity = (DuplicatorTileEntity) tileEntity;
            if (DuplicatorTileEntity.canGrind()) {
                EnumFacing enumfacing = state.getValue(FACING);
                double d0 = (double) pos.getX() + 0.5D;
                double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
                double d2 = (double) pos.getZ() + 0.5D;
                double d3 = 0.52D;
                double d4 = rand.nextDouble() * 0.6D - 0.3D;

                switch (enumfacing) {
                    case WEST:
                        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                        //Other directions
                        SmokingFireParticle fireParticle = new SmokingFireParticle(worldIn, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, 2.5F);
                        SmokingFireParticle fireParticle1 = new SmokingFireParticle(worldIn, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, 2.5F);
                        SmokingFireParticle fireParticle01 = new SmokingFireParticle(worldIn, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, 2.5F);
                        Minecraft.getMinecraft().effectRenderer.addEffect(fireParticle);
                        Minecraft.getMinecraft().effectRenderer.addEffect(fireParticle1);
                        Minecraft.getMinecraft().effectRenderer.addEffect(fireParticle01);
                        break;
                    case EAST:
                        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                        //Other directions
                        SmokingFireParticle fireParticle2 = new SmokingFireParticle(worldIn, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, 2.5F);
                        SmokingFireParticle fireParticle3 = new SmokingFireParticle(worldIn, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, 2.5F);
                        SmokingFireParticle fireParticle4 = new SmokingFireParticle(worldIn, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, 2.5F);
                        Minecraft.getMinecraft().effectRenderer.addEffect(fireParticle2);
                        Minecraft.getMinecraft().effectRenderer.addEffect(fireParticle3);
                        Minecraft.getMinecraft().effectRenderer.addEffect(fireParticle4);
                        break;
                    case NORTH:
                        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D);
                        //Other directions
                        SmokingFireParticle fireParticle5 = new SmokingFireParticle(worldIn, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, 2.5F);
                        SmokingFireParticle fireParticle6 = new SmokingFireParticle(worldIn, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, 2.5F);
                        SmokingFireParticle fireParticle7 = new SmokingFireParticle(worldIn, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, 2.5F);
                        Minecraft.getMinecraft().effectRenderer.addEffect(fireParticle5);
                        Minecraft.getMinecraft().effectRenderer.addEffect(fireParticle6);
                        Minecraft.getMinecraft().effectRenderer.addEffect(fireParticle7);
                        break;
                    case SOUTH:
                        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D);
                        //Other directions
                        SmokingFireParticle fireParticle8 = new SmokingFireParticle(worldIn, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, 2.5F);
                        SmokingFireParticle fireParticle9 = new SmokingFireParticle(worldIn, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, 2.5F);
                        SmokingFireParticle fireParticle0 = new SmokingFireParticle(worldIn, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, 2.5F);
                        Minecraft.getMinecraft().effectRenderer.addEffect(fireParticle8);
                        Minecraft.getMinecraft().effectRenderer.addEffect(fireParticle9);
                        Minecraft.getMinecraft().effectRenderer.addEffect(fireParticle0);
                }
            }
        }

    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
