package com.thenewjourney.handler;

import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.capability.block.BlockPowerProvider;
import com.thenewjourney.capability.block.IPowerBlockCapability;
import com.thenewjourney.capability.owner.IOwnerCapability;
import com.thenewjourney.capability.owner.OwnerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

public class BlockCapabilityHandler {

    @SubscribeEvent
    public void onBlockBroken(BlockEvent.BreakEvent event) {
        if (event.getWorld().isRemote) return;
        if (event.getState().getBlock().equals(ModBlocks.Crystal)) {
            if (event.getPlayer() != null) {
                IPowerBlockCapability blockNum = event.getPlayer().getCapability(BlockPowerProvider.BLOCK_CAP, null);
                IOwnerCapability owner = event.getWorld().getTileEntity(event.getPos()).getCapability(OwnerProvider.OWNER_CAP, null);
                blockNum.removeBlock(1);
                owner.removeOwner();
            } else {
                IOwnerCapability owner = event.getWorld().getTileEntity(event.getPos()).getCapability(OwnerProvider.OWNER_CAP, null);
                if (owner != null) {
                    EntityPlayer blockOwner = event.getWorld().getPlayerEntityByUUID(UUID.fromString(owner.getOwner()));
                    IPowerBlockCapability blockNum = blockOwner.getCapability(BlockPowerProvider.BLOCK_CAP, null);
                    blockNum.removeBlock(1);
                } else {
                    //You fucking hacker.
                    //Or maybe I am just a terrible coder
                    String s = "\u001B[35m" + "[The New Journey] I apologize if I just crashed your server. But there one exceptional hacker playing on it. " +
                            "If this is single player, you now have 3d anaglyph on and are either the worlds best hacker or you successfully BROKE my mod." +
                            "In either case, please hit me up! CJ3636 on reddit: https://www.reddit.com/user/cj3636" + "\u001B[0m";
                    System.out.println(s);
                    Minecraft.getMinecraft().gameSettings.anaglyph = true;
                }
            }
        }
    }

    @SubscribeEvent
    public void onBlockPlaced(BlockEvent.PlaceEvent event) {
        if (event.getWorld().isRemote) return;
        if (event.getState().getBlock().equals(ModBlocks.Crystal)) {
            if (event.getPlayer() != null) {
                IPowerBlockCapability blockNum = event.getPlayer().getCapability(BlockPowerProvider.BLOCK_CAP, null);
                IOwnerCapability owner = event.getWorld().getTileEntity(event.getPos()).getCapability(OwnerProvider.OWNER_CAP, null);
                blockNum.addBlock(1);
                owner.setOwner(event.getPlayer().getUniqueID().toString());
            } else {
                event.setCanceled(true);
            }
        }
    }
}
