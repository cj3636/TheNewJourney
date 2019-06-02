package com.cj3636.lib;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by cj3636 on 1/28/2017.
 */
public class LocUtil {
    private static boolean runCheck(World worldIn, IBlockState state, BlockPos[] posList) {
        boolean returnValue = false;
        for (BlockPos posAt : posList) {
            IBlockState stateCheck = worldIn.getBlockState(posAt);
            if (stateCheck.equals(state)) {
                returnValue = true;
            } else {
                return false;
            }
        }
        return returnValue;
    }

    private static boolean runCheckB(World worldIn, Block blockIn, BlockPos[] posList) {
        boolean returnValue = false;
        for (BlockPos posAt : posList) {
            Block stateCheck = worldIn.getBlockState(posAt).getBlock();
            if (stateCheck.equals(blockIn)) {
                returnValue = true;
            } else {
                return false;
            }
        }
        return returnValue;
    }

    /**
     * All 8 horizontal plane sides
     *
     * @param pos     block position
     * @param worldIn world class
     * @param state   to check for
     * @return boolean result of check
     */
    public static boolean checkFlat(BlockPos pos, World worldIn, IBlockState state) {
        BlockPos[] posList = {
                pos.north(),
                pos.south(),
                pos.east(),
                pos.west(),
                pos.north().east(),
                pos.north().west(),
                pos.south().east(),
                pos.south().west()
        };
        return runCheck(worldIn, state, posList);
    }

    /**
     * All 8 horizontal plane sides and the block itself
     *
     * @param pos     block position
     * @param worldIn world class
     * @param state   to check for
     * @return boolean result of check
     */
    public static boolean checkFlatAndCenter(BlockPos pos, World worldIn, IBlockState state) {
        BlockPos[] posList = {
                pos,
                pos.north(),
                pos.south(),
                pos.east(),
                pos.west(),
                pos.north().east(),
                pos.north().west(),
                pos.south().east(),
                pos.south().west()
        };
        return runCheck(worldIn, state, posList);
    }

    /**
     * All 8 vertical plane sides
     *
     * @param pos     block position
     * @param worldIn world class
     * @param state   to check for
     * @return boolean result of check
     */
    public static boolean checkVertical(BlockPos pos, World worldIn, IBlockState state, EnumFacing facing) {
        BlockPos[] posList = new BlockPos[8];
        if (facing.equals(EnumFacing.UP) || facing.equals(EnumFacing.DOWN)) return false;
        switch (facing) {
            case WEST:
                posList[0] = pos.up();
                posList[1] = pos.down();
                posList[2] = pos.north();
                posList[3] = pos.south();
                posList[4] = pos.up().north();
                posList[5] = pos.up().south();
                posList[6] = pos.down().north();
                posList[7] = pos.down().south();
                break;
            case EAST:
                posList[0] = pos.up();
                posList[1] = pos.down();
                posList[2] = pos.north();
                posList[3] = pos.south();
                posList[4] = pos.up().north();
                posList[5] = pos.up().south();
                posList[6] = pos.down().north();
                posList[7] = pos.down().south();
                break;
            case NORTH:
                posList[0] = pos.up();
                posList[1] = pos.down();
                posList[2] = pos.east();
                posList[3] = pos.west();
                posList[4] = pos.up().east();
                posList[5] = pos.up().west();
                posList[6] = pos.down().east();
                posList[7] = pos.down().west();
                break;
            case SOUTH:
                posList[0] = pos.up();
                posList[1] = pos.down();
                posList[2] = pos.east();
                posList[3] = pos.west();
                posList[4] = pos.up().east();
                posList[5] = pos.up().west();
                posList[6] = pos.down().east();
                posList[7] = pos.down().west();
                break;
        }
        return runCheck(worldIn, state, posList);
    }

    /**
     * 8 vertical plane sides (no top)
     *
     * @param pos     block position
     * @param worldIn world class
     * @param state   to check for
     * @return boolean result of check
     */
    public static boolean checkVerticalNoTop(BlockPos pos, World worldIn, IBlockState state, EnumFacing facing) {
        BlockPos[] posList = new BlockPos[8];
        if (facing.equals(EnumFacing.UP) || facing.equals(EnumFacing.DOWN)) return false;
        switch (facing) {
            case WEST:
                posList[0] = pos.down();
                posList[1] = pos.down();
                posList[2] = pos.north();
                posList[3] = pos.south();
                posList[4] = pos.up().north();
                posList[5] = pos.up().south();
                posList[6] = pos.down().north();
                posList[7] = pos.down().south();
                break;
            case EAST:
                posList[0] = pos.down();
                posList[1] = pos.down();
                posList[2] = pos.north();
                posList[3] = pos.south();
                posList[4] = pos.up().north();
                posList[5] = pos.up().south();
                posList[6] = pos.down().north();
                posList[7] = pos.down().south();
                break;
            case NORTH:
                posList[0] = pos.down();
                posList[1] = pos.down();
                posList[2] = pos.east();
                posList[3] = pos.west();
                posList[4] = pos.up().east();
                posList[5] = pos.up().west();
                posList[6] = pos.down().east();
                posList[7] = pos.down().west();
                break;
            case SOUTH:
                posList[0] = pos.down();
                posList[1] = pos.down();
                posList[2] = pos.east();
                posList[3] = pos.west();
                posList[4] = pos.up().east();
                posList[5] = pos.up().west();
                posList[6] = pos.down().east();
                posList[7] = pos.down().west();
                break;
        }
        return runCheck(worldIn, state, posList);
    }

    /**
     * All 8 vertical plane sides and the block itself
     *
     * @param pos     block position
     * @param worldIn world class
     * @param state   to check for
     * @return boolean result of check
     */
    public static boolean checkVerticalAndCenter(BlockPos pos, World worldIn, IBlockState state, EnumFacing facing) {
        BlockPos[] posList = new BlockPos[9];
        if (facing.equals(EnumFacing.UP) || facing.equals(EnumFacing.DOWN)) return false;
        switch (facing) {
            case WEST:
                posList[0] = pos.up();
                posList[1] = pos.down();
                posList[2] = pos.north();
                posList[3] = pos.south();
                posList[4] = pos.up().north();
                posList[5] = pos.up().south();
                posList[6] = pos.down().north();
                posList[7] = pos.down().south();
                posList[8] = pos;
                break;
            case EAST:
                posList[0] = pos.up();
                posList[1] = pos.down();
                posList[2] = pos.north();
                posList[3] = pos.south();
                posList[4] = pos.up().north();
                posList[5] = pos.up().south();
                posList[6] = pos.down().north();
                posList[7] = pos.down().south();
                posList[8] = pos;
                break;
            case NORTH:
                posList[0] = pos.up();
                posList[1] = pos.down();
                posList[2] = pos.east();
                posList[3] = pos.west();
                posList[4] = pos.up().east();
                posList[5] = pos.up().west();
                posList[6] = pos.down().east();
                posList[7] = pos.down().west();
                posList[8] = pos;
                break;
            case SOUTH:
                posList[0] = pos.up();
                posList[1] = pos.down();
                posList[2] = pos.east();
                posList[3] = pos.west();
                posList[4] = pos.up().east();
                posList[5] = pos.up().west();
                posList[6] = pos.down().east();
                posList[7] = pos.down().west();
                posList[8] = pos;
                break;
        }
        return runCheck(worldIn, state, posList);
    }

    /**
     * Four main sides
     *
     * @param pos     block position
     * @param worldIn world class
     * @param state   to check for
     * @return boolean result of check
     */
    public static boolean checkHorizontalAxis(BlockPos pos, World worldIn, IBlockState state) {
        BlockPos[] posList = {
                pos.north(),
                pos.south(),
                pos.east(),
                pos.west()
        };
        return runCheck(worldIn, state, posList);
    }

    /**
     * Four main sides on Z axis (probably, IDFK which axis is which)HAHA JK I LOOKED IT UP (up down, left, right)
     *
     * @param pos     block position
     * @param worldIn world class
     * @param blockIn to check for
     * @return boolean result of check
     */
    public static boolean checkVerticalAxisZ(BlockPos pos, World worldIn, Block blockIn) {
        BlockPos[] posList = {
                pos.north(),
                pos.south(),
                pos.up(),
                pos.down()
        };
        return runCheckB(worldIn, blockIn, posList);
    }

    /**
     * Four main sides on Z axis (up down, left, right...in the 'other' direction)
     *
     * @param pos     block position
     * @param worldIn world class
     * @param blockIn to check for
     * @return boolean result of check
     */
    public static boolean checkVerticalAxisX(BlockPos pos, World worldIn, Block blockIn) {
        BlockPos[] posList = {
                pos.east(),
                pos.west(),
                pos.up(),
                pos.down()
        };
        return runCheckB(worldIn, blockIn, posList);
    }

    /**
     * Four corner sides
     *
     * @param pos     block position
     * @param worldIn world class
     * @param state   to check for
     * @return boolean result of check
     */
    public static boolean checkCorners(BlockPos pos, World worldIn, IBlockState state) {
        BlockPos[] posList = {
                pos.north().east(),
                pos.north().west(),
                pos.south().east(),
                pos.south().west()
        };
        return runCheck(worldIn, state, posList);
    }

    /**
     * All 26 sides
     *
     * @param pos     block position
     * @param worldIn world class
     * @param state   to check for
     * @return boolean result of check
     */
    public static boolean checkFull(BlockPos pos, World worldIn, IBlockState state) {
        return checkFlatAndCenter(pos.up(), worldIn, state) && checkFlatAndCenter(pos.down(), worldIn, state) && checkFlat(pos, worldIn, state);
    }

    /**
     * Repels projectiles and mobs in the given AABB away from a given point
     */
    public static void repelEntitiesInAABBFromPoint(World world, AxisAlignedBB effectBounds, double x, double y, double z) {
        List<Entity> list = world.getEntitiesWithinAABB(Entity.class, effectBounds);

        for (Entity ent : list) {
            if ((ent instanceof EntityLiving) || (ent instanceof IProjectile) || (ent instanceof EntityFireball)) {
                if (!(ent instanceof IMob || ent instanceof IProjectile)) {
                    continue;
                } else {
                    if (ent instanceof EntityArrow && ((EntityArrow) ent).onGround) {
                        continue;
                    }
                    Vec3d p = new Vec3d(x, y, z);
                    Vec3d t = new Vec3d(ent.posX, ent.posY, ent.posZ);
                    double distance = p.distanceTo(t) + 0.1D;

                    Vec3d r = new Vec3d(t.x - p.x, t.y - p.y, t.z - p.z);

                    ent.motionX += r.x / 1.5D / distance;
                    ent.motionY += r.y / 1.5D / distance;
                    ent.motionZ += r.z / 1.5D / distance;
                }
            }
        }
    }
}