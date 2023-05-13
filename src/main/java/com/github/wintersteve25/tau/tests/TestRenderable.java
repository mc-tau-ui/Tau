package com.github.wintersteve25.tau.tests;

import com.github.wintersteve25.tau.components.Renderable;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.theme.Theme;
import com.github.wintersteve25.tau.utils.Color;
import net.minecraft.client.gui.screens.Screen;

public class TestRenderable implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Renderable(((pPoseStack, pMouseX, pMouseY, pPartialTicks) -> {
            Screen.fill(pPoseStack, 0, 0, pMouseX, pMouseY, Color.WHITE.getAARRGGBB());
        }));
    }
}
