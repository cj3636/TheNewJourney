package com.thenewjourney.dimension;

import com.cj3636.lib.Config;
import com.cj3636.lib.Ref;
import com.thenewjourney.dimension.fire.FireWorldProvider;
import com.thenewjourney.dimension.florus.FlorusWorldProvider;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class ModDimension {

    public static DimensionType fireDimension;
    public static DimensionType florusDimension;

    public static void mainRegistry() {
    	//Fire
    	fireDimension = DimensionType.register(Ref.MODID, "_fire", Config.fireDimId, FireWorldProvider.class, false);
    	DimensionManager.registerDimension(Config.fireDimId, fireDimension);
    	//Florus
    	florusDimension = DimensionType.register(Ref.MODID, "_florus", Config.florusDimId, FlorusWorldProvider.class, false);
    	DimensionManager.registerDimension(Config.florusDimId, florusDimension);
    }
}
