package com.jenny.mod.client;

import com.jenny.mod.JennyMod;
import com.jenny.mod.entity.JennyEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class JennyModel<T extends JennyEntity> extends HumanoidModel<T> {

    public static final ModelLayerLocation LAYER =
            new ModelLayerLocation(new ResourceLocation(JennyMod.MOD_ID, "jenny"), "main");

    public JennyModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
        part.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F), PartPose.ZERO);

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch);

        if (entity.isDancing()) {
            this.body.yRot = Mth.sin(ageInTicks * 0.5F) * 0.3F;
            this.rightArm.xRot = Mth.sin(ageInTicks * 0.5F) * 0.8F;
            this.leftArm.xRot = Mth.sin(ageInTicks * 0.5F + Mth.PI) * 0.8F;
        }
    }
}
