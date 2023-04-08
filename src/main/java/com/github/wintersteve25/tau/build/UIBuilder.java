package com.github.wintersteve25.tau.build;

import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.Vector2i;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;

import java.util.List;

public class UIBuilder {
    /**
     * @param layout The layout of this ui component. Used to position children components
     * @param uiComponent The ui component to build into a list of renderables
     * @param renderables The resulting list of renderables
     * @param dynamicUIComponents A list of DynamicUIComponents present in the UI
     * @return The size of the component
     */
    public static Vector2i build(Layout layout, UIComponent uiComponent, List<IRenderable> renderables, List<IRenderable> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        return build(layout, uiComponent, renderables, tooltips, dynamicUIComponents, eventListeners, Vector2i.zero());
    }

    private static Vector2i build(Layout layout, UIComponent uiComponent, List<IRenderable> renderables, List<IRenderable> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners, Vector2i size) {
        if (uiComponent instanceof IGuiEventListener) {
            eventListeners.add((IGuiEventListener) uiComponent);
        }

        if (uiComponent instanceof DynamicUIComponent) {
            DynamicUIComponent dynamicUIComponent = ((DynamicUIComponent)uiComponent);
            if (dynamicUIComponent.renderables == null) dynamicUIComponent.renderables = new DynamicUIComponent.DynamicChange<>();
            if (dynamicUIComponent.tooltips == null) dynamicUIComponent.tooltips = new DynamicUIComponent.DynamicChange<>();
            if (dynamicUIComponent.dynamicUIComponents == null) dynamicUIComponent.dynamicUIComponents = new DynamicUIComponent.DynamicChange<>();
            if (dynamicUIComponent.eventListeners == null) dynamicUIComponent.eventListeners = new DynamicUIComponent.DynamicChange<>();

            if (dynamicUIComponent.renderables.startIndex == -1) dynamicUIComponent.renderables.startIndex = renderables.size();
            if (dynamicUIComponent.tooltips.startIndex == -1) dynamicUIComponent.tooltips.startIndex = tooltips.size();
            if (dynamicUIComponent.dynamicUIComponents.startIndex == -1) dynamicUIComponent.dynamicUIComponents.startIndex = dynamicUIComponents.size();
            if (dynamicUIComponent.eventListeners.startIndex == -1) dynamicUIComponent.eventListeners.startIndex = eventListeners.size();

            dynamicUIComponent.layout = layout.copy();
            
            dynamicUIComponents.add(dynamicUIComponent);
        }
        
        if (uiComponent instanceof PrimitiveUIComponent) {
            PrimitiveUIComponent primitiveUIComponent = (PrimitiveUIComponent) uiComponent;
            size.add(primitiveUIComponent.build(layout, renderables, tooltips, dynamicUIComponents, eventListeners));
        }
        
        UIComponent next = uiComponent.build(layout);

        if (next == null) {
            finishDynamicUIComponent(uiComponent, renderables, tooltips, eventListeners, dynamicUIComponents);
            return size;
        }

        Vector2i resultSize = build(layout, next, renderables, tooltips, dynamicUIComponents, eventListeners, size);
        finishDynamicUIComponent(uiComponent, renderables, tooltips, eventListeners, dynamicUIComponents);
        return resultSize;
    }
    
    private static void finishDynamicUIComponent(UIComponent uiComponent, List<IRenderable> renderables, List<IRenderable> tooltips, List<IGuiEventListener> eventListeners, List<DynamicUIComponent> dynamicUIComponents) {
        if (uiComponent instanceof DynamicUIComponent) {
            DynamicUIComponent dynamicUIComponent = (DynamicUIComponent) uiComponent;

            if(dynamicUIComponent.renderables.endIndex == -1) dynamicUIComponent.renderables.endIndex = renderables.size();
            if(dynamicUIComponent.tooltips.endIndex == -1) dynamicUIComponent.tooltips.endIndex = tooltips.size();
            if(dynamicUIComponent.dynamicUIComponents.endIndex == -1) dynamicUIComponent.dynamicUIComponents.endIndex = dynamicUIComponents.size();
            if(dynamicUIComponent.eventListeners.endIndex == -1) dynamicUIComponent.eventListeners.endIndex = eventListeners.size();

            if (dynamicUIComponent.renderables.data == null) dynamicUIComponent.renderables.data = renderables;
            if (dynamicUIComponent.tooltips.data == null) dynamicUIComponent.tooltips.data = tooltips;
            if (dynamicUIComponent.eventListeners.data == null) dynamicUIComponent.eventListeners.data = eventListeners;
            if (dynamicUIComponent.dynamicUIComponents.data == null) dynamicUIComponent.dynamicUIComponents.data = dynamicUIComponents;
        }
    }
}