package com.github.wintersteve25.tau.components;

import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.theme.Theme;
import com.github.wintersteve25.tau.utils.Vector2i;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;

import java.util.List;

public final class Spacer implements PrimitiveUIComponent {
    
    private final Vector2i size;

    public Spacer(Vector2i size) {
        this.size = size;
    }

    @Override
    public Vector2i build(Layout layout, Theme theme, List<IRenderable> renderables, List<IRenderable> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        return size;
    }
}
