package su.alek.aim.block.entity;

import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.alek.aim.AimModMain;
import su.alek.aim.block.AbstractMachine44;
import su.alek.aim.gui.menu.MenuMachine44;
import su.alek.aim.recipe.Recipe44;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public abstract class EntityAbstractMachine44 extends BlockEntity implements WorldlyContainer, MenuProvider {
    public static ItemStack[] emptySlot = new ItemStack[4];
    //public NonNullList<ItemStack> items = NonNullList.withSize(8,ItemStack.EMPTY);
    public ItemStack[] input = new ItemStack[4];
    public ItemStack[] output = new ItemStack[4];
    public int recipeTime;
    public int workTime;
    public ItemStack[] recipeItems = new ItemStack[4];
    public boolean paused;
    public boolean charged;
    public boolean hasFreeSlot;
    public BlockPos lighteningRodPos;
    public ContainerData data = new ContainerData() {
        @Override
        public int get(int pIndex) {
            return switch (pIndex) {
                case 0 -> workTime;
                case 1 -> recipeTime;
                default -> 0;
            };
        }

        @Override
        public void set(int pIndex, int pValue) {
            if (pIndex == 0){
                workTime=pValue;
            }else if (pIndex == 1){
                recipeTime = pValue;
            }else {
                throw new IndexOutOfBoundsException(String.format("Index %d out of bound %d", pIndex, 2));
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };
    static {
        for (int i=0;i<4;i++){
            emptySlot[i] = ItemStack.EMPTY;
        }
    }
    {
        for (int i=0;i<4;i++){
            input[i] = ItemStack.EMPTY;
            output[i] = ItemStack.EMPTY;
            recipeItems[i] = ItemStack.EMPTY;
        }
    }

    public EntityAbstractMachine44(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
        this.charged = AimModMain.teacon_mode;
    }

    public abstract HashMap<HashSet<Item>, Recipe44> getRecipe();

    @Override
    public int getContainerSize() {
        return 8;
    }

    @Override
    public boolean canPlaceItemThroughFace(int pIndex, @NotNull ItemStack pItemStack, @Nullable Direction pDirection) {
        return pIndex <= 3;
    }

    @Override
    public boolean canTakeItemThroughFace(int pIndex, @NotNull ItemStack pStack, @NotNull Direction pDirection) {
        return pIndex >= 4;
    }
    @Override
    public int @NotNull [] getSlotsForFace(@NotNull Direction pSide) {
        return new int[]{0, 1, 2, 3, 4, 5, 6, 7};
    }

    @Override
    public boolean isEmpty() {
        return Arrays.equals(input, emptySlot) && Arrays.equals(output, emptySlot);
    }

    @Override
    public @NotNull ItemStack getItem(int pSlot) {
        if (pSlot >=0 && pSlot <=3){
            return input[pSlot];
        }else if (pSlot <=7){
            return output[pSlot -4];
        }
        else {
            throw new IndexOutOfBoundsException(pSlot);
        }
    }

    @Override
    public @NotNull ItemStack removeItem(int pSlot, int pAmount) {
        NonNullList<ItemStack> itemStacks = NonNullList.withSize(8, ItemStack.EMPTY);
        for (int i = 0; i < 8; i++) {
            itemStacks.set(i, this.getItem(i));
        }
        ItemStack itemStack = ContainerHelper.removeItem(itemStacks, pSlot, pAmount);
        if (!itemStack.isEmpty()) {
            this.setChanged();
        }
        return itemStack;
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int pSlot) {
        NonNullList<ItemStack> itemStacks = NonNullList.withSize(8, ItemStack.EMPTY);
        for (int i = 0; i < 8; i++) {
            itemStacks.set(i, this.getItem(i));
        }
        return ContainerHelper.takeItem(itemStacks, pSlot);
    }

    @Override
    public void setItem(int pSlot, @NotNull ItemStack pStack) {
        if (pSlot < 4){
            input[pSlot] = pStack;
        }else {
            output[pSlot - 4] = pStack;
        }
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return true;
    }

    @Override
    public void clearContent() {
        for (int i = 0;i < 4;i++){
            input[i] = emptySlot[i].copy();
            output[i] = emptySlot[i].copy();
        }
    }

    public static void serverTick(Level pLevel, BlockPos pos, BlockState state, EntityAbstractMachine44 blockEntity){
        if (!blockEntity.paused){
            if (Arrays.equals(blockEntity.recipeItems, emptySlot)){
                if (Recipe44.matches(blockEntity.input, blockEntity.getRecipe()) && Recipe44.canConsume(blockEntity.input,blockEntity.getRecipe())){
                    blockEntity.recipeTime = Recipe44.getTime(blockEntity.input, blockEntity.getRecipe());
                    if (blockEntity.charged){
                        blockEntity.workTime = 1;
                    }else {
                        blockEntity.workTime = 0;
                    }
                    blockEntity.recipeItems = Recipe44.getResult(blockEntity.input,blockEntity.getRecipe());
                    Recipe44.consumeByRecipe(blockEntity.input,blockEntity.getRecipe());
                }
            }else {
                if (blockEntity.charged)
                    ++blockEntity.workTime;
                if (blockEntity.workTime >= blockEntity.recipeTime){
                    for (int i = 0; i < 4; i++) {
                        blockEntity.output[i] = blockEntity.recipeItems[i].copy();
                    }
                    for (int i = 0;i < 4;i++){
                        blockEntity.recipeItems[i] = emptySlot[i].copy();
                    }
                    blockEntity.workTime = 0;
                    blockEntity.recipeTime = 0;
                }
            }
            ///////////////
            // 自动物品IO //
            //////////////
            Direction direction = state.getValue(AbstractMachine44.HORIZONTAL_FACING);
            Vec3i offset;
            switch (direction){
                case EAST:offset = new Vec3i(1,0,1);break;
                case SOUTH:offset = new Vec3i(1,0,-1);break;
                case WEST:offset = new Vec3i(-1,0,-1);break;
                case NORTH:offset = new Vec3i(-1,0,1);break;
                default:return;
            }
            // input
            BlockPos inputPos = pos.offset(0,2,0);
            if (pLevel.getBlockEntity(inputPos) instanceof Container container){
                for (int i = 0; i < container.getContainerSize(); i++) {
                    ItemStack leftOver = HopperBlockEntity.addItem(container, blockEntity, container.getItem(i), Direction.UP);
                    container.setItem(i, leftOver);
                }
            }
            // output
            BlockPos outputPos = pos.offset(offset);
            if (pLevel.getBlockEntity(outputPos) instanceof Container container){
                for (int i = 0; i < 4; i++){
                    ItemStack leftOver = HopperBlockEntity.addItem(blockEntity, container, blockEntity.getItem(i+4), direction.getOpposite());
                    blockEntity.setItem(i+4, leftOver);
                }
            }
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
        return new MenuMachine44(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag pTag, HolderLookup.@NotNull Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        this.paused = pTag.getBoolean("paused");
        this.workTime = pTag.getInt("workTime");
        this.recipeTime = pTag.getInt("recipeTime");
        NonNullList<ItemStack> items = NonNullList.withSize(12, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(pTag, items, pRegistries);
        for (int i = 0; i < 8; i++) {
            this.setItem(i, items.get(i));
        }
        for (int i = 8; i < 12; i++) {
            this.recipeItems[i-8] = items.get(i);
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag, HolderLookup.@NotNull Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        NonNullList<ItemStack> items = NonNullList.withSize(12, ItemStack.EMPTY);
        pTag.putBoolean("paused", paused);
        pTag.putInt("workTime", workTime);
        pTag.putInt("recipeTime",recipeTime);
        for (int i = 0; i < 8; i++) {
            items.set(i, this.getItem(i));
        }
        for (int i = 8; i < 12; i++) {
            items.set(i, this.recipeItems[i-8]);
        }
        ContainerHelper.saveAllItems(pTag, items, pRegistries);
    }
}
