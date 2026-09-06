package fr.alasdiablo.mods.lib.item;

import fr.alasdiablo.mods.lib.tags.LibItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public class ArmorItemWalkableOnPowderedSnow extends Item {
    public ArmorItemWalkableOnPowderedSnow(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canWalkOnPowderedSnow(@NonNull ItemStack stack, @NonNull LivingEntity wearer) {
        return stack.is(LibItemTags.WALK_ON_POWDERED_SOWN) || super.canWalkOnPowderedSnow(stack, wearer);
    }
}
