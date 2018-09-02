package com.thenewjourney.items.register;

import com.cj3636.lib.Ref;
import com.thenewjourney.world.trees.PurviaTreeGen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class ItemRegistry extends Item {

    public ItemRegistry(String unlocalizedName) {
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        PurviaTreeGen genTree = new PurviaTreeGen(true);
        Random random = new Random();
        genTree.generate(world, random, pos);
        return EnumActionResult.PASS;
    }
}
