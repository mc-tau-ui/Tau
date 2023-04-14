package com.github.wintersteve25.tau.components;

import com.github.wintersteve25.tau.utils.FlexSizeBehaviour;
import net.minecraft.client.gui.AbstractGui;
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
    private final Color color;
    private final boolean drawColor;
    private final FlexSizeBehaviour sizeBehaviour;
    
    public Container(UIComponent child, Color color, boolean drawColor, FlexSizeBehaviour sizeBehaviour) {
        this.child = child;
        this.color = color;
        this.drawColor = drawColor;
        this.sizeBehaviour = sizeBehaviour;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables, List<IRenderable> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        if (child == null && !drawColor) {
            return layout.getSize();
        }

        Vector2i size = layout.getSize();
        List<IRenderable> renderables1 = new ArrayList<>();
        List<IRenderable> tooltips1 = new ArrayList<>();
        List<DynamicUIComponent> dynamicUIComponents1 = new ArrayList<>();
        List<IGuiEventListener> eventListeners1 = new ArrayList<>();
        
        if (child != null) {
            if (sizeBehaviour == FlexSizeBehaviour.MIN) {
                size = UIBuilder.build(layout, child, renderables1, tooltips1, dynamicUIComponents1, eventListeners1);
            } else {
                UIBuilder.build(layout, child, renderables1, tooltips1, dynamicUIComponents1, eventListeners1);
            }
        }

        if (drawColor) {
            Color actualColor = color == null ? layout.getColorScheme().backgroundColor() : color;
            
            int width = size.x;
            int height = size.y;
            int x = layout.getPosition(Axis.HORIZONTAL, width);
            int y = layout.getPosition(Axis.VERTICAL, height);

            renderables.add((pMatrixStack, pMouseX, pMouseY, pPartialTicks) -> AbstractGui.fill(
                    pMatrixStack, 
                    x,
                    y,
                    x + width,
                    y + height,
                    actualColor.getAARRGGBB()
            ));
        }
        
        renderables.addAll(renderables1);
        tooltips.addAll(tooltips1);
        dynamicUIComponents.addAll(dynamicUIComponents1);
        eventListeners.addAll(eventListeners1);
        
        return size;
    }


    public static final class Builder implements UIComponent {
        private UIComponent child;
        private Color color;
        private boolean drawColor = true;
        private FlexSizeBehaviour sizeBehaviour;
        
        public Builder() {
        }

        public Builder withChild(UIComponent child) {
            this.child = child;
            return this;
        }

        public Builder withColor(Color color) {
            this.color = color;
            return this;
        }
        
        public Builder noColor() {
            this.drawColor = false;
            return this;
        }

        public Builder withSizeBehaviour(FlexSizeBehaviour sizeBehaviour) {
            this.sizeBehaviour = sizeBehaviour;
            return this;
        }

        public Container build() {
            return new Container(child, color, drawColor, sizeBehaviour == null ? FlexSizeBehaviour.MAX : sizeBehaviour);
        }

        @Override
        public UIComponent build(Layout layout) {
            return build();
        }
    }
}
