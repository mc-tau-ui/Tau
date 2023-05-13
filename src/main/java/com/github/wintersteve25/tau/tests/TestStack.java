package com.github.wintersteve25.tau.tests;

import com.github.wintersteve25.tau.components.*;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.theme.Theme;
import com.github.wintersteve25.tau.utils.Size;
import com.github.wintersteve25.tau.utils.Vector2i;
import net.minecraft.network.chat.TextComponent;

public class TestStack implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Stack(
                new Container.Builder(),
                new Center(new Sized(
                        Size.staticSize(new Vector2i(100, 20)),
                        new TextField.Builder()
                                .withMessage(new TextComponent("Hello"))
                                .withHintText(new TextComponent("Hello!")))
                ));
    }
}
