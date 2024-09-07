package su.alek.aim.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import su.alek.aim.block.entity.EntityAbstractMachine44;

public abstract class AbstractMachine44 extends BaseEntityBlock {
    protected AbstractMachine44(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    protected static <T extends BlockEntity> BlockEntityTicker<T> createBlockEntityTicker(Level pLevel, BlockEntityType<T> serverType, BlockEntityType<? extends EntityAbstractMachine44> clientType){
        return pLevel.isClientSide ? null : createTickerHelper(serverType, clientType, EntityAbstractMachine44::serverTick);
    }
}
