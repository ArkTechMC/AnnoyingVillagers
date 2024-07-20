package com.iafenvoy.annoyingvillagers.mixin;

import com.iafenvoy.annoyingvillagers.AnnoyingVillagers;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.architectury.platform.Platform;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    @Shadow
    @Final
    private boolean doBackgroundFade;

    @Shadow
    private long backgroundFadeStart;

    @Unique
    private DrawContext context;

    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void onPreRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        this.context = context;
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/RotatingCubeMapRenderer;render(FF)V"))
    private void onRenderBackground(RotatingCubeMapRenderer instance, float delta, float alpha) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        this.context.drawTexture(new Identifier(AnnoyingVillagers.MOD_ID, "textures/gui/background.png"), 0, 0, 0, 0, this.width, this.height, this.width, this.height);

        float f = this.doBackgroundFade ? (float) (Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0F : 1.0F;
        float g = this.doBackgroundFade ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
        int i = MathHelper.ceil(g * 255.0F) << 24;
        if ((i & -67108864) != 0 && Platform.isFabric())
            this.context.drawText(this.textRenderer, "Annoying Villager", 2, this.height - 20, 0x00FFFFFF | i, false);
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/LogoDrawer;draw(Lnet/minecraft/client/gui/DrawContext;IF)V"))
    private void onDrawLogo(LogoDrawer instance, DrawContext context, int screenWidth, float alpha) {
        //Cancel this
    }
}
