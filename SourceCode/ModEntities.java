package com.jenny.mod;

import com.jenny.mod.entity.JennyEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, JennyMod.MOD_ID);

    public static final RegistryObject<EntityType<JennyEntity>> JENNY = ENTITIES.register("jenny",
            () -> EntityType.Builder.of(JennyEntity::new, MobCategory.CREATURE)
                    .sized(0.6F, 1.8F)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(JennyMod.MOD_ID, "jenny").toString()));
}
