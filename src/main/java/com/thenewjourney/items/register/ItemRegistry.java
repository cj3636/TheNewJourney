package com.thenewjourney.items.register;

import com.cj3636.lib.Ref;
import net.minecraft.item.Item;

public class ItemRegistry extends Item {

    public ItemRegistry(String unlocalizedName) {
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
    }
}
