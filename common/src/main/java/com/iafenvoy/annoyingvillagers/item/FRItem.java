package com.iafenvoy.annoyingvillagers.item;

import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;

public class FRItem extends FishingRodItem {
    public FRItem() {
        super(new Item.Settings().maxDamage(128));
    }

    @Override
    public int getEnchantability() {
        return 15;
    }
}

