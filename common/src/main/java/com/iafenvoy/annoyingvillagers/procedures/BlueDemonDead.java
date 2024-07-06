package com.iafenvoy.annoyingvillagers.procedures;

import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraftforge.registries.ForgeRegistries;
import com.iafenvoy.annoyingvillagers.AnnoyingMod;

public class BlueDemonDead {
	public static void execute(WorldAccess world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof World _level) {
			if (!_level.isClient()) {
				_level.playSound(null, BlockPos.ofFloored(x, y, z), Registries.SOUND_EVENT.get(new Identifier("annoying_villagers:blue.demon.dead")), SoundCategory.NEUTRAL, 1, 1);
			} else {
				_level.playSound(x, y, z, Registries.SOUND_EVENT.get(new Identifier("annoying_villagers:blue.demon.dead")), SoundCategory.NEUTRAL, 1, 1, false);
			}
		}
		{
			Entity _ent = entity;
			if (!_ent.getWorld().isClient() && _ent.getServer() != null) {
				_ent.getServer().getCommandManager().executeWithPrefix(new ServerCommandSource(CommandOutput.DUMMY, _ent.getPos(), _ent.getRotationClient(), _ent.getWorld() instanceof ServerWorld ? (ServerWorld) _ent.getWorld() : null, 4,
						_ent.getName().getString(), _ent.getDisplayName(), _ent.getWorld().getServer(), _ent), "tellraw @a \"<Blue Deamon> I pray you find your own selfish...\"");
			}
		}
	}
}
