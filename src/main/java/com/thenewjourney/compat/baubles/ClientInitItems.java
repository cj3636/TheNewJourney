package com.thenewjourney.compat.baubles;

import com.cj3636.lib.Config;
import com.cj3636.lib.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientInitItems {
	@Optional.Method(modid = "Baubles")
	@SideOnly(Side.CLIENT)
	public static void reg2() {
		reg(InitItems.AquisBelt);
		reg(InitItems.FireBelt);
		reg(InitItems.DistortionBelt);
		reg(InitItems.EmeraldBelt);
		reg(InitItems.ShadowBelt);
		reg(InitItems.GoldRing);
		reg(InitItems.BeltOfTranscendence);
		if (Config.opTools == true) {
			reg(InitItems.FlyRing);
			reg(InitItems.RingOfFlight);
		}
	}
	public static void reg(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Ref.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
