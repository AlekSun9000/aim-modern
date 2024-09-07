package su.alek.aim.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import su.alek.aim.AimModMain;
import su.alek.aim.block.AimAllBlocks;

import java.util.function.Supplier;

public final class AimAllItems {
    ////////////////////////
    // DEFERRED REGISTERS //
    ////////////////////////
    // Create a Deferred Register to hold Items which will all be registered under the "aim" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AimModMain.MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "aim" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AimModMain.MODID);
    /////////////////////
    // NON-BLOCK ITEMS //
    /////////////////////
    // Creates a new food item with the id "aim:example_item", nutrition 1 and saturation 2
    public static final DeferredItem<Item> EXAMPLE_ITEM = AimAllItems.ITEMS.registerItem(
            "example_item",
            ItemTest::new,
            new Item.Properties().food(
                    new FoodProperties.Builder().alwaysEdible().nutrition(1).saturationModifier(2f).build()
            )
    );
    public static final Supplier<Item> SCALE_RAY = ITEMS.registerItem(
            "scale_ray",
            ItemScaleRay::new,
            new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)
    );
    public static final Supplier<Item> DEBUG_GRAVITY_CONTROLLER = ITEMS.registerItem(
            "debug_gravity_controller",
            ItemDebugGravity::new,
            new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)
    );
    ///////////////////
    // CREATIVE TABS //
    ///////////////////
    // Creates a creative tab with the id "aim:common" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = AimAllItems.CREATIVE_MODE_TABS.register("common", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.aim.common")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(new ItemStack(EXAMPLE_ITEM.get())); // Add the example item to the tab. For your own tabs, this method is preferred over the event
                output.accept(SCALE_RAY.get());
                output.accept(DEBUG_GRAVITY_CONTROLLER.get());
                output.accept(AimAllBlocks.MILL_ITEM);
            }).build());
}
