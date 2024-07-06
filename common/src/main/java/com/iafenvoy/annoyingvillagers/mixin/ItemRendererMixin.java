package com.iafenvoy.annoyingvillagers.mixin;

import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Shadow
    @Final
    private MinecraftClient minecraft;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/ForgeHooksClient;handleCameraTransforms(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/resources/model/BakedModel;Lnet/minecraft/world/item/ItemDisplayContext;Z)Lnet/minecraft/client/resources/model/BakedModel;"))
    private void render(ItemStack itemStack, ModelTransformationMode mode, boolean p_115146_, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int p_115149_, int p_115150_, BakedModel p_115151_, CallbackInfo ci) {
        if (itemStack.isOf(AnnoyingModItems.DIAMOND_FRAGMENTS.get()) && mode == ModelTransformationMode.FIRST_PERSON_RIGHT_HAND) {
            matrixStack.scale(1F, 1F, 1F);
            matrixStack.translate(0F, 0F, 0F);
        }
    }
}
