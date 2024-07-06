package com.iafenvoy.annoyingvillagers.procedures;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraftforge.registries.ForgeRegistries;
import com.iafenvoy.annoyingvillagers.AnnoyingMod;

public class ShootFireBall {
    public static void execute(WorldAccess world, double x, double y, double z, Entity entity, ItemStack itemstack) {
        if (entity == null)
            return;
        if ((entity instanceof PlayerEntity _plrCldCheck1 && _plrCldCheck1.getItemCooldownManager().isCoolingDown(itemstack.getItem())) == false) {
            if (entity.isSneaking()) {
                if (entity instanceof PlayerEntity _player)
                    _player.getItemCooldownManager().set(itemstack.getItem(), 100);
                if (world instanceof World _level) {
                    if (!_level.isClient()) {
                        _level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("entity.blaze.shoot")), SoundCategory.NEUTRAL, 1, 1);
                    } else {
                        _level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("entity.blaze.shoot")), SoundCategory.NEUTRAL, 1, 1, false);
                    }
                }
                {
                    Entity _shootFrom = entity;
                    World projectileLevel = _shootFrom.getWorld();
                    if (!projectileLevel.isClient()) {
                        ProjectileEntity _entityToSpawn = new Object() {
                            public ProjectileEntity getFireball(World level, Entity shooter) {
                                ExplosiveProjectileEntity entityToSpawn = new SmallFireballEntity(EntityType.SMALL_FIREBALL, level);
                                entityToSpawn.setOwner(shooter);
                                return entityToSpawn;
                            }
                        }.getFireball(projectileLevel, entity);
                        _entityToSpawn.setPosition(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
                        _entityToSpawn.setVelocity(_shootFrom.getRotationVector().x, _shootFrom.getRotationVector().y, _shootFrom.getRotationVector().z, 4, 0);
                        projectileLevel.spawnEntity(_entityToSpawn);
                    }
                }
                AnnoyingMod.queueServerWork(10, () -> {
                    if (world instanceof Level _level) {
                        if (!_level.isClientSide()) {
                            _level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.blaze.shoot")), SoundSource.NEUTRAL, 1, 1);
                        } else {
                            _level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.blaze.shoot")), SoundSource.NEUTRAL, 1, 1, false);
                        }
                    }
                    {
                        Entity _shootFrom = entity;
                        Level projectileLevel = _shootFrom.level();
                        if (!projectileLevel.isClientSide()) {
                            Projectile _entityToSpawn = new Object() {
                                public Projectile getFireball(Level level, Entity shooter) {
                                    AbstractHurtingProjectile entityToSpawn = new SmallFireball(EntityType.SMALL_FIREBALL, level);
                                    entityToSpawn.setOwner(shooter);
                                    return entityToSpawn;
                                }
                            }.getFireball(projectileLevel, entity);
                            _entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
                            _entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 4, 0);
                            projectileLevel.addFreshEntity(_entityToSpawn);
                        }
                    }
                    AnnoyingMod.queueServerWork(10, () -> {
                        if (world instanceof Level _level) {
                            if (!_level.isClientSide()) {
                                _level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("entity.blaze.shoot")), SoundSource.NEUTRAL, 1, 1);
                            } else {
                                _level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("entity.blaze.shoot")), SoundSource.NEUTRAL, 1, 1, false);
                            }
                        }
                        {
                            Entity _shootFrom = entity;
                            Level projectileLevel = _shootFrom.level();
                            if (!projectileLevel.isClientSide()) {
                                Projectile _entityToSpawn = new Object() {
                                    public Projectile getFireball(Level level, Entity shooter) {
                                        AbstractHurtingProjectile entityToSpawn = new SmallFireball(EntityType.SMALL_FIREBALL, level);
                                        entityToSpawn.setOwner(shooter);
                                        return entityToSpawn;
                                    }
                                }.getFireball(projectileLevel, entity);
                                _entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
                                _entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 4, 0);
                                projectileLevel.addFreshEntity(_entityToSpawn);
                            }
                        }
                    });
                });
            }
        }
    }
}
