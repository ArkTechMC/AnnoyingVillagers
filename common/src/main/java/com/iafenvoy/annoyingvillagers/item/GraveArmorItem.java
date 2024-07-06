package com.iafenvoy.annoyingvillagers.item;

import com.iafenvoy.annoyingvillagers.registry.util.ArmorMaterialUtil;
import net.minecraft.block.Blocks;
import net.minecraft.item.ArmorItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public abstract class GraveArmorItem extends ArmorItem {
    public GraveArmorItem(Type type, Settings properties) {
        super(ArmorMaterialUtil.of("grave_paladin", new int[]{13, 15, 26, 11}, 46, new int[]{4, 7, 20, 4}, 35, Registries.SOUND_EVENT.get(new Identifier("item.armor.equip_diamond")), 7, 0.6f, () -> Blocks.EMERALD_BLOCK), type, properties);
    }

    public static class Chestplate extends DrownedArmorItem {
        public Chestplate() {
            super(Type.CHESTPLATE, new Settings().fireproof());
        }
    }
}