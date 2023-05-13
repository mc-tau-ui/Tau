package com.github.wintersteve25.tau.components;

import com.github.wintersteve25.tau.theme.Theme;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.components.Widget;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.build.UIBuilder;
import com.github.wintersteve25.tau.utils.Vector2i;
import com.github.wintersteve25.tau.utils.transformations.Transformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Transform implements PrimitiveUIComponent {
    
    private final UIComponent child;
    private final Iterable<Transformation> transformations;

    public Transform(UIComponent child, Transformation... transformations) {
        this(child, Arrays.asList(transformations));
    }

    public Transform(UIComponent child, Iterable<Transformation> transformations) {
        this.child = child;
        this.transformations = transformations;
    }

    @Override
    public Vector2i build(Layout layout, Theme theme, List<Widget> renderables, List<Widget> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<GuiEventListener> eventListeners) {
        
        List<Widget> children = new ArrayList<>();
        Vector2i size = UIBuilder.build(layout, theme, child, children, tooltips, dynamicUIComponents, eventListeners);
        renderables.add((pPoseStack, pMouseX, pMouseY, pPartialTicks) -> {
            pPoseStack.pushPose();
            
            for (Transformation transformation : transformations) {
                transformation.transform(pPoseStack);
            }
            
            for (Widget renderable : children) {
                renderable.render(pPoseStack, pMouseX, pMouseY, pPartialTicks);
            }
            
            pPoseStack.popPose();
        });
        
        return size;
    }
}