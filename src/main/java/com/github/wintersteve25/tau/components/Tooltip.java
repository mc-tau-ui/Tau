package com.github.wintersteve25.tau.components;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.util.text.ITextProperties;
import net.minecraftforge.fml.client.gui.GuiUtils;
import com.github.wintersteve25.tau.build.UIBuilder;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Axis;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.RenderProvider;
import com.github.wintersteve25.tau.utils.Vector2i;

import java.util.ArrayList;
import java.util.List;

public final class Tooltip implements PrimitiveUIComponent, RenderProvider {
    
    private final List<ITextProperties> text;
    private final UIComponent child;
    
    private int screenWidth;
    private int screenHeight;
    private FontRenderer fontRenderer;

    public Tooltip(List<ITextProperties> text, UIComponent child) {
        this.text = text;
        this.child = child;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        Minecraft minecraft = Minecraft.getInstance();
        fontRenderer = minecraft.font;
        MainWindow window = minecraft.getWindow();
        screenWidth = window.getGuiScaledWidth();
        screenHeight = window.getGuiScaledHeight();
        
        Vector2i size = UIBuilder.build(layout, child, renderables, dynamicUIComponents, eventListeners);
        Layout childLayout = new Layout(size.x, size.y, layout.getPosition(Axis.HORIZONTAL, size.x), layout.getPosition(Axis.VERTICAL, size.y));
        UIBuilder.build(childLayout, new Render(this), renderables, dynamicUIComponents, eventListeners);
        return size;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks, int x, int y, int width, int height) {
        if (Vector2i.within(mouseX, mouseY, x, y, width, height)) {
            GuiUtils.drawHoveringText(matrixStack, text, mouseX, mouseY, screenWidth, screenHeight, -1, fontRenderer);
        }
    }

    public static final class Builder {
        private List<ITextProperties> text;

        public Builder() {
            text = new ArrayList<>();
        }

        public Builder withTexts(List<ITextProperties> text) {
            this.text = text;
            return this;
        }
        
        public Builder withText(ITextProperties text) {
            this.text.add(text);
            return this;
        }

        public Tooltip build(UIComponent child) {
            return new Tooltip(text, child);
        }
    }
}
