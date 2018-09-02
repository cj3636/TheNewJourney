package com.thenewjourney.fluid;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.items.ModItems;
import com.thenewjourney.misc.ModAchievements;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class InversionSerum extends BlockFluidClassic implements ITileEntityProvider {
	public InversionSerum(Fluid fluid, Material material) {
		super(fluid, material);
		this.quantaPerBlock = 1;
		this.quantaPerBlockFloat = 1F;
		stack = new FluidStack(fluid, Fluid.BUCKET_VOLUME);
	}

	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return false;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		ArrayList<EntityItem> items = new ArrayList<>();
		if (entityIn instanceof EntityItem) {
			EntityItem entityItem = (EntityItem) entityIn;
			items.add(entityItem);
		} else if (entityIn instanceof EntityChicken) {
			EntityChicken chicken = (EntityChicken) entityIn;
			chicken.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 50, 3, false, false));
			List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity(entityIn,
					new AxisAlignedBB(pos.getX() - 3.0D, pos.getY() - 3.0D, pos.getZ() - 3.0D, pos.getX() + 3.0D,
							pos.getY() + 6.0D + 3.0D, pos.getZ() + 3.0D));

			for (int i = 0; i < list.size(); ++i) {
				Entity entity = list.get(i);
				if (entity instanceof EntityPlayer) {
					EntityPlayer entityPlayer = (EntityPlayer) entity;
					//TODO Add Acheivement
					//entityPlayer.addStat(ModAchievements.achievementWhenChickensFly, 1);
				}
			}
		} else if (entityIn instanceof EntityLivingBase) {
			EntityLivingBase living = (EntityLivingBase) entityIn;
			living.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 50, 3, false, false));
		} else {
			System.err.println(Ref.MODID + " Found a major entity error! Report to CJ3636 and the Forge Team!");
		}
		if (items != null) {
			for (EntityItem itemsIn : items) {
				Item itemIn = itemsIn.getItem().getItem();
				if (itemsIn.getItem().getCount() > InversionTileEntity.getLifeValue()) {
					return;
				}
				runItemSpawner(worldIn, pos, itemIn, itemsIn.getItem().getCount());
				itemsIn.setDead();
			}
		}
	}

	private void runItemSpawner(World worldIn, BlockPos pos, Item itemIn, int stackSize) {
		if (itemIn.equals(ModItems.AquisIngot)) {
			EntityItem spawnItem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(),
					new ItemStack(ModItems.FireIngot, stackSize));
			spawnItem.setVelocity(0D, .1D, 0D);
			InversionTileEntity.setLifeValue(stackSize);
			if (!worldIn.isRemote) {
				worldIn.spawnEntity(spawnItem);
			}
		} else if (itemIn.equals(Item.getItemFromBlock(ModBlocks.BurntStone))) {
			EntityItem spawnItem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(),
					new ItemStack(ModBlocks.WarpStone, stackSize));
			spawnItem.setVelocity(0D, .1D, 0D);
			InversionTileEntity.setLifeValue(stackSize);
		} else if (itemIn.equals(ModItems.FireIngot)) {
			EntityItem spawnItem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(),
					new ItemStack(ModItems.AquisIngot, stackSize));
			spawnItem.setVelocity(0D, .1D, 0D);
			InversionTileEntity.setLifeValue(stackSize);
			if (!worldIn.isRemote) {
				worldIn.spawnEntity(spawnItem);
			}
		} else if (itemIn.equals(ModItems.RubyGem)) {
			EntityItem spawnItem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(),
					new ItemStack(Items.EMERALD, stackSize));
			spawnItem.setVelocity(0D, .1D, 0D);
			InversionTileEntity.setLifeValue(stackSize);
			if (!worldIn.isRemote) {
				worldIn.spawnEntity(spawnItem);
			}
		} else {
			EntityItem spawnItem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(itemIn, stackSize));
			spawnItem.setVelocity(0D, .1D, 0D);
			InversionTileEntity.setLifeValue(stackSize);
			if (!worldIn.isRemote) {
				worldIn.spawnEntity(spawnItem);
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new InversionTileEntity(32);
	}
}