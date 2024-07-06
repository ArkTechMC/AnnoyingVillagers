package com.iafenvoy.annoyingvillagers.procedures;

import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraftforge.registries.ForgeRegistries;

public class WoodenWeaponAttack {
    public static void execute(WorldAccess world, double x, double y, double z) {
        if (world instanceof World _level) {
            if (!_level.isClient()) {
                _level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("entity.zombie.break_wooden_door")), SoundCategory.NEUTRAL, 1, 1);
            } else {
                _level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("entity.zombie.break_wooden_door")), SoundCategory.NEUTRAL, 1, 1, false);
            }
        }
    }
}