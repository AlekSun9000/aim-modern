package su.alek.aim.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EggItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.alek.aim.AimModMain;

@Mixin(EggItem.class)
public abstract class MixinEggItem extends Item implements ProjectileItem {
    public MixinEggItem(Properties pProperties) {
        super(pProperties);
    }
    @Inject(method = "use",at=@At("HEAD"))
    public void use(Level pLevel, Player pPlayer, InteractionHand pHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir){
        AimModMain.LOGGER.info("using egg");
    }
}
