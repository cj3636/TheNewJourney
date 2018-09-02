package com.thenewjourney.items.register;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.blocks.cauldron.ImbuedCauldron;
import com.thenewjourney.items.ModItems;
import net.minecraft.block.state.IBlockState;
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

public class GobletRegistry extends Item {

    public GobletRegistry(String unlocalizedName) {
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
        this.maxStackSize = 1;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.getHeldItemMainhand();
        if (this.equals(ModItems.Goblet)) {
            IBlockState blockOn = worldIn.getBlockState(pos);
            if (blockOn.equals(ModBlocks.ImbuedCauldron.getDefaultState().withProperty(ImbuedCauldron.LEVEL, 1))) {
                playerIn.setHeldItem(hand, new ItemStack(ModItems.GobletOfFire, stack.getCount()));
                worldIn.setBlockState(pos, ModBlocks.ImbuedCauldron.getDefaultState());
                worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.BLOCK_WATERLILY_PLACE,
                        SoundCategory.BLOCKS, 50.0F, 50.0F);
                worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.BLOCK_LAVA_EXTINGUISH,
                        SoundCategory.BLOCKS, 50.0F, 50.0F);
                return EnumActionResult.PASS;
            } else if (blockOn
                    .equals(ModBlocks.ImbuedCauldron.getDefaultState().withProperty(ImbuedCauldron.LEVEL, 2))) {
                playerIn.setHeldItem(hand, new ItemStack(ModItems.GobletOfIce, stack.getCount()));
                worldIn.setBlockState(pos, ModBlocks.ImbuedCauldron.getDefaultState());
                worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.BLOCK_WATERLILY_PLACE,
                        SoundCategory.BLOCKS, 50.0F, 50.0F);
                worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.ENTITY_ENDERDRAGON_FIREBALL_EPLD,
                        SoundCategory.BLOCKS, 50.0F, 50.0F);
                return EnumActionResult.PASS;
            } else if (blockOn
                    .equals(ModBlocks.ImbuedCauldron.getDefaultState().withProperty(ImbuedCauldron.LEVEL, 3))) {
                playerIn.setHeldItem(hand, new ItemStack(ModItems.GobletOfEmerald, stack.getCount()));
                worldIn.setBlockState(pos, ModBlocks.ImbuedCauldron.getDefaultState());
                worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.BLOCK_WATERLILY_PLACE,
                        SoundCategory.BLOCKS, 50.0F, 50.0F);
                worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.ENTITY_SHULKER_SHOOT,
                        SoundCategory.BLOCKS, 50.0F, 0.05F);
                return EnumActionResult.PASS;
            }
        }
        if (stack.getItem().equals(ModItems.GobletOfFire)) {
            worldIn.setBlockState(pos.up(), ModBlocks.GobletBlock.getDefaultState()
                    .withProperty(com.thenewjourney.blocks.register.GobletRegistry.FACING, EnumFacing.WEST));
            if (!playerIn.capabilities.isCreativeMode) {
                stack.setCount(stack.getCount() - 1);
            }
            return EnumActionResult.PASS;
        } else if (stack.getItem().equals(ModItems.GobletOfIce)) {
            worldIn.setBlockState(pos.up(), ModBlocks.GobletBlock.getDefaultState()
                    .withProperty(com.thenewjourney.blocks.register.GobletRegistry.FACING, EnumFacing.EAST));
            if (!playerIn.capabilities.isCreativeMode) {
                stack.setCount(stack.getCount() - 1);
            }
            return EnumActionResult.PASS;
        } else if (stack.getItem().equals(ModItems.GobletOfEmerald)) {
            worldIn.setBlockState(pos.up(), ModBlocks.GobletBlock.getDefaultState()
                    .withProperty(com.thenewjourney.blocks.register.GobletRegistry.FACING, EnumFacing.SOUTH));
            if (!playerIn.capabilities.isCreativeMode) {
                stack.setCount(stack.getCount() - 1);
            }
            return EnumActionResult.PASS;
        } else {
            worldIn.setBlockState(pos.up(), ModBlocks.GobletBlock.getDefaultState());
            if (!playerIn.capabilities.isCreativeMode) {
                stack.setCount(stack.getCount() - 1);
            }
            return EnumActionResult.PASS;
        }
    }
}
