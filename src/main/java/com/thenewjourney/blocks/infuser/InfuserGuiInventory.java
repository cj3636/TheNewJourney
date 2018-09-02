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

import java.awt.*;

@SideOnly(Side.CLIENT)
public class InfuserGuiInventory extends GuiContainer {
	// This is the resource location for the background image
	private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/infuser.png");

	public InfuserGuiInventory(InventoryPlayer invPlayer, BlockPos pos) {
		super(new InfuserContainer(invPlayer, pos, invPlayer.player.getEntityWorld()));

		xSize = 176;
		ySize = 207;

	}
	@Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);

		final int LABEL_XPOS = 5;
		final int LABEL_YPOS = 5;
		fontRenderer.drawString("Archaic Infuser", LABEL_XPOS, LABEL_YPOS, Color.white.getRGB());
//		// You must re bind the texture and reset the colour if you still need to use it after drawing a string
//		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
//		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

	}
}