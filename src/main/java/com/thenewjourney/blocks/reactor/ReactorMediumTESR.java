package com.thenewjourney.blocks.reactor;

import com.thenewjourney.blocks.forge.RenderingUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.math.BlockPos;

import java.awt.*;
import java.util.Random;

public class ReactorMediumTESR extends TileEntitySpecialRenderer<ReactorMediumTileEntity> {
    private static long startLong = 0L;

    @Override
    public void render(ReactorMediumTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        startLong += 1;
        BlockPos posr = new BlockPos(0, 0, 0);
        float offX = 0.5F;
        float offY = 1.5F;
        float offZ = 0.5F;

        Random random = new Random();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + offX, y + offY, z + offZ);

        Color color1 = new Color(randFloat(), randFloat() / 2f, randFloat() / 2f);
        Color color2 = new Color(randFloat() / 2f, randFloat(), randFloat() / 2f);
        Color color3 = new Color(randFloat() / 2f, randFloat() / 2f, randFloat());

        RenderingUtils.renderLightRayEffects(posr.getX(), posr.getY(), posr.getZ(), color1, 0x12315L, startLong, 20, 20F, 21, 7);
        RenderingUtils.renderLightRayEffects(posr.getX(), posr.getY(), posr.getZ(), color2, 0x13279L, startLong, 20, 20F, 21, 7);
        RenderingUtils.renderLightRayEffects(posr.getX(), posr.getY(), posr.getZ(), color3, 0x36928L, startLong, 20, 20F, 21, 7);

        GlStateManager.popMatrix();
    }

    public static float randFloat() {

        Random rand = new Random();

        float result = rand.nextFloat() * (.8f - .2f) + .2f;

        return result;

    }
}