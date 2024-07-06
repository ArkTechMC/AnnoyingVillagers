package com.iafenvoy.annoyingvillagers.registry.util;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ISwingable {
    boolean onEntitySwing(ItemStack itemtack, Entity entity);

    boolean onSwingHand(ItemStack itemtack, World world, double x, double y, double z);
}
