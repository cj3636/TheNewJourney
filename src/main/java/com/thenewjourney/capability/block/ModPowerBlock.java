package com.thenewjourney.capability.block;

public class ModPowerBlock implements IPowerBlockCapability {
    private int blockNum;

    @Override
    public void addBlock(int block) {
        blockNum += block;
    }

    @Override
    public void setBlock(int block) {
        blockNum = block;
        if (blockNum < 0) {
            blockNum = 0;
        }
    }

    @Override
    public void removeBlock(int block) {
        blockNum -= block;
        if (blockNum < 0) {
            blockNum = 0;
        }
    }

    @Override
    public int getBlock() {
        return blockNum;
    }
}
