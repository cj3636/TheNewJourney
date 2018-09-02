package com.thenewjourney.fluid;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;


public class ModFluids {

    public static final Fluid InversionSerum;

    public static final Set<IFluidBlock> MOD_FLUID_BLOCKS = new HashSet<>();
    public static final Set<Fluid> FLUIDS = new HashSet<>();
    public static final ModFluids INSTANCE = new ModFluids();

    static {
        InversionSerum = createFluid("InversionSerum", false,
                fluid -> fluid.setLuminosity(300).setDensity(1).setViscosity(0),
                fluid -> new InversionSerum(fluid, new MaterialLiquid(MapColor.PURPLE)));
    }

    public static void mainRegistry() {
    }

    private static <T extends Block & IFluidBlock> Fluid createFluid(String name, boolean hasFlowIcon, Consumer<Fluid> fluidPropertyApplier, Function<Fluid, T> blockFactory) {
        final String texturePrefix = Ref.MODID + ":" + "blocks/fluid_";

        final ResourceLocation still = new ResourceLocation(texturePrefix + name + "_still");
        final ResourceLocation flowing = hasFlowIcon ? new ResourceLocation(texturePrefix + name + "_flow") : still;

        Fluid fluid = new Fluid(name, still, flowing);
        final boolean useOwnFluid = FluidRegistry.registerFluid(fluid);

        if (useOwnFluid) {
            fluidPropertyApplier.accept(fluid);
            registerFluidBlock(blockFactory.apply(fluid));
        } else {
            fluid = FluidRegistry.getFluid(name);
        }
        FLUIDS.add(fluid);
        return fluid;
    }

    private static <T extends Block & IFluidBlock> T registerFluidBlock(T block) {
        block.setRegistryName(block.getFluid().getName());
        block.setUnlocalizedName(Ref.MODID + ":" + block.getFluid().getUnlocalizedName());

        ModBlocks.registerBlock(block);
        MOD_FLUID_BLOCKS.add(block);
        return block;
    }

    public static void registerFluidContainers() {
        for (final Fluid fluid : FLUIDS) {
            registerBucket(fluid);
        }
    }

    private static void registerBucket(Fluid fluid) {
        FluidRegistry.addBucketForFluid(fluid);
    }

    public void registerModel() {
        registerFluidModels();
    }

    private void registerFluidModels() {
        ModFluids.MOD_FLUID_BLOCKS.forEach(this::registerFluidModel);
    }

    private static final String FLUID_MODEL_PATH = Ref.MODID + ":" + "fluid";

    private void registerFluidModel(IFluidBlock fluidBlock) {
        final Item item = Item.getItemFromBlock((Block) fluidBlock);
        assert item != null;

        ModelBakery.registerItemVariants(item);

        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(FLUID_MODEL_PATH, fluidBlock.getFluid().getName());

        ModelLoader.setCustomMeshDefinition(item, MeshDefinitionFix.create(stack -> modelResourceLocation));

        ModelLoader.setCustomStateMapper((Block) fluidBlock, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState p_178132_1_) {
                return modelResourceLocation;
            }
        });
    }

    @SideOnly(Side.CLIENT)
    interface MeshDefinitionFix extends ItemMeshDefinition {
        ModelResourceLocation getLocation(ItemStack stack);

        static ItemMeshDefinition create(MeshDefinitionFix lambda) {
            return lambda;
        }

        default ModelResourceLocation getModelLocation(ItemStack stack) {
            return getLocation(stack);
        }
    }
}