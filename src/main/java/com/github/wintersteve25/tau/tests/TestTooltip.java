package wintersteve25.tau.tests;

import net.minecraft.util.text.StringTextComponent;
import wintersteve25.tau.components.Center;
import wintersteve25.tau.components.Text;
import wintersteve25.tau.components.Tooltip;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;

public class TestTooltip implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Center(new Tooltip.Builder()
                .withText(new StringTextComponent("Helloooooo"))
                .build(new Text.Builder("Hello!")));
    }
}
