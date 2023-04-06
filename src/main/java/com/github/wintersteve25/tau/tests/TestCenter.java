package wintersteve25.tau.tests;

import wintersteve25.tau.components.Center;
import wintersteve25.tau.components.Text;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;

public class TestCenter implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Center(new Text.Builder("Center!"));
    }
}
