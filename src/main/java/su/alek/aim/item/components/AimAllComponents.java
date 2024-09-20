package su.alek.aim.item.components;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;
import su.alek.aim.AimModMain;

import java.util.function.Supplier;

public final class AimAllComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, AimModMain.MODID);
    //////////////////////////
    // DATA COMPONENT TYPES //
    //////////////////////////
    public static final Supplier<DataComponentType<ItemColorRecord>> ITEM_COLOR = AimAllComponents.DATA_COMPONENTS.register("item_color", () -> DataComponentType.<ItemColorRecord>builder().persistent(ItemColorRecord.CODEC).networkSynchronized(ItemColorRecord.STREAM_CODEC).build());
}
