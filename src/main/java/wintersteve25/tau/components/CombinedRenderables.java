package wintersteve25.tau.components;

import net.minecraft.client.gui.IRenderable;
import wintersteve25.tau.components.base.PrimitiveUIComponent;
import wintersteve25.tau.layout.Axis;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.Vector2i;

import java.util.List;

public class CombinedRenderables implements PrimitiveUIComponent {
    private final List<IRenderable> renderables;
    private final Vector2i size;

    public CombinedRenderables(List<IRenderable> renderables, Vector2i size) {
        this.renderables = renderables;
        this.size = size;
    }

    @Override
    public Vector2i build(Layout layout, List<IRenderable> renderables) {
        
        int x = layout.getPosition(Axis.HORIZONTAL, size.x);
        int y = layout.getPosition(Axis.VERTICAL, size.y);
        
        renderables.add((pMatrixStack, pMouseX, pMouseY, pPartialTicks) -> {
            pMatrixStack.pushPose();
            pMatrixStack.translate(x, y, 0);
            
            for (IRenderable child : this.renderables) {
                child.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
            }
            
            pMatrixStack.popPose();
        });
        
        return size;
    }
}
