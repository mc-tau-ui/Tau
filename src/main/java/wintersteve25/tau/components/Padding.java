package wintersteve25.tau.components;

import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import wintersteve25.tau.components.base.DynamicUIComponent;
import wintersteve25.tau.components.base.PrimitiveUIComponent;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Axis;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.Pad;
import wintersteve25.tau.utils.UIBuilder;
import wintersteve25.tau.utils.Vector2i;

import java.util.List;

public class Padding implements PrimitiveUIComponent {

    private final Pad pad;
    private final UIComponent child;
    
    public Padding(Pad pad, UIComponent child) {
        this.pad = pad;
        this.child = child;
    }
    
    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        if (pad == null) {
            return UIBuilder.build(layout, child, renderables, dynamicUIComponents, eventListeners);
        }
        
        layout.pushOffset(Axis.HORIZONTAL, pad.left - pad.right);
        layout.pushOffset(Axis.VERTICAL, pad.top - pad.bottom);
        Vector2i size = UIBuilder.build(layout, child, renderables, dynamicUIComponents, eventListeners);
        layout.popOffset(Axis.VERTICAL);
        layout.popOffset(Axis.HORIZONTAL);
        size.add(pad.getSize());
        
        return size;
    }
}