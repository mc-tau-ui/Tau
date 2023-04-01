package wintersteve25.tau.utils;

public class Vector2i {
    public int x;
    public int y;

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void add(Vector2i other) {
        this.x += other.x;
        this.y += other.y;
    }
    
    public static Vector2i zero() {
        return new Vector2i(0, 0);
    }

    public boolean outside(Vector2i other) {
        return x > other.x || y > other.y;
    }
}