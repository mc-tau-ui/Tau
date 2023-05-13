package com.github.wintersteve25.tau.utils;

import com.mojang.blaze3d.vertex.PoseStack;

@FunctionalInterface
public interface RenderProvider {
    void render(PoseStack PoseStack, int mouseX, int mouseY, float partialTicks, int x, int y, int width, int height);
}
