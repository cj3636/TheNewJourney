package com.thenewjourney.blocks.idol;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.blocks.provider.CrystalProvider;
import com.thenewjourney.particle.BubblingFireParticle;
import com.thenewjourney.power.ModPower;
import com.thenewjourney.world.ModWorldSaveData;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.Random;

public class IdolBlock extends BlockContainer {

    public static Color gemColor;

    public IdolTileEntity getTileEntity() {
        return tileEntity;
    }

    private IdolTileEntity tileEntity;

    public IdolBlock(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);
        this.setCreativeTab(Ref.CTAB);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        tileEntity.setFullActive(false);
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (playerIn.getHeldItemMainhand() != ItemStack.EMPTY) {
            return false;
        }
        if (!(ModPower.getPowerTier(worldIn) >= 5)) {
            playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Too low of a power tier! At least 5 is required."));
            return false;
        }
        if (worldIn.getBlockState(pos.north(2)).equals(ModBlocks.CrystalProvider.getDefaultState().withProperty(CrystalProvider.FACING, EnumFacing.SOUTH)) &&
                worldIn.getBlockState(pos.south(2)).equals(ModBlocks.CrystalProvider.getDefaultState().withProperty(CrystalProvider.FACING, EnumFacing.NORTH)) &&
                worldIn.getBlockState(pos.west(2)).equals(ModBlocks.CrystalProvider.getDefaultState().withProperty(CrystalProvider.FACING, EnumFacing.EAST)) &&
                worldIn.getBlockState(pos.east(2)).equals(ModBlocks.CrystalProvider.getDefaultState().withProperty(CrystalProvider.FACING, EnumFacing.WEST))) {
            tileEntity.setActive(true);
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        IdolTileEntity thisTileEntity = new IdolTileEntity();
        this.tileEntity = thisTileEntity;
        return thisTileEntity;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        Color gemColor = Color.BLUE;
        this.gemColor = gemColor;
        tileEntity.setGemColour(gemColor);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
        double dx = (double) pos.getX() + .5D;
        double dy = (double) pos.getY() + .5D;
        double dz = (double) pos.getZ() + .5D;
        double speedMult = 36D;
        float[] red = {rand.nextFloat(), rand.nextFloat(), rand.nextFloat()};
        BubblingFireParticle fireEffect = new BubblingFireParticle(worldIn, dx, dy, dz,
                -rand.nextDouble() / speedMult, rand.nextDouble() / speedMult, -rand.nextDouble() / speedMult, red);
        Minecraft.getMinecraft().effectRenderer.addEffect(fireEffect);
        float[] green = {rand.nextFloat(), rand.nextFloat(), rand.nextFloat()};
        BubblingFireParticle fireEffect2 = new BubblingFireParticle(worldIn, dx, dy, dz,
                rand.nextDouble() / speedMult, rand.nextDouble() / speedMult, rand.nextDouble() / speedMult, green);
        Minecraft.getMinecraft().effectRenderer.addEffect(fireEffect2);
        float[] blue = {rand.nextFloat(), rand.nextFloat(), rand.nextFloat()};
        BubblingFireParticle fireEffect3 = new BubblingFireParticle(worldIn, dx, dy, dz,
                -rand.nextDouble() / speedMult, rand.nextDouble() / speedMult, -rand.nextDouble() / speedMult, blue);
        Minecraft.getMinecraft().effectRenderer.addEffect(fireEffect3);
        float[] purple = {rand.nextFloat(), rand.nextFloat(), rand.nextFloat()};
        BubblingFireParticle fireEffect4 = new BubblingFireParticle(worldIn, dx, dy, dz,
                rand.nextDouble() / speedMult, rand.nextDouble() / speedMult, rand.nextDouble() / speedMult, purple);
        Minecraft.getMinecraft().effectRenderer.addEffect(fireEffect4);
    }
}

