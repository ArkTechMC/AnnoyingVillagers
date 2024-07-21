package com.iafenvoy.annoyingvillagers.entity;

import com.iafenvoy.annoyingvillagers.AnnoyingVillagers;
import com.iafenvoy.annoyingvillagers.client.renderer.Stage;
import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItems;
import com.iafenvoy.annoyingvillagers.util.CommandHelper;
import com.iafenvoy.annoyingvillagers.util.SoundUtil;
import com.iafenvoy.annoyingvillagers.util.Timeout;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
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
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
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

public class SteveEntity extends HostileEntity {
    public static final Stage.StagedEntityTextureProvider textures = Stage.ofProvider(AnnoyingVillagers.MOD_ID, "steve");
    private final ServerBossBar bossInfo = new ServerBossBar(this.getDisplayName(), BossBar.Color.BLUE, BossBar.Style.PROGRESS);
    private boolean pashe2, pashe3, pashe4;
    private double timer;

    public SteveEntity(EntityType<SteveEntity> type, World world) {
        super(type, world);
        this.setStepHeight(0.6f);
        this.experiencePoints = 0;
        this.setAiDisabled(false);
        this.setCustomName(Text.literal("Steve"));
        this.setCustomNameVisible(true);
        this.setPersistent();
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_SWORD));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        DefaultAttributeContainer.Builder builder = MobEntity.createMobAttributes();
        builder = builder.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3);
        builder = builder.add(EntityAttributes.GENERIC_MAX_HEALTH, 230);
        builder = builder.add(EntityAttributes.GENERIC_ARMOR, 0);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3);
        builder = builder.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.5);
        return builder;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, BlueDemonEntity.class, false, false));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.2, false) {
            @Override
            protected double getSquaredMaxAttackDistance(LivingEntity entity) {
                return this.mob.getWidth() * this.mob.getWidth() + entity.getWidth();
            }
        });
        this.goalSelector.add(2, new WanderAroundGoal(this, 1));
        this.targetSelector.add(3, new RevengeGoal(this));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(5, new SwimGoal(this));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("pashe2", this.pashe2);
        nbt.putBoolean("pashe3", this.pashe3);
        nbt.putBoolean("pashe4", this.pashe4);
        nbt.putDouble("timer", this.timer);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.pashe2 = nbt.getBoolean("pashe2");
        this.pashe3 = nbt.getBoolean("pashe3");
        this.pashe4 = nbt.getBoolean("pashe4");
        this.timer = nbt.getDouble("timer");
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
        WorldAccess world = this.getWorld();
        if (world instanceof World _level)
            SoundUtil.playSound(_level, this.getX(), this.getY(), this.getZ(), new Identifier(AnnoyingVillagers.MOD_ID, "steve.dead"), 1, 1);
        CommandHelper.execute(this, "tellraw @a \"<Steve> Noooooooooooo!\"");
        Timeout.create(20, () -> CommandHelper.execute(this, "/tellraw @a [{\"text\":\"Steve left the game\",\"color\":\"yellow\",\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false}]"));
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason reason, EntityData livingdata, NbtCompound tag) {
        EntityData retval = super.initialize(world, difficulty, reason, livingdata, tag);
        CommandHelper.execute(this, "/tellraw @a [{\"text\":\"Steve joined the game\",\"color\":\"yellow\",\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false}]");
        Timeout.create(20, () -> {
            CommandHelper.execute(this, "tellraw @a \"<Steve> My name is Steve,and I'm come to destory Herobrine\"");
            if ((WorldAccess) world instanceof World _level)
                SoundUtil.playSound(_level, this.getX(), this.getY(), this.getZ(), new Identifier(AnnoyingVillagers.MOD_ID, "steve.spawn"), 1, 1);
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
        double attack = 0;
        if (this.getHealth() < 220) {
            if (!this.pashe2 && !this.pashe3 && !this.pashe4) {
                this.pashe2 = true;
                this.setCustomName(Text.literal("Steve Pashe II"));
                CommandHelper.execute(this, "tellraw @a \"<Steve> I have to put some armors on.\"");
                if (world instanceof World _level)
                    SoundUtil.playSound(_level, x, y, z, new Identifier(AnnoyingVillagers.MOD_ID, "steve.putarmor"), 2, 1);
                ItemStack stack = new ItemStack(Items.DIAMOND_CHESTPLATE);
                stack.setCount(1);
                this.setStackInHand(Hand.MAIN_HAND, stack);
                this.swingHand(Hand.MAIN_HAND, true);
                Timeout.create(5, () -> {
                    if (world instanceof World _level)
                        SoundUtil.playSound(_level, x, y, z, new Identifier(AnnoyingVillagers.MOD_ID, "item.armor.equip_diamond"), 2, 1);
                    this.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
                    this.getEquippedStack(EquipmentSlot.CHEST).addEnchantment(Enchantments.PROTECTION, 4);
                    ItemStack stack1 = new ItemStack(Blocks.AIR);
                    stack1.setCount(1);
                    this.setStackInHand(Hand.MAIN_HAND, stack1);
                    Timeout.create(5, () -> {
                        ItemStack stack2 = new ItemStack(Items.DIAMOND_HELMET);
                        stack2.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, stack2);
                        this.swingHand(Hand.MAIN_HAND, true);
                        Timeout.create(5, () -> {
                            if (world instanceof World _level)
                                SoundUtil.playSound(_level, x, y, z, new Identifier(AnnoyingVillagers.MOD_ID, "item.armor.equip_diamond"), 1, 1);
                            this.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET));
                            LivingEntity _entGetArmor = this;
                            _entGetArmor.getEquippedStack(EquipmentSlot.CHEST).addEnchantment(Enchantments.PROTECTION, 4);
                            ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD);
                            _setstack.setCount(1);
                            this.setStackInHand(Hand.MAIN_HAND, _setstack);
                        });
                    });
                });
            }
        }
        if (this.getHealth() < 101) {
            if (this.pashe2 && !this.pashe3 && !this.pashe4) {
                this.pashe3 = true;
                if (!this.getWorld().isClient)
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 300, 7, false, true));
                if (world instanceof World _level)
                    SoundUtil.playSound(_level, x, y, z, new Identifier("block.anvil.place"), 2, 1);
                if (!this.getWorld().isClient)
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 200, false, true));
                Timeout.create(20, () -> {
                    if (world instanceof World _level)
                        SoundUtil.playSound(_level, x, y, z, new Identifier(AnnoyingVillagers.MOD_ID, "steve.p2.speak"), 2, 1);
                    CommandHelper.execute(this, "tellraw @a \"<Steve> You're right. There's no point in reasoning with you.\"");
                    Timeout.create(100, () -> {
                        CommandHelper.execute(this, "tellraw @a \"<Steve> Instead,I'll just force you into submission!\"");
                        Timeout.create(90, () -> {
                            CommandHelper.execute(this, "tellraw @a \"<Steve> Then simply beat the answers out of you!\"");
                            ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD);
                            _setstack.setCount(1);
                            this.setStackInHand(Hand.MAIN_HAND, _setstack);
                            if (world instanceof World _level)
                                SoundUtil.playSound(_level, x, y, z, new Identifier("item.armor.equip_diamond"), 1, 1);
                            this.getMainHandStack().addEnchantment(Enchantments.SHARPNESS, 10);
                            this.getMainHandStack().addEnchantment(Enchantments.KNOCKBACK, 3);
                            this.swingHand(Hand.MAIN_HAND, true);
                            Timeout.create(60, () -> {
                                CommandHelper.execute(this, "tellraw @a \"<Steve> Hows that sound?\"");
                                if (!this.getWorld().isClient)
                                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 3, false, true));
                                this.setCustomName(Text.literal("Angry Steve"));
                            });
                        });
                    });
                });
            }
        }
        if (this.getHealth() < 51) {
            if (this.pashe2 && this.pashe3 && !this.pashe4) {
                this.pashe4 = true;
                this.setCustomName(Text.literal("Angry Steve Pashe II"));
                if (!this.getWorld().isClient) {
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 7, false, false));
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 4, false, false));
                }
                if (world instanceof World _level)
                    SoundUtil.playSound(_level, x, y, z, new Identifier("block.anvil.place"), 1, 1);
                this.equipStack(EquipmentSlot.FEET, new ItemStack(Items.DIAMOND_BOOTS));
                this.getEquippedStack(EquipmentSlot.FEET).addEnchantment(Enchantments.PROTECTION, 4);
                this.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.DIAMOND_LEGGINGS));
                this.getEquippedStack(EquipmentSlot.LEGS).addEnchantment(Enchantments.PROTECTION, 4);
                this.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
                this.getEquippedStack(EquipmentSlot.CHEST).addEnchantment(Enchantments.PROTECTION, 4);
                this.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET));
                this.getEquippedStack(EquipmentSlot.HEAD).addEnchantment(Enchantments.PROTECTION, 4);
                if (world instanceof World _level) {
                    SoundUtil.playSound(_level, x, y, z, new Identifier("item.armor.equip_diamond"), 1, 1);
                }
                if (world instanceof World _level)
                    SoundUtil.playSound(_level, x, y, z, new Identifier(AnnoyingVillagers.MOD_ID, "steve.pashe3.iwky"), 1, 1);
                CommandHelper.execute(this, "tellraw @a \"<Steve> I will kill you,I WILL KILL YOU!\"");
            }
        }
        this.timer++;
        if (this.timer == 80) {
            this.timer = 0;
            attack = MathHelper.nextInt(Random.create(), 1, 5);
            if (attack == 1) attack = MathHelper.nextInt(Random.create(), 5, 9);
            if (attack == 2) attack = MathHelper.nextInt(Random.create(), 9, 12);
            if (attack == 3) attack = MathHelper.nextInt(Random.create(), 12, 15);
        }
        {
            final Vec3d _center = new Vec3d(x, y, z);
            List<Entity> _entfound = world.getEntitiesByClass(Entity.class, new Box(_center, _center).expand(32 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.squaredDistanceTo(_center))).toList();
            for (Entity entityiterator : _entfound) {
                if (this.getTarget() == entityiterator) {
                    if (attack == 13) {
                        CommandHelper.execute(this, "tellraw @a \"<Steve> Wake Up!\"");
                        if (world instanceof World _level)
                            SoundUtil.playSound(_level, x, y, z, new Identifier(AnnoyingVillagers.MOD_ID, "legendary.sword.wake.up"), 1, 1);
                        ItemStack _setstack = new ItemStack(AnnoyingModItems.AWAKENING_LEGENDARY_SWORD.get());
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, _setstack);
                        if (world instanceof ServerWorld _level)
                            _level.spawnParticles(ParticleTypes.TOTEM_OF_UNDYING, x, (y + 10), z, 35, 0.2, 0.2, 0.2, 0.2);
                        if (world instanceof World _level)
                            SoundUtil.playSound(_level, x, y, z, new Identifier("item.totem.use"), 1, 1);
                        this.requestTeleport(x, (y + 10), z);
                        Timeout.create(15, () -> {
                            this.fallDistance = 0;
                            Timeout.create(4, () -> {
                                world.addParticle(ParticleTypes.EXPLOSION_EMITTER, x, y, z, 0, 0, 0);
                                if (!this.getWorld().isClient)
                                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 80, 2, false, false));
                            });
                        });
                    }
                    if (attack == 14) {
                        ItemStack _setstack = new ItemStack(AnnoyingModItems.LEGENDARY_SWORD.get());
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, _setstack);
                    }
                    if (attack == 15) {
                        ItemStack _setstack = new ItemStack(AnnoyingModItems.LEGENDARY_SWORD.get());
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, _setstack);
                    }
                    if (attack == 7) {
                        ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD);
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, _setstack);
                        this.getMainHandStack().addEnchantment(Enchantments.SHARPNESS, 10);
                        this.getMainHandStack().addEnchantment(Enchantments.KNOCKBACK, 3);
                    }
                    if (attack == 11) {
                        ItemStack _setstack = new ItemStack(AnnoyingModItems.CRAFTING_TABLE.get());
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, _setstack);
                    }
                    if (attack == 4) {
                        ItemStack _setstack = new ItemStack(AnnoyingModItems.ENCHANTED_OAK_DOOR.get());
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, _setstack);
                    }
                    if (attack == 3) {
                        ItemStack _setstack = new ItemStack(AnnoyingModItems.WOOPIE.get());
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, _setstack);
                    }
                    if (attack == 6) {
                        if (world instanceof World _level)
                            SoundUtil.playSound(_level, x, y, z, new Identifier("entity.ender_pearl.throw"), 1, 1);
                        World projectileLevel = this.getWorld();
                        if (!projectileLevel.isClient) {
                            ProjectileEntity _entityToSpawn = new EnderPearlEntity(EntityType.ENDER_PEARL, projectileLevel);
                            _entityToSpawn.setOwner(this);
                            _entityToSpawn.setPosition(this.getX(), this.getEyeY() - 0.1, this.getZ());
                            _entityToSpawn.setVelocity(this.getRotationVector().x, this.getRotationVector().y, this.getRotationVector().z, 1, 0);
                            projectileLevel.spawnEntity(_entityToSpawn);
                        }
                    }
                    if (attack == 2) {
                        if (world instanceof World _level)
                            SoundUtil.playSound(_level, x, y, z, new Identifier("entity.ender_pearl.throw"), 1, 1);
                        World projectileLevel = this.getWorld();
                        if (!projectileLevel.isClient) {
                            ProjectileEntity _entityToSpawn = new EnderPearlEntity(EntityType.ENDER_PEARL, projectileLevel);
                            _entityToSpawn.setOwner(this);
                            _entityToSpawn.setPosition(this.getX(), this.getEyeY() - 0.1, this.getZ());
                            _entityToSpawn.setVelocity(this.getRotationVector().x, this.getRotationVector().y, this.getRotationVector().z, 1, 0);
                            projectileLevel.spawnEntity(_entityToSpawn);
                        }
                    }
                    if (attack == 1) {
                        ItemStack _setstack = new ItemStack(AnnoyingModItems.FISHING_ROD.get());
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
                    if (attack == 12) {
                        ItemStack stack = new ItemStack(Items.ENCHANTED_GOLDEN_APPLE);
                        stack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, stack);
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
                        Timeout.create(15, runnable);
                        Timeout.create(25, runnable);
                        Timeout.create(30, () -> {
                            runnable.run();
                            if (!this.getWorld().isClient)
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 4, false, true));
                            ItemStack _setstack = new ItemStack(AnnoyingModItems.LEGENDARY_SWORD.get());
                            _setstack.setCount(1);
                            this.setStackInHand(Hand.MAIN_HAND, _setstack);
                        });
                    }
                    if (attack == 5) {
                        ItemStack stack = new ItemStack(Items.ENCHANTED_GOLDEN_APPLE);
                        stack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, stack);
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
                        Timeout.create(15, runnable);
                        Timeout.create(25, runnable);
                        Timeout.create(30, () -> {
                            runnable.run();
                            if (!this.getWorld().isClient)
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 4, false, true));
                            ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD);
                            _setstack.setCount(1);
                            this.setStackInHand(Hand.MAIN_HAND, _setstack);
                        });
                    }
                    if (attack == 9) {
                        ItemStack stack = new ItemStack(Items.ENCHANTED_GOLDEN_APPLE);
                        stack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, stack);
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
                        Timeout.create(15, runnable);
                        Timeout.create(25, runnable);
                        Timeout.create(30, () -> {
                            runnable.run();
                            if (!this.getWorld().isClient)
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 4, false, true));
                            ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD);
                            _setstack.setCount(1);
                            this.setStackInHand(Hand.MAIN_HAND, _setstack);
                        });
                    }
                    if (attack == 10) {
                        ItemStack _setstack = new ItemStack(Items.BOW);
                        _setstack.setCount(1);
                        this.setStackInHand(Hand.MAIN_HAND, _setstack);
                        Runnable runnable = () -> {
                            if (world instanceof World _level)
                                SoundUtil.playSound(_level, x, y, z, new Identifier("entity.arrow.shoot"), 1, 1);
                            World projectileLevel = this.getWorld();
                            if (!projectileLevel.isClient) {
                                PersistentProjectileEntity _entityToSpawn = new ArrowEntity(EntityType.ARROW, projectileLevel);
                                _entityToSpawn.setOwner(this);
                                _entityToSpawn.setDamage(4);
                                _entityToSpawn.setPunch(1);
                                _entityToSpawn.setPosition(this.getX(), this.getEyeY() - 0.1, this.getZ());
                                _entityToSpawn.setVelocity(this.getRotationVector().x, this.getRotationVector().y, this.getRotationVector().z, 3, 0);
                                projectileLevel.spawnEntity(_entityToSpawn);
                            }
                            this.swingHand(Hand.MAIN_HAND, true);
                        };
                        Timeout.create(0, runnable);
                        Timeout.create(10, runnable);
                        Timeout.create(20, runnable);
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
