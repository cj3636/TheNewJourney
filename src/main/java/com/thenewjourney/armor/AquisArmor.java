package com.thenewjourney.armor;

import com.cj3636.lib.Ref;
import com.thenewjourney.items.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class AquisArmor extends ItemArmor {

    public AquisArmor(String unlocalizedName, ArmorMaterial material, int renderIndex, EntityEquipmentSlot armorType) {
        super(material, renderIndex, armorType);

        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
    }
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
    	if (player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).getItem() == ModItems.AquisHelmet
    	        && player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == ModItems.AquisChestplate
    	        && player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).getItem() == ModItems.AquisLeggings
    	        && player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem() == ModItems.AquisBoots) {
    		player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 40, 4, false, false));
    	}
    }
    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
    	if (stack.getItem() == ModItems.AquisHelmet) {
    		stack.addEnchantment(Enchantments.AQUA_AFFINITY, 1);
    	}
    	if (stack.getItem() == ModItems.AquisBoots) {
    		stack.addEnchantment(Enchantments.DEPTH_STRIDER, 3);
    	}
    }
    
}