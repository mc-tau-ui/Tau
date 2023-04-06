package wintersteve25.tau.components;

import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import wintersteve25.tau.Tau;
import wintersteve25.tau.components.base.DynamicUIComponent;
import wintersteve25.tau.components.base.PrimitiveUIComponent;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Axis;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.Size;
import wintersteve25.tau.build.UIBuilder;
import wintersteve25.tau.utils.Vector2i;

import java.util.List;

public final class Sized implements PrimitiveUIComponent {

    private final Size size;
    private final UIComponent child;

    public Sized(Size size, UIComponent child) {
        this.size = size;
        this.child = child;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        Vector2i componentSize = size.get(layout.getSize());
        
        if (componentSize.outside(layout.getSize())) {
            Tau.LOGGER.error("Sized UIComponent has a size greater than the amount of size available");
            return layout.getSize();
        }
        
        Layout childLayout = new Layout(
                componentSize.x, 
                componentSize.y,
                layout.getPosition(Axis.HORIZONTAL, componentSize.x),
                layout.getPosition(Axis.VERTICAL, componentSize.y)
        );

        UIBuilder.build(childLayout, child, renderables, dynamicUIComponents, eventListeners);
        return componentSize;
    }
}