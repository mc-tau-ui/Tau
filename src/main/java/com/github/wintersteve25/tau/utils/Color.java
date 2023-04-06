package com.github.wintersteve25.tau.utils;

public final class Color {
    public static final Color WHITE = new Color(0xFFFFFFFF);
    public static final Color GRAY = new Color(0xFF787878);
    public static final Color RED = new Color(0xFFFF0000);
    public static final Color GREEN = new Color(0xFF00FF00);
    public static final Color BLUE = new Color(0xFF0000FF);
    
    private final int hex;
    private final boolean hasTransparency;

    public Color(int hex) {
        this.hex = hex;
        this.hasTransparency = false;
    }
    
    public static Color fromRGBA(int r, int g, int b, int a) {
        return new Color((a << 24) + (r << 16) + (g << 8) + b);
    }

    public boolean hasTransparency() {
        return hasTransparency;
    }

    public int getAARRGGBB() {
        return hex;
    }
}