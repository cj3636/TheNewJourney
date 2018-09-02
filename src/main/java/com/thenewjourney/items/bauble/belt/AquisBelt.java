package com.thenewjourney.items.bauble.belt;

import com.thenewjourney.items.bauble.ModBeltMasterwork;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;

public class AquisBelt extends ModBeltMasterwork {

    public AquisBelt(String unlocalizedName) {
        super(unlocalizedName);
    }

    @Override
    public void tickPlayerEffect(EntityLivingBase player) {
        player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 40, 4, false, false));
        player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 40, 2, false, false));
    }

    @Override
    public void playerEquip(EntityLivingBase player) {
        double x = player.getPosition().getX();
        double y = player.getPosition().up().getY();
        double z = player.getPosition().getZ();
        player.getEntityWorld().spawnParticle(EnumParticleTypes.WATER_BUBBLE, x, y, z, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public void playerUnequip(EntityLivingBase player) {
    }
}
