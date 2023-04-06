package com.github.wintersteve25.tau.tests;

import com.github.wintersteve25.tau.components.*;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.Size;

public class TestRow implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Center(new Row.Builder()
            .withSpacing(5)
            .build(
                new Sized(
                    Size.staticSize(100, 20),
                    new Button.Builder().build(new Center(new Text.Builder("Hello")))
                ),
                new Text.Builder("Hello"),
                new Text.Builder("This is a row")
            ));
    }
}
