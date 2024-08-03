package com.iafenvoy.annoyingvillagers.registry;

import com.iafenvoy.annoyingvillagers.AnnoyingVillagers;
import com.iafenvoy.annoyingvillagers.item.*;
import com.iafenvoy.neptune.object.item.FoilItemBase;
import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import dev.architectury.core.item.ArchitecturySpawnEggItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Rarity;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class AnnoyingModItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(AnnoyingVillagers.MOD_ID, RegistryKeys.ITEM);
    public static final RegistrySupplier<Item> LEGENDARY_SWORD = register("legendary_sword", LegendarySwordItem::new);
    public static final RegistrySupplier<Item> AWAKENING_LEGENDARY_SWORD = register("awakening_legendary_sword", AwakeningLegendarySwordItem::new);
    public static final RegistrySupplier<Item> WOOPIE = register("woopie", WoopieItem::new);
    public static final RegistrySupplier<Item> DIAMOND_LONGSWORD = register("diamond_longsword", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 3.5f, 3, 12), 3, -2.2f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> DIAMOND_GREATSWORD = register("diamond_greatsword", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 6.5f, 3, 12), 3, -3.2f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> VILLAGER_KNIGHT_SWORD = register("villager_knight_sword", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 3.5f, 3, 12), 3, -2.2f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> PALADIN_S_SWORD = register("paladin_s_sword", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 4, 3, 12), 3, -2.4f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> DIAMOND_DAGGER = register("diamond_dagger", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 1.5f, 3, 12), 3, -0.5f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> DIAMOND_BLASTER_SWORD = register("diamond_blaster_sword", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 3.5f, 3, 12), 3, -2.5f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> CHARGED_DIAMOND_BLASTER_SWORD = register("charged_diamond_blaster_sword", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 3.5f, 3, 12), 3, -2.6f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> DIAMOND_LONG_BLADE = register("diamond_long_blade", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 3, 3, 12), 3, -2.1f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> DIAMOND_SHARP_BLADE = register("diamond_sharp_blade", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 4.5f, 3, 12), 3, -2.4f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> GREAT_SWORD = register("great_sword", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 4.7f, 3, 12), 3, -2.4f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> BROKEN_GREAT_SWORD = register("broken_great_sword", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 5, 3, 12), 3, -2.3f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> DIAMOND_CLAW_KNIFE = register("diamond_claw_knife", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 1, 3, 12), 3, -0.3f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> BLUE_FLAME_SWORD = register("blue_flame_sword", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 4.3f, 3, 12), 3, -2.4f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> DIAMOND_HOOK_SWORD = register("diamond_hook_sword", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 1.7f, 3, 12), 3, -1.3f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> DIAMOND_LONG_KNIFE = register("diamond_long_knife", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 3.9f, 3, 12), 3, -2.5f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> DIAMOND_ARMBLADES = register("diamond_armblades", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 1.3f, 3, 12), 3, -0.5f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> DIAMOND_SICKLE = register("diamond_sickle", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 3.5f, 3, 12), 3, -2.6f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> CHARGED_DIAMOND_SICKLE = register("charged_diamond_sickle", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 4.5f, 3, 12), 3, -2.6f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> DIAMOND_BOLT = register("diamond_bolt", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 4, 3, 12), 3, -2.5f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> TWIN_DIAMOND_SPEAR = register("twin_diamond_spear", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 3.2f, 3, 12), 3, -2.3f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> DIAMOND_SPEAR = register("diamond_spear", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 3.7f, 3, 12), 3, -2.4f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> DIAMOND_LAEVATEINN = register("diamond_laevateinn", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 6, 3, 12), 3, -3f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> DIAMOND_SCISSORS = register("diamond_scissors", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 1.1f, 3, 12), 3, -0.4f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> DOUBLE_DIAMOND_GLAIVE = register("double_diamond_glaive", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 4.2f, 3, 12), 3, -2.5f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> DIAMOND_FALCHION = register("diamond_falchion", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 2.2f, 3, 12), 3, -1.5f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> DIAMOND_RAPIER = register("diamond_rapier", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 2, 3, 12), 3, -2.3f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> THUNDER_DIAMOND_TWIN_BLADED_BATTLEAXE = register("thunder_diamond_twin_bladed_battleaxe", () -> new AxeItem(ToolMaterialUtil.of(1531, 10, 7.5f, 3, 12), 3, -3.2f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> THUNDER_DIAMOND_BATTLEAXE = register("thunder_diamond_battleaxe", () -> new AxeItem(ToolMaterialUtil.of(1531, 10, 5.5f, 3, 12), 3, -2f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> DIAMOND_GREATAXE = register("diamond_greataxe", () -> new AxeItem(ToolMaterialUtil.of(1531, 10, 5, 3, 12), 3, -2.7f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> DIAMOND_MACE = register("diamond_mace", () -> new PickaxeItem(ToolMaterialUtil.of(1531, 10, 4, 3, 12), 3, -2.7f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> RED_STEEL_AXE = register("red_steel_axe", RSAItem::new);
    public static final RegistrySupplier<Item> RED_STEEL_AXE_LONG = register("red_steel_axe_long", RSALItem::new);
    public static final RegistrySupplier<Item> RED_STEEL_AXE_BASIC = register("red_steel_axe_basic", RSABItem::new);
    public static final RegistrySupplier<Item> RUBY_SWORD = register("ruby_sword", RSItem::new);
    public static final RegistrySupplier<Item> RUBY_GREATSWORD = register("ruby_greatsword", RGSItem::new);
    public static final RegistrySupplier<Item> RUBY_KNIGHT_SWORD = register("ruby_knight_sword", RKSItem::new);
    public static final RegistrySupplier<Item> GOLDEN_HOOK_SWORD = register("golden_hook_sword", () -> new SwordItem(ToolMaterialUtil.of(32, 10, -1.3f, 3, 12), 3, -1.3f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> GOLDEN_LONGSWORD = register("golden_longsword", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 1.5f, 3, 12), 3, -2.2f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> FLAME_KNIFE = register("flame_knife", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 2.5f, 3, 12), 3, -0.5f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> PURPLE_DIAMOND_LONGSWORD = register("purple_diamond_longsword", () -> new SwordItem(ToolMaterialUtil.of(1531, 10, 5, 3, 12), 3, -2.2f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> OAK_DOOR = register("oak_door", ODItem::new);
    public static final RegistrySupplier<Item> ENCHANTED_OAK_DOOR = register("enchanted_oak_door", EODItem::new);
    public static final RegistrySupplier<Item> CRAFTING_TABLE = register("crafting_table", CTItem::new);
    public static final RegistrySupplier<Item> FISHING_ROD = register("fishing_rod", FRItem::new);
    public static final RegistrySupplier<Item> KNIFE = register("knife", () -> new SwordItem(ToolMaterialUtil.of(250, 6, 1.5f, 2, 7), 3, -1.8f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS)));
    public static final RegistrySupplier<Item> KNIFE_WITH_TNT = register("knife_with_tnt", KWTItem::new);

    public static final RegistrySupplier<Item> SHORT_STICK = register("short_stick", () -> new Item(new Item.Settings().rarity(Rarity.COMMON).arch$tab(AnnoyingModItemGroups.MATERIAL)));
    public static final RegistrySupplier<Item> MAGIC_DIAMOND = register("enchanted_diamond", () -> new FoilItemBase(p -> p.rarity(Rarity.EPIC).arch$tab(AnnoyingModItemGroups.MATERIAL)));
    public static final RegistrySupplier<Item> DIAMOND_FRAGMENTS = register("diamond_fragments", () -> new Item(new Item.Settings().rarity(Rarity.COMMON).arch$tab(AnnoyingModItemGroups.MATERIAL)));
    public static final RegistrySupplier<Item> DARKNETHERITE_INGOT = register("darknetherite_ingot", () -> new Item(new Item.Settings().rarity(Rarity.EPIC).arch$tab(AnnoyingModItemGroups.MATERIAL)));
    public static final RegistrySupplier<Item> DARKNETHERITE_NUGGET = register("darknetherite_nugget", () -> new Item(new Item.Settings().rarity(Rarity.EPIC).arch$tab(AnnoyingModItemGroups.MATERIAL)));

    public static final RegistrySupplier<Item> MAGIC_DIAMOND_HELMET = register("enchanted_diamond_helmet", EnchantedDiamondArmorItem.Helmet::new);
    public static final RegistrySupplier<Item> MAGIC_DIAMOND_CHESTPLATE = register("enchanted_diamond_chestplate", EnchantedDiamondArmorItem.Chestplate::new);
    public static final RegistrySupplier<Item> MAGIC_DIAMOND_LEGGINGS = register("enchanted_diamond_leggings", EnchantedDiamondArmorItem.Leggings::new);
    public static final RegistrySupplier<Item> MAGIC_DIAMOND_BOOTS = register("enchanted_diamond_boots", EnchantedDiamondArmorItem.Boots::new);
    public static final RegistrySupplier<Item> DARKNETHERITE_HELMET = register("darknetherite_helmet", DarknetheriteArmorItem.Helmet::new);
    public static final RegistrySupplier<Item> DARKNETHERITE_CHESTPLATE = register("darknetherite_chestplate", DarknetheriteArmorItem.Chestplate::new);
    public static final RegistrySupplier<Item> DARKNETHERITE_LEGGINGS = register("darknetherite_leggings", DarknetheriteArmorItem.Leggings::new);
    public static final RegistrySupplier<Item> DARKNETHERITE_BOOTS = register("darknetherite_boots", DarknetheriteArmorItem.Boots::new);
    public static final RegistrySupplier<Item> SUN_HELMET = register("sun_helmet", SunArmorItem.Helmet::new);
    public static final RegistrySupplier<Item> SUN_CHESTPLATE = register("sun_chestplate", SunArmorItem.Chestplate::new);
    public static final RegistrySupplier<Item> SUN_LEGGINGS = register("sun_leggings", SunArmorItem.Leggings::new);
    public static final RegistrySupplier<Item> SUN_BOOTS = register("sun_boots", SunArmorItem.Boots::new);
    public static final RegistrySupplier<Item> EMERALD_HELMET = register("emerald_helmet", EmeraldArmorItem.Helmet::new);
    public static final RegistrySupplier<Item> EMERALD_CHESTPLATE = register("emerald_chestplate", EmeraldArmorItem.Chestplate::new);
    public static final RegistrySupplier<Item> EMERALD_LEGGINGS = register("emerald_leggings", EmeraldArmorItem.Leggings::new);
    public static final RegistrySupplier<Item> EMERALD_BOOTS = register("emerald_boots", EmeraldArmorItem.Boots::new);
    public static final RegistrySupplier<Item> DROWNED_CHESTPLATE = register("drowned_chestplate", DrownedArmorItem.Chestplate::new);
    public static final RegistrySupplier<Item> GRAVE_S_PALADIN_CHESTPLATE = register("grave_s_chestplate_with_paladin_s_sword", GSCWPSSArmorItem.Chestplate::new);
    public static final RegistrySupplier<Item> GRAVE_S_CHESTPLATE = register("grave_s_chestplate", GraveArmorItem.Chestplate::new);
    public static final RegistrySupplier<Item> ILLAGER_KING_CHESTPLATE = register("illager_king_chestplate", IKArmorItem.Chestplate::new);
    public static final RegistrySupplier<Item> ILLAGER_KING_HELMET = register("illager_king_helmet", IKArmorItem.Helmet::new);

    public static final RegistrySupplier<Item> STEVE_SPAWN_EGG = register("steve_spawn_egg", () -> new ArchitecturySpawnEggItem(AnnoyingModEntities.STEVE, -16737895, -16750951, new Item.Settings().arch$tab(AnnoyingModItemGroups.MOBS)));
    public static final RegistrySupplier<Item> GRAVE_SPAWN_EGG = register("grave_spawn_egg", () -> new ArchitecturySpawnEggItem(AnnoyingModEntities.GRAVE, -16724992, -16751053, new Item.Settings().arch$tab(AnnoyingModItemGroups.MOBS)));
    public static final RegistrySupplier<Item> BLUE_DEMON_SPAWN_EGG = register("blue_demon_spawn_egg", () -> new ArchitecturySpawnEggItem(AnnoyingModEntities.BLUE_DEMON, -16777114, -16777165, new Item.Settings().arch$tab(AnnoyingModItemGroups.MOBS)));
    public static final RegistrySupplier<Item> ALEX_SPAWN_EGG = register("alex_spawn_egg", () -> new ArchitecturySpawnEggItem(AnnoyingModEntities.ALEX, -16724941, -26317, new Item.Settings().arch$tab(AnnoyingModItemGroups.MOBS)));

    private static <T extends Item> RegistrySupplier<T> register(String name, Supplier<T> item) {
        return REGISTRY.register(name, item);
    }
}

