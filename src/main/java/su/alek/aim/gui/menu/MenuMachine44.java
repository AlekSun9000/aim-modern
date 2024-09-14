package su.alek.aim.gui.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class MenuMachine44 extends AbstractContainerMenu {
    private final Container container;
    private ContainerData data;
    // client
    public MenuMachine44(int pContainerId, Inventory playerInventory) {
        this(pContainerId, playerInventory, new SimpleContainer(8), new SimpleContainerData(1));
    }
    // server
    public MenuMachine44(int pContainerId, Inventory playerInventory, Container machine, ContainerData data){
        super(AimAllMenus.MACHINE44_MENU.get(), pContainerId);
        this.container = machine;
        this.addSlot(new Slot(this.container, 0, 56, 17));
        //player inventory
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; k++) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
        this.data = data;
        this.addDataSlots(data);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }

    public int getData(){
        return this.data.get(0);
    }
}
