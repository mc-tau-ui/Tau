package com.github.wintersteve25.tau.components;

import com.github.wintersteve25.tau.theme.Theme;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.build.UIBuilder;
import com.github.wintersteve25.tau.utils.Vector2i;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Stack implements PrimitiveUIComponent {
    private final Iterable<UIComponent> children;

    public Stack(Iterable<UIComponent> children) {
        this.children = children;
    }

    public Stack(UIComponent... children) {
        this.children = Arrays.stream(children).collect(Collectors.toList());
    }

    @Override
    public Vector2i build(Layout layout, Theme theme, List<IRenderable> renderables, List<IRenderable> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        for (UIComponent child : children) {
            UIBuilder.build(layout, theme, child, renderables, tooltips, dynamicUIComponents, eventListeners);
        }
        
        return layout.getSize();
    }
}
