package com.iafenvoy.annoyingvillagers.item;

import com.iafenvoy.annoyingvillagers.procedures.RGSChange;
import com.iafenvoy.annoyingvillagers.procedures.ShootFireBall;
import com.iafenvoy.annoyingvillagers.registry.util.SwordItemBase;
import com.iafenvoy.annoyingvillagers.registry.util.ToolMaterialUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RGSItem extends SwordItemBase {
    public RGSItem() {
        super(ToolMaterialUtil.of(1731, 17, 20, 5, 23), 3, -3f, new Settings());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        RGSChange.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, ar.getValue());
        return ar;
    }

    @Override
    public boolean onEntitySwing(ItemStack itemstack, Entity entity) {
        boolean retval = super.onEntitySwing(itemstack, entity);
        ShootFireBall.execute(entity.getWorld(), entity.getX(), entity.getY(), entity.getZ(), entity, itemstack);
        return retval;
    }

}
