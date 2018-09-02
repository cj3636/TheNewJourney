package com.thenewjourney.blocks.stoneman;

import com.cj3636.lib.LocUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class StoneManTileEntity extends TileEntity implements ITickable {
    @Override
    public void update() {
        LocUtil.repelEntitiesInAABBFromPoint(world, new AxisAlignedBB(pos.add(-16, -16, -16), pos.add(16, 16, 16)), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
    }
}
