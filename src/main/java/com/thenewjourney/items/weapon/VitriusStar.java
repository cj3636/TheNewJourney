package com.thenewjourney.items.weapon;

import com.thenewjourney.entity.vitraecstar.VitraecStarEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Created by cj3636 on 4/14/2017.
 */
public class VitriusStar extends WeaponRegistry {

    public VitriusStar(String unlocalizedName, ToolMaterial material, float damage, float speed, int durability) {
        super(unlocalizedName, material, damage, speed, durability);
    }

    protected void onRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
        BlockPos pos = playerIn.getPosition();
        if (!playerIn.capabilities.isCreativeMode) {
            stack.damageItem(1, playerIn);
        }
        final Vec3d vec31 = playerIn.getLookVec();
        int i = 75;
        BlockPos blockpos = new BlockPos(vec31.x * i, vec31.y * i, vec31.z * i);
        if (!worldIn.isRemote) {
            VitraecStarEntity entitysmallfireball = new VitraecStarEntity(worldIn, playerIn, blockpos.getX(), blockpos.getY(), blockpos.getZ());
            entitysmallfireball.posY = pos.getY() + 1;
            worldIn.spawnEntity(entitysmallfireball);
        }
    }
}
