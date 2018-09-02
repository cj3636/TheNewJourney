package com.thenewjourney.entity.rift;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;


public class RiftRenderer extends Render<RiftEntity> {

    public RiftRenderer(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void doRender(RiftEntity var1, double var2, double var4, double var6, float var8, float var9) {
    }

    @Override
    protected ResourceLocation getEntityTexture(RiftEntity entity) {
        return null;
    }
}
