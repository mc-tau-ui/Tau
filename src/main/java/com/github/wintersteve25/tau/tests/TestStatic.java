package com.github.wintersteve25.tau.tests;

import net.minecraft.util.text.StringTextComponent;
import com.github.wintersteve25.tau.components.*;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.Color;
import com.github.wintersteve25.tau.utils.Size;
import com.github.wintersteve25.tau.utils.Vector2i;

public class TestStatic implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Stack(
            new Container.Builder().withColor(Color.WHITE),
            new Center(new Sized(
                    Size.staticSize(new Vector2i(100, 20)),
                    new TextField.Builder()
                            .withMessage(new StringTextComponent("Hello"))
                            .withHintText(new StringTextComponent("Hello!")))
            ));
    }
}