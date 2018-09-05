package com.thenewjourney.blocks.furnace.block;

import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.blocks.furnace.tileentity.NetherrackFurnaceTileEntity;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class NetherrackFurnaceBlock extends BlockFurnace {
    private static boolean keepInventory;
    private static int smeltTime;

    public NetherrackFurnaceBlock(boolean isBurning, String unlocalizedName, int smeltTime) {
        super(isBurning);
        this.smeltTime = smeltTime;
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(5F);
        this.setResistance(100F);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new NetherrackFurnaceTileEntity(smeltTime);
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(ModBlocks.NetherrackFurnaceIdle);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        } else {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof NetherrackFurnaceTileEntity) {
                playerIn.displayGUIChest((NetherrackFurnaceTileEntity) tileentity);
                playerIn.addStat(StatList.FURNACE_INTERACTION);
            }

            return true;
        }
    }


    public static void setState(boolean active, World worldIn, BlockPos pos) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        keepInventory = true;

        if (active) {
            worldIn.setBlockState(pos, ModBlocks.NetherrackFurnaceActive.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, ModBlocks.NetherrackFurnaceActive.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        } else {
            worldIn.setBlockState(pos, ModBlocks.NetherrackFurnaceIdle.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, ModBlocks.NetherrackFurnaceIdle.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }

        keepInventory = false;

        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (!keepInventory) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof NetherrackFurnaceTileEntity) {
                InventoryHelper.dropInventoryItems(worldIn, pos, (NetherrackFurnaceTileEntity) tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
                worldIn.removeTileEntity(pos);
            }
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.NetherrackFurnaceIdle);
    }
}