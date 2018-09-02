package com.thenewjourney.items.weapon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * Created by cj3636 on 4/14/2017.
 */
public class GreenBlade extends WeaponRegistry {

    public GreenBlade(String unlocalizedName, ToolMaterial material, float damage, float speed, int durability) {
        super(unlocalizedName, material, damage, speed, durability);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!isSelected) {
            return;
        }
        if (entityIn instanceof EntityLivingBase) {
            EntityLivingBase entityLiving = (EntityLivingBase) entityIn;
            entityLiving.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 40, 0, false, false));
            entityLiving.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 40, 0, false, false));
        }
    }
}
