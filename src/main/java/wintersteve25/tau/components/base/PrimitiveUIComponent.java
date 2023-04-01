package wintersteve25.tau.components.base;

import net.minecraft.client.gui.IRenderable;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.Vector2i;

import java.util.List;

public interface PrimitiveUIComponent extends UIComponent {
    Vector2i build(Layout layout, List<IRenderable> renderables);
    
    @Override
    default UIComponent build(Layout layout) {
        return null;
    }
}
