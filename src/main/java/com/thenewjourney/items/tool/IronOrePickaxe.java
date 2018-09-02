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

public class IronOrePickaxe extends ItemTool {

    public IronOrePickaxe(String unlocalizedName, ToolMaterial material, int maxDamage) {
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
    		if (block.getBlock() == Blocks.IRON_ORE) {
    			EntityItem item = new EntityItem(world, x, y, z, new ItemStack(Blocks.IRON_ORE));
    			world.spawnEntity(item);
    		}
    		if (block.getBlock() == Blocks.STONE) {
    			EntityItem item = new EntityItem(world, x, y, z, new ItemStack(Blocks.COBBLESTONE));
    			world.spawnEntity(item);
    		}
    	}
    	stack.damageItem(1, player);
        return false;
    }
    private static Set effectiveBlocks = Sets.newHashSet(Blocks.STONE, Blocks.IRON_ORE);
    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        return effectiveBlocks.contains(state) ? this.efficiency : 5.0F;
    }
}
