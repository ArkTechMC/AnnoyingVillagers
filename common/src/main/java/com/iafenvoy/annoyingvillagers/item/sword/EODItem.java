package com.iafenvoy.annoyingvillagers.item.sword;

import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItemGroups;
import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class EODItem extends SwordItem {
    public EODItem() {
        super(ToolMaterialUtil.of(64, 6, 2.5f, 5, 15), 3, -2.7f, new Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS));
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean hasGlint(ItemStack itemstack) {
        return true;
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
