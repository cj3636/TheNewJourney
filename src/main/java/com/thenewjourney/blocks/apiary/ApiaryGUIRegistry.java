package com.thenewjourney.blocks.apiary;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ApiaryGUIRegistry implements IGuiHandler {
    private static final int GUI_ID = 34;

    public static int getGuiID() {
        return GUI_ID;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID != getGuiID()) {
            System.err.println("Invalid ID: expected " + getGuiID() + ", received " + ID);
        }

        BlockPos xyz = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(xyz);
        if (tileEntity instanceof ApiaryTileEntity) {
            ApiaryTileEntity tileInventoryApiary = (ApiaryTileEntity) tileEntity;
            return new ApiaryContainer(player.inventory, tileInventoryApiary);
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
        if (tileEntity instanceof ApiaryTileEntity) {
            ApiaryTileEntity tileInventoryApiary = (ApiaryTileEntity) tileEntity;
            return new ApiaryGuiInventory(player.inventory, tileInventoryApiary);
        }
        return null;
    }
}
