package com.iafenvoy.annoyingvillagers.item.armor;

import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItemGroups;
import com.iafenvoy.neptune.object.item.ArmorMaterialUtil;
import net.minecraft.block.Blocks;
import net.minecraft.item.ArmorItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public abstract class EnchantedDiamondArmorItem extends ArmorItem {
    public EnchantedDiamondArmorItem(Type type) {
        super(ArmorMaterialUtil.of("magic_diamond", new int[]{13, 15, 16, 11}, 47, new int[]{6, 9, 11, 6}, 23, Registries.SOUND_EVENT.get(new Identifier("item.armor.equip_diamond")), 4, 0.3f, () -> Blocks.DIAMOND_BLOCK), type, new Settings().fireproof().arch$tab(AnnoyingModItemGroups.ARMORS));
    }

    public static class Helmet extends EnchantedDiamondArmorItem {
        public Helmet() {
            super(Type.HELMET);
        }
    }

    public static class Chestplate extends EnchantedDiamondArmorItem {
        public Chestplate() {
            super(Type.CHESTPLATE);
        }
    }

    public static class Leggings extends EnchantedDiamondArmorItem {
        public Leggings() {
            super(Type.LEGGINGS);
        }
    }

    public static class Boots extends EnchantedDiamondArmorItem {
        public Boots() {
            super(Type.BOOTS);
        }
    }
}
