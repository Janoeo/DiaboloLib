package fr.alasdiablo.mods.lib.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

public record ArmorItemSet(
        DeferredItem<Item> helmet,
        DeferredItem<Item> chestplate,
        DeferredItem<Item> leggings,
        DeferredItem<Item> boots
) {

    public void addToCreativeTab(@NotNull CreativeModeTab.Output output) {
        output.accept(helmet.get());
        output.accept(chestplate.get());
        output.accept(leggings.get());
        output.accept(boots.get());
    }

    public void buildCreativeTabContentInsertAfter(@NotNull BuildCreativeModeTabContentsEvent event, ItemLike itemAfter) {
        event.insertAfter(
                new ItemStack(itemAfter),
                new ItemStack(this.boots.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.insertAfter(
                new ItemStack(itemAfter),
                new ItemStack(this.leggings.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.insertAfter(
                new ItemStack(itemAfter),
                new ItemStack(this.helmet.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.insertAfter(
                new ItemStack(itemAfter),
                new ItemStack(this.chestplate.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }
}
