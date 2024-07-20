package com.iafenvoy.annoyingvillagers.item;

import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItemGroups;
import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItems;
import com.iafenvoy.annoyingvillagers.registry.util.FoilSwordItemBase;
import com.iafenvoy.annoyingvillagers.registry.util.ToolMaterialUtil;
import com.iafenvoy.annoyingvillagers.util.Timeout;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class AwakeningLegendarySwordItem extends FoilSwordItemBase {
    public AwakeningLegendarySwordItem() {
        super(ToolMaterialUtil.of(9999, 17, 25, 5, 27), 3, -2.4f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        ItemStack itemstack = ar.getValue();
        if (entity.isSneaking()) {
            if (entity.getMainHandStack().getItem() == itemstack.getItem()) {
                world.addParticle(ParticleTypes.FLASH, x, y, z, 0, 0, 0);
                Timeout.create(1, () -> {
                    ItemStack _setstack = new ItemStack(AnnoyingModItems.LEGENDARY_SWORD.get()).copy();
                    _setstack.setCount(1);
                    entity.setStackInHand(Hand.MAIN_HAND, _setstack);
                    entity.getInventory().markDirty();
                    NbtCompound _nbtTag = itemstack.getNbt();
                    if (_nbtTag != null) {
                        entity.getMainHandStack().setNbt(_nbtTag.copy());
                    }
                    entity.getItemCooldownManager().set(entity.getMainHandStack().getItem(), 100);
                });
            } else {
                world.addParticle(ParticleTypes.FLASH, x, y, z, 0, 0, 0);
                Timeout.create(1, () -> {
                    ItemStack _setstack = new ItemStack(AnnoyingModItems.LEGENDARY_SWORD.get()).copy();
                    _setstack.setCount(1);
                    entity.setStackInHand(Hand.OFF_HAND, _setstack);
                    entity.getInventory().markDirty();
                    NbtCompound _nbtTag = itemstack.getNbt();
                    if (_nbtTag != null)
                        entity.getOffHandStack().setNbt(_nbtTag.copy());
                    entity.getItemCooldownManager().set(entity.getOffHandStack().getItem(), 100);
                });
            }
        }
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
