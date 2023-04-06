package com.github.wintersteve25.tau.tests;

import com.github.wintersteve25.tau.components.Positioned;
import com.github.wintersteve25.tau.components.Text;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.Vector2i;

public class TestPositioned implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Positioned(
            new Vector2i(100, 20),
            new Text.Builder("Positioned")
        );
    }
}
