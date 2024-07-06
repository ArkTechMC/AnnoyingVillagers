package com.iafenvoy.annoyingvillagers.item;

import com.iafenvoy.annoyingvillagers.procedures.RKSChange;
import com.iafenvoy.annoyingvillagers.procedures.ShootFireBall;
import com.iafenvoy.annoyingvillagers.registry.util.SwordItemBase;
import com.iafenvoy.annoyingvillagers.registry.util.ToolMaterialUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RKSItem extends SwordItemBase {
    public RKSItem() {
        super(ToolMaterialUtil.of(1731, 17, 17.5f, 5, 23), 3, -2.3f, new Settings());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        RKSChange.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, ar.getValue());
        return ar;
    }

    @Override
    public boolean onEntitySwing(ItemStack itemstack, Entity entity) {
        boolean retval = super.onEntitySwing(itemstack, entity);
        ShootFireBall.execute(entity.getWorld(), entity.getX(), entity.getY(), entity.getZ(), entity, itemstack);
        return retval;
    }

}
