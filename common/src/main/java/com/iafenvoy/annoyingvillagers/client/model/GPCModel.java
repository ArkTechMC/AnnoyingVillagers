package com.iafenvoy.annoyingvillagers.client.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class GPCModel<T extends Entity> extends EntityModel<T> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier("annoying_villagers", "gpc"), "main");
    public final ModelPart Head;
    public final ModelPart RightArm;
    public final ModelPart LeftArm;
    public final ModelPart RightLeg;
    public final ModelPart LeftLeg;
    public final ModelPart Body;

    public GPCModel(ModelPart root) {
        this.Head = root.getChild("Head");
        this.RightArm = root.getChild("RightArm");
        this.LeftArm = root.getChild("LeftArm");
        this.RightLeg = root.getChild("RightLeg");
        this.LeftLeg = root.getChild("LeftLeg");
        this.Body = root.getChild("Body");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();
        ModelPartData Head = partdefinition.addChild("Head",
                ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(1.0F)).uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(1.5F)),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData RightArm = partdefinition.addChild("RightArm", ModelPartBuilder.create().uv(40, 16).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(1.0F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));
        ModelPartData LeftArm = partdefinition.addChild("LeftArm", ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(1.0F)).mirrored(false),
                ModelTransform.pivot(5.0F, 2.0F, 0.0F));
        ModelPartData RightLeg = partdefinition.addChild("RightLeg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(1.0F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));
        ModelPartData LeftLeg = partdefinition.addChild("LeftLeg", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(1.0F)).mirrored(false), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
        ModelPartData Body = partdefinition.addChild("Body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(1.01F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData bone3 = Body.addChild("bone3", ModelPartBuilder.create().uv(0, 55).cuboid(0.0F, -19.0F, 5.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)).uv(0, 55)
                .cuboid(-1.0F, -20.0F, 5.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)).uv(0, 55).cuboid(-2.0F, -19.0F, 5.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        ModelPartData bone = Body.addChild("bone",
                ModelPartBuilder.create().uv(24, 38).cuboid(-3.0F, -12.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-2.0F, -13.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-5.0F, -10.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-6.0F, -9.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-4.0F, -11.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-9.0F, -8.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-8.0F, -8.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-7.0F, -9.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-10.0F, -8.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-12.0F, -8.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-11.0F, -8.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-11.0F, -9.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-10.0F, -10.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-9.0F, -11.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 34)
                        .cuboid(-9.0F, -10.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 37).cuboid(-8.0F, -12.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-8.0F, -11.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-8.0F, -12.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 34)
                        .cuboid(-8.0F, -10.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-6.0F, -14.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-6.0F, -13.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-6.0F, -14.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 36)
                        .cuboid(-6.0F, -11.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 36).cuboid(-5.0F, -12.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 36)
                        .cuboid(-3.0F, -14.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 36).cuboid(-2.0F, -15.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 36)
                        .cuboid(-1.0F, -16.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 36).cuboid(0.0F, -17.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 36)
                        .cuboid(-4.0F, -13.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42).cuboid(-6.0F, -12.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42)
                        .cuboid(-5.0F, -13.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-4.0F, -15.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(-5.0F, -14.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42).cuboid(-4.0F, -14.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42)
                        .cuboid(-3.0F, -15.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42).cuboid(-2.0F, -16.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42)
                        .cuboid(-1.0F, -17.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42).cuboid(0.0F, -18.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-7.0F, -13.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-7.0F, -12.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42)
                        .cuboid(-7.0F, -11.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-3.0F, -16.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-1.0F, -18.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 34).cuboid(1.0F, -18.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-2.0F, -17.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(3.0F, -18.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(0.0F, -19.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(1.0F, -20.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(2.0F, -21.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(3.0F, -21.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(4.0F, -20.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(4.0F, -19.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42)
                        .cuboid(1.0F, -19.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42).cuboid(2.0F, -20.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 36)
                        .cuboid(2.0F, -19.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 36).cuboid(3.0F, -20.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40)
                        .cuboid(4.0F, -21.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40).cuboid(3.0F, -19.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40)
                        .cuboid(2.0F, -18.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40).cuboid(1.0F, -17.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40)
                        .cuboid(0.0F, -16.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40).cuboid(-1.0F, -15.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40)
                        .cuboid(-2.0F, -14.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40).cuboid(-3.0F, -13.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40)
                        .cuboid(-4.0F, -12.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40).cuboid(-5.0F, -11.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40)
                        .cuboid(-6.0F, -10.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40).cuboid(-7.0F, -10.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40)
                        .cuboid(-8.0F, -9.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40).cuboid(-9.0F, -9.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40)
                        .cuboid(-10.0F, -9.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(1.0F, -16.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(0.0F, -15.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(-1.0F, -14.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(2.0F, -17.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 44).cuboid(2.0F, -22.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 44)
                        .cuboid(5.0F, -19.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(1.0F, -22.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(5.0F, -18.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(6.0F, -18.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(0.0F, -22.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(1.0F, -23.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(2.0F, -23.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(3.0F, -22.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(4.0F, -22.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(5.0F, -21.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(6.0F, -19.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(5.0F, -17.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(5.0F, -20.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(7.0F, -25.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(8.0F, -25.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(8.0F, -26.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(8.0F, -27.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(9.0F, -27.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(10.0F, -27.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(10.0F, -26.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(10.0F, -25.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(9.0F, -25.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 34)
                        .cuboid(9.0F, -26.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(8.0F, -24.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(12, 32)
                        .cuboid(5.0F, -23.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(12, 32).cuboid(6.0F, -22.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(8, 32)
                        .cuboid(6.0F, -23.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(8, 32).cuboid(7.0F, -24.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(16, 32)
                        .cuboid(5.0F, -22.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(12, 32).cuboid(6.0F, -24.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(12, 32)
                        .cuboid(7.0F, -23.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(1.0F, 23.0F, 1.0F));
        ModelPartData bone2 = Body.addChild("bone2",
                ModelPartBuilder.create().uv(24, 38).cuboid(-3.0F, -12.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-2.0F, -13.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-5.0F, -10.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-6.0F, -9.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-4.0F, -11.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-9.0F, -8.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-8.0F, -8.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-7.0F, -9.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-10.0F, -8.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-12.0F, -8.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-11.0F, -8.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-11.0F, -9.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-10.0F, -10.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-9.0F, -11.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 34)
                        .cuboid(-9.0F, -10.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 37).cuboid(-8.0F, -12.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-8.0F, -11.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-8.0F, -12.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 34)
                        .cuboid(-8.0F, -10.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-6.0F, -14.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-6.0F, -13.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-6.0F, -14.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 36)
                        .cuboid(-6.0F, -11.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 36).cuboid(-5.0F, -12.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 36)
                        .cuboid(-3.0F, -14.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 36).cuboid(-2.0F, -15.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 36)
                        .cuboid(-1.0F, -16.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 36).cuboid(0.0F, -17.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 36)
                        .cuboid(-4.0F, -13.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42).cuboid(-6.0F, -12.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42)
                        .cuboid(-5.0F, -13.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-4.0F, -15.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(-5.0F, -14.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42).cuboid(-4.0F, -14.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42)
                        .cuboid(-3.0F, -15.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42).cuboid(-2.0F, -16.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42)
                        .cuboid(-1.0F, -17.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42).cuboid(0.0F, -18.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-7.0F, -13.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-7.0F, -12.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42)
                        .cuboid(-7.0F, -11.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38).cuboid(-3.0F, -16.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-1.0F, -18.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 34).cuboid(1.0F, -18.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 38)
                        .cuboid(-2.0F, -17.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(3.0F, -18.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(0.0F, -19.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(1.0F, -20.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(2.0F, -21.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(3.0F, -21.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(4.0F, -20.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(4.0F, -19.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42)
                        .cuboid(1.0F, -19.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 42).cuboid(2.0F, -20.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 36)
                        .cuboid(2.0F, -19.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 36).cuboid(3.0F, -20.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40)
                        .cuboid(4.0F, -21.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40).cuboid(3.0F, -19.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40)
                        .cuboid(2.0F, -18.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40).cuboid(1.0F, -17.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40)
                        .cuboid(0.0F, -16.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40).cuboid(-1.0F, -15.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40)
                        .cuboid(-2.0F, -14.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40).cuboid(-3.0F, -13.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40)
                        .cuboid(-4.0F, -12.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40).cuboid(-5.0F, -11.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40)
                        .cuboid(-6.0F, -10.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40).cuboid(-7.0F, -10.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40)
                        .cuboid(-8.0F, -9.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40).cuboid(-9.0F, -9.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 40)
                        .cuboid(-10.0F, -9.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(1.0F, -16.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(0.0F, -15.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(-1.0F, -14.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(2.0F, -17.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 44).cuboid(2.0F, -22.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 44)
                        .cuboid(5.0F, -19.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(1.0F, -22.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(5.0F, -18.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(6.0F, -18.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(0.0F, -22.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(1.0F, -23.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(2.0F, -23.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(3.0F, -22.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(4.0F, -22.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(5.0F, -21.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(6.0F, -19.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(5.0F, -17.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(5.0F, -20.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(7.0F, -25.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(8.0F, -25.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(8.0F, -26.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(8.0F, -27.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(9.0F, -27.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(10.0F, -27.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(10.0F, -26.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36)
                        .cuboid(10.0F, -25.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(9.0F, -25.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 34)
                        .cuboid(9.0F, -26.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 36).cuboid(8.0F, -24.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(12, 32)
                        .cuboid(5.0F, -23.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(12, 32).cuboid(6.0F, -22.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(8, 32)
                        .cuboid(6.0F, -23.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(8, 32).cuboid(7.0F, -24.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(16, 32)
                        .cuboid(5.0F, -22.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(12, 32).cuboid(6.0F, -24.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(12, 32)
                        .cuboid(7.0F, -23.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(-1.0F, 22.0F, 7.0F, 0.0F, 3.1416F, 0.0F));
        return TexturedModelData.of(meshdefinition, 64, 64);
    }

    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
