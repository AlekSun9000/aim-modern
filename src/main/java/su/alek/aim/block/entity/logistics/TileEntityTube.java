package su.alek.aim.block.entity.logistics;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import su.alek.aim.block.AimAllBlocks;

public class TileEntityTube extends BlockEntity {
    public TileEntityTube(BlockPos pPos, BlockState pBlockState) {
        super(AimAllBlocks.LOGISTICS_E.get(), pPos, pBlockState);
    }
}
