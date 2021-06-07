package fr.alasdiablo.diolib.data;

import net.minecraft.advancements.Advancement;
import net.minecraft.data.AdvancementProvider;
import net.minecraft.data.DataGenerator;

import java.util.List;
import java.util.function.Consumer;

public class DioAdvancementProvider extends AdvancementProvider {

    public DioAdvancementProvider(DataGenerator generatorIn, List<Consumer<Consumer<Advancement>>> advancements) {
        super(generatorIn);
        this.advancements = advancements;
    }
}