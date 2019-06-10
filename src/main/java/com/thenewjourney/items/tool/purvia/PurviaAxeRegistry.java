package com.thenewjourney.items.tool.purvia;

import com.cj3636.lib.Ref;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Set;

public class PurviaAxeRegistry extends ItemTool {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE);

    public PurviaAxeRegistry(String unlocalizedName, ToolMaterial material, float damage, float speed) {
        super(material, EFFECTIVE_ON);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
        this.attackDamage = damage;
        this.attackSpeed = speed;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        Material material = state.getMaterial();
        return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getDestroySpeed(stack, state) : this.efficiency;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        final IBlockState blockAt = worldIn.getBlockState(pos);
        for (ItemStack s : OreDictionary.getOres("logWood")) {
            if (worldIn.getBlockState(pos).getBlock().equals(Block.getBlockFromItem(s.getItem()))) {
                mineTree(worldIn, pos, playerIn, blockAt);
                worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS, 100.0F, 0.7F, true);
            }
        }
        return EnumActionResult.PASS;
    }

    private void mineTree(World worldIn, BlockPos pos, EntityPlayer playerIn, IBlockState blockAt) {
        BlockPos start = pos.add(-4, -1, -4);
        for (int i = 0; i < 64; i++) {
            mineTreeColumn(worldIn, blockAt, playerIn, new BlockPos(start.getX() + (i % 8), start.getY(), start.getZ() + (i / 8)));
        }
    }

    private void mineTreeColumn(World worldIn, IBlockState blockAt, EntityPlayer playerIn, BlockPos blockPos) {
        for (int i = 0; i < 64; i++) {
            BlockPos posAt = blockPos.up(i);
            IBlockState blockCheck = worldIn.getBlockState(posAt);
            if (blockAt.equals(blockCheck)) {
                worldIn.setBlockState(posAt, Blocks.AIR.getDefaultState(), 1);
                if (!worldIn.isRemote) {
                    EntityItem itemDrop = new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(Item.getItemFromBlock(blockAt.getBlock()), 1, blockAt.getBlock().getMetaFromState(blockAt)));
                    worldIn.spawnEntity(itemDrop);
                }
            }
        }
    }
}
