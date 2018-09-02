package com.thenewjourney.items.bauble.ring;

import com.thenewjourney.items.bauble.ModRingRare;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class FlyRing extends ModRingRare {
	public FlyRing(String unlocalizedName, int maxDamage) {
		super(unlocalizedName, maxDamage);
	}
	public float fallDistance;
	@Override
	public void tickPlayerEffect(EntityLivingBase player) {
		if (player instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)player;
            entityplayer.fallDistance = 0;
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