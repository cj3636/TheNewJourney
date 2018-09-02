package com.thenewjourney.items.bauble;

public class ModRareToolTip {
	
	public static String getRareToolTip(ModEnumRarity type) {
		switch (type) {
		case BASIC: 
			return "\u00A77" + "Basic Item";
		case COMMON:
			return "\u00A7f" + "Common Item";
		case UNCOMMON:
			return "\u00A7e" + "Uncommon Item";
		case FINE:
			return "\u00A7a" + "Fine Item";
		case MASTERWORK:
			return "\u00A71" + "Masterwork";
		case RARE:
			return "\u00A7b" + "Rare Item";
		case EPIC:
			return "\u00A7d" + "Epic Item";
		case LEGENDARY:
			return "\u00A75" + "\u00A7o" + "Legendary Item";
		}
		return null;
	}
}
