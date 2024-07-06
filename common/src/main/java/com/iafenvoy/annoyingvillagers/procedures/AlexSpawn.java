package com.iafenvoy.annoyingvillagers.procedures;

import com.iafenvoy.annoyingvillagers.AnnoyingVillagers;
import com.iafenvoy.annoyingvillagers.util.CommandHelper;
import com.iafenvoy.annoyingvillagers.util.SoundUtil;
import com.iafenvoy.annoyingvillagers.util.Timeout;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class AlexSpawn {
    public static void execute(WorldAccess world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        if (!entity.getWorld().isClient() && entity.getServer() != null)
            CommandHelper.execute(entity, "/tellraw @a [{\"text\":\"Alex joined the game\",\"color\":\"yellow\",\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false}]");
        Timeout.create(20, () -> {
            if (!entity.getWorld().isClient && entity.getServer() != null)
                CommandHelper.execute(entity, "tellraw @a \"<Alex> I'm way stronger than all of you combined!\"");
            if (world instanceof World _level)
                SoundUtil.playSound(_level, x, y, z, new Identifier(AnnoyingVillagers.MOD_ID, "alex.spawn"), 1, 1);
        });
    }
}
