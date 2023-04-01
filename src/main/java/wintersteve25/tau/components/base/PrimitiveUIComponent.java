package wintersteve25.tau.components.base;

import net.minecraft.client.gui.IRenderable;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.Vector2i;

import java.util.List;

/**
 * A UI Component with access to lower level content. For more can be used for more complicated components that need to be customly rendered
 */
public interface PrimitiveUIComponent extends UIComponent {
    Vector2i build(Layout layout, List<IRenderable> renderables);
    
    @Override
    default UIComponent build(Layout layout) {
        return null;
    }
}
