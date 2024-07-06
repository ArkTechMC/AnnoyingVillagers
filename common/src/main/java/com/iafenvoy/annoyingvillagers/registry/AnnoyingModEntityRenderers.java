package com.iafenvoy.annoyingvillagers.registry;

import com.iafenvoy.annoyingvillagers.entity.*;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class AnnoyingModEntityRenderers {
    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(AnnoyingModEntities.STEVE, SteveEntity.textures::createRenderer);
        EntityRendererRegistry.register(AnnoyingModEntities.GRAVE, GraveEntity.textures::createRenderer);
        EntityRendererRegistry.register(AnnoyingModEntities.BLUE_DEMON, BlueDemonEntity.textures::createRenderer);
        EntityRendererRegistry.register(AnnoyingModEntities.ALEX, AlexEntity.textures::createRenderer);
        EntityRendererRegistry.register(AnnoyingModEntities.ILLAGER_KING, IllagerKingEntity.textures::createRenderer);
        EntityRendererRegistry.register(AnnoyingModEntities.CHRIS, ChrisEntity.textures::createRenderer);
        EntityRendererRegistry.register(AnnoyingModEntities.GREG, GregEntity.textures::createRenderer);
    }
}
