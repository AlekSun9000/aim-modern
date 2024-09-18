package su.alek.aim.interfac;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.Item;

public record ItemCountAndComponent(Item item, DataComponentMap components, int count) {
}
