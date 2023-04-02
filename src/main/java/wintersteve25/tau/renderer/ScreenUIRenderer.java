package wintersteve25.tau.renderer;

import com.google.common.base.Stopwatch;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;
import wintersteve25.tau.components.base.DynamicUIComponent;
import wintersteve25.tau.utils.UIBuilder;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ScreenUIRenderer extends Screen {
    
    private final UIComponent uiComponent;
    private final List<IRenderable> components;
    private final List<DynamicUIComponent> dynamicUIComponents;
    
    private boolean built;
    
    public ScreenUIRenderer(UIComponent uiComponent) {
        super(StringTextComponent.EMPTY);
        this.uiComponent = uiComponent;
        this.components = new ArrayList<>();
        this.dynamicUIComponents = new ArrayList<>();
    }

    @Override
    protected void init() {
        Layout layout = new Layout(width, height);
        
        components.clear();
        dynamicUIComponents.clear();
        
        UIBuilder.build(layout, uiComponent, components, dynamicUIComponents, children);
        
        built = true;
    }

    @Override
    public void tick() {
        if (!built) return;
        
        for (DynamicUIComponent dynamicUIComponent : dynamicUIComponents) {
            dynamicUIComponent.tick();
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
        this.renderBackground(matrixStack);
        
        for (IRenderable component : components) {
            component.render(matrixStack, pMouseX, pMouseY, pPartialTicks);
        }
    }
}
