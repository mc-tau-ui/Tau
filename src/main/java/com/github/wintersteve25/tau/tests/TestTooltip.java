package com.github.wintersteve25.tau.tests;

import net.minecraft.util.text.StringTextComponent;
import com.github.wintersteve25.tau.components.Center;
import com.github.wintersteve25.tau.components.Text;
import com.github.wintersteve25.tau.components.Tooltip;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;

public class TestTooltip implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Center(new Tooltip.Builder()
                .withText(new StringTextComponent("Helloooooo"))
                .build(new Text.Builder("Hello!")));
    }
}
