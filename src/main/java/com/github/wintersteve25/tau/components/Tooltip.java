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
import net.minecraft.client.gui.components.Widget;
import net.minecraft.network.chat.FormattedText;
import net.minecraftforge.client.gui.GuiUtils;

import java.util.ArrayList;
import java.util.List;

public final class Tooltip implements PrimitiveUIComponent {

    private final List<FormattedText> text;
    private final UIComponent child;
    
    public Tooltip(List<FormattedText> text, UIComponent child) {
        this.text = text;
        this.child = child;
    }

    @Override
    public Vector2i build(Layout layout, Theme theme, List<Widget> renderables, List<Widget> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<GuiEventListener> eventListeners) {
        Minecraft minecraft = Minecraft.getInstance();
        Font fontRenderer = minecraft.font;
        Window window = minecraft.getWindow();
        int screenWidth = window.getGuiScaledWidth();
        int screenHeight = window.getGuiScaledHeight();
        
        Vector2i size = UIBuilder.build(layout, theme, child, renderables, tooltips, dynamicUIComponents, eventListeners);
        Vector2i position = layout.getPosition(size);
        
        tooltips.add((pPoseStack, pMouseX, pMouseY, pPartialTicks) -> {
            if (Vector2i.within(pMouseX, pMouseY, position.x, position.y, size.x, size.y)) {
                theme.drawTooltip(pPoseStack, pMouseX, pMouseY, screenWidth, screenHeight, fontRenderer, text);
            } 
        });

        return size;
    }
    
    public static final class Builder {
        private final List<FormattedText> text;

        public Builder() {
            text = new ArrayList<>();
        }

        public Builder withText(List<FormattedText> text) {
            this.text.addAll(text);
            return this;
        }
        
        public Builder withText(FormattedText text) {
            this.text.add(text);
            return this;
        }

        public Tooltip build(UIComponent child) {
            return new Tooltip(text, child);
        }
    }
}