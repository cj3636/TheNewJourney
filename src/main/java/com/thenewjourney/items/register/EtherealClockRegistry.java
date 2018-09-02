package com.thenewjourney.items.register;

import com.cj3636.lib.Ref;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EtherealClockRegistry extends Item {
    public EtherealClockRegistry(String unlocalizedName, int maxDamage) {
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
        this.setMaxDamage(maxDamage);
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemStackIn = playerIn.getHeldItemMainhand();
        worldIn.setWorldTime(1000);
        itemStackIn.damageItem(1, playerIn);
        return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
    }
}
