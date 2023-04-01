package wintersteve25.tau.utils;

@FunctionalInterface
public interface Size {
    Size ZERO = (s) -> Vector2i.zero();
    
    Vector2i get(Vector2i maxSize);
    
    static Size percentage(float percentage) {
        if (percentage < 0 || percentage > 1) {
            return ZERO;
        }
        
        return size -> new Vector2i(Math.round(size.x * percentage), Math.round(size.y * percentage));
    }
    
    static Size staticSize(Vector2i size) {
        return s -> size;
    }
}
