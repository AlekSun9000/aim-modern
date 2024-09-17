package su.alek.aim.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.alek.aim.block.entity.EntityAbstractMachine44;
import su.alek.aim.block.entity.TileEntityMultiHelper;

public abstract class AbstractMachine44 extends BaseEntityBlock {
    public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
    protected AbstractMachine44(Properties pProperties) {
        super(pProperties);
        registerDefaultState(stateDefinition.any()
                .setValue(HORIZONTAL_FACING, Direction.NORTH)
        );
    }

    public abstract Vec3i[] getHelpers(Direction direction);

    @Override
    protected @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
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
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull BlockHitResult pHitResult) {
        if (!pLevel.isClientSide){
            MenuProvider menuProvider = pState.getMenuProvider(pLevel, pPos);
            if (menuProvider != null)
                pPlayer.openMenu(menuProvider);
        }
        return InteractionResult.SUCCESS_NO_ITEM_USED;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(HORIZONTAL_FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        Vec3i[] helperOffsets;
        if (pState.getBlock() instanceof AbstractMachine44 block){
            helperOffsets = block.getHelpers(pState.getValue(HORIZONTAL_FACING));
            for (Vec3i helperOffset : helperOffsets) {
                BlockPos pos = pPos.offset(helperOffset);
                pLevel.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                pLevel.destroyBlock(pos, false);
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    protected void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        Vec3i[] helperOffsets;
        if (pState.getBlock() instanceof AbstractMachine44 block){
            helperOffsets = block.getHelpers(pState.getValue(HORIZONTAL_FACING));
            for (Vec3i helperOffset : helperOffsets){
                BlockPos pos = pPos.offset(helperOffset);
                pLevel.setBlock(pos, AimAllBlocks.HELPER_BLOCK.get().defaultBlockState(), 3);
                TileEntityMultiHelper blockEntity = (TileEntityMultiHelper)(pLevel.getBlockEntity(pos));
                blockEntity.setMainPos(pPos);
                pLevel.setBlockEntity(blockEntity);
            }
        }
        super.onPlace(pState, pLevel, pPos, pOldState, pMovedByPiston);
    }
}
