package com.thenewjourney.items.bauble;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import com.cj3636.lib.Ref;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ModRingBasic extends Item implements IBauble {

    public ModRingBasic(String unlocalizedName, int maxDamage) {
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setMaxStackSize(1);
        this.setMaxDamage(maxDamage);
        this.setCreativeTab(Ref.CTAB);
    }

    protected void playerEquip(EntityLivingBase player) {
    }

    protected void playerUnequip(EntityLivingBase player) {
    }

    protected void tickPlayerEffect(EntityLivingBase player) {
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.RING;
    }

    public ModEnumRarity setRarity() {
        return ModEnumRarity.BASIC;
    }

    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.UNCOMMON;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(ModRareToolTip.getRareToolTip(setRarity()));
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack) {
        return true;
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
        playerEquip(player);
        if (player.world.isRemote) {
            player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F, 1.3f);
        }
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
        playerUnequip(player);
        if (player.world.isRemote) {
            player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.1F, 1.3f);
        }
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (player.ticksExisted % 39 == 0) {
            tickPlayerEffect(player);
            if (!player.getEntityWorld().isRemote) {
                itemstack.damageItem(1, player);
            }
        }
    }

    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
        ItemStack stack = player.getHeldItemMainhand();
        if (!world.isRemote) {
            IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
            for (int i = 0; i < baubles.getSlots(); i++)
                if (baubles.getStackInSlot(i) == null && baubles.isItemValidForSlot(i, stack, player)) {
                    baubles.setStackInSlot(i, stack.copy());
                    if (!player.capabilities.isCreativeMode) {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                    }
                    onEquipped(stack, player);
                    break;
                }
        }
        if (player.world.isRemote) {
            player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F, 1.3f);
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }
}
