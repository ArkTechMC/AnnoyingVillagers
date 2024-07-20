package com.iafenvoy.annoyingvillagers.item;

import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItemGroups;
import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItems;
import com.iafenvoy.annoyingvillagers.registry.util.ToolMaterialUtil;
import com.iafenvoy.annoyingvillagers.util.SoundUtil;
import com.iafenvoy.annoyingvillagers.util.Timeout;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class LegendarySwordItem extends SwordItem {
    public LegendarySwordItem() {
        super(ToolMaterialUtil.of(9999, 17, 19, 5, 27), 3, -2.4f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS));
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
                    SoundUtil.playSound(world, x, y, z, new Identifier("item.totem.use"), 1, 1);
                    if (world instanceof ServerWorld _level)
                        _level.spawnParticles(ParticleTypes.TOTEM_OF_UNDYING, x, y, z, 150, 0.5, 0.5, 0.5, 0.5);
                    ItemStack _setstack = new ItemStack(AnnoyingModItems.AWAKENING_LEGENDARY_SWORD.get()).copy();
                    _setstack.setCount(1);
                    entity.setStackInHand(Hand.MAIN_HAND, _setstack);
                    entity.getInventory().markDirty();
                    NbtCompound _nbtTag = itemstack.getNbt();
                    if (_nbtTag != null)
                        entity.getMainHandStack().setNbt(_nbtTag.copy());
                    entity.getItemCooldownManager().set(entity.getMainHandStack().getItem(), 100);
                });
            } else {
                world.addParticle(ParticleTypes.FLASH, x, y, z, 0, 0, 0);
                Timeout.create(1, () -> {
                    SoundUtil.playSound(world, x, y, z, new Identifier("item.totem.use"), 1, 1);
                    if (world instanceof ServerWorld _level)
                        _level.spawnParticles(ParticleTypes.TOTEM_OF_UNDYING, x, y, z, 150, 0.5, 0.5, 0.5, 0.5);
                    ItemStack _setstack = new ItemStack(AnnoyingModItems.AWAKENING_LEGENDARY_SWORD.get()).copy();
                    _setstack.setCount(1);
                    entity.setStackInHand(Hand.MAIN_HAND, _setstack);
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
}
