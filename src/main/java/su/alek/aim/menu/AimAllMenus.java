package su.alek.aim.menu;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;
import su.alek.aim.AimModMain;

public final class AimAllMenus {
    ////////////////////////
    // DEFERRED REGISTERS //
    ////////////////////////
    public static final DeferredRegister<MenuType<?>> MENU = DeferredRegister.create(Registries.MENU, AimModMain.MODID);
}
