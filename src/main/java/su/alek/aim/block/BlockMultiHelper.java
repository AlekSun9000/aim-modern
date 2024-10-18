package su.alek.aim.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.alek.aim.block.entity.TileEntityMultiHelper;

public class BlockMultiHelper extends Block implements EntityBlock {
    protected BlockMultiHelper(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.INVISIBLE;
    }

    @Override
    protected void spawnDestroyParticles(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState) {}


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TileEntityMultiHelper(pPos, pState);
    }

    @Override
    public void destroy(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
        BlockState state;
        if (!pLevel.isClientSide()){
            if (pLevel.getBlockEntity(pPos) instanceof TileEntityMultiHelper blockEntity1) {
                state = pLevel.getBlockState(blockEntity1.mainPos);
                pLevel.setBlock(blockEntity1.mainPos, Blocks.AIR.defaultBlockState(), 3);
                state.getBlock().destroy(pLevel, blockEntity1.mainPos, Blocks.AIR.defaultBlockState());
            }
        }
        super.destroy(pLevel, pPos, pState);
    }

    @Override
    protected int getLightBlock(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 0;
    }

    @Override
    protected void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        BlockState state;
        if (pLevel.getBlockEntity(pPos) instanceof TileEntityMultiHelper blockEntity1) {
            state = pLevel.getBlockState(blockEntity1.mainPos);
            pLevel.setBlock(blockEntity1.mainPos, Blocks.AIR.defaultBlockState(), 3);
            state.getBlock().destroy(pLevel, blockEntity1.mainPos, Blocks.AIR.defaultBlockState());
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull BlockHitResult pHitResult) {
        TileEntityMultiHelper blockEntity = (TileEntityMultiHelper)pLevel.getBlockEntity(pPos);
        BlockPos pos = null;
        if (blockEntity != null) {
            pos = blockEntity.mainPos;
        }
        BlockEntity mainBlockEntity = null;
        if (pos != null) {
            mainBlockEntity = pLevel.getBlockEntity(pos);
        }
        BlockState mainState = null;
        if (mainBlockEntity != null) {
            mainState = mainBlockEntity.getBlockState();
        }
        if (!pLevel.isClientSide && mainState != null){
            MenuProvider menuProvider = mainState.getMenuProvider(pLevel, pos);
            if (menuProvider != null)
                pPlayer.openMenu(menuProvider);
        }
        return InteractionResult.SUCCESS_NO_ITEM_USED;
    }

    @Override
    protected @NotNull VoxelShape getOcclusionShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos) {
        return Shapes.empty();
    }
}
