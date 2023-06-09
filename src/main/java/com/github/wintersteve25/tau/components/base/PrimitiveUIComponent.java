package com.github.wintersteve25.tau.components.base;

import com.github.wintersteve25.tau.theme.Theme;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.Vector2i;

import java.util.List;

/**
 * A UI Component with access to lower level content. For more can be used for more complicated components that need to be customly rendered
 */
public interface PrimitiveUIComponent extends UIComponent {
    Vector2i build(Layout layout, Theme theme, List<IRenderable> renderables, List<IRenderable> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners);
    
    @Override
    default UIComponent build(Layout layout, Theme theme) {
        return null;
    }
}
