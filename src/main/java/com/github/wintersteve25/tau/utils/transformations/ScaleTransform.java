package com.github.wintersteve25.tau.utils.transformations;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.util.math.vector.Vector3f;
import com.github.wintersteve25.tau.utils.Vector2i;

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
