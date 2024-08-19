package com.iafenvoy.annoyingvillagers.forge;

import com.iafenvoy.annoyingvillagers.AnnoyingVillagers;
import com.iafenvoy.annoyingvillagers.AnnoyingVillagersClient;
import dev.architectury.platform.Platform;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AnnoyingVillagers.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AnnoyingVillagersForge {
    public AnnoyingVillagersForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(AnnoyingVillagers.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        AnnoyingVillagers.init();
        if (Platform.getEnv() == Dist.CLIENT)
            AnnoyingVillagersClient.init();
    }

    @SubscribeEvent
    public static void onInit(FMLCommonSetupEvent event) {
        event.enqueueWork(AnnoyingVillagers::process);
    }

    @SubscribeEvent
    public static void onClientInit(FMLClientSetupEvent event) {
        event.enqueueWork(AnnoyingVillagersClient::process);
    }
}