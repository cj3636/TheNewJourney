package com.thenewjourney.blocks.portal;

import com.cj3636.lib.Config;
import com.thenewjourney.dimension.ModTeleporter;
import com.thenewjourney.particle.FireParticle;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class VisceonFirePortal extends BlockRotatedPillar {

    public static final String name = "Sphere";

    public VisceonFirePortal(String unlocalizedName) {
        super(Material.IRON);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockRotatedPillar.AXIS, EnumFacing.Axis.X));
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setLightOpacity(0);
        this.setBlockUnbreakable();
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer playerIn = (EntityPlayer) entityIn;
            if (!worldIn.isRemote) {
                if (playerIn.dimension == 0) {
                    WorldServer worldTo = playerIn.getServer().getWorld(Config.fireDimId);
                    ModTeleporter teleporter = new ModTeleporter(worldTo);
                    playerIn.getServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) playerIn, Config.fireDimId, teleporter);
                } else {
                    WorldServer worldTo = playerIn.getServer().getWorld(0);
                    ModTeleporter teleporter = new ModTeleporter(worldTo);
                    playerIn.getServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) playerIn, 0, teleporter);
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        int textParticles = rand.nextInt(9);
        for (int i = 0; i < textParticles; i++) {
            double xP = (rand.nextFloat() * 8.0) - 4.0;
            double yP = (rand.nextFloat() * 8.0) - 4.0;
            double zP = (rand.nextFloat() * 8.0) - 4.0;

            FireParticle FireEffect = new FireParticle(worldIn, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, xP, yP, zP);
            Minecraft.getMinecraft().effectRenderer.addEffect(FireEffect);

            FireParticle FireEffect2 = new FireParticle(worldIn, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, -xP, -yP, -zP);
            Minecraft.getMinecraft().effectRenderer.addEffect(FireEffect2);

        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.375D, 0.375D, 0.375D, 0.625D, 0.625D, 0.625D);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

}