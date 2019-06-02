package com.thenewjourney.handler;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.crystal.CrystalTileEntity;
import com.thenewjourney.capability.block.BlockPowerProvider;
import com.thenewjourney.capability.block.IPowerBlockCapability;
import com.thenewjourney.capability.owner.OwnerProvider;
import com.thenewjourney.capability.tier.IPowerTierCapability;
import com.thenewjourney.capability.tier.TierPowerProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler {
    public static final ResourceLocation BLOCK_CAP = new ResourceLocation(Ref.MODID, "powerNum");
    public static final ResourceLocation TIER_CAP = new ResourceLocation(Ref.MODID, "tierNum");
    public static final ResourceLocation OWNER_CAP = new ResourceLocation(Ref.MODID, "owner");

    @SubscribeEvent
    public void attachCapabilityPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (!(event.getObject() instanceof EntityPlayer)) {
            return;
        }
        event.addCapability(BLOCK_CAP, new BlockPowerProvider());
        event.addCapability(TIER_CAP, new TierPowerProvider());
    }

    @SubscribeEvent
    public void attachCapabilityBlock(AttachCapabilitiesEvent<TileEntity> event) {
        if (!(event.getObject() instanceof CrystalTileEntity)) {
            return;
        }
        event.addCapability(OWNER_CAP, new OwnerProvider());
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        EntityPlayer player = event.getEntityPlayer();

        IPowerBlockCapability block = player.getCapability(BlockPowerProvider.BLOCK_CAP, null);
        IPowerBlockCapability oldBlock = event.getOriginal().getCapability(BlockPowerProvider.BLOCK_CAP, null);
        block.setBlock(oldBlock.getBlock());

        IPowerTierCapability tier = player.getCapability(TierPowerProvider.TIER_CAP, null);
        IPowerTierCapability oldTier = event.getOriginal().getCapability(TierPowerProvider.TIER_CAP, null);
        tier.setTier(oldTier.getTier());
    }
}
