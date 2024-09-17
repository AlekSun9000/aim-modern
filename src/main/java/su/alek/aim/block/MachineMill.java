package su.alek.aim.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.alek.aim.block.entity.EntityMachineMill;

public class MachineMill extends AbstractMachine44 {
    protected MachineMill(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Vec3i[] getHelpers(Direction direction) {
        return new Vec3i[0];
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new EntityMachineMill(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level pLevel, @NotNull BlockState pState, @NotNull BlockEntityType<T> pBlockEntityType) {
        return createBlockEntityTicker(pLevel, pBlockEntityType, AimAllBlocks.MILL_E.get());
    }
}
