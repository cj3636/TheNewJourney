package com.thenewjourney.items.tool;

import com.cj3636.lib.Ref;
import net.minecraft.item.Item;

public class Pestal extends Item {

    public Pestal(String unlocalizedName) {
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
        this.setMaxStackSize(1);
        this.setMaxDamage(15);
        this.setNoRepair();
    }
}
