package wintersteve25.tau.components;

import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import wintersteve25.tau.components.base.DynamicUIComponent;
import wintersteve25.tau.components.base.PrimitiveUIComponent;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Axis;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.layout.LayoutSetting;
import wintersteve25.tau.build.UIBuilder;
import wintersteve25.tau.utils.Vector2i;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ListView extends DynamicUIComponent implements PrimitiveUIComponent, IGuiEventListener {
    
    private final List<UIComponent> children;
    private final LayoutSetting childrenAlignment;
    private final int spacing;
    
    private int scrollOffset;
    private Vector2i position;
    private Vector2i size;
    private boolean canScrollDown;
    private boolean canScrollUp;
    
    public ListView(List<UIComponent> children, LayoutSetting childrenAlignment, int spacing) {
        this.children = children;
        this.childrenAlignment = childrenAlignment;
        this.spacing = spacing;
        scrollOffset = 0;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        Vector2i childrenSize = Vector2i.zero();
        size = layout.getSize();
        
        int childrensCanFit = -1;
        
        if (scrollOffset < 0) {
            scrollOffset = 0;
        }
        if ((scrollOffset + childrensCanFit) >= children.size()) {
            scrollOffset = children.size() - childrensCanFit;
        }
        
        for (int i = scrollOffset; i < children.size(); i++) {
            Vector2i childSize = UIBuilder.build(Layout.MAX, children.get(i), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            childrenSize.y += childSize.y + spacing;
            childrenSize.x = Math.max(childrenSize.x, childSize.x);
            
            if (childrenSize.y > size.y) {
                childrensCanFit = i + 1;
                break;
            }
        }

        canScrollDown = childrensCanFit != -1;
        canScrollUp = scrollOffset > 0;
        
        position = layout.getPosition(size);
        Layout childrenLayout = new Layout(size.x, size.y, position.x, position.y);
        childrenLayout.pushLayoutSetting(Axis.HORIZONTAL, childrenAlignment);
        List<IRenderable> childrenRenderables = new ArrayList<>();
        
        for (int i = scrollOffset; i < (childrensCanFit == -1 ? children.size() : childrensCanFit); i++) {
            Vector2i childSize = UIBuilder.build(childrenLayout, children.get(i), childrenRenderables, dynamicUIComponents, eventListeners);
            childrenLayout.pushOffset(Axis.VERTICAL, childSize.y + spacing);
        }
        
        return UIBuilder.build(
            layout,
            new Clip.Builder()
                .build(new Renderable((pMatrixStack, pMouseX, pMouseY, pPartialTicks) -> {
                    for (IRenderable renderable : childrenRenderables) {
                        renderable.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
                    }
                })),
            renderables,
            dynamicUIComponents,
            eventListeners
        );
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        if (!canScrollDown && pDelta < 0) return IGuiEventListener.super.mouseScrolled(pMouseX, pMouseY, pDelta);
        if (!canScrollUp && pDelta > 0) return IGuiEventListener.super.mouseScrolled(pMouseX, pMouseY, pDelta);
        scrollOffset -= (int) pDelta;
        rebuild();
        return true;
    }

    @Override
    public boolean isMouseOver(double pMouseX, double pMouseY) {
        return Vector2i.within((int) pMouseX, (int) pMouseY, position, size);
    }

    public static final class Builder {
        private int spacing;
        private LayoutSetting childrenAlignment;

        public Builder() {
        }
        
        public Builder withSpacing(int spacing) {
            this.spacing = spacing;
            return this;
        }

        public Builder withAlignment(LayoutSetting alignment) {
            childrenAlignment = alignment;
            return this;
        }
        
        public ListView build(UIComponent... children) {
            return new ListView(Arrays.stream(children).collect(Collectors.toList()), childrenAlignment == null ? LayoutSetting.CENTER : childrenAlignment, spacing);
        }
    }
}