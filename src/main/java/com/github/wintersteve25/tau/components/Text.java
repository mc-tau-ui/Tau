package com.github.wintersteve25.tau.components;

import com.github.wintersteve25.tau.build.UIBuilder;
import com.github.wintersteve25.tau.theme.Theme;
import com.github.wintersteve25.tau.utils.RenderProvider;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.components.Widget;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.utils.Color;
import com.github.wintersteve25.tau.layout.Axis;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.Vector2i;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.TextComponent;

import java.util.List;

public final class Text implements PrimitiveUIComponent, RenderProvider {

    private static int ellipsisWidth = 0;

    private final FormattedText text;
    private final OverflowBehaviour overflowBehaviour;
    private Color color;

    public Text(FormattedText text, Color color, OverflowBehaviour overflowBehaviour) {
        this.text = text;
        this.color = color;
        this.overflowBehaviour = overflowBehaviour;
    }

    @Override
    public Vector2i build(Layout layout, Theme theme, List<Widget> renderables, List<Widget> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<GuiEventListener> eventListeners) {
        color = color == null ? theme.getTextColor() : color;

        Font fontRenderer = Minecraft.getInstance().font;
        int width = fontRenderer.width(text);
        ellipsisWidth = fontRenderer.width("...");

        boolean willOverflow = width > layout.getWidth();
        if (willOverflow) {
            width = layout.getWidth();
        }

        int height = willOverflow && overflowBehaviour == OverflowBehaviour.WRAP ?
                fontRenderer.wordWrapHeight(text.getString(), width) :
                9; // constant for line height in minecraft

        int x = layout.getPosition(Axis.HORIZONTAL, width);
        int y = layout.getPosition(Axis.VERTICAL, height);

        int finalWidth = width;
        if (overflowBehaviour != OverflowBehaviour.CLIP) {
            renderables.add((pPoseStack, pMouseX, pMouseY, pPartialTicks) -> render(pPoseStack, pMouseX, pMouseY, pPartialTicks, x, y, finalWidth, height));
        } else {
            UIBuilder.build(
                    new Layout(width, height, x, y),
                    theme,
                    new Clip.Builder().build(new Render(this)),
                    renderables,
                    tooltips,
                    dynamicUIComponents,
                    eventListeners
            );
        }

        return new Vector2i(width, height);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks, int x, int y, int width, int height) {
        Font fontRenderer = Minecraft.getInstance().font;

        switch (overflowBehaviour) {
            case WRAP -> fontRenderer.drawWordWrap(text, x, y, width, color.getAARRGGBB());
            case ELLIPSIS -> fontRenderer.drawShadow(poseStack, fontRenderer.substrByWidth(text, width - ellipsisWidth).getString() + "...", x, y, color.getAARRGGBB(), color.hasTransparency());
            default -> fontRenderer.drawShadow(poseStack, text.getString(), x, y, color.getAARRGGBB(), color.hasTransparency());
        }
    }

    public static final class Builder implements UIComponent {
        private final Component text;
        private Color color;
        private OverflowBehaviour overflowBehaviour;

        public Builder(Component text) {
            this.text = text;
        }

        public Builder(String text) {
            this.text = new TextComponent(text);
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
                    color,
                    overflowBehaviour == null ? OverflowBehaviour.OVERFLOW : overflowBehaviour
            );
        }

        @Override
        public UIComponent build(Layout layout, Theme theme) {
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