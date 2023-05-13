package com.github.wintersteve25.tau.utils.transformations;

import com.mojang.blaze3d.vertex.PoseStack;
import org.joml.Vector3f;

public class ScaleTransform implements Transformation {
    
    private final Vector3f scale;

    public ScaleTransform(Vector3f scale) {
        this.scale = scale;
    }

    @Override
    public void transform(PoseStack PoseStack) {
        PoseStack.scale(scale.x(), scale.y(), scale.z());
    }
}
