package com.thenewjourney.entity.grenade;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class GrenadeEntity extends EntityThrowable {

    public GrenadeEntity(World world, EntityLivingBase entity) {
        super(world, entity);
    }

    public GrenadeEntity(World world) {
        super(world);
    }

    public void init() {

    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    public void onImpact(RayTraceResult result) {
        this.world.createExplosion(this, this.posX, this.posY, this.posZ, 5F, true);
        this.setDead();
    }
}
