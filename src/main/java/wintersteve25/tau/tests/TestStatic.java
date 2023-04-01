package wintersteve25.tau.tests;

import wintersteve25.tau.components.*;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;

public class TestStatic implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Button.Builder()
                .withOnPress(() -> System.out.println("Hello"))
                .build(new Text.Builder("Hello"));
    }
}
