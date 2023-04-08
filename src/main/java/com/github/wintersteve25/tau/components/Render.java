package com.github.wintersteve25.tau.components;

import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.layout.Axis;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.RenderProvider;
import com.github.wintersteve25.tau.utils.Vector2i;

import java.util.List;

public final class Render implements PrimitiveUIComponent {
    private final RenderProvider renderer;

    public Render(RenderProvider renderer) {
        this.renderer = renderer;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables, List<IRenderable> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        int width = layout.getWidth();
        int height = layout.getHeight();
        int x = layout.getPosition(Axis.HORIZONTAL, width);
        int y = layout.getPosition(Axis.VERTICAL, height);
        renderables.add((pMatrixStack, pMouseX, pMouseY, pPartialTicks) -> renderer.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks, x, y, width, height));
        return layout.getSize();
    }
}
