package com.github.wintersteve25.tau.tests;

import com.github.wintersteve25.tau.components.Center;
import com.github.wintersteve25.tau.components.Sized;
import com.github.wintersteve25.tau.components.WidgetWrapper;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.theme.Theme;
import com.github.wintersteve25.tau.utils.Size;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.gui.widget.Slider;

public class TestWidgetWrapper implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(new Sized(
            Size.staticSize(200, 20),
            new WidgetWrapper(new Slider(
                0, 0, 0, 0,
                new StringTextComponent("Prefix"),
                new StringTextComponent("Suffix"),
                0, 1, 0, true, true, (a) -> {}
            ))
        ));
    }
}
