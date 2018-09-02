package com.thenewjourney.entity.effectcloud;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EffectCloudVitaemRenderer extends Render<EffectCloudVitaemEntity> {
    public EffectCloudVitaemRenderer(RenderManager manager) {
        super(manager);
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EffectCloudVitaemEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EffectCloudVitaemEntity entity) {
        return null;
    }
}