package com.iafenvoy.annoyingvillagers.registry;

import com.iafenvoy.annoyingvillagers.AnnoyingVillagers;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class AnnoyingModSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(AnnoyingVillagers.MOD_ID, RegistryKeys.SOUND_EVENT);
    public static final RegistrySupplier<SoundEvent> LEGENDARY_SWORD_WAKE_UP = register("legendary.sword.wake.up", "legendary.sword.wake.up");
    public static final RegistrySupplier<SoundEvent> STEVE_PASHE2_IHMW = register("steve.pashe2.ihmw", "steve.pashe2.ihmw");
    public static final RegistrySupplier<SoundEvent> STEVE_PASHE2_TMDGB = register("steve.pashe2.tmdgb", "steve.pashe2.tmdgb");
    public static final RegistrySupplier<SoundEvent> STEVE_PASHE3_IWKY = register("steve.pashe3.iwky", "steve.pashe3.iwky");
    public static final RegistrySupplier<SoundEvent> STEVE_SPAWN = register("steve.spawn", "steve.spawm");
    public static final RegistrySupplier<SoundEvent> STEVE_DEAD = register("steve.dead", "steve.dead");
    public static final RegistrySupplier<SoundEvent> GRAVE_DEAD = register("grave.dead", "grave.dead");
    public static final RegistrySupplier<SoundEvent> GRAVE_SPAWN = register("grave.spawn", "grave.spawn");
    public static final RegistrySupplier<SoundEvent> GRAVE_PASHE2_IWKY = register("grave.pashe2.iwky", "grave.pashe2.iwky");
    public static final RegistrySupplier<SoundEvent> BLUE_DEMON_DEAD = register("blue.demon.dead", "blue.demon.dead");
    public static final RegistrySupplier<SoundEvent> BLUE_DEMON_SPAWN = register("blue.demon.spawn", "blue.demon.spawn");
    public static final RegistrySupplier<SoundEvent> BLUE_DEMON_ITTRSP = register("blue.demon.ittrsp", "blue.demon.ittrsp");
    public static final RegistrySupplier<SoundEvent> BLUE_DEMON_TRIDENT_FESTIVAL = register("blue.demon.trident.festival", "blue.demon.trident.festival");
    public static final RegistrySupplier<SoundEvent> ALEX_SPAWN = register("alex.spawn", "alex.spawn");
    public static final RegistrySupplier<SoundEvent> STEVE_PUTARMOR = register("steve.putarmor", "steve.putarmor");
    public static final RegistrySupplier<SoundEvent> STEVE_P2_SPEAL = register("steve.p2.speak", "steve.p2.speak");

    public static RegistrySupplier<SoundEvent> register(String name, String soundId) {
        return REGISTRY.register(name, () -> SoundEvent.of(new Identifier(AnnoyingVillagers.MOD_ID, soundId)));
    }
}