package com.iafenvoy.annoyingvillagers.entity;

import com.iafenvoy.annoyingvillagers.AnnoyingVillagers;
import com.iafenvoy.annoyingvillagers.client.renderer.Stage;
import com.iafenvoy.annoyingvillagers.procedures.SteveDead;
import com.iafenvoy.annoyingvillagers.procedures.SteveSpawn;
import com.iafenvoy.annoyingvillagers.procedures.SteveTick;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class SteveEntity extends HostileEntity {
    public static final Stage.StagedEntityTextureProvider textures=Stage.ofProvider(AnnoyingVillagers.MOD_ID,"steve");
    private final ServerBossBar bossInfo = new ServerBossBar(this.getDisplayName(), BossBar.Color.BLUE, BossBar.Style.PROGRESS);

    public SteveEntity(EntityType<SteveEntity> type, World world) {
        super(type, world);
        setStepHeight(0.6f);
        experiencePoints = 0;
        setAiDisabled(false);
        setCustomName(Text.literal("Steve"));
        setCustomNameVisible(true);
        setPersistent();
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_SWORD));
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.targetSelector.add(1, new ActiveTargetGoal(this, BlueDemonEntity.class, false, false));
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
        SteveDead.execute(this.getWorld(), this.getX(), this.getY(), this.getZ(), this);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason reason, EntityData livingdata, NbtCompound tag) {
        EntityData retval = super.initialize(world, difficulty, reason, livingdata, tag);
        SteveSpawn.execute(world, this.getX(), this.getY(), this.getZ(), this);
        return retval;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        SteveTick.execute(this.getWorld(), this.getX(), this.getY(), this.getZ(), this);
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
}
