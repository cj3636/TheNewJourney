package com.thenewjourney.items.register;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireSeedsRegistry extends ItemSeeds {
    public FireSeedsRegistry(Block crops, Block soil, String unlocalizedName) {
        super(crops, soil);
        this.setCreativeTab(Ref.CTAB);
        this.setRegistryName(unlocalizedName);
        this.setUnlocalizedName(unlocalizedName);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItemMainhand();

        if (world.getBlockState(pos).getBlock().equals(ModBlocks.ArcaneSoil) && world.isAirBlock(pos.up())) {
            world.setBlockState(pos.up(), ModBlocks.FireFern.getDefaultState());
            if (!player.capabilities.isCreativeMode) {
                stack.setCount(stack.getCount() - 1);
            }
            return EnumActionResult.PASS;
        }
        return EnumActionResult.FAIL;
    }
}