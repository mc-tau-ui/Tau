package com.github.wintersteve25.tau.components;

import com.github.wintersteve25.tau.build.UIBuilder;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Axis;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.theme.Theme;
import com.github.wintersteve25.tau.utils.InteractableState;
import com.github.wintersteve25.tau.utils.Vector2i;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public final class Button implements PrimitiveUIComponent, IGuiEventListener {

    private final Consumer<Integer> onPress;
    private final UIComponent child;
    
    private int width;
    private int height;
    private int x;
    private int y;

    public Button(Consumer<Integer> onPress, UIComponent child) {
        this.onPress = onPress;
        this.child = child;
    }

    @Override
    public Vector2i build(Layout layout, Theme theme, List<IRenderable> renderables, List<IRenderable> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        width = layout.getWidth();
        height = layout.getHeight();
        x = layout.getPosition(Axis.HORIZONTAL, width);
        y = layout.getPosition(Axis.VERTICAL, height);

        renderables.add((pMatrixStack, pMouseX, pMouseY, pPartialTicks) -> theme.drawButton(pMatrixStack, x, y, width, height, pPartialTicks, pMouseX, pMouseY, this.getInteractableState(pMouseX, pMouseY)));
        UIBuilder.build(layout, theme, child, renderables, tooltips, dynamicUIComponents, eventListeners);

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
    
    private InteractableState getInteractableState(int pMouseX, int pMouseY) {
        if (onPress == null) {
            return InteractableState.DISABLED;
        } else if (isHovered(pMouseX, pMouseY)) {
            return InteractableState.HOVERED;
        }
        
        return InteractableState.IDLE;
    }

    public static final class Builder {
        private Consumer<Integer> onPress;

        public Builder() {
        }
        
        public Builder withOnPress(Consumer<Integer> onPress) {
            this.onPress = onPress;
            return this;
        }

        public Button build(UIComponent child) {
            return new Button(onPress, child);
        }
    }
}
