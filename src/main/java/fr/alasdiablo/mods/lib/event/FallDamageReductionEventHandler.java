package fr.alasdiablo.mods.lib.event;

import fr.alasdiablo.mods.lib.tags.LibItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import org.jspecify.annotations.NonNull;

public class FallDamageReductionEventHandler {
    public static void onLivingFall(@NonNull LivingFallEvent event) {
        LivingEntity livingEntity = event.getEntity();
        ItemStack itemStack = livingEntity.getItemBySlot(EquipmentSlot.FEET);
        Item item = itemStack.getItem();

        if (itemStack.is(LibItemTags.FALL_DAMAGE_REDUCERS_BY_2)) {
            event.setDamageMultiplier(0.5f);
            MobEffectInstance jumpEffect = livingEntity.getEffect(MobEffects.JUMP_BOOST);
            if (calculateFallDamage(jumpEffect, event.getDistance(), event.getDamageMultiplier()) >= 1) {
                item.setDamage(itemStack, item.getDamage(itemStack) + 1);
            }
        }
    }

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
