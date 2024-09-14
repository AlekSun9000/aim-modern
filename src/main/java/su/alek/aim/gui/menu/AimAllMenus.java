package su.alek.aim.gui.menu;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;
import su.alek.aim.AimModMain;

import java.util.function.Supplier;

public final class AimAllMenus {
    ////////////////////////
    // DEFERRED REGISTERS //
    ////////////////////////
    public static final DeferredRegister<MenuType<?>> MENU = DeferredRegister.create(Registries.MENU, AimModMain.MODID);
    /////////////////////
    // CONTAINER MENUS //
    /////////////////////
    public static final Supplier<MenuType<MenuMachine44>> MACHINE44_MENU = MENU.register("machine44menu", () -> new MenuType<>(MenuMachine44::new,FeatureFlagSet.of()));
}
