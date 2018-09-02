package com.thenewjourney.blocks.infuser;

import com.thenewjourney.entity.rift.RiftEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class InfuserTileEntity extends TileEntity implements ITickable {
	private static boolean isRift = false;

	@Override
	public void update() {
		//int info = ModWorldSaveData.forWorld(this.getWorld()).getUnlockedInfo();
        //isRift = info == 2;
		if (isRift) {
			RiftEntity entityrift = new RiftEntity(this.getWorld(), this.getPos().getX() + 0.5, this.getPos().getY() + 0.5, this.getPos().getZ() + 0.5);
            this.getWorld().spawnEntity(entityrift);
		}
	}
}
