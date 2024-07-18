package com.iafenvoy.annoyingvillagers.procedures;

import com.iafenvoy.annoyingvillagers.AnnoyingVillagers;
import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItems;
import com.iafenvoy.annoyingvillagers.util.CommandHelper;
import com.iafenvoy.annoyingvillagers.util.SoundUtil;
import com.iafenvoy.annoyingvillagers.util.Timeout;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Comparator;
import java.util.List;

public class BlueDemonTick {
    public static void execute(WorldAccess world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        double attack = 0;
        if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) < 140) {
            if (entity.getPersistentData().getBoolean("pashe2") == false) {
                entity.getPersistentData().putBoolean("pashe2", true);
                entity.setCustomName(Text.literal("Blue Demon-Legendary Sword"));
                if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
                    _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 7, false, false));
                if (world instanceof World _level) {
                    SoundUtil.playSound(_level, x, y, z, new Identifier("block.anvil.place"), 1, 1);
                    SoundUtil.playSound(_level, x, y, z, new Identifier("item.armor.equip_diamond"), 1, 1);
                    SoundUtil.playSound(_level, x, y, z, new Identifier(AnnoyingVillagers.MOD_ID, "blue.demon.ittrsp"), 1, 1);
                }
                if (entity instanceof LivingEntity _entity) {
                    ItemStack _setstack = new ItemStack(AnnoyingModItems.LEGENDARY_SWORD.get()).copy();
                    _setstack.setCount(1);
                    _entity.setStackInHand(Hand.MAIN_HAND, _setstack);
                    if (_entity instanceof PlayerEntity _player)
                        _player.getInventory().markDirty();
                }
                if (entity instanceof LivingEntity _entity) {
                    ItemStack _setstack = new ItemStack(Blocks.AIR).copy();
                    _setstack.setCount(1);
                    _entity.setStackInHand(Hand.OFF_HAND, _setstack);
                    if (_entity instanceof PlayerEntity _player)
                        _player.getInventory().markDirty();
                }
                CommandHelper.execute(entity, "tellraw @a \"<Blue Demon> It's time to respawn,player...\"");
            }
        }
        entity.getPersistentData().putDouble("timer", (entity.getPersistentData().getDouble("timer") + 1));
        if (entity.getPersistentData().getDouble("timer") == 80) {
            entity.getPersistentData().putDouble("timer", 0);
            attack = MathHelper.nextInt(Random.create(), 1, 7);
        }

        final Vec3d _center = new Vec3d(x, y, z);
        List<Entity> _entfound = world.getEntitiesByClass(Entity.class, new Box(_center, _center).expand(32 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.squaredDistanceTo(_center))).toList();
        for (Entity entityiterator : _entfound) {
            if ((entity instanceof MobEntity _mobEnt ? (Entity) _mobEnt.getTarget() : null) == entityiterator) {
                if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient) {
                    if (attack == 1)
                        _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 3, false, true));
                    if (attack == 7)
                        _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 3, false, true));
                    if (attack == 2)
                        _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 80, 3, false, true));
                    if (attack == 3)
                        _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 80, 3, false, true));
                }
                if (attack == 8) {
                    if (entity instanceof LivingEntity _entity) {
                        ItemStack _setstack = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandStack() : ItemStack.EMPTY).copy();
                        _setstack.setCount(1);
                        _entity.setStackInHand(Hand.OFF_HAND, _setstack);
                        if (_entity instanceof PlayerEntity _player)
                            _player.getInventory().markDirty();
                    }
                    Runnable runnable = () -> {
                        if (entity instanceof LivingEntity _entity) {
                            ItemStack _setstack = new ItemStack(Items.TRIDENT).copy();
                            _setstack.setCount(1);
                            _entity.setStackInHand(Hand.MAIN_HAND, _setstack);
                            if (_entity instanceof PlayerEntity _player)
                                _player.getInventory().markDirty();
                        }
                        if (world instanceof World _level)
                            SoundUtil.playSound(_level, x, y, z, new Identifier("item.trident.throw"), 1, 1);
                        if (entity instanceof LivingEntity _entity)
                            _entity.swingHand(Hand.MAIN_HAND, true);
                        World projectileLevel = entity.getWorld();
                        if (!projectileLevel.isClient) {
                            TridentEntity entityToSpawn = new TridentEntity(EntityType.TRIDENT, projectileLevel);
                            entityToSpawn.setOwner(entity);
                            entityToSpawn.setDamage(5);
                            entityToSpawn.setPunch(3);
                            entityToSpawn.setPos(entity.getX(), entity.getEyeY() - 0.1, entity.getZ());
                            entityToSpawn.setVelocity(entity.getRotationVector().x, entity.getRotationVector().y, entity.getRotationVector().z, 5, 0);
                            projectileLevel.spawnEntity(entityToSpawn);
                        }
                    };
                    Timeout.create(2, runnable);
                    Timeout.create(12, runnable);
                    Timeout.create(22, runnable);
                    Timeout.create(24, () -> {
                        if (entity instanceof LivingEntity _entity) {
                            ItemStack _setstack = new ItemStack(Blocks.CAVE_AIR).copy();
                            _setstack.setCount(1);
                            _entity.setStackInHand(Hand.OFF_HAND, _setstack);
                            if (_entity instanceof PlayerEntity _player)
                                _player.getInventory().markDirty();
                        }
                    });
                }
                if (attack == 6) {
                    if (entity instanceof LivingEntity _entity) {
                        ItemStack _setstack = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandStack() : ItemStack.EMPTY).copy();
                        _setstack.setCount(1);
                        _entity.setStackInHand(Hand.OFF_HAND, _setstack);
                        if (_entity instanceof PlayerEntity _player)
                            _player.getInventory().markDirty();
                    }
                    Runnable runnable = () -> {
                        if (entity instanceof LivingEntity _entity) {
                            ItemStack _setstack = new ItemStack(Items.TRIDENT).copy();
                            _setstack.setCount(1);
                            _entity.setStackInHand(Hand.MAIN_HAND, _setstack);
                            if (_entity instanceof PlayerEntity _player)
                                _player.getInventory().markDirty();
                        }
                        if (world instanceof World _level)
                            SoundUtil.playSound(_level, x, y, z, new Identifier("item.trident.throw"), 1, 1);
                        if (entity instanceof LivingEntity _entity)
                            _entity.swingHand(Hand.MAIN_HAND, true);
                        World projectileLevel = entity.getWorld();
                        if (!projectileLevel.isClient) {
                            TridentEntity entityToSpawn = new TridentEntity(EntityType.TRIDENT, projectileLevel);
                            entityToSpawn.setOwner(entity);
                            entityToSpawn.setDamage(5);
                            entityToSpawn.setPunch(3);
                            entityToSpawn.setPos(entity.getX(), entity.getEyeY() - 0.1, entity.getZ());
                            entityToSpawn.setVelocity(entity.getRotationVector().x, entity.getRotationVector().y, entity.getRotationVector().z, 5, 0);
                            projectileLevel.spawnEntity(entityToSpawn);
                        }
                    };
                    Timeout.create(2, runnable);
                    Timeout.create(12, runnable);
                    Timeout.create(22, runnable);
                    Timeout.create(24, () -> {
                        if (entity instanceof LivingEntity _entity) {
                            ItemStack _setstack = new ItemStack(Blocks.CAVE_AIR).copy();
                            _setstack.setCount(1);
                            _entity.setStackInHand(Hand.OFF_HAND, _setstack);
                            if (_entity instanceof PlayerEntity _player)
                                _player.getInventory().markDirty();
                        }
                    });
                }
                if (attack == 4) {
                    if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient) {
                        _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 3, false, true));
                        _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100, 2, false, true));
                    }
                    if (world instanceof ServerWorld _level)
                        for (int index0 = 0; index0 < 18; index0++) {
                            Entity entityToSpawn = EntityType.LIGHTNING_BOLT.spawn(_level, BlockPos.ofFloored(x + MathHelper.nextDouble(Random.create(), -10, 10), y, z + MathHelper.nextDouble(Random.create(), -10, 10)), SpawnReason.MOB_SUMMONED);
                            if (entityToSpawn != null)
                                entityToSpawn.setYaw(world.getRandom().nextFloat() * 360F);
                        }
                }
                if (attack == 5) {
                    if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
                        _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 100, false, false));
                    if (world instanceof ServerWorld _level) {
                        LightningEntity entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                        entityToSpawn.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(BlockPos.ofFloored(x, y, z)));
                        _level.spawnEntity(entityToSpawn);
                    }
                    Timeout.create(20, () -> {
                        if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient)
                            _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 255, 50, false, false));
                        if (world instanceof World _level)
                            SoundUtil.playSound(_level, x, y, z, new Identifier(AnnoyingVillagers.MOD_ID, "blue.demon.trident.festival"), 1, 1);
                        CommandHelper.execute(entity, "tellraw @a \"<Blue Demon> Trident Festival!!!!!!!!!!\"");
                    });
                    Timeout.create(60, () -> {
                        for (int index1 = 0; index1 < 18; index1++) {
                            if (world instanceof ServerWorld _level) {
                                Entity entityToSpawn = EntityType.LIGHTNING_BOLT.spawn(_level, BlockPos.ofFloored(x + MathHelper.nextDouble(Random.create(), -10, 10), y, z + MathHelper.nextDouble(Random.create(), -10, 10)), SpawnReason.MOB_SUMMONED);
                                if (entityToSpawn != null)
                                    entityToSpawn.setYaw(world.getRandom().nextFloat() * 360F);
                            }
                        }
                        if (world instanceof World _level) {
                            LightningEntity entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                            if (entityToSpawn != null) {
                                entityToSpawn.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(BlockPos.ofFloored(x, y, z)));
                                _level.spawnEntity(entityToSpawn);
                            }
                        }
                        if (world instanceof World _level && !_level.isClient) {
                            _level.createExplosion(null, x, y, z, 8, World.ExplosionSourceType.TNT);
                            _level.createExplosion(null, x + MathHelper.nextInt(Random.create(), 5, 10), y, z, 7, World.ExplosionSourceType.TNT);
                            _level.createExplosion(null, x - MathHelper.nextInt(Random.create(), 5, 10), y, z, 7, World.ExplosionSourceType.TNT);
                            _level.createExplosion(null, x + MathHelper.nextInt(Random.create(), 5, 10), y, z + MathHelper.nextInt(Random.create(), 5, 10), 7, World.ExplosionSourceType.TNT);
                            _level.createExplosion(null, x - MathHelper.nextInt(Random.create(), 5, 10), y, z - MathHelper.nextInt(Random.create(), 5, 10), 7, World.ExplosionSourceType.TNT);
                            _level.createExplosion(null, x + MathHelper.nextInt(Random.create(), 5, 10), y, z - MathHelper.nextInt(Random.create(), 5, 10), 7, World.ExplosionSourceType.TNT);
                            _level.createExplosion(null, x - MathHelper.nextInt(Random.create(), 5, 10), y, z + MathHelper.nextInt(Random.create(), 5, 10), 7, World.ExplosionSourceType.TNT);
                            _level.createExplosion(null, x, y, z + MathHelper.nextInt(Random.create(), 5, 10), 7, World.ExplosionSourceType.TNT);
                            _level.createExplosion(null, x, y, z - MathHelper.nextInt(Random.create(), 5, 10), 7, World.ExplosionSourceType.TNT);
                        }
                    });
                }
            }
        }
    }
}
