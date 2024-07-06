package com.iafenvoy.annoyingvillagers.item;

import com.iafenvoy.annoyingvillagers.client.model.GPCModel;
import com.iafenvoy.annoyingvillagers.registry.util.ArmorMaterialUtil;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

public abstract class GSCWPSSArmorItem extends ArmorItem {
    public GSCWPSSArmorItem(Type type, Item.Settings properties) {
        super(ArmorMaterialUtil.of("grave_paladin", new int[]{13, 15, 26, 11}, 46, new int[]{4, 7, 20, 4}, 35, Registries.SOUND_EVENT.get(new Identifier("item.armor.equip_diamond")), 7, 0.6f, () -> Blocks.EMERALD_BLOCK), type, properties);
    }

    public static class Chestplate extends GSCWPSSArmorItem {
        public Chestplate() {
            super(Type.CHESTPLATE, new Item.Settings());
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                @Override
                @OnlyIn(Dist.CLIENT)
                public BipedEntityModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, BipedEntityModel defaultModel) {
                    BipedEntityModel armorModel = new BipedEntityModel(new ModelPart(Collections.emptyList(), Map.of("body", new GPCModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(GPCModel.LAYER_LOCATION)).Body, "left_arm",
                            new GPCModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(GPCModel.LAYER_LOCATION)).LeftArm, "right_arm",
                            new GPCModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(GPCModel.LAYER_LOCATION)).RightArm, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
                            new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                    armorModel.sneaking = living.isSneaking();
                    armorModel.riding = defaultModel.riding;
                    armorModel.child = living.isBaby();
                    return armorModel;
                }
            });
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "annoying_villagers:textures/entities/grave_paladin_sword_chestplate.png";
        }
    }
}
