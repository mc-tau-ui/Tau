package com.github.wintersteve25.tau.components.base;

import com.github.wintersteve25.tau.theme.Theme;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.components.Renderable;
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
    public Theme theme;
    
    public DynamicChange<Renderable> renderables;
    public DynamicChange<Renderable> tooltips;
    public DynamicChange<DynamicUIComponent> dynamicUIComponents;
    public DynamicChange<GuiEventListener> eventListeners;
    public boolean dirty;
    
    protected void rebuild() {
        dirty = true;
    }
    
    public final void rebuildImmediately() {
        List<Renderable> replacementRenderables = new ArrayList<>();
        List<Renderable> replacementTooltips = new ArrayList<>();
        List<DynamicUIComponent> replacementDynamicUIComponents = new ArrayList<>();
        List<GuiEventListener> replacementEventListeners = new ArrayList<>();

        UIBuilder.build(layout, theme, this, replacementRenderables, replacementTooltips, replacementDynamicUIComponents, replacementEventListeners);

        replacementRenderables = new ArrayList<>(replacementRenderables);
        replacementTooltips = new ArrayList<>(replacementTooltips);
        replacementDynamicUIComponents = new ArrayList<>(replacementDynamicUIComponents);
        replacementEventListeners = new ArrayList<>(replacementEventListeners);

        int renderablesCountDiff = renderables.update(replacementRenderables);
        int tooltipsCountDiff = tooltips.update(replacementTooltips);
        int eventListenersCountDiff = eventListeners.update(replacementEventListeners);
        int dynamicCountDiff = dynamicUIComponents.update(replacementDynamicUIComponents);

        this.renderables.endIndex += renderablesCountDiff;
        this.tooltips.endIndex += tooltipsCountDiff;
        this.eventListeners.endIndex += eventListenersCountDiff;
        this.dynamicUIComponents.endIndex += dynamicCountDiff;
        
        for (DynamicUIComponent component : dynamicUIComponents.data) {
            if (component.renderables.startIndex > this.renderables.endIndex) {
                component.renderables.startIndex += renderablesCountDiff;
                component.renderables.endIndex += renderablesCountDiff;
            }
            if (component.tooltips.startIndex > this.tooltips.endIndex) {
                component.tooltips.startIndex += tooltipsCountDiff;
                component.tooltips.endIndex += tooltipsCountDiff;
            }
            if (component.eventListeners.startIndex > this.eventListeners.endIndex) {
                component.eventListeners.startIndex += eventListenersCountDiff;
                component.eventListeners.endIndex += eventListenersCountDiff;
            }
            if (component.dynamicUIComponents.startIndex > this.dynamicUIComponents.endIndex) {
                component.dynamicUIComponents.startIndex += dynamicCountDiff;
                component.dynamicUIComponents.endIndex += dynamicCountDiff;
            }
        }
    }
    
    public void tick() {
    }
    
    public void destroy() {
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
    }
}