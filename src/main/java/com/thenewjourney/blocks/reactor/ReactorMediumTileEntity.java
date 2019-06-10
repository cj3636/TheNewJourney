package com.thenewjourney.blocks.reactor;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ReactorMediumTileEntity extends TileEntity {
    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        AxisAlignedBB aabb = new AxisAlignedBB(getPos(), getPos().add(2, 2, 2));
        return aabb;
    }
}
