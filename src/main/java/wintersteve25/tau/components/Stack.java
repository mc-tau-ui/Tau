package wintersteve25.tau.components;

import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import wintersteve25.tau.components.base.DynamicUIComponent;
import wintersteve25.tau.components.base.PrimitiveUIComponent;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.UIBuilder;
import wintersteve25.tau.utils.Vector2i;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Stack implements PrimitiveUIComponent {
    private final List<UIComponent> children;

    public Stack(List<UIComponent> children) {
        this.children = children;
    }

    public Stack(UIComponent... children) {
        this.children = Arrays.stream(children).collect(Collectors.toList());
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        for (UIComponent child : children) {
            UIBuilder.build(layout, child, renderables, dynamicUIComponents, eventListeners);
        }
        
        return layout.getSize();
    }
}
