package com.thenewjourney.items.tool;

import com.cj3636.lib.Ref;
import net.minecraft.item.ItemSpade;

public class ModShovelRegistry extends ItemSpade {

	public ModShovelRegistry(String unlocalizedName, ToolMaterial material) {
	    super(material);
	    this.setUnlocalizedName(unlocalizedName);
	    this.setRegistryName(unlocalizedName);
	    this.setCreativeTab(Ref.CTAB);
	}
}
