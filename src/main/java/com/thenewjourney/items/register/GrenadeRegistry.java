package com.thenewjourney.items.register;

import com.cj3636.lib.Ref;
import com.thenewjourney.entity.grenade.GrenadeEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class GrenadeRegistry extends Item {

    public GrenadeRegistry(String unlocalizedName) {
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemStackIn = playerIn.getHeldItemMainhand();

        if (!playerIn.capabilities.isCreativeMode) {
            itemStackIn.setCount(itemStackIn.getCount() - 1);

        }
        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isRemote) {
            GrenadeEntity entitygrenade = new GrenadeEntity(worldIn, playerIn);
            entitygrenade.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(entitygrenade);

        }
        return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
    }
}
