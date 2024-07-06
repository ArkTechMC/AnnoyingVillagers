package com.iafenvoy.annoyingvillagers.item;

import com.iafenvoy.annoyingvillagers.procedures.RSABChange;
import com.iafenvoy.annoyingvillagers.registry.util.ToolMaterialUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RSABItem extends AxeItem {
    public RSABItem() {
        super(ToolMaterialUtil.of(3576, 17, 7, 5, 27), 3, -2.2f, new Settings());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        RSABChange.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, ar.getValue());
        return ar;
    }
}
