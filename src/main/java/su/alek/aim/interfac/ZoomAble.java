package su.alek.aim.interfac;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;

public interface ZoomAble {
    InteractionResult onZoom(UseOnContext pContext);
}
