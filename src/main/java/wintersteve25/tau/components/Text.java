package wintersteve25.tau.components;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import wintersteve25.tau.components.base.PrimitiveUIComponent;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.utils.Color;
import wintersteve25.tau.layout.Axis;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.Vector2i;

import java.util.List;

public class Text implements PrimitiveUIComponent {
    
    private final ITextComponent text;
    private final Color color;
    private final OverflowBehaviour overflowBehaviour;
    
    public Text(ITextComponent text, Color color, OverflowBehaviour overflowBehaviour) {
        this.text = text;
        this.color = color;
        this.overflowBehaviour = overflowBehaviour;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables) {
        int width = Minecraft.getInstance().font.width(text);
        int height = overflowBehaviour == OverflowBehaviour.WRAP ?
                Minecraft.getInstance().font.wordWrapHeight(text.getContents(), layout.getMaximumLength(Axis.HORIZONTAL)) :
                9; // constant for line height in minecraft
        int x = layout.getPosition(Axis.HORIZONTAL, width);
        int y = layout.getPosition(Axis.VERTICAL, height);

        renderables.add((pMatrixStack, pMouseX, pMouseY, pPartialTicks) -> render(pMatrixStack, x, y, width));
    
        return new Vector2i(width, height);
    }

    private void render(MatrixStack matrixStack, int xPosition, int yPosition, int width) {
        FontRenderer fontRenderer = Minecraft.getInstance().font;

        switch (overflowBehaviour) {
            case OVERFLOW:
                fontRenderer.drawShadow(matrixStack, text.getString(), xPosition, yPosition, color.getAARRGGBB(), color.hasTransparency());
                break;
            case WRAP:
                fontRenderer.drawWordWrap(text, xPosition, yPosition, width, color.getAARRGGBB());
                break;
            case CLIP:
                fontRenderer.drawShadow(matrixStack, fontRenderer.substrByWidth(text, width).getString(), xPosition, yPosition, color.getAARRGGBB(), color.hasTransparency());
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
    }
}
