package com.thenewjourney.items.tool;

import com.cj3636.lib.Ref;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class BedrockSwordRegistry extends Item {
	
    public BedrockSwordRegistry(String unlocalizedName) {
        super();   
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
    }
    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
    	if (!player.capabilities.isCreativeMode) {
    		if (entity instanceof EntityPlayer) {
    			EntityPlayer playerHit = (EntityPlayer) entity;
    			if (playerHit.capabilities.isCreativeMode) {
    				playerHit.attackEntityFrom(DamageSource.OUT_OF_WORLD, 25F);
    			} else {
    				entity.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 8F);
    			}
    		}
    	} else {
			player.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 25F);
    	}
    	return false;
    }
}
