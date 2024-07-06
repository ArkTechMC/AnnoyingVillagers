package com.iafenvoy.annoyingvillagers.procedures;

import com.iafenvoy.annoyingvillagers.util.CommandHelper;
import com.iafenvoy.annoyingvillagers.util.Timeout;
import net.minecraft.entity.Entity;
import net.minecraft.world.WorldAccess;

public class AlexDead {
    public static void execute(WorldAccess world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        Timeout.create(20, () -> {
            if (!entity.getWorld().isClient && entity.getServer() != null)
                CommandHelper.execute(entity, "/tellraw @a [{\"text\":\"Alex left the game\",\"color\":\"yellow\",\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false}]");
        });
    }
}
