package com.thenewjourney.items.bauble.belt;

import com.thenewjourney.items.bauble.ModBeltMasterwork;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class BeltOfTranscendence extends ModBeltMasterwork {

    public BeltOfTranscendence(String unlocalizedName) {
        super(unlocalizedName);
    }

    @Override	
    public void tickPlayerEffect(EntityLivingBase player) {
        player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 40, 0, false, false));
    	player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 40, 0, false, false));
    	player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 40, 0, false, false));
    	player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 40, 0, false, false));
    	
    	player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 40, 3, false, false));
    	player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 40, 1, false, false));
    	player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 40, 4, false, false));
    	player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 40, 4, false, false));

    }

    @Override
    public void playerEquip(EntityLivingBase player) {
    }

    @Override
    public void playerUnequip(EntityLivingBase player) {
    	player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 500, 6, false, false));
    }
}
