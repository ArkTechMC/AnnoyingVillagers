package com.iafenvoy.annoyingvillagers.item;

import com.iafenvoy.annoyingvillagers.procedures.AwakeningLegendarySwordRightClick;
import com.iafenvoy.annoyingvillagers.registry.util.FoilSwordItemBase;
import com.iafenvoy.annoyingvillagers.registry.util.ToolMaterialUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class AwakeningLegendarySwordItem extends FoilSwordItemBase {
    public AwakeningLegendarySwordItem() {
        super(ToolMaterialUtil.of(9999, 17, 25, 5, 27), 3, -2.4f, new Item.Settings());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        AwakeningLegendarySwordRightClick.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, ar.getValue());
        return ar;
    }

    @Override
    public boolean hasGlint(ItemStack itemstack) {
        return true;
    }

    @Override
    public ItemStack finishUsing(ItemStack itemstack, World world, LivingEntity entity) {
        ItemStack retval = super.finishUsing(itemstack, world, entity);
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 5, 3, false, true));
        return retval;
    }
}
