package fr.alasdiablo.mods.lib.utils;

import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;

public class FallDamageReduction {
    public static int calculateFallDamage(MobEffectInstance jumpEffect, double distance, double damageMultiplier) {
        double heightBeforeTakingDamage = 3d;
        double actualFallingDistance = distance - heightBeforeTakingDamage;
        if (jumpEffect != null) {
            double jumpHeight = jumpEffect.getAmplifier() + 1d;
            return Mth.ceil((actualFallingDistance - jumpHeight) * damageMultiplier);
        }
        return Mth.ceil(actualFallingDistance * damageMultiplier);
    }
}
