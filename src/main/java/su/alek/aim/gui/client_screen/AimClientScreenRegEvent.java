package su.alek.aim.gui.client_screen;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import su.alek.aim.gui.menu.AimAllMenus;

public class AimClientScreenRegEvent {
    public static void regScreens(RegisterMenuScreensEvent event){
        event.register(AimAllMenus.MACHINE44_MENU.get(), ScreenMachine44::new);
    }
}
