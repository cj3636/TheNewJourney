package com.thenewjourney.dimension;

import com.cj3636.lib.Config;
import com.thenewjourney.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ModTeleporter extends Teleporter {

    private final WorldServer worldTo;

    public ModTeleporter(WorldServer worldTo) {
        super(worldTo);
        this.worldTo = worldTo;
    }

    @Override
    public void placeInPortal(Entity entityIn, float rotationYaw) {
        BlockPos posOld = entityIn.getPosition();
        int y = getGroundFromAbove(this.worldTo, posOld.getX(), posOld.getZ());

        BlockPos posNew = new BlockPos(posOld.getX(), y, posOld.getZ());
        entityIn.moveToBlockPosAndAngles(posNew, entityIn.rotationYaw, entityIn.rotationPitch);
        if (worldTo.provider.getDimension() == Config.fireDimId) {
            worldTo.setBlockState(posNew.down(), ModBlocks.BurntStone.getDefaultState());
        } else if (worldTo.provider.getDimension() == Config.florusDimId) {
            worldTo.setBlockState(posNew.down(), ModBlocks.FlorusBlock.getDefaultState());
        } else {
            worldTo.setBlockState(posNew.down(), Blocks.STONE.getDefaultState());
        }
    }

    @Override
    public boolean makePortal(Entity entityIn) {
        return false;
    }

    @Override
    public void removeStalePortalLocations(long worldTime) {
    }

    public static int getGroundFromAbove(World world, int x, int z) {
        int y = 255;
        boolean foundGround = false;
        while (!foundGround && y-- >= 0) {
            Block blockAt = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            foundGround = blockAt != Blocks.AIR;
        }
        return y + 1;
    }

    @Override
    public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
        return false;
    }
}
