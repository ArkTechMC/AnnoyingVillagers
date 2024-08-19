package com.iafenvoy.annoyingvillagers;

import com.iafenvoy.annoyingvillagers.registry.*;

public class AnnoyingVillagers {
    public static final String MOD_ID = "annoying_villagers";

    public static void init() {
        AnnoyingModEntities.REGISTRY.register();
        AnnoyingModItems.REGISTRY.register();
        AnnoyingModItemGroups.REGISTRY.register();
        AnnoyingModSounds.REGISTRY.register();
        AnnoyingModEntities.init();
    }

    public static void process() {
    }
}
