package su.alek.aim.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import static su.alek.aim.recipe.Recipe44.addRecipe;

import java.util.HashMap;
import java.util.HashSet;

public final class RecipeMaps {
    private RecipeMaps(){}
    public static HashMap<HashSet<Item>, Recipe44> MILL_RECIPE = new HashMap<>();
    public static HashMap<HashSet<Item>, Recipe44> ROASTER_RECIPE = new HashMap<>();
    public static HashMap<HashSet<Item>, Recipe44> DIGESTER_RECIPE = new HashMap<>();
    public static HashMap<HashSet<Item>, Recipe44> CHEMICAL_PLANT_RECIPE = new HashMap<>();
    public static HashMap<HashSet<Item>, Recipe44> CRYSTAL_RECIPE = new HashMap<>();
    static {
        addRecipe(
                MILL_RECIPE,
                new Item[]{Items.STONE      , Items.AIR, Items.AIR, Items.AIR},
                new int[] {1                , 0        , 0        , 0        },
                new Item[]{Items.COBBLESTONE, Items.AIR, Items.AIR, Items.AIR},
                new int[] {1                , 1        , 0        , 0        },
                20
                );
    }
}
