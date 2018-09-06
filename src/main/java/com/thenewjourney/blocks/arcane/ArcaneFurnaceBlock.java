package com.thenewjourney.blocks.arcane;

import com.cj3636.lib.LocUtil;
import com.thenewjourney.Main.TheNewJourneyMod;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.blocks.register.FaceRotatableBlockContainer;
import com.thenewjourney.power.ModPower;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by cj3636 on 1/27/2017.
 */
public class ArcaneFurnaceBlock extends FaceRotatableBlockContainer {

    public ArcaneFurnaceBlock(String unlocalizedName, Material material, float hardness, float resistance) {
        super(unlocalizedName, material, hardness, resistance);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new ArcaneFurnaceTileEntity();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) return true;
        if (ModPower.getPowerTier(worldIn) < 2) {
            playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Too low of power tier! At least 2 is required."));
            return true;
        }
        if (LocUtil.checkVertical(pos, worldIn, ModBlocks.WarpStone.getDefaultState(), side) &&
                LocUtil.checkVerticalNoTop(pos.offset(side.getOpposite()), worldIn, ModBlocks.WarpStone.getDefaultState(), side) &&
                LocUtil.checkVerticalAndCenter(pos.offset(side.getOpposite(), 2), worldIn, ModBlocks.WarpStone.getDefaultState(), side)) {
            playerIn.openGui(TheNewJourneyMod.instance, ArcaneFurnaceGUIRegistry.getGuiID(), worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        } else {
            if (!worldIn.isRemote) {
                playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Invalid setup! Furnace is Facing: " + side));
                playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Invalid setup! Furnace is Facing: " + LocUtil.checkVertical(pos, worldIn, ModBlocks.WarpStone.getDefaultState(), side)));
                playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Invalid setup! Furnace is Facing: " + LocUtil.checkVerticalNoTop(pos.offset(side.getOpposite()), worldIn, ModBlocks.WarpStone.getDefaultState(), side)));
                playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Invalid setup! Furnace is Facing: " + LocUtil.checkVerticalAndCenter(pos.offset(side.getOpposite()).offset(side.getOpposite()), worldIn, ModBlocks.WarpStone.getDefaultState(), side)));
                return true;
            }
        }
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof IInventory) {
            InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileEntity);
        }
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("incomplete-switch")
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
