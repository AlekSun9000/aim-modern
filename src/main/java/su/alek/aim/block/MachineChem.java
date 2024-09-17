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
import su.alek.aim.block.entity.EntityMachineChem;

public class MachineChem extends AbstractMachine44{
    protected MachineChem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Vec3i[] getHelpers(Direction direction) {
        return switch (direction) {
            case EAST -> new Vec3i[]{
                    new Vec3i(-1, 0, 0),
                    new Vec3i(0, 0, 1),
                    new Vec3i(-1, 0, 1),
                    new Vec3i(0, 1, 0),
                    new Vec3i(-1, 1, 0),
                    new Vec3i(0, 1, 1),
                    new Vec3i(-1, 1, 1)
            };
            case SOUTH -> new Vec3i[]{
                    new Vec3i(-1, 0, 0),
                    new Vec3i(0, 0, -1),
                    new Vec3i(-1, 0, -1),
                    new Vec3i(0, 1, 0),
                    new Vec3i(-1, 1, 0),
                    new Vec3i(0, 1, -1),
                    new Vec3i(-1, 1, -1)
            };
            case WEST -> new Vec3i[]{
                    new Vec3i(1, 0, 0),
                    new Vec3i(0, 0, -1),
                    new Vec3i(1, 0, -1),
                    new Vec3i(0, 1, 0),
                    new Vec3i(1, 1, 0),
                    new Vec3i(0, 1, -1),
                    new Vec3i(1, 1, -1)
            };
            case NORTH -> new Vec3i[]{
                    new Vec3i(1, 0, 0),
                    new Vec3i(0, 0, 1),
                    new Vec3i(1, 0, 1),
                    new Vec3i(0, 1, 0),
                    new Vec3i(1, 1, 0),
                    new Vec3i(0, 1, 1),
                    new Vec3i(1, 1, 1)

            };
            default -> new Vec3i[7];
        };
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new EntityMachineChem(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level pLevel, @NotNull BlockState pState, @NotNull BlockEntityType<T> pBlockEntityType) {
        return createBlockEntityTicker(pLevel, pBlockEntityType, AimAllBlocks.CHEM_E.get());
    }
}
