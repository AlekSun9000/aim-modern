package su.alek.aim.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Recipe44 {
    public Item[] input;
    public int[] inputConsume;
    public ItemStack[] output;
    public int recipeTime;
    private Recipe44(){}
    public static void addRecipe(
            HashMap<HashSet<Item>, Recipe44> recipeMap, Item[] input, int[] inputConsume, Item[] output, int[] outputCount, int recipeTime){
        if (
                input.length != 4 && inputConsume.length != 4 && output.length != 4 && outputCount.length != 4
        )
            throw new IllegalArgumentException("A developer tries to input an array whose length is not 4.");
        Recipe44 recipe = new Recipe44();
        recipe.input = input;
        recipe.inputConsume = inputConsume;
        recipe.recipeTime = recipeTime;
        HashSet<Item> inputSet = new HashSet<>(4);
        inputSet.addAll(Arrays.asList(input));
        ItemStack[] outputStack = new ItemStack[4];
        for (int i = 0; i < 4; i++) {
            outputStack[i] = new ItemStack(output[i],outputCount[i]);
        }
        recipe.output = outputStack;
        recipeMap.put(inputSet, recipe);
    }
    public static boolean matches(ItemStack[] inputStacks, HashMap<HashSet<Item>, Recipe44> recipeMap){
        if (inputStacks.length != 4) {
            throw new IllegalArgumentException("A developer tries to input an array of ItemStack whose length is not 4.");
        }
        HashSet<Item> itemSet = new HashSet<>(4);
        for (ItemStack stack : inputStacks) {
            Item item = stack.getItem();
            itemSet.add(item);
        }
        return recipeMap.containsKey(itemSet);
    }

    /*
     * call matches() before call this, otherwise NullPointerException
     */
    public static int getTime(ItemStack[] inputStacks, HashMap<HashSet<Item>, Recipe44> recipeMap){
        if (inputStacks.length != 4) {
            throw new IllegalArgumentException("A developer tries to input an array of ItemStack whose length is not 4.");
        }
        HashSet<Item> itemSet = new HashSet<>(4);
        for (ItemStack stack : inputStacks) {
            Item item = stack.getItem();
            itemSet.add(item);
        }
        return recipeMap.get(itemSet).recipeTime;
    }

    /*
     * call matches() before call this, otherwise NullPointerException
     */
    public static ItemStack[] getResult(ItemStack[] inputStacks, HashMap<HashSet<Item>, Recipe44> recipeMap){
        if (inputStacks.length != 4) {
            throw new IllegalArgumentException("A developer tries to input an array of ItemStack whose length is not 4.");
        }
        HashSet<Item> itemSet = new HashSet<>(4);
        for (ItemStack stack : inputStacks) {
            Item item = stack.getItem();
            itemSet.add(item);
        }
        Recipe44 recipe = recipeMap.get(itemSet);
        ItemStack[] stacks = new ItemStack[4];
        for (int i = 0; i < 4; i++) {
            stacks[i] = recipe.output[i].copy();
        }
        return stacks;
    }

    /*
     * call matches() before call this, otherwise NullPointerException
     */
    public static boolean canConsume(ItemStack[] inputStacks, HashMap<HashSet<Item>, Recipe44> recipeMap){
        if (inputStacks.length != 4) {
            throw new IllegalArgumentException("A developer tries to input an array of ItemStack whose length is not 4.");
        }
        ItemStack[] sortedStacks = sortByRecipe(inputStacks,recipeMap);
        HashSet<Item> itemSet = new HashSet<>(4);
        for (ItemStack stack : sortedStacks) {
            Item item = stack.getItem();
            itemSet.add(item);
        }
        Recipe44 recipe = recipeMap.get(itemSet);
        for (int i = 0; i < 4; i++){
            if (sortedStacks[i].getCount() <= recipe.inputConsume[i]){
                return false;
            }
        }
        return true;
    }

    public static ItemStack[] sortByRecipe(ItemStack[] inputStacks, HashMap<HashSet<Item>, Recipe44> recipeMap){
        if (inputStacks.length != 4) {
            throw new IllegalArgumentException("A developer tries to input an array of ItemStack whose length is not 4.");
        }
        HashSet<Item> itemSet = new HashSet<>(4);
        for (ItemStack stack : inputStacks) {
            Item item = stack.getItem();
            itemSet.add(item);
        }
        Recipe44 recipe = recipeMap.get(itemSet);
        if (recipe == null){
            return inputStacks;
        }
        HashMap<Item,Integer> map = new HashMap<>(4);
        for (int i=0;i<4;i++){
            map.put(recipe.input[i],i);
        }
        ItemStack[] returnedStack = new ItemStack[4];
        for (int i=0;i<4;i++){
            returnedStack[map.get(inputStacks[i].getItem())] = inputStacks[i];
        }
        return returnedStack;
    }

    /*
     * call matches() and canConsume() before call this
     */
    public static void consumeByRecipe(ItemStack[] inputStacks, HashMap<HashSet<Item>, Recipe44> recipeMap){
        if (inputStacks.length != 4) {
            throw new IllegalArgumentException("A developer tries to input an array of ItemStack whose length is not 4.");
        }
        ItemStack[] sortedStacks = sortByRecipe(inputStacks,recipeMap);
        HashSet<Item> itemSet = new HashSet<>(4);
        for (ItemStack stack : inputStacks) {
            Item item = stack.getItem();
            itemSet.add(item);
        }
        Recipe44 recipe = recipeMap.get(itemSet);
        for (int i=0;i<4;i++){
            sortedStacks[i].setCount(sortedStacks[i].getCount() - recipe.inputConsume[i]);
        }
    }
}
