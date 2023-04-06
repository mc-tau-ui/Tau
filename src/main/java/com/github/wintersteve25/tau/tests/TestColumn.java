package wintersteve25.tau.tests;

import wintersteve25.tau.components.Center;
import wintersteve25.tau.components.Column;
import wintersteve25.tau.components.Text;
import wintersteve25.tau.components.base.UIComponent;
import wintersteve25.tau.layout.Layout;

public class TestColumn implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Center(
            new Column.Builder().build(
                new Text.Builder("Hello1"),            
                new Text.Builder("Hello2"),            
                new Text.Builder("Hello3"),            
                new Text.Builder("Hello4")            
        ));
    }
}
