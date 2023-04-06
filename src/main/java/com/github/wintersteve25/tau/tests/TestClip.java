package wintersteve25.tau.tests;

import wintersteve25.tau.components.Center;
import wintersteve25.tau.components.Clip;
import wintersteve25.tau.components.Text;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;
import wintersteve25.tau.utils.Size;
import wintersteve25.tau.utils.Vector2i;

public class TestClip implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Center(
            new Clip(
                new Text.Builder("HelloAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"), 
                new Vector2i(0, 0), 
                Size.percentage(0.65f, 1f)));
    }
}
