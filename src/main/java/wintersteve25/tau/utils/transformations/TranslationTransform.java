package wintersteve25.tau.utils.transformations;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.math.vector.Vector3f;
import wintersteve25.tau.utils.Vector2i;

public class TranslationTransform implements Transformation {
    
    private final Vector3f translation;

    public TranslationTransform(Vector3f translation) {
        this.translation = translation;
    }

    @Override
    public void transform(MatrixStack matrixStack) {
        matrixStack.translate(translation.x(), translation.y(), translation.z());
    }
}
