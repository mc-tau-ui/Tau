package com.github.wintersteve25.tau.components;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.Size;
import com.github.wintersteve25.tau.build.UIBuilder;
import com.github.wintersteve25.tau.utils.Vector2i;

import java.util.ArrayList;
import java.util.List;

public final class Clip implements PrimitiveUIComponent {
    
    private final UIComponent child;
    private final Vector2i offset;
    private final Size size;
    
    public Clip(UIComponent child, Vector2i offset, Size size) {
        this.child = child;
        this.offset = offset;
        this.size = size;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {

        List<IRenderable> childrenRenderables = new ArrayList<>();
        Vector2i childSize = UIBuilder.build(layout, child, childrenRenderables, dynamicUIComponents, eventListeners);

        MainWindow window = Minecraft.getInstance().getWindow();

        Vector2i scaledClipSize = size.get(childSize);
        double guiScale = window.getGuiScale();

        int unscaledHeight = (int) Math.ceil(layout.getHeight() * guiScale);
        int unscaledClipWidth = (int) Math.ceil(scaledClipSize.x * guiScale);
        int unscaledClipHeight = (int) Math.ceil(scaledClipSize.y * guiScale);

        Vector2i scissorPosition = layout.getPosition(childSize);
        scissorPosition.add(offset);
        scissorPosition = new Vector2i((int)(scissorPosition.x * guiScale), -(int)(scissorPosition.y * guiScale));
        Vector2i finalScissorPosition = scissorPosition;
        
        renderables.add((pMatrixStack, pMouseX, pMouseY, pPartialTicks) -> {
            RenderSystem.enableScissor(finalScissorPosition.x, Math.abs((unscaledHeight - unscaledClipHeight) + finalScissorPosition.y), unscaledClipWidth, unscaledClipHeight);

            for (IRenderable renderable : childrenRenderables) {
                renderable.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
            }
        
            RenderSystem.disableScissor(); 
        });
        
        return childSize;
    }

    public static final class Builder {
        private Vector2i offset;
        private Size size;

        public Builder() {
        }

        public Builder withOffset(Vector2i offset) {
            this.offset = offset;
            return this;
        }

        public Builder withSize(Size size) {
            this.size = size;
            return this;
        }

        public Clip build(UIComponent child) {
            return new Clip(child, offset == null ? Vector2i.zero() : offset, size == null ? Size.percentage(1f) : size);
        }
    }
}
