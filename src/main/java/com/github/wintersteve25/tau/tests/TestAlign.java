package com.github.wintersteve25.tau.tests;

import com.github.wintersteve25.tau.components.Align;
import com.github.wintersteve25.tau.components.Text;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.layout.LayoutSetting;

public class TestAlign implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Align.Builder()
                .withVertical(LayoutSetting.END)
                .withHorizontal(LayoutSetting.CENTER)
                .build(new Text.Builder("Whats up?"));
    }
}
