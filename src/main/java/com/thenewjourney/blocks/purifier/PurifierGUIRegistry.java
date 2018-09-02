package com.thenewjourney.blocks.purifier;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class PurifierGUIRegistry implements IGuiHandler {
	private static final int GUI_ID = 35;
	public static int getGuiID() {return GUI_ID;}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID != getGuiID()) {
			System.err.println("Invalid ID: expected " + getGuiID() + ", received " + ID);
		}

		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		if (tileEntity instanceof PurifierTileEntity) {
			PurifierTileEntity tileInventoryFurnace = (PurifierTileEntity) tileEntity;
			return new PurifierContainer(player.inventory, tileInventoryFurnace);
		}
		return null;
	}
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID != getGuiID()) {
			System.err.println("Invalid ID: expected " + getGuiID() + ", received " + ID);
		}

		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		if (tileEntity instanceof PurifierTileEntity) {
			PurifierTileEntity tileInventoryFurnace = (PurifierTileEntity) tileEntity;
			return new PurifierGuiInventory(player.inventory, tileInventoryFurnace);
		}
		return null;
	}
}
