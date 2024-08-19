package com.iafenvoy.annoyingvillagers.item.armor;

import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItemGroups;
import com.iafenvoy.neptune.object.item.ArmorMaterialUtil;
import net.minecraft.block.Blocks;
import net.minecraft.item.ArmorItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public abstract class SunArmorItem extends ArmorItem {
    public SunArmorItem(Type type) {
        super(ArmorMaterialUtil.of("sun", new int[]{13, 15, 16, 11}, 35, new int[]{4, 7, 9, 4}, 21, Registries.SOUND_EVENT.get(new Identifier("item.armor.equip_iron")), 3.5f, 0, () -> Blocks.IRON_BLOCK), type, new Settings().fireproof().arch$tab(AnnoyingModItemGroups.ARMORS));
    }

    public static class Helmet extends SunArmorItem {
        public Helmet() {
            super(Type.HELMET);
        }
    }

    public static class Chestplate extends SunArmorItem {
        public Chestplate() {
            super(Type.CHESTPLATE);
        }
    }

    public static class Leggings extends SunArmorItem {
        public Leggings() {
            super(Type.LEGGINGS);
        }
    }

    public static class Boots extends SunArmorItem {
        public Boots() {
            super(Type.BOOTS);
        }
    }
}
