package su.alek.aim.interfac;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public abstract class ServerTool {
    public static void message(Player player, Component text){
        if (player instanceof ServerPlayer)
            ((ServerPlayer)player).sendSystemMessage(text,true);
    }
}
