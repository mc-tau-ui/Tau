package wintersteve25.tau.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;
import wintersteve25.tau.Tau;
import wintersteve25.tau.utils.UIBuilder;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;

import java.util.ArrayList;
import java.util.List;

public class ScreenUIRenderer extends Screen {
    
    private final UIComponent uiComponent;
    private final List<IRenderable> components;
    
    public ScreenUIRenderer(UIComponent uiComponent) {
        super(StringTextComponent.EMPTY);
        this.uiComponent = uiComponent;
        this.components = new ArrayList<>();
    }

    @Override
    protected void init() {
        Layout layout = new Layout(width, height);
        components.clear();
        UIBuilder.build(layout, uiComponent, components);
    }

    @Override
    public void render(MatrixStack matrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
        this.renderBackground(matrixStack);
        
        for (IRenderable component : components) {
            component.render(matrixStack, pMouseX, pMouseY, pPartialTicks);
        }
    }
}
