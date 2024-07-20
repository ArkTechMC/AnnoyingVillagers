package com.iafenvoy.annoyingvillagers.registry;

import com.iafenvoy.annoyingvillagers.AnnoyingVillagers;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

import java.util.function.Supplier;

public class AnnoyingModItemGroups {
    public static final DeferredRegister<ItemGroup> REGISTRY = DeferredRegister.create(AnnoyingVillagers.MOD_ID, RegistryKeys.ITEM_GROUP);

    public static final RegistrySupplier<ItemGroup> MOBS = register("mobs", () -> CreativeTabRegistry.create(
            Text.translatable("itemGroup." + AnnoyingVillagers.MOD_ID + ".mobs"),
            () -> new ItemStack(AnnoyingModItems.STEVE_SPAWN_EGG.get())
    ));
    public static final RegistrySupplier<ItemGroup> ARMORS = register("armors", () -> CreativeTabRegistry.create(
            Text.translatable("itemGroup." + AnnoyingVillagers.MOD_ID + ".armors"),
            () -> new ItemStack(AnnoyingModItems.MAGIC_DIAMOND_CHESTPLATE.get())
    ));
    public static final RegistrySupplier<ItemGroup> MATERIAL = register("material", () -> CreativeTabRegistry.create(
            Text.translatable("itemGroup." + AnnoyingVillagers.MOD_ID + ".material"),
            () -> new ItemStack(AnnoyingModItems.MAGIC_DIAMOND.get())
    ));
    public static final RegistrySupplier<ItemGroup> ORDINARY_WEAPONS = register("ordinary_weapons", () -> CreativeTabRegistry.create(
            Text.translatable("itemGroup." + AnnoyingVillagers.MOD_ID + ".ordinary_weapons"),
            () -> new ItemStack(AnnoyingModItems.DIAMOND_LONGSWORD.get())
    ));

    public static RegistrySupplier<ItemGroup> register(String name, Supplier<ItemGroup> group) {
        return REGISTRY.register(name, group);
    }
}
