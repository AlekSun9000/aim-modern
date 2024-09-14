package su.alek.aim.mixin;

import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.alek.aim.AimModMain;

import javax.annotation.Nullable;
import java.util.function.Predicate;

@Mixin(PlayerList.class)
public abstract class MixinPlayerList {
    @Inject(method = "broadcastChatMessage(Lnet/minecraft/network/chat/PlayerChatMessage;Ljava/util/function/Predicate;Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/network/chat/ChatType$Bound;)V", at = @At("HEAD"))
    public void broadcastChatMessage(PlayerChatMessage pMessage, Predicate<ServerPlayer> pShouldFilterMessageTo, @Nullable ServerPlayer pSender, ChatType.Bound pBoundChatType, CallbackInfo ci){
        AimModMain.LOGGER.debug("say");
    }
}
