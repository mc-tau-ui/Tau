package wintersteve25.tau.tests;

import wintersteve25.tau.components.*;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.Color;
import wintersteve25.tau.utils.FlexSizeBehaviour;

public class TextPanel implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Center(new Container.Builder()
                .withColor(Color.WHITE)
                .withChild(new Column.Builder()
                        .withSpacing(5)
                        .withSizeBehaviour(FlexSizeBehaviour.MIN)
                        .build(
                                new Center(new Text.Builder("Hello")),
                                new Center(new Text.Builder("Hello x2"))
                        )));
    }
}
