package com.phantomwing.rusticdelight.util;

import com.phantomwing.rusticdelight.item.ModItems;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.level.storage.loot.providers.number.floats.ResolvableFloat;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;

public class FuelHelper {
    /** Neutral cooking speed: fuels burn at the normal rate. */
    private static final ResolvableFloat NORMAL_SPEED = new ResolvableFloat.Constant(1.0F);

    public static void registerFuelItems() {
        // 26.3 replaced the fuel registry with the COOKING_FUEL item component, so burn times are
        // applied by amending the items' default components instead of building a fuel map.
        DefaultItemComponentEvents.MODIFY.register(context ->
            context.modify(ModItems.COTTON_BOLL, builder -> builder.set(DataComponents.COOKING_FUEL,
                new CookingFuel(new ResolvableInt.Constant(100), NORMAL_SPEED)))
        );
    }
}
