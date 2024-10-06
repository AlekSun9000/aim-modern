package su.alek.aim.block.entity.logistics;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import su.alek.aim.block.AimAllBlocks;

public class TileEntityOperator extends BlockEntity {
    public TileEntityOperator(BlockPos pPos, BlockState pBlockState) {
        super(AimAllBlocks.OPERATOR_E.get(), pPos, pBlockState);
    }
}
