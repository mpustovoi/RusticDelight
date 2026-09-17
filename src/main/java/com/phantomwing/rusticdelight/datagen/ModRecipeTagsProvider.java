package com.phantomwing.rusticdelight.datagen;

import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * Tags over our own recipes, so advancements can point at a recipe by name.
 *
 * <p>26.3's {@code recipe_crafted} trigger takes a {@code HolderSet<Recipe>}, and the advancement
 * provider's registry lookup does not contain recipes that {@link ModRecipeProvider} is writing in a
 * parallel pass — a direct reference fails validation. A tag is resolved when the datapack loads
 * instead, so the advancement names this tag and this provider fills it.
 *
 * <p>Entries are added with {@code required: false}: a recipe whose {@code config_boolean} condition
 * fails is simply absent, and the tag entry is then skipped rather than erroring.
 */
public class ModRecipeTagsProvider extends FabricTagsProvider<Recipe<?>> {
    public ModRecipeTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.RECIPE, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        builder(ModTags.Recipes.STRING_FROM_COTTON).addOptional(recipe("string_from_cotton_boll"));
    }

    private static ResourceKey<Recipe<?>> recipe(String path) {
        return ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, path));
    }
}
