package com.thenewjourney.blocks.register;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class CoagulativeBlockRegistry extends Block {

	public CoagulativeBlockRegistry(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setLightLevel(.8F);
        this.setCreativeTab(Ref.CTAB);
	}
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
	    return Item.getItemFromBlock(ModBlocks.Crystal);
	}
}