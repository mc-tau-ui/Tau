package com.github.wintersteve25.tau.tests;

import com.github.wintersteve25.tau.components.Align;
import com.github.wintersteve25.tau.components.Padding;
import com.github.wintersteve25.tau.components.Text;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.layout.LayoutSetting;
import com.github.wintersteve25.tau.utils.Pad;

public class TestDynamic extends DynamicUIComponent {
    private int t;

    @Override
    public void tick() {
        t++;
        if (layout == null) return;
        rebuild();
    }

    @Override
    public UIComponent build(Layout layout) {
        return new Align.Builder()
                .withVertical(LayoutSetting.START)
                .withHorizontal(LayoutSetting.END)
                .build(new Padding(new Pad.Builder().right(10).top(10).build(), new Text.Builder(String.valueOf(t))));
    }
}