package com.iafenvoy.annoyingvillagers;

import com.iafenvoy.annoyingvillagers.registry.*;
import com.iafenvoy.annoyingvillagers.util.Timeout;

public class AnnoyingVillagers {
    public static final String MOD_ID = "annoying_villagers";

    public static void init() {
        AnnoyingModEntities.REGISTRY.register();
        AnnoyingModItems.REGISTRY.register();
        AnnoyingModItemGroups.REGISTRY.register();
        AnnoyingModSounds.REGISTRY.register();
        AnnoyingModEntities.registerAttributes();
    }

    public static void process() {
        Timeout.startTimeout();
    }

    public static void initClient() {
        AnnoyingModEntityRenderers.registerEntityRenderers();
        AnnoyingModModels.registerLayerDefinitions();
    }

    public static void processClient() {

    }
}
