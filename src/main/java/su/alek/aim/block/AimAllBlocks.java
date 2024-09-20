package su.alek.aim.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import su.alek.aim.AimModMain;
import su.alek.aim.block.entity.*;

import static su.alek.aim.item.AimAllItems.ITEMS;

import java.util.List;
import java.util.function.Supplier;

public final class AimAllBlocks {
    ////////////////////////
    // DEFERRED REGISTERS //
    ////////////////////////
    // Create a Deferred Register to hold Blocks which will all be registered under the "aim" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AimModMain.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE,AimModMain.MODID);
    ////////////
    // BLOCKS //
    ////////////
    // Creates a new Block with the id "aim:example_block", combining the namespace and path
    public static final DeferredBlock<Block> EXAMPLE_BLOCK = AimAllBlocks.BLOCKS.registerSimpleBlock("example_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<MachineMill> MILL = BLOCKS.register("mill",() -> new MachineMill(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<MachineChem> CHEM = BLOCKS.register("chem", () -> new MachineChem(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> COLOR_STEEL_BLOCK = BLOCKS.registerSimpleBlock("color_steel_block", BlockBehaviour.Properties.of());
    public static final DeferredBlock<Block> ALUMINIUM_BLOCK = BLOCKS.registerSimpleBlock("aluminium_block", BlockBehaviour.Properties.of());
    public static final DeferredBlock<BlockMultiHelper> HELPER_BLOCK = BLOCKS.register("multi_helper", () -> new BlockMultiHelper(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<BlockItemSorter> SORTER = BLOCKS.register("sorter", () -> new BlockItemSorter(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<BlockTube> TEST_TUBE = BLOCKS.register("test_tube", () -> new BlockTube(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<InfBoxBlock> INF_BOX = BLOCKS.register("inf_box", ()->new InfBoxBlock(BlockBehaviour.Properties.of()));
    ////////////////////
    // BLOCK ENTITIES //
    ////////////////////
    public static final Supplier<BlockEntityType<EntityMachineMill>> MILL_E = BLOCK_ENTITIES.register("mill_te", () -> BlockEntityType.Builder.of(EntityMachineMill::new, MILL.get()).build(null));
    public static final Supplier<BlockEntityType<EntityMachineChem>> CHEM_E = BLOCK_ENTITIES.register("chem_te", () -> BlockEntityType.Builder.of(EntityMachineChem::new, CHEM.get()).build(null));
    public static final Supplier<BlockEntityType<TileEntityMultiHelper>> HELPER_E = BLOCK_ENTITIES.register("helper_te", () -> BlockEntityType.Builder.of(TileEntityMultiHelper::new, HELPER_BLOCK.get()).build(null));
    public static final Supplier<BlockEntityType<TileEntityItemSorter>> SORTER_E = BLOCK_ENTITIES.register("sorter_te", () -> BlockEntityType.Builder.of(TileEntityItemSorter::new, SORTER.get()).build(null));
    public static final Supplier<BlockEntityType<TileEntityInfBox>> INF_BOX_E = BLOCK_ENTITIES.register("inf_box", () -> BlockEntityType.Builder.of(TileEntityInfBox::new, INF_BOX.get()).build(null));
    /////////////////
    // BLOCK ITEMS //
    /////////////////
    // Creates a new BlockItem with the id "aim:example_block", combining the namespace and path
    public static final DeferredItem<BlockItem> MINECRAFT_END_PORTAL = ITEMS.registerSimpleBlockItem("end_portal", () -> Blocks.END_PORTAL);
    public static final DeferredItem<BlockItem> MINECRAFT_PORTAL = ITEMS.registerSimpleBlockItem("portal", () -> Blocks.NETHER_PORTAL);
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(EXAMPLE_BLOCK);
    public static final DeferredItem<BlockItem> MILL_ITEM = ITEMS.registerItem("mill", properties -> new BlockItem(MILL.get(), properties){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @NotNull TooltipContext pContext, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pTooltipFlag) {
            pTooltipComponents.add(Component.translatable("block.aim.mill.info"));
        }
    });
    public static final DeferredItem<BlockItem> CHEM_ITEM = ITEMS.registerSimpleBlockItem(CHEM);
    public static final DeferredItem<BlockItem> C_STEEL_ITEM = ITEMS.registerSimpleBlockItem(COLOR_STEEL_BLOCK);
    public static final DeferredItem<BlockItem> ALUMINIUM_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(ALUMINIUM_BLOCK);
    public static final DeferredItem<BlockItem> SORTER_ITEM = ITEMS.registerSimpleBlockItem(SORTER);
    public static final DeferredItem<BlockItem> TEST_TUBE_ITEM = ITEMS.registerSimpleBlockItem(TEST_TUBE);
    public static final DeferredItem<BlockItem> INF_BOX_ITEM = ITEMS.registerSimpleBlockItem(INF_BOX);
}
