package com.thenewjourney.items.bauble.ring;

import com.thenewjourney.items.bauble.ModRingBasic;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class GoldRing extends ModRingBasic {
	public GoldRing(String unlocalizedName, int maxDamage) {
		super(unlocalizedName, maxDamage);
	}
	@Override
	public void tickPlayerEffect(EntityLivingBase player) {
        if (player instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) player;
            entityplayer.capabilities.setFlySpeed(2);
            entityplayer.jumpMovementFactor = 8F;

        }
    }
	@Override
	public void playerEquip(EntityLivingBase player) {
        if (player instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) player;
            entityplayer.capabilities.setFlySpeed(2);
            entityplayer.jumpMovementFactor = 8F;
        }
    }
	@Override
	public void playerUnequip(EntityLivingBase player) {
        if (player instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) player;
            entityplayer.capabilities.setFlySpeed(0.05F);
            entityplayer.jumpMovementFactor = 0.02F;
        }
	}
}