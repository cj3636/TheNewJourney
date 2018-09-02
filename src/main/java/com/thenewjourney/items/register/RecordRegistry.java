package com.thenewjourney.items.register;

import com.cj3636.lib.Ref;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;

public class RecordRegistry extends ItemRecord {

    public RecordRegistry(String unlocalizedName, SoundEvent soundIn) {
        super(unlocalizedName, soundIn);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
    }
}
