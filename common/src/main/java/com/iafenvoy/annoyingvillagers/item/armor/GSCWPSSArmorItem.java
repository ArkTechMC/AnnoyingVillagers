package com.iafenvoy.annoyingvillagers.item.armor;

import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItemGroups;
import com.iafenvoy.neptune.object.item.ArmorMaterialUtil;
import net.minecraft.block.Blocks;
import net.minecraft.item.ArmorItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public abstract class GSCWPSSArmorItem extends ArmorItem {
    public GSCWPSSArmorItem(Type type) {
        super(ArmorMaterialUtil.of("grave_paladin", new int[]{13, 15, 26, 11}, 46, new int[]{4, 7, 20, 4}, 35, Registries.SOUND_EVENT.get(new Identifier("item.armor.equip_diamond")), 7, 0.6f, () -> Blocks.EMERALD_BLOCK), type, new Settings().arch$tab(AnnoyingModItemGroups.ARMORS));
    }

    public static class Chestplate extends GSCWPSSArmorItem {
        public Chestplate() {
            super(Type.CHESTPLATE);
        }
    }
}
