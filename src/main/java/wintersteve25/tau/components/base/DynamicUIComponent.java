package wintersteve25.tau.components.base;

import net.minecraft.client.gui.IRenderable;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.UIBuilder;

import java.util.List;

/**
 * A UI component that can be updated on demand
 */
public abstract class DynamicUIComponent implements UIComponent {
    
    // DO NOT MODIFY THESE!
    public List<IRenderable> renderables;
    public List<DynamicUIComponent> dynamicUIComponents;
    public int start;
    public int end;

    protected void rebuild(Layout layout) {
        List<IRenderable> replacement = UIBuilder.build(layout, this);
        renderables.subList(start, end).clear();
        renderables.addAll(start, replacement);
        
        // update the indexes if the number of component has changed
        int componentCountDiff = replacement.size() - (end - start);
        if (componentCountDiff != 0) {
            for (DynamicUIComponent dynamicUIComponent : dynamicUIComponents) {
                dynamicUIComponent.start += componentCountDiff;
                dynamicUIComponent.end += componentCountDiff;
            }
        }
    }
}