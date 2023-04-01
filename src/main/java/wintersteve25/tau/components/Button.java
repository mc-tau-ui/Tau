package wintersteve25.tau.components;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.widget.Widget;
import wintersteve25.tau.components.base.PrimitiveUIComponent;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Axis;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.UIBuilder;
import wintersteve25.tau.utils.Vector2i;

import java.util.List;

public class Button implements PrimitiveUIComponent {

    private final Runnable onPress;
    private final UIComponent child;

    public Button(Runnable onPress, UIComponent child) {
        this.onPress = onPress;
        this.child = child;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables) {
        
        int width = layout.getWidth();
        int height = layout.getHeight();
        int x = layout.getPosition(Axis.HORIZONTAL, width);
        int y = layout.getPosition(Axis.VERTICAL, height);
        
        renderables.add((pMatrixStack, pMouseX, pMouseY, pPartialTicks) -> {
            Minecraft minecraft = Minecraft.getInstance();
            minecraft.getTextureManager().bind(Widget.WIDGETS_LOCATION);
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            int i = this.getYImage(pMouseX > x && pMouseX < x + width && pMouseY > y && pMouseY < y + height);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();
            AbstractGui.blit(pMatrixStack, x, y, 0, 0, 46 + i * 20, width / 2, height, 256, 256);
            AbstractGui.blit(pMatrixStack, x + width / 2, y, 0, 200 - (float) width / 2, 46 + i * 20, width / 2, height, 256, 256);
        });

        UIBuilder.build(layout, child, renderables);
        
        return null;
    }

    protected int getYImage(boolean pIsHovered) {
        int i = 1;
        if (onPress == null) {
            i = 0;
        } else if (pIsHovered) {
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
