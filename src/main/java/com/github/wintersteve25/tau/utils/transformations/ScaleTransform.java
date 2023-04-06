package wintersteve25.tau.utils.transformations;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.math.vector.Vector3f;
import wintersteve25.tau.utils.Vector2i;

public class ScaleTransform implements Transformation {
    
    private final Vector3f scale;

    public ScaleTransform(Vector3f scale) {
        this.scale = scale;
    }

    @Override
    public void transform(MatrixStack matrixStack) {
        matrixStack.scale(scale.x(), scale.y(), scale.z());
    }
}
