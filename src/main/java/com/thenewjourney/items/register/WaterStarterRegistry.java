package com.thenewjourney.items.register;

import com.cj3636.lib.Ref;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFireball;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WaterStarterRegistry extends ItemFireball {

    public WaterStarterRegistry(String unlocalizedName) {
        super();

        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return EnumActionResult.SUCCESS;
    }
}