package com.github.wintersteve25.tau.utils;

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
    
    public Vector2i addNew(Vector2i other) {
        return new Vector2i(x + other.x, y + other.y);
    }

    public boolean outside(Vector2i other) {
        return x > other.x || y > other.y;
    }

    public static Vector2i zero() {
        return new Vector2i(0, 0);
    }
    
    public static boolean within(int mouseX, int mouseY, Vector2i position, Vector2i size) {
        return mouseX > position.x && mouseX < position.x + size.x && mouseY > position.y && mouseY < position.y + size.y;
    }

    public static boolean within(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }
}