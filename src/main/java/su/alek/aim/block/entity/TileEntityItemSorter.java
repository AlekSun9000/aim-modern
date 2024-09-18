package su.alek.aim.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import su.alek.aim.block.AimAllBlocks;
import su.alek.aim.interfac.ItemAndComponent;
import su.alek.aim.interfac.ItemCountAndComponent;

import java.util.ArrayList;

// WIP
public class TileEntityItemSorter extends BlockEntity implements WorldlyContainer {
    private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
    private ItemStack d = ItemStack.EMPTY;
    private ItemStack u = ItemStack.EMPTY;
    private ItemStack e = ItemStack.EMPTY;
    private ItemStack s = ItemStack.EMPTY;
    private ItemStack w = ItemStack.EMPTY;
    private ItemStack n = ItemStack.EMPTY;
    public TileEntityItemSorter(BlockPos pPos, BlockState pBlockState) {
        super(AimAllBlocks.HELPER_E.get(), pPos, pBlockState);
    }

    @Override
    public int[] getSlotsForFace(Direction pSide) {
        int[] ints = new int[27];
        for (int i = 0; i < 27; i++) {
            ints[i] = i;
        }
        return ints;
    }

    @Override
    public boolean canPlaceItemThroughFace(int pIndex, ItemStack pItemStack, @Nullable Direction pDirection) {
        return true;
    }

    @Override
    public boolean canTakeItemThroughFace(int pIndex, ItemStack pStack, Direction pDirection) {
        return false;
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack item : items){
            if (item.isEmpty())
                return true;
        }
        return false;
    }

    @Override
    public ItemStack getItem(int pSlot) {
        return items.get(pSlot);
    }

    @Override
    public ItemStack removeItem(int pSlot, int pAmount) {
        return ContainerHelper.removeItem(items, pSlot, pAmount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int pSlot) {
        return ContainerHelper.takeItem(items, pSlot);
    }

    @Override
    public void setItem(int pSlot, ItemStack pStack) {
        items.set(pSlot, pStack);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }

    @Override
    public void clearContent() {
        items = NonNullList.withSize(27, ItemStack.EMPTY);
    }

    public void setConfig(Direction direction, ItemStack stack){
        switch (direction){
            case UP:this.u = stack;
            case DOWN:this.d = stack;
            case EAST:this.e = stack;
            case SOUTH:this.s = stack;
            case WEST:this.w = stack;
            case NORTH:this.n = stack;
        }
    }

    public ItemStack getConfig(Direction direction){
        return switch (direction) {
            case UP -> this.u;
            case DOWN -> this.d;
            case EAST -> this.e;
            case SOUTH -> this.s;
            case WEST -> this.w;
            case NORTH -> this.n;
        };
    }

    public ItemStack[] getAllConfig(){
        ItemStack[] stacks = new ItemStack[6];
        int i = 0;
        stacks[i++] = getConfig(Direction.UP);
        stacks[i++] = getConfig(Direction.DOWN);
        stacks[i++] = getConfig(Direction.EAST);
        stacks[i++] = getConfig(Direction.SOUTH);
        stacks[i++] = getConfig(Direction.WEST);
        stacks[i  ] = getConfig(Direction.NORTH);
        return stacks;
    }

    /**
     * 合并ItemStack[]为ItemCountAndComponent[]
     * 每种物品有且只有一个堆，可能出现64+物品堆
     */
    public static ItemCountAndComponent[] mergeStacks(ItemStack[] stacks){
        ArrayList<ItemCountAndComponent> itemsWithCount = new ArrayList<>();
        ArrayList<ItemAndComponent> items = new ArrayList<>();
        for (int i = 0; i < stacks.length; i++) {
            Item item = stacks[i].getItem();
            DataComponentMap components = stacks[i].getComponents();
            int count = stacks[i].getCount();
            ItemAndComponent itemAndComponent = new ItemAndComponent(item, components);
            if (items.contains(itemAndComponent)){
                int lastIndex = items.lastIndexOf(itemAndComponent);
                ItemCountAndComponent oldItemWithCount = itemsWithCount.get(lastIndex);
                itemsWithCount.set(lastIndex, new ItemCountAndComponent(oldItemWithCount.item(), oldItemWithCount.components(), oldItemWithCount.count() + count));
            }else {
                itemsWithCount.add(new ItemCountAndComponent(item, components, count));
                items.add(new ItemAndComponent(item, components));
            }
        }
        return itemsWithCount.toArray(new ItemCountAndComponent[stacks.length]);
    }

    /**
     * 将每个面的需求合并
     * @return 每个面的需求，每种物品有且只有一个堆，可能出现64+物品堆
     */
    public ItemCountAndComponent[] getAllConfigs(){
        return mergeStacks(getAllConfig());
    }

    //public boolean canOutput(){}
}
