package wintersteve25.tau.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IRenderable;
import wintersteve25.tau.components.base.DynamicUIComponent;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.UIBuilder;

import java.util.ArrayList;
import java.util.List;

public class HudUIRenderer {
    private final UIComponent uiComponent;
    private final List<IRenderable> components;
    private final List<DynamicUIComponent> dynamicUIComponents;

    private boolean built;
    private int screenWidth;
    private int screenHeight;

    public HudUIRenderer(UIComponent uiComponent) {
        this.uiComponent = uiComponent;
        this.components = new ArrayList<>();
        this.dynamicUIComponents = new ArrayList<>();
    }

    private void init() {
        Layout layout = new Layout(screenWidth, screenHeight);

        components.clear();
        dynamicUIComponents.clear();

        UIBuilder.build(layout, uiComponent, components, dynamicUIComponents, new ArrayList<>());

        built = true;
    }

    private void tick() {
        if (!built) return;

        for (DynamicUIComponent dynamicUIComponent : dynamicUIComponents) {
            dynamicUIComponent.tick();
        }
    }
    
    public void render(MainWindow mainWindow, MatrixStack matrixStack, float pPartialTicks) {
        int width = mainWindow.getGuiScaledWidth();
        int height = mainWindow.getGuiScaledHeight();
        
        tick();
        for (IRenderable component : components) {
            component.render(matrixStack, 0, 0, pPartialTicks);
        }
        
        if (width != screenWidth || height != screenHeight) {
            screenWidth = width;
            screenHeight = height;
            init();
        }
    }
}
