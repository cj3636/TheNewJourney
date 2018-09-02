package com.thenewjourney.blocks.bush;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.items.ModItems;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BerryBush extends BlockBreakable implements ITileEntityProvider {

    public BerryBush(String unlocalizedName, Material material, float hardness, float resistance, boolean ignoreSimilarity) {
        super(material, ignoreSimilarity);

        this.setCreativeTab(Ref.CTAB);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (this.equals(ModBlocks.NarcoBerryBush)) {
            if (!worldIn.isRemote) {
                EntityItem berryDrop = new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(ModItems.NarcoBerry));
                worldIn.spawnEntity(berryDrop);
                worldIn.setBlockState(pos, ModBlocks.NarcoBush.getDefaultState());
                return true;
            }
        }
        return false;
    }

    public int quantityDropped(Random random) {
        return 1;
    }

    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
        if (this.equals(ModBlocks.NarcoBush)) {
            return;
        } else {
            if (!worldIn.isRemote) {
                EntityItem berryDrop = new EntityItem(worldIn, pos.getX(), pos.up().getY(), pos.getZ(), new ItemStack(ModItems.NarcoBerry));
                worldIn.spawnEntity(berryDrop);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
        return false;
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        return this != ModBlocks.FlorusBlock;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new BerryBushTileEntity();
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }
}
