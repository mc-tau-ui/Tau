package com.github.wintersteve25.tau.components.base;

import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.build.UIBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * A UI component that can be updated on demand
 */
public abstract class DynamicUIComponent implements UIComponent {
    
    // DO NOT MODIFY THESE!
    public Layout layout;
    public DynamicChange<IRenderable> renderables;
    public DynamicChange<DynamicUIComponent> dynamicUIComponents;
    public DynamicChange<IGuiEventListener> eventListeners;
    
    protected void rebuild() {
        List<IRenderable> replacementRenderables = new ArrayList<>();
        List<DynamicUIComponent> replacementDynamicUIComponents = new ArrayList<>();
        List<IGuiEventListener> replacementEventListeners = new ArrayList<>();
        
        UIBuilder.build(layout, this, replacementRenderables, replacementDynamicUIComponents, replacementEventListeners);
        
        replacementRenderables = new ArrayList<>(replacementRenderables);
        replacementDynamicUIComponents = new ArrayList<>(replacementDynamicUIComponents);
        replacementEventListeners = new ArrayList<>(replacementEventListeners);

        int renderablesCountDiff = renderables.update(replacementRenderables);
        int eventListenersCountDiff = eventListeners.update(replacementEventListeners);
        int dynamicCountDiff = dynamicUIComponents.update(replacementDynamicUIComponents);

        dynamicUIComponents.shift(dynamicCountDiff);

        for (DynamicUIComponent dynamicUIComponent : dynamicUIComponents.data) {
            dynamicUIComponent.renderables.shift(renderablesCountDiff);
            dynamicUIComponent.eventListeners.shift(eventListenersCountDiff);
            dynamicUIComponent.dynamicUIComponents.shift(dynamicCountDiff);
        }
    }
    
    public void tick() {
    }
    
    public final static class DynamicChange<T> {
        public int startIndex = -1;
        public int endIndex = -1;
        public List<T> data;

        public int update(List<T> replacement) {
            data.subList(startIndex, endIndex).clear();
            data.addAll(startIndex, replacement);
            return replacement.size() - (endIndex - startIndex);
        } 
        
        public void shift(int amount) {
            endIndex += amount;
        }
    }
}