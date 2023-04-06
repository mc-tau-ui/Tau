package com.github.wintersteve25.tau.components;

import com.github.wintersteve25.tau.build.UIBuilder;
import com.github.wintersteve25.tau.utils.RenderProvider;
import com.github.wintersteve25.tau.utils.Size;
import com.mojang.blaze3d.matrix.MatrixStack;

import com.sun.jna.platform.win32.Wdm;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.utils.Color;
import com.github.wintersteve25.tau.layout.Axis;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.Vector2i;

import java.util.List;

public final class Text implements PrimitiveUIComponent, RenderProvider {
    
    private static int ellipsisWidth = 0;
    
    private final ITextComponent text;
    private final Color color;
    private final OverflowBehaviour overflowBehaviour;
    
    public Text(ITextComponent text, Color color, OverflowBehaviour overflowBehaviour) {
        this.text = text;
        this.color = color;
        this.overflowBehaviour = overflowBehaviour;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        FontRenderer fontRenderer = Minecraft.getInstance().font;
        int width = fontRenderer.width(text);
        ellipsisWidth = fontRenderer.width("...");
        
        boolean willOverflow = width > layout.getWidth();
        if (willOverflow) {
            width = layout.getWidth();
        }
        
        int height = willOverflow && overflowBehaviour == OverflowBehaviour.WRAP ?
                fontRenderer.wordWrapHeight(text.getContents(), width) :
                9; // constant for line height in minecraft
        
        int x = layout.getPosition(Axis.HORIZONTAL, width);
        int y = layout.getPosition(Axis.VERTICAL, height);

        int finalWidth = width;
        if (overflowBehaviour != OverflowBehaviour.CLIP) {
            renderables.add((pMatrixStack, pMouseX, pMouseY, pPartialTicks) -> render(pMatrixStack, pMouseX, pMouseY, pPartialTicks, x, y, finalWidth, height));
        } else {
            UIBuilder.build(
                new Layout(width, height, x, y),
                new Clip.Builder()
                    .build(new Render(this)), 
                renderables,
                dynamicUIComponents, 
                eventListeners
            );
        }
        
        return new Vector2i(width, height);
    }
    
    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks, int x, int y, int width, int height) {
        FontRenderer fontRenderer = Minecraft.getInstance().font;

        switch (overflowBehaviour) {
            case WRAP:
                fontRenderer.drawWordWrap(text, x, y, width, color.getAARRGGBB());
                break;
            case ELLIPSIS:
                fontRenderer.drawShadow(matrixStack, fontRenderer.substrByWidth(text, width - ellipsisWidth).getString() + "...", x, y, color.getAARRGGBB(), color.hasTransparency());
                break;
            default:
                fontRenderer.drawShadow(matrixStack, text.getString(), x, y, color.getAARRGGBB(), color.hasTransparency());
                break;
        }
    }

    public static final class Builder implements UIComponent {
        private final ITextComponent text;
        private Color color;
        private OverflowBehaviour overflowBehaviour;

        public Builder(ITextComponent text) {
            this.text = text;
        }
        
        public Builder(String text) {
            this.text = new StringTextComponent(text);
        }

        public Builder withColor(Color color) {
            this.color = color;
            return this;
        }

        public Builder withOverflowBehaviour(OverflowBehaviour overflowBehaviour) {
            this.overflowBehaviour = overflowBehaviour;
            return this;
        }

        public Text build() {
            return new Text(
                    text, 
                    color == null ? Color.WHITE : color, 
                    overflowBehaviour == null ? OverflowBehaviour.OVERFLOW : overflowBehaviour
            );
        }

        @Override
        public UIComponent build(Layout layout) {
            return build();
        }
    }

    public enum OverflowBehaviour {
        OVERFLOW,
        WRAP,
        CLIP,
        ELLIPSIS
    }
}
