package com.github.wintersteve25.tau.components;

import com.github.wintersteve25.tau.layout.LayoutSetting;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Axis;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.FlexSizeBehaviour;
import com.github.wintersteve25.tau.build.UIBuilder;
import com.github.wintersteve25.tau.utils.Vector2i;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Row implements PrimitiveUIComponent {
    
    private final Iterable<UIComponent> children;
    private final int spacing;
    private final FlexSizeBehaviour sizeBehaviour;
    private final LayoutSetting alignment;
    
    public Row(int spacing, FlexSizeBehaviour sizeBehaviour, Iterable<UIComponent> children, LayoutSetting alignment) {
        this.children = children;
        this.spacing = spacing;
        this.sizeBehaviour = sizeBehaviour;
        this.alignment = alignment;
    }
    
    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        
        Vector2i size;
        
        if (sizeBehaviour == FlexSizeBehaviour.MIN) {
            size = Vector2i.zero();

            for (UIComponent child : children) {
                Vector2i childSize = UIBuilder.build(Layout.MAX, child, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
                size.x += childSize.x + spacing;
                size.y = Math.max(size.y, childSize.y);
            }
        } else {
            size = new Vector2i(layout.getWidth(), layout.getHeight());
        }

        Layout childrenLayout = new Layout(size.x, size.y, layout.getPosition(Axis.HORIZONTAL, size.x), layout.getPosition(Axis.VERTICAL, size.y));
        childrenLayout.pushLayoutSetting(Axis.VERTICAL, alignment);
        
        for (UIComponent child : children) {
            Vector2i childSize = UIBuilder.build(childrenLayout, child, renderables, dynamicUIComponents, eventListeners);
            childrenLayout.pushOffset(Axis.HORIZONTAL, childSize.x + spacing);
        }

        return size;
    }


    public static final class Builder {
        private int spacing;
        private FlexSizeBehaviour sizeBehaviour;
        private LayoutSetting alignment;

        public Builder() {
        }
        
        public Builder withSpacing(int spacing) {
            this.spacing = spacing;
            return this;
        }

        public Builder withSizeBehaviour(FlexSizeBehaviour horizontalSizeBehaviour) {
            this.sizeBehaviour = horizontalSizeBehaviour;
            return this;
        }
        
        public Builder withAlignment(LayoutSetting alignment) {
            this.alignment = alignment;
            return this;
        }

        public Row build(UIComponent... children) {
            return build(Arrays.asList(children));
        }

        public Row build(Iterable<UIComponent> children) {
            return new Row(spacing,
                    sizeBehaviour == null
                            ? FlexSizeBehaviour.MIN
                            : sizeBehaviour,
                    children,
                    alignment == null ? LayoutSetting.CENTER : alignment);
        }
    }
}
