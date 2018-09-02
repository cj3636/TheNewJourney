package com.thenewjourney.items.food;

import com.cj3636.lib.Ref;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by cj3636 on 1/21/2017.
 */
public class JellyItem extends ItemFood {
    /**
     * Number of ticks to run while 'EnumAction'ing until result.
     */
    public final int itemUseDuration;
    /**
     * The amount this food item heals the player.
     */
    private final int healAmount;
    private final float saturationModifier;
    /**
     * Whether wolves like this food (true for raw and cooked porkchop).
     */
    private final boolean isWolfsFavoriteMeat;
    /**
     * If this field is true, the food can be consumed even if the player don't need to eat.
     */
    private boolean alwaysEdible;
    /**
     * represents the potion effect that will occurr upon eating this food. Set by setPotionEffect
     */
    private PotionEffect potionId;
    /**
     * probably of the set potion effect occurring
     */
    private float potionEffectProbability;

    public JellyItem(String unlocalizedName, int amount, float saturation, boolean isWolfFood, int durability) {
        super(amount, saturation, isWolfFood);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
        this.itemUseDuration = 4;
        this.healAmount = amount;
        this.isWolfsFavoriteMeat = isWolfFood;
        this.saturationModifier = saturation;
        this.setMaxDamage(durability);
    }

    /**
     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
     * the Item before the action is complete.
     */
    @Nullable
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) entityLiving;
            entityplayer.getFoodStats().addStats(this, stack);
            worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityplayer);
            entityplayer.addStat(StatList.getObjectUseStats(this));
        }
        stack.damageItem(1, entityLiving);
        return stack;
    }

    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote && this.potionId != null && worldIn.rand.nextFloat() < this.potionEffectProbability) {
            player.addPotionEffect(new PotionEffect(this.potionId));
        }
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack) {
        return this.itemUseDuration;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.EAT;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemStackIn = playerIn.getHeldItemMainhand();
        if (playerIn.canEat(this.alwaysEdible)) {
            playerIn.setActiveHand(handIn);
            return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
        } else {
            return new ActionResult(EnumActionResult.FAIL, itemStackIn);
        }
    }

    public int getHealAmount(ItemStack stack) {
        return this.healAmount;
    }

    public float getSaturationModifier(ItemStack stack) {
        return this.saturationModifier;
    }

    /**
     * Whether wolves like this food (true for raw and cooked porkchop).
     */
    public boolean isWolfsFavoriteMeat() {
        return this.isWolfsFavoriteMeat;
    }
}
