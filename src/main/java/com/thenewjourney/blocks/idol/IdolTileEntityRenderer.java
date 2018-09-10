package com.thenewjourney.blocks.idol;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.pervateki.RenderingUtils;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class IdolTileEntityRenderer extends TileEntitySpecialRenderer<IdolTileEntity> {

    public static final ResourceLocation TEXTURE_BEACON_BEAM = new ResourceLocation(Ref.MODID, "textures/entity/beam.png");
    private static final ResourceLocation gemTexture = new ResourceLocation(Ref.MODID, "textures/blocks/CGL.png");

    public boolean isBeam() {
        return beam;
    }

    public void setBeam(boolean beam) {
        this.beam = beam;
    }

    private boolean beam;

    public static void renderBeamSegment(double x, double y, double z, double partialTicks, double textureScale, double totalWorldTime, int yOffset, int height, float[] colors, double beamRadius, double glowRadius) {
        int i = yOffset + height;
        GlStateManager.glTexParameteri(3553, 10242, 10497);
        GlStateManager.glTexParameteri(3553, 10243, 10497);
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        double d0 = totalWorldTime + partialTicks;
        double d1 = height < 0 ? d0 : -d0;
        double d2 = MathHelper.frac(d1 * 0.2D - (double) MathHelper.floor(d1 * 0.1D));
        float f = colors[0];
        float f1 = colors[1];
        float f2 = colors[2];
        double d3 = d0 * 0.025D * -1.5D;
        double d4 = 0.5D + Math.cos(d3 + 2.356194490192345D) * beamRadius;
        double d5 = 0.5D + Math.sin(d3 + 2.356194490192345D) * beamRadius;
        double d6 = 0.5D + Math.cos(d3 + (Math.PI / 4D)) * beamRadius;
        double d7 = 0.5D + Math.sin(d3 + (Math.PI / 4D)) * beamRadius;
        double d8 = 0.5D + Math.cos(d3 + 3.9269908169872414D) * beamRadius;
        double d9 = 0.5D + Math.sin(d3 + 3.9269908169872414D) * beamRadius;
        double d10 = 0.5D + Math.cos(d3 + 5.497787143782138D) * beamRadius;
        double d11 = 0.5D + Math.sin(d3 + 5.497787143782138D) * beamRadius;
        double d12 = 0.0D;
        double d13 = 1.0D;
        double d14 = -1.0D + d2;
        double d15 = (double) height * textureScale * (0.5D / beamRadius) + d14;

        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        vertexbuffer.pos(x + d4, y + (double) i, z + d5).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
        vertexbuffer.pos(x + d4, y + (double) yOffset, z + d5).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
        vertexbuffer.pos(x + d6, y + (double) yOffset, z + d7).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
        vertexbuffer.pos(x + d6, y + (double) i, z + d7).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
        vertexbuffer.pos(x + d10, y + (double) i, z + d11).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
        vertexbuffer.pos(x + d10, y + (double) yOffset, z + d11).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
        vertexbuffer.pos(x + d8, y + (double) yOffset, z + d9).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
        vertexbuffer.pos(x + d8, y + (double) i, z + d9).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
        vertexbuffer.pos(x + d6, y + (double) i, z + d7).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
        vertexbuffer.pos(x + d6, y + (double) yOffset, z + d7).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
        vertexbuffer.pos(x + d10, y + (double) yOffset, z + d11).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
        vertexbuffer.pos(x + d10, y + (double) i, z + d11).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
        vertexbuffer.pos(x + d8, y + (double) i, z + d9).tex(1.0D, d15).color(f, f1, f2, 1.0F).endVertex();
        vertexbuffer.pos(x + d8, y + (double) yOffset, z + d9).tex(1.0D, d14).color(f, f1, f2, 1.0F).endVertex();
        vertexbuffer.pos(x + d4, y + (double) yOffset, z + d5).tex(0.0D, d14).color(f, f1, f2, 1.0F).endVertex();
        vertexbuffer.pos(x + d4, y + (double) i, z + d5).tex(0.0D, d15).color(f, f1, f2, 1.0F).endVertex();
        tessellator.draw();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.depthMask(false);
        d3 = 0.5D - glowRadius;
        d4 = 0.5D - glowRadius;
        d5 = 0.5D + glowRadius;
        d6 = 0.5D - glowRadius;
        d7 = 0.5D - glowRadius;
        d8 = 0.5D + glowRadius;
        d9 = 0.5D + glowRadius;
        d10 = 0.5D + glowRadius;
        d11 = 0.0D;
        d12 = 1.0D;
        d13 = -1.0D + d2;
        d14 = (double) height * textureScale + d13;
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        vertexbuffer.pos(x + d3, y + (double) i, z + d4).tex(1.0D, d14).color(f, f1, f2, 0.125F).endVertex();
        vertexbuffer.pos(x + d3, y + (double) yOffset, z + d4).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
        vertexbuffer.pos(x + d5, y + (double) yOffset, z + d6).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
        vertexbuffer.pos(x + d5, y + (double) i, z + d6).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
        vertexbuffer.pos(x + d9, y + (double) i, z + d10).tex(1.0D, d14).color(f, f1, f2, 0.125F).endVertex();
        vertexbuffer.pos(x + d9, y + (double) yOffset, z + d10).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
        vertexbuffer.pos(x + d7, y + (double) yOffset, z + d8).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
        vertexbuffer.pos(x + d7, y + (double) i, z + d8).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
        vertexbuffer.pos(x + d5, y + (double) i, z + d6).tex(1.0D, d14).color(f, f1, f2, 0.125F).endVertex();
        vertexbuffer.pos(x + d5, y + (double) yOffset, z + d6).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
        vertexbuffer.pos(x + d9, y + (double) yOffset, z + d10).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
        vertexbuffer.pos(x + d9, y + (double) i, z + d10).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
        vertexbuffer.pos(x + d7, y + (double) i, z + d8).tex(1.0D, d14).color(f, f1, f2, 0.125F).endVertex();
        vertexbuffer.pos(x + d7, y + (double) yOffset, z + d8).tex(1.0D, d13).color(f, f1, f2, 0.125F).endVertex();
        vertexbuffer.pos(x + d3, y + (double) yOffset, z + d4).tex(0.0D, d13).color(f, f1, f2, 0.125F).endVertex();
        vertexbuffer.pos(x + d3, y + (double) i, z + d4).tex(0.0D, d14).color(f, f1, f2, 0.125F).endVertex();
        tessellator.draw();
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.depthMask(true);
    }

    private static long startLong = 0L;

    @Override
    public void render(IdolTileEntity tileEntity, double relativeX, double relativeY, double relativeZ, float partialTicks, int blockDamageProgress, float alpha) {
        IdolTileEntity ModTileEntityCrystal = (IdolTileEntity) tileEntity;
        if (tileEntity.getFullActive()) {
            if (tileEntity.getWorld().getBlockState(tileEntity.getPos().up()).equals(Blocks.EMERALD_BLOCK.getDefaultState())) {
                BlockPos posg = new BlockPos(0, 0, 0);
                startLong += 1;
                float offX = 0.5F;
                float offY = 0.5F;
                float offZ = 0.5F;
                GlStateManager.pushMatrix();
                GlStateManager.translate(relativeX + offX, relativeY + offY, relativeZ + offZ);
                RenderingUtils.renderLightRayEffects(posg.getX(), posg.up().getY(), posg.getZ(), Color.GREEN, 0x12315L, startLong, 20, 2F, 21, 7);
                GlStateManager.popMatrix();
            }
            float[] colors = {1, 2, 18};
            this.bindTexture(TEXTURE_BEACON_BEAM);
            GlStateManager.disableFog();
            this.renderBeamSegment(relativeX, relativeY, relativeZ, (double) partialTicks, 1.0D, (double) tileEntity.getWorld().getTotalWorldTime(), 0, 256, colors, 0.25D, 0.3D);
            GlStateManager.enableFog();

        }


        final double pedestalCentreOffsetX = 0.5;
        final double pedestalCentreOffsetY = 0.0;
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

            final double GEM_HEIGHT = UsefulFunctions.interpolate(playerDistance, 9, 1, 0.75, 1.75);        // desired render height of the gem

            final double MODEL_HEIGHT = 2;      // actual height of the gem in the vertexTable
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
            if (fullBrightnessColor != IdolTileEntity.INVALID_COLOR) {
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

    @Override
    public boolean isGlobalRenderer(IdolTileEntity te) {
        return true;
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
