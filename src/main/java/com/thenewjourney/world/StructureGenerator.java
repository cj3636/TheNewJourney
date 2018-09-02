package com.thenewjourney.world;

import com.cj3636.lib.Config;
import com.thenewjourney.world.FireRuin.FireRuin;
import com.thenewjourney.world.FireRuin.FireRuinFire;
import com.thenewjourney.world.FireRuin.FireRuinFlorus;
import com.thenewjourney.world.trees.PurviaTreeGen;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class StructureGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int i = world.provider.getDimension();
        if (i == -1) {
            generateNether(world, random, chunkX * 16, chunkZ * 16);
        } else if (i == 0) {
            generateSurface(world, random, chunkX * 16, chunkZ * 16);
            generateSurfaceTree(world, random, chunkX * 16, chunkZ * 16);
        } else if (i == 1) {
            generateEnd(world, random, chunkX * 16, chunkZ * 16);
        } else if (i == Config.fireDimId) {
            generateFire(world, random, chunkX * 16, chunkZ * 16);
        } else if (i == Config.florusDimId) {
            generateFlorus(world, random, chunkX * 16, chunkZ * 16);
        } else {
            System.out.println("[The New Journey] External dimensions found! Add spawns? -> thenewjourney.cfg");
        }
    }

    private void generateFire(World world, Random rand, int chunkX, int chunkZ) {
        WorldGenerator genFire = new FireRuinFire();
        final int FireChance = 2500;
        if (rand.nextInt(FireChance) <= 1) {
            int randX = chunkX + rand.nextInt(16);
            int randZ = chunkZ + rand.nextInt(16);
            int groundY = getGroundFromAbove(world, randX, randZ);
            genFire.generate(world, rand, new BlockPos(randX, groundY + 1, randZ));
        }
    }

    private void generateFlorus(World world, Random rand, int chunkX, int chunkZ) {
        WorldGenerator genFire = new FireRuinFlorus();
        final int FireChance = 2500;
        if (rand.nextInt(FireChance) <= 1) {
            int randX = chunkX + rand.nextInt(16);
            int randZ = chunkZ + rand.nextInt(16);
            int groundY = getGroundFromAbove(world, randX, randZ);
            genFire.generate(world, rand, new BlockPos(randX, groundY + 1, randZ));
        }
    }

    private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {

        WorldGenerator genFire = new FireRuin();
        final int FireChance = 2500;
        if (rand.nextInt(FireChance) <= 1) {
            int randX = chunkX + rand.nextInt(16);
            int randZ = chunkZ + rand.nextInt(16);
            int groundY = getGroundFromAbove(world, randX, randZ);
            genFire.generate(world, rand, new BlockPos(randX, groundY + 1, randZ));
        }
    }

    private void generateSurfaceTree(World world, Random rand, int chunkX, int chunkZ) {

        WorldGenerator genFire = new PurviaTreeGen(true);
        final int FireChance = 1200;
        if (rand.nextInt(FireChance) <= 1) {
            int randX = chunkX + rand.nextInt(16);
            int randZ = chunkZ + rand.nextInt(16);
            int groundY = getGroundFromAbove(world, randX, randZ);
            genFire.generate(world, rand, new BlockPos(randX, groundY + 1, randZ));
        }
    }

    private void generateNether(World world, Random random, int blockX, int blockZ) {
    }

    private void generateEnd(World world, Random random, int blockX, int blockZ) {
    }

    public static int getGroundFromAbove(World world, int x, int z) {
        int y = 255;
        boolean foundGround = false;
        while (!foundGround && y-- >= 0) {
            Block blockAt = world.getBlockState(new BlockPos(x, y, z)).getBlock();

            foundGround = blockAt != Blocks.AIR && blockAt != Blocks.WATER && blockAt != Blocks.FLOWING_WATER;
        }
        return y;
    }
}