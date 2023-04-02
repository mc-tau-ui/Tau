package wintersteve25.tau.utils;

import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import wintersteve25.tau.components.base.DynamicUIComponent;
import wintersteve25.tau.components.base.PrimitiveUIComponent;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;

import java.util.List;

public class UIBuilder {
    /**
     * @param layout The layout of this ui component. Used to position children components
     * @param uiComponent The ui component to build into a list of renderables
     * @param renderables The resulting list of renderables
     * @param dynamicUIComponents A list of DynamicUIComponents present in the UI
     * @return The size of the component
     */
    public static Vector2i build(Layout layout, UIComponent uiComponent, List<IRenderable> renderables, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        return build(layout, uiComponent, renderables, dynamicUIComponents, eventListeners, Vector2i.zero());
    }

    private static Vector2i build(Layout layout, UIComponent uiComponent, List<IRenderable> renderables, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners, Vector2i size) {
        if (uiComponent instanceof IGuiEventListener) {
            eventListeners.add((IGuiEventListener) uiComponent);
        }
        
        if (uiComponent instanceof PrimitiveUIComponent) {
            PrimitiveUIComponent primitiveUIComponent = (PrimitiveUIComponent) uiComponent;
            size.add(primitiveUIComponent.build(layout, renderables, dynamicUIComponents, eventListeners));
        }

        if (uiComponent instanceof DynamicUIComponent) {
            DynamicUIComponent dynamicUIComponent = ((DynamicUIComponent)uiComponent);
            if (dynamicUIComponent.renderables == null) dynamicUIComponent.renderables = new DynamicUIComponent.DynamicChange<>();
            if (dynamicUIComponent.dynamicUIComponents == null) dynamicUIComponent.dynamicUIComponents = new DynamicUIComponent.DynamicChange<>();
            if (dynamicUIComponent.eventListeners == null) dynamicUIComponent.eventListeners = new DynamicUIComponent.DynamicChange<>();
            
            dynamicUIComponent.renderables.startIndex = renderables.size();
            dynamicUIComponent.dynamicUIComponents.startIndex = dynamicUIComponents.size();
            dynamicUIComponent.eventListeners.startIndex = eventListeners.size();
            
            dynamicUIComponents.add(dynamicUIComponent);
        }
        
        UIComponent next = uiComponent.build(layout);

        if (next == null) {
            return size;
        }

        Vector2i resultSize = build(layout, next, renderables, dynamicUIComponents, eventListeners, size);
        
        if (uiComponent instanceof DynamicUIComponent) {
            DynamicUIComponent dynamicUIComponent = (DynamicUIComponent) uiComponent; 
            
            dynamicUIComponent.renderables.endIndex = renderables.size();
            dynamicUIComponent.dynamicUIComponents.endIndex = dynamicUIComponents.size();
            dynamicUIComponent.eventListeners.endIndex = eventListeners.size();

            if (dynamicUIComponent.renderables.data == null) dynamicUIComponent.renderables.data = renderables;
            if (dynamicUIComponent.eventListeners.data == null) dynamicUIComponent.eventListeners.data = eventListeners;
            if (dynamicUIComponent.dynamicUIComponents.data == null) dynamicUIComponent.dynamicUIComponents.data = dynamicUIComponents;
        }
        
        return resultSize;
    }
}