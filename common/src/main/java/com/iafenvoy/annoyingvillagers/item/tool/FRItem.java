package com.iafenvoy.annoyingvillagers.item.tool;

import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItemGroups;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;

public class FRItem extends FishingRodItem {
    public FRItem() {
        super(new Item.Settings().maxDamage(128).arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS));
    }

    @Override
    public int getEnchantability() {
        return 15;
    }
}

