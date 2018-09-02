package com.thenewjourney.entity.rift;

import com.cj3636.lib.Config;
import com.thenewjourney.dimension.ModTeleporter;
import com.thenewjourney.particle.RiftParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;


public class RiftEntity extends Entity {
    private int xPos;
    private int yPos;
    private int zPos;
    private int tickLife;

    public RiftEntity(World par1World, double posX, double posY, double posZ) {
        super(par1World);
        this.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        setSize(2.0f, 2.0f);
        ignoreFrustumCheck = true;
    }

    public RiftEntity(World par1World) {
        super(par1World);
        this.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
        setSize(2.0f, 2.0f);
        ignoreFrustumCheck = true;
    }

    @Override
    protected void entityInit() {
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (world.isRemote) {
            updateClient();
        }
        updateServer();
        if (tickLife > 1) {
            this.setDead();
            tickLife = 0;
        }
        tickLife++;
    }

    @SideOnly(Side.CLIENT)
    private void updateClient() {
        motionX = 0.0;
        motionY = 0.0;
        motionZ = 0.0;
        if (this.getEntityWorld().isRemote) {
            int smokeParticles = rand.nextInt(12);
            for (int i = 0; i < smokeParticles; i++) {

                double xP = (rand.nextFloat() * 8.0 - 4.0);
                double yP = (rand.nextFloat() * 8.0 - 4.0);
                double zP = (rand.nextFloat() * 8.0 - 4.0);

                this.getEntityWorld().spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, posX, posY + 1.0 + height / 2, posZ, xP, yP, zP);
                this.getEntityWorld().spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, posX, posY + 1.0 + height / 2, posZ, -xP, -yP, -zP);
            }
            int textParticles = rand.nextInt(19);
            for (int i = 0; i < textParticles; i++) {
                double xP = rand.nextFloat() * 8.0 - 4.0;
                double yP = rand.nextFloat() * 8.0 - 4.0;
                double zP = rand.nextFloat() * 8.0 - 4.0;


                RiftParticle riftEffect = new RiftParticle(this.getEntityWorld(), posX, posY + 1.0 + height / 2, posZ, xP, yP, zP);
                Minecraft.getMinecraft().effectRenderer.addEffect(riftEffect);

                RiftParticle riftEffect2 = new RiftParticle(this.getEntityWorld(), posX, posY + 1.0 + height / 2, posZ, -xP, -yP, -zP);
                Minecraft.getMinecraft().effectRenderer.addEffect(riftEffect2);
            }
        }
    }

    private void updateServer() {
        List<EntityPlayer> entityList = this.getEntityWorld().getEntitiesWithinAABB(EntityPlayer.class, this.getRenderBoundingBox());
        for (EntityPlayer playerIn : entityList) {
            if (!this.getEntityWorld().isRemote) {
                WorldServer worldTo = playerIn.getServer().getWorld(Config.fireDimId);
                ModTeleporter teleporter = new ModTeleporter(worldTo);
                playerIn.getServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) playerIn, Config.fireDimId, teleporter);
                this.setDead();
            }
        }
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(this.getPosition());
    }

    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setInteger("xPos", this.xPos);
        compound.setInteger("yPos", this.yPos);
        compound.setInteger("zPos", this.zPos);
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        this.xPos = compound.getInteger("xPos");
        this.yPos = compound.getInteger("yPos");
        this.zPos = compound.getInteger("zPos");
    }
}