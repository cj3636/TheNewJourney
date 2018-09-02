package com.thenewjourney.blocks.drawer;

import com.cj3636.lib.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DrawerGuiInventory extends GuiContainer {

    // This is the resource location for the background image for the GUI
    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/drawergui.png");
    public DrawerGuiInventory(InventoryPlayer invPlayer, DrawerTileEntity tile) {
        super(new DrawerContainer(invPlayer, tile));
        // Set the width and height of the gui.  Should match the size of the texture!
        xSize = 256;
        ySize = 256;
    }

    // draw the background for the GUI - rendered first
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
        // Bind the image texture of our custom container
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        // Draw the image
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    // draw the foreground for the GUI - rendered after the slots, but before the dragged items and tooltips
    // renders relative to the top left corner of the background
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        //final int LABEL_XPOS = 5;
        //final int LABEL_YPOS = 5;
        //fontRendererObj.drawString("Drawer", LABEL_XPOS, LABEL_YPOS, Color.BLACK.getRGB());
    }
}