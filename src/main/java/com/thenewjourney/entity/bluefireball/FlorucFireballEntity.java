package com.thenewjourney.entity.bluefireball;

import com.thenewjourney.entity.effectcloud.EffectCloudFlorusEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class FlorucFireballEntity extends EntityFireball implements IThrowableEntity{
    private Entity thrower;
    public FlorucFireballEntity(World worldIn) {
        super(worldIn);
        this.setSize(0.3125F, 0.3125F);
    }

    @SideOnly(Side.CLIENT)
    public FlorucFireballEntity(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(worldIn, x, y, z, accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
    }

    public FlorucFireballEntity(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            List<EntityLivingBase> list = this.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D));
            EffectCloudFlorusEntity entityareaeffectcloud = new EffectCloudFlorusEntity(this.world, this.posX, this.posY, this.posZ);
            entityareaeffectcloud.setOwner(this.shootingEntity);
            entityareaeffectcloud.setParticle(EnumParticleTypes.CRIT);
            entityareaeffectcloud.setRadius(3.0F);
            entityareaeffectcloud.setDuration(800);
            entityareaeffectcloud.setRadiusPerTick((7.0F - entityareaeffectcloud.getRadius()) / (float) entityareaeffectcloud.getDuration());
            entityareaeffectcloud.addEffect(new PotionEffect(MobEffects.SLOWNESS, 30, 9));

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

    @Override
    public void setThrower(Entity entity) {
        this.thrower = entity;
    }

    @Override
    public Entity getThrower() {
        return thrower;
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