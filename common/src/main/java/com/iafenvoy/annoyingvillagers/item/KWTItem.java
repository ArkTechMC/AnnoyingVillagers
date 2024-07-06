package com.iafenvoy.annoyingvillagers.item;

import com.iafenvoy.annoyingvillagers.procedures.KWTItemUse;
import com.iafenvoy.annoyingvillagers.registry.util.ToolMaterialUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class KWTItem extends SwordItem {
    public KWTItem() {
        super(ToolMaterialUtil.of(250, 6, 1.5f, 2, 7), 3, -1.8f, new Settings());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        KWTItemUse.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, ar.getValue());
        return ar;
    }
}
