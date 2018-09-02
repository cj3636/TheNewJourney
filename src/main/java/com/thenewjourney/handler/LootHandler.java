package com.thenewjourney.handler;

import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.compat.baubles.InitItems;
import com.thenewjourney.items.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class LootHandler {
	
    @SubscribeEvent
    public static void onLootLoad(LootTableLoadEvent event) {
        if(event.getName().equals(LootTableList.CHESTS_VILLAGE_BLACKSMITH)) {
            LootPool pool = event.getTable().getPool("main");
            addLoot(pool, ModItems.AncientIngot, 1);
            addLoot(pool, ModItems.BronzeIngot, 3);
            addLoot(pool, ModItems.CopperAxe, 2);
            addLoot(pool, ModItems.CopperPickaxe, 1);
            addLoot(pool, ModItems.CopperShovel, 3);
            addLoot(pool, ModItems.NarcoBerrySeed, 3);
        }
        if(event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON)) {
            LootPool pool = event.getTable().getPool("main");
            addLoot(pool, ModItems.AncientIngot, 1);
            addLoot(pool, ModItems.BronzeIngot, 3);
            addLoot(pool, ModItems.NarcoBerrySeed, 5);
        }
        if(event.getName().equals(LootTableList.CHESTS_JUNGLE_TEMPLE)) {
            LootPool pool = event.getTable().getPool("main");
            addLoot(pool, ModItems.AncientIngot, 5);
            if (Loader.isModLoaded("Baubles")) {
            	addLoot(pool, InitItems.FlyRing, 1);
                addLoot(pool, ModItems.NarcoBerrySeed, 6);
            }
        }
        if(event.getName().equals(LootTableList.CHESTS_DESERT_PYRAMID)) {
            LootPool pool = event.getTable().getPool("main");
            addLoot(pool, ModItems.AncientIngot, 5);
            if (Loader.isModLoaded("Baubles")) {
            	addLoot(pool, InitItems.FlyRing, 3);
            }
        }
        if(event.getName().equals(LootTableList.CHESTS_ABANDONED_MINESHAFT)) {
            LootPool pool = event.getTable().getPool("main");
            addLoot(pool, new ItemStack(ModBlocks.StoneMan).getItem(), 5);
        }
        if(event.getName().equals(LootTableList.CHESTS_IGLOO_CHEST)) {
            LootPool pool = event.getTable().getPool("main");
            addLoot(pool, ModItems.AncientIngot, 2);
            addLoot(pool, ModItems.InversionSerumBottle, 4);
            addLoot(pool, ModItems.NarcoBerrySeed, 5);
        }
        if(event.getName().equals(LootTableList.CHESTS_NETHER_BRIDGE)) {
            LootPool pool = event.getTable().getPool("main");
            addLoot(pool, ModItems.DistortionGem, 1);
            addLoot(pool, ModItems.InversionSerumBottle, 1);
            addLoot(pool, ModItems.EtherealClock, 1);
        }
    }
    private static void addLoot(LootPool pool, Item item, int weight) {
        pool.addEntry(new LootEntryItem(item, weight, 0, new LootFunction[0], new LootCondition[0], item.getRegistryName().toString()));
    }
}
