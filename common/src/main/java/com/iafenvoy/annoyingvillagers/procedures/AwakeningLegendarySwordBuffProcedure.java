package com.iafenvoy.annoyingvillagers.procedures;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class AwakeningLegendarySwordBuffProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
			_entity.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 5, 1, true, false));
	}
}
