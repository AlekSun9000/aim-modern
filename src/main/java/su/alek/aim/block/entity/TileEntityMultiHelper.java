package su.alek.aim.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import su.alek.aim.block.AimAllBlocks;

public class TileEntityMultiHelper extends BlockEntity {
    public BlockPos mainPos = new BlockPos(0,0,0);
    public TileEntityMultiHelper(BlockPos pPos, BlockState pBlockState) {
        super(AimAllBlocks.HELPER_E.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.putInt("mainPosX", mainPos.getX());
        pTag.putInt("mainPosY", mainPos.getY());
        pTag.putInt("mainPosZ", mainPos.getZ());
        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        int x = pTag.getInt("mainPosX");
        int y = pTag.getInt("mainPosY");
        int z = pTag.getInt("mainPosZ");
        this.mainPos = new BlockPos(x, y, z);
        super.loadAdditional(pTag, pRegistries);
    }

    public void setMainPos(BlockPos mainPos) {
        this.mainPos = mainPos;
    }
}
