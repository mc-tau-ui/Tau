package com.github.wintersteve25.tau.utils.transformations;

import com.mojang.blaze3d.vertex.PoseStack;
import org.joml.Vector3f;

public class TranslationTransform implements Transformation {
    
    private final Vector3f translation;

    public TranslationTransform(Vector3f translation) {
        this.translation = translation;
    }

    @Override
    public void transform(PoseStack PoseStack) {
        PoseStack.translate(translation.x(), translation.y(), translation.z());
    }
}
