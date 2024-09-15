package su.alek.aim.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import su.alek.aim.block.AimAllBlocks;
import su.alek.aim.recipe.Recipe44;
import su.alek.aim.recipe.RecipeMaps;

import java.util.HashMap;
import java.util.HashSet;

public class EntityMachineMill extends EntityAbstractMachine44{
    public EntityMachineMill(BlockPos pPos, BlockState pBlockState) {
        super(AimAllBlocks.MILL_E.get(), pPos, pBlockState);
    }

    @Override
    public HashMap<HashSet<Item>, Recipe44> getRecipe() {
        return RecipeMaps.MILL_RECIPE;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("screen.aim.mill");
    }
}
