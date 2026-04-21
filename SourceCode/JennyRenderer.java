package com.jenny.mod.client;

import com.jenny.mod.JennyMod;
import com.jenny.mod.entity.JennyEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class JennyRenderer extends MobRenderer<JennyEntity, JennyModel<JennyEntity>> {

    private static final ResourceLocation[] TEXTURES = {
            new ResourceLocation(JennyMod.MOD_ID, "textures/entity/jenny_default.png"),
            new ResourceLocation(JennyMod.MOD_ID, "textures/entity/jenny_casual.png"),
            new ResourceLocation(JennyMod.MOD_ID, "textures/entity/jenny_formal.png"),
            new ResourceLocation(JennyMod.MOD_ID, "textures/entity/jenny_summer.png"),
            new ResourceLocation(JennyMod.MOD_ID, "textures/entity/jenny_winter.png")
    };

    public JennyRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new JennyModel<>(ctx.bakeLayer(JennyModel.LAYER)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(JennyEntity entity) {
        int outfit = entity.getOutfit();
        return (outfit >= 0 && outfit < TEXTURES.length) ? TEXTURES[outfit] : TEXTURES[0];
    }
}
