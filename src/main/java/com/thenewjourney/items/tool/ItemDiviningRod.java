package com.thenewjourney.items.tool;

import com.cj3636.lib.Ref;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.Set;

public class ItemDiviningRod extends ItemTool {
    private static Set effectiveBlocks = Sets.newHashSet(Blocks.WOOL);

    public ItemDiviningRod(String unlocalizedName, ToolMaterial material, int maxDamage) {
        super(material, effectiveBlocks);

        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
        this.setMaxDamage(maxDamage);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack) {
        return true;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        return effectiveBlocks.contains(state) ? this.efficiency : 15.0F;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemStackIn = playerIn.getHeldItemMainhand();
        runQuarry(worldIn, playerIn.getPosition(), playerIn);
        if (playerIn.getHeldItemOffhand() != null) {
            itemStackIn.damageItem(1, playerIn);
        }

        return new ActionResult(EnumActionResult.PASS, itemStackIn);
    }

    private void runQuarry(World worldIn, BlockPos pos, EntityPlayer player) {
        BlockPos start = pos.add(-4, -1, -4);
        for (int i = 0; i < 64; i++) {
            mineToBedrock(worldIn, new BlockPos(start.getX() + (i % 8), start.getY(), start.getZ() + (i / 8)), player);
        }
    }

    private void mineToBedrock(World worldIn, BlockPos pos, EntityPlayer player) {
        for (int i = 0; i < 256; i++) {
            BlockPos posAt = pos.down(i);
            Block blockCheck = worldIn.getBlockState(posAt).getBlock();
            if (checkForOre(blockCheck, player)) {
                if (!worldIn.isRemote) {
                    player.sendMessage(new TextComponentTranslation("\u00A75" + "There is a matching ore at: " + posAt));
                }
            }
            if (posAt.getY() == 0) {
                break;
            }
        }

    }

    private boolean checkForOre(Block blockCheck, EntityPlayer player) {
        if (player.getHeldItemOffhand() != null) {
            if (player.getHeldItemOffhand().getItem().equals(Item.getItemFromBlock(blockCheck))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        return true;
    }
}
