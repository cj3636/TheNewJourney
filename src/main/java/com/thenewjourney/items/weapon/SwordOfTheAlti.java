package com.thenewjourney.items.weapon;

import com.thenewjourney.items.bauble.ModEnumRarity;
import com.thenewjourney.items.bauble.ModRareToolTip;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by cj3636 on 4/14/2017.
 */
public class SwordOfTheAlti extends WeaponRegistry {

    public SwordOfTheAlti(String unlocalizedName, ToolMaterial material, float damage, float speed, int durability) {
        super(unlocalizedName, material, damage, speed, durability);
    }

    public ModEnumRarity setRarity() {
        return ModEnumRarity.LEGENDARY;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(ModRareToolTip.getRareToolTip(setRarity()));
    }

    protected void onRightBlock(World worldIn, EntityPlayer playerIn, ItemStack stack, BlockPos pos) {
        playerIn.setPosition(playerIn.posX, playerIn.posY + 1, playerIn.posZ);
        IBlockState blockOn = worldIn.getBlockState(pos);
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
        worldIn.setBlockState(pos.up(), blockOn);
    }

    protected void onAltRightBlock(World worldIn, EntityPlayer playerIn, ItemStack stack, BlockPos pos) {
        playerIn.setAbsorptionAmount(8);
    }

    protected void onCtrlRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
        if (EntityPlayer.getBedSpawnLocation(worldIn, playerIn.getBedLocation(), true) != null) {
            double x = EntityPlayer.getBedSpawnLocation(worldIn, playerIn.getBedLocation(), true).getX();
            double y = EntityPlayer.getBedSpawnLocation(worldIn, playerIn.getBedLocation(), true).getY();
            double z = EntityPlayer.getBedSpawnLocation(worldIn, playerIn.getBedLocation(), true).getZ();
            playerIn.setPositionAndUpdate(x, y, z);
        }
    }

    protected void onShiftRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
        Vec3d lookVec = playerIn.getLookVec();
        double x = lookVec.x;
        double y = lookVec.y;
        double z = lookVec.z;
        playerIn.setVelocity(-(x * 10), y, -(z * 10));
        stack.damageItem(1, playerIn);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!isSelected) {
            if (entityIn instanceof EntityLivingBase) {
                EntityLivingBase entityLiving = (EntityLivingBase) entityIn;
                entityLiving.setAbsorptionAmount(0);
            }
        }
    }
}
