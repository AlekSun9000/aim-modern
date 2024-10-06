package su.alek.aim.item;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.alek.aim.AimModMain;
import su.alek.aim.block.AimAllBlocks;
import su.alek.aim.item.components.AimAllComponents;
import su.alek.aim.item.components.ItemColorRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

public final class AimAllItems {
    ////////////////////////
    // DEFERRED REGISTERS //
    ////////////////////////
    // Create a Deferred Register to hold Items which will all be registered under the "aim" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AimModMain.MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "aim" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AimModMain.MODID);
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, AimModMain.MODID);
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
    public static final Supplier<Item> ENERGY_CONNECTOR = ITEMS.registerItem(
            "energy_connector",
            ItemConnector::new,
            new Item.Properties().stacksTo(1)
    );
    /////////////
    // 工业原料 //
    ////////////
    public static final Supplier<Item> UNIT_HYDROCHLORIC_ACID = ITEMS.registerSimpleItem("unit_hydrochloric_acid");
    //public static final Supplier<Item> UNIT_HYDROCHLORIC_ACID = ITEMS.registerSimpleItem("unit_hydrochloric_acid", new Item.Properties().component(AimAllComponents.ITEM_COLOR, new ItemColorRecord(0x999999)));
    public static final Supplier<Item> UNIT_SULFURIC_ACID = ITEMS.registerSimpleItem("unit_sulfuric_acid");
    public static final Supplier<Item> UNIT_DILUTED_SULFURIC_ACID = ITEMS.registerSimpleItem("unit_diluted_sulfuric_acid");
    public static final Supplier<Item> UNIT_NITRIC_ACID = ITEMS.registerSimpleItem("unit_nitric_acid");
    public static final Supplier<Item> UNIT_CAUSTIC_ALKALI = ITEMS.registerSimpleItem("unit_caustic_alkali");
    public static final Supplier<Item> CAUSTIC_ALKALI = ITEMS.registerSimpleItem("caustic_alkali");
    public static final Supplier<Item> UNIT_HYDROGEN = ITEMS.registerSimpleItem("unit_hydrogen");
    public static final Supplier<Item> UNIT_OXYGEN = ITEMS.registerSimpleItem("unit_oxygen");
    public static final Supplier<Item> UNIT_CHLORINE = ITEMS.registerSimpleItem("unit_chlorine");
    public static final Supplier<Item> UNIT_NITROGEN = ITEMS.registerSimpleItem("unit_nitrogen");
    public static final Supplier<Item> UNIT_CARBON_DIOXIDE = ITEMS.registerSimpleItem("unit_carbon_dioxide");
    public static final Supplier<Item> UNIT_AIR = ITEMS.registerSimpleItem("unit_air");
    public static final Supplier<Item> UNIT_WATER = ITEMS.registerSimpleItem("unit_water");
    public static final Supplier<Item> UNIT_ROCK_SALT_WATER = ITEMS.registerSimpleItem("unit_rock_salt_water");
    public static final Supplier<Item> ROCK_SALT = ITEMS.registerSimpleItem("rock_salt");
    public static final Supplier<Item> BRIQUETTE = ITEMS.registerItem("honeycomb_briquette", (properties) -> new Item(properties){
        @Override
        public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
            return 6400;
        }

        @Override
        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
            pTooltipComponents.add(Component.translatable("item.aim.honeycomb_briquette.info"));
        }
    });
    //////////////
    // 铝工业产线 //
    //////////////
    public static final Supplier<Item> CRUSHED_BAUXITE = ITEMS.registerSimpleItem("crushed_bauxite");
    public static final Supplier<Item> BAUXITE_DUST = ITEMS.registerSimpleItem("bauxite_dust");
    public static final Supplier<Item> BAUXITE_CONCENTRATE_DUST = ITEMS.registerSimpleItem("bauxite_concentrate_dust");
    public static final Supplier<Item> UNIT_SODIUM_ALUMINATE_MUD = ITEMS.registerSimpleItem("unit_sodium_aluminate_mud");
    public static final Supplier<Item> UNIT_DILUTED_SODIUM_ALUMINATE_MUD = ITEMS.registerSimpleItem("unit_diluted_sodium_aluminate_mud");
    public static final Supplier<Item> UNIT_RAW_SODIUM_ALUMINATE_SOLUTION = ITEMS.registerSimpleItem("unit_raw_sodium_aluminate_solution");
    public static final Supplier<Item> UNIT_SODIUM_ALUMINATE_SOLUTION = ITEMS.registerSimpleItem("unit_sodium_aluminate_solution");
    public static final Supplier<Item> UNIT_ALUMINIUM_HYDROXIDE_SOLUTION = ITEMS.registerSimpleItem("unit_aluminium_hydroxide_solution");
    public static final Supplier<Item> UNIT_BAUXITE_RESIDUE = ITEMS.registerSimpleItem("unit_bauxite_residue");// 赤泥 Red Mud
    public static final Supplier<Item> ALUMINIUM_HYDROXIDE = ITEMS.registerSimpleItem("aluminium_hydroxide");
    public static final Supplier<Item> ALUMINIUM_OXIDE = ITEMS.registerSimpleItem("aluminium_oxide");
    public static final Supplier<Item> ALUMINIUM_CHLORIDE = ITEMS.registerSimpleItem("aluminium_chloride");// 无水
    public static final Supplier<Item> ALUMINIUM_CHLORIDE_CRYSTAL = ITEMS.registerSimpleItem("aluminium_chloride_crystal");// 水合
    public static final Supplier<Item> ALUMINIUM_DUST = ITEMS.registerSimpleItem("aluminium_dust");
    public static final Supplier<Item> ALUMINIUM_INGOT = ITEMS.registerSimpleItem("aluminium_ingot");
    public static final Supplier<Item> ELECTROLYTIC_ALUMINUM_INGOT = ITEMS.registerSimpleItem("electrolytic_aluminium_ingot");
    /////////
    // JKL //
    /////////
    public static final Supplier<Item> JKL_CHLORINE = ITEMS.registerItem("jkl_chlorine", ItemJkl::new);
    public static final Supplier<Item> APATITE = ITEMS.registerSimpleItem("apatite");
    public static final Supplier<Item> UNIT_NITROGEN_MONOXIDE = ITEMS.registerSimpleItem("unit_nitrogen_monoxide");
    public static final Supplier<Item> ZEOLITE = ITEMS.registerSimpleItem("zeolite");
    public static final Supplier<Item> UNIT_CALCIUM_CHLORIDE = ITEMS.registerSimpleItem("unit_calcium_chloride");
    public static final Supplier<Item> UNIT_PHOSPHORIC_ACID = ITEMS.registerSimpleItem("unit_phosphoric_acid");
    // equipments
    public static HashMap<ArmorItem.Type,Integer> epic_defense = new HashMap<>();
    static {
        epic_defense.put(ArmorItem.Type.CHESTPLATE, 24);
    }
    public static final Supplier<ArmorMaterial> EPIC_ARMOR_MATERIAL = ARMOR_MATERIALS.register("epic", (resourceLocation -> new ArmorMaterial(
            epic_defense,
            0,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            ()->Ingredient.of(Items.AIR),
            new ArrayList<>(),
            1978823.0f,
            1.0f
    )));
    public static final Supplier<ArmorItem> EPIC_WEAPON = ITEMS.registerItem("epic_weapon", (properties -> new ArmorItem(Holder.direct(EPIC_ARMOR_MATERIAL.get()), ArmorItem.Type.CHESTPLATE, properties)));
    ///////////////////
    // CREATIVE TABS //
    ///////////////////
    // Creates a creative tab with the id "aim:common" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = AimAllItems.CREATIVE_MODE_TABS.register("common", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.aim.common")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> BRIQUETTE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(new ItemStack(EXAMPLE_ITEM.get())); // Add the example item to the tab. For your own tabs, this method is preferred over the event
                output.accept(SCALE_RAY.get());
                output.accept(DEBUG_GRAVITY_CONTROLLER.get());
                output.accept(AimAllBlocks.MILL_ITEM);
                output.accept(AimAllBlocks.CHEM_ITEM);
                output.accept(AimAllBlocks.C_STEEL_ITEM);
                output.accept(AimAllBlocks.ALUMINIUM_BLOCK_ITEM);
                output.accept(JKL_CHLORINE.get());
                output.accept(ENERGY_CONNECTOR.get());
                output.accept(BRIQUETTE.get());
                output.accept(EPIC_WEAPON.get());
                //output.accept(AimAllBlocks.TEST_TUBE_ITEM);
                output.accept(AimAllBlocks.ALE_ITEM);
                output.accept(AimAllBlocks.IRON_ROD_ITEM);
                output.accept(AimAllBlocks.LOGISTICS_ITEM);
                output.accept(AimAllBlocks.OPERATOR_ITEM);
            }).build());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MATERIAL_TAB = AimAllItems.CREATIVE_MODE_TABS.register("material", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.aim.material"))
            .withTabsBefore(EXAMPLE_TAB.getKey())
            .icon(() -> JKL_CHLORINE.get().getDefaultInstance())
            .displayItems((params, output) -> {
                output.accept(UNIT_HYDROCHLORIC_ACID.get());
                output.accept(UNIT_SULFURIC_ACID.get());
                output.accept(UNIT_DILUTED_SULFURIC_ACID.get());
                output.accept(UNIT_NITRIC_ACID.get());
                output.accept(UNIT_CAUSTIC_ALKALI.get());
                output.accept(CAUSTIC_ALKALI.get());
                output.accept(UNIT_HYDROGEN.get());
                output.accept(UNIT_OXYGEN.get());
                output.accept(UNIT_CHLORINE.get());
                output.accept(UNIT_NITROGEN.get());
                output.accept(UNIT_CARBON_DIOXIDE.get());
                output.accept(UNIT_AIR.get());
                output.accept(UNIT_WATER.get());
                output.accept(UNIT_ROCK_SALT_WATER.get());
                output.accept(ROCK_SALT.get());
                output.accept(CRUSHED_BAUXITE.get());
                output.accept(BAUXITE_DUST.get());
                output.accept(BAUXITE_CONCENTRATE_DUST.get());
                output.accept(UNIT_SODIUM_ALUMINATE_MUD.get());
                output.accept(UNIT_DILUTED_SODIUM_ALUMINATE_MUD.get());
                output.accept(UNIT_RAW_SODIUM_ALUMINATE_SOLUTION.get());
                output.accept(UNIT_SODIUM_ALUMINATE_SOLUTION.get());
                output.accept(UNIT_ALUMINIUM_HYDROXIDE_SOLUTION.get());
                output.accept(UNIT_BAUXITE_RESIDUE.get());
                output.accept(ALUMINIUM_HYDROXIDE.get());
                output.accept(ALUMINIUM_OXIDE.get());
                output.accept(ALUMINIUM_CHLORIDE.get());
                output.accept(ALUMINIUM_CHLORIDE_CRYSTAL.get());
                output.accept(ALUMINIUM_DUST.get());
                output.accept(ALUMINIUM_INGOT.get());
                output.accept(ELECTROLYTIC_ALUMINUM_INGOT.get());
                output.accept(JKL_CHLORINE.get());
                output.accept(APATITE.get());
                output.accept(UNIT_NITROGEN_MONOXIDE.get());
                output.accept(ZEOLITE.get());
                output.accept(UNIT_CALCIUM_CHLORIDE.get());
                output.accept(UNIT_PHOSPHORIC_ACID.get());
                output.accept(BRIQUETTE.get());
            }).build()
    );
}
