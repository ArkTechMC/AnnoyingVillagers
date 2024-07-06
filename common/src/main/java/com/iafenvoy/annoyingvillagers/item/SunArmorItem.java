package com.iafenvoy.annoyingvillagers.item;

import com.iafenvoy.annoyingvillagers.registry.util.ArmorMaterialUtil;
import net.minecraft.block.Blocks;
import net.minecraft.item.ArmorItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public abstract class SunArmorItem extends ArmorItem {
    public SunArmorItem(Type type, Settings properties) {
        super(ArmorMaterialUtil.of("sun", new int[]{13, 15, 16, 11}, 35, new int[]{4, 7, 9, 4}, 21, Registries.SOUND_EVENT.get(new Identifier("item.armor.equip_iron")), 3.5f, 0, () -> Blocks.IRON_BLOCK), type, properties);
    }

    public static class Helmet extends EnchantedDiamondArmorItem {
        public Helmet() {
            super(Type.HELMET, new Settings().fireproof());
        }
    }

    public static class Chestplate extends EnchantedDiamondArmorItem {
        public Chestplate() {
            super(Type.CHESTPLATE, new Settings().fireproof());
        }
    }

    public static class Leggings extends EnchantedDiamondArmorItem {
        public Leggings() {
            super(Type.LEGGINGS, new Settings().fireproof());
        }
    }

    public static class Boots extends EnchantedDiamondArmorItem {
        public Boots() {
            super(Type.BOOTS, new Settings().fireproof());
        }
    }
}
