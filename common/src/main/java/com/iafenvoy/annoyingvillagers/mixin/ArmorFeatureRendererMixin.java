package com.iafenvoy.annoyingvillagers.mixin;

import com.iafenvoy.annoyingvillagers.client.renderer.IArmorRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> extends FeatureRenderer<T, M> {
    public ArmorFeatureRendererMixin(FeatureRendererContext<T, M> context) {
        super(context);
    }

    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    private void onRenderArmor(MatrixStack matrices, VertexConsumerProvider vertexConsumers, T entity, EquipmentSlot armorSlot, int light, A model, CallbackInfo ci) {
        ItemStack stack = entity.getEquippedStack(armorSlot);
        IArmorRenderer<T> renderer = (IArmorRenderer<T>) IArmorRenderer.RENDERERS.get(stack.getItem());
        if (renderer != null) {
            BipedEntityModel<T> armorModel=renderer.getHumanoidArmorModel(entity, stack, armorSlot, this.getContextModel());
            this.getContextModel().copyBipedStateTo(armorModel);
            VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(renderer.getArmorTexture(stack, entity, armorSlot)));
            armorModel.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
            ci.cancel();
        }
    }
}
