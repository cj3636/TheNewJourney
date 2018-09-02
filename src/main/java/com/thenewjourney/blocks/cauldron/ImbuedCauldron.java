package com.thenewjourney.blocks.cauldron;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ImbuedCauldron extends Block implements ITileEntityProvider {

    public ImbuedCauldron(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);

        this.setCreativeTab(Ref.CTAB);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }

    public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 3);
    protected static final AxisAlignedBB AABB_LEGS = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D);
    protected static final AxisAlignedBB AABB_WALL_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
    protected static final AxisAlignedBB AABB_WALL_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB AABB_WALL_EAST = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB AABB_WALL_WEST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);

    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
                                      List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn) {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_LEGS);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_WEST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_NORTH);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_EAST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_SOUTH);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return FULL_BLOCK_AABB;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for
     * render
     */
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        ImbuedCaulronTileEntity imbuedTileEntity = (ImbuedCaulronTileEntity) tileentity;
        if (entityIn instanceof EntityItem && imbuedTileEntity.getItemStacks()[8] == null) {
            EntityItem entityItem = (EntityItem) entityIn;
            imbuedTileEntity.addItemStack(entityItem.getItem());
            entityItem.setDead();
        }
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
                                    EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (heldItem == null) {
            return true;
        } else {
            int i = state.getValue(LEVEL).intValue();
            Item item = heldItem.getItem();

            if (item == Items.LAVA_BUCKET) {
                if (i < 3 && !worldIn.isRemote) {
                    if (!playerIn.capabilities.isCreativeMode) {
                        playerIn.setHeldItem(hand, new ItemStack(Items.BUCKET));
                    }

                    this.setWaterLevel(worldIn, pos, state, 3);
                }

                return true;
            } else if (item == Items.BUCKET) {
                if (i == 3 && !worldIn.isRemote) {
                    if (!playerIn.capabilities.isCreativeMode) {
                        heldItem.setCount(heldItem.getCount() - 1);
                        if (heldItem.getCount() == 0) {
                            playerIn.setHeldItem(hand, new ItemStack(Items.LAVA_BUCKET));
                        } else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items.LAVA_BUCKET))) {
                            playerIn.dropItem(new ItemStack(Items.LAVA_BUCKET), false);
                        }
                    }

                    this.setWaterLevel(worldIn, pos, state, 0);
                }

                return true;// TODO ADD A LAVA POTION OR REMOVE THIS SECTION
            } else if (item == Items.GLASS_BOTTLE) {
                if (i > 0 && !worldIn.isRemote) {
                    if (!playerIn.capabilities.isCreativeMode) {
                        ItemStack itemstack1 = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),
                                PotionTypes.AWKWARD);

                        if ((heldItem.getCount() - 1) == 0) {
                            playerIn.setHeldItem(hand, itemstack1);
                        } else if (!playerIn.inventory.addItemStackToInventory(itemstack1)) {
                            playerIn.dropItem(itemstack1, false);
                        } else if (playerIn instanceof EntityPlayerMP) {
                            ((EntityPlayerMP) playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
                        }
                    }

                    this.setWaterLevel(worldIn, pos, state, i - 1);
                }
                return true;
            }
        }
        return false;
    }

    public void setWaterLevel(World worldIn, BlockPos pos, IBlockState state, int level) {
        worldIn.setBlockState(pos, state.withProperty(LEVEL, Integer.valueOf(MathHelper.clamp(level, 0, 3))), 2);
        worldIn.updateComparatorOutputLevel(pos, this);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.ImbuedCauldron);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(ModBlocks.ImbuedCauldron));
    }

    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
        return blockState.getValue(LEVEL).intValue();
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(LEVEL, Integer.valueOf(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state) {
        return state.getValue(LEVEL).intValue();
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LEVEL);
    }

    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new ImbuedCaulronTileEntity();
    }
}