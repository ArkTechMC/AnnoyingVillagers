package com.iafenvoy.annoyingvillagers.client.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class IKAModel<T extends Entity> extends EntityModel<T> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier("annoying_villagers", "ika"), "main");
    public final ModelPart Head;
    public final ModelPart Body;
    public final ModelPart RightArm;
    public final ModelPart LeftArm;
    public final ModelPart RightLeg;
    public final ModelPart LeftLeg;

    public IKAModel(ModelPart root) {
        this.Head = root.getChild("Head");
        this.Body = root.getChild("Body");
        this.RightArm = root.getChild("RightArm");
        this.LeftArm = root.getChild("LeftArm");
        this.RightLeg = root.getChild("RightLeg");
        this.LeftLeg = root.getChild("LeftLeg");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();
        ModelPartData Head = partdefinition.addChild("Head",
                ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(1.0F)).uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(1.5F)).uv(25, 0)
                        .cuboid(4.0F, -13.0F, 1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)).uv(26, 2).cuboid(4.0F, -12.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).uv(8, 17)
                        .cuboid(4.0F, -11.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData cube_r1 = Head.addChild("cube_r1",
                ModelPartBuilder.create().uv(13, 8).cuboid(4.0F, -10.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(13, 8).cuboid(4.0F, -10.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 2)
                        .cuboid(1.0F, -13.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 3).cuboid(0.0F, -12.0F, 4.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 9)
                        .cuboid(-3.0F, -10.0F, 4.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(15, 9).cuboid(-3.0F, -10.0F, -5.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(15, 10)
                        .cuboid(-1.0F, -11.0F, 4.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
        ModelPartData Body = partdefinition.addChild("Body",
                ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(1.2F)).uv(25, 6).cuboid(-1.0F, -1.0F, -4.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 6)
                        .cuboid(-1.0F, 0.0F, -4.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 6).cuboid(-1.0F, 1.0F, -4.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 6)
                        .cuboid(-1.0F, 4.0F, -4.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 6).cuboid(-1.0F, 3.0F, -4.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 6)
                        .cuboid(-1.0F, 8.0F, -4.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 6).cuboid(-1.0F, 7.0F, -4.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 6)
                        .cuboid(-1.0F, 10.0F, -4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 6).cuboid(-1.0F, 9.0F, -4.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 6)
                        .cuboid(-1.0F, 6.0F, -4.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 6).cuboid(-1.0F, 5.0F, -4.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 6)
                        .cuboid(-1.0F, 2.0F, -4.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData RightArm = partdefinition.addChild("RightArm",
                ModelPartBuilder.create().uv(40, 16).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(1.2F)).uv(25, 1).cuboid(-4.0F, -4.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.2F)).uv(27, 0)
                        .cuboid(-4.0F, -4.0F, 2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.2F)).uv(27, 2).cuboid(0.0F, -4.0F, 2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.2F)).uv(27, 2)
                        .cuboid(0.0F, -4.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.2F)),
                ModelTransform.pivot(-5.0F, 2.0F, 0.0F));
        ModelPartData LeftArm = partdefinition.addChild("LeftArm",
                ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(1.2F)).mirrored(false).uv(27, 0).cuboid(3.0F, -4.0F, 2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.2F))
                        .uv(25, 1).cuboid(3.0F, -4.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.2F)).uv(27, 2).cuboid(-1.0F, -4.0F, 2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.2F)).uv(25, 3)
                        .cuboid(-1.0F, -4.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.2F)),
                ModelTransform.pivot(5.0F, 2.0F, 0.0F));
        ModelPartData RightLeg = partdefinition.addChild("RightLeg", ModelPartBuilder.create().uv(48, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(1.0F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));
        ModelPartData LeftLeg = partdefinition.addChild("LeftLeg", ModelPartBuilder.create().uv(48, 16).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(1.0F)).mirrored(false),
                ModelTransform.pivot(1.9F, 12.0F, 0.0F));
        return TexturedModelData.of(meshdefinition, 64, 32);
    }

    @Override
    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
