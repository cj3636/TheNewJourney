package com.thenewjourney.items.register;

import com.cj3636.lib.Ref;
import com.thenewjourney.items.ModItems;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireStarterRegistry extends ItemFireball {

    public FireStarterRegistry(String unlocalizedName) {
        super();

        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.getHeldItemMainhand();

        if (worldIn.isRemote) {
            return EnumActionResult.SUCCESS;
        } else {
            pos = pos.offset(facing);
        }
        if (!playerIn.canPlayerEdit(pos, facing, stack)) {
            return EnumActionResult.FAIL;
        } else {
            if (worldIn.getBlockState(pos).getMaterial() == Material.AIR) {
                worldIn.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F,
                        (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2F + 1.0F);
                worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState());
            }

            if (!playerIn.capabilities.isCreativeMode && stack.getItem() != ModItems.FireLight) {
                stack.setCount(stack.getCount() - 1);
            }
            return EnumActionResult.SUCCESS;
        }
    }
}