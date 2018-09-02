package com.thenewjourney.items.weapon;

import com.thenewjourney.entity.bluefireball.FlorucFireballEntity;
import com.thenewjourney.items.bauble.ModEnumRarity;
import com.thenewjourney.items.bauble.ModRareToolTip;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by cj3636 on 4/14/2017.
 */
public class ZirconiumStaff extends WeaponRegistry {

    public ZirconiumStaff(String unlocalizedName, ToolMaterial material, float damage, float speed, int durability) {
        super(unlocalizedName, material, damage, speed, durability);
    }

    public ModEnumRarity setRarity() {
        return ModEnumRarity.EPIC;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(ModRareToolTip.getRareToolTip(setRarity()));
    }

    @Override
    protected void onAltRightBlock(World worldIn, EntityPlayer playerIn, ItemStack stack, BlockPos pos) {
        playerIn.hurtResistantTime = 225;
        if (playerIn.world.isRemote) {
            playerIn.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F, .65f);
        }
        stack.damageItem(75, playerIn);
    }

    protected void onRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
        BlockPos pos = playerIn.getPosition();
        stack.damageItem(1, playerIn);
        final Vec3d vec31 = playerIn.getLookVec();
        int i = 75;
        BlockPos blockpos = new BlockPos(vec31.x * i, vec31.y * i, vec31.z * i);

        if (!worldIn.isRemote) {
            FlorucFireballEntity entitysmallfireball = new FlorucFireballEntity(worldIn, playerIn, blockpos.getX(), blockpos.getY(), blockpos.getZ());
            entitysmallfireball.posY = pos.getY() + 1;
            entitysmallfireball.accelerationX *= 2;
            entitysmallfireball.accelerationZ *= 2;
            worldIn.spawnEntity(entitysmallfireball);
        }
    }

    @Override
    protected void onShiftRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
        BlockPos pos = playerIn.getPosition();
        stack.damageItem(1, playerIn);
        final Vec3d vec31 = playerIn.getLookVec();
        int i = 75;
        BlockPos blockpos = new BlockPos(vec31.x * i, vec31.y * i, vec31.z * i);

        if (!worldIn.isRemote) {
            EntityWitherSkull entitysmallfireball = new EntityWitherSkull(worldIn, playerIn, blockpos.getX(), blockpos.getY(), blockpos.getZ());
            entitysmallfireball.posY = pos.getY() + 1;
            worldIn.spawnEntity(entitysmallfireball);
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        EntityPlayer entityPlayer;
        if (entityIn instanceof EntityPlayer) {
            entityPlayer = (EntityPlayer) entityIn;
        } else {
            return;
        }
        if (entityPlayer.capabilities.isCreativeMode) {
            return;
        }
        entityPlayer.capabilities.allowFlying = isSelected;
        if (entityPlayer.capabilities.isFlying) {
            stack.damageItem(1, entityPlayer);
        }
    }

    protected void onAltRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
        if (worldIn.getBlockState(playerIn.getPosition().down()).equals(Blocks.AIR.getDefaultState())) {
            worldIn.setBlockState(playerIn.getPosition().down(), Blocks.GLASS.getDefaultState());
            if (playerIn.world.isRemote) {
                playerIn.playSound(SoundEvents.BLOCK_GLASS_PLACE, 1.0F, 1.5f);
            }
        }
    }
}
