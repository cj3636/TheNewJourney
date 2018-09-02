package com.thenewjourney.items.weapon;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AquisStaff extends WeaponRegistry {

    public AquisStaff(String unlocalizedName, ToolMaterial material, float damage, float speed, int durability) {
        super(unlocalizedName, material, damage, speed, durability);
    }

    @Override
    protected void onRightBlockF(World worldIn, EntityPlayer playerIn, ItemStack stack, BlockPos pos, EnumFacing facing) {
        if (facing.equals(EnumFacing.DOWN) || facing.equals(EnumFacing.UP)) {
            BlockPos[] posS = {pos, pos.north(), pos.south(), pos.east(), pos.west(), pos.east().north(), pos.east().south(), pos.west().north(), pos.west().south()};
            for (BlockPos posAt : posS) {
                if (worldIn.getBlockState(pos).getBlock() == Blocks.BEDROCK) {
                    //do nothing
                } else {
                    worldIn.destroyBlock(posAt, true);
                    stack.damageItem(1, playerIn);
                }
            }
        } else if (facing.equals(EnumFacing.NORTH) || facing.equals(EnumFacing.SOUTH)) {
            BlockPos[] posS = {pos, pos.up(), pos.up().east(), pos.up().west(), pos.down(), pos.down().east(), pos.down().west(), pos.east(), pos.west()};
            for (BlockPos posAt : posS) {
                if (worldIn.getBlockState(pos).getBlock() == Blocks.BEDROCK) {
                    //do nothing
                } else {
                    worldIn.destroyBlock(posAt, true);
                    stack.damageItem(1, playerIn);
                }
            }
        } else {
            BlockPos[] posS = {pos, pos.up(), pos.up().south(), pos.up().north(), pos.down(), pos.down().south(), pos.down().north(), pos.south(), pos.north()};
            for (BlockPos posAt : posS) {
                if (worldIn.getBlockState(pos).getBlock() == Blocks.BEDROCK) {
                    //do nothing
                } else {
                    worldIn.destroyBlock(posAt, true);
                    stack.damageItem(1, playerIn);
                }
            }
        }
    }

    @Override
    protected void onAltRightBlock(World worldIn, EntityPlayer playerIn, ItemStack stack, BlockPos pos) {
        playerIn.hurtResistantTime = 150;
        if (playerIn.world.isRemote) {
            playerIn.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F, 1.3f);
        }
        stack.damageItem(149, playerIn);
    }

    @Override
    protected void onShiftRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
        stack.damageItem(1, playerIn);
        final Vec3d vec3 = playerIn.getPositionEyes(1.0F);
        final Vec3d vec31 = playerIn.getLook(1.0F);
        for (int i = 0; i < 75; i++) {
            Vec3d vec32 = vec3.addVector(vec31.x * i, vec31.y * i, vec31.z * i);
            BlockPos blockpos = new BlockPos(vec32.x, vec32.y, vec32.z);
            IBlockState iblockstate = worldIn.getBlockState(blockpos);
            if (!worldIn.isRemote) {
                if (iblockstate.getMaterial() != Material.AIR && iblockstate.getMaterial() != Material.WATER && iblockstate != Blocks.BEDROCK) {
                    worldIn.destroyBlock(blockpos, true);
                    worldIn.setBlockState(blockpos, Blocks.FLOWING_WATER.getDefaultState());
                    break;
                }
            }
        }
    }

    @Override
    protected void onCtrlRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
        Vec3d lookVec = playerIn.getLookVec();
        double x = lookVec.x;
        double y = lookVec.y;
        double z = lookVec.z;
        playerIn.setVelocity(x * 10, y, z * 10);
        stack.damageItem(1, playerIn);
    }
}