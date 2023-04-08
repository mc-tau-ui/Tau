package com.github.wintersteve25.tau.tests;

import com.github.wintersteve25.tau.components.*;
import com.github.wintersteve25.tau.utils.Color;
import com.github.wintersteve25.tau.utils.Size;
import net.minecraft.util.text.StringTextComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;

public class TestTooltip implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Stack(
            new Center(new Sized(
                Size.staticSize(200, 200),
                new Tooltip.Builder()
                    .withText(new StringTextComponent("Test"))
                    .build(new Container.Builder().withColor(Color.RED))
            )),
            new Container.Builder()
                .withColor(Color.WHITE)
        );
    }
}
