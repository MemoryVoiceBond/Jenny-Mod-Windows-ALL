package com.jenny.mod.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class JennyEntity extends TamableAnimal {

    private static final EntityDataAccessor<Integer> MOOD = SynchedEntityData.defineId(JennyEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> OUTFIT = SynchedEntityData.defineId(JennyEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DANCING = SynchedEntityData.defineId(JennyEntity.class, EntityDataSerializers.BOOLEAN);

    private int danceTicks = 0;

    public JennyEntity(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MOOD, 50);
        this.entityData.define(OUTFIT, 0);
        this.entityData.define(DANCING, false);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (player.getItemInHand(hand).is(Items.DIAMOND) && !isTame()) {
            tame(player);
            setMood(100);
            return InteractionResult.SUCCESS;
        }
        if (player.getItemInHand(hand).is(Items.GOLD_INGOT)) {
            setMood(Math.min(100, getMood() + 10));
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    public int getMood() { return entityData.get(MOOD); }
    public void setMood(int mood) { entityData.set(MOOD, mood); }
    public int getOutfit() { return entityData.get(OUTFIT); }
    public void setOutfit(int outfit) { entityData.set(OUTFIT, outfit % 5); }
    public boolean isDancing() { return entityData.get(DANCING); }
    public void setDancing(boolean dancing) { entityData.set(DANCING, dancing); }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Mood", getMood());
        tag.putInt("Outfit", getOutfit());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setMood(tag.getInt("Mood"));
        setOutfit(tag.getInt("Outfit"));
    }

    @Override
    public JennyEntity getBreedOffspring(net.minecraft.server.level.ServerLevel level, net.minecraft.world.entity.AgeableMob mob) {
        return null;
    }
}
