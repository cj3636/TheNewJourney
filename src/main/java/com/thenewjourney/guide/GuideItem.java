package com.thenewjourney.guide;

import com.cj3636.lib.Ref;
import com.thenewjourney.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class GuideItem extends Item {

    public GuideItem(String unlocalizedName) {
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
    }
    @SideOnly(Side.CLIENT)
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (worldIn.isRemote) {
			Minecraft.getMinecraft().displayGuiScreen(new GuideGui(worldIn));
		}
        playerIn.playSound(ModSounds.BookOpen, 1.0F, 1.0F);
    	return new ActionResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("This GUI only works with a 1080p or higher resolution." + "\n" +
                "Be sure to set your GUI Size to 'Normal' in Settings." + "\n" +
                "If you cannot use this guide visit the TNJ mod page for the wiki." + "\n" +
                "You can also use JEI descriptions to help you.");
    }
}