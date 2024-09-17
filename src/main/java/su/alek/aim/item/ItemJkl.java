package su.alek.aim.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ItemJkl extends Item {
    public ItemJkl(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel() instanceof ServerLevel level){
            Vec3i[] vec3is = new Vec3i[25];
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++){
                    vec3is[(i+2)*5 + j+2] = new Vec3i(i,0,j);
                }
            }
            BlockPos pos[] = new BlockPos[25];
            for (int i = 0; i < 25; i++) {
                pos[i] = pContext.getClickedPos().offset(vec3is[i]);
                BlockState state = level.getBlockState(pos[i]);
                for (int j = 0; j < 10; j++) {
                    state.randomTick(level, pos[i], RandomSource.create());
                }
            }
            Player player = pContext.getPlayer();
            if (player instanceof ServerPlayer serverPlayer){
                serverPlayer.sendSystemMessage(Component.literal("JinKeLa uses successfully"));
            }
        }
        return super.useOn(pContext);
    }
}
