package com.github.wintersteve25.tau.components;

import com.github.wintersteve25.tau.build.UIBuilder;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Axis;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.Color;
import com.github.wintersteve25.tau.utils.Vector2i;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.util.SoundEvents;

import java.util.List;
import java.util.function.Consumer;

public final class FlatButton implements PrimitiveUIComponent, IGuiEventListener {

    private final Consumer<Integer> onPress;
    private final UIComponent child;
    
    private final Color disabledColor;
    private final Color hoveredColor;
    private final Color normalColor;

    private int width;
    private int height;
    private int x;
    private int y;

    public FlatButton(Consumer<Integer> onPress, UIComponent child, Color disabledColor, Color hoveredColor, Color normalColor) {
        this.onPress = onPress;
        this.child = child;
        this.disabledColor = disabledColor;
        this.hoveredColor = hoveredColor;
        this.normalColor = normalColor;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables, List<IRenderable> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        width = layout.getWidth();
        height = layout.getHeight();
        x = layout.getPosition(Axis.HORIZONTAL, width);
        y = layout.getPosition(Axis.VERTICAL, height);

        renderables.add((pMatrixStack, pMouseX, pMouseY, pPartialTicks) -> {
            Color color;
            
            if (onPress == null) {
                color = disabledColor == null ? layout.getColorScheme().interactableDisabledColor() : disabledColor;
            } else if (isHovered(pMouseX, pMouseY)) {
                color = hoveredColor == null ? layout.getColorScheme().interactableHoverColor() : hoveredColor;
            } else {
                color = normalColor == null ? layout.getColorScheme().interactableColor() : normalColor;
            }

            AbstractGui.fill(pMatrixStack, x, y, x + width, y + height, color.getAARRGGBB());
        });

        UIBuilder.build(layout, child, renderables, tooltips, dynamicUIComponents, eventListeners);

        return layout.getSize();
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (onPress != null && isHovered((int) pMouseX, (int) pMouseY)) {
            onPress.accept(pButton);
            playSound(SoundEvents.UI_BUTTON_CLICK);
            return true;
        }

        return false;
    }

    private boolean isHovered(int pMouseX, int pMouseY) {
        return pMouseX > x && pMouseX < x + width && pMouseY > y && pMouseY < y + height;
    }

    public static final class Builder {
        private Consumer<Integer> onPress;
        private Color disabledColor;
        private Color hoveredColor;
        private Color normalColor;

        public Builder() {
        }

        public Builder withOnPress(Consumer<Integer> onPress) {
            this.onPress = onPress;
            return this;
        }

        public Builder withDisabledColor(Color disabledColor) {
            this.disabledColor = disabledColor;
            return this;
        }

        public Builder withHoveredColor(Color hoveredColor) {
            this.hoveredColor = hoveredColor;
            return this;
        }

        public Builder withNormalColor(Color normalColor) {
            this.normalColor = normalColor;
            return this;
        }

        public FlatButton build(UIComponent child) {
            return new FlatButton(
                    onPress, 
                    child, 
                    disabledColor, 
                    hoveredColor,
                    normalColor
            );
        }
    }
}
