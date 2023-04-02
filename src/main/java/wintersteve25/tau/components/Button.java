package wintersteve25.tau.components;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.widget.Widget;
import wintersteve25.tau.components.base.DynamicUIComponent;
import wintersteve25.tau.components.base.PrimitiveUIComponent;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Axis;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.UIBuilder;
import wintersteve25.tau.utils.Vector2i;

import java.util.List;

public final class Button implements PrimitiveUIComponent, IGuiEventListener {

    private final Runnable onPress;
    private final UIComponent child;
    
    private int width;
    private int height;
    private int x;
    private int y;

    public Button(Runnable onPress, UIComponent child) {
        this.onPress = onPress;
        this.child = child;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        width = layout.getWidth();
        height = layout.getHeight();
        x = layout.getPosition(Axis.HORIZONTAL, width);
        y = layout.getPosition(Axis.VERTICAL, height);
        Minecraft minecraft = Minecraft.getInstance();
        
        renderables.add((pMatrixStack, pMouseX, pMouseY, pPartialTicks) -> {
            minecraft.getTextureManager().bind(Widget.WIDGETS_LOCATION);
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            int i = this.getYImage(pMouseX, pMouseY);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();
            AbstractGui.blit(pMatrixStack, x, y, 0, 0, 46 + i * 20, width / 2, height, 256, 256);
            AbstractGui.blit(pMatrixStack, x + width / 2, y, 0, 200 - (float) width / 2, 46 + i * 20, width / 2, height, 256, 256);
        });
        
        UIBuilder.build(layout, child, renderables, dynamicUIComponents, eventListeners);
        
        return layout.getSize();
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (isHovered((int) pMouseX, (int)  pMouseY)) {
            onPress.run();
            return true;
        }
        
        return false;
    }

    private boolean isHovered(int pMouseX, int pMouseY) {
        return pMouseX > x && pMouseX < x + width && pMouseY > y && pMouseY < y + height;
    }
    
    private int getYImage(int pMouseX, int pMouseY) {
        int i = 1;
        if (onPress == null) {
            i = 0;
        } else if (isHovered(pMouseX, pMouseY)) {
            i = 2;
        }

        return i;
    }


    public static final class Builder {
        private Runnable onPress;

        public Builder() {
        }
        
        public Builder withOnPress(Runnable onPress) {
            this.onPress = onPress;
            return this;
        }

        public Button build(UIComponent child) {
            return new Button(onPress, child);
        }
    }
}
