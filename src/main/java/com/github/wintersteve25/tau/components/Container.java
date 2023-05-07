package com.github.wintersteve25.tau.components;

import com.github.wintersteve25.tau.theme.Theme;
import com.github.wintersteve25.tau.utils.FlexSizeBehaviour;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Axis;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.Color;
import com.github.wintersteve25.tau.build.UIBuilder;
import com.github.wintersteve25.tau.utils.Vector2i;

import java.util.ArrayList;
import java.util.List;

public final class Container implements PrimitiveUIComponent {
    
    private final UIComponent child;
    private final FlexSizeBehaviour sizeBehaviour;
    private final boolean drawBackground;
    
    public Container(UIComponent child, boolean drawBackground, FlexSizeBehaviour sizeBehaviour) {
        this.child = child;
        this.drawBackground = drawBackground;
        this.sizeBehaviour = sizeBehaviour;
    }

    @Override
    public Vector2i build(Layout layout, Theme theme, List<IRenderable> renderables, List<IRenderable> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        if (child == null && !drawBackground) {
            return layout.getSize();
        }

        Vector2i size = layout.getSize();
        List<IRenderable> renderables1 = new ArrayList<>();
        List<IRenderable> tooltips1 = new ArrayList<>();
        List<DynamicUIComponent> dynamicUIComponents1 = new ArrayList<>();
        List<IGuiEventListener> eventListeners1 = new ArrayList<>();
        
        if (child != null) {
            if (sizeBehaviour == FlexSizeBehaviour.MIN) {
                size = UIBuilder.build(layout, theme, child, renderables1, tooltips1, dynamicUIComponents1, eventListeners1);
            } else {
                UIBuilder.build(layout, theme, child, renderables1, tooltips1, dynamicUIComponents1, eventListeners1);
            }
        }

        if (drawBackground) {
            int width = size.x;
            int height = size.y;
            int x = layout.getPosition(Axis.HORIZONTAL, width);
            int y = layout.getPosition(Axis.VERTICAL, height);

            renderables.add((pMatrixStack, pMouseX, pMouseY, pPartialTicks) -> theme.drawContainer(pMatrixStack, x, y, width, height, pPartialTicks, pMouseX, pMouseY));
        }
        
        renderables.addAll(renderables1);
        tooltips.addAll(tooltips1);
        dynamicUIComponents.addAll(dynamicUIComponents1);
        eventListeners.addAll(eventListeners1);
        
        return size;
    }


    public static final class Builder implements UIComponent {
        private UIComponent child;
        private boolean drawBackground = true;
        private FlexSizeBehaviour sizeBehaviour;
        
        public Builder() {
        }

        public Builder withChild(UIComponent child) {
            this.child = child;
            return this;
        }

        public Builder noBackground() {
            this.drawBackground = false;
            return this;
        }

        public Builder withSizeBehaviour(FlexSizeBehaviour sizeBehaviour) {
            this.sizeBehaviour = sizeBehaviour;
            return this;
        }

        public Container build() {
            return new Container(child, drawBackground, sizeBehaviour == null ? FlexSizeBehaviour.MAX : sizeBehaviour);
        }

        @Override
        public UIComponent build(Layout layout, Theme theme) {
            return build();
        }
    }
}
