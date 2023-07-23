package com.github.wintersteve25.tau.theme;

import com.github.wintersteve25.tau.utils.Color;
import com.github.wintersteve25.tau.utils.InteractableState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.FormattedText;

import java.util.List;

public interface Theme {
    void drawButton(PoseStack postStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY, InteractableState state);
    
    void drawContainer(PoseStack poseStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY);
    
    void drawScrollbar(PoseStack poseStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY);
   
    void drawTooltip(PoseStack poseStack, int mouseX, int mouseY, int screenWidth, int screenHeight, Font fontRenderer, List<ClientTooltipComponent> tooltips);
    
    Color getTextColor();
}