package fr.alasdiablo.mods.lib.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

public record ArmorItem(
        DeferredItem<Item> helmet,
        DeferredItem<Item> chestplate,
        DeferredItem<Item> leggings,
        DeferredItem<Item> boots
) {

    public void displayItemsGenerator(@NotNull CreativeModeTab.Output output) {
        output.accept(helmet.get());
        output.accept(chestplate.get());
        output.accept(leggings.get());
        output.accept(boots.get());
    }
}
