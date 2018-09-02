package com.thenewjourney.blocks.infuser;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InfuserItem extends Item {

    public InfuserItem(String unlocalizedName) {
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = player.getHeldItemMainhand();
        BlockPos place = pos.offset(facing);
        if (worldIn.getBlockState(place).getBlock() != Blocks.AIR) {
            return EnumActionResult.FAIL;
        }
        worldIn.setBlockState(place, ModBlocks.ArchaicInfuser.getDefaultState(), 3);
        player.playSound(SoundEvents.BLOCK_STONE_PLACE, 1.0F, 1.0F);
        if (!player.capabilities.isCreativeMode)
            heldItem.setCount(heldItem.getCount() - 1);
        return EnumActionResult.PASS;
    }
}