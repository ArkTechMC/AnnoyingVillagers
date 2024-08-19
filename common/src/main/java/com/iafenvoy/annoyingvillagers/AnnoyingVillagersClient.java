package com.iafenvoy.annoyingvillagers;

import com.iafenvoy.annoyingvillagers.client.renderer.armor.GSCWPSSArmorRenderer;
import com.iafenvoy.annoyingvillagers.client.renderer.armor.IKArmorRenderer;
import com.iafenvoy.annoyingvillagers.item.sword.LegendarySwordItem;
import com.iafenvoy.annoyingvillagers.mixin.ModelPredicateProviderRegistryAccessor;
import com.iafenvoy.annoyingvillagers.registry.AnnoyingModEntityRenderers;
import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItems;
import com.iafenvoy.annoyingvillagers.registry.AnnoyingModModels;
import com.iafenvoy.neptune.render.armor.IArmorRendererBase;
import net.minecraft.util.Identifier;

public class AnnoyingVillagersClient {
    public static void init() {
        AnnoyingModEntityRenderers.registerEntityRenderers();
        AnnoyingModModels.registerLayerDefinitions();
    }

    public static void process() {
        IArmorRendererBase.register(new IKArmorRenderer(), AnnoyingModItems.ILLAGER_KING_HELMET.get(), AnnoyingModItems.ILLAGER_KING_CHESTPLATE.get());
        IArmorRendererBase.register(new GSCWPSSArmorRenderer(), AnnoyingModItems.GRAVE_S_PALADIN_CHESTPLATE.get());

        ModelPredicateProviderRegistryAccessor.register(AnnoyingModItems.LEGENDARY_SWORD.get(), new Identifier(AnnoyingVillagers.MOD_ID, LegendarySwordItem.AWAKENING), (stack, world, entity, seed) -> stack.getOrCreateNbt().getBoolean(LegendarySwordItem.AWAKENING) ? 1 : 0);
    }
}
