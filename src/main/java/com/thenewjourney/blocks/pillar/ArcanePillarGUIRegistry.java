package com.thenewjourney.blocks.pillar;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ArcanePillarGUIRegistry implements IGuiHandler {
    private static final int GUI_ID = 37;

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
        if (tileEntity instanceof ArcanePillarTileEntity) {
            ArcanePillarTileEntity tileInventoryArcanePillar = (ArcanePillarTileEntity) tileEntity;
            return new ArcanePillarContainer(player.inventory, tileInventoryArcanePillar);
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
        if (tileEntity instanceof ArcanePillarTileEntity) {
            ArcanePillarTileEntity tileInventoryArcanePillar = (ArcanePillarTileEntity) tileEntity;
            return new ArcanePillarGuiInventory(player.inventory, tileInventoryArcanePillar);
        }
        return null;
    }
}
