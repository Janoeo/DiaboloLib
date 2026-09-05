package fr.alasdiablo.mods.lib.utils;

import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;

public class FallDamageReduction {
    public static int calculateFallDamage(MobEffectInstance jumpEffect, float distance, float damageMultiplier) {
        float heightBeforeTakingDamage = 3f;
        float actualFallingDistance = distance - heightBeforeTakingDamage;
        if (jumpEffect != null) {
            float jumpHeight = jumpEffect.getAmplifier() + 1f;
            return Mth.ceil((actualFallingDistance - jumpHeight) * damageMultiplier);
        }
        return Mth.ceil(actualFallingDistance * damageMultiplier);
    }
}
