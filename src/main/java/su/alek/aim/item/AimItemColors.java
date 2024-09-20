package su.alek.aim.item;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import su.alek.aim.item.components.AimAllComponents;

import java.util.Objects;

import static su.alek.aim.item.AimAllItems.*;

public final class AimItemColors {
    @SubscribeEvent
    public static void regItemColors(RegisterColorHandlersEvent.Item event){
        event.register(
                (pStack, pTintIndex) -> pTintIndex == 0 ? Objects.requireNonNull(pStack.get(AimAllComponents.ITEM_COLOR)).color() : 0xffffff,
                UNIT_HYDROCHLORIC_ACID.get()
        );
    }
}
