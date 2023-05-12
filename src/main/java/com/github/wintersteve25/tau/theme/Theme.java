package com.github.wintersteve25.tau.theme;

import com.github.wintersteve25.tau.utils.Color;
import com.github.wintersteve25.tau.utils.InteractableState;
import com.mojang.blaze3d.vertex.PoseStack;

public interface Theme {
    void drawButton(PoseStack PoseStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY, InteractableState state);
    
    void drawContainer(PoseStack PoseStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY);
    
    void drawScrollbar(PoseStack PoseStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY);
    
    Color getTextColor();
    
    Color getTooltipColor();
    
    Color getTooltipBorderStartColor();
    
    Color getTooltipBorderEndColor();
}
