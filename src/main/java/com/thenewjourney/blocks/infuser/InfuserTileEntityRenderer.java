package com.thenewjourney.blocks.infuser;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class InfuserTileEntityRenderer extends TileEntitySpecialRenderer {
    //The model of your block
    private final InfuserModel model;
    private final ResourceLocation textureLocation = new ResourceLocation("thenewjourney", "textures/blocks/ArchaicInfuser.png"); //Has to be located in the same
    //folder as the other texture, but this time the texture for the model and with ".png"

    public InfuserTileEntityRenderer() {
        this.model = new InfuserModel();
    }

    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
        // int meta = world.getBlockMetadata(x, y, z);
        GL11.glPushMatrix();
        //GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
        GL11.glPopMatrix();
    }

    @Override
    public void render(TileEntity tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        //The PushMatrix tells the renderer to "start" doing something.
        GL11.glPushMatrix();
        //This is setting the initial location.
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

        bindTexture(textureLocation);
        //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

        int rotation = 0;
        switch (tileEntity.getBlockMetadata() % 4) {
            case 0:
                rotation = 270;
                break;
            case 1:
                rotation = 0;
                break;
            case 2:
                rotation = 90;
                break;
            case 3:
                rotation = 180;
                break;
        }
        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);


        //A reference to your Model file. Again, very important.
        this.model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        //Tell it to stop rendering for both the PushMatrix's
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    //Set the lighting stuff, so it changes it's brightness properly.      
    private void adjustLightFixture(World world, int i, int j, int k, Block block) {
        Tessellator tess = Tessellator.getInstance();

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 16F, 16F);
    }

}
