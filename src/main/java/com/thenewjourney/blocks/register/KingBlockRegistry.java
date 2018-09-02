package com.thenewjourney.blocks.register;

import com.cj3636.lib.Ref;
import com.thenewjourney.entity.king.KingEntity;
import com.thenewjourney.items.ModItems;
import com.thenewjourney.sound.ModSounds;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class KingBlockRegistry extends BlockBreakable {

    public static String modid = Ref.MODID;

    public KingBlockRegistry(String unlocalizedName, Material material) {
        super(material, true);
        this.setCreativeTab(Ref.CTAB);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = playerIn.getHeldItemMainhand();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        EnumFacing oppSide = facing.getOpposite();
        BlockPos launchDir = new BlockPos(pos).offset(oppSide);
        int xl = launchDir.getX();
        int yl = launchDir.getY();
        int zl = launchDir.getZ();
        if (heldItem != null) {
            if (heldItem.getItem() == ModItems.AquisIngot) {
                world.setBlockToAir(pos);

                Entity king = new KingEntity(world);
                if (!world.isRemote) {
                    king.setLocationAndAngles(x, y, z, MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F);
                    world.spawnEntity(king);
                }
                world.playSound(x, y, z, ModSounds.HoverExplosion, SoundCategory.HOSTILE, 10000.0F, 0.7F, true);
                playerIn.setVelocity(xl * .002, yl * .002, zl * .002);
                playerIn.addExperienceLevel(7);
            } else {
                playerIn.setFire(5);
            }
        } else {
            playerIn.setFire(5);
        }
        return true;
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    @Override
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }
}


