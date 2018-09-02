package com.thenewjourney.items.food;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NarcoBerrySeed extends Item {

    public NarcoBerrySeed(String unlocalizedName) {
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.getHeldItemMainhand();
        if (worldIn.getBlockState(pos).equals(Blocks.GRASS.getDefaultState()) && worldIn.getBlockState(pos.up()).equals(Blocks.AIR.getDefaultState())) {
            worldIn.setBlockState(pos.up(), ModBlocks.NarcoBush.getDefaultState());
            stack.setCount(stack.getCount() - 1);
            return EnumActionResult.PASS;
        }
        return EnumActionResult.FAIL;
    }
}
