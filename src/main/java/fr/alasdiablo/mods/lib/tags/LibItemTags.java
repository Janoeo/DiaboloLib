package fr.alasdiablo.mods.lib.tags;

import fr.alasdiablo.mods.lib.Lib;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

public class LibItemTags {
    public static final TagKey<Item> FALL_DAMAGE_REDUCERS_BY_2 = create("fall_damage_reducers_by_2");
    public static final TagKey<Item> WALK_ON_POWDERED_SOWN = create("walk_on_powdered_sown");

    private static @NotNull TagKey<Item> create(String name) {
        return ItemTags.create(
                Identifier.fromNamespaceAndPath(Lib.MOD_ID, name)
        );
    }
}
