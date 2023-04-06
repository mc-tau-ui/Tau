package wintersteve25.tau.tests;

import wintersteve25.tau.components.Align;
import wintersteve25.tau.components.Text;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.layout.LayoutSetting;

public class TestAlign implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Align.Builder()
                .withVertical(LayoutSetting.END)
                .withHorizontal(LayoutSetting.CENTER)
                .build(new Text.Builder("Whats up?"));
    }
}
