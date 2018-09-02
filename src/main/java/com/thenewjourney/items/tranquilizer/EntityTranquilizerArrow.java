package com.thenewjourney.items.tranquilizer;

import com.thenewjourney.items.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;

/**
 * Created by cj3636 on 2/2/2017.
 */
public class EntityTranquilizerArrow extends EntityArrow {
    private int duration = 600;

    public EntityTranquilizerArrow(World worldIn) {
        super(worldIn);
    }

    public EntityTranquilizerArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    public EntityTranquilizerArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() {
        super.onUpdate();

        if (this.world.isRemote && !this.inGround) {
            this.world.spawnParticle(EnumParticleTypes.SPELL_INSTANT, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    protected ItemStack getArrowStack() {
        return new ItemStack(ModItems.TranquilizerArrow);
    }

    protected void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);
        PotionEffect potioneffect = new PotionEffect(MobEffects.SLOWNESS, this.duration, 9);
        living.addPotionEffect(potioneffect);
        living.setPosition(living.posX, living.world.getTopSolidOrLiquidBlock(living.getPosition()).getY() + 1, living.posZ);
    }

    public static void registerFixesSpectralArrow(DataFixer fixer) {
        EntityArrow.registerFixesArrow(fixer, "SpectralArrow");
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("Duration")) {
            this.duration = compound.getInteger("Duration");
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("Duration", this.duration);
    }
}