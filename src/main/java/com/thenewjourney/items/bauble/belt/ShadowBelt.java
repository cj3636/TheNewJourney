package com.thenewjourney.items.bauble.belt;

import com.thenewjourney.items.bauble.ModBeltMasterwork;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class ShadowBelt extends ModBeltMasterwork {

    public ShadowBelt(String unlocalizedName) {
        super(unlocalizedName);
    }

    @Override
    public void tickPlayerEffect(EntityLivingBase player) {
        player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 40, 1, false, false));
        player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 40, 1, false, false));
    }

    @Override
    public void playerEquip(EntityLivingBase player) {
        player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 30, 1, false, false));
    }

    @Override
    public void playerUnequip(EntityLivingBase player) {
        player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 175, 2, false, false));
    }
}
