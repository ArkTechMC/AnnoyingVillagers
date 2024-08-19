package com.iafenvoy.annoyingvillagers.fabric;

import com.iafenvoy.annoyingvillagers.AnnoyingVillagersClient;
import net.fabricmc.api.ClientModInitializer;

public class AnnoyingVillagersFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AnnoyingVillagersClient.init();
        AnnoyingVillagersClient.process();
    }
}
