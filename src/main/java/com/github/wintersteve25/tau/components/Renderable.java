package com.github.wintersteve25.tau.components;

import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.Vector2i;

import java.util.List;

public final class Renderable implements PrimitiveUIComponent {
    
    private final IRenderable renderable;

    public Renderable(IRenderable renderable) {
        this.renderable = renderable;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        renderables.add(renderable);
        return layout.getSize();
    }
}
