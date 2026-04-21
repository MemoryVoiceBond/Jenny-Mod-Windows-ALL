package com.jenny.mod.entity.ai;

import com.jenny.mod.entity.JennyEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import java.util.EnumSet;

public class JennyFollowGoal extends Goal {

    private final JennyEntity jenny;
    private LivingEntity owner;
    private final double speed;
    private final float minDist;
    private final float maxDist;
    private int recalcPath;

    public JennyFollowGoal(JennyEntity jenny, double speed, float minDist, float maxDist) {
        this.jenny = jenny;
        this.speed = speed;
        this.minDist = minDist;
        this.maxDist = maxDist;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity liv = jenny.getOwner();
        if (liv == null || !jenny.isTame()) return false;
        if (jenny.distanceToSqr(liv) < minDist * minDist) return false;
        if (jenny.isDancing()) return false;
        owner = liv;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return !jenny.getNavigation().isDone() && jenny.distanceToSqr(owner) > maxDist * maxDist;
    }

    @Override
    public void start() {
        recalcPath = 0;
    }

    @Override
    public void stop() {
        owner = null;
        jenny.getNavigation().stop();
    }

    @Override
    public void tick() {
        jenny.getLookControl().setLookAt(owner, 10.0F, jenny.getMaxHeadXRot());
        if (--recalcPath <= 0) {
            recalcPath = 10;
            if (jenny.distanceToSqr(owner) >= 144.0D) {
                jenny.moveTo(owner.getX(), owner.getY(), owner.getZ());
            } else {
                jenny.getNavigation().moveTo(owner, speed);
            }
        }
    }
}
