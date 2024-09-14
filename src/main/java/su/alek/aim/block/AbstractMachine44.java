package su.alek.aim.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import su.alek.aim.block.entity.EntityAbstractMachine44;

public abstract class AbstractMachine44 extends BaseEntityBlock {
    protected AbstractMachine44(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        //return simpleCodec(AbstractMachine44::new);
        return null;
    }

    protected static <T extends BlockEntity> BlockEntityTicker<T> createBlockEntityTicker(Level pLevel, BlockEntityType<T> serverType, BlockEntityType<? extends EntityAbstractMachine44> clientType){
        return pLevel.isClientSide ? null : createTickerHelper(serverType, clientType, EntityAbstractMachine44::serverTick);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        if (!pLevel.isClientSide){
            MenuProvider menuProvider = pState.getMenuProvider(pLevel, pPos);
            if (menuProvider != null)
                pPlayer.openMenu(menuProvider);
        }
        return InteractionResult.SUCCESS_NO_ITEM_USED;
    }
}
