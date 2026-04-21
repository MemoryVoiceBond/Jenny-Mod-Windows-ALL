package com.jenny.mod;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, JennyMod.MOD_ID);

    public static final RegistryObject<Item> JENNY_SPAWN_EGG = ITEMS.register("jenny_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.JENNY, 0xFF69B4, 0xFFB6C1,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> HEART_CRYSTAL = ITEMS.register("heart_crystal",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(16)));

    public static final RegistryObject<Item> LOVE_LETTER = ITEMS.register("love_letter",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));

    public static final RegistryObject<Item> ROSE_BOUQUET = ITEMS.register("rose_bouquet",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));
}
