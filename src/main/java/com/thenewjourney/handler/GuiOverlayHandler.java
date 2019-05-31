package com.thenewjourney.handler;

import com.thenewjourney.items.tool.ModSwordRegistry;
import com.thenewjourney.items.weapon.AquisStaff;
import com.thenewjourney.items.weapon.ZirconiumStaff;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiOverlayHandler {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void eventHandler(RenderGameOverlayEvent event) {

        if (!event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {

            int posX = (event.getResolution().getScaledWidth());
            int posY = (event.getResolution().getScaledHeight());

            EntityPlayer player = Minecraft.getMinecraft().player;

            if (true) {
                Minecraft.getMinecraft().fontRenderer.drawString(getHeldDurability(player, posX, posY), posX - 25, posY - 10, getHeldColor(player));
            }
        }
    }

    private String getHeldDurability(EntityPlayer player, int posX, int posY) {
        if (player.getHeldItemMainhand().equals(ItemStack.EMPTY)) {
            return "";
        }
        if (player.getHeldItemMainhand().getItem() instanceof ZirconiumStaff || player.getHeldItemMainhand().getItem() instanceof AquisStaff) {
            int numberBlock = player.hurtResistantTime;
            String durabilitytext = "" + numberBlock;
            int durabilityItem = player.getHeldItemMainhand().getMaxDamage();
            durabilityItem -= player.getHeldItemMainhand().getItemDamage();
            Minecraft.getMinecraft().fontRenderer.drawString(Integer.toString(durabilityItem), posX - 25, posY - 20, 0xff0000);
            return durabilitytext;
        }
        if (player.getHeldItemMainhand().getItem() instanceof ItemTool) {
            int durabilityItem = player.getHeldItemMainhand().getMaxDamage();
            durabilityItem -= player.getHeldItemMainhand().getItemDamage();
            String durabilitytext = "" + durabilityItem;
            return durabilitytext;
        }
        if (player.getHeldItemMainhand().getItem() instanceof ItemHoe) {
            int durabilityItem = player.getHeldItemMainhand().getMaxDamage();
            durabilityItem -= player.getHeldItemMainhand().getItemDamage();
            String durabilitytext = "" + durabilityItem;
            return durabilitytext;
        }
        if (player.getHeldItemMainhand().getItem() instanceof ItemSword) {
            int durabilityItem = player.getHeldItemMainhand().getMaxDamage();
            durabilityItem -= player.getHeldItemMainhand().getItemDamage();
            String durabilitytext = "" + durabilityItem;
            return durabilitytext;
        }
        if (player.getHeldItemMainhand().getItem() instanceof ItemBlock) {
            int numberBlock = player.getHeldItemMainhand().getCount();
            String durabilitytext = "" + numberBlock;
            return durabilitytext;
        }
        if (player.getHeldItemMainhand().getItem() instanceof Item) {
            int numberBlock = player.getHeldItemMainhand().getCount();
            String durabilitytext = "" + numberBlock;
            return durabilitytext;
        }
        if (player.getHeldItemMainhand().getMaxDamage() > 0) {
            int numberBlock = player.getHeldItemMainhand().getCount();
            String durabilitytext = "" + numberBlock;
            return durabilitytext;
        }
        return "";
    }

    private int getHeldColor(EntityPlayer player) {
        if (player.getHeldItemMainhand().getItem() instanceof ItemSword) {
            return 0xff0000;
        }
        if (player.getHeldItemMainhand().getItem() instanceof ModSwordRegistry) {
            return 0xff0000;
        }
        if (player.getHeldItemMainhand().getItem() instanceof ItemTool) {
            return 0x0000cc;
        }
        if (player.getHeldItemMainhand().getItem() instanceof ItemHoe) {
            return 0x0000cc;
        }
        if (player.getHeldItemMainhand().getItem() instanceof ItemBlock) {
            return 0x696969;
        }
        if (player.getHeldItemMainhand().getItem() instanceof Item) {
            return 0x00ff00;
        }
        return 0x000000;
    }
}
	
