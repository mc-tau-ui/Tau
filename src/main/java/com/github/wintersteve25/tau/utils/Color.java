package com.github.wintersteve25.tau.utils;

public final class Color {
    public static final Color WHITE = new Color(0xFFFFFFFF);
    public static final Color GRAY = new Color(0xFF787878);
    public static final Color BLACK = new Color(0xFF000000);
    public static final Color RED = new Color(0xFFFF0000);
    public static final Color GREEN = new Color(0xFF00FF00);
    public static final Color BLUE = new Color(0xFF0000FF);
    
    public static final Color JADE_GREEN = new Color(0xB8DBD6);
    public static final Color LIGHT_CYAN = new Color(0x7ED0D6);
    
    private final int hex;
    private final boolean hasTransparency;

    public Color(int hex) {
        this.hex = hex;
        this.hasTransparency = ((hex >> 24) & 0xFF) < 255;
    }
    
    private Color(int hex, boolean hasTransparency) {
        this.hex = hex;
        this.hasTransparency = hasTransparency;
    }
    
    public static Color fromRGBA(int r, int g, int b, int a) {
        return new Color((a << 24) + (r << 16) + (g << 8) + b, a < 255);
    }

    public boolean hasTransparency() {
        return hasTransparency;
    }

    public int getAARRGGBB() {
        return hex;
    }
}