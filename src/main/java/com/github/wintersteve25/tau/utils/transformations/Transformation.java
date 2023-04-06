package com.github.wintersteve25.tau.utils.transformations;

import com.mojang.blaze3d.matrix.MatrixStack;

public interface Transformation {
    void transform(MatrixStack matrixStack);
}
