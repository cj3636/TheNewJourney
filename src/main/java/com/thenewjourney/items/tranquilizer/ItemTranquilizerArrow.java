package com.thenewjourney.items.tranquilizer;

import com.cj3636.lib.Ref;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by cj3636 on 2/2/2017.
 */
public class ItemTranquilizerArrow extends ItemArrow {

    public ItemTranquilizerArrow(String unlocalizedName) {
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setCreativeTab(Ref.CTAB);
    }

    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        return new EntityTranquilizerArrow(worldIn, shooter);
    }
}
