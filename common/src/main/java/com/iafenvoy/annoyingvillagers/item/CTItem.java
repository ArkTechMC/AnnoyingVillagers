package com.iafenvoy.annoyingvillagers.item;

import com.iafenvoy.annoyingvillagers.procedures.WoodenWeaponAttack;
import com.iafenvoy.annoyingvillagers.registry.util.ToolMaterialUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class CTItem extends SwordItem {
    public CTItem() {
        super(ToolMaterialUtil.of(64, 6, 0.5f, 5, 15), 3, -2.8f, new Settings());
    }

    @Override
    public boolean postHit(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.postHit(itemstack, entity, sourceentity);
        WoodenWeaponAttack.execute(entity.getWorld(), entity.getX(), entity.getY(), entity.getZ());
        return retval;
    }
}
