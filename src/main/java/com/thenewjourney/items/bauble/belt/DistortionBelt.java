package com.thenewjourney.items.bauble.belt;

import com.thenewjourney.items.bauble.ModBeltMasterwork;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class DistortionBelt extends ModBeltMasterwork {

    public DistortionBelt(String unlocalizedName) {
        super(unlocalizedName);
    }

    @Override
    public void tickPlayerEffect(EntityLivingBase player) {
        player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 40, 2, false, false));
    }

    @Override
    public void playerEquip(EntityLivingBase player) {
        player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 50, 3, false, false));
    }

    @Override
    public void playerUnequip(EntityLivingBase player) {
        player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 4, 1, false, false));
        player.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 30, 0, false, false));
    }
}
