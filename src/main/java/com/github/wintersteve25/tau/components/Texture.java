package com.github.wintersteve25.tau.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.util.ResourceLocation;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.Vector2i;

import java.util.List;

public final class Texture implements PrimitiveUIComponent {

    private final ResourceLocation textureLocation;
    private final Vector2i textureSize;
    private final Vector2i uv;
    private final Vector2i uvSize;
    private final Vector2i size;

    public Texture(ResourceLocation textureLocation, Vector2i textureSize, Vector2i uv, Vector2i uvSize, Vector2i size) {
        this.textureLocation = textureLocation;
        this.textureSize = textureSize;
        this.uv = uv;
        this.uvSize = uvSize;
        this.size = size;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {

        Minecraft minecraft = Minecraft.getInstance();
        Vector2i position = layout.getPosition(uvSize);
        
        renderables.add((pMatrixStack, pMouseX, pMouseY, pPartialTicks) -> {
            minecraft.getTextureManager().bind(textureLocation);
            AbstractGui.blit(pMatrixStack, position.x, position.y, size.x, size.y, uv.x, uv.y, uvSize.x, uvSize.y, textureSize.x, textureSize.y);
        });
        
        return uvSize;
    }

    public static final class Builder {
        private Vector2i textureSize;
        private Vector2i uv;
        private Vector2i uvSize;
        private Vector2i size;

        public Builder() {
        }

        public Builder withTextureSize(Vector2i textureSize) {
            this.textureSize = textureSize;
            return this;
        }

        public Builder withUv(Vector2i uv) {
            this.uv = uv;
            return this;
        }

        public Builder withUvSize(Vector2i uvSize) {
            this.uvSize = uvSize;
            return this;
        }
        
        public Builder withSize(Vector2i size) {
            this.size = size;
            return this;
        }

        public Texture build(ResourceLocation textureLocation) {
            textureSize = textureSize == null ? new Vector2i(256, 256) : textureSize;
            uvSize = uvSize == null ? textureSize : uvSize;
            return new Texture(textureLocation, textureSize, uv == null ? Vector2i.zero() : uv, uvSize, size == null ? uvSize : size);
        }
    }
}
