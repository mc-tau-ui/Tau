package com.github.wintersteve25.tau.utils;

import com.mojang.blaze3d.matrix.MatrixStack;

@FunctionalInterface
public interface RenderProvider {
    void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks, int x, int y, int width, int height);
}
