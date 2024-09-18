package su.alek.aim.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import su.alek.aim.block.AimAllBlocks;

public class TileEntityInfBox extends BlockEntity {
    public ItemStack stack = ItemStack.EMPTY;
    public TileEntityInfBox(BlockPos pPos, BlockState pBlockState) {
        super(AimAllBlocks.INF_BOX_E.get(), pPos, pBlockState);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        NonNullList<ItemStack> stacks = NonNullList.withSize(1, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(pTag, stacks, pRegistries);
        this.stack = stacks.getFirst();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        ContainerHelper.saveAllItems(pTag, NonNullList.of(stack),pRegistries);
    }
}
