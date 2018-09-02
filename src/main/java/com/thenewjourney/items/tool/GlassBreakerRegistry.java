package com.thenewjourney.items.tool;

import com.cj3636.lib.Ref;
import com.google.common.collect.Sets;
import com.thenewjourney.blocks.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;

public class GlassBreakerRegistry extends ItemTool {

    public GlassBreakerRegistry(String unlocalizedName, ToolMaterial material, int maxDamage) {
        super(material, effectiveBlocks);

        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
        this.setMaxDamage(maxDamage);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState block, BlockPos pos, EntityLivingBase player) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        if (!world.isRemote) {
            if (block.getBlock() == Blocks.PACKED_ICE) {
                EntityItem item = new EntityItem(world, x, y, z, new ItemStack(Blocks.PACKED_ICE));
                world.spawnEntity(item);
            }
            if (block.getBlock() == Blocks.GLASS) {
                EntityItem item = new EntityItem(world, x, y, z, new ItemStack(Blocks.GLASS));
                world.spawnEntity(item);
            }
            if (block.getBlock() == Blocks.STAINED_GLASS) {
                EntityItem item = new EntityItem(world, x, y, z, new ItemStack(Blocks.STAINED_GLASS));
                world.spawnEntity(item);
            }
            if (block.getBlock() == Blocks.GLASS_PANE) {
                EntityItem item = new EntityItem(world, x, y, z, new ItemStack(Blocks.GLASS_PANE));
                world.spawnEntity(item);
            }
            if (block.getBlock() == Blocks.STAINED_GLASS_PANE) {
                EntityItem item = new EntityItem(world, x, y, z, new ItemStack(Blocks.STAINED_GLASS_PANE));
                world.spawnEntity(item);
            }
            if (block.getBlock() == ModBlocks.FlorusBlock) {
                EntityItem item = new EntityItem(world, x, y, z, new ItemStack(ModBlocks.FlorusBlock));
                world.spawnEntity(item);
            }
            if (block.getBlock() == ModBlocks.ClearGlass) {
                EntityItem item = new EntityItem(world, x, y, z, new ItemStack(ModBlocks.ClearGlass));
                world.spawnEntity(item);
            }
        }
        stack.damageItem(1, player);
        return false;
    }

    private static Set effectiveBlocks = Sets.newHashSet(Blocks.ICE,
            Blocks.PACKED_ICE,
            Blocks.GLASS,
            Blocks.GLASS_PANE,
            Blocks.STAINED_GLASS,
            Blocks.STAINED_GLASS_PANE,
            Blocks.GLOWSTONE,
            Blocks.REDSTONE_LAMP,
            Blocks.BEACON,
            ModBlocks.FlorusBlock,
            ModBlocks.ClearGlass);

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        return effectiveBlocks.contains(state) ? this.efficiency : 25.0F;
    }
}
