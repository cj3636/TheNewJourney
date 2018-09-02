package com.thenewjourney.blocks.register;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GrinderRegistry extends Block {

    public static String modid = Ref.MODID;
    private IBlockState getblockState;

    public GrinderRegistry(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);

        this.setCreativeTab(Ref.CTAB);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(hardness);
    }

    public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
        return this == ModBlocks.CrypticBlock && side == EnumFacing.UP;
    }

    @Override
    public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {

        Item shadow = ModItems.ShadowIngot;
        ItemStack hand = player.getHeldItemMainhand();

        if (hand != null && hand.getItem() == shadow) {

            world.playSound(player, pos, SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH, SoundCategory.BLOCKS, 1.0F, 2.0F);
            world.playSound(player, pos, SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, SoundCategory.BLOCKS, 0.1F, 2.0F);

            if (!world.isRemote) {
                world.setBlockToAir(pos);
                removeItem(player, hand);

                EntityItem itemstack = new EntityItem(world, pos.up().getX(), pos.up().getY(), pos.up().getZ(), new ItemStack(ModItems.ShadowEssence));
                world.spawnEntity(itemstack);
            }
        }
    }

    public void removeItem(EntityPlayer player, ItemStack itemstack) {
        if (!player.capabilities.isCreativeMode) {
            itemstack.setCount(itemstack.getCount() - 1);
            if (itemstack.getCount() <= 0) {
                player.inventory.deleteStack(itemstack);
            }
        }
    }
}

/**
 * public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
 * <p>
 * world.setBlockState(pos, Blocks.air.getDefaultState());
 * this.gen(world, RANDOM, pos);
 * System.out.println("delete");
 * return true;
 * }
 * <p>
 * public void gen(World world, Random rand, BlockPos pos) {
 * <p>
 * FireRuin gen = new FireRuin();
 * gen.generate(world, rand, pos);
 * }
 * }
 **/
