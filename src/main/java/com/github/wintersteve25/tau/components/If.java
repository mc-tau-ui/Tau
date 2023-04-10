package com.github.wintersteve25.tau.components;

import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;

import java.util.function.Supplier;

public class If implements UIComponent {
    
    private final Supplier<Boolean> condition;
    private final UIComponent child;

    public If(Supplier<Boolean> condition, UIComponent child) {
        this.condition = condition;
        this.child = child;
    }

    @Override
    public UIComponent build(Layout layout) {
        if (condition.get()) {
            return child;
        }
        
        return new Container.Builder()
                .noColor();
    }
}
