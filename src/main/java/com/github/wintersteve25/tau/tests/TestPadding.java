package wintersteve25.tau.tests;

import wintersteve25.tau.components.Align;
import wintersteve25.tau.components.Padding;
import wintersteve25.tau.components.Text;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.layout.LayoutSetting;
import wintersteve25.tau.utils.Pad;

public class TestPadding implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Align.Builder()
                .withVertical(LayoutSetting.START)
                .withHorizontal(LayoutSetting.END)
                .build(new Padding(
                    new Pad.Builder().withRight(10).withTop(10).build(), 
                    new Text.Builder("Top right corner")));
    }
}
