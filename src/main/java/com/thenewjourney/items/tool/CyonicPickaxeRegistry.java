package com.thenewjourney.items.tool;

import com.cj3636.lib.Ref;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class CyonicPickaxeRegistry extends Item {
    public CyonicPickaxeRegistry(String unlocalizedName) {
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
        this.setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Block blockAt = worldIn.getBlockState(pos).getBlock();
        if (!worldIn.isRemote) {
            EntityItem itemDrop = new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(blockAt.getDefaultState().getBlock(), 1, 0));
            worldIn.spawnEntity(itemDrop);
        }
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
        return EnumActionResult.PASS;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        return 0f;
    }

    @Override
    public boolean canHarvestBlock(IBlockState blockIn) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("This item will destroy any and all blocks." + "\n" +
                "FORGE is annoying, you must Right Click to this." + "\n" +
                "I swear I am not lazy, this is temporary." + "\n" +
                "\u00A75" + "Enjoy!" + "\u00A7r");
    }
}
