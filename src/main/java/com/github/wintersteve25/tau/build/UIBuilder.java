package wintersteve25.tau.build;

import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import wintersteve25.tau.components.base.DynamicUIComponent;
import wintersteve25.tau.components.base.PrimitiveUIComponent;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.Vector2i;

import java.awt.*;
import java.util.ArrayList;
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

        if (uiComponent instanceof DynamicUIComponent) {
            DynamicUIComponent dynamicUIComponent = ((DynamicUIComponent)uiComponent);
            if (dynamicUIComponent.renderables == null) dynamicUIComponent.renderables = new DynamicUIComponent.DynamicChange<>();
            if (dynamicUIComponent.dynamicUIComponents == null) dynamicUIComponent.dynamicUIComponents = new DynamicUIComponent.DynamicChange<>();
            if (dynamicUIComponent.eventListeners == null) dynamicUIComponent.eventListeners = new DynamicUIComponent.DynamicChange<>();

            if (dynamicUIComponent.renderables.startIndex == -1) dynamicUIComponent.renderables.startIndex = renderables.size();
            if (dynamicUIComponent.dynamicUIComponents.startIndex == -1) dynamicUIComponent.dynamicUIComponents.startIndex = dynamicUIComponents.size();
            if (dynamicUIComponent.eventListeners.startIndex == -1) dynamicUIComponent.eventListeners.startIndex = eventListeners.size();

            dynamicUIComponent.layout = layout.copy();
            
            dynamicUIComponents.add(dynamicUIComponent);
        }
        
        if (uiComponent instanceof PrimitiveUIComponent) {
            PrimitiveUIComponent primitiveUIComponent = (PrimitiveUIComponent) uiComponent;
            size.add(primitiveUIComponent.build(layout, renderables, dynamicUIComponents, eventListeners));
        }
        
        UIComponent next = uiComponent.build(layout);

        if (next == null) {
            finishDynamicUIComponent(uiComponent, renderables, eventListeners, dynamicUIComponents);
            return size;
        }

        Vector2i resultSize = build(layout, next, renderables, dynamicUIComponents, eventListeners, size);
        finishDynamicUIComponent(uiComponent, renderables, eventListeners, dynamicUIComponents);
        return resultSize;
    }
    
    private static void finishDynamicUIComponent(UIComponent uiComponent, List<IRenderable> renderables, List<IGuiEventListener> eventListeners, List<DynamicUIComponent> dynamicUIComponents) {
        if (uiComponent instanceof DynamicUIComponent) {
            DynamicUIComponent dynamicUIComponent = (DynamicUIComponent) uiComponent;

            if(dynamicUIComponent.renderables.endIndex == -1) dynamicUIComponent.renderables.endIndex = renderables.size();
            if(dynamicUIComponent.dynamicUIComponents.endIndex == -1) dynamicUIComponent.dynamicUIComponents.endIndex = dynamicUIComponents.size();
            if(dynamicUIComponent.eventListeners.endIndex == -1) dynamicUIComponent.eventListeners.endIndex = eventListeners.size();

            if (dynamicUIComponent.renderables.data == null) dynamicUIComponent.renderables.data = renderables;
            if (dynamicUIComponent.eventListeners.data == null) dynamicUIComponent.eventListeners.data = eventListeners;
            if (dynamicUIComponent.dynamicUIComponents.data == null) dynamicUIComponent.dynamicUIComponents.data = dynamicUIComponents;
        }
    }
}