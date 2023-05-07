package com.github.wintersteve25.tau.tests;

import com.github.wintersteve25.tau.components.*;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.layout.LayoutSetting;
import com.github.wintersteve25.tau.theme.Theme;
import com.github.wintersteve25.tau.utils.Color;
import com.github.wintersteve25.tau.utils.Pad;
import com.github.wintersteve25.tau.utils.Size;
import net.minecraft.client.gui.IGuiEventListener;

public class TestDynamic extends DynamicUIComponent implements IGuiEventListener {
    private boolean clicked;

    @Override
    public UIComponent build(Layout layout, Theme theme) {
        clicked = !clicked;
        
        if (clicked) {
            return new Container.Builder();
        }
        
        return new Center(new Sized(
            Size.staticSize(200, 200),
            new Container.Builder()
        ));
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        rebuild();
        return IGuiEventListener.super.mouseClicked(pMouseX, pMouseY, pButton);
    }
    
    private static class Test implements UIComponent {
        
        @Override
        public UIComponent build(Layout layout, Theme theme) {
            return null;
        }
    }
}