package com.github.wintersteve25.tau.components;

import com.github.wintersteve25.tau.theme.Theme;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.components.Renderable;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.layout.Axis;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.Vector2i;

import java.util.List;

public final class WidgetWrapper implements PrimitiveUIComponent {
    
    private final AbstractWidget child;

    public WidgetWrapper(AbstractWidget child) {
        this.child = child;
    }

    @Override
    public Vector2i build(Layout layout, Theme theme, List<Renderable> renderables, List<Renderable> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<GuiEventListener> eventListeners) {
        child.setWidth(layout.getWidth());
        child.setHeight(layout.getHeight());
        child.x = layout.getPosition(Axis.HORIZONTAL, child.getWidth());
        child.y = layout.getPosition(Axis.VERTICAL, child.getHeight());
        
        renderables.add(child);
        eventListeners.add(child);
        
        return layout.getSize();
    }
}
