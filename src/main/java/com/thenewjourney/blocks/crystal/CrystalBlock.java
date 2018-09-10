package com.thenewjourney.blocks.crystal;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.items.ModItems;
import com.thenewjourney.power.EnumPowerColor;
import com.thenewjourney.power.ModPower;
import com.thenewjourney.world.ModWorldSaveData;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.awt.*;
import java.util.Random;

public class CrystalBlock extends BlockContainer {

    public static Color gemColor;

    public CrystalBlock(String unlocalizedName) {
        super(Material.IRON);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(8F);
        this.setResistance(120F);
        this.setLightLevel(.8F);
        this.setCreativeTab(Ref.CTAB);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
                                ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof CrystalTileEntity) {
            CrystalTileEntity tileEntityCrystal = (CrystalTileEntity) tileentity;
            Color gemColor = ModPower.getPowerColor(worldIn);
            tileEntityCrystal.setGemColour(gemColor);
            CrystalBlock.gemColor = gemColor;
        }
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            ModWorldSaveData data = ModWorldSaveData.forWorld(worldIn);
            data.setPowerNum(data.getPowerNum() + 1);
            if (data.getPowerTier() < 1) {
                data.setPowerTier(1);
            }
        }
        super.onBlockAdded(worldIn, pos, state);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            ModWorldSaveData data = ModWorldSaveData.forWorld(worldIn);
            if (data.getPowerNum() > 0) {
                data.setPowerNum(data.getPowerNum() - 1);
            } else {
                data.setPowerNum(0);
            }
        }
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.Crystal);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new CrystalTileEntity();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = playerIn.getHeldItemMainhand();
        if (heldItem != null && heldItem.getItem().equals(ModItems.CrystalBinder)) {
            worldIn.setBlockState(pos.up(), Blocks.FIRE.getDefaultState(), 3);
            worldIn.setBlockState(pos, ModBlocks.Distributor.getDefaultState(), 3);
            worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE,
                    SoundCategory.AMBIENT, 10000.0F, 0.8F + (float) Math.random() * 0.2F, true);
            if (!playerIn.capabilities.isCreativeMode) {
                heldItem.setCount(heldItem.getCount() - 1);
            }
            return true;
        }
        if (!worldIn.isRemote) {
            ModWorldSaveData data = ModWorldSaveData.forWorld(worldIn);
            if (heldItem != null && heldItem.getItem().equals(ModItems.UpgradeTwo)) {
                if (data.getPowerTier() > 1) {
                    return false;
                }
                data.setPowerTier(2);
                playerIn.sendMessage(new TextComponentTranslation(EnumPowerColor
                        .getColorTranslation(EnumPowerColor.getTierFromInt(ModPower.getPowerTier(worldIn)))
                        + "Upgraded Crystal Power to tier: " + Integer.toString(ModPower.getPowerTier(worldIn))));
                if (!playerIn.capabilities.isCreativeMode) {
                    heldItem.setCount(heldItem.getCount() - 1);
                }
            } else if (heldItem != null && heldItem.getItem().equals(ModItems.UpgradeThree)) {
                if (data.getPowerTier() != 2) {
                    return false;
                }
                data.setPowerTier(3);
                playerIn.sendMessage(new TextComponentTranslation(EnumPowerColor
                        .getColorTranslation(EnumPowerColor.getTierFromInt(ModPower.getPowerTier(worldIn)))
                        + "Upgraded Crystal Power to tier: " + Integer.toString(ModPower.getPowerTier(worldIn))));
                if (!playerIn.capabilities.isCreativeMode) {
                    heldItem.setCount(heldItem.getCount() - 1);
                }
            } else if (heldItem != null && heldItem.getItem().equals(ModItems.UpgradeFour)) {
                if (data.getPowerTier() != 3) {
                    return false;
                }
                data.setPowerTier(4);
                playerIn.sendMessage(new TextComponentTranslation(EnumPowerColor
                        .getColorTranslation(EnumPowerColor.getTierFromInt(ModPower.getPowerTier(worldIn)))
                        + "Upgraded Crystal Power to tier: " + Integer.toString(ModPower.getPowerTier(worldIn))));
                if (!playerIn.capabilities.isCreativeMode) {
                    heldItem.setCount(heldItem.getCount() - 1);
                }
            } else if (heldItem != null && heldItem.getItem().equals(ModItems.UpgradeFive)) {
                if (data.getPowerTier() != 4) {
                    return false;
                }
                data.setPowerTier(5);
                playerIn.sendMessage(new TextComponentTranslation(EnumPowerColor
                        .getColorTranslation(EnumPowerColor.getTierFromInt(ModPower.getPowerTier(worldIn)))
                        + "Upgraded Crystal Power to tier: " + Integer.toString(ModPower.getPowerTier(worldIn))));
                if (!playerIn.capabilities.isCreativeMode) {
                    heldItem.setCount(heldItem.getCount() - 1);
                }
            } else if (heldItem != null && heldItem.getItem().equals(ModItems.UpgradeSix)) {
                if (data.getPowerTier() != 5) {
                    return false;
                }
                data.setPowerTier(6);
                playerIn.sendMessage(new TextComponentTranslation(EnumPowerColor
                        .getColorTranslation(EnumPowerColor.getTierFromInt(ModPower.getPowerTier(worldIn)))
                        + "Upgraded Crystal Power to tier: " + Integer.toString(ModPower.getPowerTier(worldIn))));
                if (!playerIn.capabilities.isCreativeMode) {
                    heldItem.setCount(heldItem.getCount() - 1);
                }
            } else if (heldItem != null && heldItem.getItem().equals(ModItems.UpgradeSeven)) {
                if (data.getPowerTier() != 6) {
                    return false;
                }
                data.setPowerTier(7);
                playerIn.sendMessage(new TextComponentTranslation(EnumPowerColor
                        .getColorTranslation(EnumPowerColor.getTierFromInt(ModPower.getPowerTier(worldIn)))
                        + "Upgraded Crystal Power to tier: " + Integer.toString(ModPower.getPowerTier(worldIn))));
                if (!playerIn.capabilities.isCreativeMode) {
                    heldItem.setCount(heldItem.getCount() - 1);
                }
            } else if (heldItem != null && heldItem.getItem().equals(ModItems.UpgradeEight)) {
                if (data.getPowerTier() != 7) {
                    return false;
                }
                data.setPowerTier(8);
                playerIn.sendMessage(new TextComponentTranslation(EnumPowerColor
                        .getColorTranslation(EnumPowerColor.getTierFromInt(ModPower.getPowerTier(worldIn)))
                        + "Upgraded Crystal Power to tier: " + Integer.toString(ModPower.getPowerTier(worldIn))));
                if (!playerIn.capabilities.isCreativeMode) {
                    heldItem.setCount(heldItem.getCount() - 1);
                }
            } else {
                playerIn.sendMessage(new TextComponentTranslation("World Tier: " + Integer.toString(ModPower.getPowerTier(worldIn))));
                playerIn.sendMessage(new TextComponentTranslation("World Blocks: " + Integer.toString(ModPower.getPowerNum(worldIn))));
            }
        }
        return false;
    }
}