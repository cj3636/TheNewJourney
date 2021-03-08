package com.thenewjourney.blocks.pillar;

import com.cj3636.lib.Ref;
import com.thenewjourney.Main.TheNewJourneyMod;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.items.ModItems;
import com.thenewjourney.particle.SmokingArcaneParticle;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by cj3636 on 1/29/2017.
 */
public class ArcanePillarBlock extends BlockContainer {

    public ArcanePillarBlock(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);
        this.setCreativeTab(Ref.CTAB);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }

    public static void dropItem(World worldIn, BlockPos pos) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        ArcanePillarTileEntity arcaneTileEntity = (ArcanePillarTileEntity) tileentity;

        ItemStack itemstack = arcaneTileEntity.getStackInSlot(0);
        if (!itemstack.isEmpty()) {
            arcaneTileEntity.setInventorySlotContents(0, ItemStack.EMPTY);
            ItemStack itemstack1 = itemstack.copy();
            EntityItem entityitem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemstack1);
            entityitem.setDefaultPickupDelay();
            worldIn.setBlockToAir(pos);
            worldIn.spawnEntity(entityitem);
            arcaneTileEntity.markDirty();
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = playerIn.getHeldItemMainhand();
        if (heldItem.isEmpty()) {
            if (worldIn.getTileEntity(pos) instanceof ArcanePillarTileEntity) {
                ArcanePillarTileEntity thisTileEnity = (ArcanePillarTileEntity) worldIn.getTileEntity(pos);
                if (!thisTileEnity.getStackInSlot(0).isEmpty()) {
                    playerIn.setHeldItem(hand, thisTileEnity.getStackInSlot(0));
                    thisTileEnity.setInventorySlotContents(0, ItemStack.EMPTY);
                    return true;
                }
            }
        } else if (!heldItem.getItem().equals(ModItems.DistortionWand)) {
            if (worldIn.getTileEntity(pos) instanceof ArcanePillarTileEntity) {
                ArcanePillarTileEntity thisTileEnity = (ArcanePillarTileEntity) worldIn.getTileEntity(pos);
                if (thisTileEnity.getStackInSlot(0) == ItemStack.EMPTY) {
                    thisTileEnity.setInventorySlotContents(0, heldItem.copy());
                    if (!playerIn.capabilities.isCreativeMode) {
                        heldItem.shrink(1);
                    }
                    return true;
                }
                ItemStack stack1 = thisTileEnity.getStackInSlot(0).copy();
                stack1.setCount(1);
                ItemStack stack2 = heldItem.copy();
                stack2.setCount(1);
                if (stack1.equals(stack2)) {
                    thisTileEnity.setInventorySlotContents(0, ItemStack.EMPTY);
                    if (!playerIn.capabilities.isCreativeMode) {
                        heldItem.setCount(heldItem.getCount() + 1);
                    }
                    return true;
                }
            }
        }

        if (heldItem.getItem().equals(ModItems.DistortionWand)) {
            ArrayList<BlockPos> pillarList = new ArrayList<BlockPos>();
            BlockPos[] posList = {pos.north().north().west().west(), pos.north().north().east().east(),
                    pos.south().south().east().east(), pos.south().south().west().west()};
            for (BlockPos posAt : posList) {
                IBlockState stateCheck = worldIn.getBlockState(posAt);
                if (stateCheck.equals(state)) {
                    pillarList.add(posAt);
                }
            }
            ArrayList<BlockPos> crystalList = new ArrayList<BlockPos>();
            BlockPos[] posList2 = {pos.down().north().north().west().west(), pos.down().north().north().east().east(),
                    pos.down().south().south().east().east(), pos.down().south().south().west().west()};
            for (BlockPos posAt : posList2) {
                IBlockState stateCheck = worldIn.getBlockState(posAt);
                if (stateCheck.equals(ModBlocks.Crystal.getDefaultState())) {
                    crystalList.add(posAt);
                }
            }
            if (pillarList.size() == 4 && crystalList.size() == 4
                    && worldIn.getBlockState(pos.down()).equals(ModBlocks.SentinelSpawn.getDefaultState())) {
                runInfusion(worldIn, pillarList, pos, playerIn);
                return true;
            }
        } else {
            if (!worldIn.isRemote) {
                playerIn.openGui(TheNewJourneyMod.instance, ArcanePillarGUIRegistry.getGuiID(), worldIn, pos.getX(),
                        pos.getY(), pos.getZ());
            }
        }
        return true;
    }

    private void runInfusion(World worldIn, ArrayList<BlockPos> pos, BlockPos posIn, EntityPlayer playerIn) {
        if (worldIn.getTileEntity(pos.get(0)) instanceof ArcanePillarTileEntity
                && worldIn.getTileEntity(pos.get(1)) instanceof ArcanePillarTileEntity
                && worldIn.getTileEntity(pos.get(2)) instanceof ArcanePillarTileEntity
                && worldIn.getTileEntity(pos.get(3)) instanceof ArcanePillarTileEntity
                && worldIn.getTileEntity(posIn) instanceof ArcanePillarTileEntity) {
            System.out.println("1");
            ArcanePillarTileEntity tileEntity1 = (ArcanePillarTileEntity) worldIn.getTileEntity(pos.get(0));
            ArcanePillarTileEntity tileEntity2 = (ArcanePillarTileEntity) worldIn.getTileEntity(pos.get(1));
            ArcanePillarTileEntity tileEntity3 = (ArcanePillarTileEntity) worldIn.getTileEntity(pos.get(2));
            ArcanePillarTileEntity tileEntity4 = (ArcanePillarTileEntity) worldIn.getTileEntity(pos.get(3));
            ArcanePillarTileEntity thisTileEntity = (ArcanePillarTileEntity) worldIn.getTileEntity(posIn);

            ItemStack stack1 = tileEntity1.getStackInSlot(0);
            ItemStack stack2 = tileEntity2.getStackInSlot(0);
            ItemStack stack3 = tileEntity3.getStackInSlot(0);
            ItemStack stack4 = tileEntity4.getStackInSlot(0);
            ItemStack stack5 = thisTileEntity.getStackInSlot(0);


            if (stack1 != ItemStack.EMPTY && stack2 != ItemStack.EMPTY && stack3 != ItemStack.EMPTY && stack4 != ItemStack.EMPTY && stack5 != ItemStack.EMPTY) {
                System.out.println("2");
                int s1 = stack1.getCount();
                int s2 = stack2.getCount();
                int s3 = stack3.getCount();
                int s4 = stack4.getCount();
                int s5 = stack5.getCount();
                Item t1 = stack1.getItem();
                Item t2 = stack2.getItem();
                Item t3 = stack3.getItem();
                Item t4 = stack4.getItem();
                Item t5 = stack5.getItem();
                List<Item> stackList = new ArrayList<Item>(Arrays.asList(t1, t2, t3, t4, t5));
                List<Item> checkList = new ArrayList<Item>(
                        Arrays.asList(Item.getItemFromBlock(ModBlocks.Lamp), ModItems.CrystalBinder,
                                ModItems.ArchiumIngot, ModItems.VitaemIngot, ModItems.FloriumIngot));

                if (stackList.containsAll(checkList)) {
                    System.out.println("3");
                    if ((s1 == s2) && (s2 == s3) && (s3 == s4) && (s4 == s5)) {
                        thisTileEntity.setInventorySlotContents(0, new ItemStack(ModItems.UpgradeEight, s1));
                        thisTileEntity.markDirty();

                        worldIn.playSound(playerIn, posIn, SoundEvents.BLOCK_END_GATEWAY_SPAWN,
                                SoundCategory.AMBIENT, 100.0F, 0.2F);
                        genParticle(worldIn, posIn);
                        if (!playerIn.capabilities.isCreativeMode) {
                            tileEntity1.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity1.markDirty();
                            tileEntity2.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity2.markDirty();
                            tileEntity3.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity3.markDirty();
                            tileEntity4.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity4.markDirty();
                        }
                    }
                }
                List<Item> checkList5 = new ArrayList<Item>(Arrays.asList(ModItems.Carbon, ModItems.RubyGem, ModItems.RubyGem, ModItems.SteelRod, ModItems.SpatiumPowder));

                if (stackList.containsAll(checkList5)) {
                    if ((s1 == s2) && (s2 == s3) && (s3 == s4) && (s4 == s5)) {
                        thisTileEntity.setInventorySlotContents(0, new ItemStack(ModItems.DiviningRod));
                        thisTileEntity.markDirty();

                        worldIn.playSound(playerIn, posIn, SoundEvents.BLOCK_END_GATEWAY_SPAWN,
                                SoundCategory.AMBIENT, 100.0F, 0.2F);
                        genParticle(worldIn, posIn);
                        if (!playerIn.capabilities.isCreativeMode) {
                            tileEntity1.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity1.markDirty();
                            tileEntity2.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity2.markDirty();
                            tileEntity3.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity3.markDirty();
                            tileEntity4.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity4.markDirty();
                        }
                    }
                }
                List<Item> checkList2 = new ArrayList<Item>(Arrays.asList(ModItems.AquisIngot, ModItems.AquisIngot, ModItems.AquisIngot, ModItems.AquisIngot, Item.getItemFromBlock(ModBlocks.WarpStone)));
                if (stackList.containsAll(checkList2)) {
                    if ((s1 == s2) && (s2 == s3) && (s3 == s4) && (s4 == s5)) {
                        thisTileEntity.setInventorySlotContents(0,
                                new ItemStack(Item.getItemFromBlock(ModBlocks.Substrate)));
                        thisTileEntity.markDirty();

                        worldIn.playSound(playerIn, posIn, SoundEvents.BLOCK_END_GATEWAY_SPAWN, SoundCategory.AMBIENT,
                                100.0F, 0.2F);
                        genParticle(worldIn, posIn);
                        if (!playerIn.capabilities.isCreativeMode) {
                            tileEntity1.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity1.markDirty();
                            tileEntity2.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity2.markDirty();
                            tileEntity3.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity3.markDirty();
                            tileEntity4.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity4.markDirty();
                        }
                    }
                }
                List<Item> checkList4 = new ArrayList<Item>(Arrays.asList(
                        Item.getItemFromBlock(ModBlocks.WarpStone), Item.getItemFromBlock(ModBlocks.WarpStone),
                        Item.getItemFromBlock(ModBlocks.WarpStone), Item.getItemFromBlock(ModBlocks.WarpStone),
                        ModItems.ShadowCrystal));

                if (stackList.containsAll(checkList4)) {
                    if ((s1 == s2) && (s2 == s3) && (s3 == s4) && (s4 == s5)) {
                        thisTileEntity.setInventorySlotContents(0, new ItemStack(ModBlocks.VisceonCore));
                        if (!worldIn.isRemote) {
                            EntityItem itemDrop = new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(ModBlocks.VisceonCore, 3));
                            worldIn.spawnEntity(itemDrop);
                        }
                        thisTileEntity.markDirty();

                        worldIn.playSound(playerIn, posIn, SoundEvents.BLOCK_END_GATEWAY_SPAWN,
                                SoundCategory.AMBIENT, 100.0F, 0.2F);
                        genParticle(worldIn, posIn);
                        if (!playerIn.capabilities.isCreativeMode) {
                            tileEntity1.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity1.markDirty();
                            tileEntity2.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity2.markDirty();
                            tileEntity3.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity3.markDirty();
                            tileEntity4.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity4.markDirty();
                        }
                    }
                }
                List<Item> checkList3 = new ArrayList<Item>(Arrays.asList(
                        Item.getItemFromBlock(ModBlocks.Frame), Item.getItemFromBlock(ModBlocks.Frame),
                        Item.getItemFromBlock(ModBlocks.Frame), Item.getItemFromBlock(ModBlocks.Frame),
                        Item.getItemFromBlock(ModBlocks.VisceonCore)));

                if (stackList.containsAll(checkList3)) {
                    if ((s1 == s2) && (s2 == s3) && (s3 == s4) && (s4 == s5)) {
                        thisTileEntity.setInventorySlotContents(0, new ItemStack(ModBlocks.Quarry));
                        thisTileEntity.markDirty();

                        worldIn.playSound(playerIn, posIn, SoundEvents.BLOCK_END_GATEWAY_SPAWN,
                                SoundCategory.AMBIENT, 100.0F, 0.2F);
                        genParticle(worldIn, posIn);
                        if (!playerIn.capabilities.isCreativeMode) {
                            tileEntity1.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity1.markDirty();
                            tileEntity2.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity2.markDirty();
                            tileEntity3.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity3.markDirty();
                            tileEntity4.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity4.markDirty();
                        }
                    }
                }
            }
            if (stack1 != ItemStack.EMPTY && stack2 != ItemStack.EMPTY && stack3 != ItemStack.EMPTY && stack4 != ItemStack.EMPTY) {
                Item t1 = stack1.getItem();
                Item t2 = stack2.getItem();
                Item t3 = stack3.getItem();
                Item t4 = stack4.getItem();
                List<Item> stackList = new ArrayList<Item>(Arrays.asList(t1, t2, t3, t4));
                List<Item> checkList = new ArrayList<Item>(Arrays.asList(ModItems.AncientIngot, ModItems.BindingPowder, ModItems.BindingPowder, ModItems.FireGem));
                if (stackList.containsAll(checkList)) {
                    int s1 = stack1.getCount();
                    int s2 = stack2.getCount();
                    int s3 = stack3.getCount();
                    int s4 = stack4.getCount();
                    if ((s1 == s2) && (s2 == s3) && (s3 == s4)) {
                        thisTileEntity.setInventorySlotContents(0, new ItemStack(ModItems.ArchaicOrb, s1));
                        thisTileEntity.markDirty();

                        worldIn.playSound(playerIn, posIn, SoundEvents.BLOCK_END_GATEWAY_SPAWN, SoundCategory.AMBIENT,
                                100.0F, 0.2F);
                        genParticle(worldIn, posIn);
                        if (!playerIn.capabilities.isCreativeMode) {
                            tileEntity1.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity1.markDirty();
                            tileEntity2.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity2.markDirty();
                            tileEntity3.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity3.markDirty();
                            tileEntity4.setInventorySlotContents(0, ItemStack.EMPTY);
                            tileEntity4.markDirty();
                        }
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    private void genParticle(World worldIn, BlockPos posIn) {
        Random rand = new Random();
        double d0 = (double) posIn.getX() + 0.5D;
        double d1 = (double) posIn.getY() + rand.nextDouble() * 6.0D / 16.0D;
        double d2 = (double) posIn.getZ() + 0.5D;
        double d3 = 0.52D;
        double d4 = rand.nextDouble() * 0.6D - 0.3D;
        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);

        SmokingArcaneParticle ArcaneParticle = new SmokingArcaneParticle(worldIn, d0 + d3, d1, d2 + d4, 0.0D, 0.0D,
                0.0D, 2.5F);
        SmokingArcaneParticle ArcaneParticle1 = new SmokingArcaneParticle(worldIn, d0 + d4, d1, d2 - d3, 0.0D, 0.0D,
                0.0D, 2.5F);
        SmokingArcaneParticle ArcaneParticle01 = new SmokingArcaneParticle(worldIn, d0 + d4, d1, d2 + d3, 0.0D, 0.0D,
                0.0D, 2.5F);
        Minecraft.getMinecraft().effectRenderer.addEffect(ArcaneParticle);
        Minecraft.getMinecraft().effectRenderer.addEffect(ArcaneParticle1);
        Minecraft.getMinecraft().effectRenderer.addEffect(ArcaneParticle01);

        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);

        SmokingArcaneParticle ArcaneParticle2 = new SmokingArcaneParticle(worldIn, d0 - d3, d1, d2 + d4, 0.0D, 0.0D,
                0.0D, 2.5F);
        SmokingArcaneParticle ArcaneParticle3 = new SmokingArcaneParticle(worldIn, d0 + d4, d1, d2 - d3, 0.0D, 0.0D,
                0.0D, 2.5F);
        SmokingArcaneParticle ArcaneParticle4 = new SmokingArcaneParticle(worldIn, d0 + d4, d1, d2 + d3, 0.0D, 0.0D,
                0.0D, 2.5F);
        Minecraft.getMinecraft().effectRenderer.addEffect(ArcaneParticle2);
        Minecraft.getMinecraft().effectRenderer.addEffect(ArcaneParticle3);
        Minecraft.getMinecraft().effectRenderer.addEffect(ArcaneParticle4);

        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D);

        SmokingArcaneParticle ArcaneParticle5 = new SmokingArcaneParticle(worldIn, d0 - d3, d1, d2 + d4, 0.0D, 0.0D,
                0.0D, 2.5F);
        SmokingArcaneParticle ArcaneParticle6 = new SmokingArcaneParticle(worldIn, d0 + d3, d1, d2 + d4, 0.0D, 0.0D,
                0.0D, 2.5F);
        SmokingArcaneParticle ArcaneParticle7 = new SmokingArcaneParticle(worldIn, d0 + d4, d1, d2 + d3, 0.0D, 0.0D,
                0.0D, 2.5F);
        Minecraft.getMinecraft().effectRenderer.addEffect(ArcaneParticle5);
        Minecraft.getMinecraft().effectRenderer.addEffect(ArcaneParticle6);
        Minecraft.getMinecraft().effectRenderer.addEffect(ArcaneParticle7);

        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D);

        SmokingArcaneParticle ArcaneParticle8 = new SmokingArcaneParticle(worldIn, d0 - d3, d1, d2 + d4, 0.0D, 0.0D,
                0.0D, 2.5F);
        SmokingArcaneParticle ArcaneParticle9 = new SmokingArcaneParticle(worldIn, d0 + d3, d1, d2 + d4, 0.0D, 0.0D,
                0.0D, 2.5F);
        SmokingArcaneParticle ArcaneParticle0 = new SmokingArcaneParticle(worldIn, d0 + d4, d1, d2 - d3, 0.0D, 0.0D,
                0.0D, 2.5F);
        Minecraft.getMinecraft().effectRenderer.addEffect(ArcaneParticle8);
        Minecraft.getMinecraft().effectRenderer.addEffect(ArcaneParticle9);
        Minecraft.getMinecraft().effectRenderer.addEffect(ArcaneParticle0);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (worldIn.getTileEntity(pos) instanceof ArcanePillarTileEntity) {
            ArcanePillarTileEntity thisTileEnity = (ArcanePillarTileEntity) worldIn.getTileEntity(pos);
            thisTileEnity.markDirty();
        }
        dropItem(worldIn, pos);
        worldIn.removeTileEntity(pos);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new ArcanePillarTileEntity();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
                                        EnumFacing side) {
        return true;
    }
}