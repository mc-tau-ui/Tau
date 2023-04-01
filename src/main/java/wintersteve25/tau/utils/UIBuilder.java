package wintersteve25.tau.utils;

import net.minecraft.client.gui.IRenderable;
import wintersteve25.tau.components.base.DynamicUIComponent;
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
        return build(layout, uiComponent, renderables, new ArrayList<>(), Vector2i.zero());
    }

    private static Vector2i build(Layout layout, UIComponent uiComponent, List<IRenderable> renderables, List<DynamicUIComponent> dynamicUIComponents, Vector2i size) {
        if (uiComponent instanceof PrimitiveUIComponent) {
            PrimitiveUIComponent primitiveUIComponent = (PrimitiveUIComponent) uiComponent;
            size.add(primitiveUIComponent.build(layout, renderables));
        }

        if (uiComponent instanceof DynamicUIComponent) {
            ((DynamicUIComponent)uiComponent).start = renderables.size() - 1;
        }
        
        UIComponent next = uiComponent.build(layout);

        if (next == null) {
            return size;
        }

        Vector2i resultSize = build(layout, next, renderables, dynamicUIComponents, size);
        
        if (uiComponent instanceof DynamicUIComponent) {
            ((DynamicUIComponent)uiComponent).end = renderables.size();
            ((DynamicUIComponent)uiComponent).renderables = renderables;
            ((DynamicUIComponent)uiComponent).dynamicUIComponents = dynamicUIComponents;
        }
        
        return resultSize;
    }
}