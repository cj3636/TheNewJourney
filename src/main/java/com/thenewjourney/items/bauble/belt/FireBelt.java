package com.thenewjourney.items.bauble.belt;

import com.thenewjourney.items.bauble.ModBeltMasterwork;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public class FireBelt extends ModBeltMasterwork {

    public FireBelt(String unlocalizedName) {
        super(unlocalizedName);
    }

    @Override
    public void tickPlayerEffect(EntityLivingBase player) {
        player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 40, 1, false, false));
        if (player.isInWater()) {
            player.attackEntityFrom(DamageSource.DROWN, 6F);
        }
    }

    @Override
    public void playerEquip(EntityLivingBase player) {
        player.setFire(2);
    }

    @Override
    public void playerUnequip(EntityLivingBase player) {
    }
}
