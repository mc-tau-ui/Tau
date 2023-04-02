package wintersteve25.tau.components.base;

import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.UIBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * A UI component that can be updated on demand
 */
public abstract class DynamicUIComponent implements UIComponent {
    
    // DO NOT MODIFY THESE!
    public DynamicChange<IRenderable> renderables;
    public DynamicChange<DynamicUIComponent> dynamicUIComponents;
    public DynamicChange<IGuiEventListener> eventListeners;
    
    protected void rebuild(Layout layout) {
        List<IRenderable> replacementRenderables = new ArrayList<>();
        List<DynamicUIComponent> replacementDynamicUIComponents = new ArrayList<>();
        List<IGuiEventListener> replacementEventListeners = new ArrayList<>();
        
        UIBuilder.build(layout, this, replacementRenderables, replacementDynamicUIComponents, replacementEventListeners);
        
        replacementRenderables = new ArrayList<>(replacementRenderables);
        replacementDynamicUIComponents = new ArrayList<>(replacementDynamicUIComponents);
        replacementEventListeners = new ArrayList<>(replacementEventListeners);

        int componentCountDiff = renderables.update(replacementRenderables);
        
        // update the indexes if the number of component has changed
        if (componentCountDiff != 0) {
            for (DynamicUIComponent dynamicUIComponent : dynamicUIComponents.data) {
                dynamicUIComponent.renderables.shift(componentCountDiff);
            }
        }
        
        componentCountDiff = eventListeners.update(replacementEventListeners);

        // update the indexes if the number of component has changed
        if (componentCountDiff != 0) {
            for (DynamicUIComponent dynamicUIComponent : dynamicUIComponents.data) {
                dynamicUIComponent.eventListeners.shift(componentCountDiff);
            }
        }

        componentCountDiff = dynamicUIComponents.update(replacementDynamicUIComponents);

        // update the indexes if the number of component has changed
        if (componentCountDiff != 0) {
            for (DynamicUIComponent dynamicUIComponent : dynamicUIComponents.data) {
                dynamicUIComponent.dynamicUIComponents.shift(componentCountDiff);
            }
        }
    }
    
    public void tick() {
    }
    
    public static class DynamicChange<T> {
        public int startIndex;
        public int endIndex;
        public List<T> data;

        public int update(List<T> replacement) {
            data.subList(startIndex, endIndex).clear();
            data.addAll(startIndex, replacement);
            return replacement.size() - (endIndex - startIndex);
        } 
        
        public void shift(int amount) {
            startIndex += amount;
            endIndex += amount;
        }
    }
}