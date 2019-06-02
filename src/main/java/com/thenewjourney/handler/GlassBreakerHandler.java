package com.thenewjourney.handler;

import com.thenewjourney.items.ModItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GlassBreakerHandler {
    /**
     * @SubscribeEvent public void onBreakSpeed(PlayerEvent.BreakSpeed event)
     * {
     * EntityPlayer player = event.getEntityPlayer();
     * World world = event.getEntityPlayer().worldObj;
     * ItemStack stack = player.getHeldItemMainhand();
     * IBlockState state = event.getState();
     * <p>
     * if(state.getBlock() == Blocks.log || state.getBlock() == Blocks.log2 || state.getBlock().isWood(world, event.getPos()))
     * {
     * if(!isAxe(stack))
     * {
     * if(Config.DISABLE_BREAKING_WOOD)
     * {
     * event.setCanceled(true);
     * }
     * else if(Config.HURT_WHEN_BREAKING_WOOD)
     * {
     * if(stack == null || !(stack.getItem() instanceof ItemTool))
     * {
     * final float amount = 0.1428571428571F; // 1 health / 7 hits per log with no tool
     * player.attackEntityFrom(DamageSource.generic, amount);
     * }
     * }
     * }
     * }
     * }
     * <p>
     * public static boolean isAxe(ItemStack s)
     * {
     * return s != null && s.getItem() != null && s.getItem().getHarvestLevel(s, "axe") >= 0;
     * }
     **/    //@throws InterruptedException
    @SubscribeEvent
    public void onHarvestBlock(BlockEvent.HarvestDropsEvent event) {
        final EntityPlayer PLAYER = event.getHarvester();

        if (null == PLAYER || null == PLAYER.getHeldItemMainhand()) return;

        if (event.getState().getBlock() == Blocks.GLOWSTONE) {
            if (PLAYER.getHeldItemMainhand().getItem() == ModItems.Icepick) {
                event.getDrops().clear();
                event.getDrops().add(new ItemStack(Blocks.GLOWSTONE, 1, 0));
            }
        }
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        final EntityPlayer PLAYER = event.getPlayer();
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        if (event.getState().getBlock() == Blocks.ICE) {
            if (PLAYER.getHeldItemMainhand().getItem() == ModItems.Icepick) {
                event.isCanceled();
                world.setBlockToAir(pos);
                EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.ICE));
                event.getWorld().spawnEntity(item);
            }
        }
    }
}
