package com.iafenvoy.annoyingvillagers.procedures;

import com.iafenvoy.annoyingvillagers.AnnoyingMod;
import net.minecraft.enchantment.Enchantments;
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
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
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
import net.minecraftforge.registries.ForgeRegistries;
import com.iafenvoy.annoyingvillagers.init.AnnoyingModItem;

import java.util.List;
import java.util.Comparator;

public class SteveTick {
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
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) < 220) {
			if (entity.getPersistentData().getBoolean("pashe2") == false) {
				if (entity.getPersistentData().getBoolean("pashe3") == false) {
					if (entity.getPersistentData().getBoolean("pashe4") == false) {
						phase = 1;
						entity.getPersistentData().putBoolean("pashe2", true);
						entity.setCustomName(Text.literal("Steve Pashe II"));
						{
							Entity _ent = entity;
							if (!_ent.getWorld().isClient() && _ent.getServer() != null) {
								_ent.getServer().getCommandManager().executeWithPrefix(new ServerCommandSource(CommandOutput.DUMMY, _ent.getPos(), _ent.getRotationClient(), _ent.getWorld() instanceof ServerWorld ? (ServerWorld) _ent.getWorld() : null, 4,
										_ent.getName().getString(), _ent.getDisplayName(), _ent.getWorld().getServer(), _ent), "tellraw @a \"<Steve> I have to put some armors on.\"");
							}
						}
						if (world instanceof World _level) {
							if (!_level.isClient()) {
								_level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("annoying_villagers:steve.putarmor")), SoundCategory.NEUTRAL, 2, 1);
							} else {
								_level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("annoying_villagers:steve.putarmor")), SoundCategory.NEUTRAL, 2, 1, false);
							}
						}
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(Items.DIAMOND_CHESTPLATE).copy();
							_setstack.setCount(1);
							_entity.setStackInHand(Hand.MAIN_HAND, _setstack);
							if (_entity instanceof PlayerEntity _player)
								_player.getInventory().markDirty();
						}
						if (entity instanceof LivingEntity _entity)
							_entity.swingHand(Hand.MAIN_HAND, true);
						AnnoyingMod.queueServerWork(5, () -> {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("item.armor.equip_diamond")), SoundSource.NEUTRAL, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("item.armor.equip_diamond")), SoundSource.NEUTRAL, 1, 1, false);
								}
							}
							{
								Entity _entity = entity;
								if (_entity instanceof Player _player) {
									_player.getInventory().armor.set(2, new ItemStack(Items.DIAMOND_CHESTPLATE));
									_player.getInventory().setChanged();
								} else if (_entity instanceof LivingEntity _living) {
									_living.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
								}
							}
							(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
							if (entity instanceof LivingEntity _entity) {
								ItemStack _setstack = new ItemStack(Blocks.AIR).copy();
								_setstack.setCount(1);
								_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
								if (_entity instanceof Player _player)
									_player.getInventory().setChanged();
							}
							AnnoyingMod.queueServerWork(5, () -> {
								if (entity instanceof LivingEntity _entity) {
									ItemStack _setstack = new ItemStack(Items.DIAMOND_HELMET).copy();
									_setstack.setCount(1);
									_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
									if (_entity instanceof Player _player)
										_player.getInventory().setChanged();
								}
								if (entity instanceof LivingEntity _entity)
									_entity.swing(InteractionHand.MAIN_HAND, true);
								AnnoyingMod.queueServerWork(5, () -> {
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("item.armor.equip_diamond")), SoundSource.NEUTRAL, 1, 1);
										} else {
											_level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("item.armor.equip_diamond")), SoundSource.NEUTRAL, 1, 1, false);
										}
									}
									{
										Entity _entity = entity;
										if (_entity instanceof Player _player) {
											_player.getInventory().armor.set(3, new ItemStack(Items.DIAMOND_HELMET));
											_player.getInventory().setChanged();
										} else if (_entity instanceof LivingEntity _living) {
											_living.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET));
										}
									}
									(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
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
					}
				}
			}
		}
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) < 101) {
			if (entity.getPersistentData().getBoolean("pashe2") == true) {
				if (entity.getPersistentData().getBoolean("pashe3") == false) {
					if (entity.getPersistentData().getBoolean("pashe4") == false) {
						entity.getPersistentData().putBoolean("pashe3", true);
						phase = 2;
						if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
							_entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 300, 7, false, true));
						if (world instanceof World _level) {
							if (!_level.isClient()) {
								_level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("block.anvil.place")), SoundCategory.NEUTRAL, 2, 1);
							} else {
								_level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("block.anvil.place")), SoundCategory.NEUTRAL, 2, 1, false);
							}
						}
						if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
							_entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 200, false, true));
						AnnoyingMod.queueServerWork(20, () -> {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("annoying_villagers:steve.p2.speak")), SoundSource.NEUTRAL, 2, 1);
								} else {
									_level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("annoying_villagers:steve.p2.speak")), SoundSource.NEUTRAL, 2, 1, false);
								}
							}
							{
								Entity _ent = entity;
								if (!_ent.level().isClientSide() && _ent.getServer() != null) {
									_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null,
											4, _ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "tellraw @a \"<Steve> You're right. There's no point in reasoning with you.\"");
								}
							}
							AnnoyingMod.queueServerWork(100, () -> {
								{
									Entity _ent = entity;
									if (!_ent.level().isClientSide() && _ent.getServer() != null) {
										_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(),
														_ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent),
												"tellraw @a \"<Steve> Instead,I'll just force you into submission!\"");
									}
								}
								AnnoyingMod.queueServerWork(90, () -> {
									{
										Entity _ent = entity;
										if (!_ent.level().isClientSide() && _ent.getServer() != null) {
											_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(),
															_ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent),
													"tellraw @a \"<Steve> Then simply beat the answers out of you!\"");
										}
									}
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
									(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).enchant(Enchantments.SHARPNESS, 10);
									(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).enchant(Enchantments.KNOCKBACK, 3);
									if (entity instanceof LivingEntity _entity)
										_entity.swing(InteractionHand.MAIN_HAND, true);
									AnnoyingMod.queueServerWork(60, () -> {
										{
											Entity _ent = entity;
											if (!_ent.level().isClientSide() && _ent.getServer() != null) {
												_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(),
																_ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent),
														"tellraw @a \"<Steve> Hows that sound?\"");
											}
										}
										if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
											_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2000, 3, false, true));
										entity.setCustomName(Component.literal("Angry Steve"));
									});
								});
							});
						});
					}
				}
			}
		}
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) < 51) {
			if (entity.getPersistentData().getBoolean("pashe2") == true) {
				if (entity.getPersistentData().getBoolean("pashe3") == true) {
					if (entity.getPersistentData().getBoolean("pashe4") == false) {
						entity.getPersistentData().putBoolean("pashe4", true);
						phase = 3;
						entity.setCustomName(Text.literal("Angry Steve Pashe II"));
						if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
							_entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 7, false, false));
						if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
							_entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 4, false, false));
						if (world instanceof World _level) {
							if (!_level.isClient()) {
								_level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("block.anvil.place")), SoundCategory.NEUTRAL, 1, 1);
							} else {
								_level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("block.anvil.place")), SoundCategory.NEUTRAL, 1, 1, false);
							}
						}
						{
							Entity _entity = entity;
							if (_entity instanceof PlayerEntity _player) {
								_player.getInventory().armor.set(0, new ItemStack(Items.DIAMOND_BOOTS));
								_player.getInventory().markDirty();
							} else if (_entity instanceof LivingEntity _living) {
								_living.equipStack(EquipmentSlot.FEET, new ItemStack(Items.DIAMOND_BOOTS));
							}
						}
						(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getEquippedStack(EquipmentSlot.FEET) : ItemStack.EMPTY).addEnchantment(Enchantments.PROTECTION, 4);
						{
							Entity _entity = entity;
							if (_entity instanceof PlayerEntity _player) {
								_player.getInventory().armor.set(1, new ItemStack(Items.DIAMOND_LEGGINGS));
								_player.getInventory().markDirty();
							} else if (_entity instanceof LivingEntity _living) {
								_living.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.DIAMOND_LEGGINGS));
							}
						}
						(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getEquippedStack(EquipmentSlot.LEGS) : ItemStack.EMPTY).addEnchantment(Enchantments.PROTECTION, 4);
						{
							Entity _entity = entity;
							if (_entity instanceof PlayerEntity _player) {
								_player.getInventory().armor.set(2, new ItemStack(Items.DIAMOND_CHESTPLATE));
								_player.getInventory().markDirty();
							} else if (_entity instanceof LivingEntity _living) {
								_living.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
							}
						}
						(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getEquippedStack(EquipmentSlot.CHEST) : ItemStack.EMPTY).addEnchantment(Enchantments.PROTECTION, 4);
						{
							Entity _entity = entity;
							if (_entity instanceof PlayerEntity _player) {
								_player.getInventory().armor.set(3, new ItemStack(Items.DIAMOND_HELMET));
								_player.getInventory().markDirty();
							} else if (_entity instanceof LivingEntity _living) {
								_living.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET));
							}
						}
						(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getEquippedStack(EquipmentSlot.HEAD) : ItemStack.EMPTY).addEnchantment(Enchantments.PROTECTION, 4);
						if (world instanceof World _level) {
							if (!_level.isClient()) {
								_level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("item.armor.equip_diamond")), SoundCategory.NEUTRAL, 1, 1);
							} else {
								_level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("item.armor.equip_diamond")), SoundCategory.NEUTRAL, 1, 1, false);
							}
						}
						if (world instanceof World _level) {
							if (!_level.isClient()) {
								_level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("annoying_villagers:steve.pashe3.iwky")), SoundCategory.NEUTRAL, 1, 1);
							} else {
								_level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("annoying_villagers:steve.pashe3.iwky")), SoundCategory.NEUTRAL, 1, 1, false);
							}
						}
						{
							Entity _ent = entity;
							if (!_ent.getWorld().isClient() && _ent.getServer() != null) {
								_ent.getServer().getCommandManager().executeWithPrefix(new ServerCommandSource(CommandOutput.DUMMY, _ent.getPos(), _ent.getRotationClient(), _ent.getWorld() instanceof ServerWorld ? (ServerWorld) _ent.getWorld() : null, 4,
										_ent.getName().getString(), _ent.getDisplayName(), _ent.getWorld().getServer(), _ent), "tellraw @a \"<Steve> I will kill you,I WILL KILL YOU!\"");
							}
						}
					}
				}
			}
		}
		entity.getPersistentData().putDouble("timer", (entity.getPersistentData().getDouble("timer") + 1));
		if (entity.getPersistentData().getDouble("timer") == 80) {
			entity.getPersistentData().putDouble("timer", 0);
			if (attack == 0) {
				attack = MathHelper.nextInt(Random.create(), 1, 5);
			}
			if (attack == 1) {
				attack = MathHelper.nextInt(Random.create(), 5, 9);
			}
			if (attack == 2) {
				attack = MathHelper.nextInt(Random.create(), 9, 12);
			}
			if (attack == 3) {
				attack = MathHelper.nextInt(Random.create(), 12, 15);
			}
		}
		{
			final Vec3d _center = new Vec3d(x, y, z);
			List<Entity> _entfound = world.getEntitiesByClass(Entity.class, new Box(_center, _center).expand(32 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.squaredDistanceTo(_center))).toList();
			for (Entity entityiterator : _entfound) {
				if ((entity instanceof MobEntity _mobEnt ? (Entity) _mobEnt.getTarget() : null) == entityiterator) {
					if (attack == 13) {
						{
							Entity _ent = entity;
							if (!_ent.getWorld().isClient() && _ent.getServer() != null) {
								_ent.getServer().getCommandManager().executeWithPrefix(new ServerCommandSource(CommandOutput.DUMMY, _ent.getPos(), _ent.getRotationClient(), _ent.getWorld() instanceof ServerWorld ? (ServerWorld) _ent.getWorld() : null, 4,
										_ent.getName().getString(), _ent.getDisplayName(), _ent.getWorld().getServer(), _ent), "tellraw @a \"<Steve> Wake Up!\"");
							}
						}
						if (world instanceof World _level) {
							if (!_level.isClient()) {
								_level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("annoying_villagers:legendary.sword.wake.up")), SoundCategory.NEUTRAL, 1, 1);
							} else {
								_level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("annoying_villagers:legendary.sword.wake.up")), SoundCategory.NEUTRAL, 1, 1, false);
							}
						}
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(AnnoyingModItem.AWAKENING_LEGENDARY_SWORD.get()).copy();
							_setstack.setCount(1);
							_entity.setStackInHand(Hand.MAIN_HAND, _setstack);
							if (_entity instanceof PlayerEntity _player)
								_player.getInventory().markDirty();
						}
						if (world instanceof ServerWorld _level)
							_level.spawnParticles(ParticleTypes.TOTEM_OF_UNDYING, x, (y + 10), z, 35, 0.2, 0.2, 0.2, 0.2);
						if (world instanceof World _level) {
							if (!_level.isClient()) {
								_level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("item.totem.use")), SoundCategory.NEUTRAL, 1, 1);
							} else {
								_level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("item.totem.use")), SoundCategory.NEUTRAL, 1, 1, false);
							}
						}
						{
							Entity _ent = entity;
							_ent.requestTeleport(x, (y + 10), z);
							if (_ent instanceof ServerPlayerEntity _serverPlayer)
								_serverPlayer.networkHandler.requestTeleport(x, (y + 10), z, _ent.getYaw(), _ent.getPitch());
						}
						AnnoyingMod.queueServerWork(15, () -> {
							entity.fallDistance = 0;
							AnnoyingMod.queueServerWork(4, () -> {
								world.addParticle(ParticleTypes.EXPLOSION_EMITTER, x, y, z, 0, 0, 0);
								if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 80, 2, false, false));
							});
						});
					}
					if (attack == 14) {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(AnnoyingModItem.LEGENDARY_SWORD.get()).copy();
							_setstack.setCount(1);
							_entity.setStackInHand(Hand.MAIN_HAND, _setstack);
							if (_entity instanceof PlayerEntity _player)
								_player.getInventory().markDirty();
						}
					}
					if (attack == 15) {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(AnnoyingModItem.LEGENDARY_SWORD.get()).copy();
							_setstack.setCount(1);
							_entity.setStackInHand(Hand.MAIN_HAND, _setstack);
							if (_entity instanceof PlayerEntity _player)
								_player.getInventory().markDirty();
						}
					}
					if (attack == 7) {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD).copy();
							_setstack.setCount(1);
							_entity.setStackInHand(Hand.MAIN_HAND, _setstack);
							if (_entity instanceof PlayerEntity _player)
								_player.getInventory().markDirty();
						}
						(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandStack() : ItemStack.EMPTY).addEnchantment(Enchantments.SHARPNESS, 10);
						(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandStack() : ItemStack.EMPTY).addEnchantment(Enchantments.KNOCKBACK, 3);
					}
					if (attack == 11) {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(AnnoyingModItem.CRAFTING_TABLE.get()).copy();
							_setstack.setCount(1);
							_entity.setStackInHand(Hand.MAIN_HAND, _setstack);
							if (_entity instanceof PlayerEntity _player)
								_player.getInventory().markDirty();
						}
					}
					if (attack == 4) {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(AnnoyingModItem.ENCHANTED_OAK_DOOR.get()).copy();
							_setstack.setCount(1);
							_entity.setStackInHand(Hand.MAIN_HAND, _setstack);
							if (_entity instanceof PlayerEntity _player)
								_player.getInventory().markDirty();
						}
					}
					if (attack == 3) {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(AnnoyingModItem.WOOPIE.get()).copy();
							_setstack.setCount(1);
							_entity.setStackInHand(Hand.MAIN_HAND, _setstack);
							if (_entity instanceof PlayerEntity _player)
								_player.getInventory().markDirty();
						}
					}
					if (attack == 6) {
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
					if (attack == 1) {
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
					if (attack == 12) {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(Items.ENCHANTED_GOLDEN_APPLE).copy();
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
												if (entity instanceof LivingEntity _entity) {
													ItemStack _setstack = new ItemStack(AnnoyingModItem.LEGENDARY_SWORD.get()).copy();
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
							ItemStack _setstack = new ItemStack(Items.ENCHANTED_GOLDEN_APPLE).copy();
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
					if (attack == 9) {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(Items.ENCHANTED_GOLDEN_APPLE).copy();
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
							});
						});
					}
				}
			}
		}
	}
}
