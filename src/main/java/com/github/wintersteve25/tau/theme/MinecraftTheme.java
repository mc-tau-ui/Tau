package com.github.wintersteve25.tau.theme;

import com.github.wintersteve25.tau.Tau;
import com.github.wintersteve25.tau.utils.Color;
import com.github.wintersteve25.tau.utils.InteractableState;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.gui.GuiUtils;

public class MinecraftTheme implements Theme {
    public static final MinecraftTheme INSTANCE = new MinecraftTheme();
    
    private static final ResourceLocation TEXTURE = new ResourceLocation(Tau.MOD_ID, "textures/gui/container.png");
    private static final Color TEXT = new Color(0xFFE8E8E8);
    private static final Color TOOLTIP = new Color(GuiUtils.DEFAULT_BACKGROUND_COLOR);
    private static final Color TOOLTIP_BORDER_START = new Color(GuiUtils.DEFAULT_BORDER_COLOR_START);
    private static final Color TOOLTIP_BORDER_END = new Color(GuiUtils.DEFAULT_BORDER_COLOR_END);
    
    
    
    @Override
    public void drawButton(MatrixStack matrixStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY, InteractableState state) {
        GuiUtils.drawContinuousTexturedBox(matrixStack, TEXTURE, x, y, 0, 166 + state.getNumber() * 20, width, height, 200, 20, 2, 3, 2, 2, 0);
    }

    @Override
    public void drawContainer(MatrixStack matrixStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY) {
        GuiUtils.drawContinuousTexturedBox(matrixStack, TEXTURE, x, y, 0, 0, width, height, 176, 166, 4, 0);
    }

    @Override
    public void drawScrollbar(MatrixStack matrixStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY) {

    }

    @Override
    public Color getTextColor() {
        return TEXT;
    }

    @Override
    public Color getTooltipBorderStartColor() {
        return TOOLTIP_BORDER_START;
    }

    @Override
    public Color getTooltipBorderEndColor() {
        return TOOLTIP_BORDER_END;
    }

    @Override
    public Color getTooltipColor() {
        return TOOLTIP;
    }
}
