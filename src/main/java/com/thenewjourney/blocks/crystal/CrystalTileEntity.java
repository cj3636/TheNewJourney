package com.thenewjourney.blocks.crystal;

import com.thenewjourney.power.ModPower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class CrystalTileEntity extends TileEntity implements ITickable {
    public static final Color INVALID_COLOR = null;

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
        AxisAlignedBB aabb = new AxisAlignedBB(getPos(), getPos().add(2, 2, 2));
        return aabb;
    }

    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
        return null;
    }

    @Override
    public void update() {
        this.setGemColour(ModPower.getPowerColor(this.getWorld()));
    }

    private Color gemColour = INVALID_COLOR;  // the RGB colour of the gem

    private final long INVALID_TIME = 0;
    private long lastTime = INVALID_TIME;  // used for animation
    private double lastAngularPosition; // used for animation
}	