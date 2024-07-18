package com.iafenvoy.annoyingvillagers.item;

import com.iafenvoy.annoyingvillagers.registry.util.ToolMaterialUtil;
import com.iafenvoy.annoyingvillagers.util.SoundUtil;
import com.iafenvoy.annoyingvillagers.util.Timeout;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class WoopieItem extends SwordItem {
    public WoopieItem() {
        super(ToolMaterialUtil.of(1531, 10, 4.5f, 3, 12), 3, -2.2f, new Item.Settings());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        ItemStack itemstack = ar.getValue();
        if (entity != null) {
            if (entity.isSneaking()) {
                if ((WorldAccess) world instanceof ServerWorld _level)
                    _level.spawnParticles(ParticleTypes.FIREWORK, x, y, z, 25, 0.2, 0.2, 0.2, 0.2);
                if ((WorldAccess) world instanceof World _level)
                    SoundUtil.playSound(_level, x, y, z, new Identifier("block.anvil.use"), 1, 1);
                if ((Entity) entity instanceof LivingEntity _entity && !_entity.getWorld().isClient) {
                    _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 80, 1, true, false));
                    _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 80, 2, true, false));
                }
                if ((Entity) entity instanceof PlayerEntity _player)
                    _player.getItemCooldownManager().set(itemstack.getItem(), 160);
            } else {
                if ((Entity) entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
                    _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 10, 8, true, false));
                if ((WorldAccess) world instanceof World _level)
                    SoundUtil.playSound(_level, x, y, z, new Identifier("entity.player.attack.sweep"), 1, 1);
                Runnable runnable = () -> ((WorldAccess) world).addParticle(ParticleTypes.EXPLOSION, x, y, z, 0, 0, 0);
                Timeout.create(0, runnable);
                Timeout.create(2, runnable);
                Timeout.create(4, runnable);
                if ((Entity) entity instanceof PlayerEntity _player)
                    _player.getItemCooldownManager().set(itemstack.getItem(), 100);
            }
        }
        return ar;
    }
}
