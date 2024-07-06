package com.iafenvoy.annoyingvillagers.procedures;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraftforge.registries.ForgeRegistries;
import com.iafenvoy.annoyingvillagers.AnnoyingMod;

public class WoopieUse {
    public static void execute(WorldAccess world, double x, double y, double z, Entity entity, ItemStack itemstack) {
        if (entity == null)
            return;
        if (entity.isSneaking() == true) {
            if (world instanceof ServerWorld _level)
                _level.spawnParticles(ParticleTypes.FIREWORK, x, y, z, 25, 0.2, 0.2, 0.2, 0.2);
            if (world instanceof World _level) {
                if (!_level.isClient()) {
                    _level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("block.anvil.use")), SoundCategory.NEUTRAL, 1, 1);
                } else {
                    _level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("block.anvil.use")), SoundCategory.NEUTRAL, 1, 1, false);
                }
            }
            if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
                _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 80, 1, true, false));
            if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
                _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 80, 2, true, false));
            if (entity instanceof PlayerEntity _player)
                _player.getItemCooldownManager().set(itemstack.getItem(), 160);
        } else {
            if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
                _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 10, 8, true, false));
            if (world instanceof World _level) {
                if (!_level.isClient()) {
                    _level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("entity.player.attack.sweep")), SoundCategory.NEUTRAL, 1, 1);
                } else {
                    _level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("entity.player.attack.sweep")), SoundCategory.NEUTRAL, 1, 1, false);
                }
            }
            world.addParticle(ParticleTypes.EXPLOSION, x, y, z, 0, 0, 0);
            AnnoyingMod.queueServerWork(2, () -> {
                world.addParticle(ParticleTypes.EXPLOSION, x, y, z, 0, 0, 0);
                AnnoyingMod.queueServerWork(2, () -> {
                    world.addParticle(ParticleTypes.EXPLOSION, x, y, z, 0, 0, 0);
                });
            });
            if (entity instanceof PlayerEntity _player)
                _player.getItemCooldownManager().set(itemstack.getItem(), 100);
        }
    }
}
