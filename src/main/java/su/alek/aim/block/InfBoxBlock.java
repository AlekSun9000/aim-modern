package su.alek.aim.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import su.alek.aim.block.entity.TileEntityInfBox;
import su.alek.aim.interfac.ServerTool;
import su.alek.aim.item.AimAllItems;

public class InfBoxBlock extends BaseEntityBlock {
    protected InfBoxBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TileEntityInfBox(pPos, pState);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if (pPlayer.getItemInHand(InteractionHand.OFF_HAND).equals(AimAllItems.EXAMPLE_ITEM)){
            if (pLevel.getBlockEntity(pPos) instanceof TileEntityInfBox blockEntity){
                blockEntity.stack = pPlayer.getItemInHand(InteractionHand.MAIN_HAND).copy();
                ServerTool.message(pPlayer, Component.literal("纪念品供货箱：已设置 " + blockEntity.stack));
                return ItemInteractionResult.SUCCESS;
            }
        }else {
            if (pLevel.getBlockEntity(pPos) instanceof TileEntityInfBox blockEntity){
                pPlayer.getInventory().add(blockEntity.stack.copy());
                return ItemInteractionResult.SUCCESS;
            }
        }
        return super.useItemOn(pStack, pState, pLevel, pPos, pPlayer, pHand, pHitResult);
    }
}
