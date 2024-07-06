package com.iafenvoy.annoyingvillagers.procedures;

import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.WorldAccess;
import net.minecraftforge.registries.ForgeRegistries;
import com.iafenvoy.annoyingvillagers.AnnoyingMod;

public class SteveSpawn {
	public static void execute(WorldAccess world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		{
			Entity _ent = entity;
			if (!_ent.getWorld().isClient() && _ent.getServer() != null) {
				_ent.getServer().getCommandManager()
						.executeWithPrefix(new ServerCommandSource(CommandOutput.DUMMY, _ent.getPos(), _ent.getRotationClient(), _ent.getWorld() instanceof ServerWorld ? (ServerWorld) _ent.getWorld() : null, 4, _ent.getName().getString(),
								_ent.getDisplayName(), _ent.getWorld().getServer(), _ent),
								"/tellraw @a [{\"text\":\"Steve joined the game\",\"color\":\"yellow\",\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false}]");
			}
		}
		AnnoyingMod.queueServerWork(20, () -> {
			{
				Entity _ent = entity;
				if (!_ent.level().isClientSide() && _ent.getServer() != null) {
					_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
							_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "tellraw @a \"<Steve> My name is Steve,and I'm come to destory Herobrine\"");
				}
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), Registries.SOUND_EVENT.get(new ResourceLocation("annoying_villagers:steve.spawn")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, Registries.SOUND_EVENT.get(new ResourceLocation("annoying_villagers:steve.spawn")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		});
	}
}
