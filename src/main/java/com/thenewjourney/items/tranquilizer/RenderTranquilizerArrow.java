package com.thenewjourney.items.tranquilizer;

import com.cj3636.lib.Ref;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by cj3636 on 2/2/2017.
 */
@SideOnly(Side.CLIENT)
public class RenderTranquilizerArrow extends RenderArrow<EntityTranquilizerArrow>
{
    public static final ResourceLocation RES_SPECTRAL_ARROW = new ResourceLocation(Ref.MODID, "textures/entity/TranquilizerArrow.png");

    public RenderTranquilizerArrow(RenderManager manager)
    {
        super(manager);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityTranquilizerArrow entity)
    {
        return RES_SPECTRAL_ARROW;
    }
}