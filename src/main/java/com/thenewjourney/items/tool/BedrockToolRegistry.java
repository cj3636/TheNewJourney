package com.thenewjourney.items.tool;

import com.cj3636.lib.Ref;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;

public class BedrockToolRegistry extends ItemTool {
			
    private static Set<Block> effectiveBlocks = null;
    private DamageSource source;
    
	public BedrockToolRegistry(String unlocalizedName, int damage, ToolMaterial toolMaterial) {
        super(toolMaterial, effectiveBlocks );
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
        this.setMaxStackSize(1);
        this.setMaxDamage(damage);
        this.setNoRepair();
    }
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItemMainhand();

        if (world.getBlockState(pos).getBlock() == Blocks.BEDROCK) {
    		world.playSound(player, player.getPosition(), SoundEvents.ENTITY_LIGHTNING_IMPACT, SoundCategory.BLOCKS, 50.0F, 50.0F);
    		
    		if (!world.isRemote) {
    			int x = pos.getX();
    			int y = pos.getY();
    			int z = pos.getZ();
                world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
    			stack.damageItem(1, player);
    			EntityItem item = new EntityItem(world, x, y, z, new ItemStack(Blocks.BEDROCK));
    			world.spawnEntity(item);
    		}
    	}
    	world.playSound(player, player.getPosition(), SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.BLOCKS, 50.0F, 0.15F);
    	return super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
    }
    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
    	entityLiving.playSound(SoundEvents.ENTITY_LINGERINGPOTION_THROW, 50.0F, 0.015F);
        return false;
    }
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(4, attacker);
        target.playSound(SoundEvents.ENTITY_LIGHTNING_THUNDER, 50.0F, 50.0F);
        target.attackEntityFrom(DamageSource.GENERIC, 10000F);
        return true;
    }
    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        return 0F;
    }
}
