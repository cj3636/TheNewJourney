package com.thenewjourney.blocks.idol;

import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.blocks.provider.CrystalProviderTileEntityRenderer;
import com.thenewjourney.blocks.provider.Provider;
import com.thenewjourney.items.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.Random;

public class IdolTileEntity extends TileEntity implements ITickable {
    public static final Color INVALID_COLOR = null;
    private final long INVALID_TIME = 0;
    private boolean isActive;
    private boolean isFullActive;
    private Color gemColour = Color.YELLOW;  // the RGB colour of the gem
    private long lastTime = INVALID_TIME;  // used for animation
    private double lastAngularPosition; // used for animation

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getFullActive() {
        return isFullActive;
    }

    public void setFullActive(boolean isFullActive) {
        this.isFullActive = isFullActive;
    }

    // get the colour of the gem.  returns INVALID_COLOR if not set yet.
    public Color getGemColour() {
        return gemColour;
    }

    public void setGemColour(Color newColour) {
        gemColour = newColour;
    }

    /**
     * Calculate the next angular position of the gem, given its current speed.
     *
     * @param revsPerSecond
     * @return the angular position in degrees (0 - 360)
     */
    public double getNextAngularPosition(double revsPerSecond) {
        // we calculate the next position as the angular speed multiplied by the elapsed time since the last position.
        // Elapsed time is calculated using the system clock, which means the animations continue to
        //  run while the game is paused.
        // Alternatively, the elapsed time can be calculated as
        //  time_in_seconds = (number_of_ticks_elapsed + partialTick) / 20.0;
        //  where your tileEntity's update() method increments number_of_ticks_elapsed, and partialTick is passed by vanilla
        //   to your TESR renderTileEntityAt() method.
        long timeNow = System.nanoTime();
        if (lastTime == INVALID_TIME) {   // automatically initialise to 0 if not set yet
            lastTime = timeNow;
            lastAngularPosition = 0.0;
        }
        final double DEGREES_PER_REV = 360.0;
        final double NANOSECONDS_PER_SECOND = 1e9;
        double nextAngularPosition = lastAngularPosition + (timeNow - lastTime) * revsPerSecond * DEGREES_PER_REV / NANOSECONDS_PER_SECOND;
        nextAngularPosition = nextAngularPosition % DEGREES_PER_REV;
        lastAngularPosition = nextAngularPosition;
        lastTime = timeNow;
        return nextAngularPosition;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public double getMaxRenderDistanceSquared() {
        final int MAXIMUM_DISTANCE_IN_BLOCKS = 32;
        return MAXIMUM_DISTANCE_IN_BLOCKS * MAXIMUM_DISTANCE_IN_BLOCKS;
    }

    /**
     * Return an appropriate bounding box enclosing the TESR
     * This method is used to control whether the TESR should be rendered or not, depending on where the player is looking.
     * The default is the AABB for the parent block, which might be too small if the TESR renders outside the borders of the
     * parent block.
     * If you get the boundary too small, the TESR may disappear when you aren't looking directly at it.
     *
     * @return an appropriately size AABB for the TileEntity
     */
    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        // if your render should always be performed regardless of where the player is looking, use infinite
        //AxisAlignedBB infiniteExample = INFINITE_EXTENT_AABB;

        // our gem will stay above the block, up to 1 block higher, so our bounding box is from [x,y,z] to  [x+1, y+2, z+1]
        AxisAlignedBB aabb = INFINITE_EXTENT_AABB;
        return aabb;
    }
    boolean prov1 = false;
    boolean prov2 = false;
    boolean prov3 = false;
    boolean prov4 = false;
    @Override
    public void update() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        if (world.getTotalWorldTime() % 500 == 0) {
            this.setGemColour(new Color(r, g, b));
        }
        if (getActive() && !getFullActive()) {
            if (world.getTotalWorldTime() % 150 == 0) {
                world.setBlockState(pos.north(), ModBlocks.Provider.getDefaultState().withProperty(Provider.FACING, EnumFacing.NORTH));
                prov1 = true;
            }
            if (world.getTotalWorldTime() % 250 == 0) {
                world.setBlockState(pos.south(), ModBlocks.Provider.getDefaultState().withProperty(Provider.FACING, EnumFacing.SOUTH));
                prov2 = true;
            }
            if (world.getTotalWorldTime() % 350 == 0) {
                world.setBlockState(pos.west(), ModBlocks.Provider.getDefaultState().withProperty(Provider.FACING, EnumFacing.WEST));
                prov3= true;
            }
            if (world.getTotalWorldTime() % 450 == 0) {
                world.setBlockState(pos.east(), ModBlocks.Provider.getDefaultState().withProperty(Provider.FACING, EnumFacing.EAST));
                prov4 = true;
            }
            if (prov1 && prov2 && prov3 && prov4) {
                setFullActive(true);
                setActive(false);
            }
        }
        if (getFullActive()) {
            if (world.getBlockState(pos.up()).equals(Blocks.EMERALD_BLOCK.getDefaultState())) {
                world.sendBlockBreakProgress(world.getClosestPlayer(getPos().getX(), getPos().getY(), getPos().getZ(), 10, false).getEntityId(), this.pos.up(), random.nextInt());
                if (world.getTotalWorldTime() % 333 == 0) {
                    if (!world.isRemote) {
                        world.destroyBlock(pos.up(), false);
                        world.spawnEntity(new EntityItem(world, pos.up().getX(), pos.up().getY(), pos.up().getZ(), new ItemStack(ModItems.QuazanScale, random.nextInt(3))));
                        world.spawnEntity(new EntityItem(world, pos.up().getX(), pos.up().getY(), pos.up().getZ(), new ItemStack(ModItems.QuazanScale)));
                        world.destroyBlock(pos.north(), false);
                        world.destroyBlock(pos.south(), false);
                        world.destroyBlock(pos.east(), false);
                        world.destroyBlock(pos.west(), false);
                    }
                    setFullActive(false);
                    setActive(false);
                    world.removeTileEntity(pos);
                    world.destroyBlock(pos, false);
                    world.setBlockToAir(pos);
                    world.setBlockState(pos, ModBlocks.Idol.getDefaultState(), 2);
                }
            }
        }
    }
}	