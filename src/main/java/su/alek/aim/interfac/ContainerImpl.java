package su.alek.aim.interfac;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ContainerImpl implements Container {
    private NonNullList<ItemStack> items;
    public ContainerImpl(NonNullList<ItemStack> items){
        this.items = items;
    }
    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    @Override
    public @NotNull ItemStack getItem(int pSlot) {
        return this.items.get(pSlot);
    }

    @Override
    public @NotNull ItemStack removeItem(int pSlot, int pAmount) {
        return ContainerHelper.removeItem(this.items, pSlot, pAmount);
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int pSlot) {
        return ContainerHelper.takeItem(this.items, pSlot);
    }

    @Override
    public void setItem(int pSlot, @NotNull ItemStack pStack) {
        this.items.set(pSlot, pStack);
    }

    @Override
    public void setChanged() {
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return true;
    }

    @Override
    public void clearContent() {
        this.items = NonNullList.withSize(items.size(), ItemStack.EMPTY);
    }
}
