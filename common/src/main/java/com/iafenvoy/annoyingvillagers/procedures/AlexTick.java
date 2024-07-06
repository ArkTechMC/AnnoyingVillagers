package com.iafenvoy.annoyingvillagers.procedures;

import com.iafenvoy.annoyingvillagers.AnnoyingMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraftforge.registries.ForgeRegistries;
import com.iafenvoy.annoyingvillagers.init.AnnoyingModItem;

import java.util.List;
import java.util.Comparator;

public class AlexTick {
    public static void execute(WorldAccess world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        double attack = 0;
        double phase = 0;
        double missletimer = 0;
        double previousRecipe = 0;
        double dis = 0;

        entity.getPersistentData().putDouble("timer", (entity.getPersistentData().getDouble("timer") + 1));
        if (entity.getPersistentData().getDouble("timer") == 30) {
            entity.getPersistentData().putDouble("timer", 0);
            attack = MathHelper.nextInt(Random.create(), 1, 11);
        }
        {
            final Vec3d _center = new Vec3d(x, y, z);
            List<Entity> _entfound = world.getEntitiesByClass(Entity.class, new Box(_center, _center).expand(32 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.squaredDistanceTo(_center))).toList();
            for (Entity entityiterator : _entfound) {
                if ((entity instanceof MobEntity _mobEnt ? (Entity) _mobEnt.getTarget() : null) == entityiterator) {
                    if (attack == 1) {
                        if (entity instanceof LivingEntity _entity) {
                            ItemStack _setstack = new ItemStack(Items.GOLDEN_APPLE).copy();
                            _setstack.setCount(1);
                            _entity.setStackInHand(Hand.MAIN_HAND, _setstack);
                            if (_entity instanceof PlayerEntity _player)
                                _player.getInventory().markDirty();
                        }
                        if (world instanceof World _level) {
                            if (!_level.isClient()) {
                                _level.playSound(null, BlockPos.ofFloored(x, y + 1, z), Registries.SOUND_EVENT.get(new Identifier("entity.generic.eat")), SoundCategory.NEUTRAL, (float) 1.2,
                                        (float) MathHelper.nextDouble(Random.create(), 0.9, 1.1));
                            } else {
                                _level.playSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new Identifier("entity.generic.eat")), SoundCategory.NEUTRAL, (float) 1.2, (float) MathHelper.nextDouble(Random.create(), 0.9, 1.1), false);
                            }
                        }
                        if (entity instanceof LivingEntity _entity)
                            _entity.swingHand(Hand.MAIN_HAND, true);
                        AnnoyingMod.queueServerWork(5, () -> {
                            if (world instanceof Level _level) {
                                if (!_level.isClientSide()) {
                                    _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                            (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                } else {
                                    _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2, (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1),
                                            false);
                                }
                            }
                            if (entity instanceof LivingEntity _entity)
                                _entity.swing(InteractionHand.MAIN_HAND, true);
                            AnnoyingMod.queueServerWork(5, () -> {
                                if (world instanceof Level _level) {
                                    if (!_level.isClientSide()) {
                                        _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                    } else {
                                        _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2, (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1),
                                                false);
                                    }
                                }
                                if (entity instanceof LivingEntity _entity)
                                    _entity.swing(InteractionHand.MAIN_HAND, true);
                                AnnoyingMod.queueServerWork(5, () -> {
                                    if (world instanceof Level _level) {
                                        if (!_level.isClientSide()) {
                                            _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                    (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                        } else {
                                            _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                    (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
                                        }
                                    }
                                    if (entity instanceof LivingEntity _entity)
                                        _entity.swing(InteractionHand.MAIN_HAND, true);
                                    AnnoyingMod.queueServerWork(5, () -> {
                                        if (world instanceof Level _level) {
                                            if (!_level.isClientSide()) {
                                                _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                        (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                            } else {
                                                _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                        (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
                                            }
                                        }
                                        if (entity instanceof LivingEntity _entity)
                                            _entity.swing(InteractionHand.MAIN_HAND, true);
                                        AnnoyingMod.queueServerWork(5, () -> {
                                            if (world instanceof Level _level) {
                                                if (!_level.isClientSide()) {
                                                    _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                            (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                                } else {
                                                    _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                            (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
                                                }
                                            }
                                            if (entity instanceof LivingEntity _entity)
                                                _entity.swing(InteractionHand.MAIN_HAND, true);
                                            AnnoyingMod.queueServerWork(5, () -> {
                                                if (world instanceof Level _level) {
                                                    if (!_level.isClientSide()) {
                                                        _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                                (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                                    } else {
                                                        _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                                (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
                                                    }
                                                }
                                                if (entity instanceof LivingEntity _entity)
                                                    _entity.swing(InteractionHand.MAIN_HAND, true);
                                                if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                                                    _entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 4, false, true));
                                                if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                                                    _entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 7200, 2, false, true));
                                                if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                                                    _entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 2, false, true));
                                                if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                                                    _entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 1, false, true));
                                                if (entity instanceof LivingEntity _entity) {
                                                    ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD).copy();
                                                    _setstack.setCount(1);
                                                    _entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
                                                    if (_entity instanceof Player _player)
                                                        _player.getInventory().setChanged();
                                                }
                                            });
                                        });
                                    });
                                });
                            });
                        });
                    }
                    if (attack == 5) {
                        if (world instanceof World _level) {
                            if (!_level.isClient()) {
                                _level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("entity.ender_pearl.throw")), SoundCategory.NEUTRAL, 1, 1);
                            } else {
                                _level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("entity.ender_pearl.throw")), SoundCategory.NEUTRAL, 1, 1, false);
                            }
                        }
                        {
                            Entity _shootFrom = entity;
                            World projectileLevel = _shootFrom.getWorld();
                            if (!projectileLevel.isClient()) {
                                ProjectileEntity _entityToSpawn = new Object() {
                                    public ProjectileEntity getProjectile(World level, Entity shooter) {
                                        ProjectileEntity entityToSpawn = new EnderPearlEntity(EntityType.ENDER_PEARL, level);
                                        entityToSpawn.setOwner(shooter);
                                        return entityToSpawn;
                                    }
                                }.getProjectile(projectileLevel, entity);
                                _entityToSpawn.setPosition(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
                                _entityToSpawn.setVelocity(_shootFrom.getRotationVector().x, _shootFrom.getRotationVector().y, _shootFrom.getRotationVector().z, 1, 0);
                                projectileLevel.spawnEntity(_entityToSpawn);
                            }
                        }
                    }
                    if (attack == 6) {
                        if (entity instanceof LivingEntity _entity) {
                            ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD).copy();
                            _setstack.setCount(1);
                            _entity.setStackInHand(Hand.MAIN_HAND, _setstack);
                            if (_entity instanceof PlayerEntity _player)
                                _player.getInventory().markDirty();
                        }
                        if (entity instanceof LivingEntity _entity)
                            _entity.swingHand(Hand.MAIN_HAND, true);
                        if (world instanceof World _level) {
                            if (!_level.isClient()) {
                                _level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("entity.witch.throw")), SoundCategory.NEUTRAL, 1, 1);
                            } else {
                                _level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("entity.witch.throw")), SoundCategory.NEUTRAL, 1, 1, false);
                            }
                        }
                        AnnoyingMod.queueServerWork(5, () -> {
                            if (entity instanceof LivingEntity _entity)
                                _entity.swing(InteractionHand.MAIN_HAND, true);
                            if (world instanceof Level _level) {
                                if (!_level.isClientSide()) {
                                    _level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.witch.throw")), SoundSource.NEUTRAL, 1, 1);
                                } else {
                                    _level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.witch.throw")), SoundSource.NEUTRAL, 1, 1, false);
                                }
                            }
                            AnnoyingMod.queueServerWork(5, () -> {
                                if (entity instanceof LivingEntity _entity)
                                    _entity.swing(InteractionHand.MAIN_HAND, true);
                                if (world instanceof Level _level) {
                                    if (!_level.isClientSide()) {
                                        _level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.witch.throw")), SoundSource.NEUTRAL, 1, 1);
                                    } else {
                                        _level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.witch.throw")), SoundSource.NEUTRAL, 1, 1, false);
                                    }
                                }
                            });
                        });
                    }
                    if (attack == 7) {
                        if (entity instanceof LivingEntity _entity) {
                            ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD).copy();
                            _setstack.setCount(1);
                            _entity.setStackInHand(Hand.MAIN_HAND, _setstack);
                            if (_entity instanceof PlayerEntity _player)
                                _player.getInventory().markDirty();
                        }
                        if (entity instanceof LivingEntity _entity)
                            _entity.swingHand(Hand.MAIN_HAND, true);
                        if (world instanceof World _level) {
                            if (!_level.isClient()) {
                                _level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("entity.witch.throw")), SoundCategory.NEUTRAL, 1, 1);
                            } else {
                                _level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("entity.witch.throw")), SoundCategory.NEUTRAL, 1, 1, false);
                            }
                        }
                        AnnoyingMod.queueServerWork(5, () -> {
                            if (entity instanceof LivingEntity _entity)
                                _entity.swing(InteractionHand.MAIN_HAND, true);
                            if (world instanceof Level _level) {
                                if (!_level.isClientSide()) {
                                    _level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.witch.throw")), SoundSource.NEUTRAL, 1, 1);
                                } else {
                                    _level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.witch.throw")), SoundSource.NEUTRAL, 1, 1, false);
                                }
                            }
                            AnnoyingMod.queueServerWork(5, () -> {
                                if (entity instanceof LivingEntity _entity)
                                    _entity.swing(InteractionHand.MAIN_HAND, true);
                                if (world instanceof Level _level) {
                                    if (!_level.isClientSide()) {
                                        _level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.witch.throw")), SoundSource.NEUTRAL, 1, 1);
                                    } else {
                                        _level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.witch.throw")), SoundSource.NEUTRAL, 1, 1, false);
                                    }
                                }
                            });
                        });
                    }
                    if (attack == 2) {
                        if (world instanceof World _level) {
                            if (!_level.isClient()) {
                                _level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("entity.ender_pearl.throw")), SoundCategory.NEUTRAL, 1, 1);
                            } else {
                                _level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("entity.ender_pearl.throw")), SoundCategory.NEUTRAL, 1, 1, false);
                            }
                        }
                        {
                            Entity _shootFrom = entity;
                            World projectileLevel = _shootFrom.getWorld();
                            if (!projectileLevel.isClient()) {
                                ProjectileEntity _entityToSpawn = new Object() {
                                    public ProjectileEntity getProjectile(World level, Entity shooter) {
                                        ProjectileEntity entityToSpawn = new EnderPearlEntity(EntityType.ENDER_PEARL, level);
                                        entityToSpawn.setOwner(shooter);
                                        return entityToSpawn;
                                    }
                                }.getProjectile(projectileLevel, entity);
                                _entityToSpawn.setPosition(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
                                _entityToSpawn.setVelocity(_shootFrom.getRotationVector().x, _shootFrom.getRotationVector().y, _shootFrom.getRotationVector().z, 1, 0);
                                projectileLevel.spawnEntity(_entityToSpawn);
                            }
                        }
                    }
                    if (attack == 8) {
                        if (entity instanceof LivingEntity _entity) {
                            ItemStack _setstack = new ItemStack(Items.BOW).copy();
                            _setstack.setCount(1);
                            _entity.setStackInHand(Hand.MAIN_HAND, _setstack);
                            if (_entity instanceof PlayerEntity _player)
                                _player.getInventory().markDirty();
                        }
                        if (entity instanceof LivingEntity _entity)
                            _entity.swingHand(Hand.MAIN_HAND, true);
                        if (world instanceof World _level) {
                            if (!_level.isClient()) {
                                _level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("entity.arrow.shoot")), SoundCategory.NEUTRAL, 1, 1);
                            } else {
                                _level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("entity.arrow.shoot")), SoundCategory.NEUTRAL, 1, 1, false);
                            }
                        }
                        {
                            Entity _shootFrom = entity;
                            World projectileLevel = _shootFrom.getWorld();
                            if (!projectileLevel.isClient()) {
                                ProjectileEntity _entityToSpawn = new Object() {
                                    public ProjectileEntity getArrow(World level, Entity shooter, float damage, int knockback) {
                                        PersistentProjectileEntity entityToSpawn = new ArrowEntity(EntityType.ARROW, level);
                                        entityToSpawn.setOwner(shooter);
                                        entityToSpawn.setDamage(damage);
                                        entityToSpawn.setPunch(knockback);
                                        return entityToSpawn;
                                    }
                                }.getArrow(projectileLevel, entity, 7, 4);
                                _entityToSpawn.setPosition(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
                                _entityToSpawn.setVelocity(_shootFrom.getRotationVector().x, _shootFrom.getRotationVector().y, _shootFrom.getRotationVector().z, 3, 0);
                                projectileLevel.spawnEntity(_entityToSpawn);
                            }
                        }
                        AnnoyingMod.queueServerWork(10, () -> {
                            if (world instanceof Level _level) {
                                if (!_level.isClientSide()) {
                                    _level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.arrow.shoot")), SoundSource.NEUTRAL, 1, 1);
                                } else {
                                    _level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.arrow.shoot")), SoundSource.NEUTRAL, 1, 1, false);
                                }
                            }
                            {
                                Entity _shootFrom = entity;
                                Level projectileLevel = _shootFrom.level();
                                if (!projectileLevel.isClientSide()) {
                                    Projectile _entityToSpawn = new Object() {
                                        public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
                                            AbstractArrow entityToSpawn = new Arrow(EntityType.ARROW, level);
                                            entityToSpawn.setOwner(shooter);
                                            entityToSpawn.setBaseDamage(damage);
                                            entityToSpawn.setKnockback(knockback);
                                            return entityToSpawn;
                                        }
                                    }.getArrow(projectileLevel, entity, 7, 4);
                                    _entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
                                    _entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 3, 0);
                                    projectileLevel.addFreshEntity(_entityToSpawn);
                                }
                            }
                            if (entity instanceof LivingEntity _entity)
                                _entity.swing(InteractionHand.MAIN_HAND, true);
                            AnnoyingMod.queueServerWork(10, () -> {
                                if (world instanceof Level _level) {
                                    if (!_level.isClientSide()) {
                                        _level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.arrow.shoot")), SoundSource.NEUTRAL, 1, 1);
                                    } else {
                                        _level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.arrow.shoot")), SoundSource.NEUTRAL, 1, 1, false);
                                    }
                                }
                                {
                                    Entity _shootFrom = entity;
                                    Level projectileLevel = _shootFrom.level();
                                    if (!projectileLevel.isClientSide()) {
                                        Projectile _entityToSpawn = new Object() {
                                            public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
                                                AbstractArrow entityToSpawn = new Arrow(EntityType.ARROW, level);
                                                entityToSpawn.setOwner(shooter);
                                                entityToSpawn.setBaseDamage(damage);
                                                entityToSpawn.setKnockback(knockback);
                                                return entityToSpawn;
                                            }
                                        }.getArrow(projectileLevel, entity, 7, 4);
                                        _entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
                                        _entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 3, 0);
                                        projectileLevel.addFreshEntity(_entityToSpawn);
                                    }
                                }
                                if (entity instanceof LivingEntity _entity)
                                    _entity.swing(InteractionHand.MAIN_HAND, true);
                                AnnoyingMod.queueServerWork(10, () -> {
                                    if (entity instanceof LivingEntity _entity) {
                                        ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD).copy();
                                        _setstack.setCount(1);
                                        _entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
                                        if (_entity instanceof Player _player)
                                            _player.getInventory().setChanged();
                                    }
                                    if (world instanceof Level _level) {
                                        if (!_level.isClientSide()) {
                                            _level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("item.armor.equip_diamond")), SoundSource.NEUTRAL, 1, 1);
                                        } else {
                                            _level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("item.armor.equip_diamond")), SoundSource.NEUTRAL, 1, 1, false);
                                        }
                                    }
                                });
                            });
                        });
                    }
                    if (attack == 3) {
                        if (entity instanceof LivingEntity _entity) {
                            ItemStack _setstack = new ItemStack(Items.BOW).copy();
                            _setstack.setCount(1);
                            _entity.setStackInHand(Hand.MAIN_HAND, _setstack);
                            if (_entity instanceof PlayerEntity _player)
                                _player.getInventory().markDirty();
                        }
                        if (entity instanceof LivingEntity _entity)
                            _entity.swingHand(Hand.MAIN_HAND, true);
                        if (world instanceof World _level) {
                            if (!_level.isClient()) {
                                _level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("entity.arrow.shoot")), SoundCategory.NEUTRAL, 1, 1);
                            } else {
                                _level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("entity.arrow.shoot")), SoundCategory.NEUTRAL, 1, 1, false);
                            }
                        }
                        {
                            Entity _shootFrom = entity;
                            World projectileLevel = _shootFrom.getWorld();
                            if (!projectileLevel.isClient()) {
                                ProjectileEntity _entityToSpawn = new Object() {
                                    public ProjectileEntity getArrow(World level, Entity shooter, float damage, int knockback) {
                                        PersistentProjectileEntity entityToSpawn = new ArrowEntity(EntityType.ARROW, level);
                                        entityToSpawn.setOwner(shooter);
                                        entityToSpawn.setDamage(damage);
                                        entityToSpawn.setPunch(knockback);
                                        return entityToSpawn;
                                    }
                                }.getArrow(projectileLevel, entity, 7, 4);
                                _entityToSpawn.setPosition(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
                                _entityToSpawn.setVelocity(_shootFrom.getRotationVector().x, _shootFrom.getRotationVector().y, _shootFrom.getRotationVector().z, 3, 0);
                                projectileLevel.spawnEntity(_entityToSpawn);
                            }
                        }
                        AnnoyingMod.queueServerWork(10, () -> {
                            if (world instanceof Level _level) {
                                if (!_level.isClientSide()) {
                                    _level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.arrow.shoot")), SoundSource.NEUTRAL, 1, 1);
                                } else {
                                    _level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.arrow.shoot")), SoundSource.NEUTRAL, 1, 1, false);
                                }
                            }
                            {
                                Entity _shootFrom = entity;
                                Level projectileLevel = _shootFrom.level();
                                if (!projectileLevel.isClientSide()) {
                                    Projectile _entityToSpawn = new Object() {
                                        public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
                                            AbstractArrow entityToSpawn = new Arrow(EntityType.ARROW, level);
                                            entityToSpawn.setOwner(shooter);
                                            entityToSpawn.setBaseDamage(damage);
                                            entityToSpawn.setKnockback(knockback);
                                            return entityToSpawn;
                                        }
                                    }.getArrow(projectileLevel, entity, 7, 4);
                                    _entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
                                    _entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 3, 0);
                                    projectileLevel.addFreshEntity(_entityToSpawn);
                                }
                            }
                            if (entity instanceof LivingEntity _entity)
                                _entity.swing(InteractionHand.MAIN_HAND, true);
                            AnnoyingMod.queueServerWork(10, () -> {
                                if (world instanceof Level _level) {
                                    if (!_level.isClientSide()) {
                                        _level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.arrow.shoot")), SoundSource.NEUTRAL, 1, 1);
                                    } else {
                                        _level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.arrow.shoot")), SoundSource.NEUTRAL, 1, 1, false);
                                    }
                                }
                                {
                                    Entity _shootFrom = entity;
                                    Level projectileLevel = _shootFrom.level();
                                    if (!projectileLevel.isClientSide()) {
                                        Projectile _entityToSpawn = new Object() {
                                            public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
                                                AbstractArrow entityToSpawn = new Arrow(EntityType.ARROW, level);
                                                entityToSpawn.setOwner(shooter);
                                                entityToSpawn.setBaseDamage(damage);
                                                entityToSpawn.setKnockback(knockback);
                                                return entityToSpawn;
                                            }
                                        }.getArrow(projectileLevel, entity, 7, 4);
                                        _entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
                                        _entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 3, 0);
                                        projectileLevel.addFreshEntity(_entityToSpawn);
                                    }
                                }
                                if (entity instanceof LivingEntity _entity)
                                    _entity.swing(InteractionHand.MAIN_HAND, true);
                                AnnoyingMod.queueServerWork(10, () -> {
                                    if (entity instanceof LivingEntity _entity) {
                                        ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD).copy();
                                        _setstack.setCount(1);
                                        _entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
                                        if (_entity instanceof Player _player)
                                            _player.getInventory().setChanged();
                                    }
                                    if (world instanceof Level _level) {
                                        if (!_level.isClientSide()) {
                                            _level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("item.armor.equip_diamond")), SoundSource.NEUTRAL, 1, 1);
                                        } else {
                                            _level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("item.armor.equip_diamond")), SoundSource.NEUTRAL, 1, 1, false);
                                        }
                                    }
                                });
                            });
                        });
                    }
                    if (attack == 4) {
                        if (entity instanceof LivingEntity _entity) {
                            ItemStack _setstack = new ItemStack(Items.BOW).copy();
                            _setstack.setCount(1);
                            _entity.setStackInHand(Hand.MAIN_HAND, _setstack);
                            if (_entity instanceof PlayerEntity _player)
                                _player.getInventory().markDirty();
                        }
                        if (entity instanceof LivingEntity _entity)
                            _entity.swingHand(Hand.MAIN_HAND, true);
                        if (world instanceof World _level) {
                            if (!_level.isClient()) {
                                _level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("entity.arrow.shoot")), SoundCategory.NEUTRAL, 1, 1);
                            } else {
                                _level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("entity.arrow.shoot")), SoundCategory.NEUTRAL, 1, 1, false);
                            }
                        }
                        {
                            Entity _shootFrom = entity;
                            World projectileLevel = _shootFrom.getWorld();
                            if (!projectileLevel.isClient()) {
                                ProjectileEntity _entityToSpawn = new Object() {
                                    public ProjectileEntity getArrow(World level, Entity shooter, float damage, int knockback) {
                                        PersistentProjectileEntity entityToSpawn = new ArrowEntity(EntityType.ARROW, level);
                                        entityToSpawn.setOwner(shooter);
                                        entityToSpawn.setDamage(damage);
                                        entityToSpawn.setPunch(knockback);
                                        return entityToSpawn;
                                    }
                                }.getArrow(projectileLevel, entity, 7, 4);
                                _entityToSpawn.setPosition(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
                                _entityToSpawn.setVelocity(_shootFrom.getRotationVector().x, _shootFrom.getRotationVector().y, _shootFrom.getRotationVector().z, 3, 0);
                                projectileLevel.spawnEntity(_entityToSpawn);
                            }
                        }
                        AnnoyingMod.queueServerWork(10, () -> {
                            if (world instanceof Level _level) {
                                if (!_level.isClientSide()) {
                                    _level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.arrow.shoot")), SoundSource.NEUTRAL, 1, 1);
                                } else {
                                    _level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.arrow.shoot")), SoundSource.NEUTRAL, 1, 1, false);
                                }
                            }
                            {
                                Entity _shootFrom = entity;
                                Level projectileLevel = _shootFrom.level();
                                if (!projectileLevel.isClientSide()) {
                                    Projectile _entityToSpawn = new Object() {
                                        public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
                                            AbstractArrow entityToSpawn = new Arrow(EntityType.ARROW, level);
                                            entityToSpawn.setOwner(shooter);
                                            entityToSpawn.setBaseDamage(damage);
                                            entityToSpawn.setKnockback(knockback);
                                            return entityToSpawn;
                                        }
                                    }.getArrow(projectileLevel, entity, 7, 4);
                                    _entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
                                    _entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 3, 0);
                                    projectileLevel.addFreshEntity(_entityToSpawn);
                                }
                            }
                            if (entity instanceof LivingEntity _entity)
                                _entity.swing(InteractionHand.MAIN_HAND, true);
                            AnnoyingMod.queueServerWork(10, () -> {
                                if (world instanceof Level _level) {
                                    if (!_level.isClientSide()) {
                                        _level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.arrow.shoot")), SoundSource.NEUTRAL, 1, 1);
                                    } else {
                                        _level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.arrow.shoot")), SoundSource.NEUTRAL, 1, 1, false);
                                    }
                                }
                                {
                                    Entity _shootFrom = entity;
                                    Level projectileLevel = _shootFrom.level();
                                    if (!projectileLevel.isClientSide()) {
                                        Projectile _entityToSpawn = new Object() {
                                            public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
                                                AbstractArrow entityToSpawn = new Arrow(EntityType.ARROW, level);
                                                entityToSpawn.setOwner(shooter);
                                                entityToSpawn.setBaseDamage(damage);
                                                entityToSpawn.setKnockback(knockback);
                                                return entityToSpawn;
                                            }
                                        }.getArrow(projectileLevel, entity, 7, 4);
                                        _entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
                                        _entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 3, 0);
                                        projectileLevel.addFreshEntity(_entityToSpawn);
                                    }
                                }
                                if (entity instanceof LivingEntity _entity)
                                    _entity.swing(InteractionHand.MAIN_HAND, true);
                                AnnoyingMod.queueServerWork(10, () -> {
                                    if (entity instanceof LivingEntity _entity) {
                                        ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD).copy();
                                        _setstack.setCount(1);
                                        _entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
                                        if (_entity instanceof Player _player)
                                            _player.getInventory().setChanged();
                                    }
                                    if (world instanceof Level _level) {
                                        if (!_level.isClientSide()) {
                                            _level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("item.armor.equip_diamond")), SoundSource.NEUTRAL, 1, 1);
                                        } else {
                                            _level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("item.armor.equip_diamond")), SoundSource.NEUTRAL, 1, 1, false);
                                        }
                                    }
                                });
                            });
                        });

                    }
                    if (attack == 9) {
                        if (entity instanceof LivingEntity _entity) {
                            ItemStack _setstack = new ItemStack(Items.GOLDEN_APPLE).copy();
                            _setstack.setCount(1);
                            _entity.setStackInHand(Hand.MAIN_HAND, _setstack);
                            if (_entity instanceof PlayerEntity _player)
                                _player.getInventory().markDirty();
                        }
                        if (world instanceof World _level) {
                            if (!_level.isClient()) {
                                _level.playSound(null, BlockPos.ofFloored(x, y + 1, z), Registries.SOUND_EVENT.get(new Identifier("entity.generic.eat")), SoundCategory.NEUTRAL, (float) 1.2,
                                        (float) MathHelper.nextDouble(Random.create(), 0.9, 1.1));
                            } else {
                                _level.playSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new Identifier("entity.generic.eat")), SoundCategory.NEUTRAL, (float) 1.2, (float) MathHelper.nextDouble(Random.create(), 0.9, 1.1), false);
                            }
                        }
                        if (entity instanceof LivingEntity _entity)
                            _entity.swingHand(Hand.MAIN_HAND, true);
                        AnnoyingMod.queueServerWork(5, () -> {
                            if (world instanceof Level _level) {
                                if (!_level.isClientSide()) {
                                    _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                            (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                } else {
                                    _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2, (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1),
                                            false);
                                }
                            }
                            if (entity instanceof LivingEntity _entity)
                                _entity.swing(InteractionHand.MAIN_HAND, true);
                            AnnoyingMod.queueServerWork(5, () -> {
                                if (world instanceof Level _level) {
                                    if (!_level.isClientSide()) {
                                        _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                    } else {
                                        _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2, (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1),
                                                false);
                                    }
                                }
                                if (entity instanceof LivingEntity _entity)
                                    _entity.swing(InteractionHand.MAIN_HAND, true);
                                AnnoyingMod.queueServerWork(5, () -> {
                                    if (world instanceof Level _level) {
                                        if (!_level.isClientSide()) {
                                            _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                    (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                        } else {
                                            _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                    (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
                                        }
                                    }
                                    if (entity instanceof LivingEntity _entity)
                                        _entity.swing(InteractionHand.MAIN_HAND, true);
                                    AnnoyingMod.queueServerWork(5, () -> {
                                        if (world instanceof Level _level) {
                                            if (!_level.isClientSide()) {
                                                _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                        (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                            } else {
                                                _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                        (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
                                            }
                                        }
                                        if (entity instanceof LivingEntity _entity)
                                            _entity.swing(InteractionHand.MAIN_HAND, true);
                                        AnnoyingMod.queueServerWork(5, () -> {
                                            if (world instanceof Level _level) {
                                                if (!_level.isClientSide()) {
                                                    _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                            (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                                } else {
                                                    _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                            (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
                                                }
                                            }
                                            if (entity instanceof LivingEntity _entity)
                                                _entity.swing(InteractionHand.MAIN_HAND, true);
                                            AnnoyingMod.queueServerWork(5, () -> {
                                                if (world instanceof Level _level) {
                                                    if (!_level.isClientSide()) {
                                                        _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                                (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                                    } else {
                                                        _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                                (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
                                                    }
                                                }
                                                if (entity instanceof LivingEntity _entity)
                                                    _entity.swing(InteractionHand.MAIN_HAND, true);
                                                if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                                                    _entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 4, false, true));
                                                if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                                                    _entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 7200, 2, false, true));
                                                if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                                                    _entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 2, false, true));
                                                if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                                                    _entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 1, false, true));
                                                if (entity instanceof LivingEntity _entity) {
                                                    ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD).copy();
                                                    _setstack.setCount(1);
                                                    _entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
                                                    if (_entity instanceof Player _player)
                                                        _player.getInventory().setChanged();
                                                }
                                            });
                                        });
                                    });
                                });
                            });
                        });
                    }
                    if (attack == 10) {
                        if (entity instanceof LivingEntity _entity) {
                            ItemStack _setstack = new ItemStack(Items.GOLDEN_APPLE).copy();
                            _setstack.setCount(1);
                            _entity.setStackInHand(Hand.MAIN_HAND, _setstack);
                            if (_entity instanceof PlayerEntity _player)
                                _player.getInventory().markDirty();
                        }
                        if (world instanceof World _level) {
                            if (!_level.isClient()) {
                                _level.playSound(null, BlockPos.ofFloored(x, y + 1, z), Registries.SOUND_EVENT.get(new Identifier("entity.generic.eat")), SoundCategory.NEUTRAL, (float) 1.2,
                                        (float) MathHelper.nextDouble(Random.create(), 0.9, 1.1));
                            } else {
                                _level.playSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new Identifier("entity.generic.eat")), SoundCategory.NEUTRAL, (float) 1.2, (float) MathHelper.nextDouble(Random.create(), 0.9, 1.1), false);
                            }
                        }
                        if (entity instanceof LivingEntity _entity)
                            _entity.swingHand(Hand.MAIN_HAND, true);
                        AnnoyingMod.queueServerWork(5, () -> {
                            if (world instanceof Level _level) {
                                if (!_level.isClientSide()) {
                                    _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                            (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                } else {
                                    _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2, (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1),
                                            false);
                                }
                            }
                            if (entity instanceof LivingEntity _entity)
                                _entity.swing(InteractionHand.MAIN_HAND, true);
                            AnnoyingMod.queueServerWork(5, () -> {
                                if (world instanceof Level _level) {
                                    if (!_level.isClientSide()) {
                                        _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                    } else {
                                        _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2, (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1),
                                                false);
                                    }
                                }
                                if (entity instanceof LivingEntity _entity)
                                    _entity.swing(InteractionHand.MAIN_HAND, true);
                                AnnoyingMod.queueServerWork(5, () -> {
                                    if (world instanceof Level _level) {
                                        if (!_level.isClientSide()) {
                                            _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                    (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                        } else {
                                            _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                    (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
                                        }
                                    }
                                    if (entity instanceof LivingEntity _entity)
                                        _entity.swing(InteractionHand.MAIN_HAND, true);
                                    AnnoyingMod.queueServerWork(5, () -> {
                                        if (world instanceof Level _level) {
                                            if (!_level.isClientSide()) {
                                                _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                        (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                            } else {
                                                _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                        (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
                                            }
                                        }
                                        if (entity instanceof LivingEntity _entity)
                                            _entity.swing(InteractionHand.MAIN_HAND, true);
                                        AnnoyingMod.queueServerWork(5, () -> {
                                            if (world instanceof Level _level) {
                                                if (!_level.isClientSide()) {
                                                    _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                            (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                                } else {
                                                    _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                            (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
                                                }
                                            }
                                            if (entity instanceof LivingEntity _entity)
                                                _entity.swing(InteractionHand.MAIN_HAND, true);
                                            AnnoyingMod.queueServerWork(5, () -> {
                                                if (world instanceof Level _level) {
                                                    if (!_level.isClientSide()) {
                                                        _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                                (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                                    } else {
                                                        _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                                (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
                                                    }
                                                }
                                                if (entity instanceof LivingEntity _entity)
                                                    _entity.swing(InteractionHand.MAIN_HAND, true);
                                                if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                                                    _entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 4, false, true));
                                                if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                                                    _entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 7200, 2, false, true));
                                                if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                                                    _entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 2, false, true));
                                                if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                                                    _entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 1, false, true));
                                                if (entity instanceof LivingEntity _entity) {
                                                    ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD).copy();
                                                    _setstack.setCount(1);
                                                    _entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
                                                    if (_entity instanceof Player _player)
                                                        _player.getInventory().setChanged();
                                                }
                                            });
                                        });
                                    });
                                });
                            });
                        });
                    }
                    if (attack == 11) {
                        if (entity instanceof LivingEntity _entity) {
                            ItemStack _setstack = new ItemStack(Items.GOLDEN_APPLE).copy();
                            _setstack.setCount(1);
                            _entity.setStackInHand(Hand.MAIN_HAND, _setstack);
                            if (_entity instanceof PlayerEntity _player)
                                _player.getInventory().markDirty();
                        }
                        if (world instanceof World _level) {
                            if (!_level.isClient()) {
                                _level.playSound(null, BlockPos.ofFloored(x, y + 1, z), Registries.SOUND_EVENT.get(new Identifier("entity.generic.eat")), SoundCategory.NEUTRAL, (float) 1.2,
                                        (float) MathHelper.nextDouble(Random.create(), 0.9, 1.1));
                            } else {
                                _level.playSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new Identifier("entity.generic.eat")), SoundCategory.NEUTRAL, (float) 1.2, (float) MathHelper.nextDouble(Random.create(), 0.9, 1.1), false);
                            }
                        }
                        if (entity instanceof LivingEntity _entity)
                            _entity.swingHand(Hand.MAIN_HAND, true);
                        AnnoyingMod.queueServerWork(5, () -> {
                            if (world instanceof Level _level) {
                                if (!_level.isClientSide()) {
                                    _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                            (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                } else {
                                    _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2, (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1),
                                            false);
                                }
                            }
                            if (entity instanceof LivingEntity _entity)
                                _entity.swing(InteractionHand.MAIN_HAND, true);
                            AnnoyingMod.queueServerWork(5, () -> {
                                if (world instanceof Level _level) {
                                    if (!_level.isClientSide()) {
                                        _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                    } else {
                                        _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2, (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1),
                                                false);
                                    }
                                }
                                if (entity instanceof LivingEntity _entity)
                                    _entity.swing(InteractionHand.MAIN_HAND, true);
                                AnnoyingMod.queueServerWork(5, () -> {
                                    if (world instanceof Level _level) {
                                        if (!_level.isClientSide()) {
                                            _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                    (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                        } else {
                                            _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                    (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
                                        }
                                    }
                                    if (entity instanceof LivingEntity _entity)
                                        _entity.swing(InteractionHand.MAIN_HAND, true);
                                    AnnoyingMod.queueServerWork(5, () -> {
                                        if (world instanceof Level _level) {
                                            if (!_level.isClientSide()) {
                                                _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                        (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                            } else {
                                                _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                        (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
                                            }
                                        }
                                        if (entity instanceof LivingEntity _entity)
                                            _entity.swing(InteractionHand.MAIN_HAND, true);
                                        AnnoyingMod.queueServerWork(5, () -> {
                                            if (world instanceof Level _level) {
                                                if (!_level.isClientSide()) {
                                                    _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                            (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                                } else {
                                                    _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                            (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
                                                }
                                            }
                                            if (entity instanceof LivingEntity _entity)
                                                _entity.swing(InteractionHand.MAIN_HAND, true);
                                            AnnoyingMod.queueServerWork(5, () -> {
                                                if (world instanceof Level _level) {
                                                    if (!_level.isClientSide()) {
                                                        _level.playSound(null, BlockPos.containing(x, y + 1, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                                (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
                                                    } else {
                                                        _level.playLocalSound(x, (y + 1), z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.generic.eat")), SoundSource.NEUTRAL, (float) 1.2,
                                                                (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
                                                    }
                                                }
                                                if (entity instanceof LivingEntity _entity)
                                                    _entity.swing(InteractionHand.MAIN_HAND, true);
                                                if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                                                    _entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 4, false, true));
                                                if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                                                    _entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 7200, 2, false, true));
                                                if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                                                    _entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 2, false, true));
                                                if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                                                    _entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 1, false, true));
                                                if (entity instanceof LivingEntity _entity) {
                                                    ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD).copy();
                                                    _setstack.setCount(1);
                                                    _entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
                                                    if (_entity instanceof Player _player)
                                                        _player.getInventory().setChanged();
                                                }
                                            });
                                        });
                                    });
                                });
                            });
                        });
                    }
                }
            }
        }
    }
}

