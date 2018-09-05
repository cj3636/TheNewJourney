package com.thenewjourney.blocks.register;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRegistry extends Block {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public BlockRegistry(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);

        this.setCreativeTab(Ref.CTAB);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }

    public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
        if (this == ModBlocks.CrypticBlock && side == EnumFacing.UP) {
            return true;
        }
        if (this == ModBlocks.FireBlock && side == EnumFacing.UP) {
            return true;
        }
        return this == ModBlocks.DistortedOre && side == EnumFacing.UP;
    }
}