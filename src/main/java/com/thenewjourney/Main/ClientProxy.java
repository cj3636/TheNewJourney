package com.thenewjourney.Main;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.blocks.crystal.CrystalTileEntity;
import com.thenewjourney.blocks.crystal.CrystalTileEntityRenderer;
import com.thenewjourney.blocks.drawer.DrawerBlock;
import com.thenewjourney.blocks.forge.ForgeBlockTileEntity;
import com.thenewjourney.blocks.forge.ForgeTESR;
import com.thenewjourney.blocks.idol.IdolTileEntity;
import com.thenewjourney.blocks.idol.IdolTileEntityRenderer;
import com.thenewjourney.blocks.infuser.InfuserTileEntity;
import com.thenewjourney.blocks.infuser.InfuserTileEntityRenderer;
import com.thenewjourney.blocks.portal.VisceonFirePortal;
import com.thenewjourney.blocks.portal.VisceonFlorusPortal;
import com.thenewjourney.blocks.provider.CrystalProviderTileEntity;
import com.thenewjourney.blocks.provider.CrystalProviderTileEntityRenderer;
import com.thenewjourney.blocks.reactor.ReactorCore;
import com.thenewjourney.blocks.reactor.ReactorMediumTESR;
import com.thenewjourney.blocks.reactor.ReactorMediumTileEntity;
import com.thenewjourney.compat.baubles.InitItems;
import com.thenewjourney.entity.bluefireball.FlorucFireballEntity;
import com.thenewjourney.entity.bluefireball.FlorucFireballRenderer;
import com.thenewjourney.entity.effectcloud.EffectCloudFlorusEntity;
import com.thenewjourney.entity.effectcloud.EffectCloudFlorusRenderer;
import com.thenewjourney.entity.effectcloud.EffectCloudVitaemEntity;
import com.thenewjourney.entity.effectcloud.EffectCloudVitaemRenderer;
import com.thenewjourney.entity.grenade.GrenadeEntity;
import com.thenewjourney.entity.grenade.GrenadeRenderer;
import com.thenewjourney.entity.king.KingEntity;
import com.thenewjourney.entity.king.KingModel;
import com.thenewjourney.entity.king.KingRenderer;
import com.thenewjourney.entity.lightning.ColoredLightningEntity;
import com.thenewjourney.entity.lightning.ColoredLightningRenderer;
import com.thenewjourney.entity.rift.RiftEntity;
import com.thenewjourney.entity.rift.RiftRenderer;
import com.thenewjourney.entity.vitraecstar.VitraecStarEntity;
import com.thenewjourney.entity.vitraecstar.VitraecStarRenderer;
import com.thenewjourney.fluid.ModFluids;
import com.thenewjourney.handler.GuiOverlayHandler;
import com.thenewjourney.items.ModItems;
import com.thenewjourney.items.tranquilizer.EntityTranquilizerArrow;
import com.thenewjourney.items.tranquilizer.RenderTranquilizerArrow;
import com.thenewjourney.particle.TextureStitcher;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        //Entities
        RenderingRegistry.registerEntityRenderingHandler(KingEntity.class, new IRenderFactory<KingEntity>() {
            @Override
            public Render<? super KingEntity> createRenderFor(RenderManager manager) {
                return new KingRenderer(manager, new KingModel(), 1.0F);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(ColoredLightningEntity.class, new IRenderFactory<ColoredLightningEntity>() {
            @Override
            public Render<? super ColoredLightningEntity> createRenderFor(RenderManager manager) {
                return new ColoredLightningRenderer(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(RiftEntity.class, new IRenderFactory<RiftEntity>() {
            @Override
            public Render<? super RiftEntity> createRenderFor(RenderManager manager) {
                return new RiftRenderer(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityTranquilizerArrow.class, new IRenderFactory<EntityTranquilizerArrow>() {
            @Override
            public Render<? super EntityTranquilizerArrow> createRenderFor(RenderManager manager) {
                return new RenderTranquilizerArrow(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(GrenadeEntity.class, new IRenderFactory<GrenadeEntity>() {
            @Override
            public Render<? super GrenadeEntity> createRenderFor(RenderManager manager) {
                return new GrenadeRenderer(manager, ModItems.ShadowEssence);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(FlorucFireballEntity.class, new IRenderFactory<FlorucFireballEntity>() {
            @Override
            public Render<? super FlorucFireballEntity> createRenderFor(RenderManager manager) {
                return new FlorucFireballRenderer(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(VitraecStarEntity.class, new IRenderFactory<VitraecStarEntity>() {
            @Override
            public Render<? super VitraecStarEntity> createRenderFor(RenderManager manager) {
                return new VitraecStarRenderer(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EffectCloudFlorusEntity.class, new IRenderFactory<EffectCloudFlorusEntity>() {
            @Override
            public Render<? super EffectCloudFlorusEntity> createRenderFor(RenderManager manager) {
                return new EffectCloudFlorusRenderer(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EffectCloudVitaemEntity.class, new IRenderFactory<EffectCloudVitaemEntity>() {
            @Override
            public Render<? super EffectCloudVitaemEntity> createRenderFor(RenderManager manager) {
                return new EffectCloudVitaemRenderer(manager);
            }
        });
        //OBJ
        OBJLoader.INSTANCE.addDomain(Ref.MODID);
        //Custom texture location
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.Sphere), 0, new ModelResourceLocation(Ref.MODID + ":" + VisceonFirePortal.name, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.Spheref), 0, new ModelResourceLocation(Ref.MODID + ":" + VisceonFlorusPortal.name, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.ReactorCore), 0, new ModelResourceLocation(Ref.MODID + ":" + ReactorCore.name, "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.Drawer), 0, new ModelResourceLocation(Ref.MODID + ":" + DrawerBlock.name, "inventory"));
        //TESR
        //Fluid Model
        ModFluids.INSTANCE.registerModel();
        //Particle Stitcher
        MinecraftForge.EVENT_BUS.register(new TextureStitcher());
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        //Block and Item Bakery
        //BlockRenderer.registerBlockRender();
        //ItemRenderer.RegisterItemRenderer();
        //Gui Overlay
        MinecraftForge.EVENT_BUS.register(new GuiOverlayHandler());
        ClientRegistry.bindTileEntitySpecialRenderer(CrystalTileEntity.class, new CrystalTileEntityRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(InfuserTileEntity.class, new InfuserTileEntityRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(ForgeBlockTileEntity.class, new ForgeTESR());
        ClientRegistry.bindTileEntitySpecialRenderer(IdolTileEntity.class, new IdolTileEntityRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(CrystalProviderTileEntity.class, new CrystalProviderTileEntityRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(ReactorMediumTileEntity.class, new ReactorMediumTESR());
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Block block : ModBlocks.Block_List) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
        }
        for (Item item : ModItems.Item_List) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
        if (Loader.isModLoaded("baubles")) {
            for (Item item : InitItems.Bauble_Item_List) {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
            }
        }
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModFluids.InversionSerum.getBlock()), 0, new ModelResourceLocation(ModFluids.InversionSerum.getBlock().getRegistryName(), "inventory"));
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
}
