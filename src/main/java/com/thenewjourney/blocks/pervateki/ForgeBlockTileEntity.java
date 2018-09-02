package com.thenewjourney.blocks.pervateki;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class ForgeBlockTileEntity extends TileEntity {
	private boolean isActive;

	public ForgeBlockTileEntity() {
	}

	public ForgeBlockTileEntity(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setBoolean("isActive", this.isActive);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		this.isActive = nbtTagCompound.getBoolean("isActive");
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		writeToNBT(nbtTagCompound);
		final int METADATA = 0;
		return new SPacketUpdateTileEntity(this.pos, METADATA, nbtTagCompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}

	public boolean isActive() {
		markDirty();
		return this.isActive;
	}

	public void setActive(boolean isActive) {
		markDirty();
		this.isActive = isActive;
	}
}
