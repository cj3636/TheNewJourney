package com.thenewjourney.items.weapon;

import com.thenewjourney.items.bauble.ModEnumRarity;
import com.thenewjourney.items.bauble.ModRareToolTip;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by cj3636 on 4/14/2017.
 */
public class FloriumAxe extends WeaponRegistry {

    public FloriumAxe(String unlocalizedName, ToolMaterial material, float damage, float speed, int durability) {
        super(unlocalizedName, material, damage, speed, durability);
    }

    public ModEnumRarity setRarity() {
        return ModEnumRarity.EPIC;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(ModRareToolTip.getRareToolTip(setRarity()));
    }

    @Override
    public void onRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
        stack.damageItem(25, playerIn);
        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote) {
            EntityPotion entitypotion = new EntityPotion(worldIn, playerIn, PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.WEAKNESS));
            entitypotion.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -20.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(entitypotion);

            EntityPotion entitypotion2 = new EntityPotion(worldIn, playerIn, PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.SLOWNESS));
            entitypotion2.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -20.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(entitypotion2);
        }
        playerIn.setAbsorptionAmount(5);
    }
}
