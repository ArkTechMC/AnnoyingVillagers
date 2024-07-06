package com.iafenvoy.annoyingvillagers.fabric;

import com.iafenvoy.annoyingvillagers.AnnoyingVillagers;
import net.fabricmc.api.ClientModInitializer;

public class AnnoyingVillagersFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AnnoyingVillagers.initClient();
        AnnoyingVillagers.processClient();
    }
}
