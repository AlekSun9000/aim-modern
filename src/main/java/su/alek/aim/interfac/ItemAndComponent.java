package su.alek.aim.interfac;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.Item;

public record ItemAndComponent(Item item, DataComponentMap components) {
}
