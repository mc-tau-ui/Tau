package com.github.wintersteve25.tau.components;

import com.github.wintersteve25.tau.build.UIBuilder;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.Vector2i;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;

import java.util.List;

public class If implements PrimitiveUIComponent {
    
    private final boolean condition;
    private final UIComponent child;

    public If(boolean condition, UIComponent child) {
        this.condition = condition;
        this.child = child;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables, List<IRenderable> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        if (condition) return UIBuilder.build(layout, child, renderables, tooltips, dynamicUIComponents, eventListeners);
        return layout.getSize();
    }
}
