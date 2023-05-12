package com.github.wintersteve25.tau.components;

import com.github.wintersteve25.tau.build.UIBuilder;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.theme.Theme;
import com.github.wintersteve25.tau.utils.Color;
import com.github.wintersteve25.tau.utils.Vector2i;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.util.text.ITextProperties;

import java.util.ArrayList;
import java.util.List;

public final class Tooltip implements PrimitiveUIComponent {

    private final List<ITextProperties> text;
    private final UIComponent child;
    private final Color color;
    private final Color borderStart;
    private final Color borderEnd;

    public Tooltip(List<ITextProperties> text, UIComponent child, Color color, Color borderStart, Color borderEnd) {
        this.text = text;
        this.child = child;
        this.color = color;
        this.borderStart = borderStart;
        this.borderEnd = borderEnd;
    }

    @Override
    public Vector2i build(Layout layout, Theme theme, List<Renderable> renderables, List<Renderable> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<GuiEventListener> eventListeners) {
        Minecraft minecraft = Minecraft.getInstance();
        Font fontRenderer = minecraft.font;
        Window window = minecraft.getWindow();
        int screenWidth = window.getGuiScaledWidth();
        int screenHeight = window.getGuiScaledHeight();
        
        Vector2i size = UIBuilder.build(layout, theme, child, renderables, tooltips, dynamicUIComponents, eventListeners);
        Vector2i position = layout.getPosition(size);
        
        Color actualColor = color == null ? theme.getTooltipColor() : color;
        Color actualBorder = borderStart == null ? theme.getTooltipBorderStartColor() : borderStart;
        Color actualBorderEnd = borderEnd == null ? theme.getTooltipBorderEndColor() : borderEnd;
        
        tooltips.add((pPoseStack, pMouseX, pMouseY, pPartialTicks) -> {
            if (Vector2i.within(pMouseX, pMouseY, position.x, position.y, size.x, size.y)) {
                GuiUtils.drawHoveringText(pPoseStack, text, pMouseX, pMouseY, screenWidth, screenHeight, -1, actualColor.getAARRGGBB(), actualBorder.getAARRGGBB(), actualBorderEnd.getAARRGGBB(), fontRenderer);
            } 
        });
        
        return size;
    }

    public static final class Builder {
        private final List<ITextProperties> text;
        private Color color;
        private Color borderStart;
        private Color borderEnd;

        public Builder() {
            text = new ArrayList<>();
        }

        public Builder withText(List<ITextProperties> text) {
            this.text.addAll(text);
            return this;
        }
        
        public Builder withText(ITextProperties text) {
            this.text.add(text);
            return this;
        }

        public Builder withColor(Color color) {
            this.color = color;
            return this;
        }

        public Builder withBorderStart(Color borderStart) {
            this.borderStart = borderStart;
            return this;
        }

        public Builder withBorderEnd(Color borderEnd) {
            this.borderEnd = borderEnd;
            return this;
        }

        public Tooltip build(UIComponent child) {
            return new Tooltip(text, child, color, borderStart, borderEnd);
        }
    }
}
