package com.thenewjourney.world;

import com.cj3636.lib.Config;
import com.thenewjourney.blocks.ModBlocks;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class OreGenerator implements IWorldGenerator {

    private WorldGenerator CobaltOre;
    private WorldGenerator ShadowOre;
    private WorldGenerator RubyOre;
    private WorldGenerator DistortedOre;
    private WorldGenerator FireOre;
    private WorldGenerator Graphite;
    private WorldGenerator Marble;
    private WorldGenerator CopperOre;
    private WorldGenerator TinOre;

    public OreGenerator() {
        //Int is max block count
        this.CobaltOre = new WorldGenMinable(ModBlocks.CobaltOre.getDefaultState(), 8);
        this.ShadowOre = new WorldGenMinable(ModBlocks.ShadowOre.getDefaultState(), 5);
        this.RubyOre = new WorldGenMinable(ModBlocks.RubyOre.getDefaultState(), 8);
        this.CopperOre = new WorldGenMinable(ModBlocks.CopperOre.getDefaultState(), 7);
        this.TinOre = new WorldGenMinable(ModBlocks.TinOre.getDefaultState(), 5);
        this.Graphite = new WorldGenMinable(ModBlocks.Graphite.getDefaultState(), 4);
        this.Marble = new WorldGenMinable(ModBlocks.Marble.getDefaultState(), 16);
        this.DistortedOre = new WorldGenMinable(ModBlocks.DistortedOre.getDefaultState(), 6, BlockMatcher.forBlock(Blocks.NETHERRACK));
        this.FireOre = new WorldGenMinable(ModBlocks.FireOre.getDefaultState(), 3, BlockMatcher.forBlock(ModBlocks.BurntStone));
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        // veinsize, min, max height
        //Fire
        switch (world.provider.getDimension()) {
            //Nether
            case -1:
                this.runGenerator(this.DistortedOre, world, random, chunkX, chunkZ, Config.distortionSpawn, 70, 128);
                break;
            //Overworld
            case 0:
                this.runGenerator(this.CobaltOre, world, random, chunkX, chunkZ, Config.cobaltSpawn, 0, 30);
                this.runGenerator(this.ShadowOre, world, random, chunkX, chunkZ, Config.shadowSpawn, 4, 16);
                this.runGenerator(this.RubyOre, world, random, chunkX, chunkZ, Config.rubySpawn, 30, 70);
                this.runGenerator(this.Graphite, world, random, chunkX, chunkZ, Config.graphiteSpawn, 5, 27);
                this.runGenerator(this.Marble, world, random, chunkX, chunkZ, Config.marbleSpawn, 20, 80);
                this.runGenerator(this.CopperOre, world, random, chunkX, chunkZ, Config.copperSpawn, 50, 100);
                this.runGenerator(this.TinOre, world, random, chunkX, chunkZ, Config.tinSpawn, 0, 50);
                break;
            //End
            case 1:
                break;
        }
        //Fire
        if (world.provider.getDimension() == Config.fireDimId) {
            this.runGenerator(this.FireOre, world, random, chunkX, chunkZ, Config.fireSpawn, 30, 75);
        }
    }

    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight) {
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
        }

        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; i++) {
            int x = chunk_X * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunk_Z * 16 + rand.nextInt(16);
            generator.generate(world, rand, new BlockPos(x, y, z));
        }
    }
}
