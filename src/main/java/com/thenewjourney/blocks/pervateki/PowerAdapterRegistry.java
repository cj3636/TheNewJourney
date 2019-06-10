package com.thenewjourney.blocks.pervateki;

import com.cj3636.lib.Ref;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PowerAdapterRegistry extends BlockContainer {
    private int energy;
    private int capacity;
    private int maxReceive;
    private int maxExtract;

    public PowerAdapterRegistry(String unlocalizedName, Material material, float hardness, float resistance, int capacity, int maxReceive, int maxExtract) {
        super(material);
        this.setCreativeTab(Ref.CTAB);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energy = Math.max(0, Math.min(capacity, energy));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (worldIn.getTileEntity(pos) != null && worldIn.getTileEntity(pos) instanceof PowerAdapterTileEntity) {
                PowerAdapterTileEntity tileEntity = (PowerAdapterTileEntity) worldIn.getTileEntity(pos);
                System.out.println(tileEntity.energyStorage.getEnergyStored());
            }
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new PowerAdapterTileEntity(capacity, maxReceive, maxExtract);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
