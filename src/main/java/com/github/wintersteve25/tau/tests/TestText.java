package com.github.wintersteve25.tau.tests;

import com.github.wintersteve25.tau.components.Center;
import com.github.wintersteve25.tau.components.Sized;
import com.github.wintersteve25.tau.components.Text;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.theme.Theme;
import com.github.wintersteve25.tau.utils.Size;

public class TestText implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(
            new Text.Builder("Wow hello a lot of textttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt")
                    .withOverflowBehaviour(Text.OverflowBehaviour.ELLIPSIS)
        );
    }
}
