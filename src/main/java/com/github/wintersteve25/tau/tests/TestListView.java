package com.github.wintersteve25.tau.tests;

import com.github.wintersteve25.tau.components.*;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.layout.LayoutSetting;
import com.github.wintersteve25.tau.utils.Color;
import com.github.wintersteve25.tau.utils.Size;
import com.github.wintersteve25.tau.utils.Vector2i;

public class TestListView implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Center(
            new Sized(
                Size.staticSize(new Vector2i(200, 100)),
                new Container.Builder()
                    .withColor(new Color(0x88000000))
                    .withChild(
                        new ListView.Builder()
                            .withAlignment(LayoutSetting.CENTER)
                            .build(
                                new Text.Builder("Hello"),
                                new Text.Builder("Hello Again"),
                                new Text.Builder("Hello Again"),
                                new Text.Builder("Hello Again"),
                                new Text.Builder("Hello Again"),
                                new Text.Builder("Hello Again"),
                                new Text.Builder("Hello Again"),
                                new Text.Builder("Hello Again"),
                                new Text.Builder("Hello Again"),
                                new Text.Builder("Hello Again"),
                                new Text.Builder("Hello Again"),
                                new Text.Builder("Hello Again"),
                                new Sized(
                                    Size.staticSize(new Vector2i(60, 20)),
                                    new Button.Builder()
                                        .withOnPress(() -> {})
                                        .build(new Center(new Text.Builder("A Button")))
                                )
                            ))
                    ));
    }
}
