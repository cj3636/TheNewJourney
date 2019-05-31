package com.thenewjourney.blocks.infuser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class InfuserGUIRegistry implements IGuiHandler {
	private static final int GUIID = 32;
	public static int getGuiID() {return GUIID;}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		if (ID != getGuiID()) {
			System.err.println("Invalid ID: expected " + getGuiID() + ", received " + ID);
		}

		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		if (tileEntity instanceof InfuserTileEntity) {
			InfuserTileEntity ModTileEntityInfuser = (InfuserTileEntity) tileEntity;
			return new InfuserContainer(player.inventory, player.getEntityWorld(), pos);
		}
		return null;
	}
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		if (ID != getGuiID()) {
			System.err.println("Invalid ID: expected " + getGuiID() + ", received " + ID);
		}

		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		if (tileEntity instanceof InfuserTileEntity) {
			InfuserTileEntity ModTileEntityInfuser = (InfuserTileEntity) tileEntity;
			return new InfuserGuiInventory(player.inventory, pos);
		}
		return null;
	}
}