package wintersteve25.tau.tests;

import wintersteve25.tau.components.Row;
import wintersteve25.tau.components.Text;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;

public class TextPanel implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Row(
                5,
                new Text.Builder("Hello"),
                new Text.Builder("Hello x2")
        );
    }
}
