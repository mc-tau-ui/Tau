package com.github.wintersteve25.tau.tests;

import com.github.wintersteve25.tau.components.*;
import com.github.wintersteve25.tau.theme.Theme;
import com.github.wintersteve25.tau.utils.Size;
import net.minecraft.network.chat.TextComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;

public class TestTooltip implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Stack(
            new Center(new Sized(
                Size.staticSize(200, 200),
                new Tooltip.Builder()
                    .withText(new TextComponent("Test"))
                    .build(new Container.Builder())
            )),
            new Container.Builder()
        );
    }
}
