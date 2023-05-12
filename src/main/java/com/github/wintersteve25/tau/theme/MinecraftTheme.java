package com.github.wintersteve25.tau.theme;

import com.github.wintersteve25.tau.Tau;
import com.github.wintersteve25.tau.utils.Color;
import com.github.wintersteve25.tau.utils.InteractableState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;

public class MinecraftTheme implements Theme {
    public static final MinecraftTheme INSTANCE = new MinecraftTheme();
    
    private static final ResourceLocation TEXTURE = new ResourceLocation(Tau.MOD_ID, "textures/gui/container.png");
    private static final Color TEXT = new Color(0xFFE8E8E8);
    // TODO: Where's GuiUtils??
//    private static final Color TOOLTIP = new Color(GuiUtils.DEFAULT_BACKGROUND_COLOR);
//    private static final Color TOOLTIP_BORDER_START = new Color(GuiUtils.DEFAULT_BORDER_COLOR_START);
//    private static final Color TOOLTIP_BORDER_END = new Color(GuiUtils.DEFAULT_BORDER_COLOR_END);
    private static final Color TOOLTIP = new Color(0xFF0000);
    private static final Color TOOLTIP_BORDER_START = new Color(0x00FF00);
    private static final Color TOOLTIP_BORDER_END = new Color(0x0000FF);
    
    @Override
    public void drawButton(PoseStack PoseStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY, InteractableState state) {

        // TODO: Where's GuiUtils??
        //        GuiUtils.drawContinuousTexturedBox(PoseStack, TEXTURE, x, y, 0, 166 + state.getNumber() * 20, width, height, 200, 20, 2, 3, 2, 2, 0);
        
    }

    @Override
    public void drawContainer(PoseStack PoseStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY) {

        // TODO: Where's GuiUtils??
        //        GuiUtils.drawContinuousTexturedBox(PoseStack, TEXTURE, x, y, 0, 0, width, height, 176, 166, 4, 0);
    }

    @Override
    public void drawScrollbar(PoseStack PoseStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY) {

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
