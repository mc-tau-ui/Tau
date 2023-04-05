package wintersteve25.tau.build;

import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import wintersteve25.tau.components.base.DynamicUIComponent;

import java.util.ArrayList;
import java.util.List;

public class ScreenBuildContext implements TauBuildContext {
    private final List<IRenderable> renderables;
    private final List<DynamicUIComponent> dynamicUIComponents;
    private final List<IGuiEventListener> eventListeners;
    
    public ScreenBuildContext(List<IGuiEventListener> eventListeners) {
        this.eventListeners = eventListeners;
        this.renderables = new ArrayList<>();
        this.dynamicUIComponents = new ArrayList<>();
    }

    @Override
    public void addRenderable(IRenderable renderable) {
        renderables.add(renderable);
    }

    @Override
    public void addDynamic(DynamicUIComponent dynamicUIComponent) {
        dynamicUIComponents.add(dynamicUIComponent);
    }

    @Override
    public void addEventListener(IGuiEventListener eventListener) {
        eventListeners.add(eventListener);
    }

    @Override
    public List<IRenderable> getRenderables() {
        return renderables;
    }

    @Override
    public List<DynamicUIComponent> getDynamicUIComponents() {
        return dynamicUIComponents;
    }

    @Override
    public List<IGuiEventListener> getEventListeners() {
        return eventListeners;
    }
    
    public void clear() {
        renderables.clear();
        dynamicUIComponents.clear();
        eventListeners.clear();
    }
}