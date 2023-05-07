package com.github.wintersteve25.tau.tests;

import com.github.wintersteve25.tau.components.Transform;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.theme.Theme;
import com.github.wintersteve25.tau.utils.transformations.ScaleTransform;
import com.github.wintersteve25.tau.utils.transformations.TranslationTransform;
import net.minecraft.util.math.vector.Vector3f;

public class TestTransform implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Transform(
            new TestTexture(),
            new ScaleTransform(new Vector3f(2, 2, 1)),    
            new TranslationTransform(new Vector3f(-93, -88, 0))    
        );
    }
}
