package su.alek.aim.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class MixinPlayer {
    @Inject(method = "die",at=@At("HEAD"), cancellable = true)
    public void die(DamageSource pCause, CallbackInfo ci){
        ci.cancel();
    }
}
