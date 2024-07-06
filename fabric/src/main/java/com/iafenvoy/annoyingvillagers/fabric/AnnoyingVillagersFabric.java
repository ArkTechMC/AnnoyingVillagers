package com.iafenvoy.annoyingvillagers.fabric;

import com.iafenvoy.annoyingvillagers.AnnoyingVillagers;
import net.fabricmc.api.ModInitializer;

public class AnnoyingVillagersFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        AnnoyingVillagers.init();
        AnnoyingVillagers.process();
    }
}