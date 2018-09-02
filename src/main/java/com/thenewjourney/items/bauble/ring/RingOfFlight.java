package com.thenewjourney.items.bauble.ring;

import com.thenewjourney.items.bauble.ModRingLegendary;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class RingOfFlight extends ModRingLegendary {
	public RingOfFlight(String unlocalizedName, int maxDamage) {
		super(unlocalizedName, maxDamage);
	}
	public float fallDistance;
	@Override
	public void tickPlayerEffect(EntityLivingBase player) {
		if (player instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)player;
            entityplayer.fallDistance = 0;
            entityplayer.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 40, 0, false, false));
        }
	}
	@Override
	public void playerEquip(EntityLivingBase player) {
		if (player instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)player;
            entityplayer.capabilities.allowFlying = true;
		}
	}
	@Override
	public void playerUnequip(EntityLivingBase player) {
		if (player instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)player;
            entityplayer.capabilities.allowFlying = false;
        }
	}
}