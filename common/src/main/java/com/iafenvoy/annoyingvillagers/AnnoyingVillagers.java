package com.iafenvoy.annoyingvillagers;

import com.iafenvoy.annoyingvillagers.client.renderer.IArmorRenderer;
import com.iafenvoy.annoyingvillagers.client.renderer.armor.GSCWPSSArmorRenderer;
import com.iafenvoy.annoyingvillagers.client.renderer.armor.IKArmorRenderer;
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
        IArmorRenderer.register(new IKArmorRenderer(), AnnoyingModItems.ILLAGER_KING_HELMET.get(), AnnoyingModItems.ILLAGER_KING_CHESTPLATE.get());
        IArmorRenderer.register(new GSCWPSSArmorRenderer(), AnnoyingModItems.GRAVE_S_PALADIN_CHESTPLATE.get());
    }
}
