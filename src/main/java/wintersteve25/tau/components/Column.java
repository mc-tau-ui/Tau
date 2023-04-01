package wintersteve25.tau.components;

import net.minecraft.client.gui.IRenderable;
import wintersteve25.tau.components.base.PrimitiveUIComponent;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Axis;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.FlexSizeBehaviour;
import wintersteve25.tau.utils.UIBuilder;
import wintersteve25.tau.utils.Vector2i;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Column implements PrimitiveUIComponent {

    private final List<UIComponent> children;
    private final int spacing;
    private final FlexSizeBehaviour sizeBehaviour;

    public Column(int spacing, FlexSizeBehaviour sizeBehaviour, List<UIComponent> children) {
        this.children = children;
        this.spacing = spacing;
        this.sizeBehaviour = sizeBehaviour;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables) {

        Vector2i size;

        if (sizeBehaviour == FlexSizeBehaviour.MIN) {
            size = Vector2i.zero();

            for (UIComponent child : children) {
                Vector2i childSize = UIBuilder.build(new Layout(0, 0), child, new ArrayList<>());
                size.y += childSize.y + spacing;
                size.x = Math.max(size.x, childSize.x);
            }
        } else {
            size = new Vector2i(layout.getWidth(), layout.getHeight());
        }

        Layout childrenLayout = new Layout(size.x, size.y, layout.getPosition(Axis.HORIZONTAL, size.x), layout.getPosition(Axis.VERTICAL, size.y));

        for (UIComponent child : children) {
            Vector2i childSize = UIBuilder.build(childrenLayout, child, renderables);
            childrenLayout.pushOffset(Axis.VERTICAL, childSize.y + spacing);
        }

        return size;
    }


    public static final class Builder {
        private int spacing;
        private FlexSizeBehaviour sizeBehaviour;

        public Builder() {
        }

        public Builder withSpacing(int spacing) {
            this.spacing = spacing;
            return this;
        }

        public Builder withSizeBehaviour(FlexSizeBehaviour sizeBehaviour) {
            this.sizeBehaviour = sizeBehaviour;
            return this;
        }

        public Column build(UIComponent... children) {
            return new Column(spacing,
                    sizeBehaviour == null
                            ? FlexSizeBehaviour.MIN
                            : sizeBehaviour,
                    Arrays.stream(children).collect(Collectors.toList())
            );
        }
    }
}
