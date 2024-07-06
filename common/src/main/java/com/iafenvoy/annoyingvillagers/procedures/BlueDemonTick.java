package com.iafenvoy.annoyingvillagers.procedures;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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

public class BlueDemonTick {
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
		double hl = 0;
		double Dist = 0;
		double i = 0;
		double zj = 0;
		double yj = 0;
		double xj = 0;
		double zn = 0;
		double yn = 0;
		double xn = 0;
		double phase = 0;
		double missletimer = 0;
		double attack = 0;
		double previousRecipe = 0;
		double dis = 0;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) < 140) {
			if (entity.getPersistentData().getBoolean("pashe2") == false) {
				entity.getPersistentData().putBoolean("pashe2", true);
				entity.setCustomName(Text.literal("Blue Demon-Legendary Sword"));
				if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
					_entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 7, false, false));
				if (world instanceof World _level) {
					if (!_level.isClient()) {
						_level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("block.anvil.place")), SoundCategory.NEUTRAL, 1, 1);
					} else {
						_level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("block.anvil.place")), SoundCategory.NEUTRAL, 1, 1, false);
					}
				}
				if (world instanceof World _level) {
					if (!_level.isClient()) {
						_level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("item.armor.equip_diamond")), SoundCategory.NEUTRAL, 1, 1);
					} else {
						_level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("item.armor.equip_diamond")), SoundCategory.NEUTRAL, 1, 1, false);
					}
				}
				if (world instanceof World _level) {
					if (!_level.isClient()) {
						_level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("annoying_villagers:blue.demon.ittrsp")), SoundCategory.NEUTRAL, 1, 1);
					} else {
						_level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("annoying_villagers:blue.demon.ittrsp")), SoundCategory.NEUTRAL, 1, 1, false);
					}
				}
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = new ItemStack(AnnoyingModItem.LEGENDARY_SWORD.get()).copy();
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
				{
					Entity _ent = entity;
					if (!_ent.getWorld().isClient() && _ent.getServer() != null) {
						_ent.getServer().getCommandManager().executeWithPrefix(new ServerCommandSource(CommandOutput.DUMMY, _ent.getPos(), _ent.getRotationClient(), _ent.getWorld() instanceof ServerWorld ? (ServerWorld) _ent.getWorld() : null, 4,
								_ent.getName().getString(), _ent.getDisplayName(), _ent.getWorld().getServer(), _ent), "tellraw @a \"<Blue Demon> It's time to respawn,player...\"");
					}
				}
			}
		}
		entity.getPersistentData().putDouble("timer", (entity.getPersistentData().getDouble("timer") + 1));
		if (entity.getPersistentData().getDouble("timer") == 80) {
			entity.getPersistentData().putDouble("timer", 0);
			attack = MathHelper.nextInt(Random.create(), 1, 7);
		}
		{
			final Vec3d _center = new Vec3d(x, y, z);
			List<Entity> _entfound = world.getEntitiesByClass(Entity.class, new Box(_center, _center).expand(32 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.squaredDistanceTo(_center))).toList();
			for (Entity entityiterator : _entfound) {
				if ((entity instanceof MobEntity _mobEnt ? (Entity) _mobEnt.getTarget() : null) == entityiterator) {
					if (attack == 1) {
						if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
							_entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 3, false, true));
					}
					if (attack == 7) {
						if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
							_entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 3, false, true));
					}
					if (attack == 2) {
						if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
							_entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 80, 3, false, true));
					}
					if (attack == 3) {
						if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
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
						AnnoyingMod.queueServerWork(2, () -> {
							if (entity instanceof LivingEntity _entity) {
								ItemStack _setstack = new ItemStack(Items.TRIDENT).copy();
								_setstack.setCount(1);
								_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
								if (_entity instanceof Player _player)
									_player.getInventory().setChanged();
							}
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("item.trident.throw")), SoundSource.NEUTRAL, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("item.trident.throw")), SoundSource.NEUTRAL, 1, 1, false);
								}
							}
							{
								Entity _shootFrom = entity;
								Level projectileLevel = _shootFrom.level();
								if (!projectileLevel.isClientSide()) {
									Projectile _entityToSpawn = new Object() {
										public Projectile getTrident(Level level, Entity shooter, float damage, int knockback) {
											AbstractArrow entityToSpawn = new ThrownTrident(EntityType.TRIDENT, level);
											entityToSpawn.setOwner(shooter);
											entityToSpawn.setBaseDamage(damage);
											entityToSpawn.setKnockback(knockback);
											return entityToSpawn;
										}
									}.getTrident(projectileLevel, entity, 5, 3);
									_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
									_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 5, 0);
									projectileLevel.addFreshEntity(_entityToSpawn);
								}
							}
							if (entity instanceof LivingEntity _entity)
								_entity.swing(InteractionHand.MAIN_HAND, true);
							AnnoyingMod.queueServerWork(10, () -> {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("item.trident.throw")), SoundSource.NEUTRAL, 1, 1);
									} else {
										_level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("item.trident.throw")), SoundSource.NEUTRAL, 1, 1, false);
									}
								}
								{
									Entity _shootFrom = entity;
									Level projectileLevel = _shootFrom.level();
									if (!projectileLevel.isClientSide()) {
										Projectile _entityToSpawn = new Object() {
											public Projectile getTrident(Level level, Entity shooter, float damage, int knockback) {
												AbstractArrow entityToSpawn = new ThrownTrident(EntityType.TRIDENT, level);
												entityToSpawn.setOwner(shooter);
												entityToSpawn.setBaseDamage(damage);
												entityToSpawn.setKnockback(knockback);
												return entityToSpawn;
											}
										}.getTrident(projectileLevel, entity, 5, 3);
										_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
										_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 5, 0);
										projectileLevel.addFreshEntity(_entityToSpawn);
									}
								}
								if (entity instanceof LivingEntity _entity)
									_entity.swing(InteractionHand.MAIN_HAND, true);
								AnnoyingMod.queueServerWork(10, () -> {
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("item.trident.throw")), SoundSource.NEUTRAL, 1, 1);
										} else {
											_level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("item.trident.throw")), SoundSource.NEUTRAL, 1, 1, false);
										}
									}
									{
										Entity _shootFrom = entity;
										Level projectileLevel = _shootFrom.level();
										if (!projectileLevel.isClientSide()) {
											Projectile _entityToSpawn = new Object() {
												public Projectile getTrident(Level level, Entity shooter, float damage, int knockback) {
													AbstractArrow entityToSpawn = new ThrownTrident(EntityType.TRIDENT, level);
													entityToSpawn.setOwner(shooter);
													entityToSpawn.setBaseDamage(damage);
													entityToSpawn.setKnockback(knockback);
													return entityToSpawn;
												}
											}.getTrident(projectileLevel, entity, 5, 3);
											_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
											_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 5, 0);
											projectileLevel.addFreshEntity(_entityToSpawn);
										}
									}
									if (entity instanceof LivingEntity _entity) {
										ItemStack _setstack = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).copy();
										_setstack.setCount(1);
										_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
										if (_entity instanceof Player _player)
											_player.getInventory().setChanged();
									}
									if (entity instanceof LivingEntity _entity)
										_entity.swing(InteractionHand.MAIN_HAND, true);
									AnnoyingMod.queueServerWork(2, () -> {
										if (entity instanceof LivingEntity _entity) {
											ItemStack _setstack = new ItemStack(Blocks.CAVE_AIR).copy();
											_setstack.setCount(1);
											_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
											if (_entity instanceof Player _player)
												_player.getInventory().setChanged();
										}
									});
								});
							});
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
						AnnoyingMod.queueServerWork(2, () -> {
							if (entity instanceof LivingEntity _entity) {
								ItemStack _setstack = new ItemStack(Items.TRIDENT).copy();
								_setstack.setCount(1);
								_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
								if (_entity instanceof Player _player)
									_player.getInventory().setChanged();
							}
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("item.trident.throw")), SoundSource.NEUTRAL, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("item.trident.throw")), SoundSource.NEUTRAL, 1, 1, false);
								}
							}
							{
								Entity _shootFrom = entity;
								Level projectileLevel = _shootFrom.level();
								if (!projectileLevel.isClientSide()) {
									Projectile _entityToSpawn = new Object() {
										public Projectile getTrident(Level level, Entity shooter, float damage, int knockback) {
											AbstractArrow entityToSpawn = new ThrownTrident(EntityType.TRIDENT, level);
											entityToSpawn.setOwner(shooter);
											entityToSpawn.setBaseDamage(damage);
											entityToSpawn.setKnockback(knockback);
											return entityToSpawn;
										}
									}.getTrident(projectileLevel, entity, 5, 3);
									_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
									_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 5, 0);
									projectileLevel.addFreshEntity(_entityToSpawn);
								}
							}
							AnnoyingMod.queueServerWork(10, () -> {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("item.trident.throw")), SoundSource.NEUTRAL, 1, 1);
									} else {
										_level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("item.trident.throw")), SoundSource.NEUTRAL, 1, 1, false);
									}
								}
								{
									Entity _shootFrom = entity;
									Level projectileLevel = _shootFrom.level();
									if (!projectileLevel.isClientSide()) {
										Projectile _entityToSpawn = new Object() {
											public Projectile getTrident(Level level, Entity shooter, float damage, int knockback) {
												AbstractArrow entityToSpawn = new ThrownTrident(EntityType.TRIDENT, level);
												entityToSpawn.setOwner(shooter);
												entityToSpawn.setBaseDamage(damage);
												entityToSpawn.setKnockback(knockback);
												return entityToSpawn;
											}
										}.getTrident(projectileLevel, entity, 5, 3);
										_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
										_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 5, 0);
										projectileLevel.addFreshEntity(_entityToSpawn);
									}
								}
								AnnoyingMod.queueServerWork(10, () -> {
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("item.trident.throw")), SoundSource.NEUTRAL, 1, 1);
										} else {
											_level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("item.trident.throw")), SoundSource.NEUTRAL, 1, 1, false);
										}
									}
									{
										Entity _shootFrom = entity;
										Level projectileLevel = _shootFrom.level();
										if (!projectileLevel.isClientSide()) {
											Projectile _entityToSpawn = new Object() {
												public Projectile getTrident(Level level, Entity shooter, float damage, int knockback) {
													AbstractArrow entityToSpawn = new ThrownTrident(EntityType.TRIDENT, level);
													entityToSpawn.setOwner(shooter);
													entityToSpawn.setBaseDamage(damage);
													entityToSpawn.setKnockback(knockback);
													return entityToSpawn;
												}
											}.getTrident(projectileLevel, entity, 5, 3);
											_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
											_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 5, 0);
											projectileLevel.addFreshEntity(_entityToSpawn);
										}
									}
									if (entity instanceof LivingEntity _entity) {
										ItemStack _setstack = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).copy();
										_setstack.setCount(1);
										_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
										if (_entity instanceof Player _player)
											_player.getInventory().setChanged();
									}
									AnnoyingMod.queueServerWork(2, () -> {
										if (entity instanceof LivingEntity _entity) {
											ItemStack _setstack = new ItemStack(Blocks.CAVE_AIR).copy();
											_setstack.setCount(1);
											_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
											if (_entity instanceof Player _player)
												_player.getInventory().setChanged();
										}
									});
								});
							});
						});
					}
					if (attack == 4) {
						if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
							_entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 3, false, true));
						if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
							_entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100, 2, false, true));
						for (int index0 = 0; index0 < 6; index0++) {
							if (world instanceof ServerWorld _level) {
								Entity entityToSpawn = EntityType.LIGHTNING_BOLT.spawn(_level, BlockPos.ofFloored(x + MathHelper.nextDouble(Random.create(), -10, 10), y, z + MathHelper.nextDouble(Random.create(), -10, 10)), SpawnReason.MOB_SUMMONED);
								if (entityToSpawn != null) {
									entityToSpawn.setYaw(world.getRandom().nextFloat() * 360F);
								}
							}
							if (world instanceof ServerWorld _level) {
								Entity entityToSpawn = EntityType.LIGHTNING_BOLT.spawn(_level, BlockPos.ofFloored(x + MathHelper.nextDouble(Random.create(), -10, 10), y, z + MathHelper.nextDouble(Random.create(), -10, 10)), SpawnReason.MOB_SUMMONED);
								if (entityToSpawn != null) {
									entityToSpawn.setYaw(world.getRandom().nextFloat() * 360F);
								}
							}
							if (world instanceof ServerWorld _level) {
								Entity entityToSpawn = EntityType.LIGHTNING_BOLT.spawn(_level, BlockPos.ofFloored(x + MathHelper.nextDouble(Random.create(), -10, 10), y, z + MathHelper.nextDouble(Random.create(), -10, 10)), SpawnReason.MOB_SUMMONED);
								if (entityToSpawn != null) {
									entityToSpawn.setYaw(world.getRandom().nextFloat() * 360F);
								}
							}
						}
					}
					if (attack == 5) {
						if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
							_entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 100, false, false));
						if (world instanceof ServerWorld _level) {
							LightningEntity entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
							entityToSpawn.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(BlockPos.ofFloored(x, y, z)));;
							_level.spawnEntity(entityToSpawn);
						}
						AnnoyingMod.queueServerWork(20, () -> {
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 255, 50, false, false));

							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("annoying_villagers:blue.demon.trident.festival")), SoundSource.NEUTRAL, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("annoying_villagers:blue.demon.trident.festival")), SoundSource.NEUTRAL, 1, 1, false);
								}
							}
							{
								Entity _ent = entity;
								if (!_ent.level().isClientSide() && _ent.getServer() != null) {
									_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null,
											4, _ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "tellraw @a \"<Blue Demon> Trident Festival!!!!!!!!!!\"");
								}
							}
							AnnoyingMod.queueServerWork(40, () -> {
								for (int index1 = 0; index1 < 6; index1++) {
									if (world instanceof ServerLevel _level) {
										Entity entityToSpawn = EntityType.LIGHTNING_BOLT.spawn(_level, BlockPos.containing(x + Mth.nextDouble(RandomSource.create(), -10, 10), y, z + Mth.nextDouble(RandomSource.create(), -10, 10)),
												MobSpawnType.MOB_SUMMONED);
										if (entityToSpawn != null) {
											entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
										}
									}
									if (world instanceof ServerLevel _level) {
										Entity entityToSpawn = EntityType.LIGHTNING_BOLT.spawn(_level, BlockPos.containing(x + Mth.nextDouble(RandomSource.create(), -10, 10), y, z + Mth.nextDouble(RandomSource.create(), -10, 10)),
												MobSpawnType.MOB_SUMMONED);
										if (entityToSpawn != null) {
											entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
										}
									}
									if (world instanceof ServerLevel _level) {
										Entity entityToSpawn = EntityType.LIGHTNING_BOLT.spawn(_level, BlockPos.containing(x + Mth.nextDouble(RandomSource.create(), -10, 10), y, z + Mth.nextDouble(RandomSource.create(), -10, 10)),
												MobSpawnType.MOB_SUMMONED);
										if (entityToSpawn != null) {
											entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
										}
									}
								}
								if (world instanceof ServerLevel _level) {
									LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
									entityToSpawn.moveTo(Vec3.atBottomCenterOf(BlockPos.containing(x, y, z)));;
									_level.addFreshEntity(entityToSpawn);
								}
								if (world instanceof Level _level && !_level.isClientSide())
									_level.explode(null, x, y, z, 8, Level.ExplosionInteraction.TNT);
								if (world instanceof Level _level && !_level.isClientSide())
									_level.explode(null, (x + Mth.nextInt(RandomSource.create(), 5, 10)), y, z, 7, Level.ExplosionInteraction.TNT);
								if (world instanceof Level _level && !_level.isClientSide())
									_level.explode(null, (x - Mth.nextInt(RandomSource.create(), 5, 10)), y, z, 7, Level.ExplosionInteraction.TNT);
								if (world instanceof Level _level && !_level.isClientSide())
									_level.explode(null, (x + Mth.nextInt(RandomSource.create(), 5, 10)), y, (z + Mth.nextInt(RandomSource.create(), 5, 10)), 7, Level.ExplosionInteraction.TNT);
								if (world instanceof Level _level && !_level.isClientSide())
									_level.explode(null, (x - Mth.nextInt(RandomSource.create(), 5, 10)), y, (z - Mth.nextInt(RandomSource.create(), 5, 10)), 7, Level.ExplosionInteraction.TNT);
								if (world instanceof Level _level && !_level.isClientSide())
									_level.explode(null, (x + Mth.nextInt(RandomSource.create(), 5, 10)), y, (z - Mth.nextInt(RandomSource.create(), 5, 10)), 7, Level.ExplosionInteraction.TNT);
								if (world instanceof Level _level && !_level.isClientSide())
									_level.explode(null, (x - Mth.nextInt(RandomSource.create(), 5, 10)), y, (z + Mth.nextInt(RandomSource.create(), 5, 10)), 7, Level.ExplosionInteraction.TNT);
								if (world instanceof Level _level && !_level.isClientSide())
									_level.explode(null, x, y, (z + Mth.nextInt(RandomSource.create(), 5, 10)), 7, Level.ExplosionInteraction.TNT);
								if (world instanceof Level _level && !_level.isClientSide())
									_level.explode(null, x, y, (z - Mth.nextInt(RandomSource.create(), 5, 10)), 7, Level.ExplosionInteraction.TNT);
							});
						});
					}
				}
			}
		}
	}
}
