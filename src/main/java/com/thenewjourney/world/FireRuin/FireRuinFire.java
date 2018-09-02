package com.thenewjourney.world.FireRuin;

import com.cj3636.lib.Ref;
import com.thenewjourney.blocks.ModBlocks;
import com.thenewjourney.blocks.portal.VisceonFirePortal;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class FireRuinFire extends WorldGenerator {

    private int xPos;
    private int yPos;
    private int zPos;

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        int i = rand.nextInt(1);

        if (i == 0) {
            generate_r0(world, rand, pos);
        }

        return true;

    }

    public boolean generate_r0(World world, Random rand, BlockPos pos) {

        // Get the reference to the block to place
        Block blk = ModBlocks.BurntStone;
        Block blk1 = ModBlocks.DistortedStone;
        Block blk2 = Blocks.AIR;
        Block blk3 = Blocks.AIR;
        Block blk4 = Blocks.AIR;
        Block blk5 = ModBlocks.Sphere;
        Block blk6 = Blocks.AIR;
        Block blk7 = Blocks.AIR;
        // Get the default state(basically metadata 0)
        IBlockState stateBurnt = blk.getDefaultState();
        IBlockState stateDistorted = blk1.getDefaultState();
        IBlockState stateFireO = blk2.getDefaultState();
        IBlockState stateCryptic = blk3.getDefaultState();
        IBlockState stateDistortedB = blk4.getDefaultState();
        IBlockState stateFire = blk5.getDefaultState().withProperty(VisceonFirePortal.AXIS, EnumFacing.Axis.Y);
        IBlockState stateFireM = blk6.getDefaultState();
        IBlockState stateChest = blk7.getDefaultState();
        //this is my ridiculous math to convert old coords to new
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        xPos = x;
        yPos = y;
        zPos = z;
        BlockPos pos1 = new BlockPos(x + 3, y + -1, z + 0);
        BlockPos pos2 = new BlockPos(x + 4, y + -1, z + 0);
        BlockPos pos3 = new BlockPos(x + 5, y + -1, z + 0);
        BlockPos pos4 = new BlockPos(x + 2, y + -1, z + 1);
        BlockPos pos5 = new BlockPos(x + 3, y + -1, z + 1);
        BlockPos pos6 = new BlockPos(x + 4, y + -1, z + 1);
        BlockPos pos7 = new BlockPos(x + 5, y + -1, z + 1);
        BlockPos pos8 = new BlockPos(x + 6, y + -1, z + 1);
        BlockPos pos9 = new BlockPos(x + 1, y + -1, z + 2);
        BlockPos pos10 = new BlockPos(x + 2, y + -1, z + 2);
        BlockPos pos11 = new BlockPos(x + 3, y + -1, z + 2);
        BlockPos pos12 = new BlockPos(x + 4, y + -1, z + 2);
        BlockPos pos13 = new BlockPos(x + 5, y + -1, z + 2);
        BlockPos pos14 = new BlockPos(x + 6, y + -1, z + 2);
        BlockPos pos15 = new BlockPos(x + 7, y + -1, z + 2);
        BlockPos pos16 = new BlockPos(x + 0, y + -1, z + 3);
        BlockPos pos17 = new BlockPos(x + 1, y + -1, z + 3);
        BlockPos pos18 = new BlockPos(x + 2, y + -1, z + 3);
        BlockPos pos19 = new BlockPos(x + 3, y + -1, z + 3);
        BlockPos pos20 = new BlockPos(x + 4, y + -1, z + 3);
        BlockPos pos21 = new BlockPos(x + 5, y + -1, z + 3);
        BlockPos pos22 = new BlockPos(x + 6, y + -1, z + 3);
        BlockPos pos23 = new BlockPos(x + 7, y + -1, z + 3);
        BlockPos pos24 = new BlockPos(x + 8, y + -1, z + 3);
        BlockPos pos25 = new BlockPos(x + 0, y + -1, z + 4);
        BlockPos pos26 = new BlockPos(x + 1, y + -1, z + 4);
        BlockPos pos27 = new BlockPos(x + 2, y + -1, z + 4);
        BlockPos pos28 = new BlockPos(x + 3, y + -1, z + 4);
        BlockPos pos29 = new BlockPos(x + 4, y + -1, z + 4);
        BlockPos pos30 = new BlockPos(x + 5, y + -1, z + 4);
        BlockPos pos31 = new BlockPos(x + 6, y + -1, z + 4);
        BlockPos pos32 = new BlockPos(x + 7, y + -1, z + 4);
        BlockPos pos33 = new BlockPos(x + 8, y + -1, z + 4);
        BlockPos pos34 = new BlockPos(x + 0, y + -1, z + 5);
        BlockPos pos35 = new BlockPos(x + 1, y + -1, z + 5);
        BlockPos pos36 = new BlockPos(x + 2, y + -1, z + 5);
        BlockPos pos37 = new BlockPos(x + 3, y + -1, z + 5);
        BlockPos pos38 = new BlockPos(x + 4, y + -1, z + 5);
        BlockPos pos39 = new BlockPos(x + 5, y + -1, z + 5);
        BlockPos pos40 = new BlockPos(x + 6, y + -1, z + 5);
        BlockPos pos41 = new BlockPos(x + 7, y + -1, z + 5);
        BlockPos pos42 = new BlockPos(x + 8, y + -1, z + 5);
        BlockPos pos43 = new BlockPos(x + 1, y + -1, z + 6);
        BlockPos pos44 = new BlockPos(x + 2, y + -1, z + 6);
        BlockPos pos45 = new BlockPos(x + 3, y + -1, z + 6);
        BlockPos pos46 = new BlockPos(x + 4, y + -1, z + 6);
        BlockPos pos47 = new BlockPos(x + 5, y + -1, z + 6);
        BlockPos pos48 = new BlockPos(x + 6, y + -1, z + 6);
        BlockPos pos49 = new BlockPos(x + 7, y + -1, z + 6);
        BlockPos pos50 = new BlockPos(x + 2, y + -1, z + 7);
        BlockPos pos51 = new BlockPos(x + 3, y + -1, z + 7);
        BlockPos pos52 = new BlockPos(x + 4, y + -1, z + 7);
        BlockPos pos53 = new BlockPos(x + 5, y + -1, z + 7);
        BlockPos pos54 = new BlockPos(x + 6, y + -1, z + 7);
        BlockPos pos55 = new BlockPos(x + 3, y + -1, z + 8);
        BlockPos pos56 = new BlockPos(x + 4, y + -1, z + 8);
        BlockPos pos57 = new BlockPos(x + 5, y + -1, z + 8);
        BlockPos pos58 = new BlockPos(x + 2, y + 0, z + 2);
        BlockPos pos59 = new BlockPos(x + 6, y + 0, z + 2);
        BlockPos pos60 = new BlockPos(x + 3, y + 0, z + 3);
        BlockPos pos61 = new BlockPos(x + 4, y + 0, z + 3);
        BlockPos pos62 = new BlockPos(x + 5, y + 0, z + 3);
        BlockPos pos63 = new BlockPos(x + 3, y + 0, z + 4);
        BlockPos pos64 = new BlockPos(x + 4, y + 0, z + 4);
        BlockPos pos65 = new BlockPos(x + 5, y + 0, z + 4);
        BlockPos pos66 = new BlockPos(x + 3, y + 0, z + 5);
        BlockPos pos67 = new BlockPos(x + 4, y + 0, z + 5);
        BlockPos pos68 = new BlockPos(x + 5, y + 0, z + 5);
        BlockPos pos69 = new BlockPos(x + 2, y + 0, z + 6);
        BlockPos pos70 = new BlockPos(x + 6, y + 0, z + 6);
        BlockPos pos71 = new BlockPos(x + 3, y + 1, z + 3);
        BlockPos pos72 = new BlockPos(x + 5, y + 1, z + 3);
        BlockPos pos73 = new BlockPos(x + 3, y + 1, z + 5);
        BlockPos pos74 = new BlockPos(x + 5, y + 1, z + 5);
        BlockPos pos75 = new BlockPos(x + 4, y + 2, z + 3);
        BlockPos pos76 = new BlockPos(x + 3, y + 2, z + 4);
        BlockPos pos77 = new BlockPos(x + 5, y + 2, z + 4);
        BlockPos pos78 = new BlockPos(x + 4, y + 2, z + 5);
        BlockPos pos79 = new BlockPos(x + 4, y + 3, z + 4);


        world.setBlockState(pos1, stateBurnt);
        world.setBlockState(pos2, stateBurnt);
        world.setBlockState(pos3, stateBurnt);
        world.setBlockState(pos4, stateBurnt);
        world.setBlockState(pos5, stateFireO);
        world.setBlockState(pos6, stateDistorted);
        world.setBlockState(pos7, stateFireO);
        world.setBlockState(pos8, stateBurnt);
        world.setBlockState(pos9, stateBurnt);
        world.setBlockState(pos10, stateDistorted);
        world.setBlockState(pos11, stateDistorted);
        world.setBlockState(pos12, stateDistorted);
        world.setBlockState(pos13, stateDistorted);
        world.setBlockState(pos14, stateDistorted);
        world.setBlockState(pos15, stateBurnt);
        world.setBlockState(pos16, stateBurnt);
        world.setBlockState(pos17, stateFireO);
        world.setBlockState(pos18, stateDistorted);
        world.setBlockState(pos19, stateDistorted);
        world.setBlockState(pos20, stateCryptic);
        world.setBlockState(pos21, stateDistorted);
        world.setBlockState(pos22, stateDistorted);
        world.setBlockState(pos23, stateFireO);
        world.setBlockState(pos24, stateBurnt);
        world.setBlockState(pos25, stateDistorted);
        world.setBlockState(pos26, stateDistorted);
        world.setBlockState(pos27, stateDistorted);
        world.setBlockState(pos28, stateCryptic);
        world.setBlockState(pos29, stateDistortedB);
        world.setBlockState(pos30, stateCryptic);
        world.setBlockState(pos31, stateDistorted);
        world.setBlockState(pos32, stateDistorted);
        world.setBlockState(pos33, stateDistorted);
        world.setBlockState(pos34, stateBurnt);
        world.setBlockState(pos35, stateFireO);
        world.setBlockState(pos36, stateDistorted);
        world.setBlockState(pos37, stateDistorted);
        world.setBlockState(pos38, stateCryptic);
        world.setBlockState(pos39, stateDistorted);
        world.setBlockState(pos40, stateDistorted);
        world.setBlockState(pos41, stateFireO);
        world.setBlockState(pos42, stateBurnt);
        world.setBlockState(pos43, stateBurnt);
        world.setBlockState(pos44, stateDistorted);
        world.setBlockState(pos45, stateDistorted);
        world.setBlockState(pos46, stateDistorted);
        world.setBlockState(pos47, stateDistorted);
        world.setBlockState(pos48, stateDistorted);
        world.setBlockState(pos49, stateBurnt);
        world.setBlockState(pos50, stateBurnt);
        world.setBlockState(pos51, stateFireO);
        world.setBlockState(pos52, stateDistorted);
        world.setBlockState(pos53, stateFireO);
        world.setBlockState(pos54, stateBurnt);
        world.setBlockState(pos55, stateBurnt);
        world.setBlockState(pos56, stateDistorted);
        world.setBlockState(pos57, stateBurnt);
        world.setBlockState(pos58, stateDistorted);
        world.setBlockState(pos59, stateDistorted);
        world.setBlockState(pos60, stateDistorted);
        world.setBlockState(pos61, stateFireM);
        world.setBlockState(pos62, stateDistorted);
        world.setBlockState(pos63, stateFireM);
        world.setBlockState(pos65, stateFireM);
        world.setBlockState(pos66, stateDistorted);
        world.setBlockState(pos67, stateFireM);
        world.setBlockState(pos68, stateDistorted);
        world.setBlockState(pos69, stateDistorted);
        world.setBlockState(pos70, stateDistorted);
        world.setBlockState(pos71, stateDistorted);
        world.setBlockState(pos72, stateDistorted);
        world.setBlockState(pos73, stateDistorted);
        world.setBlockState(pos74, stateDistorted);
        world.setBlockState(pos75, stateDistorted);
        world.setBlockState(pos76, stateDistorted);
        world.setBlockState(pos77, stateDistorted);
        world.setBlockState(pos78, stateDistorted);
        world.setBlockState(pos79, stateFire);

        world.setBlockState(pos64, stateChest);
        if (rand.nextFloat() < .12) {
            if (!world.isRemote) {
                WorldServer worldserver = (WorldServer) world;
                MinecraftServer minecraftserver = world.getMinecraftServer();
                TemplateManager templatemanager = worldserver.getStructureTemplateManager();
                ResourceLocation loc = new ResourceLocation(Ref.MODID, "KingDungeon");
                Template template = templatemanager.get(minecraftserver, loc);

                if (template != null) {
                    IBlockState iblockstate = world.getBlockState(pos);
                    world.notifyBlockUpdate(pos.up(50), iblockstate, iblockstate, 3);
                    PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
                            .setRotation(Rotation.NONE).setIgnoreEntities(true).setChunk(null)
                            .setReplacedBlock(null).setIgnoreStructureBlock(true);
                    template.addBlocksToWorld(world, pos.down(25), placementsettings);
                }
                for (EntityPlayer playerEntity : world.playerEntities) {
                    playerEntity.sendMessage(new TextComponentTranslation(TextFormatting.AQUA + "A loud crack can be heard from deep below..."));
                }
            }
        }
        if (!world.isRemote) {
            for (EntityPlayer playerEntity : world.playerEntities) {
                playerEntity.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "The ground shakes..."));
                playerEntity.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + this.xPos + " " + this.zPos));
            }
        }
        return true;
    }
}
