package com.thenewjourney.items.staff;

import com.cj3636.lib.Ref;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;

public class StaffRegistry extends ItemTool {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[]{});

    public StaffRegistry(String unlocalizedName, ToolMaterial material, int maxDamage) {
        super(material, EFFECTIVE_ON);
        this.setMaxStackSize(1);
        this.setMaxDamage(maxDamage);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
    }

    //On blocks only
    protected void onRightBlock(World worldIn, EntityPlayer playerIn, ItemStack stack, BlockPos pos) {
    }

    protected void onShiftRightBlock(World worldIn, EntityPlayer playerIn, ItemStack stack, BlockPos pos) {
    }

    protected void onCtrlRightBlock(World worldIn, EntityPlayer playerIn, ItemStack stack, BlockPos pos) {
    }

    protected void onAltRightBlock(World worldIn, EntityPlayer playerIn, ItemStack stack, BlockPos pos) {
    }

    //Includes Facing Variable
    protected void onRightBlockF(World worldIn, EntityPlayer playerIn, ItemStack stack, BlockPos pos, EnumFacing facing) {
    }

    //In air AND on blocks (Can be 'if'ed out)
    protected void onRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
    }

    protected void onShiftRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
    }

    protected void onCtrlRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
    }

    protected void onAltRight(World worldIn, EntityPlayer playerIn, ItemStack stack) {
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.getHeldItemMainhand();
        if (GuiScreen.isAltKeyDown()) {
            onAltRightBlock(worldIn, playerIn, stack, pos);
        } else if (GuiScreen.isCtrlKeyDown()) {
            onCtrlRightBlock(worldIn, playerIn, stack, pos);
        } else if (GuiScreen.isShiftKeyDown()) {
            onShiftRightBlock(worldIn, playerIn, stack, pos);
        } else {
            onRightBlock(worldIn, playerIn, stack, pos);
            onRightBlockF(worldIn, playerIn, stack, pos, facing);
        }
        return EnumActionResult.PASS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemStackIn = playerIn.getHeldItemMainhand();
        if (GuiScreen.isAltKeyDown()) {
            onAltRight(worldIn, playerIn, itemStackIn);
        } else if (GuiScreen.isCtrlKeyDown()) {
            onCtrlRight(worldIn, playerIn, itemStackIn);
        } else if (GuiScreen.isShiftKeyDown()) {
            onShiftRight(worldIn, playerIn, itemStackIn);
        } else {
            onRight(worldIn, playerIn, itemStackIn);
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
    }
}
