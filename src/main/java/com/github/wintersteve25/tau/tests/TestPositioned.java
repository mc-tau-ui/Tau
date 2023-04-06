package wintersteve25.tau.tests;

import wintersteve25.tau.components.Positioned;
import wintersteve25.tau.components.Text;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.Vector2i;

public class TestPositioned implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Positioned(
            new Vector2i(100, 20),
            new Text.Builder("Positioned")
        );
    }
}
