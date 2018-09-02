package com.thenewjourney.dimension.florus;

import com.thenewjourney.dimension.ModDimension;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FlorusWorldProvider extends WorldProvider {

    @Override
    public DimensionType getDimensionType() {
        return ModDimension.florusDimension;
    }

    @Override
    public String getSaveFolder() {
        return "FLORUS";
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new FlorusChunkGenerator(world);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1) {
        return 1.0F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
        float f1 = 0.0F;
        float f2 = 0.2F;
        float f3 = 0.7F;
        return new Vec3d((double) f1, (double) f2, (double) f3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3d getSkyColor(net.minecraft.entity.Entity cameraEntity, float partialTicks) {
        float f1 = 0.5F;
        float f2 = 0.0F;
        float f3 = 0.5F;
        return new Vec3d((double) f1, (double) f2, (double) f3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3d getCloudColor(float partialTicks) {
        float f1 = 0.0F;
        float f2 = 0.0F;
        float f3 = 1.0F;
        return new Vec3d((double) f1, (double) f2, (double) f3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float par1) {
        return 8.0F;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getCloudHeight() {
        return 120;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isSkyColored() {
        return true;
    }

    @Override
    public boolean hasSkyLight() {
        return true;
    }

}
