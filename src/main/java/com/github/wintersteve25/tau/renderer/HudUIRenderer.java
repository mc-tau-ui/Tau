package com.github.wintersteve25.tau.renderer;

import com.github.wintersteve25.tau.theme.MinecraftTheme;
import com.github.wintersteve25.tau.theme.Theme;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.MainWindow;
import net.minecraft.client.gui.IRenderable;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.build.UIBuilder;

import java.util.ArrayList;
import java.util.List;

public class HudUIRenderer {
    private final UIComponent uiComponent;
    private final List<IRenderable> components;
    private final List<DynamicUIComponent> dynamicUIComponents;
    private final Theme theme;

    private boolean built;
    private int screenWidth;
    private int screenHeight;

    public HudUIRenderer(UIComponent uiComponent, Theme theme) {
        this.uiComponent = uiComponent;
        this.components = new ArrayList<>();
        this.dynamicUIComponents = new ArrayList<>();
        this.theme = theme;
    }
    
    public HudUIRenderer(UIComponent uiComponent) {
        this(uiComponent, MinecraftTheme.INSTANCE);
    }

    private void init() {
        Layout layout = new Layout(screenWidth, screenHeight);

        components.clear();
        dynamicUIComponents.clear();

        UIBuilder.build(layout, theme, uiComponent, components, new ArrayList<>(), dynamicUIComponents, new ArrayList<>());

        built = true;
    }

    public void tick() {
        if (!built) return;
        UIBuilder.rebuildDynamics(dynamicUIComponents);
    }
    
    public void render(MainWindow mainWindow, MatrixStack matrixStack, float pPartialTicks) {
        int width = mainWindow.getGuiScaledWidth();
        int height = mainWindow.getGuiScaledHeight();
        
        for (IRenderable component : components) {
            component.render(matrixStack, 0, 0, pPartialTicks);
        }
        
        if (width != screenWidth || height != screenHeight) {
            screenWidth = width;
            screenHeight = height;
            init();
        }
    }
}
