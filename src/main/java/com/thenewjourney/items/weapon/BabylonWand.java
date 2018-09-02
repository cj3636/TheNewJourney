package com.thenewjourney.items.weapon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Created by cj3636 on 2/13/2017.
 */
public class BabylonWand extends WeaponRegistry {
    private int flagUse = 0;

    public BabylonWand(String unlocalizedName, ToolMaterial material, float damage, float speed, int durability) {
        super(unlocalizedName, material, damage, speed, durability);
    }

    @Override
    public void onRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
        BlockPos pos = playerIn.getPosition();
        stack.damageItem(1, playerIn);
        final Vec3d vec31 = playerIn.getLookVec();
        int i = 75;
        BlockPos blockpos = new BlockPos(vec31.x * i, vec31.y * i, vec31.z * i);

        if (!worldIn.isRemote) {
            EntitySmallFireball entitysmallfireball = new EntitySmallFireball(worldIn, playerIn, blockpos.getX(), blockpos.getY(), blockpos.getZ());
            entitysmallfireball.posY = pos.getY() + 1;
            worldIn.spawnEntity(entitysmallfireball);
            flagUse += 1;
            if (flagUse == 10) {
                playerIn.setFire(10);
                flagUse = 0;
            }
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (worldIn.getWorldTime() % 100 == 0) {
            flagUse = 0;
        }
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    @Override
    protected void onShiftRightBlock(World worldIn, EntityPlayer playerIn, ItemStack stack, BlockPos pos) {
        stack.damageItem(1, playerIn);
        if (worldIn.getBlockState(pos.up()) != Blocks.AIR.getDefaultState()) {
            return;
        }
        worldIn.setBlockState(pos.up(), Blocks.FIRE.getDefaultState());
    }
}
