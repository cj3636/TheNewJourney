package com.thenewjourney.blocks.infuser;

import com.cj3636.lib.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class InfuserGuiInventory extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/infuser.png");

    public InfuserGuiInventory(InventoryPlayer invPlayer, BlockPos pos) {
        super(new InfuserContainer(invPlayer, invPlayer.player.getEntityWorld(), pos));
        xSize = 176;
        ySize = 207;
    }

    public void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
        GL11.glPushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        int k = width / 2 - xSize / 2;
        int l = height / 2 - ySize / 2;
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
        GL11.glPopMatrix();
    }

    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        final int LABEL_XPOS = 5;
        final int LABEL_YPOS = 5;
        fontRenderer.drawString("Archaic Infuser", LABEL_XPOS, LABEL_YPOS, Color.white.getRGB());
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
}