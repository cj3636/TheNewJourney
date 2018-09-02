package com.thenewjourney.items.weapon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by cj3636 on 4/14/2017.
 */
public class FlorucSword extends WeaponRegistry {

    public FlorucSword(String unlocalizedName, ToolMaterial material, float damage, float speed, int durability) {
        super(unlocalizedName, material, damage, speed, durability);
    }
    protected void onShiftRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
        stack.damageItem(15, playerIn);
        playerIn.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
        playerIn.playSound(SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT, 10F, 1F);
    }
}
