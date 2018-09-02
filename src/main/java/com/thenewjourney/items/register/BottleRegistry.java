package com.thenewjourney.items.register;

import com.cj3636.lib.Ref;
import com.thenewjourney.fluid.ModFluids;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BottleRegistry extends Item {
    public BottleRegistry(String unlocalizedName) {
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.getHeldItemMainhand();
        BlockPos directionPos = new BlockPos(pos);
        directionPos.offset(facing.getOpposite());
        worldIn.playSound(playerIn, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.AMBIENT, 10.0F, 0.8F + (float) Math.random() * 0.2F);
        worldIn.setBlockState(directionPos, ModFluids.InversionSerum.getBlock().getDefaultState());
        if (!playerIn.capabilities.isCreativeMode) {
            stack.setCount(stack.getCount() - 1);
        }
        return EnumActionResult.PASS;
    }
}
