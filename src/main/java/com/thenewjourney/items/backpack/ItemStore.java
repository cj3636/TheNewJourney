package com.thenewjourney.items.backpack;

import com.cj3636.lib.Ref;
import com.thenewjourney.Main.TheNewJourneyMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemStore extends Item {
    public ItemStore(String par1) {
        super();
        this.setUnlocalizedName(par1);
        this.setRegistryName(par1);
        this.setMaxStackSize(1);
        this.setCreativeTab(Ref.CTAB);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 1; // return any value greater than zero
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote) {
            playerIn.openGui(TheNewJourneyMod.instance, BackpackGUIRegistry.getGuiID(), worldIn, 0, 0, 0);
            System.out.println("rc");
            return new ActionResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
}