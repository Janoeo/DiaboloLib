
package fr.alasdiablo.mods.lib.data;

import fr.alasdiablo.mods.lib.item.ArmorItemSet;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract base class that extends {@link RecipeProvider} to provide utility methods for generating
 * common Minecraft recipes.
 * This class simplifies the creation of ore cooking recipes, storage block conversions, tool sets, and armour sets.
 *
 * <p>This provider is designed to be extended by mod-specific recipe providers, allowing for consistent
 * recipe generation across different mods whilst reducing boilerplate code.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * public class MyModRecipeProvider extends DioRecipieProvider {
 *     public MyModRecipeProvider(String modId, HolderLookup.Provider registries, RecipeOutput output) {
 *         super(modId, registries, output);
 *     }
 *
 *     @Override
 *     protected void buildRecipes() {
 *         oreCooking(MY_RAW_ORE, MY_INGOT, 0.7f, "my_ingots");
 *         nineStorage(MY_NUGGET, MY_INGOT);
 *     }
 * }
 * }</pre>
 */
@SuppressWarnings({"unused"})
public abstract class LibRecipeProvider extends RecipeProvider {
    private final String modId;

    /**
     * Constructs a new DioRecipieProvider with the specified mod ID, registries, and recipe output.
     *
     * @param modId      The mod ID used for generating resource locations for recipes
     * @param registries The holder lookup provider for accessing game registries
     * @param output     The recipe output where generated recipes will be saved
     */
    protected LibRecipeProvider(String modId, HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
        this.modId = modId;
    }

    /**
     * Generates both smelting and blasting recipes for ore processing.
     * This method creates two recipes:
     * one for furnace smelting (200 ticks) and one for blast furnace blasting (100 ticks),
     * both yielding the same result with the specified experience reward.
     *
     * <p>The generated recipes will be saved with resource locations following the pattern:</p>
     * <ul>
     *     <li>Smelting: {@code <modId>:<result>_from_smelting_<ingredient>}</li>
     *     <li>Blasting: {@code <modId>:<result>_from_blasting_<ingredient>}</li>
     * </ul>
     *
     * @param ingredient The ingredient item to be cooked (typically raw ore or similar)
     * @param result     The resulting item after cooking (typically ingot or similar)
     * @param experience The quantity of experience points awarded when the recipe is completed
     * @param group      The recipe group name for organising related recipes in the recipe book
     */
    protected void oreCooking(@NotNull ItemLike ingredient, @NotNull ItemLike result, float experience, String group) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.MISC, CookingBookCategory.BLOCKS, result, experience, 200)
                .group(group)
                .unlockedBy(getHasName(ingredient), this.has(ingredient))
                .save(
                        this.output,
                        Identifier.fromNamespaceAndPath(
                                this.modId,
                                getItemName(result) + "_from_smelting_" + getItemName(ingredient)).toString()
                );

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), RecipeCategory.MISC, CookingBookCategory.BLOCKS, result, experience, 100)
                .group(group)
                .unlockedBy(getHasName(ingredient), this.has(ingredient))
                .save(
                        this.output,
                        Identifier.fromNamespaceAndPath(
                                this.modId,
                                getItemName(result) + "_from_blasting_" + getItemName(ingredient)
                        ).toString()
                );
    }

    /**
     * Generates storage block conversion recipes following the standard 9:1 ratio.
     * This method creates two recipes:
     *   one for unpacking a storage block into 9 items, and one for packing 9 items into a storage block.
     *
     * <p>Common use cases include:</p>
     * <ul>
     *     <li>Nugget ↔ Ingot conversion</li>
     *     <li>Ingot ↔ Block conversion</li>
     * </ul>
     *
     * <p>The generated recipes will be saved with resource locations following the pattern:</p>
     * <ul>
     *     <li>Unpacking: {@code <modId>:<unpacked>_from_ingot}</li>
     *     <li>Packing: {@code <modId>:<packed>_from_nuggets}</li>
     * </ul>
     *
     * @param unpacked The item that is unpacked (e.g. nugget or ingot)
     * @param packed   The item that is packed (e.g. ingot or block)
     */
    protected void nineStorage(
            @NotNull ItemLike unpacked,
            @NotNull ItemLike packed
    ) {
        this.shapeless(RecipeCategory.MISC, unpacked, 9)
                .requires(packed)
                .unlockedBy(getHasName(packed), this.has(packed))
                .save(
                        this.output,
                        Identifier.fromNamespaceAndPath(
                                this.modId, getItemName(unpacked) + "_from_ingot"
                        ).toString()
                );

        this.shaped(RecipeCategory.MISC, packed)
                .define('#', unpacked)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(unpacked), this.has(unpacked))
                .save(
                        this.output,
                        Identifier.fromNamespaceAndPath(
                                this.modId, getItemName(packed) + "_from_nuggets"
                        ).toString()
                );
    }

    /**
     * Generates standard crafting recipes for a complete tool set using a material tag.
     * This method creates recipes for an axe, hoe, pickaxe,
     * shovel, and sword, all following Minecraft's standard tool crafting patterns.
     *
     * <p>All tools use wooden sticks as handles, and the recipes follow the standard patterns used in
     * vanilla Minecraft.
     * The recipes will be unlocked when the player gets any item from the specified material tag.</p>
     *
     * <p>Note: The hoe recipe uses {@link ItemTags#IRON_TOOL_MATERIALS} instead of the provided material tag,
     * which appears to be a potential oversight in the implementation.</p>
     *
     * @param axe      The axe item to be crafted
     * @param hoe      The hoe item to be crafted
     * @param pickaxe  The pickaxe item to be crafted
     * @param shovel   The shovel item to be crafted
     * @param sword    The sword item to be crafted
     * @param material The tag containing the crafting material(s) for the tools
     */
    protected void tools(
            @NotNull ItemLike axe, @NotNull ItemLike hoe, @NotNull ItemLike pickaxe, @NotNull ItemLike shovel, @NotNull ItemLike sword,
            @NotNull TagKey<Item> material
    ) {
        String hasName = "has_" + material.location().getPath().replace("/", "_");

        this.shaped(RecipeCategory.TOOLS, axe)
                .define('S', Items.STICK)
                .define('M', material)
                .pattern("MM")
                .pattern("MS")
                .pattern(" S")
                .unlockedBy(hasName, this.has(material))
                .save(this.output);
        this.shaped(RecipeCategory.TOOLS, hoe)
                .define('S', Items.STICK)
                .define('M', material)
                .pattern("MM")
                .pattern(" S")
                .pattern(" S")
                .unlockedBy(hasName, this.has(material))
                .save(this.output);
        this.shaped(RecipeCategory.TOOLS, pickaxe)
                .define('S', Items.STICK)
                .define('M', material)
                .pattern("MMM")
                .pattern(" S ")
                .pattern(" S ")
                .unlockedBy(hasName, this.has(material))
                .save(this.output);
        this.shaped(RecipeCategory.TOOLS, shovel)
                .define('S', Items.STICK)
                .define('M', material)
                .pattern("M")
                .pattern("S")
                .pattern("S")
                .unlockedBy(hasName, this.has(material))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, sword)
                .define('S', Items.STICK)
                .define('M', material)
                .pattern("M")
                .pattern("M")
                .pattern("S")
                .unlockedBy(hasName, this.has(material))
                .save(this.output);
    }

    /**
     * Generates standard crafting recipes for a complete tool set using a specific material item.
     * This method creates recipes for an axe, hoe, pickaxe,
     * shovel, and sword, all following Minecraft's standard tool crafting patterns.
     *
     * <p>All tools use wooden sticks as handles, and the recipes follow the standard patterns used in
     * vanilla Minecraft.
     * The recipes will be unlocked when the player gets the specified material item.</p>
     *
     * <p>Note: The hoe recipe uses {@link ItemTags#IRON_TOOL_MATERIALS} instead of the provided material item,
     * which appears to be a potential oversight in the implementation.</p>
     *
     * @param axe      The axe item to be crafted
     * @param hoe      The hoe item to be crafted
     * @param pickaxe  The pickaxe item to be crafted
     * @param shovel   The shovel item to be crafted
     * @param sword    The sword item to be crafted
     * @param material The specific item used as crafting material for the tools
     */
    protected void tools(
            @NotNull ItemLike axe, @NotNull ItemLike hoe, @NotNull ItemLike pickaxe, @NotNull ItemLike shovel, @NotNull ItemLike sword,
            @NotNull Item material
    ) {
        this.shaped(RecipeCategory.TOOLS, axe)
                .define('S', Items.STICK)
                .define('M', material)
                .pattern("MM")
                .pattern("MS")
                .pattern(" S")
                .unlockedBy(getHasName(material), this.has(material))
                .save(this.output);
        this.shaped(RecipeCategory.TOOLS, hoe)
                .define('S', Items.STICK)
                .define('M', material)
                .pattern("MM")
                .pattern(" S")
                .pattern(" S")
                .unlockedBy(getHasName(material), this.has(material))
                .save(this.output);
        this.shaped(RecipeCategory.TOOLS, pickaxe)
                .define('S', Items.STICK)
                .define('M', material)
                .pattern("MMM")
                .pattern(" S ")
                .pattern(" S ")
                .unlockedBy(getHasName(material), this.has(material))
                .save(this.output);
        this.shaped(RecipeCategory.TOOLS, shovel)
                .define('S', Items.STICK)
                .define('M', material)
                .pattern("M")
                .pattern("S")
                .pattern("S")
                .unlockedBy(getHasName(material), this.has(material))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, sword)
                .define('S', Items.STICK)
                .define('M', material)
                .pattern("M")
                .pattern("M")
                .pattern("S")
                .unlockedBy(getHasName(material), this.has(material))
                .save(this.output);
    }

    /**
     * Generates standard crafting recipes for a complete armour set using a material tag.
     * This method creates recipes for a helmet, chestplate,
     * leggings, and boots, all following Minecraft's standard armour crafting patterns.
     *
     * <p>The recipes follow the standard patterns used in vanilla Minecraft for armour pieces.
     * All recipes will be unlocked when the player gets any item from the specified material tag.</p>
     *
     * @param helmet     The helmet item to be crafted
     * @param chestplate The chestplate item to be crafted
     * @param leggings   The leggings item to be crafted
     * @param boots      The boots item to be crafted
     * @param material   The tag containing the crafting material(s) for the armour pieces
     */
    protected void armors(
            @NotNull ItemLike helmet, @NotNull ItemLike chestplate, @NotNull ItemLike leggings, @NotNull ItemLike boots,
            @NotNull TagKey<Item> material
    ) {
        String hasName = "has_" + material.location().getPath().replace("/", "_");

        this.shaped(RecipeCategory.COMBAT, helmet)
                .define('M', material)
                .pattern("MMM")
                .pattern("M M")
                .unlockedBy(hasName, this.has(material))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, chestplate)
                .define('M', material)
                .pattern("M M")
                .pattern("MMM")
                .pattern("MMM")
                .unlockedBy(hasName, this.has(material))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, leggings)
                .define('M', material)
                .pattern("MMM")
                .pattern("M M")
                .pattern("M M")
                .unlockedBy(hasName, this.has(material))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, boots)
                .define('M', material)
                .pattern("M M")
                .pattern("M M")
                .unlockedBy(hasName, this.has(material))
                .save(this.output);
    }

    /**
     * Generates standard crafting recipes for a complete armour set using a specific material item.
     * This method creates recipes for a helmet, chestplate,
     * leggings, and boots, all following Minecraft's standard armour crafting patterns.
     *
     * <p>The recipes follow the standard patterns used in vanilla Minecraft for armour pieces.
     * All recipes will be unlocked when the player gets the specified material item.</p>
     *
     * @param helmet     The helmet item to be crafted
     * @param chestplate The chestplate item to be crafted
     * @param leggings   The leggings item to be crafted
     * @param boots      The boots item to be crafted
     * @param material   The specific item used as crafting material for the armour pieces
     */
    protected void armors(
            @NotNull ItemLike helmet, @NotNull ItemLike chestplate, @NotNull ItemLike leggings, @NotNull ItemLike boots,
            @NotNull Item material
    ) {
        this.shaped(RecipeCategory.COMBAT, helmet)
                .define('M', material)
                .pattern("MMM")
                .pattern("M M")
                .unlockedBy(getHasName(material), this.has(material))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, chestplate)
                .define('M', material)
                .pattern("M M")
                .pattern("MMM")
                .pattern("MMM")
                .unlockedBy(getHasName(material), this.has(material))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, leggings)
                .define('M', material)
                .pattern("MMM")
                .pattern("M M")
                .pattern("M M")
                .unlockedBy(getHasName(material), this.has(material))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, boots)
                .define('M', material)
                .pattern("M M")
                .pattern("M M")
                .unlockedBy(getHasName(material), this.has(material))
                .save(this.output);
    }

    protected void armors(@NotNull ArmorItemSet armor, @NotNull TagKey<Item> material) {
        ItemLike helmet = armor.helmet();
        ItemLike chestplate = armor.chestplate();
        ItemLike leggings = armor.leggings();
        ItemLike boots = armor.boots();

        this.armors(helmet, chestplate, leggings, boots, material);
    }

    protected void armors(@NotNull ArmorItemSet armor, @NotNull Item material) {
        ItemLike helmet = armor.helmet();
        ItemLike chestplate = armor.chestplate();
        ItemLike leggings = armor.leggings();
        ItemLike boots = armor.boots();

        this.armors(helmet, chestplate, leggings, boots, material);
    }
}
