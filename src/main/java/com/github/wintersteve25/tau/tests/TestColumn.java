package com.github.wintersteve25.tau.tests;

import com.github.wintersteve25.tau.components.Center;
import com.github.wintersteve25.tau.components.Column;
import com.github.wintersteve25.tau.components.Text;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.theme.Theme;

public class TestColumn implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(
            new Column.Builder().build(
                new Text.Builder("Hello1"),            
                new Text.Builder("Hello2"),            
                new Text.Builder("Hello3"),            
                new Text.Builder("Hello4")            
        ));
    }
}
