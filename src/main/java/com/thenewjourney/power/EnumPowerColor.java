package com.thenewjourney.power;

import java.awt.*;

public enum EnumPowerColor {
	TIERONE(1, Color.WHITE, "TIER ONE", "\u00A7f"),//Create Crystal
    TIERTWO(2, Color.GREEN, "TIER TWO", "\u00A7a"),
    TIERTHREE(3, Color.YELLOW, "TIER THREE", "\u00A7e"),
    TIERFOUR(4, Color.CYAN, "TIER FOUR", "\u00A7b"),
    TIERFIVE(5, Color.BLUE, "TIER FIVE", "\u00A71"),
    TIERSIX(6, Color.PINK, "TIER SIX", "\u00A7d"),
    TIERSEVEN(7, Color.MAGENTA, "TIER SEVEN", "\u00A75"),
    TIEREIGHT(8, Color.RED, "TIER EIGHT", "\u00A74");
	
	public final int tier;
    public final Color powerColor;
    public final String powerTier;
    public final String translation;

    EnumPowerColor(int tier, Color color, String name, String translation) {
    	this.tier = tier;
    	this.powerColor = color;
	    this.powerTier = name;
	    this.translation = translation;
    }
    public static Color getTierColor(EnumPowerColor type) {
		return type.powerColor;	    	
	}
    public static String getColorTranslation(EnumPowerColor type) {
		return type.translation;	    	
	}
    public static EnumPowerColor getTierFromInt(int tier) {
		switch (tier) {
		case 1:
			return EnumPowerColor.TIERONE;
		case 2:
			return EnumPowerColor.TIERTWO;
		case 3:
			return EnumPowerColor.TIERTHREE;
		case 4:
			return EnumPowerColor.TIERFOUR;
		case 5:
			return EnumPowerColor.TIERFIVE;
		case 6:
			return EnumPowerColor.TIERSIX;
		case 7:
			return EnumPowerColor.TIERSEVEN;
		case 8:
			return EnumPowerColor.TIEREIGHT;
		}
		return EnumPowerColor.TIERONE;
	}
}
