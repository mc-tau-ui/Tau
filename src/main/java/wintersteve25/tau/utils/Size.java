package wintersteve25.tau.utils;

import wintersteve25.tau.Tau;

@FunctionalInterface
public interface Size {
    Size ZERO = (s) -> Vector2i.zero();
    
    Vector2i get(Vector2i maxSize);
    
    static Size percentage(float percentage) {
        if (percentage < 0 || percentage > 1) {
            Tau.LOGGER.error("Size percentage can not be less than 0 or greater than 1");
            return ZERO;
        }
        
        return size -> new Vector2i(Math.round(size.x * percentage), Math.round(size.y * percentage));
    }
    
    static Size percentage(float percentageX, float percentageY) {
        if (percentageX < 0 || percentageX > 1 || percentageY < 0 || percentageY > 1) {
            Tau.LOGGER.error("Size percentage can not be less than 0 or greater than 1");
            return ZERO;
        }

        return size -> new Vector2i(Math.round(size.x * percentageX), Math.round(size.y * percentageY));
    }
    
    static Size staticSize(Vector2i size) {
        return s -> size;
    }

    static Size staticSize(int width, int height) {
        Vector2i size = new Vector2i(width, height);
        return s -> size;
    }
}
