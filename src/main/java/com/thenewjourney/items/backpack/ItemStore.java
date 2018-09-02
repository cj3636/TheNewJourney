package com.thenewjourney.items.backpack;

import com.cj3636.lib.Ref;
import com.thenewjourney.Main.TheNewJourneyMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemStore extends Item {
    public ItemStore(String par1) {
        super();
        this.setUnlocalizedName(par1);
        this.setRegistryName(par1);
        // ItemStacks that store an NBT Tag Compound are limited to stack size of 1
        this.setMaxStackSize(1);
        // you'll want to set a creative tab as well, so you can get your item
        this.setCreativeTab(Ref.CTAB);
    }

    // Without this method, your inventory will NOT work!!!
    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 1; // return any value greater than zero
    }

    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if (!worldIn.isRemote) {
            playerIn.openGui(TheNewJourneyMod.instance, BackpackGUIRegistry.getGuiID(), worldIn, 0, 0, 0);
            return new ActionResult(EnumActionResult.PASS, itemStackIn);
        }
        return new ActionResult(EnumActionResult.FAIL, itemStackIn);
    }
}