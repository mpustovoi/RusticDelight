package com.phantomwing.rusticdelight.world;

import com.mojang.serialization.MapCodec;
import com.phantomwing.rusticdelight.RusticDelight;
import com.phantomwing.rusticdelight.world.modifiers.ConfigurableRarityFilter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

public class ModPlacementModifiers {
    // 26.3 removed PlacementModifierType; the registry now holds each modifier's MapCodec directly.
    public static final MapCodec<ConfigurableRarityFilter> CONFIGURABLE_RARITY_FILTER =
            registerModifier("configurable_rarity_filter", ConfigurableRarityFilter.CODEC);

    private static <T extends PlacementModifier> MapCodec<T> registerModifier(String name, MapCodec<T> codec) {
        Registry.register(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE,
                Identifier.fromNamespaceAndPath(RusticDelight.MOD_ID, name), codec);
        return codec;
    }

    public static void registerPlacementModfiiers() {
        RusticDelight.LOGGER.info("Registering placement modifiers for " + RusticDelight.MOD_ID);

    }
}
