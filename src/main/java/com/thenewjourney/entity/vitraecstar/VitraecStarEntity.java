package com.thenewjourney.entity.vitraecstar;

import com.thenewjourney.entity.effectcloud.EffectCloudVitaemEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class VitraecStarEntity extends EntityFireball {
    public VitraecStarEntity(World worldIn) {
        super(worldIn);
        this.setSize(0.3125F, 0.3125F);
    }

    @SideOnly(Side.CLIENT)
    public VitraecStarEntity(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(worldIn, x, y, z, accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
    }

    public VitraecStarEntity(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            List<EntityLivingBase> list = this.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D));
            EffectCloudVitaemEntity entityareaeffectcloud = new EffectCloudVitaemEntity(this.world, this.posX, this.posY, this.posZ);
            entityareaeffectcloud.setOwner(this.shootingEntity);
            entityareaeffectcloud.setParticle(EnumParticleTypes.CRIT);
            entityareaeffectcloud.setRadius(3.0F);
            entityareaeffectcloud.setDuration(800);
            entityareaeffectcloud.setRadiusPerTick((7.0F - entityareaeffectcloud.getRadius()) / (float) entityareaeffectcloud.getDuration());
            entityareaeffectcloud.addEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 1, 2));
            entityareaeffectcloud.addEffect(new PotionEffect(MobEffects.WITHER, 1, 2));

            if (!list.isEmpty()) {
                for (EntityLivingBase entitylivingbase : list) {
                    double d0 = this.getDistanceSq(entitylivingbase);

                    if (d0 < 16.0D) {
                        entityareaeffectcloud.setPosition(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ);
                        break;
                    }
                }
            }

            this.world.playEvent(2006, new BlockPos(this.posX, this.posY, this.posZ), 0);
            this.world.spawnEntity(entityareaeffectcloud);
            this.setDead();
        }
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith() {
        return false;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }

    protected EnumParticleTypes getParticleType() {
        return EnumParticleTypes.CRIT;
    }

    protected boolean isFireballFiery() {
        return false;
    }
}