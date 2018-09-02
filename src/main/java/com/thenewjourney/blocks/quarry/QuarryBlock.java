package com.thenewjourney.blocks.quarry;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.pillar.ArcanePillarTileEntity;
import com.thenewjourney.items.ModItems;
import com.thenewjourney.power.ModPower;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class QuarryBlock extends Block {

    private ArrayList<IBlockState> blocks = new ArrayList<IBlockState>();

    public QuarryBlock(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);
        this.setCreativeTab(Ref.CTAB);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean addDestroyEffects(World world, BlockPos pos, ParticleManager manager) {
        return true;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = playerIn.getHeldItemMainhand();
    	if (GuiScreen.isShiftKeyDown()) {
    		worldIn.destroyBlock(pos, true);
            return false;
		}
        if (heldItem!= null && heldItem.getItem() == ModItems.CrystalBinder && ModPower.isNumPoweredAtTier(worldIn, 5, 12)) {
        	heldItem.setCount(heldItem.getCount() - 1);
            runQuarry(worldIn, pos);
        } else {
            if (!worldIn.isRemote) {
                playerIn.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "No Power! You must hold a Crystal Binder as fuel. The world power tier must be 5 or greater. The total world power must be at least 12"));
            }
        }
        return true;
    }

    private void runQuarry(World worldIn, BlockPos pos) {
    	if (worldIn.getTileEntity(pos.up()) instanceof ArcanePillarTileEntity) {
    		runQuarryOnBlock(worldIn, pos);
    	}
        BlockPos start = pos.add(-4, -1, -4);
        for (int i = 0; i < 81; i++) {
            mineToBedrock(worldIn, new BlockPos(start.getX() + (i % 9), start.getY(), start.getZ() + (i / 9)));
        }
    }

    private void runQuarryOnBlock(World worldIn, BlockPos pos) {
    	BlockPos start = pos.add(-4, -1, -4);
        for (int i = 0; i < 81; i++) {
            mineToBedrockForBlock(worldIn, new BlockPos(start.getX() + (i % 9), start.getY(), start.getZ() + (i / 9)), pos);
        }
	}

	private void mineToBedrockForBlock(World worldIn, BlockPos pos, BlockPos quarryPos) {
		for (int i = 0; i < 256; i++) {
            BlockPos posAt = pos.down(i);
            Block blockCheck = worldIn.getBlockState(posAt).getBlock();
            if (isExactOre(blockCheck, worldIn, quarryPos)) {
                blocks.add(worldIn.getBlockState(posAt));
                worldIn.setBlockState(posAt, Blocks.AIR.getDefaultState(), 1);
                spawnBlockList(worldIn, blockCheck, pos);
            }
            if (posAt.getY() == 0) {
                break;
            }
        }
		
	}

	private boolean isExactOre(Block blockCheck, World worldIn, BlockPos pos) {
		ArcanePillarTileEntity tileEntity = (ArcanePillarTileEntity) worldIn.getTileEntity(pos.up());
		Block chosenBlock = null;
        if (tileEntity.getStackInSlot(0) == null) {
			return isOre(blockCheck);
		} else if (tileEntity.getStackInSlot(0).getItem() instanceof ItemBlock) {
			ItemBlock chosenItemBlock = (ItemBlock) tileEntity.getStackInSlot(0).getItem();
			chosenBlock = chosenItemBlock.getBlock();
			System.out.println(chosenBlock);
		}
        return blockCheck == chosenBlock;
	}

	public void mineToBedrock(World worldIn, BlockPos pos) {
        for (int i = 0; i < 256; i++) {
            BlockPos posAt = pos.down(i);
            Block blockCheck = worldIn.getBlockState(posAt).getBlock();
            if (isOre(blockCheck)) {
                blocks.add(worldIn.getBlockState(posAt));
                worldIn.setBlockState(posAt, Blocks.AIR.getDefaultState(), 1);
                spawnBlockList(worldIn, blockCheck, pos);
            }
            if (posAt.getY() == 0) {
                break;
            }
        }
    }

    private void spawnBlockList(World worldIn, Block block, BlockPos pos) {
        if (!worldIn.isRemote) {
            EntityItem itemstack = new EntityItem(worldIn, pos.getX(), pos.up().getY(), pos.getZ(), new ItemStack(block));
            worldIn.spawnEntity(itemstack);
        }
    }

    private boolean isOre(Block blockCheck) {
        return blockCheck != Blocks.STONE &&
                blockCheck != Blocks.DIRT &&
                blockCheck != Blocks.GRASS &&
                blockCheck != Blocks.GRAVEL &&
                blockCheck != Blocks.SAND &&
                blockCheck != Blocks.AIR &&
                blockCheck != Blocks.BEDROCK &&
                blockCheck != Blocks.WEB &&
                blockCheck != Blocks.PLANKS &&
                blockCheck != Blocks.OAK_FENCE &&
                blockCheck != Blocks.LOG &&
                blockCheck != Blocks.LOG2 &&
                blockCheck != Blocks.WATER &&
                blockCheck != Blocks.FLOWING_WATER &&
                blockCheck != Blocks.LAVA &&
                blockCheck != Blocks.FLOWING_LAVA &&
                blockCheck != Blocks.TALLGRASS &&
                blockCheck != Blocks.DOUBLE_PLANT &&
                blockCheck != Blocks.SANDSTONE &&
                blockCheck != Blocks.RED_SANDSTONE;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
}
