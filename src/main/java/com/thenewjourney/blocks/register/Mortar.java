package com.thenewjourney.blocks.register;

import com.cj3636.lib.Ref;
import com.thenewjourney.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class Mortar extends Block {

    private int berryNum = 0;

    public Mortar(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);

        this.setCreativeTab(Ref.CTAB);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = playerIn.getHeldItemMainhand();
        if (heldItem != null && playerIn.getHeldItemOffhand() != null) {
            if (heldItem.getItem().equals(ModItems.Pestal) && playerIn.getHeldItemOffhand().getItem().equals(ModItems.NarcoBerry)) {
                if (!playerIn.capabilities.isCreativeMode) {
                    heldItem.damageItem(1, playerIn);
                    heldItem.setCount(heldItem.getCount() - 1);
                }
                Random random = new Random();
                worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.ITEM_FLINTANDSTEEL_USE,
                        SoundCategory.AMBIENT, 100.0F, random.nextFloat());
                if (!worldIn.isRemote) {
                    this.berryNum += 1;
                    if (berryNum >= 16) {
                        berryNum = 0;
                        EntityItem narcoticItem = new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(ModItems.Narcotic));
                        worldIn.spawnEntity(narcoticItem);
                        worldIn.playSound(null, playerIn.getPosition(), SoundEvents.ITEM_BOTTLE_FILL,
                                SoundCategory.AMBIENT, 100.0F, 0.75F);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.3125D, 0.0D, 0.3125, 0.6875D, 0.1875D, 0.6875);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return true;
    }
}