package com.github.wintersteve25.tau.components;

import com.github.wintersteve25.tau.theme.Theme;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.components.Renderable;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.build.UIBuilder;
import com.github.wintersteve25.tau.utils.Vector2i;

import java.util.List;

public final class Positioned implements PrimitiveUIComponent {
    private final Vector2i position;
    private final UIComponent child;
    
    public Positioned(Vector2i position, UIComponent child) {
        this.position = position;
        this.child = child;
    }

    @Override
    public Vector2i build(Layout layout, Theme theme, List<Renderable> renderables, List<Renderable> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<GuiEventListener> eventListeners) {
        Layout childLayout = new Layout(layout.getWidth(), layout.getHeight(), position.x, position.y);
        return UIBuilder.build(childLayout, theme, child, renderables, tooltips, dynamicUIComponents, eventListeners);
    }
}
