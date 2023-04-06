package com.github.wintersteve25.tau.components;

import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.build.UIBuilder;
import com.github.wintersteve25.tau.utils.Vector2i;

import java.util.List;

public class Positioned implements PrimitiveUIComponent {
    private final Vector2i position;
    private final UIComponent child;
    
    public Positioned(Vector2i position, UIComponent child) {
        this.position = position;
        this.child = child;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        Layout childLayout = new Layout(layout.getWidth(), layout.getHeight(), position.x, position.y);
        return UIBuilder.build(childLayout, child, renderables, dynamicUIComponents, eventListeners);
    }
}
