package com.thenewjourney.items.weapon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by cj3636 on 4/14/2017.
 */
public class FlorucBattleAxe extends WeaponRegistry {

    public FlorucBattleAxe(String unlocalizedName, ToolMaterial material, float damage, float speed, int durability) {
        super(unlocalizedName, material, damage, speed, durability);
    }

    @Override
    protected void onRightBlock(World worldIn, EntityPlayer playerIn, ItemStack stack, BlockPos pos) {
        stack.damageItem(15, playerIn);
        Vec3d playerVec = playerIn.getPositionVector();
        BlockPos posN = playerIn.getPosition();
        List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, new AxisAlignedBB(posN.getX() - 7.0D, posN.getY() - 7.0D, posN.getZ() - 7.0D, posN.getX() + 7.0D, posN.getY() + 7.0D, posN.getZ() + 7.0D));

        for (int i = 0; i < list.size(); ++i) {
            Entity entity = list.get(i);
            if (entity instanceof EntityLiving) {
                Vec3d entityVec = entity.getPositionVector();
                Vec3d addVec = entityVec.subtract(playerVec);
                addVec.normalize();
                entity.motionX = addVec.x / 2;
                entity.motionZ = addVec.z / 2;
                entity.motionY = 1;
            }
        }
        playerIn.setAbsorptionAmount(6);
        PotionEffect potioneffect = new PotionEffect(MobEffects.SLOWNESS, 25, 9);
        playerIn.addPotionEffect(potioneffect);
        playerIn.playSound(SoundEvents.ITEM_SHIELD_BREAK, 10F, 0.5F);
    }
}
