package com.thenewjourney.world;

import com.thenewjourney.blocks.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class SingleOreGenerator implements IWorldGenerator {
    @Override
    public void generate(Random rand, int chunkX, int chunkZ, World worldIn, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int count = 100;
        int x = chunkX * 16 + rand.nextInt(17);
        int y = 0 + rand.nextInt(256);
        int z = chunkZ * 16 + rand.nextInt(17);
        BlockPos pos = new BlockPos(x, y, z);
        if (worldIn.getBiome(pos).equals(Biomes.JUNGLE)) {
            for (int i = 0; i < count; i++) {
                IBlockState state = worldIn.getBlockState(pos);
                if (state.getBlock().isReplaceableOreGen(state, worldIn, pos, BlockMatcher.forBlock(Blocks.LEAVES))) {
                    worldIn.setBlockState(pos, ModBlocks.NarcoBush.getDefaultState(), 2);
                }
            }
        }
        for (int i = 0; i < count; i++) {
            IBlockState state = worldIn.getBlockState(pos);
            if (state.getBlock().isReplaceableOreGen(state, worldIn, pos, BlockMatcher.forBlock(Blocks.LEAVES2))) {
                worldIn.setBlockState(pos, ModBlocks.NarcoBush.getDefaultState(), 2);
            }
        }
        for (int i = 0; i < count; i++) {
            IBlockState state = worldIn.getBlockState(pos);
            if (state.getBlock().isReplaceableOreGen(state, worldIn, pos, BlockMatcher.forBlock(Blocks.LEAVES))) {
                worldIn.setBlockState(pos, ModBlocks.BeeHive.getDefaultState(), 2);
            }
        }
        for (int i = 0; i < 5; i++) {
            IBlockState state = worldIn.getBlockState(pos);
            if (state.getBlock().isReplaceableOreGen(state, worldIn, pos, BlockMatcher.forBlock(ModBlocks.FlorusBlock))) {
                worldIn.setBlockState(pos, ModBlocks.AquisOre.getDefaultState(), 2);
            }
        }
    }
}
