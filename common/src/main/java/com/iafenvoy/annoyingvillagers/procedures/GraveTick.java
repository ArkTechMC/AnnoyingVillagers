package com.iafenvoy.annoyingvillagers.procedures;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
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
import net.minecraft.world.entity.*;
import net.minecraftforge.registries.ForgeRegistries;
import com.iafenvoy.annoyingvillagers.AnnoyingMod;
import com.iafenvoy.annoyingvillagers.init.AnnoyingModItem;

import java.util.Comparator;
import java.util.List;

public class GraveTick {
	public static void execute(WorldAccess world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		Entity entityA = null;
		Entity tgt = null;
		Entity entityB = null;
		boolean flag = false;
		boolean falgTarget = false;
		boolean hasMelee = false;
		ItemStack ci = ItemStack.EMPTY;
		ItemStack i0 = ItemStack.EMPTY;
		ItemStack i1 = ItemStack.EMPTY;
		ItemStack i2 = ItemStack.EMPTY;
		ItemStack i3 = ItemStack.EMPTY;
		ItemStack handitem = ItemStack.EMPTY;
		double i = 0;
		double attack = 0;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) < 181) {
			if (entity.getPersistentData().getBoolean("pashe2") == false) {
				entity.getPersistentData().putBoolean("pashe2", true);
				entity.setCustomName(Text.literal("X_Grave_X Pashe II"));
				if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
					_entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 7, false, false));
				if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
					_entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1000, 2, false, false));
				if (world instanceof World _level) {
					if (!_level.isClient()) {
						_level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("block.anvil.use")), SoundCategory.NEUTRAL, 1, 1);
					} else {
						_level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("block.anvil.use")), SoundCategory.NEUTRAL, 1, 1, false);
					}
				}
				{
					Entity _entity = entity;
					if (_entity instanceof PlayerEntity _player) {
						_player.getInventory().armor.set(2, new ItemStack(AnnoyingModItem.GRAVE_S_PALADIN_CHESTPLATE.get()));
						_player.getInventory().markDirty();
					} else if (_entity instanceof LivingEntity _living) {
						_living.equipStack(EquipmentSlot.CHEST, new ItemStack(AnnoyingModItem.GRAVE_S_PALADIN_CHESTPLATE.get()));
					}
				}
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = new ItemStack(AnnoyingModItem.DIAMOND_LONGSWORD.get()).copy();
					_setstack.setCount(1);
					_entity.setStackInHand(Hand.MAIN_HAND, _setstack);
					if (_entity instanceof PlayerEntity _player)
						_player.getInventory().markDirty();
				}
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = new ItemStack(AnnoyingModItem.DIAMOND_LONGSWORD.get()).copy();
					_setstack.setCount(1);
					_entity.setStackInHand(Hand.OFF_HAND, _setstack);
					if (_entity instanceof PlayerEntity _player)
						_player.getInventory().markDirty();
				}
				if (world instanceof World _level) {
					if (!_level.isClient()) {
						_level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("annoying_villagers:grave.pashe2.iwky")), SoundCategory.NEUTRAL, 1, 1);
					} else {
						_level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("annoying_villagers:grave.pashe2.iwky")), SoundCategory.NEUTRAL, 1, 1, false);
					}
				}
				{
					Entity _ent = entity;
					if (!_ent.getWorld().isClient() && _ent.getServer() != null) {
						_ent.getServer().getCommandManager().executeWithPrefix(new ServerCommandSource(CommandOutput.DUMMY, _ent.getPos(), _ent.getRotationClient(), _ent.getWorld() instanceof ServerWorld ? (ServerWorld) _ent.getWorld() : null, 4,
								_ent.getName().getString(), _ent.getDisplayName(), _ent.getWorld().getServer(), _ent), "tellraw @a \"<X_Grave_X> I will kill you... I WILL KILL YOU!!!!!!!\"");
					}
				}
			}
		}

		entity.getPersistentData().putDouble("timer", (entity.getPersistentData().getDouble("timer") + 1));
		if (entity.getPersistentData().getDouble("timer") == 80) {
			entity.getPersistentData().putDouble("timer", 0);
			attack = MathHelper.nextInt(Random.create(), 1, 8);
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
													ItemStack _setstack = new ItemStack(AnnoyingModItem.DIAMOND_LONGSWORD.get()).copy();
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
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(AnnoyingModItem.DIAMOND_LONGSWORD.get()).copy();
							_setstack.setCount(1);
							_entity.setStackInHand(Hand.MAIN_HAND, _setstack);
							if (_entity instanceof PlayerEntity _player)
								_player.getInventory().markDirty();
						}
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(AnnoyingModItem.DIAMOND_LONGSWORD.get()).copy();
							_setstack.setCount(1);
							_entity.setStackInHand(Hand.OFF_HAND, _setstack);
							if (_entity instanceof PlayerEntity _player)
								_player.getInventory().markDirty();
						}
						{
							Entity _entity = entity;
							if (_entity instanceof PlayerEntity _player) {
								_player.getInventory().armor.set(2, new ItemStack(AnnoyingModItem.GRAVE_S_PALADIN_CHESTPLATE.get()));
								_player.getInventory().markDirty();
							} else if (_entity instanceof LivingEntity _living) {
								_living.equipStack(EquipmentSlot.CHEST, new ItemStack(AnnoyingModItem.GRAVE_S_PALADIN_CHESTPLATE.get()));
							}
						}
						if (world instanceof World _level) {
							if (!_level.isClient()) {
								_level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("item.armor.equip_diamond")), SoundCategory.NEUTRAL, 1, 1);
							} else {
								_level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("item.armor.equip_diamond")), SoundCategory.NEUTRAL, 1, 1, false);
							}
						}
					}
					if (attack == 6) {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(AnnoyingModItem.PALADIN_S_SWORD.get()).copy();
							_setstack.setCount(1);
							_entity.setStackInHand(Hand.MAIN_HAND, _setstack);
							if (_entity instanceof PlayerEntity _player)
								_player.getInventory().markDirty();
						}
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(AnnoyingModItem.PALADIN_S_SWORD.get()).copy();
							_setstack.setCount(1);
							_entity.setStackInHand(Hand.OFF_HAND, _setstack);
							if (_entity instanceof PlayerEntity _player)
								_player.getInventory().markDirty();
						}
						{
							Entity _entity = entity;
							if (_entity instanceof PlayerEntity _player) {
								_player.getInventory().armor.set(2, new ItemStack(AnnoyingModItem.GRAVE_S_CHESTPLATE.get()));
								_player.getInventory().markDirty();
							} else if (_entity instanceof LivingEntity _living) {
								_living.equipStack(EquipmentSlot.CHEST, new ItemStack(AnnoyingModItem.GRAVE_S_CHESTPLATE.get()));
							}
						}
						if (world instanceof World _level) {
							if (!_level.isClient()) {
								_level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("item.armor.equip_diamond")), SoundCategory.NEUTRAL, 1, 1);
							} else {
								_level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("item.armor.equip_diamond")), SoundCategory.NEUTRAL, 1, 1, false);
							}
						}
					}
					if (attack == 7) {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(AnnoyingModItem.FISHING_ROD.get()).copy();
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
								}.getArrow(projectileLevel, entity, 4, 1);
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
									}.getArrow(projectileLevel, entity, 4, 1);
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
										}.getArrow(projectileLevel, entity, 4, 1);
										_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
										_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 3, 0);
										projectileLevel.addFreshEntity(_entityToSpawn);
									}
								}
								if (entity instanceof LivingEntity _entity)
									_entity.swing(InteractionHand.MAIN_HAND, true);
								AnnoyingMod.queueServerWork(10, () -> {
									if (entity instanceof LivingEntity _entity) {
										ItemStack _setstack = new ItemStack(AnnoyingModItem.PALADIN_S_SWORD.get()).copy();
										_setstack.setCount(1);
										_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
										if (_entity instanceof Player _player)
											_player.getInventory().setChanged();
									}
									if (entity instanceof LivingEntity _entity) {
										ItemStack _setstack = new ItemStack(AnnoyingModItem.PALADIN_S_SWORD.get()).copy();
										_setstack.setCount(1);
										_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
										if (_entity instanceof Player _player)
											_player.getInventory().setChanged();
									}
									{
										Entity _entity = entity;
										if (_entity instanceof Player _player) {
											_player.getInventory().armor.set(2, new ItemStack(AnnoyingModItem.GRAVE_S_CHESTPLATE.get()));
											_player.getInventory().setChanged();
										} else if (_entity instanceof LivingEntity _living) {
											_living.setItemSlot(EquipmentSlot.CHEST, new ItemStack(AnnoyingModItem.GRAVE_S_CHESTPLATE.get()));
										}
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
							ItemStack _setstack = new ItemStack(Items.SHIELD).copy();
							_setstack.setCount(1);
							_entity.setStackInHand(Hand.OFF_HAND, _setstack);
							if (_entity instanceof PlayerEntity _player)
								_player.getInventory().markDirty();
						}
						if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
							_entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 60, 6, false, false));
						AnnoyingMod.queueServerWork(30, () -> {
							if (entity instanceof LivingEntity _entity)
								_entity.swing(InteractionHand.MAIN_HAND, true);
							if (entity instanceof LivingEntity _entity) {
								ItemStack _setstack = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy();
								_setstack.setCount(1);
								_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
								if (_entity instanceof Player _player)
									_player.getInventory().setChanged();
							}
						});
					}
					if (attack == 4) {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(AnnoyingModItem.KNIFE_WITH_TNT.get()).copy();
							_setstack.setCount(1);
							_entity.setStackInHand(Hand.MAIN_HAND, _setstack);
							if (_entity instanceof PlayerEntity _player)
								_player.getInventory().markDirty();
						}
						AnnoyingMod.queueServerWork(15, () -> {
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 10, 100, false, false));
							AnnoyingMod.queueServerWork(5, () -> {
								if (entity instanceof LivingEntity _entity)
									_entity.swing(InteractionHand.MAIN_HAND, true);
								if (world instanceof Level _level && !_level.isClientSide())
									_level.explode(null, x, y, z, 5, Level.ExplosionInteraction.TNT);
								if (entity instanceof LivingEntity _entity) {
									ItemStack _setstack = new ItemStack(AnnoyingModItem.KNIFE.get()).copy();
									_setstack.setCount(1);
									_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
									if (_entity instanceof Player _player)
										_player.getInventory().setChanged();
								}
							});
						});
					}
				}
			}
		}
	}
}
