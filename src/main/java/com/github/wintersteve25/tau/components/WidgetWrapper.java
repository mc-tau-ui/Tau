package com.github.wintersteve25.tau.components;

import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.widget.Widget;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.layout.Axis;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.Vector2i;

import java.util.List;

public final class WidgetWrapper implements PrimitiveUIComponent {
    
    private final Widget child;

    public WidgetWrapper(Widget child) {
        this.child = child;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables, List<IRenderable> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        child.setWidth(layout.getWidth());
        child.setHeight(layout.getHeight());
        child.x = layout.getPosition(Axis.HORIZONTAL, child.getWidth());
        child.y = layout.getPosition(Axis.VERTICAL, child.getHeight());
        
        renderables.add(child);
        eventListeners.add(child);
        
        return layout.getSize();
    }
}
