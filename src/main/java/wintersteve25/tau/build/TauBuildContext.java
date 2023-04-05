package wintersteve25.tau.build;

import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import org.apache.commons.lang3.NotImplementedException;
import wintersteve25.tau.components.base.DynamicUIComponent;

import java.util.List;

public interface TauBuildContext {
    default void addRenderable(IRenderable renderable) {
        throw new NotImplementedException("Renderables are not supported by this renderer");
    }

    default void addDynamic(DynamicUIComponent dynamicUIComponent) {
        throw new NotImplementedException("DynamicUIComponents are not supported by this renderer");
    }

    default void addEventListener(IGuiEventListener eventListener) {
        throw new NotImplementedException("IGuiEventListeners are not supported by this renderer");
    }

    default List<IRenderable> getRenderables() {
        throw new NotImplementedException("Renderables are not supported by this renderer");
    }

    default List<DynamicUIComponent> getDynamicUIComponents() {
        throw new NotImplementedException("DynamicUIComponents are not supported by this renderer");
    }

    default List<IGuiEventListener> getEventListeners() {
        throw new NotImplementedException("IGuiEventListeners are not supported by this renderer");
    }


//    default void addTooltip(IRenderable renderable) {
//        throw new NotImplementedException("Tooltips are not supported by this renderer");
//    }
}
