package wintersteve25.tau.tests;

import wintersteve25.tau.components.*;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.layout.LayoutSetting;
import wintersteve25.tau.utils.Color;
import wintersteve25.tau.utils.Size;
import wintersteve25.tau.utils.Vector2i;

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
