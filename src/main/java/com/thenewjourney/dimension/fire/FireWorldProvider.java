package com.thenewjourney.dimension.fire;

import com.thenewjourney.dimension.ModDimension;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.actors.threadpool.Arrays;

public class FireWorldProvider extends WorldProvider {

    @Override
    public DimensionType getDimensionType() {
        return ModDimension.fireDimension;
    }

    @Override
    public String getSaveFolder() {
        return "FIRE";
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new FireChunkGenerator(world);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
        float f1 = 0.0F;
        float f2 = 0.0F;
        float f3 = 0.0F;
        return new Vec3d((double) f1, (double) f2, (double) f3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3d getSkyColor(net.minecraft.entity.Entity cameraEntity, float partialTicks) {
        float f1 = 0.4F;
        float f2 = 0.0F;
        float f3 = 0.0F;
        return new Vec3d((double) f1, (double) f2, (double) f3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3d getCloudColor(float partialTicks) {
        float f1 = 0.0F;
        float f2 = 0.0F;
        float f3 = 0.0F;
        return new Vec3d((double) f1, (double) f2, (double) f3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float par1) {
        return 0.0F;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getCloudHeight() {
        return 81;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isSkyColored() {
        return true;
    }

    @Override
    public boolean hasSkyLight() {
        return false;
    }

    @Override
    public boolean canDoRainSnowIce(net.minecraft.world.chunk.Chunk chunk) {
        return false;
    }

    @Override
    public void calculateInitialWeather() {
    }

    @Override
    public void updateWeather() {
    }

    @Override
    public void onPlayerAdded(EntityPlayerMP player) {
        ItemStack potion = new ItemStack(Items.POTIONITEM);
        if ( player.inventory.mainInventory.contains(potion)) {
            System.out.println("Potion");
        }
    }
}
