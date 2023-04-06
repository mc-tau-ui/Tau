package com.github.wintersteve25.tau.tests;

import com.github.wintersteve25.tau.components.Center;
import com.github.wintersteve25.tau.components.Text;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;

public class TestCenter implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Center(new Text.Builder("Center!"));
    }
}
