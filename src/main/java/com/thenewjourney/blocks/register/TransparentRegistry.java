package com.thenewjourney.blocks.register;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class TransparentRegistry extends BlockBreakable {

    public TransparentRegistry(String unlocalizedName, Material material, float hardness, float resistance, boolean ignoreSimilarity) {
        super(material, ignoreSimilarity);

        this.setCreativeTab(Ref.CTAB);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }

    public int quantityDropped(Random random) {
        if (this == ModBlocks.ClearGlass) {
            return 1;
        }
        if (this == ModBlocks.Chandelier) {
            return 1;
        }
        if (this == ModBlocks.ArmoredGlass) {
            return 1;
        }
        return 0;
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
}

