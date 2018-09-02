package com.thenewjourney.blocks.florus;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.power.ModPower;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

/**
 * Created by cj3636 on 1/23/2017.
 */
public class CompilerBlock extends Block {
    public CompilerBlock(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);

        this.setCreativeTab(Ref.CTAB);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        BlockPos[] activeFrameList = getFrames(worldIn, pos);
        if (!(ModPower.getPowerTier(worldIn) >= 3)) {
            playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Too low of a power tier! At least 3 is required."));
            return false;
        }
        if (activeFrameList != null) {
            for (BlockPos posCompile : activeFrameList) {
                compile(worldIn, posCompile, pos);
                worldIn.destroyBlock(pos, false);
                worldIn.setBlockState(pos.down(7), ModBlocks.Compiler.getDefaultState());
                worldIn.setBlockState(pos.down(8), ModBlocks.CompressedFlorusBlock.getDefaultState());
            }
        } else {
            if (!worldIn.isRemote) {
                playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Invalid Setup!"));
                return false;
            }
        }
        return true;
    }

    private BlockPos[] getFrames(World worldIn, BlockPos pos) {
        BlockPos[] frameListReturn = new BlockPos[8];
        for (int i = 0; i < 8; i++) {
            BlockPos posAt = pos.down(i + 1);
            if (worldIn.getBlockState(posAt).getBlock().equals(ModBlocks.Frame)) {
                if (checkFlorus(worldIn, posAt)) {
                    frameListReturn[i] = posAt;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
        return frameListReturn;
    }

    private boolean checkFlorus(World worldIn, BlockPos posAt) {
        boolean returnBool = false;
        BlockPos[] posList = {
                posAt.north(),
                posAt.south(),
                posAt.east(),
                posAt.west(),
                posAt.north().east(),
                posAt.north().west(),
                posAt.south().east(),
                posAt.south().west()
        };
        for (BlockPos posCheck : posList) {
            Block blockIn = worldIn.getBlockState(posCheck).getBlock();
            if (!blockIn.equals(ModBlocks.FlorusBlock)) {
                returnBool = false;
                break;
            } else {
                returnBool = true;
            }
        }
        return returnBool;
    }

    private void compile(World worldIn, BlockPos posAt, BlockPos pos) {
        boolean returnBool = false;
        BlockPos[] posList = {
                posAt.north(),
                posAt.south(),
                posAt.east(),
                posAt.west(),
                posAt.north().east(),
                posAt.north().west(),
                posAt.south().east(),
                posAt.south().west()
        };
        for (BlockPos posCheck : posList) {
            worldIn.destroyBlock(posCheck, false);
        }
        worldIn.destroyBlock(posAt, false);
    }
}
