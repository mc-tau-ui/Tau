package wintersteve25.tau.components;

import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import wintersteve25.tau.components.base.DynamicUIComponent;
import wintersteve25.tau.components.base.PrimitiveUIComponent;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.UIBuilder;
import wintersteve25.tau.utils.Vector2i;
import wintersteve25.tau.utils.transformations.Transformation;

import java.util.ArrayList;
import java.util.List;

public final class Transform implements PrimitiveUIComponent {
    
    private final UIComponent child;
    private final Transformation[] transformations;

    public Transform(UIComponent child, Transformation... transformations) {
        this.child = child;
        this.transformations = transformations;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables, List<DynamicUIComponent> dynamicUIComponents, List<IGuiEventListener> eventListeners) {
        
        List<IRenderable> children = new ArrayList<>();
        Vector2i size = UIBuilder.build(layout, child, children, dynamicUIComponents, eventListeners);
        renderables.add((pMatrixStack, pMouseX, pMouseY, pPartialTicks) -> {
            pMatrixStack.pushPose();
            
            for (Transformation transformation : transformations) {
                transformation.transform(pMatrixStack);
            }
            
            for (IRenderable renderable : children) {
                renderable.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
            }
            
            pMatrixStack.popPose();
        });
        
        return size;
    }
}