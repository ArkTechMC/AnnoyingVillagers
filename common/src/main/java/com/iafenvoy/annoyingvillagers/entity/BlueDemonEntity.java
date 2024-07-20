package com.iafenvoy.annoyingvillagers.entity;

import com.iafenvoy.annoyingvillagers.AnnoyingVillagers;
import com.iafenvoy.annoyingvillagers.client.renderer.Stage;
import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItems;
import com.iafenvoy.annoyingvillagers.util.CommandHelper;
import com.iafenvoy.annoyingvillagers.util.SoundUtil;
import com.iafenvoy.annoyingvillagers.util.Timeout;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Comparator;
import java.util.List;

public class BlueDemonEntity extends HostileEntity {
    public static final Stage.StagedEntityTextureProvider textures = Stage.ofProvider(AnnoyingVillagers.MOD_ID, "blue_demon");
    private final ServerBossBar bossInfo = new ServerBossBar(this.getDisplayName(), BossBar.Color.BLUE, BossBar.Style.PROGRESS);
    private boolean pashe2;
    private double timer;

    public BlueDemonEntity(EntityType<BlueDemonEntity> type, World world) {
        super(type, world);
        this.setStepHeight(0.6f);
        this.experiencePoints = 0;
        this.setAiDisabled(false);
        this.setCustomName(Text.literal("Blue Demon"));
        this.setCustomNameVisible(true);
        this.setPersistent();
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.TRIDENT));
        this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(Items.TRIDENT));
        this.equipStack(EquipmentSlot.CHEST, new ItemStack(AnnoyingModItems.DROWNED_CHESTPLATE.get()));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        DefaultAttributeContainer.Builder builder = MobEntity.createMobAttributes();
        builder = builder.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3);
        builder = builder.add(EntityAttributes.GENERIC_MAX_HEALTH, 280);
        builder = builder.add(EntityAttributes.GENERIC_ARMOR, 8);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 9);
        builder = builder.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.7);
        return builder;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, SteveEntity.class, false, false));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2, false) {
            @Override
            protected double getSquaredMaxAttackDistance(LivingEntity entity) {
                return this.mob.getWidth() * this.mob.getWidth() + entity.getWidth();
            }
        });
        this.goalSelector.add(3, new WanderAroundGoal(this, 1));
        this.targetSelector.add(4, new RevengeGoal(this));
        this.goalSelector.add(5, new LookAroundGoal(this));
        this.goalSelector.add(6, new SwimGoal(this));
    }

    @Override
    public EntityGroup getGroup() {
        return EntityGroup.DEFAULT;
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public double getHeightOffset() {
        return -0.35D;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return Registries.SOUND_EVENT.get(new Identifier("entity.generic.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return Registries.SOUND_EVENT.get(new Identifier("entity.generic.death"));
    }

    @Override
    public boolean damage(DamageSource damagesource, float amount) {
        if (damagesource.isOf(DamageTypes.FALL))
            return false;
        if (damagesource.isOf(DamageTypes.DROWN))
            return false;
        if (damagesource.isOf(DamageTypes.LIGHTNING_BOLT))
            return false;
        if (damagesource.isOf(DamageTypes.EXPLOSION) || damagesource.isOf(DamageTypes.PLAYER_EXPLOSION))
            return false;
        if (damagesource.isOf(DamageTypes.TRIDENT))
            return false;
        return super.damage(damagesource, amount);
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        WorldAccess world = this.getWorld();
        if (world instanceof World _level)
            SoundUtil.playSound(_level, this.getX(), this.getY(), this.getZ(), new Identifier(AnnoyingVillagers.MOD_ID, "blue.demon.dead"), 1, 1);
        CommandHelper.execute(this, "tellraw @a \"<Blue Deamon> I pray you find your own selfish...\"");
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason reason, EntityData livingdata, NbtCompound tag) {
        EntityData retval = super.initialize(world, difficulty, reason, livingdata, tag);
        Timeout.create(20, () -> {
            CommandHelper.execute(this, "tellraw @a \"<Blue Demon> Are you my enemy?\"");
            if ((WorldAccess) world instanceof World _level)
                SoundUtil.playSound(_level, this.getX(), this.getY(), this.getZ(), new Identifier(AnnoyingVillagers.MOD_ID, "blue.demon.spawn"), 1, 1);
        });
        return retval;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("pashe2", this.pashe2);
        nbt.putDouble("timer", this.timer);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.pashe2 = nbt.getBoolean("pashe2");
        this.timer = nbt.getDouble("timer");
    }

    @Override
    public void baseTick() {
        super.baseTick();
        WorldAccess world = this.getWorld();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        double attack = 0;
        if (this.getHealth() < 140) {
            if (!this.pashe2) {
                this.pashe2 = true;
                this.setCustomName(Text.literal("Blue Demon-Legendary Sword"));
                if (!this.getWorld().isClient)
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 7, false, false));
                if (world instanceof World _level) {
                    SoundUtil.playSound(_level, x, y, z, new Identifier("block.anvil.place"), 1, 1);
                    SoundUtil.playSound(_level, x, y, z, new Identifier("item.armor.equip_diamond"), 1, 1);
                    SoundUtil.playSound(_level, x, y, z, new Identifier(AnnoyingVillagers.MOD_ID, "blue.demon.ittrsp"), 1, 1);
                }
                ItemStack stack = new ItemStack(AnnoyingModItems.LEGENDARY_SWORD.get()).copy();
                stack.setCount(1);
                this.setStackInHand(Hand.MAIN_HAND, stack);
                ItemStack _setstack = new ItemStack(Blocks.AIR).copy();
                _setstack.setCount(1);
                this.setStackInHand(Hand.OFF_HAND, _setstack);
                CommandHelper.execute(this, "tellraw @a \"<Blue Demon> It's time to respawn,player...\"");
            }
        }
        this.timer++;
        if (this.timer == 80) {
            this.timer = 0;
            attack = MathHelper.nextInt(Random.create(), 1, 7);
        }

        final Vec3d _center = new Vec3d(x, y, z);
        List<Entity> _entfound = world.getEntitiesByClass(Entity.class, new Box(_center, _center).expand(32 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.squaredDistanceTo(_center))).toList();
        for (Entity entityiterator : _entfound) {
            if (this.getTarget() == entityiterator) {
                if (!this.getWorld().isClient) {
                    if (attack == 1)
                        this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 3, false, true));
                    if (attack == 7)
                        this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 3, false, true));
                    if (attack == 2)
                        this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 80, 3, false, true));
                    if (attack == 3)
                        this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 80, 3, false, true));
                }
                if (attack == 8) {
                    ItemStack stack = this.getMainHandStack();
                    stack.setCount(1);
                    this.setStackInHand(Hand.OFF_HAND, stack);
                    Runnable runnable = () -> {
                        ItemStack _setstack = new ItemStack(Items.TRIDENT);
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, _setstack);
                        if (world instanceof World _level)
                            SoundUtil.playSound(_level, x, y, z, new Identifier("item.trident.throw"), 1, 1);
                        this.swingHand(Hand.MAIN_HAND, true);
                        World projectileLevel = this.getWorld();
                        if (!projectileLevel.isClient) {
                            TridentEntity entityToSpawn = new TridentEntity(EntityType.TRIDENT, projectileLevel);
                            entityToSpawn.setOwner(this);
                            entityToSpawn.setDamage(5);
                            entityToSpawn.setPunch(3);
                            entityToSpawn.setPos(this.getX(), this.getEyeY() - 0.1, this.getZ());
                            entityToSpawn.setVelocity(this.getRotationVector().x, this.getRotationVector().y, this.getRotationVector().z, 5, 0);
                            projectileLevel.spawnEntity(entityToSpawn);
                        }
                    };
                    Timeout.create(2, runnable);
                    Timeout.create(12, runnable);
                    Timeout.create(22, runnable);
                    Timeout.create(24, () -> {
                        ItemStack _setstack = new ItemStack(Blocks.CAVE_AIR).copy();
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.OFF_HAND, _setstack);
                    });
                }
                if (attack == 6) {
                    ItemStack stack = this.getMainHandStack().copy();
                    stack.setCount(1);
                    this.setStackInHand(Hand.OFF_HAND, stack);
                    Runnable runnable = () -> {
                        ItemStack _setstack = new ItemStack(Items.TRIDENT);
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, _setstack);
                        if (world instanceof World _level)
                            SoundUtil.playSound(_level, x, y, z, new Identifier("item.trident.throw"), 1, 1);
                        this.swingHand(Hand.MAIN_HAND, true);
                        World projectileLevel = this.getWorld();
                        if (!projectileLevel.isClient) {
                            TridentEntity entityToSpawn = new TridentEntity(EntityType.TRIDENT, projectileLevel);
                            entityToSpawn.setOwner(this);
                            entityToSpawn.setDamage(5);
                            entityToSpawn.setPunch(3);
                            entityToSpawn.setPos(this.getX(), this.getEyeY() - 0.1, this.getZ());
                            entityToSpawn.setVelocity(this.getRotationVector().x, this.getRotationVector().y, this.getRotationVector().z, 5, 0);
                            projectileLevel.spawnEntity(entityToSpawn);
                        }
                    };
                    Timeout.create(2, runnable);
                    Timeout.create(12, runnable);
                    Timeout.create(22, runnable);
                    Timeout.create(24, () -> {
                        ItemStack _setstack = new ItemStack(Blocks.CAVE_AIR).copy();
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.OFF_HAND, _setstack);
                    });
                }
                if (attack == 4) {
                    if (!this.getWorld().isClient) {
                        this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 3, false, true));
                        this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100, 2, false, true));
                    }
                    if (world instanceof ServerWorld _level)
                        for (int index0 = 0; index0 < 18; index0++) {
                            Entity entityToSpawn = EntityType.LIGHTNING_BOLT.spawn(_level, BlockPos.ofFloored(x + MathHelper.nextDouble(Random.create(), -10, 10), y, z + MathHelper.nextDouble(Random.create(), -10, 10)), SpawnReason.MOB_SUMMONED);
                            if (entityToSpawn != null)
                                entityToSpawn.setYaw(world.getRandom().nextFloat() * 360F);
                        }
                }
                if (attack == 5) {
                    if (!this.getWorld().isClient)
                        this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 100, false, false));
                    if (world instanceof ServerWorld _level) {
                        LightningEntity entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                        if (entityToSpawn != null) {
                            entityToSpawn.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(BlockPos.ofFloored(x, y, z)));
                            _level.spawnEntity(entityToSpawn);
                        }
                    }
                    Timeout.create(20, () -> {
                        if (!this.getWorld().isClient)
                            this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 255, 50, false, false));
                        if (world instanceof World _level)
                            SoundUtil.playSound(_level, x, y, z, new Identifier(AnnoyingVillagers.MOD_ID, "blue.demon.trident.festival"), 1, 1);
                        CommandHelper.execute(this, "tellraw @a \"<Blue Demon> Trident Festival!!!!!!!!!!\"");
                    });
                    Timeout.create(60, () -> {
                        for (int index1 = 0; index1 < 18; index1++)
                            if (world instanceof ServerWorld _level) {
                                Entity entityToSpawn = EntityType.LIGHTNING_BOLT.spawn(_level, BlockPos.ofFloored(x + MathHelper.nextDouble(Random.create(), -10, 10), y, z + MathHelper.nextDouble(Random.create(), -10, 10)), SpawnReason.MOB_SUMMONED);
                                if (entityToSpawn != null)
                                    entityToSpawn.setYaw(world.getRandom().nextFloat() * 360F);
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

    @Override
    public boolean canUsePortals() {
        return false;
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    public void mobTick() {
        super.mobTick();
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }
}
