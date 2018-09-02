package com.thenewjourney.blocks.register;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class GemOreRegistry extends Block {
	
	public GemOreRegistry(String unlocalizedName, Material material, float hardness, float resistance, Item drop){
		super(material);
		this.setCreativeTab(Ref.CTAB);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);
		this.setHardness(hardness);
		this.setResistance(resistance);
	}
	@Override
	public Item getItemDropped(IBlockState blockstate, Random random, int fortune) {
		if (this == ModBlocks.DistortedOre) {
			return ModItems.DistortionGem;
		}
		if (this == ModBlocks.RubyOre) {
			return ModItems.RubyGem;
		}
		return null;
	}
	@Override
	public int quantityDroppedWithBonus(int fortune, Random random)
    {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(this.getBlockState().getValidStates().iterator().next(), random, fortune))
        {
            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0)
            {
                i = 0;
            }

            return this.quantityDropped(random) * (i + 1);
        }
        else
        {
            return this.quantityDropped(random);
        }
    }
	@Override
    protected boolean canSilkHarvest()
    {
        return true;
    }
}
