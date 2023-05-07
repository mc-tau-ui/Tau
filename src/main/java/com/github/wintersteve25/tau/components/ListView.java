package com.github.wintersteve25.tau.components;

import com.github.wintersteve25.tau.theme.Theme;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Axis;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.layout.LayoutSetting;
import com.github.wintersteve25.tau.build.UIBuilder;
import com.github.wintersteve25.tau.utils.Vector2i;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public Vector2i build(Layout layout, Theme theme, List<IRenderable> renderables, List<IRenderable> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
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
            Vector2i childSize = UIBuilder.build(layout.copy(), theme, children.get(i), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

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
            Vector2i childSize = UIBuilder.build(childrenLayout, theme, children.get(i), childrenRenderables, tooltips, dynamicUIComponents, eventListeners);
            childrenLayout.pushOffset(Axis.VERTICAL, childSize.y + spacing);
        }
        
        return UIBuilder.build(
            layout,
            theme,
            new Clip.Builder()
                .build(new Renderable((pMatrixStack, pMouseX, pMouseY, pPartialTicks) -> {
                    for (IRenderable renderable : childrenRenderables) {
                        renderable.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
                    }
                })),
            renderables, 
            tooltips,
            dynamicUIComponents,
            eventListeners
        );
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        if (!canScrollDown && pDelta < 0) return IGuiEventListener.super.mouseScrolled(pMouseX, pMouseY, pDelta);
        if (!canScrollUp && pDelta > 0) return IGuiEventListener.super.mouseScrolled(pMouseX, pMouseY, pDelta);
        scrollOffset += pDelta > 0 ? -1 : 1;
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
            return build(Arrays.asList(children));
        }

        public ListView build(List<UIComponent> children) {
            return new ListView(children, childrenAlignment == null ? LayoutSetting.CENTER : childrenAlignment, spacing);
        }
    }
}