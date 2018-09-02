package com.thenewjourney.items.staff;

import com.thenewjourney.entity.lightning.ColoredLightningEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ShadowStaff extends StaffRegistry {

    public ShadowStaff(String unlocalizedName, ToolMaterial material, int maxDamage) {
        super(unlocalizedName, material, maxDamage);
    }

    @Override
    protected void onShiftRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
        stack.damageItem(3, playerIn);
        final Vec3d vec3 = playerIn.getPositionEyes(1.0F);
        final Vec3d vec31 = playerIn.getLook(1.0F);
        for (int i = 0; i < 150; i++) {
            Vec3d vec32 = vec3.addVector(vec31.x * i, vec31.y * i, vec31.z * i);
            BlockPos blockpos = new BlockPos(vec32.x, vec32.y, vec32.z);
            IBlockState iblockstate = worldIn.getBlockState(blockpos);
            if (iblockstate.getMaterial() != null && iblockstate.getMaterial() != Material.AIR) {
                worldIn.spawnEntity(new ColoredLightningEntity(worldIn, blockpos.getX(), blockpos.getY(), blockpos.getZ(), false));
            }
        }
    }

    @Override
    protected void onCtrlRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
        stack.damageItem(16, playerIn);
        playerIn.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 160, 1, false, false));
        playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED, 160, 8, false, false));
    }

    @Override
    protected void onRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
        BlockPos playerPos = playerIn.getPosition();
        for (int i = 255; i > 0; i--) {
            BlockPos posAt = new BlockPos(playerPos.getX(), i, playerPos.getZ());
            if (worldIn.getBlockState(posAt).getMaterial().isSolid()) {
                playerIn.attemptTeleport(playerPos.getX(), i + 1, playerPos.getZ());
                stack.damageItem(5, playerIn);
                return;
            }
        }
    }
}