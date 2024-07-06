package com.iafenvoy.annoyingvillagers.item;

import com.iafenvoy.annoyingvillagers.registry.util.ArmorMaterialUtil;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public abstract class DrownedArmorItem extends ArmorItem {
    public DrownedArmorItem(Type type, Settings properties) {
        super(ArmorMaterialUtil.of("drowned", new int[]{13, 15, 26, 11}, 56, new int[]{4, 7, 24, 4}, 37, Registries.SOUND_EVENT.get(new Identifier("item.armor.equip_diamond")), 10, 1, () -> Blocks.DIAMOND_BLOCK), type, properties);
    }

    public static class Chestplate extends DrownedArmorItem {
        public Chestplate() {
            super(Type.CHESTPLATE, new Settings().fireproof());
        }
    }
}
