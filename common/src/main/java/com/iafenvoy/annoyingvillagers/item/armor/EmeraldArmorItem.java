package com.iafenvoy.annoyingvillagers.item.armor;

import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItemGroups;
import com.iafenvoy.neptune.object.item.ArmorMaterialUtil;
import net.minecraft.block.Blocks;
import net.minecraft.item.ArmorItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public abstract class EmeraldArmorItem extends ArmorItem {
    public EmeraldArmorItem(Type type) {
        super(ArmorMaterialUtil.of("emerald", new int[]{13, 15, 16, 11}, 35, new int[]{3, 6, 8, 3}, 19, Registries.SOUND_EVENT.get(new Identifier("item.armor.equip_diamond")), 3.5f, 0, () -> Blocks.EMERALD_BLOCK), type, new Settings().fireproof().arch$tab(AnnoyingModItemGroups.ARMORS));
    }

    public static class Helmet extends EmeraldArmorItem {
        public Helmet() {
            super(Type.HELMET);
        }
    }

    public static class Chestplate extends EmeraldArmorItem {
        public Chestplate() {
            super(Type.CHESTPLATE);
        }
    }

    public static class Leggings extends EmeraldArmorItem {
        public Leggings() {
            super(Type.LEGGINGS);
        }
    }

    public static class Boots extends EmeraldArmorItem {
        public Boots() {
            super(Type.BOOTS);
        }
    }
}
