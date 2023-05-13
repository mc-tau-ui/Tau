package com.github.wintersteve25.tau.tests;

import com.github.wintersteve25.tau.components.Center;
import com.github.wintersteve25.tau.components.Render;
import com.github.wintersteve25.tau.components.Sized;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.theme.Theme;
import com.github.wintersteve25.tau.utils.ItemRenderProvider;
import com.github.wintersteve25.tau.utils.Size;
import net.minecraft.world.item.Items;

public class TestRender implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(new Sized(
            Size.staticSize(16, 16),
            new Render(new ItemRenderProvider(Items.GRASS_BLOCK))
        ));
    }
}
