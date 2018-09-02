package com.thenewjourney.entity.king;

import com.thenewjourney.items.ModItems;
import com.thenewjourney.particle.FireParticle;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KingEntity extends EntityPigZombie implements IBreathAttacker {
    private static final DataParameter<Boolean> ARMS_RAISED = EntityDataManager.createKey(KingEntity.class, DataSerializers.BOOLEAN);
    private final BossInfoServer bossInfo = (BossInfoServer) (new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
    private static final DataParameter<Boolean> BREATH_FLAG = EntityDataManager.createKey(KingEntity.class, DataSerializers.BOOLEAN);

    public KingEntity(World worldIn) {
        super(worldIn);
        this.setSize(1.0F, 4.0F);
        this.setRotation(1.57F, 1.57F);
    }

    @Override
    public float getEyeHeight() {
        return 1.75F;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        this.setBreathing(true);
        return true;
    }


    @Override
    public boolean isNonBoss() {
        return false;
    }


    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2399D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(9.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(38.0D);
    }

    protected void entityInit() {
        super.entityInit();
        this.getDataManager().register(ARMS_RAISED, Boolean.valueOf(false));
        dataManager.register(BREATH_FLAG, false);

    }

    @SideOnly(Side.CLIENT)
    public boolean isArmsRaised() {
        return this.getDataManager().get(ARMS_RAISED).booleanValue();
    }

    public void setArmsRaised(boolean armsRaised) {
        this.getDataManager().set(ARMS_RAISED, Boolean.valueOf(armsRaised));
    }

    @Override
    protected Item getDropItem() {
        return ModItems.FireSeed;
    }

    @Override
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
        Item item = ModItems.FireSeed;
        this.dropItem(item, 1);
    }

    @Override
    public String getName() {
        if (this.hasCustomName()) return this.getCustomNameTag();
        return "King";
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);

        if (this.world.rand.nextInt(7) == 0) {
            for (int i = 0; i < 2; ++i) {
                KingEntity entityking = new KingEntity(this.world);
                entityking.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 1.0F);
                this.world.spawnEntity(entityking);
            }
        }
        return livingdata;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = super.attackEntityAsMob(entityIn);

        if (flag) {
            float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();

            if (this.getHeldItemMainhand() == null) {
                entityIn.setFire(2 * (int) f);
            }
            float i = (float) Math.random();
            if (i < 0.7F) {
                ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.HUNGER, 140 * (int) f, 3));
            }
            if (i < 0.3F) {
                entityIn.setFire(2 * (int) f);
            }
        }
        return flag;
    }

    @Override
    public void onKillEntity(EntityLivingBase entityLivingIn) {
        dropFewItems(true, 1);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.BLOCK_CHORUS_FLOWER_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.BLOCK_ANVIL_BREAK;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_FIRE_EXTINGUISH;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        SoundEvent soundevent = SoundEvents.ENTITY_ZOMBIE_VILLAGER_STEP;
        this.playSound(soundevent, 0.15F, 1.0F);
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        super.attackEntityFrom(source, amount);
        if (this.getAttackTarget() != null) {
            EntityLivingBase entitylivingbase = this.getAttackTarget();

            if (entitylivingbase == null && source.getImmediateSource() instanceof EntityLivingBase) {
                entitylivingbase = (EntityLivingBase) source.getImmediateSource();
            }

            int i = MathHelper.floor(this.posX);
            int j = MathHelper.floor(this.posY);
            int k = MathHelper.floor(this.posZ);

            if (entitylivingbase != null && (double) this.rand.nextFloat() <= .01 && this.world.getGameRules().getBoolean("doMobSpawning")) {
                KingEntity entityking;
                entityking = new KingEntity(this.world);

                for (int l = 0; l < 50; ++l) {
                    int i1 = i + MathHelper.getInt(this.rand, 7, 40) * MathHelper.getInt(this.rand, -1, 1);
                    int j1 = j + MathHelper.getInt(this.rand, 7, 40) * MathHelper.getInt(this.rand, -1, 1);
                    int k1 = k + MathHelper.getInt(this.rand, 7, 40) * MathHelper.getInt(this.rand, -1, 1);

                    if (this.world.getBlockState(new BlockPos(i1, j1 - 1, k1)).isSideSolid(this.world, new BlockPos(i1, j1 - 1, k1), net.minecraft.util.EnumFacing.UP)) {
                        entityking.setPosition((double) i1, (double) j1, (double) k1);

                        if (!this.world.isAnyPlayerWithinRangeAt((double) i1, (double) j1, (double) k1, 7.0D) && this.world.checkNoEntityCollision(entityking.getEntityBoundingBox(), entityking) && this.world.getCollisionBoxes(entityking, entityking.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(entityking.getEntityBoundingBox())) {
                            this.world.spawnEntity(entityking);
                            entityking.setAttackTarget(entitylivingbase);
                            entityking.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entityking)), null);
                            break;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void onLivingUpdate() {
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
        this.world.spawnParticle(EnumParticleTypes.SPELL_MOB, this.posX + this.rand.nextGaussian() * 0.30000001192092896D, this.posY + this.rand.nextGaussian() * 0.30000001192092896D, this.posZ + this.rand.nextGaussian() * 0.30000001192092896D, 0.699999988079071D, 0.699999988079071D, 0.5D);
        super.onLivingUpdate();

        if (isBreathing()) {
            Vec3d look = this.getLookVec();

            double dist = 0;
            double px = this.posX;
            double py = this.posY;
            double pz = this.posZ;

            for (int i = 0; i < 10; i++) {
                double dx = look.x;
                double dy = look.y;
                double dz = look.z;

                double spread = 5 + this.getRNG().nextDouble() * 2.5;
                double velocity = 3.0 + this.getRNG().nextDouble() * 0.15;

                // spread flame
                dx += this.getRNG().nextGaussian() * 0.007499999832361937D * spread;
                dy += this.getRNG().nextGaussian() * 0.007499999832361937D * spread;
                dz += this.getRNG().nextGaussian() * 0.007499999832361937D * spread;
                dx *= velocity;
                dy *= velocity;
                dz *= velocity;

                FireParticle FireEffect2 = new FireParticle(world, px, py, pz, dx, dy, dz);
                Minecraft.getMinecraft().effectRenderer.addEffect(FireEffect2);
            }
        }
    }

    @Override
    public void addTrackingPlayer(EntityPlayerMP player) {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void removeTrackingPlayer(EntityPlayerMP player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    public boolean isBreathing() {
        return dataManager.get(BREATH_FLAG);

    }

    @Override
    public void setBreathing(boolean flag) {
        dataManager.set(BREATH_FLAG, flag);
    }

    @Override
    public void doBreathAttack(Entity target) {
        target.attackEntityFrom(DamageSource.causeMobDamage(this), 5F);
    }
}