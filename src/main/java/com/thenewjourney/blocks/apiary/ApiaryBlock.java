package com.thenewjourney.blocks.apiary;

import com.thenewjourney.Main.TheNewJourneyMod;
import com.thenewjourney.blocks.register.FaceRotatableBlockContainer;
import com.thenewjourney.particle.BeeParticle;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class ApiaryBlock extends FaceRotatableBlockContainer {

    public static final String name = "Apiary";
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public ApiaryBlock(String unlocalizedName, float hardness, float resistance) {
        super(unlocalizedName, Material.WOOD, hardness, resistance);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
        double dx = pos.getX();
        double dy = pos.getY();
        double dz = pos.getZ();

        dx += rand.nextFloat() * 2 - 1;
        dy += rand.nextFloat() * 2 - 1;
        dz += rand.nextFloat() * 2 - 1;

        BeeParticle beeParticle = new BeeParticle(worldIn, dx, dy, dz, 0.0F, 1.0F, 0.0F);
        Minecraft.getMinecraft().effectRenderer.addEffect(beeParticle);

        BeeParticle beeParticle2 = new BeeParticle(worldIn, -dx, -dy, -dz, 0.0F, 1.0F, 0.0F);
        Minecraft.getMinecraft().effectRenderer.addEffect(beeParticle2);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) return true;
        playerIn.openGui(TheNewJourneyMod.instance, ApiaryGUIRegistry.getGuiID(), worldIn, pos.getX(), pos.getY(), pos.getZ());
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
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
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
}