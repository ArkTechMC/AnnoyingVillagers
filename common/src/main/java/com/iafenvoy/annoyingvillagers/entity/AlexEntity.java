package com.iafenvoy.annoyingvillagers.entity;

import com.iafenvoy.annoyingvillagers.AnnoyingVillagers;
import com.iafenvoy.annoyingvillagers.client.renderer.Stage;
import com.iafenvoy.annoyingvillagers.util.CommandHelper;
import com.iafenvoy.annoyingvillagers.util.SoundUtil;
import com.iafenvoy.annoyingvillagers.util.Timeout;
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
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
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

public class AlexEntity extends HostileEntity {
    public static final Stage.StagedEntityTextureProvider textures = Stage.ofProvider(AnnoyingVillagers.MOD_ID, "alex");
    private final ServerBossBar bossInfo = new ServerBossBar(this.getDisplayName(), BossBar.Color.GREEN, BossBar.Style.PROGRESS);
    private double timer;

    public AlexEntity(EntityType<AlexEntity> type, World world) {
        super(type, world);
        this.setStepHeight(0.6f);
        this.experiencePoints = 0;
        this.setAiDisabled(false);
        this.setCustomName(Text.literal("Alex"));
        this.setCustomNameVisible(true);
        this.setPersistent();
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_SWORD));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        DefaultAttributeContainer.Builder builder = MobEntity.createMobAttributes();
        builder = builder.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.45);
        builder = builder.add(EntityAttributes.GENERIC_MAX_HEALTH, 230);
        builder = builder.add(EntityAttributes.GENERIC_ARMOR, 25);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 17);
        builder = builder.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1);
        builder = builder.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.2);
        return builder;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
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
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.timer = nbt.getDouble("timer");
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putDouble("timer", this.timer);
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
        return super.damage(damagesource, amount);
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        this.getWorld();
        Timeout.create(20, () -> CommandHelper.execute(this, "/tellraw @a [{\"text\":\"Alex left the game\",\"color\":\"yellow\",\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false}]"));
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason reason, EntityData livingdata, NbtCompound tag) {
        EntityData retval = super.initialize(world, difficulty, reason, livingdata, tag);
        CommandHelper.execute(this, "/tellraw @a [{\"text\":\"Alex joined the game\",\"color\":\"yellow\",\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false}]");
        Timeout.create(20, () -> {
            CommandHelper.execute(this, "tellraw @a \"<Alex> I'm way stronger than all of you combined!\"");
            if ((WorldAccess) world instanceof World _level)
                SoundUtil.playSound(_level, this.getX(), this.getY(), this.getZ(), new Identifier(AnnoyingVillagers.MOD_ID, "alex.spawn"), 1, 1);
        });
        return retval;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        WorldAccess world = this.getWorld();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        int attack = 0;

        this.timer++;
        if (this.timer == 30) {
            this.timer = 0;
            attack = MathHelper.nextInt(Random.create(), 1, 11);
        }

        final Vec3d _center = new Vec3d(x, y, z);
        List<Entity> _entfound = world.getEntitiesByClass(Entity.class, new Box(_center, _center).expand(32 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.squaredDistanceTo(_center))).toList();
        for (Entity entityiterator : _entfound) {
            if (this.getTarget() == entityiterator) {
                switch (attack) {
                    case 1: {
                        ItemStack _setstack = new ItemStack(Items.GOLDEN_APPLE);
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, _setstack);
                        Runnable runnable = () -> {
                            if (world instanceof World _level)
                                SoundUtil.playSound(_level, x, y + 1, z, new Identifier("entity.generic.eat"), 1.2f, (float) MathHelper.nextDouble(Random.create(), 0.9, 1.1));
                            this.swingHand(Hand.MAIN_HAND, true);
                        };
                        Timeout.create(0, runnable);
                        Timeout.create(5, runnable);
                        Timeout.create(10, runnable);
                        Timeout.create(15, runnable);
                        Timeout.create(20, runnable);
                        Timeout.create(25, runnable);
                        Timeout.create(30, () -> {
                            runnable.run();
                            if (!this.getWorld().isClient) {
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 4, false, true));
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 7200, 2, false, true));
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 2, false, true));
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 1, false, true));
                            }
                            ItemStack stack = new ItemStack(Items.DIAMOND_SWORD);
                            stack.setCount(1);
                            this.setStackInHand(Hand.MAIN_HAND, stack);
                        });
                    }
                    case 5: {
                        if (world instanceof World _level)
                            SoundUtil.playSound(_level, x, y, z, new Identifier("entity.ender_pearl.throw"), 1, 1);
                        World projectileLevel = this.getWorld();
                        if (!projectileLevel.isClient) {
                            ProjectileEntity entityToSpawn = new EnderPearlEntity(EntityType.ENDER_PEARL, projectileLevel);
                            entityToSpawn.setOwner(this);
                            entityToSpawn.setPosition(this.getX(), this.getEyeY() - 0.1, this.getZ());
                            entityToSpawn.setVelocity(this.getRotationVector().x, this.getRotationVector().y, this.getRotationVector().z, 1, 0);
                            projectileLevel.spawnEntity(entityToSpawn);
                        }
                    }
                    case 6: {
                        ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD);
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, _setstack);
                        Runnable runnable = () -> {
                            this.swingHand(Hand.MAIN_HAND, true);
                            if (world instanceof World _level)
                                SoundUtil.playSound(_level, x, y, z, new Identifier("entity.witch.throw"), 1, 1);
                        };
                        Timeout.create(0, runnable);
                        Timeout.create(5, runnable);
                        Timeout.create(10, runnable);
                    }
                    case 7: {
                        ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD);
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, _setstack);
                        Runnable runnable = () -> {
                            this.swingHand(Hand.MAIN_HAND, true);
                            if (world instanceof World _level)
                                SoundUtil.playSound(_level, x, y, z, new Identifier("entity.witch.throw"), 1, 1);
                        };
                        Timeout.create(0, runnable);
                        Timeout.create(5, runnable);
                        Timeout.create(10, runnable);
                    }
                    case 2: {
                        if (world instanceof World _level)
                            SoundUtil.playSound(_level, x, y, z, new Identifier("entity.ender_pearl.throw"), 1, 1);
                        World projectileLevel = this.getWorld();
                        if (!projectileLevel.isClient) {
                            ProjectileEntity entityToSpawn = new EnderPearlEntity(EntityType.ENDER_PEARL, projectileLevel);
                            entityToSpawn.setOwner(this);
                            entityToSpawn.setPosition(this.getX(), this.getEyeY() - 0.1, this.getZ());
                            entityToSpawn.setVelocity(this.getRotationVector().x, this.getRotationVector().y, this.getRotationVector().z, 1, 0);
                            projectileLevel.spawnEntity(entityToSpawn);
                        }
                    }
                    case 8: {
                        ItemStack _setstack = new ItemStack(Items.BOW);
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, _setstack);
                        Runnable runnable = () -> {
                            this.swingHand(Hand.MAIN_HAND, true);
                            if (world instanceof World _level)
                                SoundUtil.playSound(_level, x, y, z, new Identifier("entity.arrow.shoot"), 1, 1);
                            World projectileLevel = this.getWorld();
                            if (!projectileLevel.isClient) {
                                PersistentProjectileEntity entityToSpawn = new ArrowEntity(EntityType.ARROW, projectileLevel);
                                entityToSpawn.setOwner(this);
                                entityToSpawn.setDamage(7);
                                entityToSpawn.setPunch(4);
                                entityToSpawn.setPosition(this.getX(), this.getEyeY() - 0.1, this.getZ());
                                entityToSpawn.setVelocity(this.getRotationVector().x, this.getRotationVector().y, this.getRotationVector().z, 3, 0);
                                projectileLevel.spawnEntity(entityToSpawn);
                            }
                        };
                        Timeout.create(0, runnable);
                        Timeout.create(10, runnable);
                        Timeout.create(20, runnable);
                        Timeout.create(30, runnable);
                    }
                    case 3: {
                        ItemStack _setstack = new ItemStack(Items.BOW);
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, _setstack);
                        Runnable runnable = () -> {
                            this.swingHand(Hand.MAIN_HAND, true);
                            if (world instanceof World _level)
                                SoundUtil.playSound(_level, x, y, z, new Identifier("entity.arrow.shoot"), 1, 1);
                            World projectileLevel = this.getWorld();
                            if (!projectileLevel.isClient) {
                                PersistentProjectileEntity entityToSpawn = new ArrowEntity(EntityType.ARROW, projectileLevel);
                                entityToSpawn.setOwner(this);
                                entityToSpawn.setDamage(7);
                                entityToSpawn.setPunch(4);
                                entityToSpawn.setPosition(this.getX(), this.getEyeY() - 0.1, this.getZ());
                                entityToSpawn.setVelocity(this.getRotationVector().x, this.getRotationVector().y, this.getRotationVector().z, 3, 0);
                                projectileLevel.spawnEntity(entityToSpawn);
                            }
                        };
                        Timeout.create(0, runnable);
                        Timeout.create(10, runnable);
                        Timeout.create(20, runnable);
                        Timeout.create(30, runnable);
                    }
                    case 4: {
                        ItemStack _setstack = new ItemStack(Items.BOW);
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, _setstack);
                        Runnable runnable = () -> {
                            this.swingHand(Hand.MAIN_HAND, true);
                            if (world instanceof World _level)
                                SoundUtil.playSound(_level, x, y, z, new Identifier("entity.arrow.shoot"), 1, 1);
                            World projectileLevel = this.getWorld();
                            if (!projectileLevel.isClient) {
                                PersistentProjectileEntity entityToSpawn = new ArrowEntity(EntityType.ARROW, projectileLevel);
                                entityToSpawn.setOwner(this);
                                entityToSpawn.setDamage(7);
                                entityToSpawn.setPunch(4);
                                entityToSpawn.setPosition(this.getX(), this.getEyeY() - 0.1, this.getZ());
                                entityToSpawn.setVelocity(this.getRotationVector().x, this.getRotationVector().y, this.getRotationVector().z, 3, 0);
                                projectileLevel.spawnEntity(entityToSpawn);
                            }
                        };
                        Timeout.create(0, runnable);
                        Timeout.create(10, runnable);
                        Timeout.create(20, runnable);
                        Timeout.create(30, runnable);
                    }
                    case 9: {
                        ItemStack _setstack = new ItemStack(Items.GOLDEN_APPLE);
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, _setstack);
                        Runnable runnable = () -> {
                            if (world instanceof World _level)
                                SoundUtil.playSound(_level, x, y + 1, z, new Identifier("entity.generic.eat"), 1.2f, (float) MathHelper.nextDouble(Random.create(), 0.9, 1.1));
                            this.swingHand(Hand.MAIN_HAND, true);
                        };
                        Timeout.create(0, runnable);
                        Timeout.create(5, runnable);
                        Timeout.create(10, runnable);
                        Timeout.create(15, runnable);
                        Timeout.create(20, runnable);
                        Timeout.create(25, runnable);
                        Timeout.create(30, () -> {
                            runnable.run();
                            if (!this.getWorld().isClient) {
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 4, false, true));
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 7200, 2, false, true));
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 2, false, true));
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 1, false, true));
                            }
                            ItemStack stack = new ItemStack(Items.DIAMOND_SWORD);
                            stack.setCount(1);
                            this.setStackInHand(Hand.MAIN_HAND, stack);
                        });
                    }
                    case 10: {
                        ItemStack _setstack = new ItemStack(Items.GOLDEN_APPLE);
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, _setstack);
                        Runnable runnable = () -> {
                            if (world instanceof World _level)
                                SoundUtil.playSound(_level, x, y + 1, z, new Identifier("entity.generic.eat"), 1.2f, (float) MathHelper.nextDouble(Random.create(), 0.9, 1.1));
                            this.swingHand(Hand.MAIN_HAND, true);
                        };
                        Timeout.create(0, runnable);
                        Timeout.create(5, runnable);
                        Timeout.create(10, runnable);
                        Timeout.create(15, runnable);
                        Timeout.create(20, runnable);
                        Timeout.create(25, runnable);
                        Timeout.create(30, () -> {
                            runnable.run();
                            if (!this.getWorld().isClient) {
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 4, false, true));
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 7200, 2, false, true));
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 2, false, true));
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 1, false, true));
                            }
                            ItemStack stack = new ItemStack(Items.DIAMOND_SWORD);
                            stack.setCount(1);
                            this.setStackInHand(Hand.MAIN_HAND, stack);
                        });
                    }
                    case 11: {
                        ItemStack _setstack = new ItemStack(Items.GOLDEN_APPLE);
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, _setstack);
                        Runnable runnable = () -> {
                            if (world instanceof World _level)
                                SoundUtil.playSound(_level, x, y + 1, z, new Identifier("entity.generic.eat"), 1.2f, (float) MathHelper.nextDouble(Random.create(), 0.9, 1.1));
                            this.swingHand(Hand.MAIN_HAND, true);
                        };
                        Timeout.create(0, runnable);
                        Timeout.create(5, runnable);
                        Timeout.create(10, runnable);
                        Timeout.create(15, runnable);
                        Timeout.create(20, runnable);
                        Timeout.create(25, runnable);
                        Timeout.create(30, () -> {
                            runnable.run();
                            if (!this.getWorld().isClient) {
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 4, false, true));
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 7200, 2, false, true));
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 2, false, true));
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 1, false, true));
                            }
                            ItemStack stack = new ItemStack(Items.DIAMOND_SWORD);
                            stack.setCount(1);
                            this.setStackInHand(Hand.MAIN_HAND, stack);
                        });
                    }
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