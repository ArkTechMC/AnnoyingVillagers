package com.iafenvoy.annoyingvillagers.item;

import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItemGroups;
import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class ODItem extends SwordItem {
    public ODItem() {
        super(ToolMaterialUtil.of(64, 6, 1, 5, 15), 3, -2.8f, new Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS));
    }

    @Override
    public boolean postHit(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.postHit(itemstack, entity, sourceentity);
        WorldAccess world = entity.getWorld();
        if (world instanceof World _level)
            SoundUtil.playSound(_level, entity.getX(), entity.getY(), entity.getZ(), new Identifier("entity.zombie.break_wooden_door"), 1, 1);
        return retval;
    }
}
