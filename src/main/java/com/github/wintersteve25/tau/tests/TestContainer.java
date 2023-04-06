package wintersteve25.tau.tests;

import wintersteve25.tau.components.Center;
import wintersteve25.tau.components.Container;
import wintersteve25.tau.components.Text;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.Color;

public class TestContainer implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Container.Builder()
            .withColor(Color.BLUE)
            .withChild(new Center(new Text.Builder("Blue!!")));
    }
}
