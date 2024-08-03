package com.iafenvoy.annoyingvillagers.item;

import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItemGroups;
import com.iafenvoy.neptune.object.item.ArmorMaterialUtil;
import net.minecraft.block.Blocks;
import net.minecraft.item.ArmorItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public abstract class IKArmorItem extends ArmorItem {
    public IKArmorItem(Type type) {
        super(ArmorMaterialUtil.of("illager_armor", new int[]{13, 15, 26, 23}, 51, new int[]{4, 7, 16, 13}, 33, Registries.SOUND_EVENT.get(new Identifier("item.armor.equip_diamond")), 11, 0.6f, () -> Blocks.EMERALD_BLOCK), type, new Settings().fireproof().arch$tab(AnnoyingModItemGroups.ARMORS));
    }

    public static class Helmet extends IKArmorItem {
        public Helmet() {
            super(Type.HELMET);
        }
    }

    public static class Chestplate extends IKArmorItem {
        public Chestplate() {
            super(Type.CHESTPLATE);
        }
    }
}
