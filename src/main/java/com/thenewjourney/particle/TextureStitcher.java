package com.thenewjourney.particle;

import com.cj3636.lib.Ref;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TextureStitcher {
	@SubscribeEvent
	public void stitcherEventPre(TextureStitchEvent.Pre event) {
		ResourceLocation riftTex = new ResourceLocation(Ref.MODID, "particle/RiftParticle");
		event.getMap().registerSprite(riftTex);
		
		ResourceLocation fireTex = new ResourceLocation(Ref.MODID, "particle/FireParticle");
		event.getMap().registerSprite(fireTex);
		
		ResourceLocation florusTex = new ResourceLocation(Ref.MODID, "particle/FlorusParticle");
		event.getMap().registerSprite(florusTex);
		
		ResourceLocation beeTex = new ResourceLocation(Ref.MODID, "particle/BeeParticle");
		event.getMap().registerSprite(beeTex);

		ResourceLocation arcaneTex = new ResourceLocation(Ref.MODID, "particle/ArcaneParticle");
		event.getMap().registerSprite(arcaneTex);

        ResourceLocation florucTex = new ResourceLocation(Ref.MODID, "particle/FireballParticle");
        event.getMap().registerSprite(florucTex);

		ResourceLocation vitaemTex = new ResourceLocation(Ref.MODID, "particle/VitaemParticle");
		event.getMap().registerSprite(vitaemTex);
		
		ResourceLocation bubbleTex = new ResourceLocation(Ref.MODID, "particle/sphere");
		event.getMap().registerSprite(bubbleTex);
    }
}