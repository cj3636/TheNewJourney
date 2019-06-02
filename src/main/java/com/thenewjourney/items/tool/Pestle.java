package com.thenewjourney.items.tool;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.items.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Pestle extends ItemFood {

    private boolean isReady = false;

    public Pestle(String unlocalizedName) {
        super(0, 0, false);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
        this.setMaxStackSize(1);
        this.setMaxDamage(15);
        this.setNoRepair();
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (worldIn.getBlockState(pos).getBlock().equals(ModBlocks.Mortar)) {
                if (player.getHeldItemOffhand().getItem().equals(ModItems.NarcoBerry)) {
                    if (player.getHeldItemOffhand().getCount() >= 16) {
                        if (!player.capabilities.isCreativeMode) {
                            player.getHeldItemOffhand().shrink(16);

                        }
                        isReady = true;
                    }
                }
            }
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (isReady) {
            EntityItem entityitem = new EntityItem(worldIn, entityLiving.posX, entityLiving.posY, entityLiving.posZ, new ItemStack(ModItems.Narcotic));
            worldIn.spawnEntity(entityitem);
            if (entityLiving instanceof EntityPlayer && !((EntityPlayer) entityLiving).capabilities.isCreativeMode) {
                stack.damageItem(1, entityLiving);
            }
        }
        isReady = false;
        return stack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 90;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }
}
