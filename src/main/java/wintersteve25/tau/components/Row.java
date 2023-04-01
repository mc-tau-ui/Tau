package wintersteve25.tau.components;

import net.minecraft.client.gui.IRenderable;
import wintersteve25.tau.components.base.PrimitiveUIComponent;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Axis;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.UIBuilder;
import wintersteve25.tau.utils.Vector2i;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Row implements PrimitiveUIComponent {
    
    private final List<UIComponent> children;
    private final int spacing;
    
    public Row(int spacing, List<UIComponent> children) {
        this.children = children;
        this.spacing = spacing;
    }

    public Row(int spacing, UIComponent... children) {
        this.children = Arrays.stream(children).collect(Collectors.toList());
        this.spacing = spacing;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables) {
        
        Vector2i size = Vector2i.zero();
        List<IRenderable> childrenRenderables = new ArrayList<>();
        Layout childrenLayout = new Layout(layout.getWidth(), layout.getHeight());
        
        for (UIComponent child : children) {
            Vector2i childSize = UIBuilder.build(childrenLayout, child, childrenRenderables);
            childSize.x += spacing;
            size.add(childSize);
            childrenLayout.pushOffset(Axis.HORIZONTAL, childSize.x);
        }

        UIBuilder.build(layout, new CombinedRenderables(childrenRenderables, size), renderables);
        
        return size;
    }
}
