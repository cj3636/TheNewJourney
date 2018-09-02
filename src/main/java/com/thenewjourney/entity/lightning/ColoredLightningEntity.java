package com.thenewjourney.entity.lightning;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityWeatherEffect;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

public class ColoredLightningEntity extends EntityWeatherEffect {
    /**
     * Declares which state the lightning bolt is in. Whether it's in the air, hit the ground, etc.
     */
    private int lightningState;
    /**
     * A random long that is used to change the vertex of the lightning rendered in RenderLightningBolt
     */
    public long boltVertex;
    /**
     * Determines the time before the EntityLightningBolt is destroyed. It is a random integer decremented over time.
     */
    private int boltLivingTime;
    private final boolean effectOnly;

    public ColoredLightningEntity(World worldIn, double x, double y, double z, boolean effectOnlyIn) {
        super(worldIn);
        this.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
        this.lightningState = 10;
        this.boltVertex = this.rand.nextLong();
        this.boltLivingTime = this.rand.nextInt(7) + 1;
        this.effectOnly = effectOnlyIn;
        this.ignoreFrustumCheck = true;
    }

    public SoundCategory getSoundCategory() {
        return SoundCategory.WEATHER;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() {
        super.onUpdate();

        if (this.lightningState == 10) {
            this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_LIGHTNING_THUNDER, SoundCategory.WEATHER, 10000.0F, 0.8F + this.rand.nextFloat() * 0.2F);
        }

        --this.lightningState;

        if (this.lightningState < 0) {
            if (this.boltLivingTime == 0) {
                this.setDead();
            } else if (this.lightningState < -this.rand.nextInt(10)) {
                --this.boltLivingTime;
                this.lightningState = 1;
            }
        }
        if (!this.effectOnly) {
            List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(this.posX - 3.0D, this.posY - 3.0D, this.posZ - 3.0D, this.posX + 3.0D, this.posY + 6.0D + 3.0D, this.posZ + 3.0D));

            for (int i = 0; i < list.size(); ++i) {
                Entity entity = list.get(i);
                entity.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 8.0F);
                if (!entity.isImmuneToFire())
                    entity.setFire(8);
            }
        }
    }

    @Override
    protected void entityInit() {
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
    }
}
