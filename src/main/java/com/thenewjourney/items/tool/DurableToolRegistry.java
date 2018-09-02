package com.thenewjourney.items.tool;

import com.cj3636.lib.Ref;
import net.minecraft.item.Item;

public class DurableToolRegistry extends Item {
    public DurableToolRegistry(String unlocalizedName, int damage) {
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
        this.setMaxStackSize(1);
        this.setMaxDamage(damage);
        this.setNoRepair();
    }
}
