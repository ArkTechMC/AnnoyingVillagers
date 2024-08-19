package com.iafenvoy.annoyingvillagers.client.renderer.armor;

import com.iafenvoy.annoyingvillagers.AnnoyingVillagers;
import com.iafenvoy.annoyingvillagers.client.model.IKAModel;
import com.iafenvoy.neptune.render.armor.IArmorRendererBase;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.Collections;
import java.util.Map;

public class IKArmorRenderer implements IArmorRendererBase {
    @Override
    public BipedEntityModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, BipedEntityModel defaultModel) {
        BipedEntityModel armorModel = null;
        if (slot == EquipmentSlot.HEAD)
            armorModel = new BipedEntityModel<>(new ModelPart(Collections.emptyList(), Map.of(
                    "head", new IKAModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(IKAModel.LAYER_LOCATION)).Head,
                    "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                    "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                    "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                    "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                    "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                    "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
        else if (slot == EquipmentSlot.CHEST)
            armorModel = new BipedEntityModel<>(new ModelPart(Collections.emptyList(), Map.of(
                    "body", new IKAModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(IKAModel.LAYER_LOCATION)).Body,
                    "left_arm", new IKAModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(IKAModel.LAYER_LOCATION)).LeftArm,
                    "right_arm", new IKAModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(IKAModel.LAYER_LOCATION)).RightArm,
                    "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                    "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                    "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                    "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
        if (armorModel != null) {
            armorModel.sneaking = living.isSneaking();
            armorModel.riding = defaultModel.riding;
            armorModel.child = living.isBaby();
        }
        return armorModel;
    }

    @Override
    public Identifier getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot) {
        return new Identifier(AnnoyingVillagers.MOD_ID, "textures/entities/illager_king_layer_1.png");
    }
}
