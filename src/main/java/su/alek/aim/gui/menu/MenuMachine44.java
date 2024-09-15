package su.alek.aim.gui.menu;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import su.alek.aim.interfac.ContainerImpl;

public class MenuMachine44 extends AbstractContainerMenu {
    private final ContainerData data;
    // client
    public MenuMachine44(int pContainerId, Inventory playerInventory) {
        this(pContainerId, playerInventory, new SimpleContainer(8), new SimpleContainerData(2));
    }
    // server
    public MenuMachine44(int pContainerId, Inventory playerInventory, Container machine, ContainerData data){
        super(AimAllMenus.MACHINE44_MENU.get(), pContainerId);
        // input slot
        this.addSlot(new Slot(machine, 0, 44, 18));
        this.addSlot(new Slot(machine, 1, 62, 18));
        this.addSlot(new Slot(machine, 2, 44, 36));
        this.addSlot(new Slot(machine, 3, 62, 36));
        // output slot
        this.addSlot(new Slot(machine, 4, 98, 18));
        this.addSlot(new Slot(machine, 5, 116, 18));
        this.addSlot(new Slot(machine, 6, 98, 36));
        this.addSlot(new Slot(machine, 7, 116, 36));
        // fluid : from 0.3.0
        // battery : from 0.2.0
        // player inventory
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 87 + i * 18));
            }
        }
        for (int k = 0; k < 9; k++) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 145));
        }
        // player left hand
        this.addSlot(new Slot(new ContainerImpl(playerInventory.offhand), 0, 8, 55));

        this.data = data;
        this.addDataSlots(data);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pIndex) {
        Slot slot = this.slots.get(pIndex);
        ItemStack stack = slot.getItem();
        ItemStack stack1 = stack.copy();
        if (slot.hasItem()){
            if (pIndex <=7 && pIndex >=4) {
                if (!this.moveItemStackTo(stack1, 9, 45, true))
                    return ItemStack.EMPTY;
            } else if (pIndex <=3 && pIndex >=0){
                if (!this.moveItemStackTo(stack1, 0, 4, false))
                    return ItemStack.EMPTY;
            }
        }
        if (stack1.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        if (stack1.getCount() == stack.getCount()) {
            return ItemStack.EMPTY;
        }
        slot.onTake(pPlayer, stack1);
        return stack;
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return true;
    }

    public int getRecipeTime(){
        return this.data.get(1);
    }
    public int getWorkTime(){
        return this.data.get(0);
    }
}
