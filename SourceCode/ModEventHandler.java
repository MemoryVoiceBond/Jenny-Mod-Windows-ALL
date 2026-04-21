package com.jenny.mod.event;

import com.jenny.mod.JennyMod;
import com.jenny.mod.ModEntities;
import com.jenny.mod.client.JennyModel;
import com.jenny.mod.client.JennyRenderer;
import com.jenny.mod.entity.JennyEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JennyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventHandler {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.JENNY.get(), JennyEntity.createAttributes().build());
        JennyMod.LOGGER.info("Jenny attributes registered");
    }

    @Mod.EventBusSubscriber(modid = JennyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEvents {

        @SubscribeEvent
        public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(JennyModel.LAYER, JennyModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ModEntities.JENNY.get(), JennyRenderer::new);
        }
    }
}
