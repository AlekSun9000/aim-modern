package su.alek.aim.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import su.alek.aim.AimModMain;
import su.alek.aim.block.entity.EntityMachineMill;
import su.alek.aim.item.AimAllItems;

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
    ////////////////////
    // BLOCK ENTITIES //
    ////////////////////
    public static final Supplier<BlockEntityType<EntityMachineMill>> MILL_E = BLOCK_ENTITIES.register("mill_te", () -> BlockEntityType.Builder.of(EntityMachineMill::new, MILL.get()).build(null));
    /////////////////
    // BLOCK ITEMS //
    /////////////////
    // Creates a new BlockItem with the id "aim:example_block", combining the namespace and path
    public static final DeferredItem<BlockItem> MINECRAFT_END_PORTAL = AimAllItems.ITEMS.registerSimpleBlockItem("end_portal", () -> Blocks.END_PORTAL);
    public static final DeferredItem<BlockItem> MINECRAFT_PORTAL = AimAllItems.ITEMS.registerSimpleBlockItem("portal", () -> Blocks.NETHER_PORTAL);
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = AimAllItems.ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);
    public static final DeferredItem<BlockItem> MILL_ITEM = AimAllItems.ITEMS.registerSimpleBlockItem("mill",MILL);
}
