package su.alek.aim.gui.client_screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;
import su.alek.aim.gui.menu.MenuMachine44;

public class ScreenMachine44 extends AbstractContainerScreen<MenuMachine44> {
    private final MenuMachine44 menu;
    public ScreenMachine44(MenuMachine44 pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.menu = pMenu;
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        pGuiGraphics.blit(ResourceLocation.fromNamespaceAndPath("aim", "textures/gui/machine44.png"), this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
        pGuiGraphics.drawString(this.font, String.format("workTime:%d", menu.getWorkTime()),0, 8, 0xffffff);
        pGuiGraphics.drawString(this.font,String.format("RecipeTime:%d", menu.getRecipeTime()),0, 0, 0xffffff);
    }
}
