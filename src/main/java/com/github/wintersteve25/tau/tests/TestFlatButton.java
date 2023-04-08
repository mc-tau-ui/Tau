package com.github.wintersteve25.tau.tests;

import com.github.wintersteve25.tau.components.*;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.Size;

public class TestFlatButton implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Center(new Sized(
                Size.staticSize(100, 20),
                new FlatButton.Builder()
                        .withOnPress((b) -> {})
                        .build(new Center(new Text.Builder("Button")))
        ));
    }
}
