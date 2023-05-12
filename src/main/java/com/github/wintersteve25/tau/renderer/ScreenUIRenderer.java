package com.github.wintersteve25.tau.renderer;

import com.github.wintersteve25.tau.theme.MinecraftTheme;
import com.github.wintersteve25.tau.theme.Theme;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.build.UIBuilder;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScreenUIRenderer extends Screen {
    
    private final UIComponent uiComponent;
    private final List<Renderable> components;
    private final List<Renderable> tooltips;
    private final List<DynamicUIComponent> dynamicUIComponents;
    private final boolean renderBackground;
    private final Theme theme;
    
    private boolean built;
    
    public ScreenUIRenderer(UIComponent uiComponent, boolean renderBackground, Theme theme) {
        super(StringTextComponent.EMPTY);
        this.uiComponent = uiComponent;
        this.renderBackground = renderBackground;
        this.theme = theme;
        this.components = new ArrayList<>();
        this.tooltips = new ArrayList<>();
        this.dynamicUIComponents = new ArrayList<>();
    }
    
    public ScreenUIRenderer(UIComponent uiComponent, boolean renderBackground) {
        this(uiComponent, renderBackground, MinecraftTheme.INSTANCE);
    }
    
    public ScreenUIRenderer(UIComponent uiComponent) {
        this(uiComponent, true);
    }

    @Override
    protected void init() {
        Layout layout = new Layout(width, height);

        components.clear();
        tooltips.clear();
        dynamicUIComponents.clear();
        UIBuilder.build(layout, theme, uiComponent, components, tooltips, dynamicUIComponents, children);
        Collections.reverse(children);
        
        built = true;
    }

    @Override
    public void tick() {
        if (!built) return;
        UIBuilder.rebuildDynamics(dynamicUIComponents);
    }

    @Override
    public void onClose() {
        for (DynamicUIComponent dynamicUIComponent : dynamicUIComponents) {
            dynamicUIComponent.destroy();
        }
    }

    @Override
    public void render(PoseStack PoseStack, int pMouseX, int pMouseY, float pPartialTicks) {
        if (renderBackground) {
            this.renderBackground(PoseStack);
        }
        
        for (Renderable component : components) {
            component.render(PoseStack, pMouseX, pMouseY, pPartialTicks);
        }
        
        for (Renderable tooltip : tooltips) {
            tooltip.render(PoseStack, pMouseX, pMouseY, pPartialTicks);
        }
    }
}
