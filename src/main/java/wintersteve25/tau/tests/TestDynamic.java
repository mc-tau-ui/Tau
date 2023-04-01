package wintersteve25.tau.tests;

import wintersteve25.tau.components.Center;
import wintersteve25.tau.components.Text;
import wintersteve25.tau.components.base.DynamicUIComponent;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;

public class TestDynamic extends DynamicUIComponent {
    private int t;
    private Layout layout;
    
    @Override
    public void tick() {
        t++;
        if (layout == null) return;
        rebuild(layout);
    }

    @Override
    public UIComponent build(Layout layout) {
        this.layout = layout;
        return new Center(new Text.Builder(String.valueOf(t)));
    }
}
