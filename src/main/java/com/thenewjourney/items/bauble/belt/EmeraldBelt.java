package com.thenewjourney.items.bauble.belt;

import com.thenewjourney.items.bauble.ModBeltMasterwork;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class EmeraldBelt extends ModBeltMasterwork {

    public EmeraldBelt(String unlocalizedName) {
        super(unlocalizedName);
    }

    @Override
    public void tickPlayerEffect(EntityLivingBase player) {
        player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 40, 3, false, false));
        player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 200, 2, false, false));
    }

    @Override
    public void playerEquip(EntityLivingBase player) {
        player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 1, 1, false, false));
    }

    @Override
    public void playerUnequip(EntityLivingBase player) {
        player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200, 4, false, false));
    }
}
