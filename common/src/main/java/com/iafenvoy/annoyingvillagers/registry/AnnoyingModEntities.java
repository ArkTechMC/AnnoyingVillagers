package com.iafenvoy.annoyingvillagers.registry;

import com.iafenvoy.annoyingvillagers.AnnoyingVillagers;
import com.iafenvoy.annoyingvillagers.entity.*;
import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Supplier;

public class AnnoyingModEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(AnnoyingVillagers.MOD_ID, RegistryKeys.ENTITY_TYPE);
    public static final RegistrySupplier<EntityType<SteveEntity>> STEVE = build("steve", SteveEntity::new, SpawnGroup.MONSTER, 16, 3, false, 0.6f, 1.8f);
    public static final RegistrySupplier<EntityType<GraveEntity>> GRAVE = build("x_grave_x", GraveEntity::new, SpawnGroup.MONSTER, 16, 3, false, 0.6f, 1.8f);
    public static final RegistrySupplier<EntityType<BlueDemonEntity>> BLUE_DEMON = build("blue_demon", BlueDemonEntity::new, SpawnGroup.MONSTER, 16, 3, false, 0.6f, 1.8f);
    public static final RegistrySupplier<EntityType<AlexEntity>> ALEX = build("alex", AlexEntity::new, SpawnGroup.MONSTER, 16, 3, false, 0.6f, 1.8f);
    public static final RegistrySupplier<EntityType<IllagerKingEntity>> ILLAGER_KING = build("illager_king", IllagerKingEntity::new, SpawnGroup.MONSTER, 16, 3, false, 0.6f, 1.8f);
    public static final RegistrySupplier<EntityType<ChrisEntity>> CHRIS = build("chris", ChrisEntity::new, SpawnGroup.MONSTER, 16, 3, false, 0.6f, 1.8f);
    public static final RegistrySupplier<EntityType<GregEntity>> GREG = build("greg", GregEntity::new, SpawnGroup.MONSTER, 16, 3, false, 0.6f, 1.8f);

    private static <T extends Entity> RegistrySupplier<EntityType<T>> build(String name, EntityType.EntityFactory<T> constructor, SpawnGroup category, int trackingRange, int updateInterval, boolean fireImmune, float sizeX, float sizeY) {
        return register(name, () -> {
            EntityType.Builder<T> builder = EntityType.Builder.create(constructor, category).maxTrackingRange(trackingRange).trackingTickInterval(updateInterval).setDimensions(sizeX, sizeY);
            if (fireImmune) builder.makeFireImmune();
            return builder.build(name);
        });
    }

    private static <T extends Entity> RegistrySupplier<EntityType<T>> register(String name, Supplier<EntityType<T>> type) {
        return REGISTRY.register(name, type);
    }

    public static void init() {
        registerAttributes();
    }

    public static void registerAttributes() {
        EntityAttributeRegistry.register(STEVE, SteveEntity::createAttributes);
        EntityAttributeRegistry.register(GRAVE, GraveEntity::createAttributes);
        EntityAttributeRegistry.register(BLUE_DEMON, BlueDemonEntity::createAttributes);
        EntityAttributeRegistry.register(ALEX, AlexEntity::createAttributes);
        EntityAttributeRegistry.register(ILLAGER_KING, IllagerKingEntity::createAttributes);
        EntityAttributeRegistry.register(CHRIS, ChrisEntity::createAttributes);
        EntityAttributeRegistry.register(GREG, GregEntity::createAttributes);
    }
}
