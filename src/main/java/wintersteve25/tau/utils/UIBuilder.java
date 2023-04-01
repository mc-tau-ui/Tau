package wintersteve25.tau.utils;

import net.minecraft.client.gui.IRenderable;
import wintersteve25.tau.components.base.PrimitiveUIComponent;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;

import java.util.ArrayList;
import java.util.List;

public class UIBuilder {

    /**
     * @param layout The layout of this ui component. Used to position children components
     * @param uiComponent The ui component to build into a list of renderables
     * @return The resulting list of renderables
     */
    public static List<IRenderable> build(Layout layout, UIComponent uiComponent) {
        List<IRenderable> result = new ArrayList<>();
        build(layout, uiComponent, result);
        return result;
    }
    
    /**
     * @param layout The layout of this ui component. Used to position children components
     * @param uiComponent The ui component to build into a list of renderables
     * @param renderables The resulting list of renderables
     * @return The size of the component
     */
    public static Vector2i build(Layout layout, UIComponent uiComponent, List<IRenderable> renderables) {
        return build(layout, uiComponent, renderables, Vector2i.zero());
    }

    private static Vector2i build(Layout layout, UIComponent uiComponent, List<IRenderable> renderables, Vector2i size) {
        if (uiComponent instanceof PrimitiveUIComponent) {
            PrimitiveUIComponent primitiveUIComponent = (PrimitiveUIComponent) uiComponent;
            size.add(primitiveUIComponent.build(layout, renderables));
        }

        UIComponent next = uiComponent.build(layout);

        if (next == null) {
            return size;
        }

        return build(layout, next, renderables, size);
    }
}