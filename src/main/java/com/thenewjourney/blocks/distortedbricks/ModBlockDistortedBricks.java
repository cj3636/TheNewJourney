package com.thenewjourney.blocks.distortedbricks;

import com.cj3636.lib.Ref;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

public class ModBlockDistortedBricks extends BlockContainer {
	
	public ModBlockDistortedBricks(String unlocalizedName, Material material, float hardness, float resistance){
		super(material);
		this.setCreativeTab(Ref.CTAB);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);
		this.setHardness(hardness);
		this.setResistance(resistance);
	}
    @Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		 return new TileEntityDistortedBricks();
    }
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
