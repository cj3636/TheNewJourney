package com.thenewjourney.blocks.register;

import com.cj3636.lib.Ref;
import com.thenewjourney.particle.BeeParticle;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class HiveRegistry extends Block {

    private int pAmount = 3;

    public HiveRegistry(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);
        this.setCreativeTab(Ref.CTAB);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }

    @Override
    protected boolean canSilkHarvest() {
        return true;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {

        if (true) {
            double dx = pos.getX() + 0.5D;
            double dy = pos.getY() + 0.5D;
            double dz = pos.getZ() + 0.5D;

            for (int i = 0; i < pAmount; i++) {
                dx += 4 * rand.nextFloat() * 2 - 1;
                dy += 4 * rand.nextFloat() * 2 - 1;
                dz += 4 * rand.nextFloat() * 2 - 1;

                BeeParticle beeParticle = new BeeParticle(worldIn, dx, dy, dz, 0.0F, 1.0F, 0.0F);
                Minecraft.getMinecraft().effectRenderer.addEffect(beeParticle);

                BeeParticle beeParticle2 = new BeeParticle(worldIn, -dx, -dy, -dz, 0.0F, 1.0F, 0.0F);
                Minecraft.getMinecraft().effectRenderer.addEffect(beeParticle2);

                BeeParticle beeParticle3 = new BeeParticle(worldIn, -dx, dy, dz, 0.0F, 1.0F, 0.0F);
                Minecraft.getMinecraft().effectRenderer.addEffect(beeParticle3);

                BeeParticle beeParticle4 = new BeeParticle(worldIn, dx, dy, -dz, 0.0F, 1.0F, 0.0F);
                Minecraft.getMinecraft().effectRenderer.addEffect(beeParticle4);
            }
        }
    }
}
