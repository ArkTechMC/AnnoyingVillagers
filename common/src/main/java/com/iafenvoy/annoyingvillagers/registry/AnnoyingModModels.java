package com.iafenvoy.annoyingvillagers.registry;

import com.iafenvoy.annoyingvillagers.client.model.GPCModel;
import com.iafenvoy.annoyingvillagers.client.model.IKAModel;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class AnnoyingModModels {
    public static void registerLayerDefinitions() {
        EntityModelLayerRegistry.register(GPCModel.LAYER_LOCATION, GPCModel::createBodyLayer);
        EntityModelLayerRegistry.register(IKAModel.LAYER_LOCATION, IKAModel::createBodyLayer);
    }
}

