package com.github.wintersteve25.tau.tests;

import com.github.wintersteve25.tau.components.Align;
import com.github.wintersteve25.tau.components.Padding;
import com.github.wintersteve25.tau.components.Text;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.layout.LayoutSetting;
import com.github.wintersteve25.tau.utils.Pad;

public class TestPadding implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Align.Builder()
                .withVertical(LayoutSetting.START)
                .withHorizontal(LayoutSetting.END)
                .build(new Padding(
                    new Pad.Builder().withRight(10).withTop(10).build(), 
                    new Text.Builder("Top right corner")));
    }
}
