package wintersteve25.tau.tests;

import wintersteve25.tau.components.Align;
import wintersteve25.tau.components.Padding;
import wintersteve25.tau.components.Text;
import wintersteve25.tau.components.base.DynamicUIComponent;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.layout.LayoutSetting;
import wintersteve25.tau.utils.Pad;

public class TestDynamic extends DynamicUIComponent {
    private int t;

    @Override
    public void tick() {
        t++;
        if (layout == null) return;
        rebuild();
    }

    @Override
    public UIComponent build(Layout layout) {
        return new Align.Builder()
                .withVertical(LayoutSetting.START)
                .withHorizontal(LayoutSetting.END)
                .build(new Padding(new Pad.Builder().withRight(10).withTop(10).build(), new Text.Builder(String.valueOf(t))));
    }
}