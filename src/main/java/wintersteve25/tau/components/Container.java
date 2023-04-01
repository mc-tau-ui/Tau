package wintersteve25.tau.components;

import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IRenderable;
import wintersteve25.tau.components.base.PrimitiveUIComponent;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Axis;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.Color;
import wintersteve25.tau.utils.UIBuilder;
import wintersteve25.tau.utils.Vector2i;

import java.util.List;

public class Container implements PrimitiveUIComponent {
    
    private final UIComponent child;
    private final Color color;
    
    public Container(UIComponent child, Color color) {
        this.child = child;
        this.color = color;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables) {
        if (child == null && color == null) {
            return layout.getSize();
        }

        if (color != null) {
            int width = layout.getWidth();
            int height = layout.getHeight();
            int x = layout.getPosition(Axis.HORIZONTAL, width);
            int y = layout.getPosition(Axis.VERTICAL, height);

            renderables.add((pMatrixStack, pMouseX, pMouseY, pPartialTicks) -> AbstractGui.fill(
                    pMatrixStack, 
                    x,
                    y,
                    x + width,
                    y + height,
                    0xFFFFFFFF
            ));
        }
        
        if (child != null) {
            UIBuilder.build(layout, child, renderables);
        }
        
        return layout.getSize();
    }


    public static final class Builder implements UIComponent {
        private UIComponent child;
        private Color color;

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

        public Container build() {
            return new Container(child, color);
        }

        @Override
        public UIComponent build(Layout layout) {
            return build();
        }
    }
}
