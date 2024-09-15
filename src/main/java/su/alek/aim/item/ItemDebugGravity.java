package su.alek.aim.item;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForgeMod;
import org.jetbrains.annotations.NotNull;
import su.alek.aim.AimModMain;
import su.alek.aim.interfac.KeyHandler;
import su.alek.aim.interfac.ServerTool;

import java.util.List;

public class ItemDebugGravity extends Item {
    private final ResourceLocation GRAVITY_CONTROL = ResourceLocation.fromNamespaceAndPath(AimModMain.MODID,"gravity_control");
    public ItemDebugGravity(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public void appendHoverText(@NotNull ItemStack pStack, Item.@NotNull TooltipContext pContext, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack,pContext,pTooltipComponents,pTooltipFlag);
        if (KeyHandler.keyPressed(340)){
            pTooltipComponents.add(Component.translatable("aim.debug_creative_flight_description"));
        }else {
            pTooltipComponents.add(Component.translatable("aim.hold_shift"));
        }
    }
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        super.use(pLevel,pPlayer,pUsedHand);
        AttributeInstance ai = pPlayer.getAttribute(NeoForgeMod.CREATIVE_FLIGHT);
        if (ai != null && ai.hasModifier(GRAVITY_CONTROL)) {
            ai.removeModifier(GRAVITY_CONTROL);
            ServerTool.message(pPlayer,Component.translatable("aim.creative_flight_off"));
            return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
        }else if (ai != null){
            ai.addPermanentModifier(new AttributeModifier(GRAVITY_CONTROL,1.0,AttributeModifier.Operation.ADD_VALUE));
            ServerTool.message(pPlayer,Component.translatable("aim.creative_flight_on"));
            return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
        }
        return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
    }
}
