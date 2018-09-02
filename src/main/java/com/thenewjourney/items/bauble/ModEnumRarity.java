package com.thenewjourney.items.bauble;

import net.minecraft.util.text.TextFormatting;

public enum ModEnumRarity {
    BASIC(TextFormatting.GRAY, "Basic"),
    COMMON(TextFormatting.WHITE, "Common"),
    UNCOMMON(TextFormatting.YELLOW, "Uncommon"),
    FINE(TextFormatting.GREEN, "Fine"),
    RARE(TextFormatting.AQUA, "Rare"),
    MASTERWORK(TextFormatting.BLUE, "Master Work"),
    EPIC(TextFormatting.LIGHT_PURPLE, "Epic"),
    LEGENDARY(TextFormatting.DARK_PURPLE, TextFormatting.ITALIC, "Legendary");

    public final TextFormatting rarityColor;
    public final String rarityName;

    ModEnumRarity(TextFormatting color, String name) {
        this.rarityColor = color;
        this.rarityName = name;
    }
    ModEnumRarity(TextFormatting color, TextFormatting type, String name) {
        this.rarityColor = color;
        this.rarityName = name;
    }
}
