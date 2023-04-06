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
        Vector2i scaledPosition = layout.getPosition(childSize);
        scaledPosition.add(offset);
        
        double guiScale = window.getGuiScale();

        int glX = (int) (scaledPosition.x * guiScale);
        int glY = (int) ((window.getGuiScaledHeight() - (scaledPosition.y + scaledClipSize.y)) * guiScale);
        int glWidth = (int) (scaledClipSize.x * guiScale);
        int glHeight = (int) (scaledClipSize.y * guiScale);
        
        renderables.add((pMatrixStack, pMouseX, pMouseY, pPartialTicks) -> {
            RenderSystem.enableScissor(glX, glY, glWidth, glHeight);

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
