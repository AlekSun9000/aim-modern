package su.alek.aim.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import su.alek.aim.AimModMain;

public class ItemScaleRay extends Item {
    private final ResourceLocation SCALE_RAY_REG = ResourceLocation.fromNamespaceAndPath(AimModMain.MODID,"scale_ray");
    public ItemScaleRay(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand){
        AttributeInstance ai = pInteractionTarget.getAttribute(Attributes.SCALE);
        if (ai.hasModifier(SCALE_RAY_REG)) {
            ai.removeModifier(SCALE_RAY_REG);
        }else {
            ai.addPermanentModifier(new AttributeModifier(SCALE_RAY_REG,-0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        }
        return InteractionResult.SUCCESS;
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand){
        AttributeInstance ai = pPlayer.getAttribute(Attributes.SCALE);
        if (ai != null && ai.hasModifier(SCALE_RAY_REG)) {
            ai.removeModifier(SCALE_RAY_REG);
            return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
        }else if (ai != null){
            ai.addPermanentModifier(new AttributeModifier(SCALE_RAY_REG,-0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
            return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
        }
        return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
    }
}
