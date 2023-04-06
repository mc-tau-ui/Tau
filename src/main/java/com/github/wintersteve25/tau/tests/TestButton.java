package wintersteve25.tau.tests;

import wintersteve25.tau.components.Button;
import wintersteve25.tau.components.Center;
import wintersteve25.tau.components.Sized;
import wintersteve25.tau.components.Text;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.Size;

public class TestButton implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Center(new Sized(
            Size.staticSize(100, 20),
            new Button.Builder()
                .build(new Center(new Text.Builder("Button")))
        ));
    }
}
