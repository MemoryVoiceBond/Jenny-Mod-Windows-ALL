package com.jenny.mod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, JennyMod.MOD_ID);

    public static final RegistryObject<SoundEvent> JENNY_AMBIENT = registerSound("entity.jenny.ambient");
    public static final RegistryObject<SoundEvent> JENNY_HURT = registerSound("entity.jenny.hurt");
    public static final RegistryObject<SoundEvent> JENNY_HAPPY = registerSound("entity.jenny.happy");
    public static final RegistryObject<SoundEvent> JENNY_DANCE = registerSound("entity.jenny.dance");

    private static RegistryObject<SoundEvent> registerSound(String name) {
        ResourceLocation loc = new ResourceLocation(JennyMod.MOD_ID, name);
        return SOUNDS.register(name.replace(".", "_"), () -> SoundEvent.createVariableRangeEvent(loc));
    }
}
