package com.iafenvoy.annoyingvillagers.item;

import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItemGroups;
import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItems;
import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import com.iafenvoy.neptune.util.Timeout;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class KWTItem extends SwordItem {
    public KWTItem() {
        super(ToolMaterialUtil.of(250, 6, 1.5f, 2, 7), 3, -1.8f, new Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS));
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
                    ItemStack _setstack = new ItemStack(AnnoyingModItems.KNIFE.get());
                    _setstack.setCount(1);
                    entity.setStackInHand(Hand.MAIN_HAND, _setstack);
                    if ((LivingEntity) entity instanceof PlayerEntity _player)
                        _player.getInventory().markDirty();
                    {
                        NbtCompound _nbtTag = itemstack.getNbt();
                        if (_nbtTag != null)
                            entity.getMainHandStack().setNbt(_nbtTag.copy());
                    }
                    entity.getItemCooldownManager().set(entity.getMainHandStack().getItem(), 100);
                    if (!world.isClient)
                        world.createExplosion(null, x, y, z, 5, World.ExplosionSourceType.TNT);
                });
            } else {
                world.addParticle(ParticleTypes.FLASH, x, y, z, 0, 0, 0);
                Timeout.create(1, () -> {
                    ItemStack _setstack = new ItemStack(AnnoyingModItems.KNIFE.get());
                    _setstack.setCount(1);
                    entity.setStackInHand(Hand.OFF_HAND, _setstack);
                    if ((LivingEntity) entity instanceof PlayerEntity _player)
                        _player.getInventory().markDirty();
                    NbtCompound _nbtTag = itemstack.getNbt();
                    if (_nbtTag != null)
                        entity.getOffHandStack().setNbt(_nbtTag.copy());
                    entity.getItemCooldownManager().set(entity.getOffHandStack().getItem(), 100);
                    if (!world.isClient)
                        world.createExplosion(null, x, y, z, 5, World.ExplosionSourceType.TNT);
                });
            }
        }
        return ar;
    }
}
