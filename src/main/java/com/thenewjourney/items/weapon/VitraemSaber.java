package com.thenewjourney.items.weapon;

import com.thenewjourney.items.bauble.ModEnumRarity;
import com.thenewjourney.items.bauble.ModRareToolTip;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by cj3636 on 4/14/2017.
 */
public class VitraemSaber extends WeaponRegistry {

    public VitraemSaber(String unlocalizedName, ToolMaterial material, float damage, float speed, int durability) {
        super(unlocalizedName, material, damage, speed, durability);
    }

    public ModEnumRarity setRarity() {
        return ModEnumRarity.LEGENDARY;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(ModRareToolTip.getRareToolTip(setRarity()));
    }

    protected void onRightBlock(World worldIn, EntityPlayer playerIn, ItemStack stack, BlockPos pos) {
        playerIn.setPosition(playerIn.posX, playerIn.posY + 1, playerIn.posZ);
        IBlockState blockOn = worldIn.getBlockState(pos);
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
        worldIn.setBlockState(pos.up(), blockOn);
    }


    protected void onShiftRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
        if (!worldIn.isRemote) {
            ItemStack thePotion = PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.STRONG_POISON);
            EntityPotion entitypotion = new EntityPotion(worldIn, playerIn, thePotion);
            entitypotion.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -20.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(entitypotion);
        }
    }

    protected void onCtrlRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
        playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED, 800, 2, false, false));
    }

    protected void onAltRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
        playerIn.heal(2);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!isSelected) {
            return;
        }
        if (entityIn instanceof EntityLivingBase) {

            EntityLivingBase entityLiving = (EntityLivingBase) entityIn;
            entityLiving.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 40, 3, false, false));
            entityLiving.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 40, 3, false, false));
        }
    }
}
