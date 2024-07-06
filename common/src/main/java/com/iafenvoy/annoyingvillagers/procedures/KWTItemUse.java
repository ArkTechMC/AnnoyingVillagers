package com.iafenvoy.annoyingvillagers.procedures;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.WorldAccess;
import com.iafenvoy.annoyingvillagers.AnnoyingMod;
import com.iafenvoy.annoyingvillagers.init.AnnoyingModItem;

public class KWTItemUse {
    public static void execute(WorldAccess world, double x, double y, double z, Entity entity, ItemStack itemstack) {
        if (entity == null)
            return;
        if (entity.isSneaking()) {
            if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandStack() : ItemStack.EMPTY).getItem() == itemstack.getItem()) {
                world.addParticle(ParticleTypes.FLASH, x, y, z, 0, 0, 0);
                entity.getPersistentData().putDouble("uses", (itemstack.getDamage()));
                AnnoyingMod.queueServerWork(1, () -> {
                    if (entity instanceof LivingEntity _entity) {
                        ItemStack _setstack = new ItemStack(AnnoyingModItem.KNIFE.get()).copy();
                        _setstack.setCount(1);
                        _entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
                        if (_entity instanceof Player _player)
                            _player.getInventory().setChanged();
                    }
                    {
                        CompoundTag _nbtTag = itemstack.getTag();
                        if (_nbtTag != null)
                            (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).setTag(_nbtTag.copy());
                    }
                    (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).setDamageValue((int) entity.getPersistentData().getDouble("uses"));
                    if (entity instanceof Player _player)
                        _player.getCooldowns().addCooldown((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem(), 100);
                    if (world instanceof Level _level && !_level.isClientSide())
                        _level.explode(null, x, y, z, 5, Level.ExplosionInteraction.TNT);
                });
            } else {
                world.addParticle(ParticleTypes.FLASH, x, y, z, 0, 0, 0);
                entity.getPersistentData().putDouble("uses", (itemstack.getDamage()));
                AnnoyingMod.queueServerWork(1, () -> {
                    if (entity instanceof LivingEntity _entity) {
                        ItemStack _setstack = new ItemStack(AnnoyingModItem.KNIFE.get()).copy();
                        _setstack.setCount(1);
                        _entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
                        if (_entity instanceof Player _player)
                            _player.getInventory().setChanged();
                    }
                    {
                        CompoundTag _nbtTag = itemstack.getTag();
                        if (_nbtTag != null)
                            (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).setTag(_nbtTag.copy());
                    }
                    (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).setDamageValue((int) entity.getPersistentData().getDouble("uses"));
                    if (entity instanceof Player _player)
                        _player.getCooldowns().addCooldown((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem(), 100);
                    if (world instanceof Level _level && !_level.isClientSide())
                        _level.explode(null, x, y, z, 5, Level.ExplosionInteraction.TNT);
                });
            }
        }
    }
}
