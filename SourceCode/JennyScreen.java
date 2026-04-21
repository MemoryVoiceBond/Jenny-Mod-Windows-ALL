package com.jenny.mod.client.gui;

import com.jenny.mod.JennyMod;
import com.jenny.mod.entity.JennyEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class JennyScreen extends Screen {

    private static final ResourceLocation BG =
            new ResourceLocation(JennyMod.MOD_ID, "textures/gui/jenny_gui.png");

    private final JennyEntity jenny;
    private int guiLeft, guiTop;

    public JennyScreen(JennyEntity jenny) {
        super(Component.literal("Jenny"));
        this.jenny = jenny;
    }

    @Override
    protected void init() {
        guiLeft = (width - 176) / 2;
        guiTop = (height - 166) / 2;

        addRenderableWidget(Button.builder(Component.literal("Pet"), b -> pet())
                .bounds(guiLeft + 10, guiTop + 30, 60, 20).build());
        addRenderableWidget(Button.builder(Component.literal("Dance"), b -> dance())
                .bounds(guiLeft + 10, guiTop + 55, 60, 20).build());
        addRenderableWidget(Button.builder(Component.literal("Outfit"), b -> changeOutfit())
                .bounds(guiLeft + 10, guiTop + 80, 60, 20).build());
    }

    private void pet() {
        jenny.setMood(Math.min(100, jenny.getMood() + 5));
    }

    private void dance() {
        jenny.setDancing(!jenny.isDancing());
    }

    private void changeOutfit() {
        jenny.setOutfit(jenny.getOutfit() + 1);
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float delta) {
        renderBackground(stack);
        drawString(stack, font, "Mood: " + jenny.getMood() + "%", guiLeft + 90, guiTop + 35, 0xFFFFFF);
        drawString(stack, font, "Outfit: " + jenny.getOutfit(), guiLeft + 90, guiTop + 55, 0xFFFFFF);
        super.render(stack, mouseX, mouseY, delta);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
