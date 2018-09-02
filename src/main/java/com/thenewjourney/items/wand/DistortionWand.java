package com.thenewjourney.items.wand;

import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.blocks.stoneman.StoneManBlock;
import com.thenewjourney.entity.lightning.ColoredLightningEntity;
import com.thenewjourney.fluid.ModFluids;
import com.thenewjourney.items.ModItems;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class DistortionWand extends WandRegistry {

    public DistortionWand(String unlocalizedName, ToolMaterial material, int maxDamage) {
        super(unlocalizedName, material, maxDamage);
    }

    @Override
    public void onRightBlock(World worldIn, EntityPlayer playerIn, ItemStack stack, BlockPos pos) {
        IBlockState blockOn = worldIn.getBlockState(pos);
        worldIn.spawnEntity(new ColoredLightningEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), true));
        if (blockOn.getBlock() == ModBlocks.Distributor) {
            if (worldIn.isDaytime()) {
                if (!worldIn.isRemote) {
                    playerIn.sendMessage(new TextComponentTranslation("\u00A74" + "\u00A7o" + "It is too early..."));
                }
            } else {
                runWandCommand(worldIn, pos, stack, playerIn);
            }
        }
        if (blockOn.getBlock() == Blocks.CAULDRON) {
            worldIn.setBlockState(pos, ModBlocks.ImbuedCauldron.getDefaultState());
            stack.damageItem(1, playerIn);
            return;
        }
        if (blockOn.getBlock() == Blocks.LIT_PUMPKIN) {
            runWandStoneMan(worldIn, pos, blockOn.getValue(BlockPumpkin.FACING), playerIn);
            stack.damageItem(1, playerIn);
        }
    }

    private void runWandStoneMan(World worldIn, BlockPos pos, EnumFacing facing, EntityPlayer playerIn) {
        if (facing.equals(EnumFacing.EAST) || facing.equals(EnumFacing.WEST)) {
            IBlockState posP = worldIn.getBlockState(pos);
            IBlockState posB1 = worldIn.getBlockState(pos.down());
            IBlockState posB2 = worldIn.getBlockState(pos.down(2));
            IBlockState posF1 = worldIn.getBlockState(pos.down().north());
            IBlockState posF2 = worldIn.getBlockState(pos.down().south());
            if (posP.getBlock().equals(Blocks.LIT_PUMPKIN) && posB1.getBlock().equals(ModBlocks.BurntStone)
                    && posB2.getBlock().equals(ModBlocks.BurntStone)
                    && posF1.getBlock().equals(Blocks.NETHER_BRICK_FENCE)
                    && posF2.getBlock().equals(Blocks.NETHER_BRICK_FENCE)) {
                worldIn.setBlockToAir(pos);
                worldIn.setBlockToAir(pos.down());
                worldIn.setBlockToAir(pos.down(2));
                worldIn.setBlockToAir(pos.down().north());
                worldIn.setBlockToAir(pos.down().south());
                worldIn.setBlockState(pos.down(2),
                        ModBlocks.StoneMan.getDefaultState().withProperty(StoneManBlock.FACING, facing));
            } else {
                playerIn.sendMessage(new TextComponentTranslation(
                        "\u00A74" + "\u00A7o" + "Attempted East | West axis stoneman failed!"));
            }
        } else if (facing.equals(EnumFacing.NORTH) || facing.equals(EnumFacing.SOUTH)) {
            IBlockState posP = worldIn.getBlockState(pos);
            IBlockState posB1 = worldIn.getBlockState(pos.down());
            IBlockState posB2 = worldIn.getBlockState(pos.down(2));
            IBlockState posF1 = worldIn.getBlockState(pos.down().east());
            IBlockState posF2 = worldIn.getBlockState(pos.down().west());
            if (posP.getBlock().equals(Blocks.LIT_PUMPKIN) && posB1.getBlock().equals(ModBlocks.BurntStone)
                    && posB2.getBlock().equals(ModBlocks.BurntStone)
                    && posF1.getBlock().equals(Blocks.NETHER_BRICK_FENCE)
                    && posF2.getBlock().equals(Blocks.NETHER_BRICK_FENCE)) {
                worldIn.setBlockToAir(pos);
                worldIn.setBlockToAir(pos.down());
                worldIn.setBlockToAir(pos.down(2));
                worldIn.setBlockToAir(pos.down().east());
                worldIn.setBlockToAir(pos.down().west());
                worldIn.setBlockState(pos.down(2),
                        ModBlocks.StoneMan.getDefaultState().withProperty(StoneManBlock.FACING, facing));
            } else {
                playerIn.sendMessage(new TextComponentTranslation(
                        "\u00A74" + "\u00A7o" + "Attempted North | South axis stoneman failed!"));
            }
        }
    }

    @Override
    protected void onAltRightBlock(World worldIn, EntityPlayer playerIn, ItemStack stack, BlockPos pos) {
        if (worldIn.getBlockState(pos).getBlock().equals(Blocks.BOOKSHELF)) {
            worldIn.destroyBlock(pos, false);
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(),
                        new ItemStack(ModItems.ArcaneArtifact)));
            }
            if (!playerIn.capabilities.isCreativeMode) {
                stack.setCount(stack.getCount() - 1);
                worldIn.playSound(playerIn, pos, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 1.0F, 1.0F);
            }
        } else {
            playerIn.sendMessage(
                    new TextComponentTranslation("\u00A74" + "\u00A7o" + "Block was not a vanilla bookshelf!"));
        }
    }

    private void runWandCommand(World worldIn, BlockPos pos, ItemStack stack, EntityPlayer playerIn) {

        IBlockState block_n = worldIn.getBlockState(pos.north());
        IBlockState block_s = worldIn.getBlockState(pos.south());
        IBlockState block_e = worldIn.getBlockState(pos.east());
        IBlockState block_w = worldIn.getBlockState(pos.west());

        BlockPos posDown = pos.down();

        IBlockState block_d = worldIn.getBlockState(posDown);

        IBlockState block_f = worldIn.getBlockState(posDown.north());
        IBlockState block_b = worldIn.getBlockState(posDown.south());
        IBlockState block_r = worldIn.getBlockState(posDown.east());
        IBlockState block_l = worldIn.getBlockState(posDown.west());

        IBlockState block_fr = worldIn.getBlockState(posDown.north().east());
        IBlockState block_fl = worldIn.getBlockState(posDown.north().west());
        IBlockState block_br = worldIn.getBlockState(posDown.south().east());
        IBlockState block_bl = worldIn.getBlockState(posDown.south().west());
        IBlockState check = ModBlocks.DistortedBricks.getDefaultState();
        IBlockState check2 = ModBlocks.DistortedStone.getDefaultState();
        if (block_n.equals(check) && block_s.equals(check) && block_e.equals(check) && block_w.equals(check)
                && block_d.equals(check) && block_f.equals(check) && block_b.equals(check) && block_r.equals(check)
                && block_l.equals(check) && block_fr.equals(check) && block_fl.equals(check) && block_br.equals(check)
                && block_bl.equals(check)) {
            worldIn.setBlockState(pos, ModFluids.InversionSerum.getBlock().getDefaultState());
            stack.damageItem(1, playerIn);
        } else if (block_n.equals(check2) && block_s.equals(check2) && block_e.equals(check2) && block_w.equals(check2)
                && block_d.equals(check2) && block_f.equals(check2) && block_b.equals(check2) && block_r.equals(check2)
                && block_l.equals(check2) && block_fr.equals(check2) && block_fl.equals(check2)
                && block_br.equals(check2) && block_bl.equals(check2)) {
            worldIn.setBlockState(pos, ModFluids.InversionSerum.getBlock().getDefaultState());
            stack.damageItem(1, playerIn);
        } else {
            playerIn.sendMessage(new TextComponentTranslation("\u00A74" + "\u00A7o"
                    + "Invalid cauldron setup! Must be L:3 W: 3 H: 2 and made of Distorted Stone or Distorted Bricks."));
        }
    }
}