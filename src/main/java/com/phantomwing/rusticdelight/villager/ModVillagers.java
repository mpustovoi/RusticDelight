package com.phantomwing.rusticdelight.villager;

import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.VillagerFood;
import net.minecraft.world.item.Item;

import java.util.List;

/**
 * Runtime villager hooks. Trade registration is now data-driven (see
 * {@code com.phantomwing.rusticdelight.datagen.ModVillagerTrades}); this class only handles the
 * villager nutrition values that let farmers count, share and breed on our crops.
 */
public class ModVillagers {
    public static void registerFoodsAndTrades() {
        registerFoodPoints();
    }

    /**
     * Gives Rustic Delight's edible crops a villager nutrition value so farmer villagers count,
     * share and breed on them like vanilla crops. 26.3 replaced the global
     * {@code Villager.FOOD_POINTS} map with the per-item VILLAGER_FOOD component, so there is no
     * shared map left to clobber. Which items farmers actually pick up is a separate concern,
     * driven by the {@code minecraft:villager_picks_up} tag (see {@code ModItemTagsProvider}).
     */
    private static void registerFoodPoints() {
        List<Item> nutritious = List.of(
                ModItems.BELL_PEPPER_GREEN,
                ModItems.BELL_PEPPER_YELLOW,
                ModItems.BELL_PEPPER_RED,
                ModItems.BELL_PEPPER_ORANGE,
                ModItems.BELL_PEPPER_WHITE,
                ModItems.BELL_PEPPER_PINK,
                ModItems.BELL_PEPPER_BLUE,
                ModItems.BELL_PEPPER_PURPLE,
                ModItems.BELL_PEPPER_BLACK,
                // Cotton and coffee aren't truly food, but counting them (value 1) lets farmer villagers
                // reliably offload them to a partner so they work in automatic farms. The minor realism
                // cost (villagers eating / breeding on them) is unnoticeable in normal play.
                ModItems.COTTON_BOLL,
                ModItems.COFFEE_BEANS
        );

        VillagerFood oneFoodPoint = new VillagerFood(1);
        DefaultItemComponentEvents.MODIFY.register(context ->
            context.modify(nutritious, (builder, item) -> builder.set(DataComponents.VILLAGER_FOOD, oneFoodPoint))
        );
    }
}
