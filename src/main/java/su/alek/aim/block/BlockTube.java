package su.alek.aim.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class BlockTube extends Block {
    public BlockTube(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(PipeBlock.NORTH, false)
                        .setValue(PipeBlock.EAST, false)
                        .setValue(PipeBlock.SOUTH, false)
                        .setValue(PipeBlock.WEST, false)
                        .setValue(PipeBlock.UP,false)
                        .setValue(PipeBlock.DOWN, false)
        );
    }

    @Override
    protected int getLightBlock(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 0;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(PipeBlock.UP);
        pBuilder.add(PipeBlock.DOWN);
        pBuilder.add(PipeBlock.EAST);
        pBuilder.add(PipeBlock.SOUTH);
        pBuilder.add(PipeBlock.WEST);
        pBuilder.add(PipeBlock.NORTH);
    }
}
