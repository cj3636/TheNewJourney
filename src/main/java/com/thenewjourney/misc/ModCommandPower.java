package com.thenewjourney.misc;

import com.thenewjourney.power.EnumPowerColor;
import com.thenewjourney.power.ModPower;
import com.thenewjourney.world.ModWorldSaveData;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ModCommandPower implements ICommand {
    private final ArrayList<String> aliases;

    public ModCommandPower() {
        aliases = new ArrayList<String>();
        aliases.add("tnj");
        aliases.add("tnjp");
    }

    @Override
    public String getName() {
        return "journeypower";
    }

    @Override
    public String getUsage(ICommandSender var1) {
        return "journeypower <1, 2, 3, 4, 5, 6, 7, 8, clear, blocks> <up, clear>";
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public boolean isUsernameIndex(String[] var1, int var2) {
        return false;
    }

    @Override
    public int compareTo(ICommand arg0) {
        return 0;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        World world = sender.getEntityWorld();
        if (!world.isRemote) {
            ModWorldSaveData data = ModWorldSaveData.forWorld(world);
            if (args.length == 0) {
                sender.sendMessage(new TextComponentTranslation("journeypower <1, 2, 3, 4, 5, 6, 7, 8, clear, blocks> <up, clear>"));
                sender.sendMessage(new TextComponentTranslation("Tier: " + EnumPowerColor.getColorTranslation(EnumPowerColor.getTierFromInt(ModPower.getPowerTier(world))) + ModPower.getPowerTier(world)));
                sender.sendMessage(new TextComponentTranslation("Blocks: " + EnumPowerColor.getColorTranslation(EnumPowerColor.getTierFromInt(ModPower.getPowerTier(world))) + ModPower.getPowerNum(world)));
            } else if (args[0].equals("1")) {
                data.setPowerTier(1);
                sender.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Set to 1!"));
            } else if (args[0].equals("2")) {
            	data.setPowerTier(2);
                sender.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Set to 2!"));
            } else if (args[0].equals("3")) {
            	data.setPowerTier(3);
                sender.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Set to 3!"));
            } else if (args[0].equals("4")) {
                data.setPowerTier(4);
                sender.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Set to 4!"));
            } else if (args[0].equals("5")) {
                data.setPowerTier(5);
                sender.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Set to 5!"));
            } else if (args[0].equals("6")) {
                data.setPowerTier(6);
                sender.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Set to 6!"));
            } else if (args[0].equals("7")) {
                data.setPowerTier(7);
                sender.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Set to 7!"));
            } else if (args[0].equals("8")) {
                data.setPowerTier(8);
                sender.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Set to 8!"));
            } else if (args[0].equals("clear")) {
                data.setPowerTier(0);
                sender.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Cleared Tiers!"));
            } else if (args[0].equals("blocks")) {
            	sender.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Used to set the number of powered source blocks in the world."));
                if (args.length == 2 && args[1].equals("up")) {
                	data.setPowerNum(data.getPowerNum() + 1);
                	sender.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Set powered blocks to: " + data.getPowerNum()));
                } else if (args.length == 2 && args[1].equals("clear")) {
                	data.setPowerNum(0);
                	sender.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Set powered blocks to: " + data.getPowerNum()));
                } else if (args.length == 2) {
                    sender.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Invalid Argument!"));
                }
            } else {
                sender.sendMessage(new TextComponentTranslation("\u00A75" + "\u00A7o" + "Invalid Argument!"));
            }
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender.canUseCommand(server.getOpPermissionLevel(), "journeypower");
    }

    @Override
    public java.util.List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return null;
    }
}
