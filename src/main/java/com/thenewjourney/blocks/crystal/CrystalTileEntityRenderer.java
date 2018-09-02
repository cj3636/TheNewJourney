package com.thenewjourney.blocks.crystal;

import com.cj3636.lib.Ref;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class CrystalTileEntityRenderer extends TileEntitySpecialRenderer {

    public CrystalTileEntityRenderer() {
    }

    private static final ResourceLocation gemTexture = new ResourceLocation(Ref.MODID, "textures/blocks/CGL.png");

    @Override
    public void render(TileEntity tileEntity, double relativeX, double relativeY, double relativeZ, float partialTicks, int blockDamageProgress, float alpha) {
        if (!(tileEntity instanceof CrystalTileEntity)) return; // should never happen
        CrystalTileEntity ModTileEntityCrystal = (CrystalTileEntity) tileEntity;

        final double pedestalCentreOffsetX = 0.5;
        final double pedestalCentreOffsetY = 1.0;
        final double pedestalCentreOffsetZ = 0.5;

        Vec3d playerEye = new Vec3d(0.0, 0.0, 0.0);
        Vec3d pedestalCentre = new Vec3d(relativeX + pedestalCentreOffsetX, relativeY + pedestalCentreOffsetY, relativeZ + pedestalCentreOffsetZ);
        double playerDistance = playerEye.distanceTo(pedestalCentre);

        final double DISTANCE_FOR_MIN_SPIN = 8.0;
        final double DISTANCE_FOR_MAX_SPIN = 4.0;

        final double DISTANCE_FOR_MIN_GLOW = 12.0;
        final double DISTANCE_FOR_MAX_GLOW = 10.0;

        final double DISTANCE_FOR_MIN_LEVITATE = 8.0;
        final double DISTANCE_FOR_MAX_LEVITATE = 2.0;

        final double MIN_LEVITATE_HEIGHT = 0.0;
        final double MAX_LEVITATE_HEIGHT = 0.0;

        final double MIN_GLOW = 5.0;
        final double MAX_GLOW = 5.0;

        final double MIN_REV_PER_SEC = 0.01;
        final double MAX_REV_PER_SEC = 1.0;

        double gemCentreOffsetX = pedestalCentreOffsetX;
        double gemCentreOffsetY = pedestalCentreOffsetY;
        double gemCentreOffsetZ = pedestalCentreOffsetZ;

        double glowMultiplier = UsefulFunctions.interpolate(playerDistance, DISTANCE_FOR_MIN_GLOW, DISTANCE_FOR_MAX_GLOW, MIN_GLOW, MAX_GLOW);


        double revsPerSecond = UsefulFunctions.interpolate(playerDistance, 10, 0, MIN_REV_PER_SEC, MAX_REV_PER_SEC);
        double angularPositionInDegrees = ModTileEntityCrystal.getNextAngularPosition(revsPerSecond);

        try {
            // save the transformation matrix and the rendering attributes, so that we can restore them after rendering.  This
            //   prevents us disrupting any vanilla TESR that render after ours.
            //  using try..finally is not essential but helps make it more robust in case of exceptions
            // For further information on rendering using the Tessellator, see http://greyminecraftcoder.blogspot.co.at/2014/12/the-tessellator-and-worldrenderer-18.html
            GL11.glPushMatrix();
            GL11.glPushAttrib(GL11.GL_ENABLE_BIT);

            // First we need to set up the translation so that we render our gem with the bottom point at 0,0,0
            // when the renderTileEntityAt method is called, the tessellator is set up so that drawing a dot at [0,0,0] corresponds to the player's eyes
            // This means that, in order to draw a dot at the TileEntity [x,y,z], we need to translate the reference frame by the difference between the
            // two points, i.e. by the [relativeX, relativeY, relativeZ] passed to the method.  If you then draw a cube from [0,0,0] to [1,1,1], it will
            // render exactly over the top of the TileEntity's block.
            // In this example, the zero point of our model needs to be in the middle of the block, not at the [x,y,z] of the block, so we need to
            // add an extra offset as well, i.e. [gemCentreOffsetX, gemCentreOffsetY, gemCentreOffsetZ]
            GlStateManager.translate(relativeX + gemCentreOffsetX, relativeY + gemCentreOffsetY, relativeZ + gemCentreOffsetZ);

            GlStateManager.rotate((float) angularPositionInDegrees, 0, 1, 0);   // rotate around the vertical axis

            final double GEM_HEIGHT = 2.0;        // desired render height of the gem
            final double MODEL_HEIGHT = 2.0;      // actual height of the gem in the vertexTable
            final double SCALE_FACTOR = GEM_HEIGHT / MODEL_HEIGHT;
            GlStateManager.scale(SCALE_FACTOR, SCALE_FACTOR, SCALE_FACTOR);

            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder worldrenderer = tessellator.getBuffer();
            this.bindTexture(gemTexture);         // texture for the gem appearance

            // set the key rendering flags appropriately...
            GL11.glDisable(GL11.GL_LIGHTING);     // turn off "item" lighting (face brightness depends on which direction it is facing)
            GL11.glDisable(GL11.GL_BLEND);        // turn off "alpha" transparency blending
            GL11.glDepthMask(true);               // gem is hidden behind other objects

            // set the rendering colour as the gem base colour
            Color fullBrightnessColor = ModTileEntityCrystal.getGemColour();
            float red = 0, green = 0, blue = 0;
            if (fullBrightnessColor != CrystalTileEntity.INVALID_COLOR) {
                red = (float) (fullBrightnessColor.getRed() / 255.0);
                green = (float) (fullBrightnessColor.getGreen() / 255.0);
                blue = (float) (fullBrightnessColor.getBlue() / 255.0);
            }
            GlStateManager.color(red, green, blue);     // change the rendering colour

            // change the "multitexturing" lighting value (default value is the brightness of the tile entity's block)
            // - this will make the gem "glow" brighter than the surroundings if it is dark.
            final int SKY_LIGHT_VALUE = (int) (15 * glowMultiplier);
            final int BLOCK_LIGHT_VALUE = 1;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, SKY_LIGHT_VALUE * 16.0F, BLOCK_LIGHT_VALUE * 16.0F);

            worldrenderer.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION_TEX);

            addGemVertices(worldrenderer);
            tessellator.draw();

        } finally {
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
    }

    //BSSSSSSSSSSSSSSSSSSSSS
    private void addGemVertices(BufferBuilder worldrenderer) {
        final double[][] vertexTable = {
                {0.000, 1.000, 0.000, 0.000, 0.118},          //1
                {-0.354, 0.500, -0.354, 0.000, 0.354},
                {-0.354, 0.500, 0.354, 0.236, 0.236},
                {-0.354, 0.500, 0.354, 0.236, 0.236},         //2
                {-0.354, 0.500, -0.354, 0.000, 0.354},
                {0.000, 0.000, 0.000, 0.236, 0.471},
                {-0.354, 0.500, 0.354, 0.236, 0.236},         //3
                {0.000, 0.000, 0.000, 0.236, 0.471},
                {0.354, 0.500, 0.354, 0.471, 0.354},
                {-0.354, 0.500, 0.354, 0.236, 0.236},         //4
                {0.354, 0.500, 0.354, 0.471, 0.354},
                {0.000, 1.000, 0.000, 0.471, 0.118},
                {0.000, 1.000, 0.000, 0.471, 0.118},          //5
                {0.354, 0.500, 0.354, 0.471, 0.354},
                {0.354, 0.500, -0.354, 0.707, 0.236},
                {0.354, 0.500, -0.354, 0.707, 0.236},         //6
                {0.354, 0.500, 0.354, 0.471, 0.354},
                {0.000, 0.000, 0.000, 0.707, 0.471},
                {0.354, 0.500, -0.354, 0.707, 0.236},         //7
                {0.000, 0.000, 0.000, 0.707, 0.471},
                {-0.354, 0.500, -0.354, 0.943, 0.354},
                {0.000, 1.000, 0.000, 0.943, 0.118},          //8
                {0.354, 0.500, -0.354, 0.707, 0.236},
                {-0.354, 0.500, -0.354, 0.943, 0.354}
        };
        for (double[] vertex : vertexTable) {
            worldrenderer.pos(vertex[0], vertex[1], vertex[2])
                    .tex(vertex[3], vertex[4])
                    .endVertex();
        }
    }

    public static class UsefulFunctions {
        public static double interpolate(double x, double x1, double x2, double y1, double y2) {
            if (x1 > x2) {
                double temp = x1;
                x1 = x2;
                x2 = temp;
                temp = y1;
                y1 = y2;
                y2 = temp;
            }

            if (x <= x1) return y1;
            if (x >= x2) return y2;
            double xFraction = (x - x1) / (x2 - x1);
            return y1 + xFraction * (y2 - y1);
        }

        public static Vec3d scalarMultiply(Vec3d source, double multiplier) {
            return new Vec3d(source.x * multiplier, source.y * multiplier, source.z * multiplier);
        }


    }
}
