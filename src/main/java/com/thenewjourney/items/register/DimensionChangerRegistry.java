package com.thenewjourney.items.register;

import com.cj3636.lib.Config;
import com.thenewjourney.dimension.ModTeleporter;
import com.thenewjourney.items.wand.WandRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class DimensionChangerRegistry extends WandRegistry {

    public DimensionChangerRegistry(String unlocalizedName, ToolMaterial material, int maxDamage) {
    	super(unlocalizedName, material, maxDamage);
    }
    @Override
	protected void onRightBlock(World worldIn, EntityPlayer playerIn, ItemStack stack, BlockPos pos) {
    	if (!worldIn.isRemote) {
    		ModTeleporter teleporter = new ModTeleporter((WorldServer)worldIn);
    		if (playerIn.dimension != 0) {
    			playerIn.getServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP)playerIn, 0, teleporter);
    		} else {
        		playerIn.getServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP)playerIn, Config.fireDimId, teleporter);
    		}
    	}
	}
    @Override
	protected void onShiftRightBlock(World worldIn, EntityPlayer playerIn, ItemStack stack, BlockPos pos) {
    	if (!worldIn.isRemote) {
    		ModTeleporter teleporter = new ModTeleporter((WorldServer)worldIn);
    		if (playerIn.dimension != 0) {
    			playerIn.getServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP)playerIn, 0, teleporter);
    		} else {
        		playerIn.getServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP)playerIn, Config.florusDimId, teleporter);
    		}
    	}
	}
    @Override
	protected void onCtrlRightBlock(World worldIn, EntityPlayer playerIn, ItemStack stack, BlockPos pos) {
    	if (!worldIn.isRemote) {
    		ModTeleporter teleporter = new ModTeleporter((WorldServer)worldIn);
    		if (playerIn.dimension != 0) {
    			playerIn.getServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP)playerIn, 0, teleporter);
    		} else {
        		playerIn.getServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP)playerIn, 1, teleporter);
    		}
    	}
	}
    @Override
	protected void onAltRightBlock(World worldIn, EntityPlayer playerIn, ItemStack stack, BlockPos pos) {
    	if (!worldIn.isRemote) {
    		ModTeleporter teleporter = new ModTeleporter((WorldServer)worldIn);
    		if (playerIn.dimension != 0) {
    			playerIn.getServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP)playerIn, 0, teleporter);
    		} else {
        		playerIn.getServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP)playerIn, -1, teleporter);
    		}
    	}
	}
}
