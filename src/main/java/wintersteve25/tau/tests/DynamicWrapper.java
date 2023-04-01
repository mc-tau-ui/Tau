package wintersteve25.tau.tests;

import wintersteve25.tau.components.base.DynamicUIComponent;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;

public class DynamicWrapper extends DynamicUIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new TextPanel();
    }
}
