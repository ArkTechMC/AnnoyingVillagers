package com.iafenvoy.annoyingvillagers.item;

import com.iafenvoy.annoyingvillagers.registry.util.ArmorMaterialUtil;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public abstract class DarknetheriteArmorItem extends ArmorItem {
    public DarknetheriteArmorItem(Type type, Settings properties) {
        super(ArmorMaterialUtil.of("darknetherite", new int[]{16, 18, 19, 14}, 49, new int[]{7, 10, 12, 7}, 27, Registries.SOUND_EVENT.get(new Identifier("item.armor.equip_netherite")), 5.5f, 0.5f, () -> Items.NETHERITE_INGOT), type, properties);
    }

    public static class Helmet extends DarknetheriteArmorItem {
        public Helmet() {
            super(Type.HELMET, new Settings().fireproof());
        }
    }

    public static class Chestplate extends DarknetheriteArmorItem {
        public Chestplate() {
            super(Type.CHESTPLATE, new Settings().fireproof());
        }
    }

    public static class Leggings extends DarknetheriteArmorItem {
        public Leggings() {
            super(Type.LEGGINGS, new Settings().fireproof());
        }
    }

    public static class Boots extends DarknetheriteArmorItem {
        public Boots() {
            super(Type.BOOTS, new Settings().fireproof());
        }
    }
}
