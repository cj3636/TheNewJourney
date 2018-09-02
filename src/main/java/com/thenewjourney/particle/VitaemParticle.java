package com.thenewjourney.particle;

import com.cj3636.lib.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class VitaemParticle extends Particle {
    private final float oSize;
    private boolean hasHitGround;
    private ResourceLocation texture = new ResourceLocation(Ref.MODID, "particle/VitaemParticle");

    public VitaemParticle(World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
        this.motionX = xSpeed;
        this.motionY = ySpeed;
        this.motionZ = zSpeed;
        this.particleScale *= 0.75F;
        this.oSize = this.particleScale;
        this.particleMaxAge = (int) (20.0D / ((double) this.rand.nextFloat() * 0.8D + 0.2D));
        this.hasHitGround = false;
        TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(texture.toString());
        this.setParticleTexture(sprite);
    }

    @Override
    public int getFXLayer() {
        return 1;
    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge) {
            this.setExpired();
        } else {
            //this.setParticleTextureIndex(3 * this.particleAge / this.particleMaxAge + 5);

            if (this.onGround) {
                this.motionY = 0.0D;
                this.hasHitGround = true;
            }

            if (this.hasHitGround) {
                this.motionY += 0.002D;
            }

            this.move(this.motionX, this.motionY, this.motionZ);

            if (this.posY == this.prevPosY) {
                this.motionX *= 1.1D;
                this.motionZ *= 1.1D;
            }

            this.motionX *= 0.9599999785423279D;
            this.motionZ *= 0.9599999785423279D;

            if (this.hasHitGround) {
                this.motionY *= 0.9599999785423279D;
            }
        }
    }

    /**
     * Renders the particle
     */
    public void renderParticle(BufferBuilder worldRendererIn, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        this.particleScale = this.oSize * MathHelper.clamp(((float) this.particleAge + partialTicks) / (float) this.particleMaxAge * 32.0F, 0.0F, 1.0F);
        super.renderParticle(worldRendererIn, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }

    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory {
        public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
            return new VitaemParticle(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        }
    }
}
