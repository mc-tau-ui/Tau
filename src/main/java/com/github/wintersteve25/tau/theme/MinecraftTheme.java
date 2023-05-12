package com.github.wintersteve25.tau.theme;

import com.github.wintersteve25.tau.Tau;
import com.github.wintersteve25.tau.utils.Color;
import com.github.wintersteve25.tau.utils.InteractableState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.ScreenUtils;

import java.util.List;

public class MinecraftTheme implements Theme {
    public static final MinecraftTheme INSTANCE = new MinecraftTheme();
    
    private static final ResourceLocation TEXTURE = new ResourceLocation(Tau.MOD_ID, "textures/gui/container.png");
    private static final Color TEXT = new Color(0xFFE8E8E8);
    private static final Color TOOLTIP = new Color(ScreenUtils.DEFAULT_BACKGROUND_COLOR);
    private static final Color TOOLTIP_BORDER_START = new Color(ScreenUtils.DEFAULT_BORDER_COLOR_START);
    private static final Color TOOLTIP_BORDER_END = new Color(ScreenUtils.DEFAULT_BORDER_COLOR_END);
    
    @Override
    public void drawButton(PoseStack postStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY, InteractableState state) {
        ScreenUtils.blitWithBorder(postStack, TEXTURE, x, y, 0, 166 + state.getNumber() * 20, width, height, 200, 20, 2, 3, 2, 2, 0);
    }

    @Override
    public void drawContainer(PoseStack poseStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY) {
        ScreenUtils.blitWithBorder(poseStack, TEXTURE, x, y, 0, 0, width, height, 176, 166, 4, 0);
    }

    @Override
    public void drawScrollbar(PoseStack poseStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY) {

    }

    @Override
    public void drawTooltip(PoseStack poseStack, int mouseX, int mouseY, int screenWidth, int screenHeight, Font fontRenderer, List<FormattedText> tooltips) {
        
    }

    @Override
    public Color getTextColor() {
        return TEXT;
    }
}
