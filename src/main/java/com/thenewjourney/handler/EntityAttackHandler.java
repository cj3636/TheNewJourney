package com.thenewjourney.handler;

import com.thenewjourney.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityAttackHandler {
    public static void checkArmor(ItemStack stack, Entity attacker) {
        if (attacker != null) {
            if (stack != null) {
                if (stack.getItem() == ModItems.FireHelmet ||
                        stack.getItem() == ModItems.FireChestplate ||
                        stack.getItem() == ModItems.FireLeggings ||
                        stack.getItem() == ModItems.FireBoots) {
                    attacker.setFire(4);
                }
            }
        }
    }

    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event) {
        World world = event.getEntity().getEntityWorld();
        Entity killer = event.getSource().getImmediateSource();

        double x = event.getEntity().getPosition().getX();
        double y = event.getEntity().getPosition().getY();
        double z = event.getEntity().getPosition().getZ();
        if (killer instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getImmediateSource();
            ItemStack diamondstack = player.getHeldItemMainhand();
            if (diamondstack != null && diamondstack.getItem() == Items.DIAMOND && !player.capabilities.isCreativeMode) {
                diamondstack.setCount(diamondstack.getCount() - 1);
                if (diamondstack.getCount() <= 0) {
                    player.inventory.deleteStack(diamondstack);
                }

                EntityItem blooddiamond = new EntityItem(world, x, y, z, new ItemStack(ModItems.BloodGem));
                world.spawnEntity(blooddiamond);
            }
        }
    }

    @SubscribeEvent
    public void onEntityDeath(LivingAttackEvent event) {
        Entity attacker = event.getSource().getImmediateSource();
        Entity attacked = event.getEntity();
        if (attacked instanceof EntityPlayer) {
            Iterable<ItemStack> armor = attacked.getArmorInventoryList();

            for (ItemStack stack : armor) {
                checkArmor(stack, attacker);
            }
        }
    }
}