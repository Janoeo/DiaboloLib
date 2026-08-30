package fr.alasdiablo.mods.lib.item;

import fr.alasdiablo.mods.lib.DioLib;
import fr.alasdiablo.mods.lib.utils.DateRange;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class GroundCreativeModeTab {

    @Contract("_ -> param1")
    public static CreativeModeTab.@NotNull Builder createBaseBuilder(CreativeModeTab.@NotNull Builder builder) {
        if (DateRange.IS_WINTER) {
            builder.backgroundTexture(
                    Identifier.fromNamespaceAndPath(DioLib.MOD_ID, "textures/gui/container/creative_inventory/ground_winter.png")
            );
            return builder;
        }

        if (DateRange.IS_APRIL_FIRST) {
            builder.backgroundTexture(
                    Identifier.fromNamespaceAndPath(DioLib.MOD_ID, "textures/gui/container/creative_inventory/ground_april.png")
            );
            return builder;
        }

        builder.backgroundTexture(
                Identifier.fromNamespaceAndPath(DioLib.MOD_ID, "textures/gui/container/creative_inventory/ground.png")
        );
        return builder;
    }
}
