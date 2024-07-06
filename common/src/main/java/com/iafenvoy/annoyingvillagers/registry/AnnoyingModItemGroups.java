package com.iafenvoy.annoyingvillagers.registry;

import com.iafenvoy.annoyingvillagers.AnnoyingVillagers;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKeys;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AnnoyingModItemGroups {
	public static final DeferredRegister<ItemGroup> REGISTRY = DeferredRegister.create(AnnoyingVillagers.MOD_ID,RegistryKeys.ITEM_GROUP);

	public static final RegistrySupplier<ItemGroup> MOBS = REGISTRY.register("mobs", () -> CreativeTabRegistry.builder()
			.title(Component.translatable("ItemGroup.annoying_villagers.mobs"))
			.icon(() -> new ItemStack(AnnoyingModItems.STEVE_SPAWN_EGG.get()))
			.displayItems((parameters, output) -> {
				output.accept(AnnoyingModItems.STEVE_SPAWN_EGG.get());
				output.accept(AnnoyingModItems.GRAVE_SPAWN_EGG.get());
				output.accept(AnnoyingModItems.BLUE_DEMON_SPAWN_EGG.get());
				output.accept(AnnoyingModItems.ALEX_SPAWN_EGG.get());
			}).build());
	public static final RegistrySupplier<ItemGroup> ARMORS = REGISTRY.register("armors", () -> CreativeModeTab.builder()
			.title(Component.translatable("ItemGroup.annoying_villagers.armors"))
			.icon(() -> new ItemStack(AnnoyingModItems.MAGIC_DIAMOND_CHESTPLATE.get()))
			.displayItems((parameters, output) -> {
				output.accept(AnnoyingModItems.MAGIC_DIAMOND_HELMET.get());
				output.accept(AnnoyingModItems.MAGIC_DIAMOND_CHESTPLATE.get());
				output.accept(AnnoyingModItems.MAGIC_DIAMOND_LEGGINGS.get());
				output.accept(AnnoyingModItems.MAGIC_DIAMOND_BOOTS.get());
				output.accept(AnnoyingModItems.DARKNETHERITE_HELMET.get());
				output.accept(AnnoyingModItems.DARKNETHERITE_CHESTPLATE.get());
				output.accept(AnnoyingModItems.DARKNETHERITE_LEGGINGS.get());
				output.accept(AnnoyingModItems.DARKNETHERITE_BOOTS.get());
				output.accept(AnnoyingModItems.EMERALD_HELMET.get());
				output.accept(AnnoyingModItems.EMERALD_CHESTPLATE.get());
				output.accept(AnnoyingModItems.EMERALD_LEGGINGS.get());
				output.accept(AnnoyingModItems.EMERALD_BOOTS.get());
				output.accept(AnnoyingModItems.SUN_HELMET.get());
				output.accept(AnnoyingModItems.SUN_CHESTPLATE.get());
				output.accept(AnnoyingModItems.SUN_LEGGINGS.get());
				output.accept(AnnoyingModItems.SUN_BOOTS.get());
				output.accept(AnnoyingModItems.DROWNED_CHESTPLATE.get());
				output.accept(AnnoyingModItems.GRAVE_S_PALADIN_CHESTPLATE.get());
				output.accept(AnnoyingModItems.GRAVE_S_CHESTPLATE.get());
				output.accept(AnnoyingModItems.ILLAGER_KING_HELMET.get());
				output.accept(AnnoyingModItems.ILLAGER_KING_CHESTPLATE.get());
			}).build());
	public static final RegistryObject<ItemGroup> ADVANCED_WEAPONS = REGISTRY.register("advanced_weapons", () -> CreativeModeTab.builder()
			.title(Component.translatable("ItemGroup.annoying_villagers.advanced_weapons"))
			.icon(() -> new ItemStack(AnnoyingModItems.LEGENDARY_SWORD.get()))
			.displayItems((parameters, output) -> {
				output.accept(AnnoyingModItems.LEGENDARY_SWORD.get());
				output.accept(AnnoyingModItems.WOOPIE.get());
				output.accept(AnnoyingModItems.RED_STEEL_AXE.get());
				output.accept(AnnoyingModItems.RED_STEEL_AXE_LONG.get());
				output.accept(AnnoyingModItems.RED_STEEL_AXE_BASIC.get());
				output.accept(AnnoyingModItems.RUBY_SWORD.get());
				output.accept(AnnoyingModItems.RUBY_KNIGHT_SWORD.get());
				output.accept(AnnoyingModItems.RUBY_GREATSWORD.get());
			}).build());
	public static final RegistryObject<ItemGroup> MATERIAL = REGISTRY.register("material", () -> CreativeModeTab.builder()
			.title(Component.translatable("ItemGroup.annoying_villagers.material"))
			.icon(() -> new ItemStack(AnnoyingModItems.MAGIC_DIAMOND.get()))
			.displayItems((parameters, output) -> {
				output.accept(AnnoyingModItems.SHORT_STICK.get());
				output.accept(AnnoyingModItems.MAGIC_DIAMOND.get());
				output.accept(AnnoyingModItems.DIAMOND_FRAGMENTS.get());
				output.accept(AnnoyingModItems.DARKNETHERITE_INGOT.get());
				output.accept(AnnoyingModItems.DARKNETHERITE_NUGGET.get());
						}).build());
		public static final RegistryObject<ItemGroup> ORDINARY_WEAPONS = REGISTRY.register("ordinary_weapons", () -> CreativeModeTab.builder()
				.title(Component.translatable("ItemGroup.annoying_villagers.ordinary_weapons"))
				.icon(() -> new ItemStack(AnnoyingModItems.DIAMOND_LONGSWORD.get()))
				.displayItems((parameters, output) -> {
					output.accept(AnnoyingModItems.DIAMOND_GREATSWORD.get());
					output.accept(AnnoyingModItems.DIAMOND_LONGSWORD.get());
					output.accept(AnnoyingModItems.VILLAGER_KNIGHT_SWORD.get());
					output.accept(AnnoyingModItems.DIAMOND_DAGGER.get());
					output.accept(AnnoyingModItems.PALADIN_S_SWORD.get());
					output.accept(AnnoyingModItems.CHARGED_DIAMOND_BLASTER_SWORD.get());
					output.accept(AnnoyingModItems.DIAMOND_BLASTER_SWORD.get());
					output.accept(AnnoyingModItems.DIAMOND_LONG_BLADE.get());
					output.accept(AnnoyingModItems.DIAMOND_SHARP_BLADE.get());
					output.accept(AnnoyingModItems.GREAT_SWORD.get());
					output.accept(AnnoyingModItems.BROKEN_GREAT_SWORD.get());
					output.accept(AnnoyingModItems.DIAMOND_CLAW_KNIFE.get());
					output.accept(AnnoyingModItems.BLUE_FLAME_SWORD.get());
					output.accept(AnnoyingModItems.DIAMOND_ARMBLADES.get());
					output.accept(AnnoyingModItems.DIAMOND_LONG_KNIFE.get());
					output.accept(AnnoyingModItems.DIAMOND_HOOK_SWORD.get());
					output.accept(AnnoyingModItems.DIAMOND_SICKLE.get());
					output.accept(AnnoyingModItems.CHARGED_DIAMOND_SICKLE.get());
					output.accept(AnnoyingModItems.DIAMOND_BOLT.get());
					output.accept(AnnoyingModItems.TWIN_DIAMOND_SPEAR.get());
					output.accept(AnnoyingModItems.DIAMOND_SPEAR.get());
					output.accept(AnnoyingModItems.DIAMOND_LAEVATEINN.get());
					output.accept(AnnoyingModItems.DIAMOND_SCISSORS.get());
					output.accept(AnnoyingModItems.DOUBLE_DIAMOND_GLAIVE.get());
					output.accept(AnnoyingModItems.DIAMOND_FALCHION.get());
					output.accept(AnnoyingModItems.DIAMOND_RAPIER.get());
					output.accept(AnnoyingModItems.THUNDER_DIAMOND_TWIN_BLADED_BATTLEAXE.get());
					output.accept(AnnoyingModItems.THUNDER_DIAMOND_BATTLEAXE.get());
					output.accept(AnnoyingModItems.DIAMOND_GREATAXE.get());
					output.accept(AnnoyingModItems.DIAMOND_MACE.get());
					output.accept(AnnoyingModItems.GOLDEN_LONGSWORD.get());
					output.accept(AnnoyingModItems.GOLDEN_HOOK_SWORD.get());
					output.accept(AnnoyingModItems.PURPLE_DIAMOND_LONGSWORD.get());
					output.accept(AnnoyingModItems.FLAME_KNIFE.get());
					output.accept(AnnoyingModItems.CRAFTING_TABLE.get());
					output.accept(AnnoyingModItems.ENCHANTED_OAK_DOOR.get());
					output.accept(AnnoyingModItems.OAK_DOOR.get());
					output.accept(AnnoyingModItems.FISHING_ROD.get());
					output.accept(AnnoyingModItems.KNIFE.get());
					output.accept(AnnoyingModItems.KNIFE_WITH_TNT.get());
				}).build());

	}
