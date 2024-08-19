package com.iafenvoy.annoyingvillagers.item.armor;

import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItemGroups;
import com.iafenvoy.neptune.object.item.ArmorMaterialUtil;
import net.minecraft.block.Blocks;
import net.minecraft.item.ArmorItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public abstract class DrownedArmorItem extends ArmorItem {
    public DrownedArmorItem(Type type) {
        super(ArmorMaterialUtil.of("drowned", new int[]{13, 15, 26, 11}, 56, new int[]{4, 7, 24, 4}, 37, Registries.SOUND_EVENT.get(new Identifier("item.armor.equip_diamond")), 10, 1, () -> Blocks.DIAMOND_BLOCK), type, new Settings().fireproof().arch$tab(AnnoyingModItemGroups.ARMORS));
    }

    public static class Chestplate extends DrownedArmorItem {
        public Chestplate() {
            super(Type.CHESTPLATE);
        }
    }
}
