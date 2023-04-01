package wintersteve25.tau.utils;

public final class Color {
    public static final Color WHITE = new Color(16121855);
    
    private final int decimal;
    private final boolean hasTransparency;
    
    public Color(int decimal) {
        this.decimal = decimal;
        // TODO
        this.hasTransparency = false;
    }

    public boolean hasTransparency() {
        return hasTransparency;
    }

    public int getDecimal() {
        return decimal;
    }
}
