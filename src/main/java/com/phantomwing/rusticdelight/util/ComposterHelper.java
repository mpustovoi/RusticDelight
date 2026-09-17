package com.phantomwing.rusticdelight.util;

import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.component.Compostable;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class ComposterHelper {
    /**
     * 26.3 replaced {@code ComposterBlock.COMPOSTABLES} with the COMPOSTABLE item component, which
     * carries a layer provider rather than a raw chance. Vanilla's named providers are binomial
     * "one layer at N%" rolls, so they line up 1:1 with the chances used before: LOW is 30%,
     * LOW_MEDIUM 50%, MEDIUM 65%, MEDIUM_HIGH 85% and ALWAYS_ADD_ONE 100%.
     */
    private static void registerCompostableItems(DefaultItemComponentEvents.ModifyContext context,
                                                 ResourceKey<ContextIntProvider> layers, ItemLike... items) {
        Compostable compostable = new Compostable(layers);
        for (ItemLike item : items) {
            context.modify(item.asItem(), builder -> builder.set(DataComponents.COMPOSTABLE, compostable));
        }
    }

    public static void registerCompostableItems() {
        DefaultItemComponentEvents.MODIFY.register(context -> {
            // 30% chance
            registerCompostableItems(context, ContextIntProviders.COMPOSTABLE_LOW,
                ModItems.COTTON_SEEDS,
                ModItems.BELL_PEPPER_SEEDS,
                ModItems.PALE_BELL_PEPPER_SEEDS,
                ModItems.DARK_BELL_PEPPER_SEEDS
            );

            // 50% chance
            registerCompostableItems(context, ContextIntProviders.COMPOSTABLE_LOW_MEDIUM,
                ModItems.COTTON_BOLL,
                ModItems.POTATO_SLICES,
                ModItems.COFFEE_BEANS,
                ModItems.BELL_PEPPER_SLICE_GREEN,
                ModItems.BELL_PEPPER_SLICE_YELLOW,
                ModItems.BELL_PEPPER_SLICE_RED,
                ModItems.BELL_PEPPER_SLICE_ORANGE,
                ModItems.BELL_PEPPER_SLICE_WHITE,
                ModItems.BELL_PEPPER_SLICE_PINK,
                ModItems.BELL_PEPPER_SLICE_BLUE,
                ModItems.BELL_PEPPER_SLICE_PURPLE,
                ModItems.BELL_PEPPER_SLICE_BLACK
            );

            // 65% chance
            registerCompostableItems(context, ContextIntProviders.COMPOSTABLE_MEDIUM,
                ModItems.BELL_PEPPER_GREEN,
                ModItems.BELL_PEPPER_YELLOW,
                ModItems.BELL_PEPPER_RED,
                ModItems.BELL_PEPPER_ORANGE,
                ModItems.BELL_PEPPER_WHITE,
                ModItems.BELL_PEPPER_PINK,
                ModItems.BELL_PEPPER_BLUE,
                ModItems.BELL_PEPPER_PURPLE,
                ModItems.BELL_PEPPER_BLACK,
                ModItems.BELL_PEPPER_GREEN_BLOCK,
                ModItems.BELL_PEPPER_YELLOW_BLOCK,
                ModItems.BELL_PEPPER_RED_BLOCK,
                ModItems.BELL_PEPPER_ORANGE_BLOCK,
                ModItems.BELL_PEPPER_WHITE_BLOCK,
                ModItems.BELL_PEPPER_PINK_BLOCK,
                ModItems.BELL_PEPPER_BLUE_BLOCK,
                ModItems.BELL_PEPPER_PURPLE_BLOCK,
                ModItems.BELL_PEPPER_BLACK_BLOCK,
                ModItems.WILD_COFFEE,
                ModItems.WILD_COTTON,
                ModItems.WILD_BELL_PEPPERS,
                ModItems.WILD_PALE_BELL_PEPPERS,
                ModItems.WILD_DARK_BELL_PEPPERS,
                ModItems.ROASTED_COFFEE_BEANS
            );

            // 85% chance
            registerCompostableItems(context, ContextIntProviders.COMPOSTABLE_MEDIUM_HIGH,
                ModItems.COFFEE_COOKIE,
                ModItems.SYRUP_COOKIE,
                ModItems.CHERRY_BLOSSOM_COOKIE,
                ModItems.SYRUP_CHEESECAKE_SLICE,
                ModItems.CHERRY_BLOSSOM_CHEESECAKE_SLICE,
                ModItems.COFFEE_CHEESECAKE_SLICE
            );

            // 100% chance
            registerCompostableItems(context, ContextIntProviders.COMPOSTABLE_ALWAYS_ADD_ONE,
                ModItems.SYRUP_CHEESECAKE,
                ModItems.CHERRY_BLOSSOM_CHEESECAKE,
                ModItems.COFFEE_CHEESECAKE
            );
        });
    }
}
